package org.splevo.ui.refinementbrowser;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.splevo.ui.Activator;
import org.splevo.ui.jdt.JavaEditorConnector;
import org.splevo.vpm.software.JavaSoftwareElement;

/**
 * Action to open the currently selected element of a tree viewer in a source editor and higlight
 * it.
 * 
 * @author Benjamin Klatt
 * 
 */
public final class OpenSourceInEditorAction extends Action {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(OpenSourceInEditorAction.class);

    /** The tree viewer to access the selected items. */
    private TreeViewer treeViewer = null;
    
    /** The connector to the java editor. */
    private JavaEditorConnector javaEditorConnector = new JavaEditorConnector(); 

    /**
     * Constructor requiring a reference to the tree viewer containing the refinement details.
     * 
     * @param treeViewer
     *            The tree viewer to get the selection from.
     */
    public OpenSourceInEditorAction(TreeViewer treeViewer) {
        this.treeViewer = treeViewer;
    }

    /**
     * The run method to execute the action.
     */
    public void run() {

        if (this.treeViewer.getTree().getSelection().length == 0) {
            logger.error("No item selected in refinement detail view tree.");
            return;
        }
        TreeItem selectedItem = treeViewer.getTree().getSelection()[0];
        Object object = selectedItem.getData();

        if (object instanceof JavaSoftwareElement) {
            javaEditorConnector.openEditor((JavaSoftwareElement) object);
        } else {
            logger.warn("A non supported element has been selected");
        }
    }

    /**
     * Check if this action is enabled. This method checks if
     * the selected element is a JavaSoftwareElement because only those
     * can be opened in an editor yet.
     * 
     * @return true/false whether it can be applied.
     */
    @Override
    public boolean isEnabled() {
        if (treeViewer.getTree().getSelection().length < 1) {
            return false;
        }
        TreeItem selectedItem = treeViewer.getTree().getSelection()[0];
        Object data = selectedItem.getData();
        if (data instanceof JavaSoftwareElement) {
            return true;
        }
        return false;
    }

    /**
     * Get the text for this action.
     * 
     * @return The action label.
     */
    @Override
    public String getText() {
        return "Open Source in Editor";
    }

    /**
     * Get the image descriptor for this action.
     * 
     * @return The action icon.
     */
    @Override
    public ImageDescriptor getImageDescriptor() {
        return Activator.getImageDescriptor("icons/jcu_obj.gif");
    }
}
