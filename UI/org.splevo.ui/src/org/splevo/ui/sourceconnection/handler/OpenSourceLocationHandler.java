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
package org.splevo.ui.sourceconnection.handler;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.ITextEditor;
import org.splevo.ui.sourceconnection.jdt.JavaEditorConnector;
import org.splevo.vpm.software.JavaSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.software.SourceLocation;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * Action to open the implementing locations of a selected variant in the java editor.
 */
public class OpenSourceLocationHandler extends AbstractHandler {

    private Logger logger = Logger.getLogger(OpenSourceLocationHandler.class);

    /** The connector to the java editor. */
    protected JavaEditorConnector javaEditorConnector = new JavaEditorConnector();

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        ISelection curSelection = HandlerUtil.getCurrentSelection(event);
        if (curSelection != null && curSelection instanceof IStructuredSelection) {
            IStructuredSelection selection = (IStructuredSelection) curSelection;

            List<String> filesResetBefore = Lists.newArrayList();

            for (Object selectedItem : selection.toList()) {
                if (selectedItem instanceof Variant) {
                    openVariant((Variant) selectedItem, filesResetBefore);
                } else if (selectedItem instanceof VariationPoint) {
                    openVariationPoint((VariationPoint) selectedItem, filesResetBefore);
                }
            }

        }
        return null;
    }

    /**
     * Open the location of a variationPoint.
     *
     * @param variationPoint
     *            The variationPoint to show the code for.
     */
    private void openVariationPoint(VariationPoint variationPoint, List<String> filesResetBefore) {
        for (Variant variant : variationPoint.getVariants()) {
            openVariant(variant, filesResetBefore);
        }
    }

    /**
     * Open the implementing elements of a variant.
     *
     * @param variant
     *            The variant to show the code for.
     * @param
     */
    private void openVariant(Variant variant, List<String> filesResetBefore) {

        String message = String.format("Implementation of Variant %s", variant.getVariantId());

        for (SoftwareElement softwareElement : variant.getImplementingElements()) {
            if (softwareElement instanceof JavaSoftwareElement) {

                ITextEditor editor = javaEditorConnector.openEditor((JavaSoftwareElement) softwareElement);

                String filePath = softwareElement.getSourceLocation().getFilePath();
                if (!filesResetBefore.contains(filePath)) {
                    javaEditorConnector.resetLocationHighlighting(editor);
                    filesResetBefore.add(filePath);
                }

                javaEditorConnector.highlightInTextEditor(editor, softwareElement, message);
            }
        }
    }

    /**
     * Gets the textual representation of a {@link VariationPoint}.
     *
     * @param vp
     *            The {@link VariationPoint}.
     * @param exclude
     *            This {@link Variant} wont be included in the string. Null if nothing has to be
     *            excluded.
     * @return The {@link String} contents of the {@link VariationPoint}.
     */
    @SuppressWarnings("unused")
    private String getCodeForVariationPoint(VariationPoint vp, Variant exclude) {
        StringBuilder code = new StringBuilder();
        for (Variant v : vp.getVariants()) {
            if (v == null || v.equals(exclude)) {
                continue;
            }

            for (SoftwareElement e : v.getImplementingElements()) {
                code.append(v.getVariantId());
                code.append(":\n");
                code.append(getCodeForSourceLocation(e.getSourceLocation()));
                code.append("\n\n");
            }
        }
        return code.toString();
    }

    /**
     * Extracts the code from the file by a given {@link SourceLocation}.
     *
     * @param sourceLocation
     *            The {@link SourceLocation}.
     * @return The textual content.
     */
    private String getCodeForSourceLocation(SourceLocation sourceLocation) {
        String code = "";
        IPath location = Path.fromOSString(sourceLocation.getFilePath());
        try {
            code = FileUtils.readFileToString(location.toFile());
        } catch (IOException e) {
            logger.error("Error while reading source file", e);
        }

        return code.substring(sourceLocation.getStartPosition(), sourceLocation.getEndPosition());
    }
}
