package org.splevo.ui.sourceconnection.handler;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;
import org.splevo.ui.editors.UnifiedDiffEditor;
import org.splevo.ui.sourceconnection.UnifiedDiffConnectorContent;
import org.splevo.ui.sourceconnection.helper.FileLineNumberPair;

public class OpenUnifiedDiffSourceHandler extends AbstractHandler {
    /** Logger instance */
    private static Logger LOGGER = Logger.getLogger(OpenUnifiedDiffSourceHandler.class);
    
    /**
     * {@inheritDoc}
     * Opens the source code file for the currently selected unified difference line in the default source editor.
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        // get active editor
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        IEditorPart editorPart = window.getActivePage().getActiveEditor();

        // proceed only if called from unified difference editor
        if (editorPart instanceof UnifiedDiffEditor) {
            UnifiedDiffEditor editor = (UnifiedDiffEditor) editorPart;
            IEditorSite editorSite = editor.getEditorSite();

            if (editorSite != null) {
                // get selection provider
                ISelectionProvider selectionProvider = editorSite.getSelectionProvider();
                if (selectionProvider != null) {
                    // prepare fetching
                    ISelection selection = selectionProvider.getSelection();
                    int selectedLineNumber = ((ITextSelection) selection).getStartLine();
                    UnifiedDiffConnectorContent content = editor.getConnectorContent();

                    // fetch file information according to selected (unified) line
                    if (content != null) {
                        List<FileLineNumberPair> sources = content.getFileInformationFor(selectedLineNumber);

                        // open source(s) in new editor tab
                        IEditorPart openedEditor = null;
                        for (FileLineNumberPair source : sources) {
                            if (source.getFile().exists() && source.getFile().isFile()) {
                                openedEditor = openSourceInEditor(workbench, source.getFile());
                                selectAndReveal(openedEditor, source);
                            } else {
                                LOGGER.error("The source file (to open) \"" + source.getFile().getAbsolutePath()
                                        + "\" does not exist!");
                            }
                        }
                    } else {
                        LOGGER.error("Could not fetch unified connector content!");
                    }
                }
            }
        } else {
            LOGGER.error("The Active Editor is not of type " + UnifiedDiffEditor.class.getName() + " but "
                    + editorPart.getClass().getName());
        }

        return null;
    }

    /**
     * Opens the given source file in the default source editor.
     * 
     * @param workbench
     *            the workbench object.
     * @param source
     *            the given source file.
     */
    private IEditorPart openSourceInEditor(IWorkbench workbench, File source) {
        IEditorPart editor = null;
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        IEditorRegistry editorRegistry = workbench.getEditorRegistry();
        try {
            editor = window.getActivePage().openEditor(createEditorInput(source),
                    editorRegistry.getDefaultEditor(source.getName()).getId());
        } catch (PartInitException e) {
            LOGGER.error("Default source editor for source \"" + source.getAbsolutePath()
                    + "\" could not be opened!", e);
        }

        return editor;
    }

    /**
     * Selects and reveals a given line number within the given editor provided by the file information.
     * 
     * @param editor
     *            the editor to select and reveal in.
     * @param fileInformation
     *            the information containing the line number.
     */
    private void selectAndReveal(IEditorPart editor, FileLineNumberPair fileInformation) {
        AbstractDecoratedTextEditor specificEditor = (AbstractDecoratedTextEditor) editor;
        IEditorInput input = specificEditor.getEditorInput();
        IDocument document = specificEditor.getDocumentProvider().getDocument(input);
        if (document != null) {
            // create line information
            IRegion lineInfo = null;
            try {
                lineInfo = document.getLineInformation(fileInformation.getLineNumber() - 1);
            } catch (BadLocationException e) {
                LOGGER.error("The selected line " + (fileInformation.getLineNumber() - 1)
                        + " could not be found within source file \"" + fileInformation.getFile().getAbsolutePath() + "\"!", e);
            }

            // highlight the selected line number
            if (lineInfo != null) {
                specificEditor.selectAndReveal(lineInfo.getOffset(), lineInfo.getLength());
            }
        }
    }

    /**
     * Generates editor input for a given file.
     * 
     * @param file
     *            the given file.
     * @return the editor input.
     */
    private IEditorInput createEditorInput(File file) {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IPath location = Path.fromOSString(file.getAbsolutePath());
        IFile ifile = workspace.getRoot().getFileForLocation(location);
        return new FileEditorInput(ifile);
    }
}
