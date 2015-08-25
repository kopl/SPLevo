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
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.refinementbrowser.action;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.splevo.ui.SPLevoUIPlugin;
import org.splevo.ui.listeners.WorkflowListenerUtil;
import org.splevo.ui.refinementbrowser.VPMRefinementBrowser;
import org.splevo.ui.workflow.VPMRefinementWorkflowConfiguration;
import org.splevo.ui.workflow.VPMRefinementWorkflowDelegate;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * An action to trigger the application of a list of refinements to variation point model.
 */
public class ApplyRefinementsAction extends Action {

    /** The refinement viewer to access the selected refinements. */
    protected final VPMRefinementBrowser vpmRefinementBrowser;

    /**
     * Constructor requiring a reference to the viewer to get the refinements to be executed from as
     * well as the text of the action.
     *
     * @param vpmRefinementBrowser
     *            The reference to the viewer.
     * @param text
     *            The text for the action.
     */
    public ApplyRefinementsAction(VPMRefinementBrowser vpmRefinementBrowser, String text) {
        super(text);
        this.vpmRefinementBrowser = vpmRefinementBrowser;
    }

    /**
     * Apply the refinement by - checking that refinements have been selected - configuring and
     * running the workflow - closing the browser.
     *
     * @param event
     *            The event that triggered the refinement action.
     */
    @Override
    public void runWithEvent(Event event) {

        List<Refinement> refinements = getRefinementsFromRefinementBrowser();

        // handle an empty selection by asking the user how to proceed
        if (refinements.size() == 0) {
            Shell shell = event.widget.getDisplay().getActiveShell();
            boolean cancel = MessageDialog.openQuestion(shell, "SPLevo Info",
                    "No Refinements selected. Do you want to cancel the analysis?");
            if (!cancel) {
                return;
            }
        } else {
            VPMRefinementWorkflowConfiguration config = buildWorflowConfiguration(refinements);
            VPMRefinementWorkflowDelegate delegate = new VPMRefinementWorkflowDelegate(config);
            WorkflowListenerUtil.runWorkflowAndUpdateUI(delegate, getText(), config.getSplevoProjectEditor());
        }

        // close the browser
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        IWorkbenchPage page = window.getActivePage();
        page.closeEditor(vpmRefinementBrowser, false);
    }
    
    /**
     * Retrieves the refinements to be processed from the refinements browser.
     * 
     * @return The list of refinements to be processed.
     */
    protected List<Refinement> getRefinementsFromRefinementBrowser() {
        return this.vpmRefinementBrowser.getRefinementModel().getRefinements();
    }

    /**
     * Extract access to the VPM from a given list of refinements.
     *
     * @param refinements
     *            The refinements to check.
     * @return The VPM or null if none is referenced anyhow.
     */
    private VariationPointModel extractVPM(List<Refinement> refinements) {

        for (Refinement refinement : refinements) {
            EList<VariationPoint> vps = refinement.getVariationPoints();
            if (vps.size() > 0) {
                VariationPointGroup group = (VariationPointGroup) vps.get(0).eContainer();
                VariationPointModel model = (VariationPointModel) group.eContainer();
                return model;
            } else {
                VariationPointModel vpm = extractVPM(refinement.getSubRefinements());
                if (vpm != null) {
                    return vpm;
                }
            }
        }

        return null;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return SPLevoUIPlugin.getImageDescriptor("icons/apply-refinements.gif");
    }

    /**
     * Build the configuration for the workflow.
     *
     * @param refinements
     *            The list of refinements to apply.
     * @return The prepared configuration.
     */
    private VPMRefinementWorkflowConfiguration buildWorflowConfiguration(List<Refinement> refinements) {
        VPMRefinementWorkflowConfiguration config = new VPMRefinementWorkflowConfiguration();
        config.setSplevoProjectEditor(vpmRefinementBrowser.getSPLevoProjectEditor());
        config.getRefinements().addAll(refinements);
        VariationPointModel model = extractVPM(refinements);
        config.setVariationPointModel(model);
        return config;
    }

}
