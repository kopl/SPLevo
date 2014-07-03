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
 *******************************************************************************/
package org.splevo.ui.handler;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.ide.IDE;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.util.WorkspaceUtil;

/**
 * This command opens the difference model of a {@link SPLevoProject}.
 */
public class OpenDiffModelHandler extends AbstractHandler {

	/** The logger for this class. */
	private Logger logger = Logger.getLogger(OpenDiffModelHandler.class);

	/**
	 * Open a dialog to verify clean up with user and
	 * clean project resources accordingly.
	 *
	 * {@inheritDoc}
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);
		if (!(activeEditor instanceof SPLevoProjectEditor)) {
			return null;
		}

		// Get editor and splevo project
		SPLevoProjectEditor splevoProjectEditor = (SPLevoProjectEditor) activeEditor;
		SPLevoProject project = splevoProjectEditor.getSplevoProject();

        String diffingModelPath = project.getDiffingModelPath();
        if (diffingModelPath != null && diffingModelPath.length() > 0) {
            String basePath = WorkspaceUtil.getAbsoluteWorkspacePath();
            File fileToOpen = new File(basePath + File.separator + diffingModelPath);
            IFileStore fileStore = EFS.getLocalFileSystem().getStore(fileToOpen.toURI());
            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

            try {
                IDE.openEditorOnFileStore(page, fileStore);
            } catch (PartInitException pie) {
                logger.error("failed to open diff file.");
            }
        }

		return null;
	}
}
