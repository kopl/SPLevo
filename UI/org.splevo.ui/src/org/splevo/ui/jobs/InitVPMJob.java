package org.splevo.ui.jobs;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.builder.java2kdmdiff.Java2KDMVPMBuilder;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

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
        Java2KDMDiffPackage.eINSTANCE.eClass();
        File diffModelFile = new File(basePath + splevoProject.getDiffingModelPath());
        DiffModel diffModel;
        try {
            diffModel = (DiffModel) ModelUtils.load(diffModelFile, new ResourceSetImpl());
        } catch (IOException e) {
            throw new JobFailedException("Failed to load diff model.", e);
        }

        logger.info("Build initival vpm model");
        Java2KDMVPMBuilder java2KDMVPMBuilder = new Java2KDMVPMBuilder();
        VariationPointModel vpm = java2KDMVPMBuilder.buildVPM(diffModel);

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
    public void rollback(IProgressMonitor monitor) throws RollbackFailedException {
        // no rollback possible
    }

    @Override
    public String getName() {
        return "Init VPM model Job";
    }
}
