package org.splevo.vpm.analyzer.refinement;

import java.util.List;

import org.splevo.vpm.analyzer.graph.RelationshipEdge;
import org.splevo.vpm.refinement.RefinementType;

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

    /**
     * Constructor requiring to set the edge labels to match and the resulting refinement type.
     * 
     * @param edgeLabels
     *            The list of edge labels.
     * @param refinementType
     *            The type of refinement to create.
     */
    public BasicDetectionRule(List<String> edgeLabels, RefinementType refinementType) {
        this.edgeLabels = edgeLabels;
        this.refinementType = refinementType;
    }

    /* (non-Javadoc)
     * @see org.splevo.vpm.analyzer.refinement.DetectionRule#matches(org.splevo.vpm.analyzer.graph.RelationshipEdge)
     */
    @Override
    public boolean match(RelationshipEdge edge) {

        boolean matched = true;
        matched = edge.getRelationshipLabels().containsAll(edgeLabels);

        return matched;
    }

    /* (non-Javadoc)
     * @see org.splevo.vpm.analyzer.refinement.DetectionRule#getEdgeLabels()
     */
    @Override
    public List<String> getEdgeLabels() {
        return edgeLabels;
    }

    /* (non-Javadoc)
     * @see org.splevo.vpm.analyzer.refinement.DetectionRule#getRefinementType()
     */
    @Override
    public RefinementType getRefinementType() {
        return refinementType;
    }
}
