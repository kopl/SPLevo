package org.splevo.vpm.analyzer.graph;

import org.graphstream.graph.Graph;
import org.graphstream.stream.thread.ThreadProxyPipe;

/**
 * A proxy to change the edge's labels to the value of a custom
 * attribute if a value is set for it.
 * 
 * @author Benjamin Klatt
 * 
 */
public class CustomEdgeLabelAttributeProxy extends ThreadProxyPipe {
    
    /** The key of the label attribute to use instead of the ui.label. */
    private String labelKey;

    /**
     * The constructor for the edge label.
     * 
     * @param graph
     *            The graph to present.
     * @param labelKey
     *            The label key to be aware of.
     */
    public CustomEdgeLabelAttributeProxy(final Graph graph, final String labelKey) {
        super(graph, true);

        this.labelKey = labelKey;
    }
    
    @Override
    public void sendEdgeAttributeAdded(String sourceId, long timeId, String edgeId, String attribute, Object value) {
        if (attribute.equals(this.labelKey)) {
            super.sendEdgeAttributeAdded(sourceId, timeId, edgeId, "ui.label", value);
        }
        super.sendEdgeAttributeAdded(sourceId, timeId, edgeId, attribute, value);
    }
}
