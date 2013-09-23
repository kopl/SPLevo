package org.splevo.vpm.analyzer.semantic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.semantic.lucene.Indexer;


/**
 * Unit-Tests for the {@link SemanticVPMAnalyzer} class.
 * 
 * @author Daniel Kojic
 *
 */
public class SemanticVPMAnalyzerTest extends AbstractTest {
  
    /**
     * Test method for 
     * {@link org.splevo.vpm.analyzer.programstructure.SemanticVPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)}.
     */
    @Test
    public void testIfAnalyzeThrowsIllegalArgumentException() {
    	SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
    	try {
    		analyzer.analyze(null);
    		assertTrue("Analyze should throw IllegalArgumentException for null argument.", false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
    }
    
    /**
     * Test method for 
     * {@link org.splevo.vpm.analyzer.semantic.SemanticVPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)}.
     * @throws Exception Thrown if there was an error while reading graph.
     */
    @Test
    public void testAnalyzeOverallSim() throws Exception {
    	// Initialization
    	VPMGraph graph = SPLevoTestUtil.loadGCDVPMGraph();
    	SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
    	int originalNodeCount = graph.getNodeCount();
        int originalEdgeCount = graph.getEdgeCount();
    	Map<String, Object> availableConfigurations = analyzer.getConfigurations();
    	
    	setConfig(availableConfigurations, "", false, false, true, false, 1.d, 0.3d, 0.3d, 5);
    	
    	// Execution
    	VPMAnalyzerResult results = analyzer.analyze(graph);
    	
    	// Verification
    	assertNotNull("The analyzer result must not be null", results);
    	int size = results.getEdgeDescriptors().size();
        assertEquals("The graph's node count should not have been changed.", originalNodeCount, graph.getNodeCount());
        assertEquals("The graph's edge count should not have been changed.", originalEdgeCount, graph.getEdgeCount());
        assertEquals("Failure in Overall-Sim.-Search: Edge count of 10 expected but was " + size + ".", 10, size);
    }

    /**
     * Test method for 
     * {@link org.splevo.vpm.analyzer.semantic.SemanticVPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)}.
     * @throws Exception Thrown if there was an error while reading graph.
     */
    @Test
    public void testAnalyzeRareTerm() throws Exception {
    	// Initialization
    	VPMGraph graph = SPLevoTestUtil.loadGCDVPMGraph();
    	SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
    	int originalNodeCount = graph.getNodeCount();
    	int originalEdgeCount = graph.getEdgeCount();
    	Map<String, Object> availableConfigurations = analyzer.getConfigurations();
    	
    	setConfig(availableConfigurations, "", false, true, false, false, 1.d, 0.21d, 0.3d, 5);
    	
    	// Execution
    	VPMAnalyzerResult results = analyzer.analyze(graph);
    	
    	// Verification
    	assertNotNull("The analyzer result must not be null", results);
    	int size = results.getEdgeDescriptors().size();
    	assertEquals("The graph's node count should not have been changed.", originalNodeCount, graph.getNodeCount());
    	assertEquals("The graph's edge count should not have been changed.", originalEdgeCount, graph.getEdgeCount());
    	assertEquals("Failure in Rare-Term-Search: Edge count of 10 expected but was " + size + ".", 10, size);
    }

    /**
     * Test method for 
     * {@link org.splevo.vpm.analyzer.semantic.SemanticVPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)}.
     * @throws Exception Thrown if there was an error while reading graph.
     */
    @Test
    public void testAnalyzeTopNTerm() throws Exception {
    	// Initialization
    	VPMGraph graph = SPLevoTestUtil.loadGCDVPMGraph();
    	SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
    	int originalNodeCount = graph.getNodeCount();
    	int originalEdgeCount = graph.getEdgeCount();
    	Map<String, Object> availableConfigurations = analyzer.getConfigurations();
    	
    	setConfig(availableConfigurations, "", false, false, false, true, 1.d, 0.21d, 1.d, 1);
    	
    	// Execution
    	VPMAnalyzerResult results = analyzer.analyze(graph);
    	
    	// Verification
    	assertNotNull("The analyzer result must not be null", results);
    	int size = results.getEdgeDescriptors().size();
    	assertEquals("The graph's node count should not have been changed.", originalNodeCount, graph.getNodeCount());
    	assertEquals("The graph's edge count should not have been changed.", originalEdgeCount, graph.getEdgeCount());
    	assertEquals("Failure in Top-N-Term-Search: Edge count of 10 expected but was " + size + ".", 10, size);
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
     * Clear the Index before testing.
     */
    @After
    public void cleanEnvAfter() {
    	try {
    		Indexer.getInstance().clearIndex();
    	} catch (IOException e) {
    		return;
    	}
    }
    
    /**
     * Helper method to set configuration.
     * 
     * @param config The config map.
     * @param stopWords The Stop-Words.
     * @param matchComments Configuration.
     * @param useRareTermFinder Configuration.
     * @param useOverallSimilarityFinder Configuration.
     * @param useTopNFinder Configuration.
     * @param minSimilarity Configuration.
     * @param maxPercentage Configuration.
     * @param leastDocFreq Configuration.
     * @param n Configuration.
     */
    private void setConfig(Map<String, Object> config, 
    		String stopWords,
    		boolean matchComments, 
			boolean useRareTermFinder, 
			boolean useOverallSimilarityFinder, 
			boolean useTopNFinder, 
			double minSimilarity, 
			double maxPercentage,
			double leastDocFreq,
			int n) {
		for (String key : config.keySet()) {
			if (key.equals(ConfigDefaults.LABEL_STOP_WORDS)) {
				config.put(key, stopWords);
			}
			if (key.equals(ConfigDefaults.LABEL_INCLUDE_COMMENTS)) {
				config.put(key, matchComments);
			}
			if (key.equals(ConfigDefaults.LABEL_LEAST_DOC_FREQ)) {
				config.put(key, leastDocFreq);
			}
			if (key.equals(ConfigDefaults.LABEL_MINIMUM_SIMILARITY)) {
				config.put(key, minSimilarity);
			}
			if (key.equals(ConfigDefaults.LABEL_N)) {
				config.put(key, n);
			}
			if (key.equals(ConfigDefaults.LABEL_RARE_TERM_FINDER_MAX_PERCENTAGE)) {
				config.put(key, maxPercentage);
			}
			if (key.equals(ConfigDefaults.LABEL_USE_OVERALL_SIMILARITY_FINDER)) {
				config.put(key, useOverallSimilarityFinder);
			}
			if (key.equals(ConfigDefaults.LABEL_USE_RARE_FINDER)) {
				config.put(key, useRareTermFinder);
			}
			if (key.equals(ConfigDefaults.LABEL_USE_TOP_N_TERM_FINDER)) {
				config.put(key, useTopNFinder);
			}
		}
	}
}
