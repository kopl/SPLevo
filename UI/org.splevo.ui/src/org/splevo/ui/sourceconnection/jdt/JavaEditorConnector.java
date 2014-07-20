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
package org.splevo.ui.sourceconnection.jdt;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.SimpleMarkerAnnotation;
import org.splevo.ui.refinementbrowser.RefinementDetailsView;
import org.splevo.vpm.software.JavaSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.software.SourceLocation;
import org.splevo.vpm.variability.Variant;

import com.google.common.collect.Lists;

/**
 * A connector to open a {@link JavaSoftwareElement} in the JDT java editor.
 *
 * It is recommended to use the SourceEditorConnector. Using this java editor specific connector
 * directly should be done if you really need this direct access.
 *
 * The connector provides three actions to interact with the JDT connector:
 * <ol>
 * <li>Open resource file the editor</li>
 * <li>Reset current highlighting</li>
 * <li>Set new highlighting</li>
 * </ol>
 * Those actions are separated to provide enough control to reset a file and highlight several code
 * locations with individual messages afterwards.
 */
public class JavaEditorConnector {

    private static Logger logger = Logger.getLogger(RefinementDetailsView.class);

    public static final String LOCATION_ANNOTATION_VARIANT_MULTIPLE = "org.splevo.ui.annotations.variant.multiple";
    public static final String LOCATION_ANNOTATION_VARIANT_SINGLE = "org.splevo.ui.annotations.variant.single";
    public static final String LOCATION_MARKER = "org.splevo.ui.markers.codelocationmarker.variant";
    public static final String LOCATION_MARKER_VARIANT_SINGLE = "org.splevo.ui.markers.codelocation.variant.single";
    public static final String LOCATION_MARKER_ATTRIBUTE_VARIANT = "org.splevo.ui.markers.attribute.variant";

    /**
     * Open the java editor for a specific source location.
     *
     * @param softwareElement
     *            The software element to open in the JDT Java Editor.
     *
     * @return The opened editor.
     */
    public ITextEditor openEditor(JavaSoftwareElement softwareElement) {

        SourceLocation sourceLocation = softwareElement.getSourceLocation();
        if (sourceLocation != null) {
            return openJavaEditor(sourceLocation, true);
        } else {
            logger.warn("No source location accessible.");
        }

        return null;
    }

    /**
     * Open a source region in an editor. The flag can control if the same file can be opened
     * multiple times. The file is identified by it's filename. This might lead to problems in case
     * of the customized product copies.
     *
     *
     * @param sourceLocation
     *            The source region.
     * @param openFileMultipleTimes
     *            The true/false flag if a file can be opened multiple times.
     * @return The opened {@link ITextEditor}
     */
    private ITextEditor openJavaEditor(final SourceLocation sourceLocation, final boolean openFileMultipleTimes) {

        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IPath location = Path.fromOSString(sourceLocation.getFilePath());
        final IFile inputFile = workspace.getRoot().getFileForLocation(location);
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

        ITextEditor iEditorPart = null;
        if (!openFileMultipleTimes) {
            // Look for an opened editor with the
            // file in it
            for (IEditorReference editorReference : PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                    .getActivePage().getEditorReferences()) {

                IEditorPart editorTmp = editorReference.getEditor(false);

                if (editorTmp instanceof ITextEditor) {
                    ITextEditor abstractTextEditor = (ITextEditor) editorTmp;
                    if (inputFile.getName().equalsIgnoreCase(abstractTextEditor.getEditorInput().getName())) {
                        iEditorPart = (ITextEditor) editorTmp;
                        break;
                    }
                }
            }
        }
        // If no opened editor, then open a new one
        if (iEditorPart == null) {
            try {
                iEditorPart = (ITextEditor) IDE.openEditor(activePage, inputFile, false);
            } catch (Exception e) {
                logger.error(e);
            }
        }

        return iEditorPart;
    }

    /**
     * Highlights given area in the specified editor with a given message and background color.
     * Supports multiple highlighting.
     *
     * @param editor
     *            The {@link IEditorPart} to highlight.
     * @param message
     *            The {@link String} message to be displayed.
     * @param variant
     *            The {@link Variant} to set for the marker as attribute with the id
     *            {@link JavaEditorConnector#LOCATION_MARKER_ATTRIBUTE_VARIANT}. If null provided,
     *            the attribute is not set.
     */
    public void highlightInTextEditor(ITextEditor editor, String message, Variant variant) {

        int start = Integer.MAX_VALUE;
        int end = 0;
        for (SoftwareElement softwareElement : variant.getImplementingElements()) {
            SourceLocation sourceLocation = softwareElement.getSourceLocation();
            int startElement = sourceLocation.getStartPosition();
            int endElement = sourceLocation.getEndPosition();
            if (startElement < start) {
                start = startElement;
            }
            if (endElement > end) {
                end = endElement;
            }
        }

        int offset = start;
        int length = end - start;
        TextSelection selection = new TextSelection(offset, length);

        try {
            IMarker marker = createMarker(editor, message, variant);
            createLocationAnnotation(marker, selection, editor, variant);
        } catch (CoreException e) {
            logger.error("Could't clear and create text markers.", e);
        }

    }

