package org.splevo.ui.jobs;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.google.common.io.PatternFilenameFilter;

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
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss:S");

        this.logger.info("Load source models");
        JavaApplication leadingModel = null;
        JavaApplication integrationModel = null;
        try {
            File leadingModelFile = findRootModelFileInDirectory(basePath + splevoProject.getSourceModelPathLeading());
            leadingModel = KDMUtil.loadKDMModel(leadingModelFile);
            File integrationModelFile = findRootModelFileInDirectory(basePath + splevoProject.getSourceModelPathIntegration());
            integrationModel = KDMUtil.loadKDMModel(integrationModelFile);
        } catch (final IOException e) {
            throw new JobFailedException("Failed to load source models", e);
        }

        logger.info("Init diffing service");
        final Java2KDMDiffingService diffingService = new Java2KDMDiffingService();
        final String diffingRuleRaw = this.splevoProject.getDiffingFilterRules();
        final String[] parts = diffingRuleRaw.split(System.getProperty("line.separator"));
        for (final String rule : parts) {
            diffingService.getIgnorePackages().add(rule);
        }

        logger.info("Diffing started at: " + (dateFormat.format(new Date())));
        DiffModel diffModel = null;
        try {
            diffModel = diffingService.doDiff(integrationModel, leadingModel);
        } catch (final InterruptedException e) {
            throw new JobFailedException("Failed to process diffing.", e);
        }
        logger.info("Diffing finished at: " + (dateFormat.format(new Date())));
        logger.info("Number of differences: " + diffModel.getDifferences().size());

        // check if the process was canceled
        if (monitor.isCanceled()) {
            monitor.done();
            return;
        }

        logger.info("Save Diff Model");
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

    /**
     * Find the MoDisco root model file within a directory.
     * @param basePath The path of the directory to search in.
     * @return The detected File.
     */
    private File findRootModelFileInDirectory(final String basePath) {
        File leadingModelDirectory = new File(basePath);
        String[] rootFiles = leadingModelDirectory.list(new PatternFilenameFilter(".*java2kdm\\.xmi"));
        File moDiscoRootFile = new File(leadingModelDirectory.getAbsolutePath() + File.separator + rootFiles[0]);
        return moDiscoRootFile;
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
