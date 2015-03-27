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
import org.splevo.ui.refinementbrowser.RefinementDetailsView;
import org.splevo.ui.util.UIUtil;

/**
 * Base class for rename actions in the refinement browser.
 */
public abstract class RefinementBrowserRenameAction extends RefinementBrowserContextMenuAction {

    private static final Logger LOGGER = Logger.getLogger(RefinementBrowserRenameAction.class);
    private static final String LABEL = "Rename";
    
    /**
     * Constructs a new rename action for the refinement browser.
     * @param treeViewer The tree viewer containing the refinements.
     * @param detailsView The tree viewer containing refinements and variation points.
     */
    protected RefinementBrowserRenameAction(TreeViewer treeViewer, RefinementDetailsView detailsView) {
        super(LABEL, treeViewer, detailsView);
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
     * Indicates if the given element is correctly typed for the rename action.
     * @param element The element to be renamed.
     * @return True if the element is correctly typed and therefore a rename operation can be applied.
     */
    protected abstract boolean isCorrectlyTyped(Object element);

    @Override
    protected void perform() {
        TreeItem[] selection = getTreeViewer().getTree().getSelection();
        for (TreeItem treeItem : selection) {
            if (!renameElement(treeItem.getData())) {
                LOGGER.debug("Refinement rename canceled");
            } else {
                UIUtil.getItemProviderText(treeItem.getData());                
            }
        }
        getTreeViewer().refresh();
    }
    
    /**
     * Starts the UI interaction to determine the new name and renames the element.
     * @param element The element to be renamed.
     * @return True if the element has been renamed, False otherwise.
     */
    protected abstract boolean renameElement(Object element);

}
