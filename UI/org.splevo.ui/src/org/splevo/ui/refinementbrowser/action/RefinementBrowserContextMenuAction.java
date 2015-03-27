package org.splevo.ui.refinementbrowser.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * Base class for context menu actions of the refinement browser.
 */
public abstract class RefinementBrowserContextMenuAction extends Action {

    /**
     * The label of the menu entry.
     */
    private final String text;
    
    /**
     * The corresponding {@link TreeViewer} that has the refinements.
     */
    private final TreeViewer treeViewer;
    
    /**
     * Constructs a new context menu action.
     * @param text The label of the action.
     * @param treeViewer The tree viewer containing the refinements.
     */
    protected RefinementBrowserContextMenuAction(String text, TreeViewer treeViewer) {
        this.text = text;
        this.treeViewer = treeViewer;
    }
    
    /**
     * @return The tree viewer for the refinements.
     */
    protected TreeViewer getTreeViewer() {
        return treeViewer;
    }
    
    @Override
    public String getText() {
        return text;
    }

    @Override
    public boolean isEnabled() {
        return isApplicable();
    }
    
    /**
     * Indicates if the action is applicable in the given context.
     * 
     * @return True if applicable, false otherwise.
     */
    protected abstract boolean isApplicable();
    
    @Override
    public void run() {
        perform();
    }
    
    /**
     * Performs the action.
     */
    protected abstract void perform();

}
