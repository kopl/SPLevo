package org.splevo.ui.workflow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.jobs.GenerateFeatureModelJob;
import org.splevo.ui.jobs.LoadVPMJob;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.jobs.SaveFeatureModelJob;
import org.splevo.ui.jobs.UpdateUIJob;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * Delegate defining a work flow to initialize a feature model based on the
 * latest variation point model.
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
	public GenerateFeatureModelWorkflowDelegate(
			BasicSPLevoWorkflowConfiguration config) {
		this.config = config;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IJob createWorkflowJob(BasicSPLevoWorkflowConfiguration config) {

		SequentialBlackboardInteractingJob<SPLevoBlackBoard> jobSequence = new SequentialBlackboardInteractingJob<SPLevoBlackBoard>();
		jobSequence.setBlackboard(new SPLevoBlackBoard());

		SPLevoProject splevoProject = config.getSplevoProjectEditor()
				.getSplevoProject();

		// load the diff model
		LoadVPMJob loadVPMJob = new LoadVPMJob(splevoProject);
		jobSequence.add(loadVPMJob);

		// init the vpm
		GenerateFeatureModelJob generateFMJob = new GenerateFeatureModelJob(splevoProject);
		jobSequence.add(generateFMJob);

		// save the model
		String targetPath = splevoProject.getWorkspace()
				+ "models/fm/feature-model.featuremodel";
		SaveFeatureModelJob saveFMJob = new SaveFeatureModelJob(targetPath);
		jobSequence.add(saveFMJob);

		// init the ui update job
		UpdateUIJob updateUiJob = new UpdateUIJob(
				config.getSplevoProjectEditor(), "Feature model generated");
		jobSequence.add(updateUiJob);

		// return the prepared workflow
		return jobSequence;
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
