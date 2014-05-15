/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Busch
 *******************************************************************************/

package org.splevo.ui.listeners;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.util.WorkspaceUtil;

/**
 * Mouse adapter to open the diff model registered in a SPLevo project.
 */
public class OpenDiffModelListener extends MouseAdapter {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(OpenDiffModelListener.class);

    /** The internal reference to the splevo project editor to work with. */
    private SPLevoProjectEditor splevoProjectEditor = null;

    /**
     * Constructor to register the editor to open the diff model of.
     *
     * @param splevoProjectEditor
     *            The reference to the splevo project editor.
     */
    public OpenDiffModelListener(SPLevoProjectEditor splevoProjectEditor) {
        this.splevoProjectEditor = splevoProjectEditor;
    }

    @Override
    public void mouseUp(MouseEvent e) {
        String diffingModelPath = splevoProjectEditor.getSplevoProject().getDiffingModelPath();
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
    }
}