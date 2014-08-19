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
package org.splevo.jamopp.refactoring.java.ifelse.tests;

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
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassConstructor;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Contains the tests for the {@link IfStaticConfigClassConstructor} class.
 */
public class IfStaticConfigClassConstructorTest {

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
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = MembersFactory.eINSTANCE.createConstructor();
        Commentable implEl2 = MembersFactory.eINSTANCE.createConstructor();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfStaticConfigClassConstructor refactoring = new IfStaticConfigClassConstructor();

        assertThat(refactoring.canBeAppliedTo(vpMock), is(true));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have a location
     * that is not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidLocation() {
        Commentable location = ClassifiersFactory.eINSTANCE.createInterface();
        Commentable implEl1 = MembersFactory.eINSTANCE.createConstructor();
        Commentable implEl2 = MembersFactory.eINSTANCE.createConstructor();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfStaticConfigClassConstructor refactoring = new IfStaticConfigClassConstructor();

        assertThat(refactoring.canBeAppliedTo(vpMock), is(false));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have variants
     * with implementing elements that are not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidVariantElements() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = MembersFactory.eINSTANCE.createConstructor();
        Commentable implEl2 = MembersFactory.eINSTANCE.createClassMethod();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfStaticConfigClassConstructor refactoring = new IfStaticConfigClassConstructor();

        assertThat(refactoring.canBeAppliedTo(vpMock), is(false));
    }

    /**
     * Tests whether the refactoring merges two constructors, each with one parameter but with
     * different types correctly.
     * 
     * @throws Exception
     *             In case of an unexpected exception.
     */
    @Test
    public void testRefactorCaseConstructorExistingOneParam() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getConstructorExistingOneParamCase(VariabilityType.OPTXOR);
        IfStaticConfigClassConstructor refactoring = new IfStaticConfigClassConstructor();
        refactoring.refactor(vp, null);

        // location has two constructors
        Class vpLocation = (Class) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getConstructors().size(), is(2));
        
        // assert number of parameters
        assertThat(vpLocation.getConstructors().get(0).getParameters().size(), is(1));
        assertThat(vpLocation.getConstructors().get(1).getParameters().size(), is(1));
        
        // assert constructor return types
        assertThat(vpLocation.getConstructors().get(0).getParameters().get(0).getTypeReference(), instanceOf(Int.class));
        assertThat(vpLocation.getConstructors().get(1).getParameters().get(0).getTypeReference(),
                instanceOf(Short.class));
    }

    /**
     * Tests whether the refactoring merges two constructors correctly, where the first has one and
     * the second two parameters.
     * 
     * @throws Exception
     *             In case of an unexpected exception.
     */
    @Test
    public void testRefactorCaseConstructorAddTwoParam() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getConstructorAddTwoParamCase(VariabilityType.OPTXOR);
        IfStaticConfigClassConstructor refactoring = new IfStaticConfigClassConstructor();
        refactoring.refactor(vp, null);

        // location has two constructors
        Class vpLocation = (Class) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getConstructors().size(), is(2));
        
        // constructors have one and two parameters
        assertThat(vpLocation.getConstructors().get(0).getParameters().size(), is(1));
        assertThat(vpLocation.getConstructors().get(1).getParameters().size(), is(2));
        
        // assert parameter types
        assertThat(vpLocation.getConstructors().get(0).getParameters().get(0).getTypeReference(), instanceOf(Int.class));
        assertThat(vpLocation.getConstructors().get(1).getParameters().get(0).getTypeReference(), instanceOf(Int.class));
        assertThat(vpLocation.getConstructors().get(1).getParameters().get(1).getTypeReference(),
                instanceOf(Short.class));
    }
}
