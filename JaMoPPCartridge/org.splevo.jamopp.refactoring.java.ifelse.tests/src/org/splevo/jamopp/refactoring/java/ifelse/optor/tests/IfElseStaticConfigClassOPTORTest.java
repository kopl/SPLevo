/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse.optor.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.statements.StatementsFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassOPTOR;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Contains tests for the {@link IfElseStaticConfigClassOPTOR} class.
 */
public class IfElseStaticConfigClassOPTORTest {

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have a binding
     * time that is not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidBindingTime() {
        Commentable location = MembersFactory.eINSTANCE.createClassMethod();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = StatementsFactory.eINSTANCE.createEmptyStatement();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.LOAD_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassOPTOR refactoring = new IfElseStaticConfigClassOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have a
     * extensibility that is not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidExtensibility() {
        Commentable location = MembersFactory.eINSTANCE.createClassMethod();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = StatementsFactory.eINSTANCE.createEmptyStatement();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.YES,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassOPTOR refactoring = new IfElseStaticConfigClassOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have a
     * variability type that is not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidVarType() {
        Commentable location = MembersFactory.eINSTANCE.createClassMethod();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = StatementsFactory.eINSTANCE.createEmptyStatement();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassOPTOR refactoring = new IfElseStaticConfigClassOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }
}
