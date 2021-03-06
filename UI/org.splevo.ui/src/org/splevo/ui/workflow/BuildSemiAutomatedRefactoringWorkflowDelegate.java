package org.splevo.ui.workflow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.jobs.LoadVPMJob;
import org.splevo.ui.jobs.OpenTaskViewJob;
import org.splevo.ui.jobs.OpenVPMJob;
import org.splevo.ui.jobs.RefreshLeadingCopyProjects;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.jobs.SemiAutomatedRefactorVPMJob;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * Delegate that builds the workflow for a semi-automated refactoring.
 */
public class BuildSemiAutomatedRefactoringWorkflowDelegate extends
        AbstractWorkbenchDelegate<BasicSPLevoProjectWorkflowConfiguration, UIBasedWorkflow<Blackboard<?>>> {

    private BasicSPLevoProjectWorkflowConfiguration config = new BasicSPLevoProjectWorkflowConfiguration();
    private final SPLevoBlackBoard blackboard;
    private final String variationPointId;

    /**
     * Constructs the delegate.
     * 
     * @param blackboard
     *            The blackboard to derive and store information.
     * @param variationPointId
     *            The ID of the variation point to be refactored.
     * @param project
     *            The SPLevoProject to which the variation point belongs to.
     */
    public BuildSemiAutomatedRefactoringWorkflowDelegate(SPLevoBlackBoard blackboard, String variationPointId,
            SPLevoProject project) {
        config.setSplevoProject(project);
        this.blackboard = blackboard;
        this.variationPointId = variationPointId;
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        // nothing to do here
    }

    @Override
    protected IJob createWorkflowJob(BasicSPLevoProjectWorkflowConfiguration config) {
        final SPLevoProject splevoProject = config.getSplevoProject();

        SequentialBlackboardInteractingJob<SPLevoBlackBoard> jobSequence = new SequentialBlackboardInteractingJob<SPLevoBlackBoard>();
        jobSequence.setBlackboard(blackboard);

        // load the latest vpm model in the blackboard
        LoadVPMJob loadVPMJob = new LoadVPMJob(splevoProject);
        jobSequence.add(loadVPMJob);

        // execute refactorings
        SemiAutomatedRefactorVPMJob refactorVPMJob = new SemiAutomatedRefactorVPMJob(splevoProject, variationPointId);
        jobSequence.add(refactorVPMJob);

        // refresh the leading copy projects in Eclipse
        jobSequence.add(new RefreshLeadingCopyProjects(splevoProject));

        // load the latest vpm model in the blackboard
        LoadVPMJob loadVPMJobAfterRefactoring = new LoadVPMJob(splevoProject);
        jobSequence.add(loadVPMJobAfterRefactoring);

        // reload latest vpm model in UI
        jobSequence.add(new OpenVPMJob(splevoProject, null));

        // refresh task view list
        jobSequence.add(new OpenTaskViewJob(splevoProject));

        return jobSequence;
    }

    @Override
    protected boolean useSeparateConsoleForEachJobRun() {
        return false;
    }

    @Override
    protected BasicSPLevoProjectWorkflowConfiguration getConfiguration() {
        return config;
    }

}
