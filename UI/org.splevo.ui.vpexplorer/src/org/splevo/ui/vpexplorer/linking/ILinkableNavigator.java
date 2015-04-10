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
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

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
         * @param selectedVPs
         *            The selected items.
         */
        public static void expandToObject(final TreeViewer treeViewer, ITreeContentProvider contentProvider,
                Iterable<VariationPoint> selectedVPs) {
            if (Iterables.isEmpty(selectedVPs)) {
                return;
            }

            treeViewer.collapseAll();
            for (VariationPoint vp : selectedVPs) {
                expandToObject(treeViewer, contentProvider, vp);
            }

            Iterable<TreeItem> selection = Iterables.filter(
                    Iterables.transform(selectedVPs, new Function<VariationPoint, TreeItem>() {
                        @Override
                        public TreeItem apply(VariationPoint arg0) {
                            return (TreeItem) treeViewer.testFindItem(arg0);
                        }
                    }), Predicates.notNull());
            treeViewer.getTree().setSelection(Iterables.toArray(selection, TreeItem.class));

        }

        private static void expandToObject(TreeViewer treeViewer, ITreeContentProvider contentProvider,
                VariationPoint vp) {
            List<Object> treePathElements = new LinkedList<Object>();
            treePathElements.add(vp);
            for (Object parent = contentProvider.getParent(vp); parent != null; parent = contentProvider
                    .getParent(parent)) {
                treePathElements.add(0, parent);
            }
            TreePath treePath = new TreePath(treePathElements.toArray());
            treeViewer.expandToLevel(treePath, 0);
        }

        /**
         * Finds the corresponding variation point in a given variation point model. The variation
         * points are compared by their id only. If the given variation point is not part of the
         * given variation point model, the return value will be another object than the given one.
         * If it is already part of the variation point model, the same object will be returned.
         * 
         * @param variationPoint
         *            The variation point for which a corresponding variation point shall be found.
         * @param vpm
         *            The variation point model to search through.
         * @return The corresponding variation point or Null if no such element exists.
         */
        public static Optional<VariationPoint> find(VariationPoint variationPoint, VariationPointModel vpm) {
            for (VariationPointGroup vpg : vpm.getVariationPointGroups()) {
                for (VariationPoint vp : vpg.getVariationPoints()) {
                    if (vp.getId().equals(variationPoint.getId())) {
                        return Optional.of(vp);
                    }
                }
            }
            return Optional.absent();
        }
    }

    /**
     * Notification of a changed selection in another navigator.
     * 
     * @param selectedElement
     *            The selected item.
     */
    public void elementSelectedInOtherNavigator(Iterable<Object> selectedElement);

}
