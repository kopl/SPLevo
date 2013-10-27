package org.splevo.vpm.analyzer.semantic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.config.BooleanConfiguration;
import org.splevo.vpm.analyzer.config.NumericConfiguration;
import org.splevo.vpm.analyzer.config.StringConfiguration;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.semantic.extensionpoint.Activator;
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
	 * {@link org.splevo.vpm.analyzer.programstructure.SemanticVPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)}
	 * .
	 */
	@Test
	public void testIfAnalyzeThrowsIllegalArgumentException() {
		SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
		try {
			analyzer.analyze(null);
			assertTrue(
					"Analyze should throw IllegalArgumentException for null argument.",
					false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for
	 * {@link org.splevo.vpm.analyzer.programstructure.SemanticVPMAnalyzer#getConfigurations()}
	 * .
	 */
	@Test
	public void testGetConfigurations() {
		SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
		assertNotNull("Null configuration map is not allowed",
				analyzer.getConfigurations());
	}

	/**
	 * Test method for
	 * {@link org.splevo.vpm.analyzer.programstructure.SemanticVPMAnalyzer#getName()}
	 * .
	 */
	@Test
	public void testGetName() {
		SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
		assertNotNull("Null name is not allowed", analyzer.getName());
		assertTrue("Empty name not allowed", analyzer.getName().length() > 0);
	}

	/**
	 * Test method for
	 * {@link org.splevo.vpm.analyzer.programstructure.SemanticVPMAnalyzer#getRelationshipLabel()}
	 * .
	 */
	@Test
	public void testGetRelationshipLabel() {
		SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
		assertNotNull("Null label is not allowed",
				analyzer.getRelationshipLabel());
		assertTrue("Empty label not allowed", analyzer.getRelationshipLabel()
				.length() > 0);
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
}
