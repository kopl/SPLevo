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

import java.util.HashMap;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.expressions.EqualityExpression;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.StatementsFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.java.ifelse.optxor.IfStaticConfigClassStatementInConditionOPTXOR;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.jamopp.refactoring.java.ifelse.util.FullyAutomatedIfElseRefactoringUtil;
import org.splevo.jamopp.vpm.software.JaMoPPJavaSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoringService;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Contains the tests for the {@link IfStaticConfigClassStatementInConditionOPTXOR} class.
 */
public class IfStaticConfigClassStatementInConditionOPTXORTest {
	private FullyAutomatedIfElseRefactoringUtil ifElseRefactoringUtil = new FullyAutomatedIfElseRefactoringUtil();
	
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

        IfStaticConfigClassStatementInConditionOPTXOR refactoring = new IfStaticConfigClassStatementInConditionOPTXOR(ifElseRefactoringUtil);

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

        IfStaticConfigClassStatementInConditionOPTXOR refactoring = new IfStaticConfigClassStatementInConditionOPTXOR(ifElseRefactoringUtil);

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

        IfStaticConfigClassStatementInConditionOPTXOR refactoring = new IfStaticConfigClassStatementInConditionOPTXOR(ifElseRefactoringUtil);

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
        IfStaticConfigClassStatementInConditionOPTXOR refactoring = new IfStaticConfigClassStatementInConditionOPTXOR(ifElseRefactoringUtil);
        HashMap<String, Object> configurations = new HashMap<String, Object>();
        configurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, "");
        refactoring.refactor(vp, configurations);

        // location has a blockand an else-if
        Condition vpLocation = (Condition) ((JaMoPPJavaSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getStatement(), instanceOf(Block.class));
        assertThat(vpLocation.getElseStatement(), instanceOf(Condition.class));

        // verify second if
        Condition secondCond = (Condition) vpLocation.getElseStatement();
        assertThat(secondCond.getStatement(), instanceOf(Block.class));
        assertThat(secondCond.getCondition(), instanceOf(IdentifierReference.class));
        assertThat(secondCond.getElseStatement(), instanceOf(Condition.class));
        Block secondBlock = (Block) secondCond.getStatement();
        assertThat(secondBlock.getStatements().size(), equalTo(1));
        assertThat(secondBlock.getStatements().get(0), instanceOf(Condition.class));

        // verify third if
        Condition thirdCond = (Condition) secondCond.getElseStatement();
        assertThat(thirdCond.getStatement(), instanceOf(Block.class));
        assertThat(thirdCond.getCondition(), instanceOf(IdentifierReference.class));
        assertThat(thirdCond.getElseStatement(), instanceOf(Condition.class));
        Block thirdBlock = (Block) thirdCond.getStatement();
        assertThat(thirdBlock.getStatements().size(), equalTo(1));
        assertThat(thirdBlock.getStatements().get(0), instanceOf(Condition.class));

        // verify fourth if
        Condition fourthCond = (Condition) thirdCond.getElseStatement();
        assertThat(fourthCond.getStatement(), instanceOf(Block.class));
        assertThat(fourthCond.getCondition(), instanceOf(EqualityExpression.class));
        assertThat(fourthCond.getElseStatement(), nullValue());
        Block fourthBlock = (Block) fourthCond.getStatement();
        assertThat(fourthBlock.getStatements().size(), equalTo(1));
        assertThat(fourthBlock.getStatements().get(0), instanceOf(ExpressionStatement.class));
        
        // verify correct VPM
        RefactoringTestUtil.assertValidVPM(vp);
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
        IfStaticConfigClassStatementInConditionOPTXOR refactoring = new IfStaticConfigClassStatementInConditionOPTXOR(ifElseRefactoringUtil);
        HashMap<String, Object> configurations = new HashMap<String, Object>();
        configurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, "");
        refactoring.refactor(vp, configurations);

        // location has a condition with an if-block and an else-block
        Condition vpLocation = (Condition) ((JaMoPPJavaSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getStatement(), instanceOf(Block.class));
        assertThat(vpLocation.getElseStatement(), instanceOf(Condition.class));

        // verify second if
        Condition secondCond = (Condition) vpLocation.getElseStatement();
        assertThat(secondCond.getStatement(), instanceOf(Block.class));
        assertThat(secondCond.getCondition(), instanceOf(IdentifierReference.class));
        assertThat(secondCond.getElseStatement(), instanceOf(Condition.class));
        Block secondBlock = (Block) secondCond.getStatement();
        assertThat(secondBlock.getStatements().size(), equalTo(1));
        assertThat(secondBlock.getStatements().get(0), instanceOf(ExpressionStatement.class));

        // verify third if
        Condition thirdCond = (Condition) secondCond.getElseStatement();
        assertThat(thirdCond.getStatement(), instanceOf(Block.class));
        assertThat(thirdCond.getCondition(), instanceOf(IdentifierReference.class));
        assertThat(thirdCond.getElseStatement(), instanceOf(ExpressionStatement.class));
        Block thirdBlock = (Block) thirdCond.getStatement();
        assertThat(thirdBlock.getStatements().size(), equalTo(1));
        assertThat(thirdBlock.getStatements().get(0), instanceOf(ExpressionStatement.class));
        
        // verify correct VPM
        RefactoringTestUtil.assertValidVPM(vp);
    }

    /**
     * Tests whether the variation point has the correct number of else-ifs when refactoring
     * conditions.
     * 
     * @throws Exception
     *             In case of an unexpected exception.
     */
    @Test
    public void testRefactorCaseConditionAddMultipleCond() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getConditionAddMultipleCondCase(VariabilityType.OPTXOR);
        IfStaticConfigClassStatementInConditionOPTXOR refactoring = new IfStaticConfigClassStatementInConditionOPTXOR(ifElseRefactoringUtil);
        HashMap<String, Object> configurations = new HashMap<String, Object>();
        configurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, "");
        refactoring.refactor(vp, configurations);

        // location has a condition with an if-block and an else-block
        Condition vpLocation = (Condition) ((JaMoPPJavaSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getStatement(), instanceOf(Block.class));
        assertThat(vpLocation.getElseStatement(), instanceOf(Condition.class));

        // assert the correct number of ifs in the chain
        int conditionCounter = 1;
        Condition currentCondition = vpLocation;

        while (currentCondition.getElseStatement() != null && currentCondition.getElseStatement() instanceof Condition) {
            currentCondition = (Condition) currentCondition.getElseStatement();
            conditionCounter++;
        }

        assertThat(conditionCounter, equalTo(5));
        
        // verify correct VPM
        RefactoringTestUtil.assertValidVPM(vp);
    }
}
