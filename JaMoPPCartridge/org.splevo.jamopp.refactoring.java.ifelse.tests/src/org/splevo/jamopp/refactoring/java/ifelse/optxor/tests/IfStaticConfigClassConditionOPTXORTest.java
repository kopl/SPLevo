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
package org.splevo.jamopp.refactoring.java.ifelse.optxor.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.expressions.ConditionalOrExpression;
import org.emftext.language.java.expressions.EqualityExpression;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.StatementsFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.java.ifelse.optxor.IfStaticConfigClassConditionOPTXOR;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Contains the tests for the {@link IfStaticConfigClassConditionOPTXOR} class.
 */
public class IfStaticConfigClassConditionOPTXORTest {

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
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfStaticConfigClassConditionOPTXOR refactoring = new IfStaticConfigClassConditionOPTXOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(true));
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
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfStaticConfigClassConditionOPTXOR refactoring = new IfStaticConfigClassConditionOPTXOR();

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
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfStaticConfigClassConditionOPTXOR refactoring = new IfStaticConfigClassConditionOPTXOR();

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
        VariationPoint vp = RefactoringTestUtil.getConditionAddCondCase(VariabilityType.OPTXOR);
        IfStaticConfigClassConditionOPTXOR refactoring = new IfStaticConfigClassConditionOPTXOR();
        refactoring.refactor(vp, null);

        // location has a blockand an else-if
        Condition vpLocation = (Condition) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getStatement(), instanceOf(Block.class));
        assertThat(vpLocation.getElseStatement(), instanceOf(Condition.class));

        // has the two conditions in its if-block and another else
        Condition varCond1 = (Condition) vpLocation.getElseStatement();
        assertThat(varCond1.getCondition(), instanceOf(ConditionalOrExpression.class));
        assertThat(varCond1.getElseStatement(), instanceOf(Condition.class));
        assertThat(varCond1.getStatement(), instanceOf(Block.class));
        assertThat(((Block) varCond1.getStatement()).getStatements().size(), equalTo(2));

        // else-statement 2
        Condition varCond2 = (Condition) varCond1.getElseStatement();
        assertThat(varCond2.getCondition(), instanceOf(EqualityExpression.class));
        assertThat(varCond2.getElseStatement(), nullValue());
        assertThat(varCond2.getStatement(), instanceOf(Block.class));
        assertThat(((Block) varCond2.getStatement()).getStatements().size(), equalTo(1));
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
        VariationPoint vp = RefactoringTestUtil.getConditionDifferentElseStatementCase(VariabilityType.OPTXOR);
        IfStaticConfigClassConditionOPTXOR refactoring = new IfStaticConfigClassConditionOPTXOR();
        refactoring.refactor(vp, null);

        // location has a condition with an if-block and an else-block
        Condition vpLocation = (Condition) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getStatement(), instanceOf(Block.class));
        assertThat(vpLocation.getElseStatement(), instanceOf(Condition.class));

        // else-if has two var conditions
        Condition mainVarCond = (Condition) vpLocation.getElseStatement();
        assertThat(mainVarCond.getStatement(), instanceOf(Block.class));
        assertThat(mainVarCond.getCondition(), instanceOf(ConditionalOrExpression.class));
        assertThat(mainVarCond.getElseStatement(), instanceOf(ExpressionStatement.class));

        // verify block contents. should have two var conds
        Block block = (Block) mainVarCond.getStatement();
        assertThat(block.getStatements().size(), equalTo(2));
        assertThat(block.getStatements().get(0), instanceOf(Condition.class));
        assertThat(block.getStatements().get(1), instanceOf(Condition.class));

        Condition varCond1 = (Condition) block.getStatements().get(0);
        Condition varCond2 = (Condition) block.getStatements().get(1);
        assertThat(varCond1.getElseStatement(), nullValue());
        assertThat(varCond2.getElseStatement(), nullValue());

        // both varconditions contain one element
        assertThat(varCond1.getStatement(), instanceOf(Block.class));
        assertThat(varCond2.getStatement(), instanceOf(Block.class));
        assertThat(((Block) varCond1.getStatement()).getStatements().size(), equalTo(1));
        assertThat(((Block) varCond2.getStatement()).getStatements().size(), equalTo(1));
    }

    /**
     * Tests whether the refactoring merges conditions correctly where each variant has two
     * exclusive else-ifs.
     * 
     * @throws Exception
     *             In case of an unexpected exception.
     */
    @Test
    public void testRefactorCaseConditionAddMultipleCond() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getConditionAddMultipleCondCase(VariabilityType.OPTXOR);
        IfStaticConfigClassConditionOPTXOR refactoring = new IfStaticConfigClassConditionOPTXOR();
        refactoring.refactor(vp, null);

        // location has a condition with an if-block and an else-block
        Condition vpLocation = (Condition) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getStatement(), instanceOf(Block.class));
        assertThat(vpLocation.getElseStatement(), instanceOf(Condition.class));

        // assert the correct number of ifs in the chain
        int conditionCounter = 1;
        Condition currentCondition = vpLocation;

        while (currentCondition.getElseStatement() != null && currentCondition.getElseStatement() instanceof Condition) {
            currentCondition = (Condition) currentCondition.getElseStatement();
            conditionCounter++;
        }

        assertThat(conditionCounter, equalTo(4));
    }
}
