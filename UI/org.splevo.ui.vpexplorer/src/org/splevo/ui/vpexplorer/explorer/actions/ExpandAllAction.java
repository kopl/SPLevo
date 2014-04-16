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
package org.splevo.ui.vpexplorer.explorer.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.splevo.ui.vpexplorer.Activator;
import org.splevo.ui.vpexplorer.explorer.VPExplorer;

/**
 * Action to expand the complete navigator tree.
 */
public class ExpandAllAction extends Action {

    private final VPExplorer explorer;

    /** Path to icon to use for the action. */
    private static final String ICON = "icons/expandall.gif";

    /**
     * Instantiates a new toggle groups action.
     *
     * @param explorer
     *            the explorer to be toggled
     */
    public ExpandAllAction(VPExplorer explorer) {
        super("Expand All", IAction.AS_CHECK_BOX);
        this.explorer = explorer;
        setToolTipText("Expand complete tree");
        setImageDescriptor(Activator.getImageDescriptor(ICON));
    }

    @Override
    public void run() {
        explorer.getCommonViewer().expandAll();
        this.setChecked(false);
    }

}
