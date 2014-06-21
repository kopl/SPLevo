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

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.types.Int;
import org.emftext.language.java.types.Short;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassConstructorOPTOR;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Contains the tests for the {@link IfElseStaticConfigClassConstructorOPTOR} class.
 */
public class IfElseStaticConfigClassConstructorOPTORTest {

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
     * <li>Variant 2: Has a constructor</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>true</code>
     */
    @Test
    public void testIfCanBeAppliedWithValidVP() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = MembersFactory.eINSTANCE.createConstructor();
        Commentable implEl2 = MembersFactory.eINSTANCE.createConstructor();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassConstructorOPTOR refactoring = new IfElseStaticConfigClassConstructorOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), is(true));
    }

    /**
     * <strong>Description</strong><br/>
     * The method checks whether the method handles an incorrect binding time correctly.
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
     * <li>Variant 2: Has a constructor</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidBindingTime() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = MembersFactory.eINSTANCE.createConstructor();
        Commentable implEl2 = MembersFactory.eINSTANCE.createConstructor();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.LOAD_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassConstructorOPTOR refactoring = new IfElseStaticConfigClassConstructorOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), is(false));
    }

    /**
     * <strong>Description</strong><br/>
     * The method checks whether the method handles an incorrect extensibility correctly.
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
     * <li>Variant 2: Has a constructor</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidExtensibility() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = MembersFactory.eINSTANCE.createConstructor();
        Commentable implEl2 = MembersFactory.eINSTANCE.createConstructor();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.YES,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassConstructorOPTOR refactoring = new IfElseStaticConfigClassConstructorOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), is(false));
    }

    /**
     * <strong>Description</strong><br/>
     * The method checks whether the method handles an incorrect variability type correctly.
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
     * <li>Variant 2: Has a constructor</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidVarType() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = MembersFactory.eINSTANCE.createConstructor();
        Commentable implEl2 = MembersFactory.eINSTANCE.createConstructor();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassConstructorOPTOR refactoring = new IfElseStaticConfigClassConstructorOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), is(false));
    }

    /**
     * <strong>Description</strong><br/>
     * The method checks whether the method handles an incorrect variation point location correctly.
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
     * <li>Variant 2: Has a constructor</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidLocation() {
        Commentable location = ClassifiersFactory.eINSTANCE.createInterface();
        Commentable implEl1 = MembersFactory.eINSTANCE.createConstructor();
        Commentable implEl2 = MembersFactory.eINSTANCE.createConstructor();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassConstructorOPTOR refactoring = new IfElseStaticConfigClassConstructorOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), is(false));
    }

    /**
     * <strong>Description</strong><br/>
     * The method checks whether the method handles incorrect variant elements correctly.
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
     * <li>Variant 2: Has a constructor</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidVariantElements() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = MembersFactory.eINSTANCE.createConstructor();
        Commentable implEl2 = MembersFactory.eINSTANCE.createClassMethod();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassConstructorOPTOR refactoring = new IfElseStaticConfigClassConstructorOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), is(false));
    }

    /**
     * <strong>Description</strong><br/>
     * Merges constructors, each with one param of a different type.
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
     * <li>Variant 1: Constructor with int parameter</li>
     * <li>Variant 2: Constructor with short parameter</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * Both constructors must be contained in the base after the refactoring.
     * 
     * @throws Exception
     *             An unexpected exception occurred.
     */
    @Test
    public void testRefactorCaseConstructorExistingOneParam() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getConstructorExistingOneParamCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassConstructorOPTOR refactoring = new IfElseStaticConfigClassConstructorOPTOR();
        refactoring.refactor(vp);

        Class vpLocation = (Class) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getConstructors().size(), is(2));
        assertThat(vpLocation.getConstructors().get(0).getParameters().size(), is(1));
        assertThat(vpLocation.getConstructors().get(1).getParameters().size(), is(1));
        assertThat(vpLocation.getConstructors().get(0).getParameters().get(0).getTypeReference(), instanceOf(Int.class));
        assertThat(vpLocation.getConstructors().get(1).getParameters().get(0).getTypeReference(),
                instanceOf(Short.class));
    }

    /**
     * <strong>Description</strong><br/>
     * Merges constructors, one constructor has one parameter, the other has two.
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
     * <li>Variant 1: Constructor with an int parameter</li>
     * <li>Variant 2: Constructor with an int and a short parameter</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * Both constructors must be contained in the base after the refactoring.
     * 
     * @throws Exception
     *             An unexpected exception occurred.
     */
    @Test
    public void testRefactorCaseConstructorAddTwoParam() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getConstructorAddTwoParamCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassConstructorOPTOR refactoring = new IfElseStaticConfigClassConstructorOPTOR();
        refactoring.refactor(vp);

        Class vpLocation = (Class) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getConstructors().size(), is(2));
        assertThat(vpLocation.getConstructors().get(0).getParameters().size(), is(1));
        assertThat(vpLocation.getConstructors().get(1).getParameters().size(), is(2));
        assertThat(vpLocation.getConstructors().get(0).getParameters().get(0).getTypeReference(), instanceOf(Int.class));
        assertThat(vpLocation.getConstructors().get(1).getParameters().get(0).getTypeReference(), instanceOf(Int.class));
        assertThat(vpLocation.getConstructors().get(1).getParameters().get(1).getTypeReference(),
                instanceOf(Short.class));
    }
}
