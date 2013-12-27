/*******************************************************************************
 * Copyright (c) 2013
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.vpm.analyzer.programstructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.splevo.modisco.java.vpm.analyzer.programstructure.MoDiscoJavaProgramStructureProvider;
import org.splevo.tests.AbstractTest;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.graph.VPMGraph;

/**
 * Test case for the ProgramDependencyVPMAnalyzer.
 *
 * @author Benjamin Klatt
 */
public class ProgramStructureVPMAnalyzerTest extends AbstractTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(ProgramStructureVPMAnalyzerTest.class);

    /** An instance of the analyzer for general tests. */
    private final ProgramStructureVPMAnalyzer analyzer = new ProgramStructureVPMAnalyzer();

    /**
     * Test method for
     * {@link org.splevo.vpm.analyzer.programstructure.ProgramStructureVPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)}
     * .
     *
     * It actively registers the MoDiscoProgramStructureProvider to enable the test run as a regular
     * JUnit test without starting the OSGi Bundles.
     *
     * @throws Exception
     *             Identifies the test input graph could not be read.
     */
    @Test
    public void testAnalyze() throws Exception {

        Activator.getProgramStructureProviders().add(new MoDiscoJavaProgramStructureProvider());

        VPMGraph graph = SPLevoTestUtil.loadGCDVPMGraph();

        int originalNodeCount = graph.getNodeCount();
        int originalEdgeCount = graph.getEdgeCount();

        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNotNull("The analyzer result must not be null", result);
        assertEquals("The graph's node count should not have been changed.", originalNodeCount, graph.getNodeCount());
        assertEquals("The graph's edge count should not have been changed.", originalEdgeCount, graph.getEdgeCount());

        for (VPMEdgeDescriptor edgeDescriptor : result.getEdgeDescriptors()) {
            logger.debug("Edge Sub Label referred SoftwareElement: " + edgeDescriptor.getRelationshipSubLabel());
        }

        assertEquals("Wrong edge descriptor count", 10, result.getEdgeDescriptors().size());

    }

    /**
     * Test method for
     * {@link org.splevo.vpm.analyzer.programstructure.ProgramStructureVPMAnalyzer#getConfigurations()}
     * .
     */
    @Test
    public void testGetConfigurations() {
        assertNotNull("Null for configurations is not allowed", analyzer.getConfigurations());
    }

    /**
     * Test method for
     * {@link org.splevo.vpm.analyzer.programstructure.ProgramStructureVPMAnalyzer#getName()}.
     */
    @Test
    public void testGetName() {
        assertNotNull("Null name is not allowed", analyzer.getName());
        assertTrue("Empty name not allowed", analyzer.getName().length() > 0);
    }

    /**
     * Test method for
     * {@link org.splevo.vpm.analyzer.programstructure.ProgramStructureVPMAnalyzer#getRelationshipLabel()}
     * .
     */
    @Test
    public void testGetRelationshipLabel() {
        assertNotNull("Null label is not allowed", analyzer.getRelationshipLabel());
        assertTrue("Empty label not allowed", analyzer.getRelationshipLabel().length() > 0);
    }

}
