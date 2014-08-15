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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.workflow.BasicSPLevoWorkflowConfiguration;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration;

/**
 * 
 */
public abstract class AbstractSPLevoHandler extends AbstractHandler {

	private static final String INVALID_PROJECT_CONFIGURATION = "Invalid Project Configuration";
	protected SPLevoProjectEditor splevoProjectEditor = null;
	protected Shell activeShell = null;

	/**
	 * Check for SPLevoProject Editor is active and return the instance
	 * 
	 * @param event
	 *            - An event containing all the information about the current
	 *            state of the application; must not be null.
	 * @return SPLevoProject editor if active
	 * @throws ExecutionException
	 *             Wrong editor is active
	 */
	protected SPLevoProjectEditor getActiveSPLevoEditor(ExecutionEvent event)
			throws ExecutionException {
		IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);

		if (!(activeEditor instanceof SPLevoProjectEditor)) {
			showMessageAndThrowException("Wrong active editor",
					"Active editor is not SPLevoProjectEditor");
		}

		// Get editor and splevo project
		return (SPLevoProjectEditor) activeEditor;
	}

	/**
	 * Build the configuration for the workflow.
	 * 
	 * @return The prepared configuration.
	 */
	protected BasicSPLevoWorkflowConfiguration buildWorflowConfiguration() {
		BasicSPLevoWorkflowConfiguration config = new BasicSPLevoWorkflowConfiguration();
		config.setSplevoProjectEditor(splevoProjectEditor);
		return config;
	}

	/**
	 * Check if config is valid otherwise show prompt and throw exception
	 * 
	 * @param config
	 *            the configuration to be checked
	 * @throws ExecutionException
	 *             on invalid configuration
	 */
	protected void checkConfig(VPMAnalysisWorkflowConfiguration config)
			throws ExecutionException {
		if (!config.isValid()) {
			showMessageAndThrowException(INVALID_PROJECT_CONFIGURATION,
					config.getErrorMessage());
		}
	}

	/**
	 * Check if config is valid otherwise show prompt and throw exception
	 * 
	 * @param config
	 *            the configuration to be checked
	 * @throws ExecutionException
	 *             on invalid configuration
	 */
	protected void checkConfig(BasicSPLevoWorkflowConfiguration config)
			throws ExecutionException {
		if (!config.isValid()) {
			showMessageAndThrowException(INVALID_PROJECT_CONFIGURATION,
					config.getErrorMessage());
		}
	}

	/**
	 * Show an error message to user and abort execution
	 * 
	 * @param error
	 *            error name
	 * @param message
	 *            short error description
	 * @throws ExecutionException
	 *             abort execution
	 */
	protected void showMessageAndThrowException(String error, String message)
			throws ExecutionException {
		MessageDialog.openError(activeShell, error, message);
		throw new ExecutionException(error);
	}
}
