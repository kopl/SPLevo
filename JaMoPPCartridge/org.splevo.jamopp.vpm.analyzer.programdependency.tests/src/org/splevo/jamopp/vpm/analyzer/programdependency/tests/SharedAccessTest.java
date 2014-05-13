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

import static org.splevo.jamopp.vpm.analyzer.programdependency.tests.TestUtil.assertDependencyCount;
import static org.splevo.jamopp.vpm.analyzer.programdependency.tests.TestUtil.assertNodeCount;

import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.graph.VPMGraph;

/**
 * Unit test to prove the mapping options of the diffing.
 */
public class SharedAccessTest {

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
    public void testSharedVariableAccess() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph("testcode/sharedAccess/variableusage/");
        VPMAnalyzerResult result = TestUtil.analyzeExtendedSharedAccess(graph);

        assertNodeCount(graph, 2);
        assertDependencyCount(result, 1);
    }
}
