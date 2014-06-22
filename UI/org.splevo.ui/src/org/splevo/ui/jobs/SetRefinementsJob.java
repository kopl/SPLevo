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

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;

/**
 * Job to set a list of refinements in the blackboard. The refinements must be set in the job
 * through the constructor.
 */
public class SetRefinementsJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The refinements to set in the blackboard. */
    private List<Refinement> refinements;

    /**
     * Constructor to set the refinements to be applied.
     *
     * @param refinements
     *            The reference to the refinements.
     */
    public SetRefinementsJob(List<Refinement> refinements) {
        this.refinements = refinements;
    }

    @Override
    public void execute(IProgressMonitor monitor) {

        logger.info("Set the refinements to perform in the blackboard");
        getBlackboard().getRefinementsToApply().addAll(refinements);

        // finish run
        monitor.done();
    }

    @Override
    public String getName() {
        return "Set refinements Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) {
    }
}
