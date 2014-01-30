package org.splevo.ui.jobs;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.fm.builder.FeatureModelBuilderService;
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Lists;

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
        FeatureModelBuilderService service = new FeatureModelBuilderService();
        // TODO make configurable and do not trigger all builders
        List<String> ids = Lists.newArrayList();
        for (String builderId : service.getAvailableBuilders().keySet()) {
            ids.add(builderId);
        }
        String targetPath = getModelFilePath(splevoProject);
        service.buildAndSaveModels(ids, vpm, splevoProject.getName(), targetPath);

        // check if the process was canceled
        if (monitor.isCanceled()) {
            monitor.done();
            return;
        }

        // finish run
        monitor.done();
    }

    /**
     * Return the Feature Model file path.
     *
     * @param splevoProject
     *            The {@link SPLevoProject} to get the workspace from.
     * @return the file {@link IPath}
     */
    private String getModelFilePath(SPLevoProject splevoProject) {
        String path = splevoProject.getWorkspace() + "models" + File.separator + "fm" + File.separator;
        return path;
    }

    @Override
    public String getName() {
        return "Generate Feature Model Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
    }
}
