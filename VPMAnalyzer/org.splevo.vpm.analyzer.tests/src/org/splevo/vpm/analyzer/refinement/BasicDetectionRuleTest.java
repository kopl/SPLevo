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
package org.splevo.vpm.analyzer.refinement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.graphstream.graph.Node;
import org.junit.Ignore;
import org.junit.Test;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.analyzer.AbstractTest;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.codelocation.CodeLocationVPMAnalyzer;
import org.splevo.vpm.analyzer.graph.RelationshipEdge;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * Test case for the basic detection rule.
 */
public class BasicDetectionRuleTest extends AbstractTest {

    private Logger logger = Logger.getLogger(BasicDetectionRuleTest.class);

    /**
     * Test method for
     * {@link org.splevo.vpm.analyzer.refinement.BasicDetectionRule#detect(org.splevo.vpm.analyzer.graph.VPMGraph, boolean)}
     * .
     *
     * @throws Exception
     *             Identifies the test failed to load the test model.
     */
    @Test
    public void testDetect() throws Exception {

        // prepare the graph with relationship edges
        // for the gcd example.
        VPMGraph graph = SPLevoTestUtil.loadGCDVPMGraph();
        VPMAnalyzer analyzer = new CodeLocationVPMAnalyzer();
        VPMAnalyzerResult result = analyzer.analyze(graph);

        List<VPMAnalyzerResult> results = new ArrayList<VPMAnalyzerResult>();
        results.add(result);

        DefaultVPMAnalyzerService service = new DefaultVPMAnalyzerService();
        service.createGraphEdges(graph, results);

        // prepare a simple detection rule
        List<String> edgeLabels = new ArrayList<String>();
        edgeLabels.add(CodeLocationVPMAnalyzer.RELATIONSHIP_LABEL_CODE_LOCATION);
        DetectionRule detectionRule = new BasicDetectionRule(edgeLabels, RefinementType.MERGE);

        List<Refinement> refinements = detectionRule.detect(graph, true);
        assertEquals("Wrong number of refinements detected", 2, refinements.size());
    }

    /**
     * Test if the detection rule is able to handle graphs with cycles.
     */
    @Ignore("For performance only. Should not be part of each test run")
    @Test
    public void testLongSubGraph() {

        String testEdgeLabel = "TESTLABEL";
        VPMGraph graph = new VPMGraph("TESTGRAPH");

        int nodeCount = 1500;

        Node lastNode = null;
        for (int i = 0; i < nodeCount; i++) {
            lastNode = buildAndConnectNode(testEdgeLabel, "NODE" + i, graph, lastNode);
        }

        logger.info(String.format("Graph prepared with %s nodes and %s edges", graph.getNodeCount(), graph.getEdgeCount()));

        List<String> ruleLabels = Lists.newArrayList(testEdgeLabel);
        BasicDetectionRule rule = new BasicDetectionRule(ruleLabels, RefinementType.GROUPING);

        rule.detect(graph, true);
    }

    private Node buildAndConnectNode(String edgeLabel, String newNodeId, VPMGraph graph, Node lastNode) {
        VariationPoint vpB = mock(VariationPoint.class);
        Node node = graph.addNode(newNodeId);
        node.addAttribute(VPMGraph.VARIATIONPOINT, vpB);
        if (lastNode != null) {
            String edgeId = lastNode.getId() + node.getId();
            RelationshipEdge edgeAB = (RelationshipEdge) graph.addEdge(edgeId, lastNode.getId(), node.getId());
            edgeAB.addRelationshipLabel(edgeLabel);
        }
        return node;
    }
}
