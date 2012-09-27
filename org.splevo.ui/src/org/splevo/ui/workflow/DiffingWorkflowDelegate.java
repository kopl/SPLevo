package org.splevo.ui.workflow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.ui.jobs.DiffingJob;
import org.splevo.ui.jobs.UpdateUIJob;

import de.uka.ipd.sdq.workflow.Blackboard;
import de.uka.ipd.sdq.workflow.IJob;
import de.uka.ipd.sdq.workflow.OrderPreservingCompositeJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * Delegate defining a workflow for the initial diffing of the software models.
 * This includes an update of the user interface to set the paths to the diffing models.
 */
public class DiffingWorkflowDelegate
		extends
		AbstractWorkbenchDelegate<DiffingWorkflowConfiguration, UIBasedWorkflow<Blackboard<?>>> {

	/** The configuration of the workflow. */
	private DiffingWorkflowConfiguration config = null;

	/**
	 * Constructor requiring a diffing workflow configuration.
	 * 
	 * @param config
	 *            The configuration of the workflow.
	 */
	public DiffingWorkflowDelegate(DiffingWorkflowConfiguration config) {
		this.config = config;
	}

	/**
	 * Create the workflow
	 */
	@Override
	protected IJob createWorkflowJob(DiffingWorkflowConfiguration config) {
		
		OrderPreservingCompositeJob compositeJob = new OrderPreservingCompositeJob();

		// init the parallel extraction
		DiffingJob diffingJob = new DiffingJob(config.getSplevoProjectEditor().getSplevoProject());
		compositeJob.add(diffingJob);
		
		// init the ui update job
		IJob updateUiJob = new UpdateUIJob(config.getSplevoProjectEditor(),"Diffing performed");
		compositeJob.add(updateUiJob);

		// return the prepared workflow
		return compositeJob;
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// nothing to do here
	}

	@Override
	protected boolean useSeparateConsoleForEachJobRun() {
		return false;
	}

	@Override
	protected DiffingWorkflowConfiguration getConfiguration() {
		return this.config;
	}
}
