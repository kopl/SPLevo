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
package org.splevo.ui.jobs;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.VPMAnalyzerService;
import org.splevo.vpm.analyzer.refinement.DetectionRule;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementFactory;
import org.splevo.vpm.refinement.RefinementModel;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;

/**
 * Job to apply the detection rules and store a refinement model containing the identified models in
 * the black board.
 */
public class DetectRefinementsJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The variation point analyzer service to work with. */
    private VPMAnalyzerService vpmAnalyzerService = new DefaultVPMAnalyzerService();

    /** The detection rules to be applied. */
    private List<DetectionRule> detectionRules;

    /** Option to detect related VPs that can be merged. */
    private boolean useMergeDetection;
    
    private boolean fullRefinementReasons;

    /**
     * Default constructor for the job.
     *
     * @param detectionRules
     *            The detection rules to apply in the job.
     * @param useMergeDetection
     *            Option to detect related VPs that can be merged.
     * @param fullRefinementReasons
     *            Option to collect full refinement reasons.
     */
    public DetectRefinementsJob(List<DetectionRule> detectionRules, boolean useMergeDetection, boolean fullRefinementReasons) {
        this.detectionRules = detectionRules;
        this.useMergeDetection = useMergeDetection;
        this.fullRefinementReasons = fullRefinementReasons;
    }

    /**
     * Run the refinement detection.
     *
     * {@inheritDoc}
     */
    @Override
    public void execute(IProgressMonitor monitor) {

        List<Refinement> refinements = vpmAnalyzerService.deriveRefinements(getBlackboard().getVpmGraph(),
                detectionRules, useMergeDetection, fullRefinementReasons);

        // check if the process was canceled
        if (monitor.isCanceled()) {
            monitor.done();
            logger.info("Workflow cancled.");
            return;
        }

        logger.info("Store Refinement model in blackboard (" + refinements.size() + " Refinements)");
        RefinementModel refinementModel = RefinementFactory.eINSTANCE.createRefinementModel();
        refinementModel.getRefinements().addAll(refinements);
        getBlackboard().setRefinementModel(refinementModel);

        // finish run
        monitor.done();
    }

    @Override
    public String getName() {
        return "Detect Refinements Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) {
    }
}
