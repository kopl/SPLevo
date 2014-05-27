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
import org.splevo.jamopp.vpm.analyzer.programdependency.references.DependencyType;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.graph.VPMGraph;

/**
 * Unit test to prove the dependency analysis using the extended Robillard dependency selector mode.
 *
 * <strong>Note:</strong><br>
 * The test is derived from the basic robillard selector test as it must pass the same tests with
 * the analyzer configured in extended mode. For this, it also overwrites the analyzer
 * initialization.
 *
 * The extended mode must pass the same tests as the basic Robillard test plus additional types of
 * dependencies.
 */
public class RobillardExtendedSelectorTest extends RobillardSelectorTest {

    private static final String BASE_PATH_EXTENDED = "testcode/robillardExtended/";

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
    @Override
    public void setUpAnalyzer() {
        analyzer = TestUtil.configureRobillardAnalyzer(true, false);
    }

    /**
     * Test the dependency detection for a field declaration calling an added method to initialize
     * it's value.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testFieldCallsMethod() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "FieldCallsMethod/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.FieldCallsMethod, 1);
    }

    /**
     * Test the dependency detection for a field declaration creating an instance of an added class
     * to initialize it's value.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testFieldCreatesClass() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "FieldCreatesClass/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.FieldCreatesClass, 1);
    }

    /**
     * Test the dependency detection between a field and an class import declaration.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testFieldImportClass() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "FieldImportClass/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 3);
        assertDependency(result, DependencyType.FieldImportClass, 2);
    }

    /**
     * Test the dependency detection between a field and an enumeration import declaration.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testFieldImportEnumeration() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "FieldImportEnumeration/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 3);
        assertDependency(result, DependencyType.FieldImportEnumeration, 2);
    }

    /**
     * Test the dependency detection between a field and an interface import declaration.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testFieldImportInterface() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "FieldImportInterface/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.FieldImportInterface, 1);
    }

    /**
     * Test the dependency detection for a field reading another field (e.g. instance field reads
     * constant etc.)
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testFieldReadsField() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "FieldReadsField/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.FieldReadsField, 1);
    }

    /**
     * Test the dependency detection for a field typed with a specific class
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testFieldTypedClass() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "FieldTypedClass/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.FieldTypedClass, 1);
    }

    /**
     * Test the dependency detection for a field typed with an enumeration
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testFieldTypedEnumeration() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "FieldTypedEnumeration/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.FieldTypedEnumeration, 1);
    }

    /**
     * Test the dependency detection for a field typed with an interface
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testFieldTypedInterface() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "FieldTypedInterface/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.FieldTypedInterface, 1);
    }

    /**
     * Test the dependency detection for a class having a specific interface as super type.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testInterfaceSuperTypeClass() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "InterfaceSuperTypeClass/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.InterfaceSuperTypeClass, 1);
    }

    /**
     * Test the dependency detection for an enumeration having an interface as super type
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testInterfaceSuperTypeEnumeration() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "InterfaceSuperTypeEnumeration/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.InterfaceSuperTypeEnumeration, 1);
    }

    /**
     * Test the dependency detection for an interface extending another interface
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testInterfaceSuperTypeInterface() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "InterfaceSuperTypeInterface/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.InterfaceSuperTypeInterface, 1);
    }

    /**
     * Test the dependency detection for a method with an class return type matching an import.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testMethodImportClass() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "MethodImportClass/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.MethodImportClass, 1);
    }

    /**
     * Test the dependency detection for a method with an enumeration return type matching an
     * import.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testMethodImportEnumeration() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "MethodImportEnumeration/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.MethodImportEnumeration, 1);
    }

    /**
     * Test the dependency detection for a method with an interface return type matching an import.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testMethodImportInterface() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "MethodImportInterface/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.MethodImportInterface, 1);
    }

    /**
     * Test the dependency detection for a method with a return type matching an added class.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testMethodTypedClass() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "MethodTypedClass/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.MethodTypedClass, 1);
    }

    /**
     * Test the dependency detection for a method with a return type matching an added enumeration.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testMethodTypedEnumeration() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "MethodTypedEnumeration/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.MethodTypedEnumeration, 1);
    }

    /**
     * Test the dependency detection for a method with a return type matching an added interface.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testMethodTypedInterface() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "MethodTypedInterface/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.MethodTypedInterface, 1);
    }

    /**
     * Test the dependency detection for a changed method's parameter typed with a changed class.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testParameterTypedClass() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "ParameterTypedClass/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.ParameterTypedClass, 1);
    }

    /**
     * Test the dependency detection for a parameter typed with an added enumeration.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testParameterTypedEnumeration() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "ParameterTypedEnumeration/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.ParameterTypedEnumeration, 1);
    }

    /**
     * Test the dependency detection for a parameter typed with an added interface.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testParameterTypedInterface() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "ParameterTypedInterface/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.ParameterTypedInterface, 1);
    }

    /**
     * Test the dependency detection for a statement casting an object to a changed enumeration.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testStatementChecksEnumerationCast() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "StatementChecksEnumeration/Cast/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.StatementChecksEnumeration, 1);
    }

    /**
     * Test the dependency detection for a statement checking an object if it is an instance of a
     * changed enumeration.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testStatementChecksEnumerationInstanceOf() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "StatementChecksEnumeration/InstanceOf/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.StatementChecksEnumeration, 1);
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementChecksInterfaceInstanceOf() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "StatementChecksInterface/InstanceOf/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.StatementChecksInterface, 1);
    }

    /**
     * Test to detect dependencies between method signature and an import declaration referring to
     * the same type.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementImportClass() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "StatementImportClass/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.StatementImportClass, 1);
    }

    /**
     * Test to detect dependencies between method signature and an import declaration referring to
     * the same type.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementImportEnumeration() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "StatementImportEnumeration/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.StatementImportEnumeration, 1);
    }

    /**
     * Test to detect dependencies between method signature and an import declaration referring to
     * the same type.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementImportInterface() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "StatementImportInterface/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.StatementImportInterface, 1);
    }

    /**
     * Test statements modifying variables (regular and additional variables).
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementModifiesField() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "StatementModifiesField/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 4);
        assertDependency(result, DependencyType.StatementModifiesField, 2);
    }

    /**
     * Test statements modifying variables (regular and additional variables).
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementModifiesVariable() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "StatementModifiesVariable/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 8);
        assertDependency(result, DependencyType.StatementModifiesVariable, 4);
    }

    /**
     * Test to detect dependencies between statements and an import declaration referring to the
     * same type.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementReadsVariable() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "StatementReadsVariable/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 8);
        assertDependency(result, DependencyType.StatementReadsVariable, 4);
    }

    /**
     * Test the dependency detection for a statement declaring one or more variables typed with a
     * changed class.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testStatementTypedClass() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "StatementTypedClass/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.StatementTypedClass, 1);
    }

    /**
     * Test the dependency detection for a statement declaring one or more variables typed with an
     * added enumeration.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testStatementTypedEnumeration() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "StatementTypedEnumeration/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.StatementTypedEnumeration, 1);
    }

    /**
     * Test the dependency detection for a statement declaring one or more variables typed with an
     * added interface.
     *
     * @throws Exception
     *             Identifies a failed processing.
     */
    @Test
    public void testStatementTypedInterface() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "StatementTypedInterface/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.StatementTypedInterface, 1);
    }

    /**
     * Test method writing a new value to a variable.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementWritesVariable() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "StatementWritesVariable/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 4);
        assertDependency(result, DependencyType.StatementWritesVariable, 2);
    }

    /**
     * Test to detect dependency between a new interface and a cast to it.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatementChecksInterfaceCast() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH_EXTENDED + "StatementChecksInterface/Cast/");
        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNodeCount(graph, 2);
        assertDependency(result, DependencyType.StatementChecksInterface, 1);
    }
}
