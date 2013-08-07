package org.splevo.ui.listeners;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.workflow.BasicSPLevoWorkflowConfiguration;
import org.splevo.ui.workflow.ModelExtractionWorkflowDelegate;

/**
 * Mouse adapter to listen for events which trigger the extraction of the source projects.
 */
public class ExtractProjectListener extends MouseAdapter {

    /** The internal reference to the splevo project editor to work with. */
    private SPLevoProjectEditor splevoProjectEditor = null;

    /**
     * Constructor requiring the reference to a splevoProject.
     * 
     * @param splevoProjectEditor
     *            The references to the splevo project editor.
     */
    public ExtractProjectListener(SPLevoProjectEditor splevoProjectEditor) {
        this.splevoProjectEditor = splevoProjectEditor;
    }

    @Override
    public void mouseUp(MouseEvent e) {

        // build the job configuration
        BasicSPLevoWorkflowConfiguration config = buildWorkflowConfiguration();

        // validate configuration
        if (!config.isValid()) {
            Shell shell = e.widget.getDisplay().getShells()[0];
            MessageDialog.openError(shell, "Invalid Project Configuration", config.getErrorMessage());
            return;
        }

        // trigger workflow
        ModelExtractionWorkflowDelegate workflowDelegate = new ModelExtractionWorkflowDelegate(config);
        IAction action = new Action("Extract") {
        };
        workflowDelegate.run(action);
        
        splevoProjectEditor.enableButtonsAfterExtraction();
        
    }

    /**
     * Build the configuration for the workflow.
     * 
     * @return The prepared configuration.
     */
    private BasicSPLevoWorkflowConfiguration buildWorkflowConfiguration() {
        BasicSPLevoWorkflowConfiguration config = new BasicSPLevoWorkflowConfiguration();
        config.setSplevoProjectEditor(splevoProjectEditor);
        return config;
    }

}
