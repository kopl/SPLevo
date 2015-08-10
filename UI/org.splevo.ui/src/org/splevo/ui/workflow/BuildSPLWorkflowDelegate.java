/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic
 *******************************************************************************/
package org.splevo.ui.workflow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.jobs.LoadVPMJob;
import org.splevo.ui.jobs.OpenTaskViewJob;
import org.splevo.ui.jobs.RefactorVPMJob;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.jobs.SaveAndReloadVPMJob;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * A delegate defining the workflow for the refactoring of the variation point model.
 */
public class BuildSPLWorkflowDelegate extends
        AbstractWorkbenchDelegate<BuildSPLWorkflowConfiguration, UIBasedWorkflow<Blackboard<?>>> {

    // private Logger logger = Logger.getLogger(BuildSPLWorkflowDelegate.class);
    private BuildSPLWorkflowConfiguration config;
    private SPLevoBlackBoard blackboard;

    /**
     * Constructor requiring a difference analysis workflow configuration.
     * 
     * @param config
     *            The configuration of the workflow.
     * @param blackboard
     *            The blackboard to communicate through.
     */
    public BuildSPLWorkflowDelegate(BuildSPLWorkflowConfiguration config, SPLevoBlackBoard blackboard) {
        this.config = config;
        this.blackboard = blackboard;
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        // nothing to do here
    }

    @Override
    protected IJob createWorkflowJob(BuildSPLWorkflowConfiguration arg0) {
        SPLevoProject splevoProject = config.getSplevoProjectEditor().getSplevoProject();

        SequentialBlackboardInteractingJob<SPLevoBlackBoard> jobSequence =
                new SequentialBlackboardInteractingJob<SPLevoBlackBoard>();
        jobSequence.setBlackboard(blackboard);
        
        // save and reload vpm model
        jobSequence.add(new SaveAndReloadVPMJob(splevoProject, "refactored"));

        // load the latest vpm model
        LoadVPMJob loadVPMJob = new LoadVPMJob(splevoProject);
        jobSequence.add(loadVPMJob);

        // execute refactorings
        RefactorVPMJob refactorVPMJob = new RefactorVPMJob(splevoProject);
        jobSequence.add(refactorVPMJob);
        
        // open task view
        jobSequence.add(new OpenTaskViewJob());
        
        //open cheat sheet job by the list of semit-automated refactorings (from the task view)? or by context menu?

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
