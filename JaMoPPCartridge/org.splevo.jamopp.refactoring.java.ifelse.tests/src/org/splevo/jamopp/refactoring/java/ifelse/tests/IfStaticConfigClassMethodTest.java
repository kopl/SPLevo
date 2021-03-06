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

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.common.util.Diagnostic;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.types.Void;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassMethod;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.jamopp.vpm.software.JaMoPPJavaSoftwareElement;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Contains the tests for the {@link IfStaticConfigClassMethodTest} class.
 */
public class IfStaticConfigClassMethodTest {

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
        Commentable implEl1 = MembersFactory.eINSTANCE.createClassMethod();
        Commentable implEl2 = MembersFactory.eINSTANCE.createClassMethod();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfStaticConfigClassMethod refactoring = new IfStaticConfigClassMethod();

        assertThat(refactoring.canBeAppliedTo(vpMock).getSeverity(), is(Diagnostic.OK));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have a location
     * that is not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidLocation() {
        Commentable location = MembersFactory.eINSTANCE.createClassMethod();
        Commentable implEl1 = MembersFactory.eINSTANCE.createClassMethod();
        Commentable implEl2 = MembersFactory.eINSTANCE.createClassMethod();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfStaticConfigClassMethod refactoring = new IfStaticConfigClassMethod();

        assertThat(refactoring.canBeAppliedTo(vpMock).getSeverity(), is(Diagnostic.ERROR));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have variants
     * with implementing elements that are not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidVariantElements() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = MembersFactory.eINSTANCE.createClassMethod();
        Commentable implEl2 = MembersFactory.eINSTANCE.createConstructor();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfStaticConfigClassMethod refactoring = new IfStaticConfigClassMethod();

        assertThat(refactoring.canBeAppliedTo(vpMock).getSeverity(), is(Diagnostic.ERROR));
    }

    /**
     * Tests whether the refactoring
     * 
     * @throws Exception
     *             In case of an unexpected exception.
     */
    @Test
    public void testRefactorCaseMethodAddSameParam() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getMethodAddSameParamCase(VariabilityType.OPTXOR);
        IfStaticConfigClassMethod refactoring = new IfStaticConfigClassMethod();
        refactoring.refactor(vp, null);

        // location has one method
        Class vpLocation = (Class) ((JaMoPPJavaSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getMethods().size(), equalTo(1));

        // assert number of parameters
        assertThat(vpLocation.getMethods().get(0).getParameters().size(), equalTo(0));

        // assert method name and return type
        assertThat(vpLocation.getMethods().get(0).getTypeReference(), instanceOf(Void.class));
        
        // verify correct VPM
        RefactoringTestUtil.assertValidVPM(vp);
    }

    /**
     * Tests whether the refactoring
     * 
     * @throws Exception
     *             In case of an unexpected exception.
     */
    @Test
    public void testRefactorCaseMethodAddDifferentParam() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getMethodAddDifferentParamCase(VariabilityType.OPTXOR);
        IfStaticConfigClassMethod refactoring = new IfStaticConfigClassMethod();
        refactoring.refactor(vp, null);

        // location has two methods
        Class vpLocation = (Class) ((JaMoPPJavaSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getMethods().size(), equalTo(2));

        Method firstMethod = vpLocation.getMethods().get(0);
        Method secondMethod = vpLocation.getMethods().get(1);
        
        // assert number of parameters
        assertThat(firstMethod.getParameters().size(), anyOf(equalTo(1), equalTo(2)));
        assertThat(secondMethod.getParameters().size(), anyOf(equalTo(1), equalTo(2)));
        assertThat(firstMethod.getParameters().size(), not(equalTo(secondMethod.getParameters().size())));

        // assert method names and return types
        assertThat(firstMethod.getName(), equalTo("someMethod"));
        assertThat(secondMethod.getName(), equalTo("someMethod"));
        assertThat(firstMethod.getTypeReference(), instanceOf(Void.class));
        assertThat(secondMethod.getTypeReference(), instanceOf(Void.class));
        
        // verify correct VPM
        RefactoringTestUtil.assertValidVPM(vp);
    }
}
