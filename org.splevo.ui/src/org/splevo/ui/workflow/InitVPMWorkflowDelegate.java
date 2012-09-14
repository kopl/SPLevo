package org.splevo.ui.workflow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.ui.jobs.InitVPMJob;
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
public class InitVPMWorkflowDelegate
		extends
		AbstractWorkbenchDelegate<BasicSPLevoWorkflowConfiguration, UIBasedWorkflow<Blackboard<?>>> {

	/** The configuration of the workflow. */
	private BasicSPLevoWorkflowConfiguration config = null;

	/**
	 * Constructor requiring a diffing workflow configuration.
	 * 
	 * @param config
	 *            The configuration of the workflow.
	 */
	public InitVPMWorkflowDelegate(BasicSPLevoWorkflowConfiguration config) {
		this.config = config;
	}

	/**
	 * Create the workflow
	 */
	@Override
	protected IJob createWorkflowJob(BasicSPLevoWorkflowConfiguration config) {
		
		OrderPreservingCompositeJob compositeJob = new OrderPreservingCompositeJob();

		// init the parallel extraction
		InitVPMJob initVPMJob = new InitVPMJob(config.getSplevoProject());
		compositeJob.add(initVPMJob);
		
		// init the ui update job
		IJob updateUiJob = new UpdateUIJob(config.getSplevoProjectEditor(),"Initial VPM created");
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
	protected BasicSPLevoWorkflowConfiguration getConfiguration() {
		return this.config;
	}
}
