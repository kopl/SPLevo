package org.splevo.ui.workflow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.jobs.InitVPMJob;
import org.splevo.ui.jobs.LoadDiffingModelJob;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.jobs.SaveVPMJob;
import org.splevo.ui.jobs.UpdateUIJob;

import de.uka.ipd.sdq.workflow.Blackboard;
import de.uka.ipd.sdq.workflow.IJob;
import de.uka.ipd.sdq.workflow.OrderPreservingBlackboardCompositeJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * Delegate defining a workflow to initialize the variation point model.
 */
public class InitVPMWorkflowDelegate extends
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
     * {@inheritDoc}
     */
    @Override
    protected IJob createWorkflowJob(BasicSPLevoWorkflowConfiguration config) {

        OrderPreservingBlackboardCompositeJob<SPLevoBlackBoard> compositeJob = new OrderPreservingBlackboardCompositeJob<SPLevoBlackBoard>();
        compositeJob.setBlackboard(new SPLevoBlackBoard());

        SPLevoProject splevoProject = config.getSplevoProjectEditor().getSplevoProject();

        // load the diff model
        LoadDiffingModelJob loadDiffJob = new LoadDiffingModelJob(splevoProject);
        compositeJob.add(loadDiffJob);

        // init the vpm
        InitVPMJob initVPMJob = new InitVPMJob(splevoProject);
        compositeJob.add(initVPMJob);

        // save the model
        String targetPath = splevoProject.getWorkspace() + "models/vpms/initial-vpm.vpm";
        SaveVPMJob saveVPMJob = new SaveVPMJob(splevoProject, targetPath);
        compositeJob.add(saveVPMJob);

        // init the ui update job
        IJob updateUiJob = new UpdateUIJob(config.getSplevoProjectEditor(), "Initial VPM created");
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
