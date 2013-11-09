package org.splevo.ui.jobs;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.featuremodel.FeatureModel;
import org.splevo.fm.builder.FeatureModelUtil;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Job to save the feature model stored in the blackboard.
 */
public class SaveFeatureModelJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The path to write the model to. */
    private String targetPath;

    /**
     * Constructor to set the target path to write the model to.
     * 
     * @param targetPath
     *            The eclipse workspace relative path to write to.
     */
    public SaveFeatureModelJob(String targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        FeatureModel fm = getBlackboard().getFeatureModel();

        logger.info("Save feature model");
        try {
            FeatureModelUtil.save(fm, new File(targetPath));
        } catch (IOException e) {
            throw new JobFailedException("Failed to save feature model.", e);
        }

        // finish run
        monitor.done();
    }

    @Override
    public String getName() {
        return "Save Feature Model Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
    }
}
