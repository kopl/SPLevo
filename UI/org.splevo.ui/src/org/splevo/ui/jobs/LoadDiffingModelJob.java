package org.splevo.ui.jobs;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.splevo.diffing.DefaultDiffingService;
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

        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        String basePath = workspace.getRoot().getRawLocation().toOSString();

        // trigger loading the diff extensions.
        DefaultDiffingService diffingService = new DefaultDiffingService();
        diffingService.getDiffers();

        logger.info("Load diff models");
        File diffModelFile = new File(basePath + splevoProject.getDiffingModelPath());
        DiffModel diffModel;
        try {
            diffModel = (DiffModel) ModelUtils.load(diffModelFile, new ResourceSetImpl());
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
