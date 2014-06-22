/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
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
