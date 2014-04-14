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
package org.splevo.ui.vpexplorer.explorer;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonNavigator;
import org.splevo.ui.vpexplorer.explorer.actions.ToggleGroupsAction;

/**
 * The VPExplorer displays a VP model in a tree structure.
 */
public class VPExplorer extends CommonNavigator {

    /** Id to reference the view inside eclipse. */
    public static final String VIEW_ID = "org.splevo.ui.vpexplorer";

    private VPExplorerContent vpExplorerContent;

    private boolean showGrouping = false;

    /**
     * Default explorer setting up the required dependencies.
     */
    public VPExplorer() {
        vpExplorerContent = new VPExplorerContent(this);
    }

    @Override
    protected IAdaptable getInitialInput() {
        this.getCommonViewer().refresh();
        return vpExplorerContent;
    }

    /**
     * Access the static content element.
     * 
     * @return The singleton content element.
     */
    public VPExplorerContent getVpExplorerContent() {
        return vpExplorerContent;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.navigator.CommonNavigator#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createPartControl(Composite aParent) {
        super.createPartControl(aParent);
        Action action = new ToggleGroupsAction(this);
        IActionBars actionBars = getViewSite().getActionBars();
        IMenuManager dropDownMenu = actionBars.getMenuManager();
        IToolBarManager toolBar = actionBars.getToolBarManager();
        dropDownMenu.add(action);
        toolBar.add(action);
    }

    /**
     * Toggles between "should display groupings" and "should not display groupings".
     */
    public void toggleShowGrouping() {
        showGrouping = !showGrouping;
        this.getCommonViewer().refresh();
    }

    /**
     * Returns if groupings should be shown.
     * 
     * @return true, if groupings should be shown.
     */
    public boolean getShowGrouping() {
        return showGrouping;
    }
}
