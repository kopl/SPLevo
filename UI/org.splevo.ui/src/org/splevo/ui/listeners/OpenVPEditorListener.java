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

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.util.VPMUIUtil;

/**
 * Mouse adapter to listen for events which trigger the extraction of the source projects.
 */
public class OpenVPEditorListener extends MouseAdapter {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(OpenVPEditorListener.class);

    /** The internal reference to the splevo project editor to work with. */
    private SPLevoProjectEditor splevoProjectEditor = null;

    /**
     * A listener to open the VPExplorer with a reasonable user feedback. It always openes the
     * latest vpm of the SPLevo project under control of a splevoProjectEditor.
     *
     * The editor is bound to the vpm instead of the splevo project or the vpm itself, as both might
     * been target of change between the listener intialization and assignment to the editor and the
     * click by the user.
     *
     * @param splevoProjectEditor
     *            The reference to the splevo project editor.
     */
    public OpenVPEditorListener(SPLevoProjectEditor splevoProjectEditor) {
        this.splevoProjectEditor = splevoProjectEditor;
    }

    @Override
    public void mouseUp(MouseEvent e) {

        SPLevoProject splevoProject = splevoProjectEditor.getSplevoProject();
        EList<String> vpmModelPaths = splevoProject.getVpmModelPaths();
        if (vpmModelPaths.size() == 0) {
            Shell shell = Display.getDefault().getShells()[0];
            MessageDialog.openError(shell, "No VPM to open", "There is Variation Point Model to be opened");
            logger.error("Tried to open VPM from a SPLevoProject that does not contain any");
            return;
        }

        String vpmPath = vpmModelPaths.get(vpmModelPaths.size() - 1);
        VPMUIUtil.openVPExplorer(splevoProject, vpmPath);
    }
}