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
import org.splevo.jamopp.vpm.analyzer.programdependency.ConfigurationBuilder;
import org.splevo.jamopp.vpm.analyzer.programdependency.JaMoPPProgramDependencyVPMAnalyzer;
import org.splevo.jamopp.vpm.analyzer.programdependency.references.ReferenceSelectorRegistry;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.config.ChoiceConfiguration;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;
import org.splevo.vpm.analyzer.graph.VPMGraph;

/**
 * Unit test to prove the dependency analysis using the robillard reference selector.
 */
public class RobillardSelectorTest {

    private static final String BASE_PATH = "testcode/robillard/";

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(RobillardSelectorTest.class);

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
    public void testMethodCall() throws Exception {

        String testBasePath = BASE_PATH + "MethodCall/";

        String relativePathA = testBasePath + "a";
        String relativePathB = testBasePath + "b";
        VPMGraph graph = TestUtil.prepareVPMGraph(relativePathA, relativePathB);

        JaMoPPProgramDependencyVPMAnalyzer analyzer = createRobillardConfiguredAnalyzer();
        VPMAnalyzerResult result = analyzer.analyze(graph);

        for (VPMEdgeDescriptor edgeDescriptor : result.getEdgeDescriptors()) {
            logger.debug("Edge Sub Label: " + edgeDescriptor.getRelationshipSubLabel());
        }

        assertThat("Wrong number of graph nodes", graph.getNodeSet().size(), is(3));
        assertThat("Wrong number of relationships", result.getEdgeDescriptors().size(), is(1));
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testReadField() throws Exception {

        String testBasePath = BASE_PATH + "ReadField/";

        String relativePathA = testBasePath + "a";
        String relativePathB = testBasePath + "b";
        VPMGraph graph = TestUtil.prepareVPMGraph(relativePathA, relativePathB);

        JaMoPPProgramDependencyVPMAnalyzer analyzer = createRobillardConfiguredAnalyzer();
        VPMAnalyzerResult result = analyzer.analyze(graph);

        for (VPMEdgeDescriptor edgeDescriptor : result.getEdgeDescriptors()) {
            logger.debug("Edge Sub Label: " + edgeDescriptor.getRelationshipSubLabel());
        }

        assertThat("Wrong number of graph nodes", graph.getNodeSet().size(), is(4));
        assertThat("Wrong number of relationships", result.getEdgeDescriptors().size(), is(2));
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testWriteField() throws Exception {

        String testBasePath = BASE_PATH + "WriteField/";

        String relativePathA = testBasePath + "a";
        String relativePathB = testBasePath + "b";
        VPMGraph graph = TestUtil.prepareVPMGraph(relativePathA, relativePathB);

        JaMoPPProgramDependencyVPMAnalyzer analyzer = createRobillardConfiguredAnalyzer();
        VPMAnalyzerResult result = analyzer.analyze(graph);

        for (VPMEdgeDescriptor edgeDescriptor : result.getEdgeDescriptors()) {
            logger.debug("Edge Sub Label: " + edgeDescriptor.getRelationshipSubLabel());
        }

        assertThat("Wrong number of graph nodes", graph.getNodeSet().size(), is(4));
        assertThat("Wrong number of relationships", result.getEdgeDescriptors().size(), is(2));
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testNewConstructorCall() throws Exception {

        String testBasePath = BASE_PATH + "NewConstructorCall/";

        String relativePathA = testBasePath + "a";
        String relativePathB = testBasePath + "b";
        VPMGraph graph = TestUtil.prepareVPMGraph(relativePathA, relativePathB);

        JaMoPPProgramDependencyVPMAnalyzer analyzer = createRobillardConfiguredAnalyzer();
        VPMAnalyzerResult result = analyzer.analyze(graph);

        for (VPMEdgeDescriptor edgeDescriptor : result.getEdgeDescriptors()) {
            logger.debug("Edge Sub Label: " + edgeDescriptor.getRelationshipSubLabel());
        }

        assertThat("Wrong number of graph nodes", graph.getNodeSet().size(), is(2));
        assertThat("Wrong number of relationships", result.getEdgeDescriptors().size(), is(1));
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testInstanceOf() throws Exception {

        String testBasePath = BASE_PATH + "InstanceOf/";

        String relativePathA = testBasePath + "a";
        String relativePathB = testBasePath + "b";
        VPMGraph graph = TestUtil.prepareVPMGraph(relativePathA, relativePathB);

        JaMoPPProgramDependencyVPMAnalyzer analyzer = createRobillardConfiguredAnalyzer();
        VPMAnalyzerResult result = analyzer.analyze(graph);

        for (VPMEdgeDescriptor edgeDescriptor : result.getEdgeDescriptors()) {
            logger.debug("Edge Sub Label: " + edgeDescriptor.getRelationshipSubLabel());
        }

        assertThat("Wrong number of graph nodes", graph.getNodeSet().size(), is(2));
        assertThat("Wrong number of relationships", result.getEdgeDescriptors().size(), is(1));
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testClassCast() throws Exception {

        String testBasePath = BASE_PATH + "ClassCast/";

        String relativePathA = testBasePath + "a";
        String relativePathB = testBasePath + "b";
        VPMGraph graph = TestUtil.prepareVPMGraph(relativePathA, relativePathB);

        JaMoPPProgramDependencyVPMAnalyzer analyzer = createRobillardConfiguredAnalyzer();
        VPMAnalyzerResult result = analyzer.analyze(graph);

        for (VPMEdgeDescriptor edgeDescriptor : result.getEdgeDescriptors()) {
            logger.debug("Edge Sub Label: " + edgeDescriptor.getRelationshipSubLabel());
        }

        assertThat("Wrong number of graph nodes", graph.getNodeSet().size(), is(2));
        assertThat("Wrong number of relationships", result.getEdgeDescriptors().size(), is(1));
    }

    private JaMoPPProgramDependencyVPMAnalyzer createRobillardConfiguredAnalyzer() {
        JaMoPPProgramDependencyVPMAnalyzer analyzer = new JaMoPPProgramDependencyVPMAnalyzer();

        VPMAnalyzerConfigurationSet configurations = analyzer.getConfigurations();
        String groupId = ConfigurationBuilder.CONFIG_GROUP_DEPENDENCIES;
        String configId = ConfigurationBuilder.CONFIG_ID_REFERENCE_SELECTOR;
        ChoiceConfiguration configuration = (ChoiceConfiguration) configurations.getConfiguration(groupId, configId);

        configuration.setCurrentValue(ReferenceSelectorRegistry.ROBILLARD_SELECTOR);

        return analyzer;
    }
}
