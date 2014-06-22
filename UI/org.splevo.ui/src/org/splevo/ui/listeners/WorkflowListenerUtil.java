/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    CONTRIBUTR_FIRST_AND_LAST_NAME - WORK_DONE (e.g. "initial API and implementation and/or initial documentation")
 *******************************************************************************/
package org.splevo.ui.listeners;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Display;
import org.splevo.ui.editors.SPLevoProjectEditor;

import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * Utility class for workflow listeners.
 */
public final class WorkflowListenerUtil {

	/**
	 * Disable default constructor for utility class.
	 */
	private WorkflowListenerUtil() {
	}

	/**
	 * Run a workflow as an asynchronous job and update the SPLevo project
	 * editor ui afterwards.
	 *
	 * @param workflowDelegate
	 *            The delegate of the workflow to run.
	 * @param workflowTitle
	 *            The title of the workflow to show.
	 * @param splevoProjectEditor
	 *            The editor to update.
	 */
	public static void runWorkflowAndUpdateUI(
			final AbstractWorkbenchDelegate<?, ?> workflowDelegate,
			final String workflowTitle,
			final SPLevoProjectEditor splevoProjectEditor) {

		Runnable uiProcess = new Runnable() {
			@Override
			public void run() {
				splevoProjectEditor.updateUI(workflowTitle + " completed");
			}
		};

		WorkflowListenerUtil.runWorkflowAndRunUITask(workflowDelegate,
				workflowTitle, uiProcess);
	}

	/**
	 * Run a workflow as an asynchronous job and trigger a post-process with ui
	 * access afterwards.
	 *
	 * @param workflowDelegate
	 *            The delegate of the workflow to run.
	 * @param workflowTitle
	 *            The title of the workflow to show.
	 * @param uiRunnable
	 *            The post-workflow process to trigger and granted with ui
	 *            access. Null means no process is triggered.
	 */
    public static void runWorkflowAndRunUITask(
			final AbstractWorkbenchDelegate<?, ?> workflowDelegate,
			final String workflowTitle, final Runnable uiRunnable) {

		Job job = new Job(workflowTitle) {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask(workflowTitle, IProgressMonitor.UNKNOWN);
				IAction action = new Action(workflowTitle) {
				};
				workflowDelegate.setProgressMonitor(monitor);
				workflowDelegate.run(action);
				if (uiRunnable != null) {
					Display.getDefault().asyncExec(uiRunnable);
				}

				monitor.done();
				// use this to open a Shell in the UI thread
				return Status.OK_STATUS;
			}

		};
		job.setUser(true);
		job.schedule();
	}
}
