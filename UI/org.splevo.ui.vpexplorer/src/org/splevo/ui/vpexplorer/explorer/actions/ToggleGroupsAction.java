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

package org.splevo.ui.vpexplorer.explorer.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.splevo.ui.vpexplorer.Activator;
import org.splevo.ui.vpexplorer.explorer.VPExplorer;

/**
 * This action toggles the display of groupings in the vp explorer.
 */
public class ToggleGroupsAction extends Action {

    private final VPExplorer explorer;

    /** Path to icon to use for the action. */
    private static final String ICON = "icons/VariationPointGroup.gif";

    /**
     * Instantiates a new toggle groups action.
     * 
     * @param explorer
     *            the explorer to be toggled
     */
    public ToggleGroupsAction(VPExplorer explorer) {
        super("Toggle Groups Display Action", IAction.AS_CHECK_BOX);
        this.explorer = explorer;
        setToolTipText("Toggle display of packages");
        setImageDescriptor(Activator.getImageDescriptor(ICON));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        explorer.toggleShowGrouping();
    }

}
