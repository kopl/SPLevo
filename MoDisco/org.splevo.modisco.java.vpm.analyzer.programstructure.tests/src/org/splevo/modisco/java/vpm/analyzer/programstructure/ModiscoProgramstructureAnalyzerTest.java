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
 *******************************************************************************/
package org.splevo.modisco.java.vpm.analyzer.programstructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.Before;
import org.junit.Test;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.graph.VPMGraph;

/**
 * Test the program structure analyzer with a modisco specific structure provider to ensure their
 * compatibility.
 */
public class ModiscoProgramstructureAnalyzerTest {

    private Logger logger = Logger.getLogger(ModiscoProgramstructureAnalyzerTest.class);

    /**
     * Set up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Load the VPM and init the VPM graph for the GCD Calculator example.<br>
     * Trigger the ProgramstructureAnalyzer assigned with a Modisco Structure Provider.<br>
     * Test the results.
     *
     * @throws Exception any failure during the process.
     */
    @Test
    public void test() throws Exception {

        MoDiscoJavaProgramStructureVPMAnalyzer analyzer = new MoDiscoJavaProgramStructureVPMAnalyzer();

        VPMGraph vpmGraph = TestUtil.loadGCDVPMGraph();
        int originalNodeCount = vpmGraph.getNodeCount();
        int originalEdgeCount = vpmGraph.getEdgeCount();

        VPMAnalyzerResult result = analyzer.analyze(vpmGraph);

        assertNotNull("The analyzer result must not be null", result);
        assertEquals("The graph's node count should not have been changed.", originalNodeCount, vpmGraph.getNodeCount());
        assertEquals("The graph's edge count should not have been changed.", originalEdgeCount, vpmGraph.getEdgeCount());

        for (VPMEdgeDescriptor edgeDescriptor : result.getEdgeDescriptors()) {
            logger.debug("Edge Sub Label referred SoftwareElement: " + edgeDescriptor.getRelationshipSubLabel());
        }

        assertEquals("Wrong edge descriptor count", 10, result.getEdgeDescriptors().size());

    }

}
