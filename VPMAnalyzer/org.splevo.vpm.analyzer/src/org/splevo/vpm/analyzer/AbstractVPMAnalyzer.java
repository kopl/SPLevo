package org.splevo.vpm.analyzer;

import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.Node;

/**
 * An abstract variation point model analyzer providing some common infrastructure.
 * 
 * @author Benjamin Klatt
 * 
 */
public abstract class AbstractVPMAnalyzer implements VPMAnalyzer {

    @Override
    public abstract String getRelationshipLabel();

    /** Internal list to cache which edges have already been created. */
    protected List<String> edgeRegistry = new ArrayList<String>();

    /**
     * Build a unique internal identifier for the undirected edge between the nodes. This is done
     * ensuring an alphabetical order to be independent of the parameter order.
     * 
     * @param node1
     *            The first node to connect.
     * @param node2
     *            The second node to connect.
     * @return The prepared edge id.
     */
    protected String buildEdgeRegistryID(Node node1, Node node2) {
        if (node1.getId().compareTo(node2.getId()) < 0) {
            return node1.getId() + "#" + node2.getId();
        } else {
            return node2.getId() + "#" + node1.getId();
        }
    }

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
    public String buildEdgeId(Node node1, Node node2) {
        if (node1.getId().compareTo(node2.getId()) < 0) {
            return getRelationshipLabel() + "#" + node1.getId() + "#" + node2.getId();
        } else {
            return getRelationshipLabel() + "#" + node2.getId() + "#" + node1.getId();
        }
    }

    /**
     * Builds the edge descriptor.
     * 
     * @param node1
     *            the source node of the edge.
     * @param node2
     *            the target node of the edge.
     * @param relationshipSubLabel
     *            the relationship sub label
     * @return the VPM edge descriptor. Might be null if the edge already exists.
     */
    protected VPMEdgeDescriptor buildEdgeDescriptor(Node node1, Node node2, String relationshipSubLabel) {

        VPMEdgeDescriptor descriptor = null;

        // skip if code structure relationship already exists
        String edgeRegistryID = buildEdgeRegistryID(node1, node2);
        if (!edgeRegistry.contains(edgeRegistryID)) {
            edgeRegistry.add(edgeRegistryID);

            // build edge descriptor
            descriptor = new VPMEdgeDescriptor(getRelationshipLabel(), relationshipSubLabel, node1.getId(), node2.getId());
        }

        return descriptor;
    }

}
