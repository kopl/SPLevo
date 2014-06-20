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
     * <strong>Description</strong><br/>
     * Input is a variation point that is tailored to this refactoring.
     * 
     * <strong>Input</strong><br/>
     * Variation point Characteristics:<br/>
     * <ul>
     * <li>Binding Time: Compile</li>
     * <li>Variability Type: OPTOR</li>
     * <li>Extensible: No</li>
     * </ul>
     * 
     * Variation point Internals:<br/>
     * <ul>
     * <li>Location: Class</li>
     * <li>Variant 1: Has a constructor</li>
     * <li>Variant 1: Has a constructor</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>true</code>
     */
    @Test
    public void testIfCanBeAppliedWithValidVP() {
        Commentable location = ContainersFactory.eINSTANCE.createCompilationUnit();
        Commentable implEl1 = ImportsFactory.eINSTANCE.createClassifierImport();
        Commentable implEl2 = ImportsFactory.eINSTANCE.createClassifierImport();
        VariationPoint vpMock = RefactoringTestUtil.generateSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassImportOPTOR refactoring = new IfElseStaticConfigClassImportOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(true));
    }

    /**
     * <strong>Description</strong><br/>
     * The method should check for the correct binding time: compile.
     * 
     * <strong>Input</strong><br/>
     * Variation point Characteristics:<br/>
     * <ul>
     * <li>Binding Time: Load</li>
     * <li>Variability Type: OPTOR</li>
     * <li>Extensible: No</li>
     * </ul>
     * 
     * Variation point Internals:<br/>
     * <ul>
     * <li>Location: Class</li>
     * <li>Variant 1: Has a constructor</li>
     * <li>Variant 1: Has a constructor</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidBindingTime() {
        Commentable location = ContainersFactory.eINSTANCE.createCompilationUnit();
        Commentable implEl1 = ImportsFactory.eINSTANCE.createClassifierImport();
        Commentable implEl2 = ImportsFactory.eINSTANCE.createClassifierImport();
        VariationPoint vpMock = RefactoringTestUtil.generateSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.LOAD_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassImportOPTOR refactoring = new IfElseStaticConfigClassImportOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * <strong>Description</strong><br/>
     * The method should check for the correct extensibility: no.
     * 
     * <strong>Input</strong><br/>
     * Variation point Characteristics:<br/>
     * <ul>
     * <li>Binding Time: Compile</li>
     * <li>Variability Type: OPTOR</li>
     * <li>Extensible: Yes</li>
     * </ul>
     * 
     * Variation point Internals:<br/>
     * <ul>
     * <li>Location: Class</li>
     * <li>Variant 1: Has a constructor</li>
     * <li>Variant 1: Has a constructor</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidExtensibility() {
        Commentable location = ContainersFactory.eINSTANCE.createCompilationUnit();
        Commentable implEl1 = ImportsFactory.eINSTANCE.createClassifierImport();
        Commentable implEl2 = ImportsFactory.eINSTANCE.createClassifierImport();
        VariationPoint vpMock = RefactoringTestUtil.generateSimpleVPMock(VariabilityType.OPTOR, Extensible.YES,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassImportOPTOR refactoring = new IfElseStaticConfigClassImportOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * <strong>Description</strong><br/>
     * The method should check for the correct variability type: optor.
     * 
     * <strong>Input</strong><br/>
     * Variation point Characteristics:<br/>
     * <ul>
     * <li>Binding Time: Compile</li>
     * <li>Variability Type: OR</li>
     * <li>Extensible: No</li>
     * </ul>
     * 
     * Variation point Internals:<br/>
     * <ul>
     * <li>Location: Class</li>
     * <li>Variant 1: Has a constructor</li>
     * <li>Variant 1: Has a constructor</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidVarType() {
        Commentable location = ContainersFactory.eINSTANCE.createCompilationUnit();
        Commentable implEl1 = ImportsFactory.eINSTANCE.createClassifierImport();
        Commentable implEl2 = ImportsFactory.eINSTANCE.createClassifierImport();
        VariationPoint vpMock = RefactoringTestUtil.generateSimpleVPMock(VariabilityType.OR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassImportOPTOR refactoring = new IfElseStaticConfigClassImportOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * <strong>Description</strong><br/>
     * Location of a variation point for this refactoring must be a compilation unit.
     * 
     * <strong>Input</strong><br/>
     * Variation point Characteristics:<br/>
     * <ul>
     * <li>Binding Time: Compile</li>
     * <li>Variability Type: OPTOR</li>
     * <li>Extensible: No</li>
     * </ul>
     * 
     * Variation point Internals:<br/>
     * <ul>
     * <li>Location: Interface</li>
     * <li>Variant 1: Has a constructor</li>
     * <li>Variant 1: Has a constructor</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidLocation() {
        Commentable location = ClassifiersFactory.eINSTANCE.createInterface();
        Commentable implEl1 = ImportsFactory.eINSTANCE.createClassifierImport();
        Commentable implEl2 = ImportsFactory.eINSTANCE.createClassifierImport();
        VariationPoint vpMock = RefactoringTestUtil.generateSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassImportOPTOR refactoring = new IfElseStaticConfigClassImportOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * <strong>Description</strong><br/>
     * Location of a variation point for this refactoring must be a class.
     * 
     * <strong>Input</strong><br/>
     * Variation point Characteristics:<br/>
     * <ul>
     * <li>Binding Time: Compile</li>
     * <li>Variability Type: OPTOR</li>
     * <li>Extensible: No</li>
     * </ul>
     * 
     * Variation point Internals:<br/>
     * <ul>
     * <li>Location: Interface</li>
     * <li>Variant 1: Has a constructor</li>
     * <li>Variant 1: Has a constructor</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidVariantElements() {
        Commentable location = ContainersFactory.eINSTANCE.createCompilationUnit();
        Commentable implEl1 = ImportsFactory.eINSTANCE.createClassifierImport();
        Commentable implEl2 = MembersFactory.eINSTANCE.createClassMethod();
        VariationPoint vpMock = RefactoringTestUtil.generateSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassImportOPTOR refactoring = new IfElseStaticConfigClassImportOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * <strong>Description</strong><br/>
     * Merges imports. The base must contain the imports from all variants.
     * 
     * <strong>Input</strong><br/>
     * Variation point Characteristics:<br/>
     * <ul>
     * <li>Binding Time: Compile</li>
     * <li>Variability Type: OPTOR</li>
     * <li>Extensible: No</li>
     * </ul>
     * 
     * Variation point Internals:<br/>
     * <ul>
     * <li>Location: Class</li>
     * <li>Variant 1: Import: ArrayList</li>
     * <li>Variant 2: Import: LinkedList</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * The base must contain two imports: the linkedlist and arraylist import.
     * 
     * @throws Exception An unexpected error occured. 
     */
    @Test
    public void testRefactorCaseImportTwoDistinct() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getImportTwoDistinctCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassImportOPTOR refactoring = new IfElseStaticConfigClassImportOPTOR();
        refactoring.refactor(vp);

        CompilationUnit vpLocation = (CompilationUnit) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getImports().size(), equalTo(2));
        assertThat(vpLocation.getImports().get(0), instanceOf(ClassifierImport.class));
        assertThat(vpLocation.getImports().get(1), instanceOf(ClassifierImport.class));
        assertThat(((org.emftext.language.java.imports.ClassifierImport) vpLocation.getImports().get(0))
                .getClassifier().getName(), equalTo("ArrayList"));
        assertThat(((org.emftext.language.java.imports.ClassifierImport) vpLocation.getImports().get(1))
                .getClassifier().getName(), equalTo("LinkedList"));
    }

    /**
     * <strong>Description</strong><br/>
     * Merges imports. The base must contain the imports from all variants.
     * 
     * <strong>Input</strong><br/>
     * Variation point Characteristics:<br/>
     * <ul>
     * <li>Binding Time: Compile</li>
     * <li>Variability Type: OPTOR</li>
     * <li>Extensible: No</li>
     * </ul>
     * 
     * Variation point Internals:<br/>
     * <ul>
     * <li>Location: Class</li>
     * <li>Variant 1: 3 Imports: a,b,c</li>
     * <li>Variant 2: 3 Imports: b,c,d</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * The base must contain four imports: a,b,c and d.
     * 
     * @throws Exception An unexpected error occured. 
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
