package org.splevo.ui.workflow;

import org.splevo.project.SPLevoProject;


/**
 * Configuration for the source model diffing workflow.
 *
 */
public class DiffingWorkflowConfiguration extends
		BasicSPLevoWorkflowConfiguration {

	@Override
	public String getErrorMessage() {

		if (getSplevoProjectEditor() == null) {
			return ERROR_MSG_EDITOR_MISSING;
		}

		if (getSplevoProjectEditor().getSplevoProject() == null) {
			return "No SPLevo project referenced.";
		}

		SPLevoProject splevoProject = getSplevoProjectEditor()
				.getSplevoProject();
		if (splevoProject.getDifferIds().size() < 1) {
			return "No differ configured.";
		}

		return null;
	}

}
