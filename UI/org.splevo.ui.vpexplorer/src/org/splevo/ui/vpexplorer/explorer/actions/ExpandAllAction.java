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
import org.eclipse.swt.widgets.TreeItem;
import org.splevo.ui.vpexplorer.Activator;
import org.splevo.ui.vpexplorer.explorer.VPExplorer;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Action to expand the navigator tree.
 *
 * A mode can be defined to decide about expanding the full tree or up to a specific type of element
 * (e.g. VariationPoint) only.
 */
public class ExpandAllAction extends Action {

    private static final String ICON_FULL = "icons/expandall.gif";
    private static final String ICON_VARIATIONPOINT = "icons/expandall_variationpoint.gif";

    /** The mode up to which items to expand the tree. */
    public enum MODE {
        FULL, VARIATIONPOINT
    }

    private final VPExplorer explorer;
    private final MODE mode;

    /**
     * Instantiates a new toggle groups action.
     *
     * @param explorer
     *            the explorer to be toggled
     */
    public ExpandAllAction(VPExplorer explorer) {
        this(explorer, MODE.FULL);
    }

    /**
     * Instantiates a new toggle groups action.
     *
     * @param explorer
     *            the explorer to be toggled
     * @param mode
     *            The mode up to which element type to expand the tree.
     */
    public ExpandAllAction(VPExplorer explorer, MODE mode) {
        super(mode == MODE.VARIATIONPOINT ? "Expand All VariationPoints" : "Expand All", IAction.AS_CHECK_BOX);
        this.explorer = explorer;
        this.mode = mode;

        if (mode == MODE.VARIATIONPOINT) {
            setToolTipText("Expand up to Variation Points");
            setImageDescriptor(Activator.getImageDescriptor(ICON_VARIATIONPOINT));
        } else {
            setToolTipText("Expand complete tree");
            setImageDescriptor(Activator.getImageDescriptor(ICON_FULL));
        }
    }

    @Override
    public void run() {

        if (mode == MODE.FULL) {
            explorer.getCommonViewer().expandAll();
        } else {
            for (TreeItem item : explorer.getCommonViewer().getTree().getItems()) {
                expandTreeItem(item);
            }
        }
        this.setChecked(false);
        explorer.getCommonViewer().refresh();
    }

    /**
     * Recursively expand the tree until an element of the defined mode is found.
     *
     * @param item
     *            The tree item to expand including it sub items.
     */
    private void expandTreeItem(TreeItem item) {
        if (item.getData() instanceof VariationPoint) {
            return;
        } else {
            item.setExpanded(true);
            explorer.getCommonViewer().refresh();
            for (TreeItem subItem : item.getItems()) {
                expandTreeItem(subItem);
            }
        }
    }

    @Override
    public String getId() {
        return "org.splevo.ui.vpexplorer.explorer.actions" + mode.toString();
    }

}
