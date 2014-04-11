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
package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.vpm.analyzer.programdependency.JaMoPPProgramDependencyVPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.graph.VPMGraph;

/**
 * Unit test to prove the mapping options of the diffing.
 */
public class VariableUsageTest {

    private static final String BASE_PATH = "testcode/variableusage/";

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(VariableUsageTest.class);

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        TestUtil.initLogging();
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testVariableUsage() throws Exception {

        String relativePathA = BASE_PATH + "a";
        String relativePathB = BASE_PATH + "b";
        VPMGraph graph = TestUtil.prepareVPMGraph(relativePathA, relativePathB);

        JaMoPPProgramDependencyVPMAnalyzer analyzer = new JaMoPPProgramDependencyVPMAnalyzer();
        VPMAnalyzerResult result = analyzer.analyze(graph);

        for (VPMEdgeDescriptor edgeDescriptor : result.getEdgeDescriptors()) {
            logger.debug("Edge Sub Label: " + edgeDescriptor.getRelationshipSubLabel());
        }

        assertThat("Wrong number of graph nodes", graph.getNodeSet().size(), is(2));
        assertThat("Wrong number of dependencies", result.getEdgeDescriptors().size(), is(1));

    }
}
