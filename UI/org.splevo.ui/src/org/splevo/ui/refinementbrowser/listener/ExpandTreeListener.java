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
package org.splevo.ui.refinementbrowser.listener;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * Listener that can be assigned to expand current tree not in case of a double click event.
 */
public class ExpandTreeListener implements IDoubleClickListener {
    @Override
    public void doubleClick(DoubleClickEvent event) {
        TreeViewer viewer = (TreeViewer) event.getViewer();
        IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection();
        Object selectedNode = thisSelection.getFirstElement();
        viewer.setExpandedState(selectedNode, !viewer.getExpandedState(selectedNode));
    }
}
