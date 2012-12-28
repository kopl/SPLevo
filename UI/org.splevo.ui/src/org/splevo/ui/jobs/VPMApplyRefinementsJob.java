package org.splevo.ui.jobs;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.VPMRefinementService;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * Job to read a VPM from the blackboard, 
 * apply a given list of refinements also accessed through the blackboard
 * and store the VPM back into the blackboard.
 */
public class VPMApplyRefinementsJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

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
    public void rollback(IProgressMonitor monitor) throws RollbackFailedException {
        // no rollback possible
    }

    @Override
    public String getName() {
        return "Save feature model Job";
    }
}
