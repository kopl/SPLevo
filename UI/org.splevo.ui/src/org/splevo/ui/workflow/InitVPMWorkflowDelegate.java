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

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.diffing.Differ;
import org.splevo.diffing.DifferRegistry;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.jobs.DiffingJob;
import org.splevo.ui.jobs.ExtractionJob;
import org.splevo.ui.jobs.InitVPMJob;
import org.splevo.ui.jobs.OpenVPMJob;
import org.splevo.ui.jobs.RefreshWorkspaceJob;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.jobs.SaveVPMJob;

import com.google.common.collect.Lists;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.ParallelBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
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

        SequentialBlackboardInteractingJob<SPLevoBlackBoard> jobSequence = new SequentialBlackboardInteractingJob<SPLevoBlackBoard>();
        jobSequence.setBlackboard(new SPLevoBlackBoard());

        SPLevoProject splevoProject = config.getSplevoProjectEditor().getSplevoProject();

        // diffing

        List<String> requiredExtractors = getRequiredExtractors(splevoProject);

        // create the parallel extraction
        ParallelBlackboardInteractingJob<SPLevoBlackBoard> parallelJob = new ParallelBlackboardInteractingJob<SPLevoBlackBoard>();
        for (String extractorId : requiredExtractors) {
            ExtractionJob leadingExtractionJob = new ExtractionJob(extractorId, splevoProject, true);
            ExtractionJob integrationExtractionJob = new ExtractionJob(extractorId, splevoProject, false);
            parallelJob.add(leadingExtractionJob);
            parallelJob.add(integrationExtractionJob);
        }
        jobSequence.add(parallelJob);

        // refresh the workspace to ensure if an extractor has changed the workspace
        // the eclipse environment does not struggle because of an workspace not in sync
        jobSequence.add(new RefreshWorkspaceJob());

        // difference analysis
        final DiffingJob diffingJob = new DiffingJob(splevoProject);
        jobSequence.add(diffingJob);

        // init the vpm
        InitVPMJob initVPMJob = new InitVPMJob(splevoProject);
        jobSequence.add(initVPMJob);

        // save the model
        String targetPath = splevoProject.getWorkspace() + "models/vpms/initial-vpm.vpm";
        SaveVPMJob saveVPMJob = new SaveVPMJob(splevoProject, targetPath);
        jobSequence.add(saveVPMJob);

        // open the model
        // FIXME: Use the model from blackboard if JaMoPP location info can be loaded on demand
        // Some services, such as the "Open Source Location" action in the VPExplorer
        // need the detailed location information.
        // jobSequence.add(new OpenVPMJob());
        jobSequence.add(new OpenVPMJob(splevoProject, targetPath));

        // return the prepared workflow
        return jobSequence;
    }

    private List<String> getRequiredExtractors(SPLevoProject splevoProject) {
        List<String> requiredExtractors = Lists.newArrayList();
        EList<String> differIds = splevoProject.getDifferIds();
        for (String differId : differIds) {
            Differ differ = DifferRegistry.getInstance().getElementById(differId);
            if (differ != null) {
                requiredExtractors.addAll(differ.getRequiredExtractorIds());
            }
        }
        return requiredExtractors;
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
