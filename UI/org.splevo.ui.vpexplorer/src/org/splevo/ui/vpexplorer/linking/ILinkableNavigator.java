/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.vpexplorer.linking;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.splevo.vpm.variability.VariationPoint;

// TODO find out if the link helper extension point can be used for this
/**
 * Interface for navigators that can be linked to other navigators.
 */
public interface ILinkableNavigator {

    /**
     * Helper class that allows expanding and selecting an item in a tree viewer.
     */
    public static class ILinkableNavigatorHelper {

        /**
         * Expand and select an given item in a tree viewer. All other tree items are collapsed.
         * 
         * @param treeViewer
         *            The treeviewer that contains the item.
         * @param contentProvider
         *            A content provider to find parents and children of the given item.
         * @param selectedVP
         *            The selected item.
         */
        public static void expandToObject(TreeViewer treeViewer, ITreeContentProvider contentProvider,
                VariationPoint selectedVP) {
            List<Object> treePathElements = new LinkedList<Object>();
            treePathElements.add(selectedVP);
            for (Object parent = contentProvider.getParent(selectedVP); parent != null; parent = contentProvider
                    .getParent(parent)) {
                treePathElements.add(0, parent);
            }
            TreePath treePath = new TreePath(treePathElements.toArray());

            treeViewer.collapseAll();
            treeViewer.expandToLevel(treePath, 0);

            Widget possibleTreeItem = treeViewer.testFindItem(selectedVP);
            if (possibleTreeItem instanceof TreeItem) {
                treeViewer.getTree().setTopItem((TreeItem) possibleTreeItem);
                treeViewer.getTree().select((TreeItem) possibleTreeItem);
            }
        }
    }

    /**
     * Notification of a changed selection in another navigator.
     * 
     * @param selectedElement
     *            The selected item.
     */
    public void elementSelectedInOtherNavigator(Object selectedElement);

}
