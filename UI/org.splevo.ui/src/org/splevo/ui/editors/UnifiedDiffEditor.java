package org.splevo.ui.editors;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.ui.text.IJavaPartitions;
import org.eclipse.jdt.ui.text.JavaSourceViewerConfiguration;
import org.eclipse.jdt.ui.text.JavaTextTools;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.text.source.CompositeRuler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.editors.text.TextEditor;
import org.splevo.ui.sourceconnection.UnifiedDiffConnectorModel;
import org.splevo.ui.vpexplorer.explorer.KeyFocusListener;

/**
 * This class represents a text editor which visualizes an unified difference.
 * 
 * @author André Wengert
 */
@SuppressWarnings("restriction")
public class UnifiedDiffEditor extends TextEditor {
    /** The Logger instance */
    private static final Logger LOGGER = Logger.getLogger(UnifiedDiffEditor.class);
    
    private static final String CONTEXT_ID = "org.splevo.ui.unified.context";

    /** The editor input */
    private UnifiedDiffEditorInput input;
    /** A reference to the editor ruler. */
    private CompositeRuler ruler;

    @Override
    protected void initializeEditor() {
        super.initializeEditor();

        // install java source viewer configuration to allow java syntax highlighting
        JavaTextTools javaTextTools = JavaPlugin.getDefault().getJavaTextTools();
        JavaSourceViewerConfiguration sourceViewerConfiguration = new JavaSourceViewerConfiguration(
                javaTextTools.getColorManager(), JavaPlugin.getDefault().getCombinedPreferenceStore(), this,
                IJavaPartitions.JAVA_PARTITIONING);
        setSourceViewerConfiguration(sourceViewerConfiguration);
        
        
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
    public void editorContextMenuAboutToShow(IMenuManager menu) {
        // remove all unimportant (removable) menu items
        menu.removeAll();
    }

    @Override
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);

        // get the editor input
        if (getEditorInput() instanceof UnifiedDiffEditorInput) {
            input = (UnifiedDiffEditorInput) getEditorInput();
        } else {
            LOGGER.warn("The editor input of the UnifiedDiffEditor instance is not of type UnifiedDiffEditorInput but of type "
                    + getEditorInput().getClass().getSimpleName()
                    + "! This may lead to some operations not functioning as " + "intended.");
        }

        if (input instanceof UnifiedDiffEditorInput) {
            // set part name
            setPartName(((UnifiedDiffEditorInput) input).getName());

            // create line markers (difference highlights) and extract colors
            UnifiedDiffConnectorModel content = input.getDiffConnectorModel();
            UnifiedDiffHighlighter diffHighlighter = new UnifiedDiffHighlighter(this);
            Map<Integer, Color> unifiedLinesToColorMapping = diffHighlighter.highlightLines();

            // create unified columns
            int columnCount = content.getIntegrationCopyCount() + 1; // integration copies + leading
            for (int i = 0; i < columnCount; i++) {
                UnifiedDiffRulerColumn column = new UnifiedDiffRulerColumn(i, content.getUnifiedLineNumbers(),
                        unifiedLinesToColorMapping);
                ruler.addDecorator(i, column);
            }
        } else {
            throw new RuntimeException("The editor input of the UnifiedDiffEditor instance is not of type "
                    + "UnifiedDiffEditorInput but of type " + input.getClass().getSimpleName() + ".");
        }        
        this.getViewer().getTextWidget().addFocusListener(new KeyFocusListener(CONTEXT_ID));
    }
    
    @Override
    protected IVerticalRuler createVerticalRuler() {
        // main purpose of this is to get a reference to the vertical ruler object
        ruler = (CompositeRuler) super.createVerticalRuler();
        return ruler;
    }

    /**
     * Gets the unified difference connector content.
     * 
     * @return the unified difference connector content.
     */
    public UnifiedDiffConnectorModel getDiffConnectorModel() {
        if (input != null && input instanceof UnifiedDiffEditorInput) {
            return input.getDiffConnectorModel();
        } else if (input == null && getEditorInput() instanceof UnifiedDiffEditorInput) {
            return ((UnifiedDiffEditorInput) getEditorInput()).getDiffConnectorModel();
        } else {
            throw new RuntimeException("The editor input of the UnifiedDiffEditor instance is not set or the instance "
                    + "is not of type UnifiedDiffEditorInput but of type " + input.getClass().getSimpleName() + ".");
        }
    }

    /**
     * Gets the source viewer of editor.
     * @return the source viewer.
     */
    public ISourceViewer getViewer() {
        return super.getSourceViewer();
    }
    
}
