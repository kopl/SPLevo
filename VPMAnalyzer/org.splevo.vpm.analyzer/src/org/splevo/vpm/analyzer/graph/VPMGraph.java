/**
 * 
 */
package org.splevo.vpm.analyzer.graph;

import java.util.List;

import org.graphstream.graph.Edge;
import org.graphstream.graph.EdgeFactory;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.AbstractNode;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swingViewer.Viewer;

/**
 * A variation point model representing graph.
 * 
 * This uses a custom edge factory to create RelationShip elements.
 * 
 * @author Benjamin Klatt
 * 
 */
//TODO: Check, if VPMGraph must really be a multi graph
public class VPMGraph extends MultiGraph {

    /** The key to store and access the label of a graph node. */
    public static final String GS_LABEL = "ui.label";
    /** The key to store and access the variation point of a graph node. */
    public static final String VARIATIONPOINT = "vp.vp";

    /**
     * Creates an empty graph.
     * 
     * @param id
     *            Unique identifier of the graph.
     * @param strictChecking
     *            If true any non-fatal error throws an exception.
     * @param autoCreate
     *            If true (and strict checking is false), nodes are automatically created when
     *            referenced when creating a edge, even if not yet inserted in the graph.
     * @param initialNodeCapacity
     *            Initial capacity of the node storage data structures. Use this if you know the
     *            approximate maximum number of nodes of the graph. The graph can grow beyond this
     *            limit, but storage reallocation is expensive operation.
     * @param initialEdgeCapacity
     *            Initial capacity of the edge storage data structures. Use this if you know the
     *            approximate maximum number of edges of the graph. The graph can grow beyond this
     *            limit, but storage reallocation is expensive operation.
     */
    public VPMGraph(String id, boolean strictChecking, boolean autoCreate, int initialNodeCapacity,
            int initialEdgeCapacity) {
        super(id, strictChecking, autoCreate, initialNodeCapacity, initialEdgeCapacity);
        // All we need to do is to change the edge factory
        setEdgeFactory(new EdgeFactory<RelationshipEdge>() {
            public RelationshipEdge newInstance(String id, Node src, Node dst, boolean directed) {
                return new RelationshipEdge(id, (AbstractNode) src, (AbstractNode) dst, directed);
            }
        });
    }

    /**
     * Creates an empty graph with default edge and node capacity.
     * 
     * @param id
     *            Unique identifier of the graph.
     * @param strictChecking
     *            If true any non-fatal error throws an exception.
     * @param autoCreate
     *            If true (and strict checking is false), nodes are automatically created when
     *            referenced when creating a edge, even if not yet inserted in the graph.
     */
    public VPMGraph(String id, boolean strictChecking, boolean autoCreate) {
        this(id, strictChecking, autoCreate, DEFAULT_NODE_CAPACITY, DEFAULT_EDGE_CAPACITY);
    }

    /**
     * Creates an empty graph with strict checking and without auto-creation.
     * 
     * @param id
     *            Unique identifier of the graph.
     */
    public VPMGraph(String id) {
        this(id, true, false);
    }
}
