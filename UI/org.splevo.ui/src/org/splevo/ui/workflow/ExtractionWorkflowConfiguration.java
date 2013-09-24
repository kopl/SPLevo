package org.splevo.ui.workflow;

import org.splevo.project.SPLevoProject;

/**
 * A workflow configuration for the extraction process.
 */
public class ExtractionWorkflowConfiguration extends
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
		if (splevoProject.getExtractorIds().size() < 1) {
			return "No software model extractor configured.";
		}

		return null;
	}

}
