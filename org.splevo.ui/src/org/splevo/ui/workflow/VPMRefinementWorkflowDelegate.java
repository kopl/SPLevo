package org.splevo.ui.workflow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.jobs.SaveVPMJob;
import org.splevo.ui.jobs.SetRefinementsJob;
import org.splevo.ui.jobs.SetVPMJob;
import org.splevo.ui.jobs.UpdateUIJob;
import org.splevo.ui.jobs.VPMApplyRefinementsJob;

import de.uka.ipd.sdq.workflow.Blackboard;
import de.uka.ipd.sdq.workflow.IJob;
import de.uka.ipd.sdq.workflow.OrderPreservingBlackboardCompositeJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * A delegate defining the workflow for 
 * the refinement of the variation point model.
 */
public class VPMRefinementWorkflowDelegate
		extends
		AbstractWorkbenchDelegate<VPMRefinementWorkflowConfiguration, UIBasedWorkflow<Blackboard<?>>> {
	
	/** The configuration of the workflow. */
	private VPMRefinementWorkflowConfiguration config = null;

	/**
	 * Constructor requiring a basic splevo workflow configuration.
	 * 
	 * @param config
	 *            The configuration of the workflow.
	 */
	public VPMRefinementWorkflowDelegate(VPMRefinementWorkflowConfiguration config) {
		this.config = config;
	}

	/**
	 * Create the workflow
	 */
	@Override
	protected IJob createWorkflowJob(VPMRefinementWorkflowConfiguration config) {
		
		// initialize the basic elements
		SPLevoProject splevoProject = config.getSplevoProjectEditor().getSplevoProject();
		OrderPreservingBlackboardCompositeJob<SPLevoBlackBoard> compositeJob =
				new OrderPreservingBlackboardCompositeJob<SPLevoBlackBoard>();
		compositeJob.setBlackboard(new SPLevoBlackBoard());
				
		SetVPMJob setVpmJob = new SetVPMJob(config.getVariationPointModel());
		compositeJob.add(setVpmJob);
		
		// set the refinements to perform and variation point model in the blackboard
		SetRefinementsJob setRefinementsJob = new SetRefinementsJob(config.getRefinements());
		compositeJob.add(setRefinementsJob);
		
		// perform the refinements automatically
		VPMApplyRefinementsJob vpmApplyRefinementsJob = new VPMApplyRefinementsJob();
		compositeJob.add(vpmApplyRefinementsJob);
		
		// save the latest vpm model
		String modelNamePrefix = ""+splevoProject.getVpmModelPaths().size();
		String targetPath = splevoProject.getWorkspace()
							+ "models/vpms/"
							+ modelNamePrefix
							+ "-vpm.vpm";
		SaveVPMJob saveVPMJob = new SaveVPMJob(splevoProject, targetPath);
		compositeJob.add(saveVPMJob);
		
		// init the ui update job
		IJob updateUiJob = new UpdateUIJob(config.getSplevoProjectEditor(),"VPM refined");
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
	protected VPMRefinementWorkflowConfiguration getConfiguration() {
		return this.config;
	}
}
