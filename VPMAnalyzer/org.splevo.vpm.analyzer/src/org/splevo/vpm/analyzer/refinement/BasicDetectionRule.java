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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.graph.RelationshipEdge;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementFactory;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * A detection rule to derive refinement recommendations from a set of VPMRelationshipEdge labels.
 * 
 * @author Benjamin Klatt
 * 
 */
public class BasicDetectionRule implements DetectionRule {

    /** The edges that must be match. */
    private List<String> edgeLabels = null;

    /** The type of refinement to create in case of a match. */
    private RefinementType refinementType = null;

    /** A cache for the edges already processed. */
    private final List<Edge> processedEdges = new ArrayList<Edge>();

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

    @Override
    public List<Refinement> detect(final VPMGraph vpmGraph) {

        final List<Refinement> refinements = new ArrayList<Refinement>();

        for (final Edge edge : vpmGraph.getEachEdge()) {

            if (this.processedEdges.contains(edge)) {
                continue;
            }

            List<RelationshipEdge> subgraphEdges = new ArrayList<RelationshipEdge>();
            detectSubgraph((RelationshipEdge) edge, subgraphEdges);

            // build refinement
            if (subgraphEdges.size() > 0) {
                Refinement refinement = buildRefinementForEdges(subgraphEdges);
                refinements.add(refinement);
            }

        }

        return refinements;
    }

    /**
     * Build the subgraph of edges matching the detection rule criteria. It also takes care that
     * processed edges are registered to not been processed twice. Especially in an undirected
     * graph, this would lead into problems.
     * 
     * @param edge
     *            The edge to check.
     * @param subgraphEdges
     *            The edge list to put matched edges into.
     */
    private void detectSubgraph(RelationshipEdge edge, List<RelationshipEdge> subgraphEdges) {
        if (match(edge)) {
            subgraphEdges.add(edge);
            processedEdges.add(edge);

            // recursively process subsequent edges
            for (RelationshipEdge subsequentEdge : subsequentEdges(edge)) {
                if (processedEdges.contains(subsequentEdge)) {
                    continue;
                }
                detectSubgraph(subsequentEdge, subgraphEdges);
            }
        }
    }

    /**
     * Get the subsequent edges for the source and target nodes of the edge.
     * 
     * @param edge
     *            The edge to get the subsequent edges for.
     * @return The list of edges on the next level.
     */
    private List<RelationshipEdge> subsequentEdges(RelationshipEdge edge) {
        List<RelationshipEdge> edges = new ArrayList<RelationshipEdge>();
        Collection<RelationshipEdge> sourceEdges = edge.getSourceNode().getEdgeSet();
        Collection<RelationshipEdge> targetEdges = edge.getTargetNode().getEdgeSet();

        edges.addAll(sourceEdges);
        edges.addAll(targetEdges);

        return edges;
    }

    /**
     * Build a refinement for an edge.
     * 
     * @param edges
     *            The edges to build the refinement for.
     * @return The refinemnt.
     */
    private Refinement buildRefinementForEdges(List<RelationshipEdge> edges) {

        Refinement refinement = RefinementFactory.eINSTANCE.createRefinement();
        refinement.setType(this.getRefinementType());

        for (RelationshipEdge edge : edges) {
            addVariationPoint(refinement, edge.getSourceNode());
            addVariationPoint(refinement, edge.getTargetNode());
        }

        // TODO: handle analyzer configuration for refinement.
        StringBuilder source = new StringBuilder();
        source.append("Detection Rule: ");
        source.append(edgeLabels.toString());
        source.append("\n");
        
        refinement.setSource("Detection Rule " + edgeLabels.toString());

        return refinement;
    }

    /**
     * Add the variation point of a node to the refinement. It is ensured, that no vp is added
     * twice.
     * 
     * @param refinement
     *            The refinement to add the vp to.
     * @param node
     *            The node to get the vp from.
     */
    private void addVariationPoint(Refinement refinement, Node node) {
        VariationPoint vp = node.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);
        if (!refinement.getVariationPoints().contains(vp)) {
            refinement.getVariationPoints().add(vp);
        }
    }

    /**
     * Match an edge against the matching specification.
     * 
     * @param edge
     *            The edge to check.
     * @return true/false whether the edge matches to this rule or not.
     */
    public boolean match(final RelationshipEdge edge) {

        boolean matched = true;
        matched = edge.getRelationshipLabels().containsAll(this.edgeLabels);

        return matched;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.splevo.vpm.analyzer.refinement.DetectionRule#getEdgeLabels()
     */
    @Override
    public List<String> getEdgeLabels() {
        return this.edgeLabels;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.splevo.vpm.analyzer.refinement.DetectionRule#getRefinementType()
     */
    @Override
    public RefinementType getRefinementType() {
        return this.refinementType;
    }
}
