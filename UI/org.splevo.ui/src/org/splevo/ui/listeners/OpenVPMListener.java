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
import org.splevo.project.VPMModelReference;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.vpexplorer.util.VPMUIUtil;

/**
 * Mouse adapter to open the latest variation point model of a SPLevo project.
 */
public class OpenVPMListener extends MouseAdapter {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(OpenVPMListener.class);

    /** The internal reference to the splevo project editor to work with. */
    private SPLevoProjectEditor splevoProjectEditor = null;

    /**
     * A listener to open latest variation point model of the SPLevo project under control with a
     * reasonable user feedback.
     *
     * The listener is bound to the editor instead of the splevo project or the vpm itself, as both
     * might been target of change between the listener initialization, assignment to the editor,
     * and the user interaction.
     *
     * @param splevoProjectEditor
     *            The reference to the splevo project editor.
     */
    public OpenVPMListener(SPLevoProjectEditor splevoProjectEditor) {
        this.splevoProjectEditor = splevoProjectEditor;
    }

    @Override
    public void mouseUp(MouseEvent e) {

        SPLevoProject splevoProject = splevoProjectEditor.getSplevoProject();
        EList<VPMModelReference> vpmModelReferences = splevoProject.getVpmModelReferences();
        if (vpmModelReferences.size() == 0) {
            Shell shell = Display.getDefault().getShells()[0];
            MessageDialog.openError(shell, "No VPM to open", "There is Variation Point Model to be opened");
            logger.error("Tried to open VPM from a SPLevoProject that does not contain any");
            return;
        }

        VPMModelReference vpmModelReference = vpmModelReferences.get(vpmModelReferences.size() - 1);
        VPMUIUtil.openVPExplorer(splevoProject, vpmModelReference);
    }
}