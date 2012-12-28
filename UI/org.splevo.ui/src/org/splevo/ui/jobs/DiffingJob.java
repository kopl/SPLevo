package org.splevo.ui.jobs;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.diffing.Java2KDMDiffingService;
import org.splevo.modisco.util.KDMUtil;
import org.splevo.project.SPLevoProject;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * Job to execute the diffing on the source models provided through the blackboard.
 */
public class DiffingJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The splevo project to store the required data to. */
    private final SPLevoProject splevoProject;

    /**
     * Constructor for the diffing job.
     * 
     * @param splevoProject
     *            The SPLevo project to interact with.
     */
    public DiffingJob(final SPLevoProject splevoProject) {
        this.splevoProject = splevoProject;
    }

    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final String basePath = workspace.getRoot().getRawLocation().toOSString();

        this.logger.info("Load source models");
        JavaApplication leadingModel = null;
        JavaApplication integrationModel = null;
        try {

            leadingModel = KDMUtil.loadKDMModel(new File(basePath + this.splevoProject.getSourceModelPathLeading()));
            integrationModel = KDMUtil.loadKDMModel(new File(basePath
                    + this.splevoProject.getSourceModelPathIntegration()));
        } catch (final IOException e) {
            throw new JobFailedException("Failed to load source models", e);
        }

        this.logger.info("Init diffing service");
        final Java2KDMDiffingService diffingService = new Java2KDMDiffingService();
        final String diffingRuleRaw = this.splevoProject.getDiffingFilterRules();
        final String[] parts = diffingRuleRaw.split(System.getProperty("line.separator"));
        for (final String rule : parts) {
            diffingService.getIgnorePackages().add(rule);
        }

        this.logger.info("Execute diffing");
        DiffModel diffModel = null;
        try {
            diffModel = diffingService.doDiff(integrationModel, leadingModel);
        } catch (final InterruptedException e) {
            throw new JobFailedException("Failed to process diffing.", e);
        }

        // check if the process was canceled
        if (monitor.isCanceled()) {
            monitor.done();
            return;
        }

        this.logger.info("Save Diff Model");
        try {
            final String targetPath = this.splevoProject.getWorkspace() + "models/diffmodel/diffModel.java2kdmdiff";

            ModelUtils.save(diffModel, basePath + targetPath);
            this.splevoProject.setDiffingModelPath(targetPath);
        } catch (final IOException e) {
            throw new JobFailedException("Failed to save diff model.", e);
        }

        // refresh workspace
        try {
            workspace.getRoot().refreshLocal(IResource.DEPTH_ONE, monitor);
        } catch (final CoreException e) {
            e.printStackTrace();
        }

        // finish run
        monitor.done();
    }

    @Override
    public void rollback(final IProgressMonitor monitor) throws RollbackFailedException {
        // no rollback possible
    }

    @Override
    public String getName() {
        return "Diff source models Job";
    }
}
