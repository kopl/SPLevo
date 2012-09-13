package org.splevo.ui.workflow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.ui.jobs.ExtractionJob;
import org.splevo.ui.jobs.UpdateUIJob;

import de.uka.ipd.sdq.workflow.Blackboard;
import de.uka.ipd.sdq.workflow.IJob;
import de.uka.ipd.sdq.workflow.OrderPreservingCompositeJob;
import de.uka.ipd.sdq.workflow.ParallelCompositeJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * Delegate defining a workflow for the initial extraction of the software models.
 * This includes an update of the user interface to set the paths to the extracted models.
 *
 */
public class ModelExtractionWorkflowDelegate
		extends
		AbstractWorkbenchDelegate<ModelExtractionWorkflowConfiguration, UIBasedWorkflow<Blackboard<?>>> {

	/** The configuration of the workflow. */
	private ModelExtractionWorkflowConfiguration config = null;

	/**
	 * Constructor requiring a a workflow configuration.
	 * 
	 * @param config
	 *            The configuration of the workflow.
	 */
	public ModelExtractionWorkflowDelegate(ModelExtractionWorkflowConfiguration config) {
		this.config = config;
	}

	/**
	 * Create the workflow
	 */
	@Override
	protected IJob createWorkflowJob(ModelExtractionWorkflowConfiguration config) {
		
		OrderPreservingCompositeJob compositeJob = new OrderPreservingCompositeJob();

		// init the parallel extraction
		ExtractionJob leadingExtractionJob = new ExtractionJob(config.getSplevoProject(), true);
		ExtractionJob integrationExtractionJob = new ExtractionJob(config.getSplevoProject(), false);
		ParallelCompositeJob parallelExtractionJob = new ParallelCompositeJob();
		parallelExtractionJob.add(leadingExtractionJob);
		parallelExtractionJob.add(integrationExtractionJob);
		compositeJob.add(parallelExtractionJob);
		
		// init the ui update job
		IJob updateUiJob = new UpdateUIJob(config.getSplevoProjectEditor());
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
	protected ModelExtractionWorkflowConfiguration getConfiguration() {
		return this.config;
	}
}
