package org.splevo.ui.workflow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.jobs.ExtractionJob;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.jobs.UpdateUIJob;

import de.uka.ipd.sdq.workflow.Blackboard;
import de.uka.ipd.sdq.workflow.IJob;
import de.uka.ipd.sdq.workflow.OrderPreservingCompositeJob;
import de.uka.ipd.sdq.workflow.ParallelBlackboardInteractingCompositeJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * Delegate defining a workflow for the initial extraction of the software models. This includes an
 * update of the user interface to set the paths to the extracted models.
 * 
 */
public class ModelExtractionWorkflowDelegate extends
        AbstractWorkbenchDelegate<BasicSPLevoWorkflowConfiguration, UIBasedWorkflow<Blackboard<?>>> {

    /** The configuration of the workflow. */
    private BasicSPLevoWorkflowConfiguration config = null;

    /**
     * Constructor requiring a a workflow configuration.
     * 
     * @param config
     *            The configuration of the workflow.
     */
    public ModelExtractionWorkflowDelegate(BasicSPLevoWorkflowConfiguration config) {
        this.config = config;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IJob createWorkflowJob(BasicSPLevoWorkflowConfiguration config) {

        OrderPreservingCompositeJob compositeJob = new OrderPreservingCompositeJob();

        // create the parallel extraction
        SPLevoProject splevoProject = config.getSplevoProjectEditor().getSplevoProject();
        ExtractionJob leadingExtractionJob = new ExtractionJob(splevoProject, true);
        ExtractionJob integrationExtractionJob = new ExtractionJob(splevoProject, false);

        ParallelBlackboardInteractingCompositeJob<SPLevoBlackBoard> compositeExtractionJob = new ParallelBlackboardInteractingCompositeJob<SPLevoBlackBoard>();
        compositeExtractionJob.add(leadingExtractionJob);
        compositeExtractionJob.add(integrationExtractionJob);
        compositeJob.add(compositeExtractionJob);
        
        // create the ui update job
        IJob updateUiJob = new UpdateUIJob(config.getSplevoProjectEditor(), "Source Models extracted");
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
