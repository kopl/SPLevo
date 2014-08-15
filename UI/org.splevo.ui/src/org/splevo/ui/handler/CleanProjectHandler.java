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
 *    Benjamin Klatt
 *    Anton Huck
 *******************************************************************************/
package org.splevo.ui.handler;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.splevo.project.SPLevoProject;

/**
 * This command cleans a {@link SPLevoProject}.
 */
public class CleanProjectHandler extends AbstractSPLevoHandler {

	/** The logger for this class. */
	private Logger logger = Logger.getLogger(CleanProjectHandler.class);

	/**
	 * Open a dialog to verify clean up with user and clean project resources
	 * accordingly.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		splevoProjectEditor = getActiveSPLevoEditor(event);
		
		activeShell = HandlerUtil.getActiveShell(event);
		
		boolean proceed = MessageDialog
				.openConfirm(
						activeShell,
						"Clean Project",
						"Deletes the \"models\" and \"logs\" directories.\n"
								+ "The Project Selection, Project Infos, Extractor Selection\n"
								+ "and Diffing Package Filter Rules wont be removed.\n\n"
								+ "Proceed anyway?");

		if (!proceed) {
			return null;
		}

		// splevo project
		SPLevoProject project = splevoProjectEditor.getSplevoProject();

		// Delete project files and metadata
		cleanProjetcFiles(project);
		cleanProjectMetadata(project);

		// Update UI
		splevoProjectEditor.updateUI("Project cleaned.");

		return null;
	}

	/**
	 * Deletes the /models and the /logs folders of the specified
	 * {@link SPLevoProject}.
	 * 
	 * @param project
	 *            The {@link SPLevoProject}.
	 */
	private void cleanProjetcFiles(SPLevoProject project) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IPath workSpacePath = root.getFullPath().append(project.getWorkspace());
		IPath modelsPath = workSpacePath.append("/models");
		IPath logsPath = workSpacePath.append("/logs");
		IResource modelsResource = root.findMember(modelsPath);
		IResource logsResource = root.findMember(logsPath);

		try {
			if (modelsResource != null) {
				modelsResource.delete(true, null);
			}
		} catch (CoreException e) {
			logger.error("Cannot delete models folder.", e);
		}

		try {
			if (logsResource != null) {
				logsResource.delete(true, null);
			}
		} catch (CoreException e) {
			logger.error("Cannot delete logs folder.", e);
		}
	}

	/**
	 * Deletes the model paths from a {@link SPLevoProject}.
	 * 
	 * @param project
	 *            The {@link SPLevoProject}.
	 */
	private void cleanProjectMetadata(SPLevoProject project) {
		project.setDiffingModelPath(null);
		project.setSourceModelPathIntegration(null);
		project.setSourceModelPathLeading(null);
		project.getVpmModelPaths().clear();
	}
}
