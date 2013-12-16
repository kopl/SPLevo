package org.splevo.ui.jobs;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import de.uka.ipd.sdq.workflow.jobs.AbstractJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Job to refresh the active eclipse workspace.
 *
 * @author Benjamin Klatt
 *
 */
public class RefreshWorkspaceJob extends AbstractJob {

    @Override
    public String getName() {
        return "Refresh Workspace Job";
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        monitor.beginTask(getName(), 100);

        // refresh workspace
        try {
            ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, monitor);
        } catch (CoreException e) {
            e.printStackTrace();
        }

    }

	@Override
	public void cleanup(IProgressMonitor monitor) throws CleanupFailedException {
	}
}
