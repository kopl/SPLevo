/**
 *
 */
package org.splevo.vpm.analyzer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.Test;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.analyzer.codelocation.CodeLocationVPMAnalyzer;
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
     * @throws Exception
     *             identifies test input could not be read.
     */
    @Test
    public void testInitVPMGraph() throws Exception {
        DefaultVPMAnalyzerService service = new DefaultVPMAnalyzerService();
        VariationPointModel vpm = SPLevoTestUtil.loadGCDVPMModel();
        Graph graph = service.initVPMGraph(vpm);

        assertNotNull("VPMGraph not allowed to be null.", graph);

        assertEquals("Wrong number of vertices.", 7, graph.getNodeCount());

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
        Node node1A = VPMAnalyzerTestUtil.createNode(graph1, "A");
        Node node1B = VPMAnalyzerTestUtil.createNode(graph1, "B");
        Node node1C = VPMAnalyzerTestUtil.createNode(graph1, "C");
        Node node1D = VPMAnalyzerTestUtil.createNode(graph1, "D");

        VPMGraph graph2 = new VPMGraph("VPMGraph");
        Node node2A = VPMAnalyzerTestUtil.createNode(graph2, "A");
        Node node2B = VPMAnalyzerTestUtil.createNode(graph2, "B");
        Node node2C = VPMAnalyzerTestUtil.createNode(graph2, "C");
        Node node2D = VPMAnalyzerTestUtil.createNode(graph2, "D");

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
     * {@link org.splevo.vpm.analyzer.DefaultVPMAnalyzerService#mergeGraphEdges(java.util.List)}.
     *
     * @throws InterruptedException
     *             identifies test has been interrupted.
     */
    @Test
    public void testMergeGraphEdges() throws InterruptedException {

        VPMGraph graph = new VPMGraph("VPMGraph");
        Node nodeVP1 = VPMAnalyzerTestUtil.createNode(graph, "VP1");
        Node nodeVP2 = VPMAnalyzerTestUtil.createNode(graph, "VP2");

        RelationshipEdge edge1 = graph.addEdge("CodeStructure#VP1#VP2", nodeVP1, nodeVP2);
        edge1.addRelationshipLabel("CodeStructure");

        RelationshipEdge edge2 = graph.addEdge("ProgramDependency#VP1#VP2", nodeVP1, nodeVP2);
        edge2.addRelationshipLabel("ProgramDependency");

        DefaultVPMAnalyzerService service = new DefaultVPMAnalyzerService();
        service.mergeGraphEdges(graph);

        assertNotNull("Merged graph is null", graph);
        assertEquals("Wrong number of merged edges", 1, graph.getEdgeCount());

        assertEquals("wrong merged edge id", "VP1#VP2", graph.getEdge(0).getId());
    }

    /**
     * Test method for
     * {@link org.splevo.vpm.analyzer.DefaultVPMAnalyzerService#createGraphEdges(VPMGraph, List)}.
     *
     * @throws InterruptedException
     *             identifies test has been interrupted.
     */
    @Test
    public void testCreateGraphEdges() throws InterruptedException {

        VPMGraph graph = new VPMGraph("VPMGraph");
        VPMAnalyzerTestUtil.createNode(graph, "VP1");
        VPMAnalyzerTestUtil.createNode(graph, "VP2");
        VPMAnalyzerTestUtil.createNode(graph, "VP3");
        VPMAnalyzerTestUtil.createNode(graph, "VP4");


        VPMAnalyzerResult resultCS = new VPMAnalyzerResult(new CodeLocationVPMAnalyzer());

        VPMEdgeDescriptor descriptorVP1VP2 = new VPMEdgeDescriptor("CodeStructure", "Method", "VP1", "VP2");
        resultCS.getEdgeDescriptors().add(descriptorVP1VP2);

        VPMEdgeDescriptor descriptorVP3VP4CS = new VPMEdgeDescriptor("CodeStructure", "Method", "VP3", "VP4");
        resultCS.getEdgeDescriptors().add(descriptorVP3VP4CS);

        VPMAnalyzerResult resultPD = new VPMAnalyzerResult(new CodeLocationVPMAnalyzer());

        VPMEdgeDescriptor descriptorVP3VP4PD = new VPMEdgeDescriptor("ProgramDependency", "Variable", "VP3", "VP4");
        resultPD.getEdgeDescriptors().add(descriptorVP3VP4PD);

        List<VPMAnalyzerResult> results = new ArrayList<VPMAnalyzerResult>();
        results.add(resultCS);
        results.add(resultPD);

        DefaultVPMAnalyzerService service = new DefaultVPMAnalyzerService();
        service.createGraphEdges(graph, results);

        assertNotNull("Merged graph is null", graph);
        assertEquals("Wrong number of merged edges", 2, graph.getEdgeCount());

        assertNotNull("Missing Edge VP3#VP4 (Maybe wrong id creation)", graph.getEdge("VP3#VP4"));
        assertTrue("Unexpected Edge VP4#VP3 (Wrong id creation)", graph.getEdge("VP4#VP3") == null);

    }

    /**
     * Test method for
     * {@link org.splevo.vpm.analyzer.DefaultVPMAnalyzerService#deriveRefinements(org.splevo.vpm.analyzer.graph.VPMGraph, java.util.List)}
     * .
     *
     * @throws Exception
     *             identifies the test graph could not be loaded.
     */
    @Test
    public void testDeriveRefinements() throws Exception {

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

        // trigger deriving refinements
        DefaultVPMAnalyzerService service = new DefaultVPMAnalyzerService();
        List<Refinement> refinements = service.deriveRefinements(graph, detectionRules);

        // check the result
        assertNotNull("Unexpected null value. At least an empty list is expected", refinements);
        assertThat("Wrong number of refinements", refinements.size(), is(1));

        Refinement refinement = refinements.get(0);
        assertEquals("Wrong number of VPs in refinement", 2, refinement.getVariationPoints().size());
        assertEquals("Wrong refinement type", RefinementType.GROUPING, refinement.getType());
    }

}
