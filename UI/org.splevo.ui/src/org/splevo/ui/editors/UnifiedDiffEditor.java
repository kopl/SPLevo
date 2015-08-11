package org.splevo.ui.editors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.ui.text.IJavaPartitions;
import org.eclipse.jdt.ui.text.JavaSourceViewerConfiguration;
import org.eclipse.jdt.ui.text.JavaTextTools;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.CompositeRuler;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.IElementStateListener;
import org.splevo.ui.editors.listener.UnifiedDiffEditorListener;
import org.splevo.ui.sourceconnection.UnifiedDiffConnector;

/**
 * Implementation of a unified diff editor. At the moment, this is only a non editable viewer for
 * the unified diff.
 */
@SuppressWarnings("restriction")
public class UnifiedDiffEditor extends TextEditor {
    /** The id of the editor. */
    public static final String ID = "org.splevo.ui.editors.UnifiedDiffEditor"; //$NON-NLS-1$
    /** TODO: Comment... */
    private UnifiedDiffConnector connector;
    /** TODO: Comment... */
    private CompositeRuler ruler;
    
    /**
     * Constructs an instance of class {@link UnifiedDiffEditor}.
     */
    public UnifiedDiffEditor() {
        UnifiedDiffEditorListener listener = new UnifiedDiffEditorListener();
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        IWorkbenchPage page = window.getActivePage();
        page.addPartListener(listener);
    }
    
    @Override
    protected void initializeEditor() {
        super.initializeEditor();
        
        // install java source viewer configuration to allow java syntax highlighting
        JavaTextTools javaTextTools = JavaPlugin.getDefault().getJavaTextTools();
        JavaSourceViewerConfiguration sourceViewerConfiguration = new JavaSourceViewerConfiguration(
                javaTextTools.getColorManager(), 
                JavaPlugin.getDefault().getCombinedPreferenceStore(), 
                this, IJavaPartitions.JAVA_PARTITIONING);
        setSourceViewerConfiguration(sourceViewerConfiguration);
        
        // install document provider
        // FIXME: setDocumentProvider(new UnifiedDiffDocumentProvider());
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        // Do nothing when 'Save' action is performed
        monitor.done();
    }

    @Override
    public void doSaveAs() {
        // Do nothing when 'Save As' action is performed
    }

    @Override
    public boolean isDirty() {
        // Never mark editable file as dirty (="content has changed")
        return false;
    }

    @Override
    public boolean isEditable() {
        // Mark Editor as not editable.
        return false;
    }

    @Override
    public boolean isSaveAsAllowed() {
        // Saving is not allowed
        return false;
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void editorContextMenuAboutToShow(IMenuManager menu)
    {
        // removes all not needed menu items
        menu.removeAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(Composite parent)
    {
        super.createPartControl(parent);
        
        // create unified difference connector
        //IEditorInput input = getEditorInput();
        //IFile leadingFile = (input instanceof IFileEditorInput) ? ((IFileEditorInput) input).getFile() : null;
        
        // create working copy for leading document and set input and title
//        FileEditorInput unifiedDiffFile;
//        try
//        {
//            unifiedDiffFile = (FileEditorInput) createWorkingCopyFor(leadingFile);
//        }
//        catch (CoreException | IOException e)
//        {
//            unifiedDiffFile = null;
//            e.printStackTrace();
//        }
//        setInput(unifiedDiffFile);
        
        setPartName("Unified Diff");
        
        //connector = new UnifiedDiffConnector(unifiedDiffFile.getFile(), leadingFile);
        
        // create unified columns
//        UnifiedDiffConnectorContent connectorContent = connector.getConnectorContent();
//        UnifiedDiffRulerColumn leadingColumn = new UnifiedDiffRulerColumn(0, connectorContent.getConnectedLineNumbers());
//        ruler.addDecorator(0, leadingColumn);
    }
}
