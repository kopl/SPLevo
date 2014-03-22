/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.vpm.analyzer.refinement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMAnalyzerTestUtil;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.codelocation.CodeLocationVPMAnalyzer;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.mergedecider.MergeDecider;
import org.splevo.vpm.analyzer.mergedecider.MergeDeciderRegistry;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * Unit test to detect merge able VPs.
 */
public class MergeDetectionTest {

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Ensure the {@link MergeDeciderRegistry} is cleaned before each test.
     */
    @Before
    public void before() {
        MergeDeciderRegistry.clearRegistry();
    }

    /**
     * Ensure the {@link MergeDeciderRegistry} is cleaned after each test.
     */
    @After
    public void after() {
        MergeDeciderRegistry.clearRegistry();
    }

    /**
     * Test to build a vpm model from a diffing result.
     *
     * <strong>Test Input</strong><br>
     * VPM Graph with 3 VPs that relate two each other.<br>
     * 2 of them mergeable, 1 not<br>
     *
     * <strong>Test Result</strong><br>
     * Two refinements: - a merge containing VP1 and VP2 - a group containing VP1 and VP3
     *
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testMergeDetection() throws Exception {

        VPMGraph graph = new VPMGraph("VPMGraph");
        VPMAnalyzerTestUtil.createNodeWithVP(graph, "VP1");
        VPMAnalyzerTestUtil.createNodeWithVP(graph, "VP2");
        VPMAnalyzerTestUtil.createNodeWithVP(graph, "VP3");

        VPMAnalyzerResult resultCS = new VPMAnalyzerResult(new CodeLocationVPMAnalyzer());
        resultCS.getEdgeDescriptors().add(new VPMEdgeDescriptor("CodeStructure", "Method", "VP1", "VP2"));
        resultCS.getEdgeDescriptors().add(new VPMEdgeDescriptor("CodeStructure", "Method", "VP2", "VP3"));
        resultCS.getEdgeDescriptors().add(new VPMEdgeDescriptor("CodeStructure", "Method", "VP1", "VP3"));

        List<VPMAnalyzerResult> results = new ArrayList<VPMAnalyzerResult>();
        results.add(resultCS);

        DefaultVPMAnalyzerService service = new DefaultVPMAnalyzerService();
        service.createGraphEdges(graph, results);

        MergeDecider mergeDecider = mock(MergeDecider.class);
        OngoingStubbing<Boolean> methodCallStub = when(mergeDecider.canBeMerged(any(VariationPoint.class),
                any(VariationPoint.class)));
        methodCallStub = methodCallStub.thenReturn(Boolean.TRUE);
        methodCallStub = methodCallStub.thenReturn(Boolean.FALSE);
        methodCallStub.thenReturn(Boolean.FALSE);
        MergeDeciderRegistry.registerMergeDecider(mergeDecider);

        DetectionRule rule = new BasicDetectionRule(Lists.newArrayList("CodeStructure"), RefinementType.GROUPING);
        List<Refinement> refinements = service.deriveRefinements(graph, Lists.newArrayList(rule));

        assertThat("Wrong number of refinements", refinements.size(), is(2));
        assertThat("Wrong type of first refinement", refinements.get(0).getType(), is(RefinementType.MERGE));
        assertThat("Wrong type of second refinement", refinements.get(1).getType(), is(RefinementType.GROUPING));
    }

    /**
     * Test that if all variation points of a refinement can be merged no additional grouping is
     * created.
     *
     * <strong>Test Input</strong><br>
     * VPM Graph with 3 VPs that relate two each other.<br>
     * All of them mergeable<br>
     *
     * <strong>Test Result</strong><br>
     * One refinement: - a merge containing VP1, VP2 and VP3
     *
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testMergeOnly() throws Exception {

        VPMGraph graph = new VPMGraph("VPMGraph");
        VPMAnalyzerTestUtil.createNodeWithVP(graph, "VP1");
        VPMAnalyzerTestUtil.createNodeWithVP(graph, "VP2");
        VPMAnalyzerTestUtil.createNodeWithVP(graph, "VP3");

        VPMAnalyzerResult resultCS = new VPMAnalyzerResult(new CodeLocationVPMAnalyzer());
        resultCS.getEdgeDescriptors().add(new VPMEdgeDescriptor("CodeStructure", "Method", "VP1", "VP2"));
        resultCS.getEdgeDescriptors().add(new VPMEdgeDescriptor("CodeStructure", "Method", "VP2", "VP3"));
        resultCS.getEdgeDescriptors().add(new VPMEdgeDescriptor("CodeStructure", "Method", "VP1", "VP3"));

        List<VPMAnalyzerResult> results = new ArrayList<VPMAnalyzerResult>();
        results.add(resultCS);

        DefaultVPMAnalyzerService service = new DefaultVPMAnalyzerService();
        service.createGraphEdges(graph, results);

        MergeDecider mergeDecider = mock(MergeDecider.class);
        OngoingStubbing<Boolean> methodCallStub = when(mergeDecider.canBeMerged(any(VariationPoint.class),
                any(VariationPoint.class)));
        methodCallStub = methodCallStub.thenReturn(Boolean.TRUE);
        methodCallStub.thenReturn(Boolean.TRUE);
        MergeDeciderRegistry.registerMergeDecider(mergeDecider);

        DetectionRule rule = new BasicDetectionRule(Lists.newArrayList("CodeStructure"), RefinementType.GROUPING);
        List<Refinement> refinements = service.deriveRefinements(graph, Lists.newArrayList(rule));

        assertThat("Wrong number of refinements", refinements.size(), is(1));
        assertThat("Wrong type of refinement", refinements.get(0).getType(), is(RefinementType.MERGE));
    }

}
