/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    CONTRIBUTR_FIRST_AND_LAST_NAME - WORK_DONE (e.g. "initial API and implementation and/or initial documentation")
 *******************************************************************************/
package org.splevo.ui.wizards.vpmanalysis;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.listeners.WorkflowListenerUtil;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration;
import org.splevo.ui.workflow.VPMAnalysisWorkflowDelegate;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.refinement.Refinement;

/**
 * Listener to react on page transitions of the analysis wizard.
 */
public class VPMAnalysisWizardPageChangeListener implements IPageChangedListener {

    private List<VPMAnalyzer> analyzers = null;
    private VPMAnalysisWizard vpmAnalysisWizard;

    /**
     * Constructor to link the listener with wizard it listens to.
     *
     * @param vpmAnalysisWizard
     *            The wizard to access.
     */
    public VPMAnalysisWizardPageChangeListener(VPMAnalysisWizard vpmAnalysisWizard) {
        this.vpmAnalysisWizard = vpmAnalysisWizard;
    }

    @Override
    public void pageChanged(PageChangedEvent event) {
        Object selectedPage = event.getSelectedPage();

        vpmAnalysisWizard.updateConfiguration();

        if (selectedPage instanceof VPMAnalyzerConfigurationPage) {
            VPMAnalyzerConfigurationPage configPage = (VPMAnalyzerConfigurationPage) selectedPage;
            analyzers = configPage.getAnalyzers();

        } else if (selectedPage instanceof ResultHandlingConfigurationPage) {
            ResultHandlingConfigurationPage resultPage = (ResultHandlingConfigurationPage) selectedPage;
            if (analyzers != null) {
                resultPage.setSelectedAnalyzers(analyzers);
            }

        } else if (selectedPage instanceof VPMRefinementPage) {

            final VPMRefinementPage refinementPage = (VPMRefinementPage) selectedPage;
            refinementPage.reset();
            refinementPage.setMessage("Variation Point Analysis in progress ...", DialogPage.INFORMATION);

            VPMAnalysisWorkflowConfiguration config = vpmAnalysisWizard.getAnalysisWorkflowConfiguration();
            final SPLevoBlackBoard blackBoard = new SPLevoBlackBoard();
            VPMAnalysisWorkflowDelegate delegate = new VPMAnalysisWorkflowDelegate(config, blackBoard, true);

            Runnable uiProcess = new Runnable() {
                @Override
                public void run() {
                    EList<Refinement> refinements = blackBoard.getRefinementModel().getRefinements();
                    refinementPage.setRefinements(refinements);
                }
            };

            WorkflowListenerUtil.runWorkflowAndRunUITask(delegate, "Refine VPM", uiProcess);

        }
    }
}
