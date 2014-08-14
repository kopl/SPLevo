/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Anton Huck
 *******************************************************************************/
package org.splevo.ui.handler;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.listeners.WorkflowListenerUtil;
import org.splevo.ui.workflow.BasicSPLevoWorkflowConfiguration;
import org.splevo.ui.workflow.InitVPMWorkflowDelegate;

/**
 * @author Anton Huck
 * 
 */
public class InitVPMHandler extends AbstractHandler {

	/** The logger for this class. */
	private Logger logger = Logger.getLogger(InitVPMHandler.class);

	private SPLevoProjectEditor splevoProjectEditor = null;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		logger.debug("Init VPM Handler fired");
		IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);
		if (!(activeEditor instanceof SPLevoProjectEditor)) {
			logger.error("activeEditor is not SPLevoProjectEditor");
			return null;
		}

		// Get editor and splevo project
		this.splevoProjectEditor = (SPLevoProjectEditor) activeEditor;

		// build the job configuration
		BasicSPLevoWorkflowConfiguration config = buildWorflowConfiguration();

		// validate configuration
		if (!config.isValid()) {
			MessageDialog.openError(null, "Invalid Project Configuration",
					config.getErrorMessage());
			logger.error("Invalid Project Configuration");
			return null;
		}

		// if there are existing vpms inform
		// the user that they will be deleted
		if (config.getSplevoProjectEditor().getSplevoProject()
				.getVpmModelPaths().size() > 0) {
			boolean proceed = MessageDialog
					.openConfirm(
							null,
							"Override existing VPMs",
							"There are existing VPMs. "
									+ "Initializing a new one will override those existing ones."
									+ "Do you want to proceed?");
			if (!proceed) {
				return null;
			} else {
				deleteVPMs(config.getSplevoProjectEditor().getSplevoProject());
			}
		}

		// trigger workflow
		InitVPMWorkflowDelegate delegate = new InitVPMWorkflowDelegate(config);
		String title = "Init VPM";
		WorkflowListenerUtil.runWorkflowAndUpdateUI(delegate, title,
				splevoProjectEditor);

		return null;
	}

	/**
	 * Delete the vpms registered in the splevo project.
	 * 
	 * @param splevoProject
	 *            The project to get the vpms from.
	 */
	private void deleteVPMs(SPLevoProject splevoProject) {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		String basePath = workspace.getRoot().getRawLocation().toOSString();
		for (String vpmPath : splevoProject.getVpmModelPaths()) {
			String modelPath = basePath + vpmPath;
			new File(modelPath).delete();
		}
		splevoProject.getVpmModelPaths().clear();

	}

	/**
	 * Build the configuration for the workflow.
	 * 
	 * @return The prepared configuration.
	 */
	private BasicSPLevoWorkflowConfiguration buildWorflowConfiguration() {
		BasicSPLevoWorkflowConfiguration config = new BasicSPLevoWorkflowConfiguration();
		config.setSplevoProjectEditor(splevoProjectEditor);
		return config;
	}
}
