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
package org.splevo.ui.refinementbrowser;

import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.splevo.vpm.refinement.Refinement;

/**
 * Utilities for implementing the refinement browser.
 */
public final class RefinementBrowserUtil {

    private RefinementBrowserUtil() {
    }

    /**
     * Return a tree viewer if it is the source of an event.
     *
     * @param event
     *            The event to check the source for.
     * @return The tree viewer or null if the source is something else.
     */
    public static TreeViewer getTreeViewer(SelectionChangedEvent event) {
        Object source = event.getSource();
        if (source instanceof TreeViewer) {
            return (TreeViewer) source;
        } else {
            return null;
        }
    }

    /**
     * Get the refinement serving as input for a tree viewer.
     *
     * @param treeViewer
     *            The tree viewer to process.
     * @return Null if the tree viewer is null or it's input is not a refinement.
     */
    public static Refinement getRefinement(TreeViewer treeViewer) {
        if (treeViewer == null) {
            return null;
        }
        Object input = treeViewer.getInput();
        if (input instanceof Refinement) {
            return (Refinement) input;
        } else {
            return null;
        }
    }
}
