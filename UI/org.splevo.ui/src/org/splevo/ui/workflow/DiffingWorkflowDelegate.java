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
 * Delegate defining a workflow for the initial diffing of the software models. This includes an
 * update of the user interface to set the paths to the diffing models.
 */
public class DiffingWorkflowDelegate extends
        AbstractWorkbenchDelegate<DiffingWorkflowConfiguration, UIBasedWorkflow<Blackboard<?>>> {

    /** The configuration of the work flow. */
    private DiffingWorkflowConfiguration config = null;

    /**
     * Constructor requiring a diffing work flow configuration.
     * 
     * @param config
     *            The configuration of the work flow.
     */
    public DiffingWorkflowDelegate(final DiffingWorkflowConfiguration config) {
        this.config = config;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IJob createWorkflowJob(final DiffingWorkflowConfiguration config) {

        final OrderPreservingCompositeJob compositeJob = new OrderPreservingCompositeJob();

        // init the parallel extraction
        final DiffingJob diffingJob = new DiffingJob(config.getSplevoProjectEditor().getSplevoProject());
        compositeJob.add(diffingJob);

        // init the ui update job
        final IJob updateUiJob = new UpdateUIJob(config.getSplevoProjectEditor(), "Diffing performed");
        compositeJob.add(updateUiJob);

        // return the prepared workflow
        return compositeJob;
    }

    @Override
    public void selectionChanged(final IAction action, final ISelection selection) {
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
