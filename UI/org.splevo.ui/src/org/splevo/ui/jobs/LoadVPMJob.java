package org.splevo.ui.jobs;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.splevo.extraction.DefaultExtractionService;
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.VPMUtil;
import org.splevo.vpm.software.SoftwarePackage;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityPackage;

import com.google.common.collect.Lists;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Job to load a variation point model into the blackboard.
 */
public class LoadVPMJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The splevo project to get the vpm info from. */
    private SPLevoProject splevoProject;

    /**
     * The index of the vpm to be loaded. If this is -1 the latest one will be loaded.
     */
    private int targetVPMIndex = -1;

    /**
     * Constructor to set a reference to the splevoproject.
     *
     * @param splevoProject
     *            The reference to the splevoproject.
     */
    public LoadVPMJob(SPLevoProject splevoProject) {
        this.splevoProject = splevoProject;
        this.targetVPMIndex = -1;
    }

    /**
     * Constructor to set a reference to the splevo project and the index of the vpm to be loaded.
     *
     * @param splevoProject
     *            The reference to the splevo project.
     * @param index
     *            The index of the vpm.
     */
    public LoadVPMJob(SPLevoProject splevoProject, int index) {
        this.splevoProject = splevoProject;
        this.targetVPMIndex = index;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        logger.info("Load vpm model");
        variabilityPackage.eINSTANCE.eClass();
        SoftwarePackage.eINSTANCE.eClass();

        int index = targetVPMIndex;
        if (targetVPMIndex == -1) {
            index = splevoProject.getVpmModelPaths().size() - 1;
        }

        ResourceSetImpl resourceSet = initResourceSet();

        VariationPointModel vpm;
        try {
            File vpmFile = new File(splevoProject.getVpmModelPaths().get(index));
            vpm = VPMUtil.loadVariationPointModel(vpmFile, resourceSet);
        } catch (Exception e) {
            throw new JobFailedException("Failed to load vpm model.", e);
        }

        logger.info("Put variation point model on the blackboard");
        getBlackboard().setVariationPointModel(vpm);

        // finish run
        monitor.done();
    }

    /**
     * Initialize the resource set including preparation by the source model extractors for specific
     * source models.
     *
     * @return The initialized resource set.
     */
    private ResourceSetImpl initResourceSet() {
        ResourceSetImpl resourceSet = new ResourceSetImpl();
        DefaultExtractionService extractionService = new DefaultExtractionService();
        List<String> sourceModelPaths = Lists.newArrayList();
        sourceModelPaths.add(splevoProject.getSourceModelPathLeading());
        sourceModelPaths.add(splevoProject.getSourceModelPathIntegration());
        extractionService.prepareResourceSet(resourceSet, sourceModelPaths);
        return resourceSet;
    }

    @Override
    public String getName() {
        return "Load VPM model Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
    }
}
