package org.splevo.ui.jdt;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.modisco.java.composition.javaapplication.Java2File;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.eclipse.modisco.java.composition.javaapplication.JavaNodeSourceRegion;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.splevo.modisco.util.SourceConnector;
import org.splevo.ui.refinementbrowser.RefinementDetailsView;

/**
 * A connector to open ASTNode elements in the JDT java editor.
 * @author Benjamin Klatt
 *
 */
public class JavaEditorConnector {

	/** The logger for this class. */
	private Logger logger = Logger.getLogger(RefinementDetailsView.class);

    /**
     * Open the java editor for a specific source location.
     * 
     * @param locationNode The AST node identifying the location to open.
     * @param javaApplication The java application model to access the inventory.
     */
    public void openEditor(ASTNode locationNode, JavaApplication javaApplication) {
        SourceConnector sourceConnector = new SourceConnector(javaApplication);
        JavaNodeSourceRegion sourceRegion = sourceConnector.findSourceRegion(locationNode);

        if (sourceRegion != null) {
            openJavaEditor(sourceRegion, true);
        } else {
            logger.warn("No SourceRegion accessible.");
        }
    }

	/**
	 * Open a source region in an editor. The flag can control if the same file
	 * can be opened multiple times. The file is identified by it's filename.
	 * This might lead to problems in case of the customized product copies.
	 * 
	 * 
	 * @param sourceRegion
	 *            The source region.
	 * @param openFileMultipleTimes
	 *            The true/false flag if a file can be opened multiple times.
	 */
	@SuppressWarnings("restriction")
	public void openJavaEditor(final JavaNodeSourceRegion sourceRegion,
			final boolean openFileMultipleTimes) {

		if (sourceRegion.eContainer() instanceof Java2File) {
			Java2File fileMapping = (Java2File) sourceRegion.eContainer();
			String filePath = fileMapping.getFile().getPath();

			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IPath location = Path.fromOSString(filePath);
			final IFile inputFile = workspace.getRoot().getFileForLocation(
					location);

			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					IWorkbenchPage activePage = PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow().getActivePage();

					IEditorPart iEditorPart = null;
					if (!openFileMultipleTimes) {
						// Look for an opened editor with the
						// file in it
						for (IEditorReference editorReference : PlatformUI
								.getWorkbench().getActiveWorkbenchWindow()
								.getActivePage().getEditorReferences()) {

							IEditorPart editorTmp = editorReference
									.getEditor(false);

							if (editorTmp instanceof AbstractTextEditor) {
								AbstractTextEditor abstractTextEditor = (AbstractTextEditor) editorTmp;
								if (inputFile.getName().equalsIgnoreCase(
										abstractTextEditor.getEditorInput()
												.getName())) {
									iEditorPart = editorTmp;
									break;
								}
							}
						}
					}
					// If no opened editor, then open a new one
					if (iEditorPart == null) {
						try {
							iEditorPart = IDE.openEditor(activePage, inputFile,
									false);
						} catch (Exception e) {
							logger.error(e);
						}
					}

					selectInTextEditor(iEditorPart, sourceRegion);

				}

				/**
				 * Select a specific region in a text editor.
				 * 
				 * @param iEditorPart
				 *            The editor to select the text in.
				 * @param sourceRegion
				 *            The source region to mark in the editor.
				 */
				public void selectInTextEditor(final IEditorPart iEditorPart,
						final JavaNodeSourceRegion sourceRegion) {
					if (iEditorPart != null) {
						AbstractTextEditor abstractTextEditor = (AbstractTextEditor) iEditorPart;

						abstractTextEditor
								.getSite()
								.getPage()
								.activate(
										abstractTextEditor.getSite().getPart());

						abstractTextEditor.selectAndReveal(sourceRegion
								.getStartPosition().intValue(), sourceRegion
								.getEndPosition().intValue()
								- sourceRegion.getStartPosition().intValue());
					}

				}
			});
		}
	}
}
