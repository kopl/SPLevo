package org.splevo.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.featuremodel.FeatureModel;
import org.splevo.fm.builder.VPM2FMBuilder;
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Job to generate a feature model from a variation point model.
 */
public class GenerateFeatureModelJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The splevo project to store the required data to. */
    private SPLevoProject splevoProject;

    /**
     * Constructor to set the reference to the SPLevo project.
     * 
     * @param splevoProject
     *            The SPLevo project to exchange data with.
     */
    public GenerateFeatureModelJob(SPLevoProject splevoProject) {
        this.splevoProject = splevoProject;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        logger.info("Build initival vpm model");
        VariationPointModel vpm = getBlackboard().getVariationPointModel();
        if (vpm == null) {
            throw new JobFailedException("No variation point model available in the blackboard.");
        }
        VPM2FMBuilder builder = new VPM2FMBuilder();
        FeatureModel featureModel = builder.buildFeatureModel(vpm, splevoProject.getName());

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
    public String getName() {
        return "Generate Feature Model Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
    }
}
