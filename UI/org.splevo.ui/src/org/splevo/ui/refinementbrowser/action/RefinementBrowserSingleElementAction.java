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
package org.splevo.ui.refinementbrowser.action;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Base class for actions on single elements in the refinement browser.
 */
public abstract class RefinementBrowserSingleElementAction extends RefinementBrowserContextMenuAction {

    private static final Logger LOGGER = Logger.getLogger(RefinementBrowserSingleElementAction.class);
    
    /**
     * Constructs a single element refinement browser action.
     * @param label The label of the action.
     * @param treeViewer The corresponding tree viewer.
     */
    protected RefinementBrowserSingleElementAction(String label, TreeViewer treeViewer) {
        super(label, treeViewer);
    }

    @Override
    protected boolean isApplicable() {
        if (getTreeViewer().getTree().getSelection().length != 1) {
            return false;
        }

        if (!isCorrectlyTyped(getTreeViewer().getTree().getSelection()[0].getData())) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Indicates if the given element is correctly typed for the action.
     * @param element The element to be processed.
     * @return True if the element is correctly typed and therefore can be processed.
     */
    protected abstract boolean isCorrectlyTyped(Object element);

    @Override
    protected void perform() {
        TreeItem[] selection = getTreeViewer().getTree().getSelection();
        for (TreeItem treeItem : selection) {
            if (!perform(treeItem.getData())) {
                LOGGER.debug("Refinement processing failed.");
            }
        }
    }
    
    /**
     * Processes the selected element.
     * @param element The element to be processed.
     * @return True if the element has been processed successfully, False otherwise.
     */
    protected abstract boolean perform(Object element);

}
