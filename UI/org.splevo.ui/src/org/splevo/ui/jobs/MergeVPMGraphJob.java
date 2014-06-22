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

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.VPMAnalyzerService;
import org.splevo.vpm.analyzer.graph.VPMGraph;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;

/**
 * Job to merge the relationship edges in the VPM graph stored in the blackboard.
 */
public class MergeVPMGraphJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The analyzer service for the merge. */
    private VPMAnalyzerService analyzerService = new DefaultVPMAnalyzerService();

    /**
     * Default constructor.
     */
    public MergeVPMGraphJob() {
    }

    @Override
    public void execute(IProgressMonitor monitor) {

        logger.info("Load VPM Graph");
        VPMGraph vpmGraph = getBlackboard().getVpmGraph();

        logger.info("Analyze VPM Graph");
        analyzerService.mergeGraphEdges(vpmGraph);

        // finish run
        monitor.done();
    }

    @Override
    public String getName() {
        return "Merge VPMGraph Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) {
    }
}
