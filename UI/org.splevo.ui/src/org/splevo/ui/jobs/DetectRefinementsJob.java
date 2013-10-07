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
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Job to apply the detection rules and store a refinement model containing the identified models in
 * the black board.
 */
public class DetectRefinementsJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The variation point analyzer service to work with. */
    private VPMAnalyzerService vpmAnalyzerService = new DefaultVPMAnalyzerService();

    /** The detection rules to be applied. */
    private List<DetectionRule> detectionRules;
    
    /**
     * Default constructor for the job.
     * @param detectionRules The detection rules to apply in the job.
     */
    public DetectRefinementsJob(List<DetectionRule> detectionRules) {
        this.detectionRules = detectionRules;
    }

    /**
     * Runs the long running operation.
     * 
     * @param monitor
     *            the progress monitor
     * @throws JobFailedException
     *             Identifies the job could not be finished successfully.
     * @throws UserCanceledException
     *             Identifies the user has canceled the work flow.
     */
    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        List<Refinement> refinements = vpmAnalyzerService.deriveRefinements(getBlackboard().getVpmGraph(),
                detectionRules);

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
	public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
	}
}
