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
package org.splevo.refactoring.ifelsebasic;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.jamopp.vpm.builder.JaMoPPVPMBuilder;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementFactory;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.refinement.VPMRefinementService;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Test the if else refactoring implementation.
 */
public class IfElseBasicRefactoringTest {

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Test to build a vpm model from a diffing result.
     *
     * <strong>Test Input</strong><br>
     * Two classes with a differing import (BigInteger vs. BigDecimal)
     *
     * <strong>Test Result</strong><br>
     * The compilation unit of the leading variant (the location of the variation point) must
     * contain two imports (BigInteger and BigDecimal)
     *
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void test() throws Exception {

        String basePath = "testcode/";

        VariationPointModel vpm = initializeVariationPointModel(basePath);
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), is(2));

        // diffing imports are expected to be separate add and deletes, so merge them.
        Refinement refinement = RefinementFactory.eINSTANCE.createRefinement();
        refinement.setType(RefinementType.MERGE);
        refinement.getVariationPoints().add(vpm.getVariationPointGroups().get(0).getVariationPoints().get(0));
        refinement.getVariationPoints().add(vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        VPMRefinementService refinementService = new VPMRefinementService();
        refinementService.applyRefinements(Lists.newArrayList(refinement), vpm);
        assertThat("Wrong number of refined vpm groups", vpm.getVariationPointGroups().size(), is(1));


        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);

        IfElseBasicRefactoring refactoring = new IfElseBasicRefactoring();
        boolean applicable = refactoring.getSupportedVariabilityRealizationTechnique().canBeAppliedTo(variationPoint);
        assertThat("Refactoring not applicable to VP", applicable, is(true));

        refactoring.refactor(variationPoint);

        SoftwareElement locationElement = variationPoint.getLocation();
        if (locationElement instanceof JaMoPPSoftwareElement) {
            JaMoPPSoftwareElement jamoppLocationElement = (JaMoPPSoftwareElement) locationElement;
            Commentable jamoppElement = jamoppLocationElement.getJamoppElement();
            assertThat("Location is not a CompilationUnit", jamoppElement, instanceOf(CompilationUnit.class));

            CompilationUnit cu = (CompilationUnit) jamoppElement;
            assertThat("Wrong number of imports after refactoring", cu.getImports().size(), is(2));
        } else {
            fail("Unexpected Variation Point Location");
        }
    }

    /**
     * Initialize the variation point model to refactor. Extract, Diff and init VPM.
     *
     * @param basePath
     *            The base path of the code to load (must contain subdirectories a and b)
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
