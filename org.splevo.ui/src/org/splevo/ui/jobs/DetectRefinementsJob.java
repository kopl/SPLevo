package org.splevo.ui.jobs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.VPMAnalyzerService;
import org.splevo.vpm.analyzer.refinement.BasicDetectionRule;
import org.splevo.vpm.analyzer.refinement.DetectionRule;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementFactory;
import org.splevo.vpm.refinement.RefinementModel;
import org.splevo.vpm.refinement.RefinementType;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * Job to apply the detection rules and store a refinement model containing the identified models in
 * the black board.
 */
public class DetectRefinementsJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The variation point analyzer service to work with. */
    private VPMAnalyzerService vpmAnalyzerService = new DefaultVPMAnalyzerService();

    /**
     * Default constructor for the job.
     */
    public DetectRefinementsJob() {
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

        // init default detection rules
        // TODO: make the detection rules configurable by the user
        List<String> edgeLabels = new ArrayList<String>();
        edgeLabels.add("CodeStructure");
        DetectionRule detectionRule = new BasicDetectionRule(edgeLabels, RefinementType.MERGE);

        List<DetectionRule> detectionRules = new ArrayList<DetectionRule>();
        detectionRules.add(detectionRule);

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
    public void rollback(IProgressMonitor monitor) throws RollbackFailedException {
        // no rollback possible
    }

    @Override
    public String getName() {
        return "Detect Refinements Job";
    }
}
