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

import static org.splevo.jamopp.vpm.analyzer.programdependency.tests.TestUtil.assertDependency;
import static org.splevo.jamopp.vpm.analyzer.programdependency.tests.TestUtil.assertNodeCount;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.vpm.analyzer.programdependency.JaMoPPProgramDependencyVPMAnalyzer;
import org.splevo.jamopp.vpm.analyzer.programdependency.references.DependencyType;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.graph.VPMGraph;

/**
 * Unit test to prove the dependency analysis using the robillard reference selector.
 */
public class RobillardSelectorTest {

    private static final String BASE_PATH = "testcode/robillard/";


    protected JaMoPPProgramDependencyVPMAnalyzer analyzer = null;

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUpInfrastructure() {
        TestUtil.initLogging();
    }

    /**
     * Setup the analyzer in the extended mode.
     */
    @Before
    public void setUpAnalyzer() {
        analyzer = TestUtil.configureRobillardAnalyzer(false, false);
    }


    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testClassSupertypeClass() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH + "ClassSupertypeClass/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.ClassSuperTypeClass, 1);
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementCallsMethod() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH + "StatementCallsMethod/basic/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 3);
        assertDependency(result, DependencyType.StatementCallsMethod, 1);
    }

    /**
     * Test the detection of a dependency between a statement calling a constructor.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementCallsMethodConstructor() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH + "StatementCallsMethod/constructor/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.StatementCallsMethod, 1);
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementChecksClassInstanceOf() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH + "StatementChecksClass/InstanceOf/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.StatementChecksClass, 1);
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementChecksClassClassCast() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH + "StatementChecksClass/ClassCast/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.StatementChecksClass, 1);
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementCreatesClass() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH + "StatementCreatesClass/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.StatementCreatesClass, 1);
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementReadsField() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH + "StatementReadsField/basic/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 4);
        assertDependency(result, DependencyType.StatementReadsField, 2);
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementReadsFieldNested() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH + "StatementReadsField/nested/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 10);
        assertDependency(result, DependencyType.StatementReadsField, 5);
    }

    /**
     * Test to detect read dependencies between statements and a field using the this command.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementReadsFieldThis() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH + "StatementReadsField/this/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.StatementReadsField, 1);
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementWritesField() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH + "StatementWritesField/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 4);
        assertDependency(result, DependencyType.StatementWritesField, 2);
    }
}
