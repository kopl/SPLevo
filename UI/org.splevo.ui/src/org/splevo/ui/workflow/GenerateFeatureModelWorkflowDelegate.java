package org.splevo.ui.workflow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.jobs.GenerateFeatureModelJob;
import org.splevo.ui.jobs.LoadVPMJob;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.jobs.SaveFeatureModelJob;
import org.splevo.ui.jobs.UpdateUIJob;

import de.uka.ipd.sdq.workflow.Blackboard;
import de.uka.ipd.sdq.workflow.IJob;
import de.uka.ipd.sdq.workflow.OrderPreservingBlackboardCompositeJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * Delegate defining a workflow for the initial diffing of the software models.
 * This includes an update of the user interface to set the paths to the diffing models.
 */
public class GenerateFeatureModelWorkflowDelegate
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
	public GenerateFeatureModelWorkflowDelegate(BasicSPLevoWorkflowConfiguration config) {
		this.config = config;
	}

	/**
	 * Create the workflow
	 */
	@Override
	protected IJob createWorkflowJob(BasicSPLevoWorkflowConfiguration config) {
		
		OrderPreservingBlackboardCompositeJob<SPLevoBlackBoard> compositeJob =
				new OrderPreservingBlackboardCompositeJob<SPLevoBlackBoard>();
		compositeJob.setBlackboard(new SPLevoBlackBoard());

		SPLevoProject splevoProject = config.getSplevoProjectEditor().getSplevoProject();
		
		// load the diff model
		LoadVPMJob loadVPMJob = new LoadVPMJob(splevoProject);
		compositeJob.add(loadVPMJob);
		
		// init the vpm
		GenerateFeatureModelJob generateFMJob = new GenerateFeatureModelJob();
		compositeJob.add(generateFMJob);
		
		// save the model
		String targetPath = splevoProject.getWorkspace()
							+ "models/fm/feature-model.featuremodel";
		SaveFeatureModelJob saveFMJob = new SaveFeatureModelJob(targetPath);
		compositeJob.add(saveFMJob);
		
		// init the ui update job
		IJob updateUiJob = new UpdateUIJob(config.getSplevoProjectEditor(),"Feature model generated");
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
