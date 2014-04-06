/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.vpm.analyzer.refinement;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.graph.RelationshipEdge;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementFactory;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * A detection rule to derive refinement recommendations from a set of VPMRelationshipEdge labels.
 */
public class BasicDetectionRule implements DetectionRule {

    private Logger logger = Logger.getLogger(BasicDetectionRule.class);

    /** The edges that must be match. */
    private List<String> edgeLabels = null;

    /** The type of refinement to create in case of a match. */
    private RefinementType refinementType = RefinementType.GROUPING;

    /**
     * Constructor requiring to set the edge labels to match and the resulting refinement type.
     *
     * @param edgeLabels
     *            The list of edge labels.
     * @param refinementType
     *            The type of refinement to create.
     */
    public BasicDetectionRule(final List<String> edgeLabels, final RefinementType refinementType) {
        this.edgeLabels = edgeLabels;
        this.refinementType = refinementType;
    }

    /**
     * <p>
     * Detect refinements based on analyzing the edge labels of the relationships stored in a VPM
     * graph.
     * </p>
     * <p>
     * For each subgraph identified by connected edges with the labels configured for the detection
     * rule, a refinement of the configured type is created.
     * </p>
     *
     * The detection algorithm checks all edges if they match all search edge labels. Not matching
     * ones are ignored.<br>
     * In case of a match:
     * <ul>
     * <li>Get the nodes of edges</li>
     * <li>Check if they are already contained in a subgraph</li>
     * <li>If the nodes are:
     * <ul>
     * <li>In the same subgraph: do nothing</li>
     * <li>In different subgraphs: merge the subgraphs</li>
     * <li>Only one is in a subgraph: add the other node to the same</li>
     * <li>None is in a subgraph: Create a new subgraph</li>
     * </ul>
     * </li>
     * <li>Finally, create a refinement per subgraph</li>
     * </ul>
     *
     * For an efficient processing three indexes are used:
     * <ol>
     * <li>Node to subgraph: For fast lookup of subgraph membership of the edge's nodes</li>
     * <li>Subgraph to node: For fast subgraph merging</li>
     * <li>Subgraph to edge: For fast refinement creation per subgraph</li>
     * </ol>
     * Building refinement per subgraph is done based on the edges to enable further interpretation
     * of relationship information stored to the edges. For example: Which terms lead to a semantic
     * relationship.
     *
     * DesignDecision: Refinement creation based on edges to access further relationship information
     * stored with edges
     *
     * DesignDecision: Flat edge-based subgraph analysis using indexes instead of deep search to
     * improve performance.
     *
     * DesignDecision: No recursive deep search as this has lead to stack overflows in the past due
     * to to large graphs
     *
     *
     * @param vpmGraph
     *            The graph to analyze.
     * @return The detected refinements.
     */
    @Override
    public List<Refinement> detect(final VPMGraph vpmGraph) {

        List<Refinement> refinements = Lists.newArrayList();

        Map<Node, Integer> nodeSubgraphIndex = Maps.newLinkedHashMap();
        ArrayListMultimap<Integer, Node> subgraphNodeIndex = ArrayListMultimap.create();
        ArrayListMultimap<Integer, RelationshipEdge> subgraphEdgeIndex = ArrayListMultimap.create();

        int nextSubgraphId = 0;

        for (Edge edge : vpmGraph.getEachEdge()) {
            RelationshipEdge relationshipEdge = (RelationshipEdge) edge;
            if (match(relationshipEdge)) {

                Node sourceNode = relationshipEdge.getSourceNode();
                Node targetNode = relationshipEdge.getTargetNode();

                Integer sourceNodeSubgraphId = nodeSubgraphIndex.get(sourceNode);
                Integer targetNodeSubgraphId = nodeSubgraphIndex.get(targetNode);

                // decide about subgraph
                Integer subgraphId;
                if (sourceNodeSubgraphId != null && targetNodeSubgraphId != null) {
                    if (!sourceNodeSubgraphId.equals(targetNodeSubgraphId)) {
                        mergeSubGraphNodes(nodeSubgraphIndex, subgraphNodeIndex, sourceNodeSubgraphId,
                                targetNodeSubgraphId);
                        mergeSubgraphEdges(subgraphEdgeIndex, sourceNodeSubgraphId, targetNodeSubgraphId);
                    }
                    continue;

                } else if (sourceNodeSubgraphId != null) {
                    subgraphId = sourceNodeSubgraphId;

                } else if (targetNodeSubgraphId != null) {
                    subgraphId = targetNodeSubgraphId;

                } else {
                    subgraphId = new Integer(nextSubgraphId++);
                }

                // index
                nodeSubgraphIndex.put(sourceNode, subgraphId);
                nodeSubgraphIndex.put(targetNode, subgraphId);
                subgraphNodeIndex.put(subgraphId, sourceNode);
                subgraphNodeIndex.put(subgraphId, targetNode);
                subgraphEdgeIndex.put(subgraphId, relationshipEdge);
            }
        }

        logger.info("Subgraph Count: " + subgraphEdgeIndex.keySet().size());

        for (Integer subgraphId : subgraphEdgeIndex.keySet()) {
            List<RelationshipEdge> subgraphEdges = subgraphEdgeIndex.get(subgraphId);
            Refinement refinement = buildRefinementForEdges(subgraphEdges);
            refinements.add(refinement);
        }

        return refinements;
    }

