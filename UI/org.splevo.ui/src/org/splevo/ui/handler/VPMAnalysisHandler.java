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

import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.ProgressMonitorPart;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.wizards.vpmanalysis.VPMAnalysisWizard;
import org.splevo.ui.wizards.vpmanalysis.VPMAnalysisWizardPageChangeListener;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration.ResultPresentation;

/**
 * Handler to start VPM analysis
 */
public class VPMAnalysisHandler extends AbstractHandler {
	/** The logger for this class. */
	private Logger logger = Logger.getLogger(VPMAnalysisHandler.class);

	private SPLevoProjectEditor splevoProjectEditor = null;

	private VPMAnalysisWizard vpmAnalysisWizard = null;

	/**
	 * Check config and start analysis wizard
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

		assignActiveEditor(event);

		VPMAnalysisWorkflowConfiguration config = buildWorflowConfiguration();

		// trigger the wizard to configure the refinement process
		vpmAnalysisWizard = new VPMAnalysisWizard(config);

		WizardDialog wizardDialog = createWizardDialog(null, vpmAnalysisWizard);
		wizardDialog
				.addPageChangedListener(new VPMAnalysisWizardPageChangeListener(
						vpmAnalysisWizard));

		if (wizardDialog.open() != Window.OK) {
			logger.debug("Variation Point Analyses canceled");
			return null;
		}

		// validate configuration
		if (!config.isValid()) {
			logger.error(config.getErrorMessage());
			MessageDialog.openError(null, "Invalid Project Configuration",
					config.getErrorMessage());
			throw new ExecutionException("Invalid Project Configuration");
		}

		return null;
	}

	/**
	 * Figure out active editor type an assign
	 * 
	 * @param event
	 *            - An event containing all the information about the current
	 *            state of the application; must not be null.
	 * @throws ExecutionException
	 *             Wrong editor is active
	 */
	private void assignActiveEditor(ExecutionEvent event)
			throws ExecutionException {
		IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);

		if (!(activeEditor instanceof SPLevoProjectEditor)) {
			logger.error("Active editor is not SPLevoProjectEditor");

			MessageDialog.openError(null, "Wrong active editor",
					"Active editor is not SPLevoProjectEditor");

			throw new ExecutionException(
					"Active editor is not SPLevoProjectEditor");
		}

		// Get editor and splevo project
		this.splevoProjectEditor = (SPLevoProjectEditor) activeEditor;
	}

	/**
	 * Create the wizard dialog as a container of the vpm analysis wizard.
	 * 
	 * @param shell
	 *            The UI shell to access the ui.
	 * @param vpmAnalysisWizard
	 *            The wizard to display in the dialog.
	 * @return The prepared wizard dialog.
	 */
	private WizardDialog createWizardDialog(Shell shell,
			VPMAnalysisWizard vpmAnalysisWizard) {
		WizardDialog wizardDialog = new WizardDialog(shell, vpmAnalysisWizard) {
			@Override
			protected Control createDialogArea(Composite parent) {
				Control ctrl = super.createDialogArea(parent);
				getProgressMonitor();
				return ctrl;
			}

			@Override
			protected IProgressMonitor getProgressMonitor() {
				ProgressMonitorPart monitor = (ProgressMonitorPart) super
						.getProgressMonitor();
				GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
				gridData.heightHint = 0;
				monitor.setLayoutData(gridData);
				monitor.setVisible(false);
				return monitor;
			}
		};
		return wizardDialog;
	}

	/**
	 * Build the configuration for the workflow.
	 * 
	 * @return The prepared configuration.
	 */
	private VPMAnalysisWorkflowConfiguration buildWorflowConfiguration() {
		VPMAnalysisWorkflowConfiguration defaultConfig = new VPMAnalysisWorkflowConfiguration();
		defaultConfig.setSplevoProjectEditor(splevoProjectEditor);

		// Set default presentation mode
		defaultConfig.setPresentation(ResultPresentation.REFINEMENT_BROWSER);

		return defaultConfig;
	}

}
