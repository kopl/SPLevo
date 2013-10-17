package org.splevo.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.VPMAnalyzerService;
import org.splevo.vpm.analyzer.graph.VPMGraph;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

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
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

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
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
    }
}
