package org.splevo.ui.views.vpmgraph;

import org.eclipse.jface.action.Action;
import org.eclipse.wb.swt.ResourceManager;
import org.graphstream.graph.Node;

/**
 * Action to filter the single nodes without any relationships from a graph.
 * 
 * This action only manipulates the visible graph by setting or removing the ui.hide attribute from
 * those nodes without any edges.
 * 
 * @author Benjamin Klatt
 * 
 */
class FilterSingleNodeAction extends Action {

    /** 
     * The vpm graph to manipulate. 
     * It is necessary to work with the view and always request the 
     * current instance of the graph because this might change during runtime.
     * Otherwise, the action might not manipulate the right graph instance. 
     */
    private VPMGraphView vpmGraphView;

    /** The attribute id to hide a node in the view. */
    private static final String NODE_ATTRIBUTE_HIDE = "ui.hide";

    /**
     * Constructor to set the necessary references.
     * 
     * @param vpmGraphView
     *            The graph to manipulate
     */
    public FilterSingleNodeAction(VPMGraphView vpmGraphView) {
        super("Filter Single Nodes Action");
        this.vpmGraphView = vpmGraphView;
        setToolTipText("Filter single nodes without relationships");
        setImageDescriptor(ResourceManager.getPluginImageDescriptor("org.splevo.ui",
                "icons/inactive/filter-empty-nodes.png"));
    }

    @Override
    public void run() {
        toggleIcon();
        toggleNodes(isChecked());
    }

    /**
     * Toggle the nodes ui.hide attribute. Add or remove the attribute depending on the provided
     * parameter.
     * 
     * @param checked
     *            The parameter to decide about the attribute add/remove.
     */
    private void toggleNodes(boolean checked) {

        if (checked) {
            for (Node currentNode : this.vpmGraphView.getVpmGraph().getNodeSet()) {
                if (currentNode.getDegree() == 0) {
                    currentNode.addAttribute(NODE_ATTRIBUTE_HIDE);
                }
            }
        } else {
            for (Node currentNode : this.vpmGraphView.getVpmGraph().getNodeSet()) {
                if (currentNode.getDegree() == 0) {
                    currentNode.removeAttribute(NODE_ATTRIBUTE_HIDE);
                }
            }
        }
    }

    /**
     * Toggle the action icon depending on it's checked status.
     */
    private void toggleIcon() {
        if (isChecked()) {
            setChecked(false);
            setImageDescriptor(ResourceManager.getPluginImageDescriptor("org.splevo.ui",
                    "icons/inactive/filter-empty-nodes.png"));
        } else {
            setChecked(true);
            setImageDescriptor(ResourceManager.getPluginImageDescriptor("org.splevo.ui",
                    "icons/active/filter-empty-nodes.png"));
        }
    }
}