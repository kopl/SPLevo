package org.splevo.ui.jobs;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.splevo.ui.views.taskview.TaskView;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Job to open the task view.
 */
public class OpenTaskViewJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

	@Override
	public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
		
	}

	@Override
	public void execute(IProgressMonitor arg0) throws JobFailedException,
			UserCanceledException {
		
			final Exception[] thrownException = new Exception[1];
	
        	PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				
				@Override
				public void run() {
					IViewPart viewPart;
					try {
						viewPart = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().showView(TaskView.ID, "TaskView", IWorkbenchPage.VIEW_ACTIVATE);
					} catch (PartInitException e) {
						thrownException[0] = e;
						return;
					}
					
					final TaskView view = (TaskView) viewPart;

			        PlatformUI.getWorkbench().addWorkbenchListener(new IWorkbenchListener() {

			            @Override
			            public boolean preShutdown(IWorkbench workbench, boolean forced) {
			                IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			                activePage.hideView(view);
			                return true;
			            }

			            @Override
			            public void postShutdown(IWorkbench workbench) {
			            }
			        });
				}
			});
        	
        	if (thrownException[0] != null) {
        		throw new JobFailedException("Open View Job Failed", thrownException[0]);
        	}
	}

	@Override
	public String getName() {
		return "Get All SPLevo Tasks";
	}

}
