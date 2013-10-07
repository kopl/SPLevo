package org.splevo.ui.jobs;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.graphstream.algorithm.ConnectedComponents;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMAnalyzerService;
import org.splevo.vpm.analyzer.graph.VPMGraph;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

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

        logStatistics(vpmGraph);
        
        // finish run
        monitor.done();
    }

    /**
     * Log the statistics for a variation point graph.
     * 
     * @param vpmGraph
     *            The vpm graph to log the statics for.
     */
    private void logStatistics(VPMGraph vpmGraph) {
        // number of subgraphs
        ConnectedComponents cc = new ConnectedComponents();
        cc.init(vpmGraph);
        this.logger.info("VPM Analysis Result: #Subgraphs: " + cc.getConnectedComponentsCount());

        this.logger.info("VPM Analysis Result: #Edges: " + vpmGraph.getEdgeCount());

        // number of nodes without relationships
        int singleNodeCounter = 0;
        for (Node node : vpmGraph.getNodeSet()) {
            if (node.getDegree() == 0) {
                singleNodeCounter++;
            }
        }
        this.logger.info("VPM Analysis Result: #SingleNodes " + singleNodeCounter + " of " + vpmGraph.getNodeCount());
    }

    @Override
    public String getName() {
        return "Merge VPMGraph Job";
    }

	@Override
	public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
	}
}
