package org.splevo.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.VPMAnalyzerService;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Job to initialize the variation point graph from the variation point model. Both are exchanged
 * with the blackboard.
 */
public class InitVPMGraphJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /**
     * Runs the long running operation.
     * 
     * @param monitor
     *            the progress monitor
     * @throws JobFailedException
     *             Identifies the job could not be executed successfully.
     * @throws UserCanceledException
     *             Identifies the user has canceled the job.
     * 
     */
    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        logger.info("Get VPM from blackboard");
        VariationPointModel vpm = getBlackboard().getVariationPointModel();

        logger.info("Initialize graph");
        VPMAnalyzerService service = new DefaultVPMAnalyzerService();
        VPMGraph graph = service.initVPMGraph(vpm);

        // check if the process was canceled
        if (monitor.isCanceled()) {
            monitor.done();
            logger.info("Workflow cancled.");
            return;
        }

        logger.info("Store graph in blackboard");
        getBlackboard().setVpmGraph(graph);

        // finish run
        monitor.done();
    }

    /**
     * Get the name of the job.
     * 
     * @return Get the name of the job.
     */
    @Override
    public String getName() {
        return "Init VPMGraph Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
    }
}
