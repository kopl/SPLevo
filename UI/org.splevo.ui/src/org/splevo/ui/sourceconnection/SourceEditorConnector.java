/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.ui.sourceconnection;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.texteditor.ITextEditor;
import org.splevo.ui.sourceconnection.jdt.JavaEditorConnector;
import org.splevo.vpm.software.JavaSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.software.SourceLocation;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * A connector to open source locations in an appropriate editor.
 *
 * This connector can and should be used by any action purposed to open a variant or variation
 * points implementation in an editor.
 */
public class SourceEditorConnector {

    private static Logger logger = Logger.getLogger(SourceEditorConnector.class);

    private static JavaEditorConnector javaEditorConnector = new JavaEditorConnector();

    /**
     * Open the location of a variationPoint.
     *
     * @param variationPoint
     *            The variationPoint to show the code for.
     * @param filesOpen
     *            The list of files that have been open before. This state must be managed outside
     *            to respect the current actions context.
     */
    public static void openVariationPoint(VariationPoint variationPoint, List<String> filesOpen) {
        for (Variant variant : variationPoint.getVariants()) {
            openVariant(variant, filesOpen);
        }
    }

    /**
     * Open the implementing elements of a variant.
     *
     * @param variant
     *            The variant to show the code for.
     * @param filesOpen
     *            The list of files that have been open before. This state must be managed outside
     *            to respect the current actions context.
     */
    public static void openVariant(Variant variant, List<String> filesOpen) {

        String message = String.format("Implementation of Variant %s", variant.getVariantId());

        for (SoftwareElement softwareElement : variant.getImplementingElements()) {
            if (softwareElement instanceof JavaSoftwareElement) {

                ITextEditor editor = javaEditorConnector.openEditor((JavaSoftwareElement) softwareElement);
                prepareEditorIfFileNotOpen(filesOpen, softwareElement, editor);
                javaEditorConnector.highlightInTextEditor(editor, softwareElement, message, variant);
            }
        }
    }

    private static void prepareEditorIfFileNotOpen(List<String> filesOpen, SoftwareElement softwareElement,
            ITextEditor editor) {
        String filePath = softwareElement.getSourceLocation().getFilePath();
        if (!filesOpen.contains(filePath)) {
            javaEditorConnector.resetLocationHighlighting(editor);
            filesOpen.add(filePath);
            javaEditorConnector.jumpToLocation(editor, softwareElement.getSourceLocation());
        }
    }

    /**
     * Gets the textual representation of a {@link VariationPoint}.
     *
     * @param variant
     *            The {@link Variant} to get the alternative code for.
     * @return Get the code for a variant.
     */
    public static List<String> getCodeForVariant(Variant variant) {
        List<String> elementCodes = Lists.newLinkedList();
        if (variant != null) {
            for (SoftwareElement e : variant.getImplementingElements()) {
                elementCodes.add(appendCodeForSourceLocation(e.getSourceLocation()));
            }
        }
        return elementCodes;
    }

    /**
     * Extracts the code from the file by a given {@link SourceLocation}.
     *
     * @param sourceLocation
     *            The {@link SourceLocation}.
     */
    private static String appendCodeForSourceLocation(SourceLocation sourceLocation) {
        String code = null;
        IPath location = Path.fromOSString(sourceLocation.getFilePath());
        try {
            code = FileUtils.readFileToString(location.toFile());
        } catch (IOException e) {
            logger.error("Error while reading source file", e);
            return null;
        }

        return code.substring(sourceLocation.getStartPosition(), sourceLocation.getEndPosition());
    }

}
