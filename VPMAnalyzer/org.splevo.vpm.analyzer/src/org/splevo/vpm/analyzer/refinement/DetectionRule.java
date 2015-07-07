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
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.vpm.analyzer.refinement;

import java.util.List;

import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementType;

/**
 * A detection rule to derive refinement recommendations from a set of VPMRelationshipEdge labels.
 */
public interface DetectionRule {

    /**
     * Derives the refinements from the given VPMGraph.
     * 
     * @param vpmGraph
     *            The graph containing the edges to detect refinements from.
     * @param fullRefinementReasons
     *            If activated, as many as possible refinement reasons are collected. The resulting
     *            refinements must not change after enabling/disabling this flag. The refinement
     *            information might differ.
     * @return The list of detected refinements.
     */
    public abstract List<Refinement> detect(VPMGraph vpmGraph, boolean fullRefinementReasons);

    /**
     * The labels to match the edges against.
     * 
     * @return the edgeLabels
     */
    public abstract List<String> getEdgeLabels();

    /**
     * Get the type of refinement to create if the detection rule has matched an edge.
     * 
     * @return the refinementType
     */
    public abstract RefinementType getRefinementType();

}
