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

import java.io.File;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;
import org.splevo.project.SPLevoProject;
import org.splevo.project.VPMModelReference;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.workflow.BasicSPLevoWorkflowConfiguration;
import org.splevo.ui.workflow.InitVPMWorkflowDelegate;

/**
 * Mouse adapter to listen for events which trigger the extraction of the source projects.
 */
public class InitVPMListener extends MouseAdapter {

    /** The internal reference to the splevo project editor to work with. */
    private SPLevoProjectEditor splevoProjectEditor = null;

    /**
     * Constructor requiring the reference to a splevoProject.
     *
     * @param splevoProjectEditor
     *            The references to the splevo project editor.
     */
    public InitVPMListener(SPLevoProjectEditor splevoProjectEditor) {
        this.splevoProjectEditor = splevoProjectEditor;
    }

    @Override
    public void mouseUp(MouseEvent e) {

        // build the job configuration
        BasicSPLevoWorkflowConfiguration config = buildWorflowConfiguration();
        Shell shell = e.widget.getDisplay().getActiveShell();

        // validate configuration
        if (!config.isValid()) {
            MessageDialog.openError(shell, "Invalid Project Configuration", config.getErrorMessage());
            return;
        }

        // if there are existing vpms inform
        // the user that they will be deleted
        if (config.getSplevoProjectEditor().getSplevoProject().getVpmModelReferences().size() > 0) {
            boolean proceed = MessageDialog.openConfirm(shell, "Override existing VPMs", "There are existing VPMs. "
                    + "Initializing a new one will override those existing ones." + "Do you want to proceed?");
            if (!proceed) {
                return;
            } else {
                deleteVPMs(config.getSplevoProjectEditor().getSplevoProject());
            }
        }

        // trigger workflow
        InitVPMWorkflowDelegate delegate = new InitVPMWorkflowDelegate(config);
        String title = "Init VPM";
        WorkflowListenerUtil.runWorkflowAndUpdateUI(delegate, title, splevoProjectEditor);
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
        for (VPMModelReference vpmReference : splevoProject.getVpmModelReferences()) {
            String modelPath = basePath + vpmReference.getPath();
            new File(modelPath).delete();
        }
        splevoProject.getVpmModelReferences().clear();

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