    /**
     * Create a new source location annotation.
     *
     * @param marker
     *            The marker to assign to the annotation.
     * @param selection
     *            The selection to highlight.
     * @param editor
     *            The editor to set the annotations in.
     * @param variant
     *            The variant to decide about the marker presentation.
     */
    private void createLocationAnnotation(IMarker marker, ITextSelection selection, ITextEditor editor, Variant variant) {

        IDocumentProvider idp = editor.getDocumentProvider();
        IEditorInput editorInput = editor.getEditorInput();
        IDocument document = idp.getDocument(editorInput);
        IAnnotationModel annotationModel = idp.getAnnotationModel(editorInput);

        String annotationType = getAnnotationType(variant);
        SimpleMarkerAnnotation annotation = new SimpleMarkerAnnotation(annotationType, marker);
        annotationModel.connect(document);
        annotationModel.addAnnotation(annotation, new Position(selection.getOffset(), selection.getLength()));
        annotationModel.disconnect(document);
    }

    private String getAnnotationType(Variant variant) {
        int variantCount = variant.getVariationPoint().getVariants().size();
        if (variantCount == 1) {
            return LOCATION_ANNOTATION_VARIANT_SINGLE;
        } else {
            return LOCATION_ANNOTATION_VARIANT_MULTIPLE;
        }
    }

    /**
     * Remove all existing location highlighting for the file currently opened in an editor.
     *
     * To gain access to an editor, use {@link JavaEditorConnector#openEditor(JavaSoftwareElement)}.
     *
     * @param editor
     *            The editor to reset the annotations in.
     */
    @SuppressWarnings("unchecked")
    public void resetLocationHighlighting(ITextEditor editor) {

        IDocumentProvider idp = editor.getDocumentProvider();
        IAnnotationModel annotationModel = idp.getAnnotationModel(editor.getEditorInput());

        Iterator<Annotation> annotationIterator = annotationModel.getAnnotationIterator();
        for (Annotation annotation : Lists.newArrayList(annotationIterator)) {
            if (isCodeLocationAnnotation(annotation)) {
                annotationModel.removeAnnotation(annotation);
            }
        }
    }

    private boolean isCodeLocationAnnotation(Annotation annotation) {
        return LOCATION_ANNOTATION_VARIANT_MULTIPLE.equals(annotation.getType())
                || LOCATION_ANNOTATION_VARIANT_SINGLE.equals(annotation.getType());
    }

    /**
     * Highlight a code location with a given message in an editor.
     *
     * To gain access to an editor, use {@link JavaEditorConnector#openEditor(JavaSoftwareElement)}.
     *
     * To reset existing highlighting, use
     * {@link JavaEditorConnector#resetLocationHighlighting(ITextEditor)}.<br>
     * Note: It is up to you to decide when to reset of you want to highlight several locations at
     * the same time.
     *
     * @param editor
     *            The editor to set the highlighting in.
     * @param message
     *            The message to mark the text with.
     * @return The marker for the given message.
     * @throws CoreException
     *             Throws {@link CoreException} for invalid resources.
     */
    private IMarker createMarker(ITextEditor editor, String message, Variant variant) throws CoreException {
        IFile inputFile = getEditorFile(editor);
        if (inputFile != null) {
            IMarker marker = inputFile.createMarker(LOCATION_MARKER);
            marker.setAttribute(IMarker.MESSAGE, message);
            if (variant != null) {
                marker.setAttribute(LOCATION_MARKER_ATTRIBUTE_VARIANT, VariantRegistry.register(variant));
            }
            return marker;
        } else {
            logger.warn("Editor is not handling a file");
            return null;
        }
    }

    /**
     * Jump to a specific location (offset) in an editor.
     *
     * @param editor
     *            The editor to select the position in.
     * @param location
     *            The location to jump to.
     */
    public void jumpToLocation(ITextEditor editor, SourceLocation location) {
        editor.selectAndReveal(location.getStartPosition(), 0);
    }

    /**
     * Get the file currently opened in the editor.
     *
     * @param editor
     *            The editor to access the file.
     * @return The IFile resource or null if non contained or editor is not a file editor.
     */
    private IFile getEditorFile(ITextEditor editor) {
        IFile inputFile = null;
        if (editor.getEditorInput() instanceof FileEditorInput) {
            FileEditorInput input = (FileEditorInput) editor.getEditorInput();
            inputFile = input.getFile();
        }
        return inputFile;
    }
}
