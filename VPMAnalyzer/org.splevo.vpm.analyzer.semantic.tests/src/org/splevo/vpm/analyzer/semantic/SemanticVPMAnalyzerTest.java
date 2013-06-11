package org.splevo.vpm.analyzer.semantic;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.Node;
import org.junit.Before;
import org.junit.Test;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.semantic.lucene.Indexer;


/**
 * Unit-Tests for the SemanticVPNAnalyzer class.
 * 
 * @author Daniel Kojic
 *
 */
public class SemanticVPMAnalyzerTest extends AbstractTest {
    
    /**
     * Test method for {@link org.splevo.vpm.analyzer.programstructure.SemanticVPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)}.
     */
    @Test
    public void testIfAnalyzeReturnsNull() {
    	ArrayList<String> contents = new ArrayList<String>();
    	contents.add("test");
    	VPMGraph graph = getVPMGraph(contents);
        
    	SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
        VPMAnalyzerResult result = analyzer.analyze(graph);
        assertNotNull("The analyzer result must not be null", result);
    }
 
    /**
     * Test method for {@link org.splevo.vpm.analyzer.programstructure.SemanticVPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)}.
     */
    @Test
    public void testIfAnalyzeThrowsIllegalArgumentException() {
    	SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
    	try {
    		analyzer.analyze(null);
    		assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
    }
    
    /**
     * Test method for {@link org.splevo.vpm.analyzer.programstructure.SemanticVPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)}.
     */
    @Test
    public void testIfAnalyzeFindsSimpleLink() {
    	ArrayList<String> contents = new ArrayList<String>();
    	contents.add("var1 var2 var3");
    	contents.add("var1 var2 var3");
    	contents.add("this is an example");
    	contents.add("dummy text");

    	VPMGraph graph = getVPMGraph(contents);
        
    	SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
        VPMAnalyzerResult result = analyzer.analyze(graph);
        assertNotNull("The analyzer result must not be null", result);
        
        // verify results
        int numResults = result.getEdgeDescriptors().size();
        assertTrue("Result should contain 1 link but contains " + numResults, result.getEdgeDescriptors().size() == 1);
        String sourceNodeID = result.getEdgeDescriptors().get(0).getSourceNodeID();
        String targetNodeID = result.getEdgeDescriptors().get(0).getTargetNodeID();
        
        assertTrue("Wrong source node.", sourceNodeID.equals("node0"));
        assertTrue("Wrong target node.", targetNodeID.equals("node1"));
    }
    
    /**
     * Test method for {@link org.splevo.vpm.analyzer.programstructure.SemanticVPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)}.
     */
    @Test
    public void testIfAnalyzeFindsHardLink() {
    	ArrayList<String> contents = new ArrayList<String>();
    	contents.add("calc method tmp vec calc calc var");
    	contents.add("tmp null who method calc calc");
    	contents.add("this is an example");
    	contents.add("dummy text");

    	VPMGraph graph = getVPMGraph(contents);
        
    	SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
        VPMAnalyzerResult result = analyzer.analyze(graph);
        assertNotNull("The analyzer result must not be null", result);
        
        // verify results
        int numResults = result.getEdgeDescriptors().size();
        assertTrue("Result should contain 1 link but contains " + numResults, result.getEdgeDescriptors().size() == 1);
        String sourceNodeID = result.getEdgeDescriptors().get(0).getSourceNodeID();
        String targetNodeID = result.getEdgeDescriptors().get(0).getTargetNodeID();
        
        assertTrue("Expected Nodename: node0 but was " + sourceNodeID, sourceNodeID.equals("node0"));
        assertTrue("Expected Nodename: node1 but was " + targetNodeID, targetNodeID.equals("node1"));
    }
    
    /**
     * Test method for {@link org.splevo.vpm.analyzer.programstructure.SemanticVPMAnalyzer#getAvailableConfigurations()}.
     */
    @Test
    public void testGetAvailableConfigurations() {
    	SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
        assertNotNull("Null available configuration map is not allowed", analyzer.getAvailableConfigurations());
    }

    /**
     * Test method for {@link org.splevo.vpm.analyzer.programstructure.SemanticVPMAnalyzer#getConfigurationLabels()}.
     */
    @Test
    public void testGetConfigurationLabels() {
    	SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
        assertNotNull("Null configuration label map is not allowed", analyzer.getConfigurationLabels());
    }

    /**
     * Test method for {@link org.splevo.vpm.analyzer.programstructure.SemanticVPMAnalyzer#getConfigurations()}.
     */
    @Test
    public void testGetConfigurations() {
    	SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
        assertNotNull("Null configuration map is not allowed", analyzer.getConfigurations());
    }

    /**
     * Test method for {@link org.splevo.vpm.analyzer.programstructure.SemanticVPMAnalyzer#getName()}.
     */
    @Test
    public void testGetName() {
    	SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
        assertNotNull("Null name is not allowed", analyzer.getName());
        assertTrue("Empty name not allowed", analyzer.getName().length() > 0);
    }

    /**
     * Test method for {@link org.splevo.vpm.analyzer.programstructure.SemanticVPMAnalyzer#getRelationshipLabel()}.
     */
    @Test
    public void testGetRelationshipLabel() {
    	SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
        assertNotNull("Null label is not allowed", analyzer.getRelationshipLabel());
        assertTrue("Empty label not allowed", analyzer.getRelationshipLabel().length() > 0);
    }
    
    
    /**
     * Clear the Index before testing.
     */
    @Before
    public void cleanEnvBefore() {
    	try {
			Indexer.getInstance().clearIndex();
		} catch (IOException e) {
			return;
		}
    }
    
    /**
     * Creates a primitive {@link VPMGraph} for testing purposes.
     * 
     * @param content The text to be added to the {@link VPMGraph}.
     * 
     * @return A dummy {@link VPMGraph}.
     */
    private VPMGraph getVPMGraph(List<String> content) {
		VPMGraph graph = new VPMGraph("01", false, true);
		for (int i = 0; i < content.size(); i++) {
			Node node = graph.addNode("node" + i);
			node.addAttribute(VPMGraph.VARIATIONPOINT, new TestVariationPointImpl(content.get(i)));
		}
		return graph;
	}
}
