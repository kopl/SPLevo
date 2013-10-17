package org.splevo.ui.workflow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.ui.jobs.DiffingJob;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.jobs.UpdateUIJob;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
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
    	SequentialBlackboardInteractingJob<SPLevoBlackBoard> jobSequence = new SequentialBlackboardInteractingJob<SPLevoBlackBoard>();
        jobSequence.setBlackboard(new SPLevoBlackBoard());
        
        // init the parallel extraction
        final DiffingJob diffingJob = new DiffingJob(config.getSplevoProjectEditor().getSplevoProject());
        jobSequence.add(diffingJob);

        // init the ui update job
        final IJob updateUiJob = new UpdateUIJob(config.getSplevoProjectEditor(), "Diffing performed");
        jobSequence.add(updateUiJob);

        // return the prepared workflow
        return jobSequence;
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
