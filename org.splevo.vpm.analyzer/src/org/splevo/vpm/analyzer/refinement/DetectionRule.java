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
public interface DetectionRule {

    /**
     * Check if a RelationShipEdge matches the conditions of this detection rule.
     * 
     * @param edge
     *            The edge to check.
     * @return True if the rule matched, false if not.
     */
    public abstract boolean match(RelationshipEdge edge);

    /**
     * The labels to match an edge agains.
     * @return the edgeLabels
     */
    public abstract List<String> getEdgeLabels();

    /**
     * Get the type of refinement to create if the detection rule has matched an edge.
     * @return the refinementType
     */
    public abstract RefinementType getRefinementType();

}
