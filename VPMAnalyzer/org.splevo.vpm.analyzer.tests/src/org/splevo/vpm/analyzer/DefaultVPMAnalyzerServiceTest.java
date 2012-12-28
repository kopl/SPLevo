/**
 * 
 */
package org.splevo.vpm.analyzer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.Test;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.analyzer.graph.RelationshipEdge;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.refinement.BasicDetectionRule;
import org.splevo.vpm.analyzer.refinement.DetectionRule;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Test case for the default vpm analyzer service implementation.
 * 
 * @author Benjamin Klatt
 * 
 */
public class DefaultVPMAnalyzerServiceTest extends AbstractTest {

    /**
     * Test initializing the VPMGraph for the vpm model initialized for the GCD example.
     * 
     * @throws InterruptedException
     *             identifies test has been interrupted.
     * @throws IOException
     *             identifies test input could not be read.
     */
    @Test
    public void testInitVPMGraph() throws IOException, InterruptedException {
        DefaultVPMAnalyzerService service = new DefaultVPMAnalyzerService();
        VariationPointModel vpm = SPLevoTestUtil.loadGCDVPMModel();
        Graph graph = service.initVPMGraph(vpm);

        assertNotNull("VPMGraph not allowed to be null.", graph);

        assertEquals("Wrong number of vertices.", 6, graph.getNodeCount());

    }

    /**
     * Test method for
     * {@link org.splevo.vpm.analyzer.DefaultVPMAnalyzerService#mergeGraphs(java.util.List)}.
     * 
     * @throws InterruptedException
     *             identifies test has been interrupted.
     */
    @SuppressWarnings("unused")
    @Test
    public void testMergeGraphs() throws InterruptedException {

        VPMGraph graph1 = new VPMGraph("VPMGraph");
        Node node1A = createNode(graph1, "A");
        Node node1B = createNode(graph1, "B");
        Node node1C = createNode(graph1, "C");
        Node node1D = createNode(graph1, "D");

        VPMGraph graph2 = new VPMGraph("VPMGraph");
        Node node2A = createNode(graph2, "A");
        Node node2B = createNode(graph2, "B");
        Node node2C = createNode(graph2, "C");
        Node node2D = createNode(graph2, "D");

        RelationshipEdge edge1 = graph1.addEdge("structure.e1", node1A, node1B);
        edge1.addRelationshipLabel("Structure");

        RelationshipEdge edge2 = graph2.addEdge("flow.e1", node2C, node2D);
        edge2.addRelationshipLabel("Flow");

        RelationshipEdge edge3 = graph2.addEdge("flow.e2", node2A, node2B);
        edge3.addRelationshipLabel("Flow");

        List<VPMGraph> graphs = new ArrayList<VPMGraph>();
        graphs.add(graph1);
        graphs.add(graph2);

        DefaultVPMAnalyzerService service = new DefaultVPMAnalyzerService();
        VPMGraph graph = service.mergeGraphs(graphs);

        assertNotNull("Merge did not return any graph", graph);
        assertEquals("Wrong number of merged edges", 2, graph.getEdgeCount());

    }

    /**
     * Test method for
     * {@link org.splevo.vpm.analyzer.DefaultVPMAnalyzerService#deriveRefinements(org.splevo.vpm.analyzer.graph.VPMGraph, java.util.List)}
     * .
     * 
     * @throws InterruptedException
     *             identifies test has been interrupted.
     * @throws IOException
     *             identifies the test graph could not be loaded.
     */
    @Test
    public void testDeriveRefinements() throws IOException, InterruptedException {

        // init the test graph
        VPMGraph graph = SPLevoTestUtil.loadGCDVPMGraph();
        Node node1 = graph.getNode(0);
        Node node2 = graph.getNode(1);
        RelationshipEdge edge = graph.addEdge("structure.e1", node1, node2);
        edge.addRelationshipLabel("Flow");
        edge.addRelationshipLabel("Structure");

        // prepare the detection rule
        List<String> detectionSpecs = new ArrayList<String>();
        detectionSpecs.add("Flow");
        detectionSpecs.add("Structure");
        BasicDetectionRule rule = new BasicDetectionRule(detectionSpecs, RefinementType.GROUPING);
        List<DetectionRule> detectionRules = new ArrayList<DetectionRule>();
        detectionRules.add(rule);

        // trigger the derivce refinement
        DefaultVPMAnalyzerService service = new DefaultVPMAnalyzerService();
        List<Refinement> refinements = service.deriveRefinements(graph, detectionRules);

        // check the result
        assertNotNull("Unexpected null value. At least an empty list is expected", refinements);
        assertEquals("Wrong number of refinements", 1, refinements.size());

        Refinement refinement = refinements.get(0);
        assertEquals("Wrong number of VPs in refinement", 2, refinement.getVariationPoints().size());
        assertEquals("Wrong refinement type", RefinementType.GROUPING, refinement.getType());
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
    private Node createNode(Graph graph, String label) {
        Node node = graph.addNode(label);
        node.addAttribute(VPMGraph.GS_LABEL, label);

        return node;
    }

}
