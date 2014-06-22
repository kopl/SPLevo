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

import org.splevo.ui.editors.SPLevoProjectEditor;

import de.uka.ipd.sdq.workflow.workbench.AbstractWorkflowStatusListener;

/**
 * A listener to update the ui of a {@link SPLevoProjectEditor} when a workflow
 * is finished.
 */
public class UpdateUIWorkflowStatusListener extends
		AbstractWorkflowStatusListener {

	private SPLevoProjectEditor splevoProjectEditor;

	private String message = null;

	/** Flag identifying that finished is notified. */
	private boolean finished = false;

	/**
	 * Constructor to set the SPLevo editor to update.
	 *
	 * @param splevoProjectEditor
	 *            The editor to update.
	 */
	public UpdateUIWorkflowStatusListener(
			SPLevoProjectEditor splevoProjectEditor) {
		this(splevoProjectEditor, null);
	}

	/**
	 * Constructor to set the SPLevo editor to update.
	 *
	 * @param splevoProjectEditor
	 *            The editor to update.
	 * @param message
	 *            The message to display. Null to display none.
	 */
	public UpdateUIWorkflowStatusListener(
			SPLevoProjectEditor splevoProjectEditor, String message) {
		this.splevoProjectEditor = splevoProjectEditor;
		this.message = message;
	}

	@Override
	public void notifyFinished() {
		if (message != null) {
			splevoProjectEditor.updateUI(message);
		} else {
			splevoProjectEditor.updateUI();
		}
		finished = true;
	}

	/**
	 * Check if the listener has been notified about the workflows finishing.
	 *
	 * @return true/false if the workflow is finished or not.
	 */
	public boolean isFinished() {
		return finished;
	}

}