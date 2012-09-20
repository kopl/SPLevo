package org.splevo.ui.workflow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.jobs.LoadVPMJob;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.jobs.SaveVPMJob;
import org.splevo.ui.jobs.UpdateUIJob;
import org.splevo.ui.jobs.VPMAnalysisJob;
import org.splevo.ui.jobs.VPMApplyRefinementsJob;

import de.uka.ipd.sdq.workflow.Blackboard;
import de.uka.ipd.sdq.workflow.IJob;
import de.uka.ipd.sdq.workflow.OrderPreservingBlackboardCompositeJob;
import de.uka.ipd.sdq.workflow.ParallelBlackboardInteractingCompositeJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * A workflow delegate defining the workflow for 
 * the refinement of the variation point model.
 */
public class VPMAnalysisWorkflowDelegate
		extends
		AbstractWorkbenchDelegate<VPMAnalysisWorkflowConfiguration, UIBasedWorkflow<Blackboard<?>>> {

	/** The configuration of the workflow. */
	private VPMAnalysisWorkflowConfiguration config = null;

	/**
	 * Constructor requiring a diffing workflow configuration.
	 * 
	 * @param config
	 *            The configuration of the workflow.
	 */
	public VPMAnalysisWorkflowDelegate(VPMAnalysisWorkflowConfiguration config) {
		this.config = config;
	}

	/**
	 * Create the workflow
	 */
	@Override
	protected IJob createWorkflowJob(VPMAnalysisWorkflowConfiguration config) {
		
		// initialize the basic elements
		SPLevoProject splevoProject = config.getSplevoProject();
		OrderPreservingBlackboardCompositeJob<SPLevoBlackBoard> compositeJob =
				new OrderPreservingBlackboardCompositeJob<SPLevoBlackBoard>();
		compositeJob.setBlackboard(new SPLevoBlackBoard());
		
		// load the latest vpm model
		LoadVPMJob loadVPMJob = new LoadVPMJob(splevoProject);
		compositeJob.add(loadVPMJob);

		// trigger the configured refinement analysis
		ParallelBlackboardInteractingCompositeJob<SPLevoBlackBoard> parallelAnalysisJob = new ParallelBlackboardInteractingCompositeJob<SPLevoBlackBoard>();
		if(config.getAnalyses().size() < 1){
			// TODO: trigger error logging
			return null;
		}
		for(VPMAnalysisConfiguration analysisConfig : config.getAnalyses()){
			VPMAnalysisJob vpmAnalysisJob = new VPMAnalysisJob(analysisConfig);
			parallelAnalysisJob.add(vpmAnalysisJob);
		}
		compositeJob.add(parallelAnalysisJob);
		
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
		IJob updateUiJob = new UpdateUIJob(config.getSplevoProjectEditor(),"VPM analyzed and refined");
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
	protected VPMAnalysisWorkflowConfiguration getConfiguration() {
		return this.config;
	}
}
