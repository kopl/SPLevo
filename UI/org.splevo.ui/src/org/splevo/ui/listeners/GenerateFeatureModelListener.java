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
package org.splevo.ui.listeners;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.workflow.BasicSPLevoWorkflowConfiguration;
import org.splevo.ui.workflow.GenerateFeatureModelWorkflowDelegate;

/**
 * Mouse adapter to listen for events which trigger the extraction of the source
 * projects.
 */
public class GenerateFeatureModelListener extends MouseAdapter {

	/** The internal reference to the splevo project editor to work with. */
	private SPLevoProjectEditor splevoProjectEditor = null;

	/**
	 * Constructor requiring the reference to a splevoProject.
	 *
	 * @param splevoProjectEditor
	 *            The references to the splevo project editor.
	 */
	public GenerateFeatureModelListener(SPLevoProjectEditor splevoProjectEditor) {
		this.splevoProjectEditor = splevoProjectEditor;
	}

	@Override
	public void mouseUp(MouseEvent e) {

		// build the job configuration
		BasicSPLevoWorkflowConfiguration config = buildWorflowConfiguration();
		Shell shell = e.widget.getDisplay().getActiveShell();

		// validate configuration
		if (!config.isValid()) {
			MessageDialog.openError(shell, "Invalid Project Configuration",
					config.getErrorMessage());
			return;
		}

		// if there are existing vpms inform
		// the user that they will be deleted
		if (config.getSplevoProjectEditor().getSplevoProject()
				.getVpmModelPaths().size() == 0) {
			MessageDialog.openError(shell, "Variation Point Model Missing",
					"There is no variation point model available "
							+ "to generate a feature model from.");
			return;
		}

		// trigger workflow
		GenerateFeatureModelWorkflowDelegate delegate = new GenerateFeatureModelWorkflowDelegate(
				config);
		String title = "Generate Feature Model";
		WorkflowListenerUtil.runWorkflowAndUpdateUI(delegate, title,
				splevoProjectEditor);
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
