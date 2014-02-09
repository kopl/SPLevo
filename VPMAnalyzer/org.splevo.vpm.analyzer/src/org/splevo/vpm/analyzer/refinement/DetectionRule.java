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

import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.refinement.Refinement;
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
     * @param vpmGraph
     *            The graph containing the edges to detect refinements from.
     * @return True if the rule matched, false if not.
     */
    public abstract List<Refinement> detect(VPMGraph vpmGraph);

    /**
     * The labels to match the edges against.
     * @return the edgeLabels
     */
    public abstract List<String> getEdgeLabels();

    /**
     * Get the type of refinement to create if the detection rule has matched an edge.
     * @return the refinementType
     */
    public abstract RefinementType getRefinementType();

}
