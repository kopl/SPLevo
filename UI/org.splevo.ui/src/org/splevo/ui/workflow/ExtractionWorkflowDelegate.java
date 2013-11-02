package org.splevo.ui.workflow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.jobs.ExtractionJob;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.jobs.UpdateUIJob;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.ParallelJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * Delegate defining a workflow for the initial extraction of the software models. This includes an
 * update of the user interface to set the paths to the extracted models.
 * 
 */
public class ExtractionWorkflowDelegate extends
        AbstractWorkbenchDelegate<ExtractionWorkflowConfiguration, UIBasedWorkflow<Blackboard<?>>> {

    /** The configuration of the workflow. */
    private ExtractionWorkflowConfiguration config = null;

    /**
     * Constructor requiring a a workflow configuration.
     * 
     * @param config
     *            The configuration of the workflow.
     */
    public ExtractionWorkflowDelegate(ExtractionWorkflowConfiguration config) {
        this.config = config;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IJob createWorkflowJob(ExtractionWorkflowConfiguration config) {

        SequentialBlackboardInteractingJob<SPLevoBlackBoard> jobSequence = new SequentialBlackboardInteractingJob<SPLevoBlackBoard>();
        jobSequence.setBlackboard(new SPLevoBlackBoard());

        // create the parallel extraction
        SPLevoProject splevoProject = config.getSplevoProjectEditor().getSplevoProject();

        ParallelJob parallelJob = new ParallelJob();

        for (String extractorId : splevoProject.getExtractorIds()) {
            ExtractionJob leadingExtractionJob = new ExtractionJob(extractorId, splevoProject, true);
            ExtractionJob integrationExtractionJob = new ExtractionJob(extractorId, splevoProject, false);
            parallelJob.add(leadingExtractionJob);
            parallelJob.add(integrationExtractionJob);
        }

        jobSequence.add(parallelJob);

        // create the ui update job
        IJob updateUiJob = new UpdateUIJob(config.getSplevoProjectEditor(), "Source Models extracted");
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
    protected ExtractionWorkflowConfiguration getConfiguration() {
        return this.config;
    }
}
