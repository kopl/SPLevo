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

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.MembersFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassInterfaceOPTOR;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Contains the tests for the {@link IfElseStaticConfigClassInterfaceOPTORTest} class.
 */
public class IfElseStaticConfigClassInterfaceOPTORTest {

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
        Commentable implEl1 = ClassifiersFactory.eINSTANCE.createInterface();
        Commentable implEl2 = ClassifiersFactory.eINSTANCE.createInterface();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassInterfaceOPTOR refactoring = new IfElseStaticConfigClassInterfaceOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(true));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have a location
     * that is not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidLocation() {
        Commentable location = MembersFactory.eINSTANCE.createClassMethod();
        Commentable implEl1 = ClassifiersFactory.eINSTANCE.createInterface();
        Commentable implEl2 = ClassifiersFactory.eINSTANCE.createInterface();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassInterfaceOPTOR refactoring = new IfElseStaticConfigClassInterfaceOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have variants
     * with implementing elements that are not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidVariantElements() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl2 = ClassifiersFactory.eINSTANCE.createClass();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassInterfaceOPTOR refactoring = new IfElseStaticConfigClassInterfaceOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * Tests whether the refactoring adds a missing interface from the integration variant to the base
     * correctly.
     * 
     * @throws Exception
     *             In case of an unexpected exception.
     */
    @Test
    public void testRefactorCaseInterfaceMerge() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getInterfaceMergeCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassInterfaceOPTOR refactoring = new IfElseStaticConfigClassInterfaceOPTOR();
        refactoring.refactor(vp);

        // location has two members (the interfaces A and B)
        MemberContainer vpLocation = (MemberContainer) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getMembers().size(), equalTo(2));

        // both members are interfaces
        assertThat(vpLocation.getMembers().get(0), instanceOf(Interface.class));
        assertThat(vpLocation.getMembers().get(1), instanceOf(Interface.class));

        Interface interface1 = (Interface) vpLocation.getMembers().get(0);
        Interface interface2 = (Interface) vpLocation.getMembers().get(1);

        // interfaces have the correct name
        assertThat(interface1.getName(), anyOf(equalTo("A"), equalTo("B")));
        assertThat(interface2.getName(), anyOf(equalTo("A"), equalTo("B")));
        assertThat(interface1.getName(), not(equalTo(interface2.getName())));
    }
}
