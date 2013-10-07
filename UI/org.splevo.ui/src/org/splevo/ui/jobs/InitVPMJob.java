package org.splevo.ui.jobs;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.builder.DefaultVPMBuilderService;
import org.splevo.vpm.builder.VPMBuildException;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Job to initialize a variation point model from a diffing model.
 */
public class InitVPMJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The splevo project to store the required data to. */
    private SPLevoProject splevoProject;

    /**
     * Constructor to set the reference to the SPLevo project.
     * 
     * @param splevoProject
     *            The SPLevo project to exchange data with.
     */
    public InitVPMJob(SPLevoProject splevoProject) {
        this.splevoProject = splevoProject;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        String basePath = workspace.getRoot().getRawLocation().toOSString();

        logger.info("Load diff models");
        File diffModelFile = new File(basePath + splevoProject.getDiffingModelPath());
        DiffModel diffModel;
        try {
            diffModel = (DiffModel) ModelUtils.load(diffModelFile, new ResourceSetImpl());
        } catch (IOException e) {
            throw new JobFailedException("Failed to load diff model.", e);
        }

        logger.info("Build initival vpm model");
        Map<String, Object> builderOptions = new LinkedHashMap<String, Object>();
        DefaultVPMBuilderService builderService = new DefaultVPMBuilderService();
        VariationPointModel vpm;
        try {
            vpm = builderService.buildVPM(diffModel, splevoProject.getVariantNameLeading(), splevoProject.getVariantNameIntegration(), builderOptions);
        } catch (VPMBuildException e) {
            throw new JobFailedException("Failed to initialize the VPM model", e);
        }

        // check if the process was canceled
        if (monitor.isCanceled()) {
            monitor.done();
            logger.info("Workflow cancled.");
            return;
        }

        logger.info("Store VPM model in blackboard");
        getBlackboard().setVariationPointModel(vpm);

        // finish run
        monitor.done();
    }

    @Override
    public String getName() {
        return "Init VPM model Job";
    }

	@Override
	public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
	}
}
