package org.splevo.ui.listeners;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.workflow.ExtractionWorkflowConfiguration;
import org.splevo.ui.workflow.ExtractionWorkflowDelegate;

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
	 * @param splevoProjectEditor
	 *            The references to the splevo project editor.
	 */
	public ExtractProjectListener(SPLevoProjectEditor splevoProjectEditor) {
		this.splevoProjectEditor = splevoProjectEditor;
	}

	@Override
	public void mouseUp(MouseEvent e) {

		// build the job configuration
		ExtractionWorkflowConfiguration config = buildWorkflowConfiguration();
		Shell shell = e.widget.getDisplay().getShells()[0];

		// validate configuration
		if (!config.isValid()) {
			MessageDialog.openError(shell, "Invalid Project Configuration",
					config.getErrorMessage());
			return;
		}

		SPLevoProject splevoProject = splevoProjectEditor.getSplevoProject();

		// Check pre-existing results
		if (splevoProject.getDiffingModelPath() != null
				|| splevoProject.getVpmModelPaths().size() > 0) {
			
			boolean proceed = MessageDialog.openConfirm(shell,
					"Override existing Data",
					"There is existing data from previous processings.\n"
							+ "This data will be deleted if you proceed.\n"
							+ "Proceed anyway?");
			if (!proceed) {
				return;
			} else {
				resetDownstramData(splevoProject);
			}
		}

		// trigger workflow
		ExtractionWorkflowDelegate workflowDelegate = new ExtractionWorkflowDelegate(
				config);
			IAction action = new Action("Extract") {
		};
		workflowDelegate.run(action);

		splevoProjectEditor.enableButtonsIfInformationAvailable();

	}

	/**
	 * Reset the data of all downstream processes.
	 * 
	 * @param splevoProject
	 *            The project to access the relevant information.
	 */
	private void resetDownstramData(SPLevoProject splevoProject) {
		splevoProject.setDiffingModelPath(null);
		splevoProject.getVpmModelPaths().clear();
	}

	/**
	 * Build the configuration for the workflow.
	 * 
	 * @return The prepared configuration.
	 */
	private ExtractionWorkflowConfiguration buildWorkflowConfiguration() {
		ExtractionWorkflowConfiguration config = new ExtractionWorkflowConfiguration();
		config.setSplevoProjectEditor(splevoProjectEditor);
		return config;
	}

}
