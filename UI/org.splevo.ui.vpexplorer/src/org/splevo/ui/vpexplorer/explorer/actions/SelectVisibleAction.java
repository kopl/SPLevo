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
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.navigator.CommonViewer;
import org.splevo.ui.vpexplorer.Activator;
import org.splevo.ui.vpexplorer.explorer.VPExplorer;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Action to select all variation points currently visible in the tree.
 */
public class SelectVisibleAction extends Action {

    private static final String ICON = "icons/VariationPoint_select.gif";

    private final VPExplorer explorer;

    /**
     * Instantiates a new toggle groups action.
     *
     * @param explorer
     *            the explorer to be toggled
     */
    public SelectVisibleAction(VPExplorer explorer) {
        super("Expand visible VariationPoints", IAction.AS_PUSH_BUTTON);
        this.explorer = explorer;
        setToolTipText("Select all variation points currently visible (expanded and not filtered)");
        setImageDescriptor(Activator.getImageDescriptor(ICON));
    }

    @Override
    public void run() {
        CommonViewer viewer = explorer.getCommonViewer();

        Tree tree = viewer.getTree();
        tree.deselectAll();

        TreeItem[] items = tree.getItems();
        selectItems(tree, items);
    }

    /**
     * Select the provided items representing a variation point. All other type of nodes are checked
     * to be extended (children are displayed if available) and the their sub-items are checked to
     * be selected recursively.
     *
     * @param tree
     *            The tree to select items in.
     * @param items
     *            The list of items to check on a specific level.
     */
    private void selectItems(Tree tree, TreeItem[] items) {
        for (TreeItem item : items) {
            Object dataItem = item.getData();
            if (dataItem instanceof VariationPoint) {
                tree.select(item);
            } else {
                if (item.getExpanded()) {
                    TreeItem[] subItems = item.getItems();
                    selectItems(tree, subItems);
                }
            }
        }
    }

    @Override
    public String getId() {
        return "org.splevo.ui.vpexplorer.explorer.actions.SelectVisbleVPs";
    }

}
