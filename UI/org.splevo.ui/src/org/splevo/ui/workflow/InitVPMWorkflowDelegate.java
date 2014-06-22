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
import org.splevo.ui.jobs.InitVPMJob;
import org.splevo.ui.jobs.LoadDiffingModelJob;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.jobs.SaveVPMJob;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * Delegate defining a workflow to initialize the variation point model.
 */
public class InitVPMWorkflowDelegate
		extends
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

		SPLevoProject splevoProject = config.getSplevoProjectEditor()
				.getSplevoProject();

		// load the diff model
		LoadDiffingModelJob loadDiffJob = new LoadDiffingModelJob(splevoProject);
		jobSequence.add(loadDiffJob);

		// init the vpm
		InitVPMJob initVPMJob = new InitVPMJob(splevoProject);
		jobSequence.add(initVPMJob);

		// save the model
		String targetPath = splevoProject.getWorkspace()
				+ "models/vpms/initial-vpm.vpm";
		SaveVPMJob saveVPMJob = new SaveVPMJob(splevoProject, targetPath);
		jobSequence.add(saveVPMJob);

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
	protected BasicSPLevoWorkflowConfiguration getConfiguration() {
		return this.config;
	}
}
