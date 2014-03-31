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
import org.eclipse.core.resources.IResource;
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
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.SimpleMarkerAnnotation;
import org.splevo.ui.refinementbrowser.RefinementDetailsView;
import org.splevo.vpm.software.JavaSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.software.SourceLocation;

import com.google.common.collect.Lists;

/**
 * A connector to open ASTNode elements in the JDT java editor.
 */
public class JavaEditorConnector {

    private static final String LOCATION_ANNOTATION = "org.splevo.ui.annotations.textmarkerannotation";
    private static final String LOCATION_MARKER = "org.splevo.ui.markers.codelocationmarker";
    /** The logger for this class. */
    private Logger logger = Logger.getLogger(RefinementDetailsView.class);

    /**
     * Open the java editor for a specific source location.
     *
     * @param softwareElement
     *            The software element to open in the JDT Java Editor.
     *
     * @return The opened {@link IEditorPart}
     */
    public IEditorPart openEditor(JavaSoftwareElement softwareElement) {

        SourceLocation sourceLocation = softwareElement.getSourceLocation();
        if (sourceLocation != null) {
            return openJavaEditor(sourceLocation, true);
        } else {
            logger.warn("No SourceRegion accessible.");
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
     * @return The opened {@link IEditorPart}
     */
    public IEditorPart openJavaEditor(final SourceLocation sourceLocation, final boolean openFileMultipleTimes) {

        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IPath location = Path.fromOSString(sourceLocation.getFilePath());
        final IFile inputFile = workspace.getRoot().getFileForLocation(location);
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

        IEditorPart iEditorPart = null;
        if (!openFileMultipleTimes) {
            // Look for an opened editor with the
            // file in it
            for (IEditorReference editorReference : PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                    .getActivePage().getEditorReferences()) {

                IEditorPart editorTmp = editorReference.getEditor(false);

                if (editorTmp instanceof AbstractTextEditor) {
                    AbstractTextEditor abstractTextEditor = (AbstractTextEditor) editorTmp;
                    if (inputFile.getName().equalsIgnoreCase(abstractTextEditor.getEditorInput().getName())) {
                        iEditorPart = editorTmp;
                        break;
                    }
                }
            }
        }
        // If no opened editor, then open a new one
        if (iEditorPart == null) {
            try {
                iEditorPart = IDE.openEditor(activePage, inputFile, false);
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
     *            The {@link IEditorPart}.
     * @param softwareElement
     *            The {@link SoftwareElement} to be highlighted.
     * @param message
     *            The {@link String} message to be displayed.
     */
    public void highlightInTextEditor(IEditorPart editor, SoftwareElement softwareElement, String message) {
        SourceLocation sourceLocation = softwareElement.getSourceLocation();
        IPath location = Path.fromOSString(sourceLocation.getFilePath());
        final IFile inputFile = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(location);

        int offset = sourceLocation.getStartPosition();
        int length = sourceLocation.getEndPosition() - offset;
        TextSelection selection = new TextSelection(offset, length);

        try {
            clearAndSetMarkers(editor, message, inputFile, selection);
        } catch (CoreException e) {
            logger.error("Could't clear and create text markers.", e);
        }
    }

    private void clearAndSetMarkers(IEditorPart editor, String message, final IFile inputFile, TextSelection selection)
            throws CoreException {
        inputFile.deleteMarkers(LOCATION_MARKER, true, IResource.DEPTH_INFINITE);
        IMarker marker = createMarker(inputFile, message);
        resetAndAddNewAnnotation(marker, selection, (AbstractTextEditor) editor);
    }

    /**
     * Select a specific region in a text editor.
     *
     * @param iEditorPart
     *            The editor to select the text in.
     * @param startPosition
     *            The start position of the mark to set.
     * @param endPosition
     *            The end position of the mark to set.
     */
    public void selectInTextEditor(final IEditorPart iEditorPart, int startPosition, int endPosition) {
        if (iEditorPart != null) {

            IWorkbenchPartSite site = iEditorPart.getSite();
            site.getPage().activate(site.getPart());

            AbstractTextEditor abstractTextEditor = (AbstractTextEditor) iEditorPart;
            abstractTextEditor.selectAndReveal(startPosition, endPosition - startPosition);
        }

    }

    /**
     * Adds a annotation with a given marker to the specified {@link IEditorPart}.
     *
     * @param marker
     *            The marker to be displayed on the right side.
     * @param selection
     *            The relevant text selection.
     * @param editor
     *            The {@link ITextEditor}.
     */
    @SuppressWarnings("unchecked")
    private void resetAndAddNewAnnotation(IMarker marker, ITextSelection selection, ITextEditor editor) {
        IDocumentProvider idp = editor.getDocumentProvider();
        IDocument document = idp.getDocument(editor.getEditorInput());
        IAnnotationModel iamf = idp.getAnnotationModel(editor.getEditorInput());

        Iterator<Annotation> annotationIterator = iamf.getAnnotationIterator();
        for (Annotation annotation : Lists.newArrayList(annotationIterator)) {
            if (LOCATION_ANNOTATION.equals(annotation.getType())) {
                iamf.removeAnnotation(annotation);
            }
        }

        SimpleMarkerAnnotation ma = new SimpleMarkerAnnotation(LOCATION_ANNOTATION, marker);
        iamf.connect(document);
        iamf.addAnnotation(ma, new Position(selection.getOffset(), selection.getLength()));
        iamf.disconnect(document);
    }

    /**
     * Creates a marker with a given message to a {@link IResource}.
     *
     * @param res
     *            The {@link IResource}.
     * @param message
     *            The {@link String} message.
     * @return An {@link IMarker}.
     * @throws CoreException
     *             Throws {@link CoreException} for invalid resources.
     */
    private IMarker createMarker(IResource res, String message) throws CoreException {
        IMarker marker = null;
        // The id that is defined in the plugin.xml
        marker = res.createMarker(LOCATION_MARKER);
        marker.setAttribute(IMarker.MESSAGE, message);

        return marker;
    }
}
