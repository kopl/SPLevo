/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.vpm.analyzer;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.variabilityFactory;

/**
 * Utility class to support VPM Analysis test development.
 */
public final class VPMAnalyzerTestUtil {

    private VPMAnalyzerTestUtil() {
    }

    /**
     * Create node with a label.
     *
     * @param graph
     *            The graph to create the node in.
     * @param label
     *            The string for the node identifier and label.
     * @return The prepared node.
     */
    public static Node createNode(Graph graph, String label) {
        Node node = graph.addNode(label);
        node.addAttribute(VPMGraph.GS_LABEL, label);

        return node;
    }

    /**
     * Create a node in the graph with the provided label and a mock variation point.
     *
     * @param graph
     *            The graph to add the node to.
     * @param label
     *            The label of the node
     * @return The prepared node.
     */
    public static Node createNodeWithVP(VPMGraph graph, String label) {
        Node node = createNode(graph, label);
        VariationPoint vp = variabilityFactory.eINSTANCE.createVariationPoint();
        VariationPointGroup group = variabilityFactory.eINSTANCE.createVariationPointGroup();
        group.setGroupId(label);
        vp.setGroup(group);
        node.addAttribute(VPMGraph.VARIATIONPOINT, vp);
        return node;
    }

}
