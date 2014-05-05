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
package org.splevo.jamopp.vpm.mergedecider;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.jamopp.vpm.builder.JaMoPPVPMBuilder;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Test case for valid "can be merged"-decisions
 */
public class JaMoPPMergeDeciderTest {

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Test that two variation points describing changed imports in the same class are decided
     * correctly to be mergeable.
     *
     * @throws Exception
     *             identifies a failed test.
     */
    @Test
    public void testDeclaredVariableChanged() throws Exception {

        String basePath = "testcode/DeclaredVariableChanged/";

        VariationPointModel vpm = initializeVariationPointModel(basePath);
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), is(2));

        VariationPoint vp1 = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        VariationPoint vp2 = vpm.getVariationPointGroups().get(1).getVariationPoints().get(0);

        JaMoPPMergeDecider decider = new JaMoPPMergeDecider();
        Boolean mergeDecision = decider.canBeMerged(vp1, vp2);
        assertThat("Wrong merge decision", mergeDecision, is(Boolean.FALSE));
    }

    /**
     * Test that two variation points describing changed imports in the same class are decided
     * correctly to be mergeable.
     *
     * @throws Exception
     *             identifies a failed test.
     */
    @Test
    public void testMergedDecisionImports() throws Exception {

        String basePath = "testcode/import/";

        VariationPointModel vpm = initializeVariationPointModel(basePath);
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), is(2));

        VariationPoint vp1 = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        VariationPoint vp2 = vpm.getVariationPointGroups().get(1).getVariationPoints().get(0);

        JaMoPPMergeDecider decider = new JaMoPPMergeDecider();
        Boolean mergeDecision = decider.canBeMerged(vp1, vp2);
        assertThat("Wrong merge decision", mergeDecision, is(Boolean.TRUE));
    }

    /**
     * Test that two VPs of changed statements with another statement in between can not be merged.
     *
     * @throws Exception
     *             identifies a failed test.
     */
    @Test
    public void testUnmergeableStatements() throws Exception {

        String basePath = "testcode/unmergeable-statements/";

        VariationPointModel vpm = initializeVariationPointModel(basePath);
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), is(2));

        VariationPoint vp1 = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        VariationPoint vp2 = vpm.getVariationPointGroups().get(1).getVariationPoints().get(0);

        assertThat("Wrong number of variants", vp1.getVariants().size(), is(1));
        JaMoPPSoftwareElement jamoppElement = (JaMoPPSoftwareElement) vp1.getVariants().get(0).getImplementingElements().get(0);
        assertThat("Wrong code element.", jamoppElement.getJamoppElement(), instanceOf(LocalVariableStatement.class));

        JaMoPPMergeDecider decider = new JaMoPPMergeDecider();
        Boolean mergeDecision = decider.canBeMerged(vp1, vp2);
        assertThat("Wrong merge decision", mergeDecision, is(Boolean.FALSE));

    }

    /**
     * Test that two VPs of changed statements with another statement in between can not be merged.
     *
     * @throws Exception
     *             identifies a failed test.
     */
    @Test
    public void testMergeableStatements() throws Exception {

        String basePath = "testcode/mergeable-statements/";

        VariationPointModel vpm = initializeVariationPointModel(basePath);
        assertThat("3 VPs = 3 Changed statements", vpm.getVariationPointGroups().size(), is(3));

        VariationPoint vp1 = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        VariationPoint vp2 = vpm.getVariationPointGroups().get(1).getVariationPoints().get(0);
        VariationPoint vp3 = vpm.getVariationPointGroups().get(2).getVariationPoints().get(0);

        JaMoPPMergeDecider decider = new JaMoPPMergeDecider();
        assertThat("Wrong merge decision VP1 / VP2", decider.canBeMerged(vp1, vp2), is(Boolean.TRUE));
        assertThat("Wrong merge decision VP2 / VP3", decider.canBeMerged(vp2, vp3), is(Boolean.TRUE));
        assertThat("Wrong merge decision VP1 / VP3", decider.canBeMerged(vp1, vp3), is(Boolean.FALSE));
    }

    /**
     * Initialize the variation point model to refactor. Extract, Diff and init VPM.
     *
     * @param basePath
     *            The base path of the code to load (must contain sub-directories a and b)
     * @return The initialized VPM based on the source code differences.
     * @throws Exception
     *             Failed to initialize the model.
     */
    private VariationPointModel initializeVariationPointModel(String basePath) throws Exception {
        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        List<String> urisA = Lists.newArrayList(new File(basePath + "a").getAbsolutePath());
        List<String> urisB = Lists.newArrayList(new File(basePath + "b").getAbsolutePath());
        NullProgressMonitor monitor = new NullProgressMonitor();
        ResourceSet setA = extractor.extractSoftwareModel(urisA, monitor, null);
        ResourceSet setB = extractor.extractSoftwareModel(urisB, monitor, null);

        String ignorePackages = buildIgnorePackages();

        Map<String, String> diffOptions = Maps.newLinkedHashMap();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_IGNORE_PACKAGES, ignorePackages);

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        JaMoPPVPMBuilder builder = new JaMoPPVPMBuilder();
        VariationPointModel vpm = builder.buildVPM(comparison, "leading", "integration");
        return vpm;
    }

    /**
     * Build the configuration string for the packages to ignore.
     *
     * @return The regular expressions for the packages to ignore.
     */
    private String buildIgnorePackages() {
        StringBuilder sb = new StringBuilder();
        sb.append("java.*");
        sb.append(System.getProperty("line.separator"));
        String ignorePackages = sb.toString();
        return ignorePackages;
    }

}
