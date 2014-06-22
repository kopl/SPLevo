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
package org.splevo.ui.jobs;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import de.uka.ipd.sdq.workflow.jobs.AbstractJob;

/**
 * Job to refresh the active eclipse workspace.
 *
 * @author Benjamin Klatt
 *
 */
public class RefreshWorkspaceJob extends AbstractJob {

    @Override
    public String getName() {
        return "Refresh Workspace Job";
    }

    @Override
    public void execute(IProgressMonitor monitor) {
        monitor.beginTask(getName(), 100);

        // refresh workspace
        try {
            ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, monitor);
        } catch (CoreException e) {
            e.printStackTrace();
        }

    }

	@Override
	public void cleanup(IProgressMonitor monitor) {
	}
}
