package org.splevo.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.featuremodel.FeatureModel;
import org.splevo.fm.builder.VPM2FMBuilder;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * Job to generate a feature model from a variation point model.
 */
public class GenerateFeatureModelJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        logger.info("Build initival vpm model");
        VariationPointModel vpm = getBlackboard().getVariationPointModel();
        if (vpm == null) {
            throw new JobFailedException("No variation point model available in the blackboard.");
        }
        VPM2FMBuilder builder = new VPM2FMBuilder();
        FeatureModel featureModel = builder.buildFeatureModel(vpm);

        // check if the process was canceled
        if (monitor.isCanceled()) {
            monitor.done();
            return;
        }

        logger.info("Store VPM model in blackboard");
        getBlackboard().setFeatureModel(featureModel);

        // finish run
        monitor.done();
    }

    @Override
    public void rollback(IProgressMonitor monitor) throws RollbackFailedException {
        // no rollback possible
    }

    @Override
    public String getName() {
        return "Generate Feature Model Job";
    }
}
