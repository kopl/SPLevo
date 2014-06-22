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
package org.splevo.vpm.analyzer.codelocation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.graph.VPMGraph;

/**
 * Test case for the CodeStructureVPMAnalyzer.
 */
public class CodeLocationVPMAnalyzerTest extends AbstractTest {

    /**
     * Test method for
     * {@link org.splevo.vpm.analyzer.codelocation.CodeLocationVPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)}
     * .
     *
     * @throws Exception
     *             Identifies the initial vpm graph could not be loaded.
     */
    @Test
    public void testAnalyze() throws Exception {
        VPMGraph graph = SPLevoTestUtil.loadGCDVPMGraph();

        int originalNodeCount = graph.getNodeCount();
        int originalEdgeCount = graph.getEdgeCount();

        VPMAnalyzer analyzer = new CodeLocationVPMAnalyzer();
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNotNull("The analyzer result must not be null", result);
        assertEquals("The graph's node count should not have been changed.", originalNodeCount, graph.getNodeCount());
        assertEquals("The graph's edge count should not have been changed.", originalEdgeCount, graph.getEdgeCount());

        assertEquals("Wrong edge descriptor count", 11, result.getEdgeDescriptors().size());
    }

    /**
     * Test method for
     * {@link org.splevo.vpm.analyzer.codelocation.CodeLocationVPMAnalyzer#getName()}.
     */
    @Test
    public void testGetName() {
        VPMAnalyzer anlayzer = new CodeLocationVPMAnalyzer();
        assertNotNull("The analyzer name must not be not.", anlayzer.getName());
    }

    /**
     * Test method for
     * {@link org.splevo.vpm.analyzer.codelocation.CodeLocationVPMAnalyzer#getRelationshipLabel()}.
     */
    @Test
    public void testGetRelationshipLabel() {
        VPMAnalyzer anlayzer = new CodeLocationVPMAnalyzer();
        assertNotNull("The analyzer relationship label must not be not.", anlayzer.getRelationshipLabel());
    }

}
