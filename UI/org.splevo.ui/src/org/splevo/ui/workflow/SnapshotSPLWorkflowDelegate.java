package org.splevo.ui.workflow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.jobs.SaveAndReloadVPMJob;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * A delegate defining the workflow for the saving of the variation point model.
 */
public class SnapshotSPLWorkflowDelegate extends
        AbstractWorkbenchDelegate<BuildSPLWorkflowConfiguration, UIBasedWorkflow<Blackboard<?>>> {

    private BuildSPLWorkflowConfiguration config;
    private SPLevoBlackBoard blackboard;
    private String snapName;

    /**
     * Constructor requiring a difference analysis workflow configuration.
     * 
     * @param config The configuration of the workflow.
     * @param blackboard The blackboard to communicate through.
     * @param snapName The name of the vpm model
     * @throws JobFailedException if exists is true
     */
    public SnapshotSPLWorkflowDelegate(BuildSPLWorkflowConfiguration config, SPLevoBlackBoard blackboard, 
            String snapName) throws JobFailedException {
        this.config = config;
        this.blackboard = blackboard;
        this.snapName = snapName;
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        // nothing to do here
    }

    @Override
    protected IJob createWorkflowJob(BuildSPLWorkflowConfiguration arg0) {       
        
        final SPLevoProjectEditor splevoProjectEditor = config.getSplevoProjectEditor();
        final SPLevoProject splevoProject = splevoProjectEditor.getSplevoProject();

        SequentialBlackboardInteractingJob<SPLevoBlackBoard> jobSequence = 
                new SequentialBlackboardInteractingJob<SPLevoBlackBoard>();
        jobSequence.setBlackboard(blackboard);

        // save and reload vpm model
        jobSequence.add(new SaveAndReloadVPMJob(splevoProject, snapName, false, false));

        return jobSequence;
    }

    @Override
    protected BuildSPLWorkflowConfiguration getConfiguration() {
        return config;
    }

    @Override
    protected boolean useSeparateConsoleForEachJobRun() {
        return false;
    }
}