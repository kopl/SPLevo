/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic - initial API and implementation and initial documentation
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse.optor.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.ImportsFactory;
import org.emftext.language.java.members.MembersFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassImportOPTOR;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Contains the tests for the {@link IfElseStaticConfigClassImportOPTOR} class.
 */
public class IfElseStaticConfigClassImportOPTORTest {
    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Tests whether the canBeApplied method returns true for applicable variation points.
     */
    @Test
    public void testIfCanBeAppliedWithValidVP() {
        Commentable location = ContainersFactory.eINSTANCE.createCompilationUnit();
        Commentable implEl1 = ImportsFactory.eINSTANCE.createClassifierImport();
        Commentable implEl2 = ImportsFactory.eINSTANCE.createClassifierImport();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassImportOPTOR refactoring = new IfElseStaticConfigClassImportOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(true));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have a location
     * that is not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidLocation() {
        Commentable location = ClassifiersFactory.eINSTANCE.createInterface();
        Commentable implEl1 = ImportsFactory.eINSTANCE.createClassifierImport();
        Commentable implEl2 = ImportsFactory.eINSTANCE.createClassifierImport();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassImportOPTOR refactoring = new IfElseStaticConfigClassImportOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have variants
     * with implementing elements that are not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidVariantElements() {
        Commentable location = ContainersFactory.eINSTANCE.createCompilationUnit();
        Commentable implEl1 = ImportsFactory.eINSTANCE.createClassifierImport();
        Commentable implEl2 = MembersFactory.eINSTANCE.createClassMethod();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassImportOPTOR refactoring = new IfElseStaticConfigClassImportOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * Tests whether the refactoring adds a missing import correctly from the integration variant
     * into the base.
     * 
     * @throws Exception
     *             In case of an unexpected exception.
     */
    @Test
    public void testRefactorCaseImportTwoDistinct() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getImportTwoDistinctCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassImportOPTOR refactoring = new IfElseStaticConfigClassImportOPTOR();
        refactoring.refactor(vp);

        CompilationUnit vpLocation = (CompilationUnit) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();

        // location has two classifier imports
        assertThat(vpLocation.getImports().size(), equalTo(2));
        assertThat(vpLocation.getImports().get(0), instanceOf(ClassifierImport.class));
        assertThat(vpLocation.getImports().get(1), instanceOf(ClassifierImport.class));

        // assert correct import classifier
        assertThat(((org.emftext.language.java.imports.ClassifierImport) vpLocation.getImports().get(0))
                .getClassifier().getName(), equalTo("ArrayList"));
        assertThat(((org.emftext.language.java.imports.ClassifierImport) vpLocation.getImports().get(1))
                .getClassifier().getName(), equalTo("LinkedList"));
    }

    /**
     * Tests whether the refactoring merges imports correctly in case of duplicates.
     * 
     * @throws Exception
     *             In case of an unexpected exception.
     */
    @Test
    public void testRefactorCaseImportCommonMultiple() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getImportCommonMultipleCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassImportOPTOR refactoring = new IfElseStaticConfigClassImportOPTOR();
        refactoring.refactor(vp);

        CompilationUnit vpLocation = (CompilationUnit) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getImports().size(), equalTo(4));
        assertThat(vpLocation.getImports().get(0), instanceOf(ClassifierImport.class));
        assertThat(vpLocation.getImports().get(1), instanceOf(ClassifierImport.class));
        assertThat(vpLocation.getImports().get(2), instanceOf(ClassifierImport.class));
        assertThat(vpLocation.getImports().get(3), instanceOf(ClassifierImport.class));
        assertThat(((org.emftext.language.java.imports.ClassifierImport) vpLocation.getImports().get(0))
                .getClassifier().getName(), equalTo("a"));
        assertThat(((org.emftext.language.java.imports.ClassifierImport) vpLocation.getImports().get(1))
                .getClassifier().getName(), equalTo("b"));
        assertThat(((org.emftext.language.java.imports.ClassifierImport) vpLocation.getImports().get(2))
                .getClassifier().getName(), equalTo("c"));
        assertThat(((org.emftext.language.java.imports.ClassifierImport) vpLocation.getImports().get(3))
                .getClassifier().getName(), equalTo("d"));
    }
}
