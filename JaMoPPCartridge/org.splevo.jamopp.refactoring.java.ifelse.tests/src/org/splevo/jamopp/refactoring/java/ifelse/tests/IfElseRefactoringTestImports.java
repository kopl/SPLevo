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
 *    Daniel Kojic
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.emftext.language.java.containers.CompilationUnit;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseRefactoring;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Test the java if-else refactoring implementation for XOR variability.
 */
public class IfElseRefactoringTestImports {

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Tests whether the refactoring can be applied.
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
    public void testCanBeAppliedForImportMerge() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoring();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("MergeImports");
        RefactoringTestUtil.performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0)
                .getVariationPoints().get(0), vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertThat("Wrong number of refined vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        assertThat(((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement(),
                instanceOf(CompilationUnit.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseXOR(variationPoint);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * Tests whether the leading compilation unit the correct number of refactorings.
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
    public void testMergeImports() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoring();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("MergeImports");
        RefactoringTestUtil.performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0)
                .getVariationPoints().get(0), vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertThat("Wrong number of refined vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        assertThat(((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement(),
                instanceOf(CompilationUnit.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseXOR(variationPoint);

        // excute test
        objectUnderTest.refactor(variationPoint);

        // verification
        CompilationUnit cu = (CompilationUnit) ((JaMoPPSoftwareElement) variationPoint.getLocation())
                .getJamoppElement();
        assertThat("CompilationUnit should have two imports.", cu.getImports().size(), equalTo(2));
    }
}
