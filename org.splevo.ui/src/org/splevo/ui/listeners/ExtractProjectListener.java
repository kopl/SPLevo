package org.splevo.ui.listeners;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.workflow.ExtractionJobConfiguration;
import org.splevo.ui.workflow.ModelExtractionWorkflowDelegate;

/**
 * Mouse adapter to listen for events which trigger the extraction of the source
 * projects.
 */
public class ExtractProjectListener extends MouseAdapter {

	/** The internal reference to the splevo project editor to work with. */
	private SPLevoProjectEditor splevoProjectEditor = null;

	/**
	 * Constructor requiring the reference to a splevoProject.
	 * 
	 * @param splevoProject
	 *            The references to the project.
	 */
	public ExtractProjectListener(SPLevoProjectEditor splevoProjectEditor) {
		this.splevoProjectEditor = splevoProjectEditor;
	}

	@Override
	public void mouseUp(MouseEvent e) {

		boolean valid = checkConfigurationValue(e,
				splevoProjectEditor.getSplevoProject().getVariantNameLeading(),
				"You need to specify a name for the leading variant.");
		if (valid == false) {
			return;
		}
		
		runPalladioWFEbasedWorkflow(e);
	}
	
	private void runPalladioWFEbasedWorkflow(MouseEvent e){
		
		// build the job configuration
		ExtractionJobConfiguration config = new ExtractionJobConfiguration();
		config.setSplevoProject(splevoProjectEditor.getSplevoProject());
		config.setSplevoProjectEditor(splevoProjectEditor);
		
		ModelExtractionWorkflowDelegate workflowDelegate = new ModelExtractionWorkflowDelegate(config);
		IAction action = new Action("Extract"){
		};
		workflowDelegate.run(action);
	}

	/**
	 * Check a given configuration value to be not empty
	 * @param e The mouse event to check.
	 * @param configurationValue The value to check.
	 * @param message The error message to present in the ui dialog.
	 * @return
	 */
	private boolean checkConfigurationValue(MouseEvent e, 
			String configurationValue,
			String message) {
		if (configurationValue == null
				|| configurationValue.isEmpty()) {
			Shell shell = e.widget.getDisplay().getShells()[0];
			MessageDialog.openError(shell, "Invalid Project Configuration",
					message);
			return false;
		}
		return true;
	}

}
