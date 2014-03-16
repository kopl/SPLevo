package org.splevo.ui.workflow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.jobs.CopyVPMJob;
import org.splevo.ui.jobs.LoadVPMJob;
import org.splevo.ui.jobs.RefactorJob;
import org.splevo.ui.jobs.SPLevoBlackBoard;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * Builds the Software Product Line based on the latest variation point model and the source
 * projects.
 */
public class BuildSPLWorkflowDelegate extends
        AbstractWorkbenchDelegate<BuildSPLWorkflowConfiguration, UIBasedWorkflow<Blackboard<?>>> {

    private BuildSPLWorkflowConfiguration workflowConfiguration;

    /**
     * Initializes this workflow with a given {@link BasicSPLevoWorkflowConfiguration}.
     * 
     * @param workflowConfiguration
     *            The {@link BasicSPLevoWorkflowConfiguration} for this workflow.
     */
    public BuildSPLWorkflowDelegate(BuildSPLWorkflowConfiguration workflowConfiguration) {
        this.workflowConfiguration = workflowConfiguration;
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        // nothing to do here
    }

    @Override
    protected IJob createWorkflowJob(BuildSPLWorkflowConfiguration config) {
        // create job and blackboard
        SequentialBlackboardInteractingJob<SPLevoBlackBoard> jobSequence = 
                new SequentialBlackboardInteractingJob<SPLevoBlackBoard>();
        jobSequence.setBlackboard(new SPLevoBlackBoard());

        // get splevo project
        SPLevoProject splevoProject = workflowConfiguration.getSplevoProjectEditor().getSplevoProject();

        // initialize jobs
        LoadVPMJob loadVPMJob = new LoadVPMJob(splevoProject);
        CopyVPMJob copyVPMJob = new CopyVPMJob(splevoProject, config.getOutputPath());
        RefactorJob refactorJob = new RefactorJob(splevoProject);

        // add jobs to workflow
        jobSequence.add(loadVPMJob);
        jobSequence.add(copyVPMJob);
        jobSequence.add(refactorJob);

        return jobSequence;
    }

    @Override
    protected BuildSPLWorkflowConfiguration getConfiguration() {
        return workflowConfiguration;
    }

    @Override
    protected boolean useSeparateConsoleForEachJobRun() {
        return false;
    }
}
