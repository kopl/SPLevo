package org.splevo.ui.jobs;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.project.SPLevoProject;
import org.splevo.refactoring.DefaultRefactoringService;
import org.splevo.refactoring.RefactoringService;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Copies the leading source project into a separate folder and refactores it based on the VPM
 * model.
 */
public class RefactorJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    private SPLevoProject splevoProject;

    /**
     * Initializes this job with the given {@link SPLevoProject}.
     * 
     * @param splevoProject
     *            The {@link SPLevoProject}.
     */
    public RefactorJob(SPLevoProject splevoProject) {
        this.splevoProject = splevoProject;
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
        // nothing to do here
    }

    @Override
    public void execute(IProgressMonitor arg0) throws JobFailedException, UserCanceledException {
        // get VPM and output path
        VariationPointModel variationPointModel = getBlackboard().getVariationPointModel();
        String outputPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toPortableString()
                + splevoProject.getWorkspace() + "spl/";

        // trigger refactoring
        final RefactoringService refactoringService = new DefaultRefactoringService();
        refactoringService.buildSoftwareProductLine(variationPointModel, outputPath);
    }

    @Override
    public String getName() {
        return "Refactoring Job";
    }

}
