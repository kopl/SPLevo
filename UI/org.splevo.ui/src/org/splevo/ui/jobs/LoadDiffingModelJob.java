package org.splevo.ui.jobs;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.splevo.diffing.DiffingModelUtil;
import org.splevo.project.SPLevoProject;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Job to load a diffing model in the blackboard.
 */
public class LoadDiffingModelJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The splevo project to store the required data to. */
    private SPLevoProject splevoProject;

    /**
     * Constructor to set a reference to the splevoproject.
     * 
     * @param splevoProject
     *            The reference to the splevoproject.
     */
    public LoadDiffingModelJob(SPLevoProject splevoProject) {
        this.splevoProject = splevoProject;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        logger.info("Load diff models");
        File diffModelFile = new File(splevoProject.getDiffingModelPath());
        
        DiffModel diffModel;
        try {
            diffModel = DiffingModelUtil.loadModel(diffModelFile);
        } catch (IOException e) {
            throw new JobFailedException("Failed to load diff model.", e);
        }

        logger.info("Put diff model on the blackboard");
        getBlackboard().setDiffModel(diffModel);

        // finish run
        monitor.done();
    }

    @Override
    public String getName() {
        return "Load diffing model Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
    }
}
