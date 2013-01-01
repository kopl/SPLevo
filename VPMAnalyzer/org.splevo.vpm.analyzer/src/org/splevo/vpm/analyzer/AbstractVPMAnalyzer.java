package org.splevo.vpm.analyzer;

import org.graphstream.graph.Node;

/**
 * An abstract variation point model analyzer providing some common infrastructure.
 * @author Benjamin Klatt
 *
 */
public abstract class AbstractVPMAnalyzer implements VPMAnalyzer {

    @Override
    public abstract String getRelationshipLabel();

    /**
     * Build a unique identifier for the undirected edge between the nodes. The edge id is build
     * from the Relationship label and the nodes ids in ascending order.
     * 
     * @param node1
     *            The first node to connect.
     * @param node2
     *            The second node to connect.
     * @return The prepared edge id.
     */
    public final String buildEdgeId(Node node1, Node node2) {
        if (node1.getId().compareTo(node2.getId()) < 0) {
            return getRelationshipLabel() + "#" + node1.getId() + "#" + node2.getId();
        } else {
            return getRelationshipLabel() + "#" + node2.getId() + "#" + node1.getId();
        }
    }

}
