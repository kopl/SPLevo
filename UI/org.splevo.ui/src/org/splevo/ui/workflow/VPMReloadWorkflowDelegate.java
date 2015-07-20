package org.splevo.ui.workflow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.jobs.LoadVPMJob;
import org.splevo.ui.jobs.OpenVPMJob;
import org.splevo.ui.jobs.SPLevoBlackBoard;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * A delegate defining the workflow for reloading of the latest variation point model.
 */
public class VPMReloadWorkflowDelegate extends
        AbstractWorkbenchDelegate<BasicSPLevoWorkflowConfiguration, UIBasedWorkflow<Blackboard<?>>> {

    private final BasicSPLevoWorkflowConfiguration config = new BasicSPLevoWorkflowConfiguration();
    private final boolean loadLayoutInformation;

    /**
     * Constructs a new delegate.
     * 
     * @param splevoProjectEditor
     *            The SPLevoProjectEditor to be used to determine the current VPM.
     * @param loadLayoutInformation
     *            Indicates if layout information shall be loaded.
     */
    public VPMReloadWorkflowDelegate(SPLevoProjectEditor splevoProjectEditor, boolean loadLayoutInformation) {
        config.setSplevoProjectEditor(splevoProjectEditor);
        this.loadLayoutInformation = loadLayoutInformation;
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
    protected IJob createWorkflowJob(BasicSPLevoWorkflowConfiguration arg0) {

        // initialize the basic elements
        SPLevoProject splevoProject = config.getSplevoProjectEditor().getSplevoProject();
        SequentialBlackboardInteractingJob<SPLevoBlackBoard> jobSequence =
                new SequentialBlackboardInteractingJob<SPLevoBlackBoard>();

        jobSequence.setBlackboard(new SPLevoBlackBoard());

        // load the latest vpm model
        jobSequence.add(new LoadVPMJob(splevoProject, loadLayoutInformation));

        // reload latest vpm model in UI
        jobSequence.add(new OpenVPMJob(splevoProject, null));

        return jobSequence;
    }

    @Override
    protected BasicSPLevoWorkflowConfiguration getConfiguration() {
        return config;
    }

}
