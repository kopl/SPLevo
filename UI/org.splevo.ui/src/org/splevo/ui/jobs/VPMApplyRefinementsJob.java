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
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.VPMRefinementService;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;

/**
 * Job to read a VPM from the blackboard, apply a given list of refinements also accessed through
 * the blackboard and store the VPM back into the blackboard.
 */
public class VPMApplyRefinementsJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    @Override
    public void execute(IProgressMonitor monitor) {

        logger.info("Load VPM Model");
        VariationPointModel vpm = getBlackboard().getVariationPointModel();

        // check if the process was canceled
        if (monitor.isCanceled()) {
            monitor.done();
            logger.info("Workflow cancled.");
            return;
        }

        // apply the refinements currently stored in the blackboard
        List<Refinement> refinementsToPerform = getBlackboard().getRefinementsToApply();
        logger.info("#Refinements to be performed: " + refinementsToPerform.size());
        VPMRefinementService service = new VPMRefinementService();
        VariationPointModel newVPM = service.applyRefinements(refinementsToPerform, vpm);

        logger.info("Store VPM model in blackboard");
        getBlackboard().setVariationPointModel(newVPM);

        // finish run
        monitor.done();
    }

    @Override
    public String getName() {
        return "Save feature model Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) {
    }
}
