package org.splevo.ui.editors.listener;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Shell;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.listeners.WorkflowListenerUtil;
import org.splevo.ui.util.WorkspaceUtil;
import org.splevo.ui.workflow.BuildSPLWorkflowConfiguration;
import org.splevo.ui.workflow.BuildSPLWorkflowDelegate;

/**
 * Builds the SPL on mouse down event.
 */
public class BuildSPLListener implements MouseListener {

	private SPLevoProjectEditor splevoProjectEditor;

	/**
	 * Default constructor.
	 * 
	 * @param splevoProjectEditor
	 *            The associated {@link SPLevoProjectEditor}.
	 */
	public BuildSPLListener(SPLevoProjectEditor splevoProjectEditor) {
		this.splevoProjectEditor = splevoProjectEditor;
	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
		// nothing to do here
	}

	@Override
	public void mouseDown(MouseEvent e) {
		// nothing to do here
	}

	@Override
	public void mouseUp(MouseEvent e) {
		// build the job configuration
		BuildSPLWorkflowConfiguration config = buildBuildSPLWorflowConfiguration();
		Shell shell = e.widget.getDisplay().getShells()[0];

		// validate configuration
		if (this.splevoProjectEditor.getSplevoProject()
						.getVpmModelPaths().size() == 0) {
			MessageDialog.openError(shell, "Cannot proceed. Reason: No VPMs created, yet.",
					config.getErrorMessage());
			return;
		}

		// trigger workflow
		BuildSPLWorkflowDelegate delegate = new BuildSPLWorkflowDelegate(config);
		WorkflowListenerUtil.runWorkflowAndUpdateUI(delegate, "Build SPL",
				splevoProjectEditor);
	}

	/**
	 * Build the configuration for the workflow.
	 * 
	 * @return The prepared configuration.
	 */
	private BuildSPLWorkflowConfiguration buildBuildSPLWorflowConfiguration() {
	    // TODO: change path
	    String path = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + "/SPL";
	    BuildSPLWorkflowConfiguration config = new BuildSPLWorkflowConfiguration(path);
		config.setSplevoProjectEditor(this.splevoProjectEditor);
		return config;
	}

}
