package org.splevo.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.graph.VPMGraph;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * Job to perform set of configured vpm analysis, automatically apply the refinements and store the
 * resulting vpm on the blackboard.
 */
public class VPMAnalysisJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The configuration of the analysis to perform. */
    private VPMAnalyzer analyzer;

    /**
     * Constructor to set configuration of the analysis to perform.
     * 
     * @param analyzer
     *            The configuration of the analysis to perform.
     */
    public VPMAnalysisJob(VPMAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    /**
     * Runs the long running operation.
     * 
     * @param monitor
     *            the progress monitor
     * @throws JobFailedException
     *             Identifies the job could not be executed successfully.
     * @throws UserCanceledException
     *             Identifies the user has canceled the job.
     */
    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        logger.info("Load VPM Graph");
        VPMGraph vpmGraph = getBlackboard().getVpmGraph();

        logger.info("Analyze VPM Graph");
        analyzer.analyze(vpmGraph);

        // finish run
        monitor.done();
    }

    @Override
    public void rollback(IProgressMonitor monitor) throws RollbackFailedException {
        // no rollback possible
    }

    /**
     * Get the name of the job.
     * 
     * @return The name of the job.
     */
    @Override
    public String getName() {
        return "Save feature model Job";
    }
}