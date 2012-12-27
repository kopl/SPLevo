package org.splevo.ui.listeners;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.workflow.DiffingWorkflowConfiguration;
import org.splevo.ui.workflow.DiffingWorkflowDelegate;

/**
 * Mouse adapter to listen for events which trigger the extraction of the source
 * projects.
 */
public class DiffSourceModelListener extends MouseAdapter {

	/** The internal reference to the splevo project editor to work with. */
	private SPLevoProjectEditor splevoProjectEditor = null;

	/**
	 * Constructor requiring the reference to a splevoProject.
	 * 
	 * @param splevoProject
	 *            The references to the project.
	 */
	public DiffSourceModelListener(SPLevoProjectEditor splevoProjectEditor) {
		this.splevoProjectEditor = splevoProjectEditor;
	}

	@Override
	public void mouseUp(MouseEvent e) {
		
		// build the job configuration
		DiffingWorkflowConfiguration config = buildWorflowConfiguration();
		
		
		// validate configuration
		if(!config.isValid()){
			Shell shell = e.widget.getDisplay().getShells()[0];
			MessageDialog.openError(shell, "Invalid Project Configuration", config.getErrorMessage());
			return;
		}
		
		// trigger workflow
		DiffingWorkflowDelegate workflowDelegate = new DiffingWorkflowDelegate(config);
		IAction action = new Action("Diffing"){};
		workflowDelegate.run(action);
	}

	/**
	 * Build the configuration for the workflow.
	 * @return The prepared configuration.
	 */
	private DiffingWorkflowConfiguration buildWorflowConfiguration() {
		DiffingWorkflowConfiguration config = new DiffingWorkflowConfiguration();
		config.setSplevoProjectEditor(splevoProjectEditor);
		return config;
	}

}
