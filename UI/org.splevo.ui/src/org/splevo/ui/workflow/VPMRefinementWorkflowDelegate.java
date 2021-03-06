/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.ui.workflow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.jobs.OpenVPMJob;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.jobs.SaveVPMJob;
import org.splevo.ui.jobs.SetRefinementsJob;
import org.splevo.ui.jobs.SetVPMJob;
import org.splevo.ui.jobs.VPMApplyRefinementsJob;

import com.google.common.collect.Iterables;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * A delegate defining the workflow for the refinement of the variation point model.
 */
public class VPMRefinementWorkflowDelegate extends
        AbstractWorkbenchDelegate<VPMRefinementWorkflowConfiguration, UIBasedWorkflow<Blackboard<?>>> {

    /** The configuration of the workflow. */
    private VPMRefinementWorkflowConfiguration config = null;

    /**
     * Constructor requiring a basic splevo workflow configuration.
     *
     * @param config
     *            The configuration of the workflow.
     */
    public VPMRefinementWorkflowDelegate(VPMRefinementWorkflowConfiguration config) {
        this.config = config;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IJob createWorkflowJob(VPMRefinementWorkflowConfiguration config) {

        // initialize the basic elements
        SPLevoProject splevoProject = config.getSplevoProjectEditor().getSplevoProject();
        SequentialBlackboardInteractingJob<SPLevoBlackBoard> jobSequence = new SequentialBlackboardInteractingJob<SPLevoBlackBoard>();
        jobSequence.setBlackboard(new SPLevoBlackBoard());

        SetVPMJob setVpmJob = new SetVPMJob(config.getVariationPointModel());
        jobSequence.add(setVpmJob);

        // set the refinements to perform and variation point model in the blackboard
        SetRefinementsJob setRefinementsJob = new SetRefinementsJob(config.getRefinements());
        jobSequence.add(setRefinementsJob);

        // perform the refinements automatically
        VPMApplyRefinementsJob vpmApplyRefinementsJob = new VPMApplyRefinementsJob();
        jobSequence.add(vpmApplyRefinementsJob);

        // save the latest vpm model
        String modelNamePrefix = "" + splevoProject.getVpmModelReferences().size();
        String targetPath = splevoProject.getWorkspace() + "models/vpms/" + modelNamePrefix + "-vpm.vpm";
        boolean refactoringStarted = Iterables.getLast(splevoProject.getVpmModelReferences()).isRefactoringStarted();
        SaveVPMJob saveVPMJob = new SaveVPMJob(splevoProject, targetPath, refactoringStarted);
        jobSequence.add(saveVPMJob);

        // open the model
        jobSequence.add(new OpenVPMJob(splevoProject, null));

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
    protected VPMRefinementWorkflowConfiguration getConfiguration() {
        return this.config;
    }
}
