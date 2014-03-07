/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.vpm.analyzer.semantic;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.splevo.vpm.analyzer.semantic.lucene.Indexer;

/**
 * Unit-Tests for the {@link SemanticVPMAnalyzer} class.
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
