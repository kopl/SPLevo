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

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.splevo.ui.listeners.WorkflowListenerUtil;
import org.splevo.ui.workflow.BasicSPLevoWorkflowConfiguration;
import org.splevo.ui.workflow.GenerateFeatureModelWorkflowDelegate;

/**
 * Handler to generate feature modell
 */
public class GenerateFeatureModellHandler extends AbstractSPLevoHandler {

	private static final String VARIATION_POINT_MODEL_MISSING = "Variation Point Model Missing";
	private static final String VARIATION_POINT_MODEL_MISSING_MESSAGE = "There is no variation point model available "
			+ "to generate a feature model from.";

	/**
	 * Check config and generate feature model
	 * 
	 * @param event
	 *            - An event containing all the information about the current
	 *            state of the application; must not be null.
	 * @return the result of the execution. Reserved for future use, must be
	 *         null.
	 * @throws ExecutionException
	 *             - if an exception occurred during execution.
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		splevoProjectEditor = getActiveSPLevoEditor(event);

		// build the job configuration
		BasicSPLevoWorkflowConfiguration config = buildWorflowConfiguration();

		activeShell = HandlerUtil.getActiveShell(event);

		// validate configuration
		checkConfig(config);

		// inform on missing variation point model
		if (config.getSplevoProjectEditor().getSplevoProject()
				.getVpmModelPaths().size() == 0) {
			showMessageAndThrowException(VARIATION_POINT_MODEL_MISSING,
					VARIATION_POINT_MODEL_MISSING_MESSAGE);
		}

		// trigger workflow
		GenerateFeatureModelWorkflowDelegate delegate = new GenerateFeatureModelWorkflowDelegate(
				config);
		String title = "Generate Feature Model";
		WorkflowListenerUtil.runWorkflowAndUpdateUI(delegate, title,
				splevoProjectEditor);
		return null;
	}
}
