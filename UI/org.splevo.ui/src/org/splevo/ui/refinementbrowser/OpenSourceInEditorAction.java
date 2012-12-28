package org.splevo.ui.refinementbrowser;

import org.apache.log4j.Logger;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.eclipse.modisco.java.composition.javaapplication.JavaNodeSourceRegion;
import org.eclipse.swt.widgets.TreeItem;
import org.splevo.modisco.util.SourceConnector;
import org.splevo.ui.Activator;
import org.splevo.ui.jdt.JavaEditorConnector;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPointModel;

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

        if (object instanceof ASTNode) {
            ASTNode astNode = (ASTNode) object;

            TreeItem parent = selectedItem.getParentItem();
            Variant variant = (Variant) parent.getData();
            VariationPointModel vpm = variant.getVariationPoint().getGroup().getModel();

            JavaApplication javaApplication = null;
            if (!variant.getLeading()) {
                javaApplication = vpm.getLeadingModel();
            } else {
                javaApplication = vpm.getIntegrationModel();
            }

            SourceConnector sourceConnector = new SourceConnector(javaApplication);
            JavaNodeSourceRegion sourceRegion = sourceConnector.findSourceRegion(astNode);

            if (sourceRegion != null) {
                JavaEditorConnector javaEditorConnector = new JavaEditorConnector();
                javaEditorConnector.openJavaEditor(sourceRegion, true);
            } else {
                logger.warn("No SourceRegion accessible.");
            }
        } else {
            logger.warn("A non-eObject has been selected");
        }
    }

    /**
     * Check if this action is enabled. This method takes checks the currently selected icon in the
     * tree.
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
        if (data instanceof ASTNode) {
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
