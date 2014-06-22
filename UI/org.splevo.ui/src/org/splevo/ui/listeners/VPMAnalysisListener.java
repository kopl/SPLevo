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

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.ProgressMonitorPart;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.wizards.vpmanalysis.VPMAnalysisWizard;
import org.splevo.ui.wizards.vpmanalysis.VPMAnalysisWizardPageChangeListener;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration.ResultPresentation;

/**
 * Mouse adapter to listen for events which trigger the refinement process of a variation point
 * model.
 */
public class VPMAnalysisListener extends MouseAdapter {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(VPMAnalysisListener.class);

    /** The internal reference to the SPLevo project editor to work with. */
    private SPLevoProjectEditor splevoProjectEditor = null;

    private VPMAnalysisWorkflowConfiguration config = null;

    private VPMAnalysisWizard vpmAnalysisWizard = null;

    /**
     * Constructor requiring the reference to a splevoProject.
     *
     * @param splevoProjectEditor
     *            The references to the splevo project editor.
     */
    public VPMAnalysisListener(SPLevoProjectEditor splevoProjectEditor) {
        this.splevoProjectEditor = splevoProjectEditor;
        this.config = buildWorflowConfiguration();
    }

    /**
     * Handle a button click to create and start the VPM Analysis wizard.
     *
     * @param event
     *            The mouse event to react on. All events are handled the same way.
     */
    @Override
    public void mouseUp(MouseEvent event) {

        Shell shell = event.widget.getDisplay().getShells()[0];

        // trigger the wizard to configure the refinement process
        vpmAnalysisWizard = new VPMAnalysisWizard(config);
        WizardDialog wizardDialog = createWizardDialog(shell, vpmAnalysisWizard);
        wizardDialog.addPageChangedListener(new VPMAnalysisWizardPageChangeListener(vpmAnalysisWizard));

        if (wizardDialog.open() != Window.OK) {
            logger.debug("Variation Point Analyses canceled");
            return;
        }

        // validate configuration
        if (!config.isValid()) {
            MessageDialog.openError(shell, "Invalid Project Configuration", config.getErrorMessage());
            return;
        }


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
    private WizardDialog createWizardDialog(Shell shell, VPMAnalysisWizard vpmAnalysisWizard) {
        WizardDialog wizardDialog = new WizardDialog(shell, vpmAnalysisWizard) {
            @Override
            protected Control createDialogArea(Composite parent) {
                Control ctrl = super.createDialogArea(parent);
                getProgressMonitor();
                return ctrl;
            }

            @Override
            protected IProgressMonitor getProgressMonitor() {
                ProgressMonitorPart monitor = (ProgressMonitorPart) super.getProgressMonitor();
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
