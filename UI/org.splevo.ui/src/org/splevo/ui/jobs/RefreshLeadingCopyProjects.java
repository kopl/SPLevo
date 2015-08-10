package org.splevo.ui.jobs;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.splevo.project.SPLevoProject;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Job to refresh all leading copy projects located in the workspace.
 */
public class RefreshLeadingCopyProjects extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    private static final Logger LOGGER = Logger.getLogger(RefreshLeadingCopyProjects.class);
    private final SPLevoProject splevoProject;

    /**
     * Constructs a new refresh job.
     * 
     * @param splevoProject
     *            The SPLevoProject for leading projects examiniation
     */
    public RefreshLeadingCopyProjects(SPLevoProject splevoProject) {
        this.splevoProject = splevoProject;
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
        // nothing to do here
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        monitor.beginTask("Refreshing", splevoProject.getLeadingProjects().size());

        for (String leadingProjectName : splevoProject.getLeadingProjects()) {
            IProject leadingProject = ResourcesPlugin.getWorkspace().getRoot().getProject(leadingProjectName);
            if (leadingProject == null) {
                LOGGER.warn("Could not refresh leading project " + leadingProjectName
                        + " because we could not find it in the workspace.");
            } else {
                try {
                    leadingProject.refreshLocal(IProject.DEPTH_INFINITE, new NullProgressMonitor());
                } catch (CoreException e) {
                    throw new JobFailedException(e);
                }
            }
            monitor.worked(1);
        }

        // wait for rebuilds to finish
        try {
            IJobManager jobManager = Job.getJobManager();
            jobManager.join(ResourcesPlugin.FAMILY_MANUAL_BUILD, null);
            jobManager.join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
        } catch (InterruptedException e) {
            LOGGER.warn("Refreshing might not be finished, but we received an interrupt.", e);
        }

        monitor.done();
    }

    @Override
    public String getName() {
        return "Refresh leading copy projects job.";
    }

}
