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

import java.math.BigInteger;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.StatementsFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassConditionOPTOR;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Contains the tests for the {@link IfElseStaticConfigClassConditionOPTOR} class.
 */
public class IfElseStaticConfigClassConditionOPTORTest {

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
        Commentable location = StatementsFactory.eINSTANCE.createCondition();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = StatementsFactory.eINSTANCE.createEmptyStatement();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassConditionOPTOR refactoring = new IfElseStaticConfigClassConditionOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(true));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have a binding
     * time that is not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidBindingTime() {
        Commentable location = StatementsFactory.eINSTANCE.createCondition();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = StatementsFactory.eINSTANCE.createEmptyStatement();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.LOAD_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassConditionOPTOR refactoring = new IfElseStaticConfigClassConditionOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have a
     * extensibility that is not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidExtensibility() {
        Commentable location = StatementsFactory.eINSTANCE.createCondition();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = StatementsFactory.eINSTANCE.createEmptyStatement();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.YES,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassConditionOPTOR refactoring = new IfElseStaticConfigClassConditionOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have a
     * variability type that is not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidVarType() {
        Commentable location = StatementsFactory.eINSTANCE.createCondition();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = StatementsFactory.eINSTANCE.createEmptyStatement();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassConditionOPTOR refactoring = new IfElseStaticConfigClassConditionOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have a location
     * that is not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidLocation() {
        Commentable location = ClassifiersFactory.eINSTANCE.createInterface();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = StatementsFactory.eINSTANCE.createEmptyStatement();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassConditionOPTOR refactoring = new IfElseStaticConfigClassConditionOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have variants
     * with implementing elements that are not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidVariantElements() {
        Commentable location = StatementsFactory.eINSTANCE.createCondition();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = MembersFactory.eINSTANCE.createClassMethod();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassConditionOPTOR refactoring = new IfElseStaticConfigClassConditionOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * Tests whether the refactoring adds missing else-if statements correctly into an existing
     * if-else chain.
     * 
     * @throws Exception
     *             In case of an unexpected exception.
     */
    @Test
    public void testRefactorCaseConditionAddCond() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getConditionAddCondCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassConditionOPTOR refactoring = new IfElseStaticConfigClassConditionOPTOR();
        refactoring.refactor(vp);

        // location has one condition with else-statements
        Condition vpLocation = (Condition) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getStatement(), instanceOf(Block.class));
        assertThat(vpLocation.getElseStatement(), instanceOf(Condition.class));

        // else-statement (variant 1) has another condition with one statement in its if-block.
        Condition varCond1 = (Condition) vpLocation.getElseStatement();
        assertThat(varCond1.getStatement(), instanceOf(Block.class));
        Block varCondBlock1 = (Block) varCond1.getStatement();
        assertThat(varCondBlock1.getStatements().size(), equalTo(1));
        assertThat(varCondBlock1.getStatements().get(0), instanceOf(Condition.class));
        Condition block1Cond = (Condition) varCondBlock1.getStatements().get(0);
        assertThat(((Block) block1Cond.getStatement()).getStatements().size(), equalTo(1));

        // else-statement (variant 2) has another condition with one statement in its if-block.
        assertThat(varCond1.getElseStatement(), instanceOf(Condition.class));
        Condition varCond2 = (Condition) varCond1.getElseStatement();
        assertThat(varCond2.getStatement(), instanceOf(Block.class));
        Block varCondBlock2 = (Block) varCond2.getStatement();
        assertThat(varCondBlock2.getStatements().size(), equalTo(1));
        assertThat(varCondBlock2.getStatements().get(0), instanceOf(Condition.class));
        Condition block2Cond = (Condition) varCondBlock2.getStatements().get(0);
        assertThat(((Block) block2Cond.getStatement()).getStatements().size(), equalTo(1));
    }

    /**
     * Tests whether the refactoring merges varying else-statements (independent statements)
     * correctly.
     * 
     * @throws Exception
     *             In case of an unexpected exception.
     */
    @Test
    public void testRefactorCaseConditionDifferentElseStatement() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getConditionDifferentElseStatementCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassConditionOPTOR refactoring = new IfElseStaticConfigClassConditionOPTOR();
        refactoring.refactor(vp);

        // location has a condition with an if-block and another condition in its else-statement
        Condition vpLocation = (Condition) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getStatement(), instanceOf(Block.class));
        assertThat(vpLocation.getElseStatement(), instanceOf(Condition.class));

        // the second if-statement (variant 1) has the expression statement
        Condition varCond1 = (Condition) vpLocation.getElseStatement();
        assertThat(varCond1.getStatement(), instanceOf(Block.class));
        Block varCondBlock1 = (Block) varCond1.getStatement();
        assertThat(varCondBlock1.getStatements().size(), equalTo(1));
        assertThat(varCondBlock1.getStatements().get(0), instanceOf(ExpressionStatement.class));

        // the second if-statement (variant 1) has the expression statement
        assertThat(varCond1.getElseStatement(), instanceOf(Condition.class));
        Condition varCond2 = (Condition) varCond1.getElseStatement();
        assertThat(varCond2.getStatement(), instanceOf(Block.class));
        Block varCondBlock2 = (Block) varCond2.getStatement();
        assertThat(varCondBlock2.getStatements().size(), equalTo(1));
        assertThat(varCondBlock2.getStatements().get(0), instanceOf(ExpressionStatement.class));

        // correct values in the syso expressions?
        BigInteger val1 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) varCondBlock1
                .getStatements().get(0));
        BigInteger val2 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) varCondBlock2
                .getStatements().get(0));

        assertThat(val1, anyOf(equalTo(BigInteger.valueOf(2)), equalTo(BigInteger.valueOf(3))));
        assertThat(val2, anyOf(equalTo(BigInteger.valueOf(2)), equalTo(BigInteger.valueOf(3))));
        assertThat(val1, not(equalTo(val2)));
    }
}
