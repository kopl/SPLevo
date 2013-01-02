package org.splevo.ui.jobs;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMAnalyzerService;
import org.splevo.vpm.analyzer.graph.VPMGraph;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * Job to create edges for the VPMAnalyzer results in the VPMGraph.
 * 
 * The job
 * <ul>
 * <li>Read the VPMAnalyzerResults from the black board</li>
 * <li>Read the VPMGraph from the black board</li>
 * <li>Create edges for all edge descriptors in the analyzer results</li>
 * </ul>
 */
public class MergeVPMAnalyzerResultsIntoGraphJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The analyzer service for the merge. */
    private VPMAnalyzerService analyzerService = new DefaultVPMAnalyzerService();

    /**
     * Default constructor.
     */
    public MergeVPMAnalyzerResultsIntoGraphJob() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        logger.info("Load VPM Graph");
        VPMGraph vpmGraph = getBlackboard().getVpmGraph();

        logger.info("Load VPM analyzer results");
        List<VPMAnalyzerResult> analyzerResults = getBlackboard().getVpmAnalyzerResults();

        logger.info("Merge analyzer results into graph.");
        if (analyzerResults.size() == 0) {
            logger.warn("No VPMAnalyzer results found in the blackboard");
        }
        analyzerService.createGraphEdges(vpmGraph, analyzerResults);

        // finish run
        monitor.done();
    }

    @Override
    public void rollback(IProgressMonitor monitor) throws RollbackFailedException {
        // no rollback possible
    }

    @Override
    public String getName() {
        return "Merge VPMGraph Job";
    }
}