    private void mergeSubgraphEdges(ArrayListMultimap<Integer, RelationshipEdge> subgraphEdgeIndex,
            Integer sourceNodeSubGraphId, Integer targetNodeSubGraphId) {
        List<RelationshipEdge> edgesToMerge = subgraphEdgeIndex.get(targetNodeSubGraphId);
        subgraphEdgeIndex.putAll(sourceNodeSubGraphId, edgesToMerge);
        subgraphEdgeIndex.removeAll(targetNodeSubGraphId);
    }

    private void mergeSubGraphNodes(Map<Node, Integer> nodeSubgraphIndex,
            ArrayListMultimap<Integer, Node> subgraphNodeIndex, Integer sourceNodeSubGraphId,
            Integer targetNodeSubGraphId) {
        List<Node> nodesToMove = subgraphNodeIndex.get(targetNodeSubGraphId);
        for (Node node : nodesToMove) {
            nodeSubgraphIndex.put(node, sourceNodeSubGraphId);
        }
        subgraphNodeIndex.putAll(sourceNodeSubGraphId, nodesToMove);
        subgraphNodeIndex.removeAll(targetNodeSubGraphId);
    }

    /**
     * Build a refinement for an edge.
     *
     * @param subgraphEdges
     *            The edges connecting the subgraph to build a refinement for.
     * @return The prepared refinement.
     */
    private Refinement buildRefinementForEdges(List<RelationshipEdge> subgraphEdges) {

        Refinement refinement = RefinementFactory.eINSTANCE.createRefinement();
        refinement.setType(this.getRefinementType());


        StringBuilder refinementSource = new StringBuilder();
        refinementSource.append("Detected Relationship(s): ");
        boolean first = true;
        for (String searchedLabel : edgeLabels) {
            if (!first) {
                refinementSource.append(", ");
            }
            refinementSource.append(searchedLabel);
            first = false;
        }

        Set<Node> nodes = Sets.newLinkedHashSet();
        for (RelationshipEdge edge : subgraphEdges) {
            nodes.add(edge.getSourceNode());
            nodes.add(edge.getTargetNode());
        }

        for (Node node : nodes) {
            addVariationPoint(refinement, node);
        }

        refinement.setSource(refinementSource.toString());

        return refinement;
    }

    /**
     * Add the variation point of a node to the refinement.
     *
     * DesignDecision: No duplicate checking to improve performance
     *
     * Note: Adding two nodes with the same VariationPoint would lead to assign the variation point
     * twice to the refinement. There is no duplicate checking done here, because this has shown to
     * lead to a significant performance loss at this point.
     *
     *
     * @param refinement
     *            The refinement to add the vp to.
     * @param node
     *            The node to get the vp from.
     */
    private void addVariationPoint(Refinement refinement, Node node) {
        VariationPoint vp = node.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);
        refinement.getVariationPoints().add(vp);
    }

    /**
     * Match an edge against the matching specification.
     *
     * @param edge
     *            The edge to check.
     * @return true/false whether the edge matches to this rule or not.
     */
    private boolean match(final RelationshipEdge edge) {
        return edge.getRelationshipLabels().containsAll(this.edgeLabels);
    }

    @Override
    public List<String> getEdgeLabels() {
        return this.edgeLabels;
    }

    @Override
    public RefinementType getRefinementType() {
        return this.refinementType;
    }
}
