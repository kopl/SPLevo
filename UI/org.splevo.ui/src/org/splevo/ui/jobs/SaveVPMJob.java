package org.splevo.ui.jobs;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.VPMUtil;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Job to save the variation point model currently present in the blackboard.
 */
public class SaveVPMJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The splevo project to store the required data to. */
    private SPLevoProject splevoProject;

    /** The path to write the model to. */
    private String targetPath;

    /**
     * Constructor to set the reference to the splevo project and the target path to write the model
     * to.
     * 
     * @param splevoProject
     *            The project to update.
     * @param targetPath
     *            The eclipse workspace relative path to write to.
     */
    public SaveVPMJob(SPLevoProject splevoProject, String targetPath) {
        this.splevoProject = splevoProject;
        this.targetPath = targetPath;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        VariationPointModel vpm = getBlackboard().getVariationPointModel();

        logger.info("Save VPM Model");
        try {
            VPMUtil.save(vpm, new File(targetPath));
            splevoProject.getVpmModelPaths().add(targetPath);
        } catch (IOException e) {
            throw new JobFailedException("Failed to save vpm model.", e);
        }

        // finish run
        monitor.done();
    }

    @Override
    public String getName() {
        return "Save VPM model Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
    }
}
