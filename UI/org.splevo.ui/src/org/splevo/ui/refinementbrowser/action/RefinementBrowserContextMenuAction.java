package org.splevo.ui.refinementbrowser.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;
import org.splevo.ui.refinementbrowser.RefinementDetailsView;

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
     * The {@link TreeViewer} that has the details view.
     */
    private final RefinementDetailsView detailsView;
    
    /**
     * Constructs a new context menu action.
     * @param text The label of the action.
     * @param treeViewer The tree viewer containing the refinements.
     * @param detailsView The details view containing refinements and variation points.
     */
    protected RefinementBrowserContextMenuAction(String text, TreeViewer treeViewer, RefinementDetailsView detailsView) {
        this.text = text;
        this.treeViewer = treeViewer;
        this.detailsView = detailsView;
    }
    
    /**
     * @return The tree viewer for the refinements.
     */
    protected TreeViewer getTreeViewer() {
        return treeViewer;
    }
    
    /**
     * @return The details view for the selected refinement.
     */
    protected RefinementDetailsView getRefinementDetailsView() {
        return detailsView;
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
