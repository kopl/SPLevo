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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.vpm.analyzer.programdependency.JaMoPPProgramDependencyVPMAnalyzer;
import org.splevo.jamopp.vpm.mergedecider.JaMoPPMergeDecider;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.mergedecider.MergeDeciderRegistry;
import org.splevo.vpm.analyzer.refinement.BasicDetectionRule;
import org.splevo.vpm.analyzer.refinement.DetectionRule;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementType;

import com.google.common.collect.Lists;

/**
 * Unit test to prove the mapping options of the diffing.
 */
public class MergeDeciderTest {

    private static final String BASE_PATH = "testcode/mergedecider/";

    /** The logger for this class. */
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(MergeDeciderTest.class);

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        TestUtil.initLogging();
    }

    /**
     * Ensure a fresh registry status before each test and when it is done.
     */
    @Before
    @After
    public void clearRegistry() {
        MergeDeciderRegistry.getInstance().clearRegistry();
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStatements() throws Exception {

        VPMGraph graph = TestUtil.prepareVPMGraph(BASE_PATH + "Statements/");

        MergeDeciderRegistry.getInstance().registerElement(new JaMoPPMergeDecider());

        JaMoPPProgramDependencyVPMAnalyzer analyzer = new JaMoPPProgramDependencyVPMAnalyzer();
        VPMAnalyzerResult result = analyzer.analyze(graph);

        DefaultVPMAnalyzerService service = new DefaultVPMAnalyzerService();
        service.createGraphEdges(graph, Lists.newArrayList(result));
        ArrayList<String> labels = Lists.newArrayList(analyzer.getRelationshipLabel());
        BasicDetectionRule rule = new BasicDetectionRule(labels, RefinementType.GROUPING);
        List<DetectionRule> rules = Lists.newArrayList((DetectionRule) rule);
        List<Refinement> refinements = service.deriveRefinements(graph, rules, true, true);

        // return statements are not matched with each other.
        // so we expect each variable decl. statement to be referenced with two differing returns
        // so 4 references in total
        assertThat("Wrong number of refinements", refinements.size(), is(1));
        assertThat("Wrong number of VPs", refinements.get(0).getVariationPoints().size(), is(4));
        assertThat("Wrong refinement type", refinements.get(0).getType(), is(RefinementType.MERGE));
    }
}
