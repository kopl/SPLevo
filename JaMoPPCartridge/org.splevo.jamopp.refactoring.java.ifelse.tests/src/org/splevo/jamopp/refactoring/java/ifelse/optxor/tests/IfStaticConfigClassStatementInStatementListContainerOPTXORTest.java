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
 *    Stephan Seifermann - assertion for correct VPM
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse.optxor.tests;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.util.HashMap;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.literals.DecimalIntegerLiteral;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.ForLoop;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.Switch;
import org.emftext.language.java.statements.TryBlock;
import org.emftext.language.java.statements.WhileLoop;
import org.emftext.language.java.variables.LocalVariable;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassConstructor;
import org.splevo.jamopp.refactoring.java.ifelse.optxor.IfStaticConfigClassStatementInStatementListContainerOPTXOR;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.jamopp.refactoring.java.ifelse.util.FullyAutomatedIfElseRefactoringUtil;
import org.splevo.jamopp.refactoring.java.ifelse.util.IfElseRefactoringUtil;
import org.splevo.jamopp.vpm.software.JaMoPPJavaSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoringService;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Contains the tests for the {@link IfStaticConfigClassStatementInStatementListContainerOPTXOR} class.
 */
public class IfStaticConfigClassStatementInStatementListContainerOPTXORTest {
    private IfElseRefactoringUtil ifElseRefactoringUtil = new FullyAutomatedIfElseRefactoringUtil();
	
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
        Commentable location = MembersFactory.eINSTANCE.createClassMethod();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = StatementsFactory.eINSTANCE.createEmptyStatement();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfStaticConfigClassStatementInStatementListContainerOPTXOR refactoring = new IfStaticConfigClassStatementInStatementListContainerOPTXOR(ifElseRefactoringUtil);

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

        IfStaticConfigClassConstructor refactoring = new IfStaticConfigClassConstructor();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have variants
     * with implementing elements that are not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidVariantElements() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = MembersFactory.eINSTANCE.createClassMethod();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfStaticConfigClassConstructor refactoring = new IfStaticConfigClassConstructor();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have variants
     * that contain conflicting local variables (same name different type).
     * 
     * @throws Exception
     *             In case of an unexpected exception.
     */
    @Test
    public void testIfCanBeAppliedWithVariableDiffTypes() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementLocalVarDiffTypesCase(VariabilityType.OPTXOR);
        IfStaticConfigClassStatementInStatementListContainerOPTXOR refactoring = new IfStaticConfigClassStatementInStatementListContainerOPTXOR(ifElseRefactoringUtil);

        assertThat(refactoring.canBeAppliedTo(vp), equalTo(false));
    }

    /**
     * Two variants which share one local variable with equal types and different initial values.
     * Variables are not referenced by following code and must not be split. Both must be moved to
     * the variant-specific if.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseLocalVariableEqualType() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementLocalVarEqualTypeCase(VariabilityType.OPTXOR);
        IfStaticConfigClassStatementInStatementListContainerOPTXOR refactoring = new IfStaticConfigClassStatementInStatementListContainerOPTXOR(ifElseRefactoringUtil);
        HashMap<String, Object> configurations = new HashMap<String, Object>();
        configurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, "");
        refactoring.refactor(vp, configurations);

        ClassMethod vpLocation = (ClassMethod) ((JaMoPPJavaSoftwareElement) vp.getLocation()).getJamoppElement();

        // vp location has 2 statements: the two conditions (one per variant)
        assertThat(vpLocation.getStatements().size(), equalTo(2));
        assertThat(vpLocation.getStatements().get(0), instanceOf(Condition.class));
        assertThat(vpLocation.getStatements().get(1), instanceOf(Condition.class));

        Condition firstCond = (Condition) vpLocation.getStatements().get(0);
        Condition secondCond = (Condition) vpLocation.getStatements().get(1);

        assertThat(firstCond.getStatement(), instanceOf(Block.class));
        assertThat(secondCond.getStatement(), instanceOf(Block.class));

        EList<Statement> firstCondIfBlockStatements = ((Block) firstCond.getStatement()).getStatements();
        EList<Statement> secondCondIfBlockStatements = ((Block) secondCond.getStatement()).getStatements();

        assertThat(firstCondIfBlockStatements.size(), equalTo(1));
        assertThat(secondCondIfBlockStatements.size(), equalTo(1));
        assertThat(firstCondIfBlockStatements.get(0), instanceOf(LocalVariableStatement.class));
        assertThat(secondCondIfBlockStatements.get(0), instanceOf(LocalVariableStatement.class));
        
        LocalVariable variable1 = ((LocalVariableStatement) firstCondIfBlockStatements.get(0)).getVariable();
        LocalVariable variable2 = ((LocalVariableStatement) secondCondIfBlockStatements.get(0)).getVariable();

        assertThat(variable1.getName(), equalTo("a"));
        assertThat(variable2.getName(), equalTo("a"));
        assertThat(variable1.getInitialValue(), instanceOf(DecimalIntegerLiteral.class));
        assertThat(variable2.getInitialValue(), instanceOf(DecimalIntegerLiteral.class));

        BigInteger decimalValue1 = ((DecimalIntegerLiteral) variable1.getInitialValue()).getDecimalValue();
        BigInteger decimalValue2 = ((DecimalIntegerLiteral) variable2.getInitialValue()).getDecimalValue();
        assertThat(decimalValue1, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(decimalValue2, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(decimalValue1, not(equalTo(decimalValue2)));
        
        RefactoringTestUtil.assertValidVPM(vp);
    }

    /**
     * Variants have shared local variables with equal types and different initial values. Local
     * variable declarations must be split into declaration and assignment. The declaration must be
     * moved to the front of the first if while the assignment must be contained in the
     * variant-specific if.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseLocalVariableEqualTypeSplit() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementLocalVarEqualTypeSplitCase(VariabilityType.OPTXOR);
        IfStaticConfigClassStatementInStatementListContainerOPTXOR refactoring = new IfStaticConfigClassStatementInStatementListContainerOPTXOR(ifElseRefactoringUtil);
        HashMap<String, Object> configurations = new HashMap<String, Object>();
        configurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, "");
        refactoring.refactor(vp, configurations);

        ClassMethod vpLocation = (ClassMethod) ((JaMoPPJavaSoftwareElement) vp.getLocation()).getJamoppElement();

        // vp location has 4 statements: the variable, two conditions (one per variant) and the
        // common statement
        assertThat(vpLocation.getStatements().size(), equalTo(4));
        assertThat(vpLocation.getStatements().get(0), instanceOf(LocalVariableStatement.class));
        assertThat(vpLocation.getStatements().get(1), instanceOf(Condition.class));
        assertThat(vpLocation.getStatements().get(2), instanceOf(Condition.class));

        LocalVariableStatement localVarStat = (LocalVariableStatement) vpLocation.getStatements().get(0);
        Condition firstCond = (Condition) vpLocation.getStatements().get(1);
        Condition secondCond = (Condition) vpLocation.getStatements().get(2);

        // assert correct variable name and initial value
        assertThat(localVarStat.getVariable().getName(), equalTo("a"));
        assertThat(localVarStat.getVariable().getInitialValue(), instanceOf(DecimalIntegerLiteral.class));
        assertThat(((DecimalIntegerLiteral) localVarStat.getVariable().getInitialValue()).getDecimalValue(),
                equalTo(BigInteger.valueOf(0)));

        // both conditions must have an if-block
        assertThat(firstCond.getStatement(), instanceOf(Block.class));
        assertThat(secondCond.getStatement(), instanceOf(Block.class));

        EList<Statement> firstCondIfBlockStatements = ((Block) firstCond.getStatement()).getStatements();
        EList<Statement> secondCondIfBlockStatements = ((Block) secondCond.getStatement()).getStatements();

        // conditions have the correct contents: the expression statement
        assertThat(firstCondIfBlockStatements.size(), equalTo(1));
        assertThat(secondCondIfBlockStatements.size(), equalTo(1));
        assertThat(firstCondIfBlockStatements.get(0), instanceOf(ExpressionStatement.class));
        assertThat(secondCondIfBlockStatements.get(0), instanceOf(ExpressionStatement.class));
        assertThat(((ExpressionStatement) firstCondIfBlockStatements.get(0)).getExpression(),
                instanceOf(AssignmentExpression.class));
        assertThat(((ExpressionStatement) secondCondIfBlockStatements.get(0)).getExpression(),
                instanceOf(AssignmentExpression.class));
        assertThat(
                ((AssignmentExpression) ((ExpressionStatement) firstCondIfBlockStatements.get(0)).getExpression())
                        .getValue(),
                instanceOf(DecimalIntegerLiteral.class));
        assertThat(
                ((AssignmentExpression) ((ExpressionStatement) secondCondIfBlockStatements.get(0)).getExpression())
                        .getValue(),
                instanceOf(DecimalIntegerLiteral.class));

        // assert correct assignment values
        DecimalIntegerLiteral firstAssignmentExpr = (DecimalIntegerLiteral) ((AssignmentExpression) ((ExpressionStatement) firstCondIfBlockStatements
                .get(0)).getExpression()).getValue();
        DecimalIntegerLiteral secondAssignmentExpr = (DecimalIntegerLiteral) ((AssignmentExpression) ((ExpressionStatement) secondCondIfBlockStatements
                .get(0)).getExpression()).getValue();

        assertThat(firstAssignmentExpr.getDecimalValue(),
                anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(secondAssignmentExpr.getDecimalValue(),
                anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(firstAssignmentExpr.getDecimalValue(), not(equalTo(secondAssignmentExpr.getDecimalValue())));
        
        RefactoringTestUtil.assertValidVPM(vp);
    }

    /**
     * <strong>Description</strong><br/>
     * Two variants that both share a statement. The Integration variant has one additional
     * statement at the end. The refactoring should introduce a condition that executes the
     * integration variant's statement if configured.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseOneAdd() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementOneAddCase(VariabilityType.OPTXOR);
        IfStaticConfigClassStatementInStatementListContainerOPTXOR refactoring = new IfStaticConfigClassStatementInStatementListContainerOPTXOR(ifElseRefactoringUtil);
        HashMap<String, Object> configurations = new HashMap<String, Object>();
        configurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, "");
        refactoring.refactor(vp, configurations);

        ClassMethod vpLocation = (ClassMethod) ((JaMoPPJavaSoftwareElement) vp.getLocation()).getJamoppElement();

        // location has two statements: the common expression statement and the condition for the
        // integration variant
        assertThat(vpLocation.getStatements().size(), equalTo(2));
        assertThat(vpLocation.getStatements().get(0), instanceOf(ExpressionStatement.class));
        assertThat(vpLocation.getStatements().get(1), instanceOf(Condition.class));

        // verify the contents of the condition
        Condition cond = (Condition) vpLocation.getStatements().get(1);
        assertThat(cond.getStatement(), instanceOf(Block.class));
        assertThat(((Block) cond.getStatement()).getStatements().size(), equalTo(1));

        // assert correct values of the SYSO expressions
        BigInteger val1 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) vpLocation.getStatements()
                .get(0));
        BigInteger val2 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) ((Block) cond
                .getStatement()).getStatements().get(0));

        assertThat(val1, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val2, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val1, not(equalTo(val2)));
        
        RefactoringTestUtil.assertValidVPM(vp);
    }

    /**
     * <strong>Description</strong><br/>
     * Two variants that both have one different statement. Two conditions should be introduced that
     * execute the statements as configured.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseOneEither() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementOneEitherCase(VariabilityType.OPTXOR);
        IfStaticConfigClassStatementInStatementListContainerOPTXOR refactoring = new IfStaticConfigClassStatementInStatementListContainerOPTXOR(ifElseRefactoringUtil);
        HashMap<String, Object> configurations = new HashMap<String, Object>();
        configurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, "");
        refactoring.refactor(vp, configurations);

        ClassMethod vpLocation = (ClassMethod) ((JaMoPPJavaSoftwareElement) vp.getLocation()).getJamoppElement();

        // location has two conditions (one per variant)
        assertThat(vpLocation.getStatements().size(), equalTo(2));
        assertThat(vpLocation.getStatements().get(0), instanceOf(Condition.class));
        assertThat(vpLocation.getStatements().get(1), instanceOf(Condition.class));

        // assert condition contents (the statements)
        Condition cond1 = (Condition) vpLocation.getStatements().get(0);
        Condition cond2 = (Condition) vpLocation.getStatements().get(1);
        assertThat(cond1.getStatement(), instanceOf(Block.class));
        assertThat(cond2.getStatement(), instanceOf(Block.class));
        assertThat(((Block) cond1.getStatement()).getStatements().size(), equalTo(1));
        assertThat(((Block) cond2.getStatement()).getStatements().size(), equalTo(1));

        // assert the SYSO values
        BigInteger val1 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) ((Block) cond1
                .getStatement()).getStatements().get(0));
        BigInteger val2 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) ((Block) cond2
                .getStatement()).getStatements().get(0));

        assertThat(val1, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val2, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val1, not(equalTo(val2)));
        
        RefactoringTestUtil.assertValidVPM(vp);
    }

    /**
     * Two variants that both have one different statement in a nested block (try). Two conditions
     * should be introduced in the block that execute the statements as configured.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseNestedTry() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementNestedTryCase(VariabilityType.OPTXOR);
        IfStaticConfigClassStatementInStatementListContainerOPTXOR refactoring = new IfStaticConfigClassStatementInStatementListContainerOPTXOR(ifElseRefactoringUtil);
        HashMap<String, Object> configurations = new HashMap<String, Object>();
        configurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, "");
        refactoring.refactor(vp, configurations);

        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPJavaSoftwareElement) vp.getLocation())
                .getJamoppElement();

        ClassMethod method = (ClassMethod) vpLocation.eContainer();
        assertThat(method.getStatements().size(), equalTo(1));
        assertThat(method.getStatements().get(0), instanceOf(TryBlock.class));

        assertThat(vpLocation.getStatements().size(), equalTo(2));
        assertThat(vpLocation.getStatements().get(0), instanceOf(Condition.class));
        assertThat(vpLocation.getStatements().get(1), instanceOf(Condition.class));

        Condition cond1 = (Condition) vpLocation.getStatements().get(0);
        Condition cond2 = (Condition) vpLocation.getStatements().get(1);

        assertThat(((Block) cond1.getStatement()).getStatements().size(), equalTo(1));
        assertThat(((Block) cond2.getStatement()).getStatements().size(), equalTo(1));

        BigInteger val1 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) ((Block) cond1
                .getStatement()).getStatements().get(0));
        BigInteger val2 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) ((Block) cond2
                .getStatement()).getStatements().get(0));

        assertThat(val1, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val2, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val1, not(equalTo(val2)));
        
        RefactoringTestUtil.assertValidVPM(vp);
    }

    /**
     * Two variants that both have one different statement in a nested block (catch). Two conditions
     * should be introduced in the block that execute the statements as configured.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseNestedCatch() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementNestedCatchCase(VariabilityType.OPTXOR);
        IfStaticConfigClassStatementInStatementListContainerOPTXOR refactoring = new IfStaticConfigClassStatementInStatementListContainerOPTXOR(ifElseRefactoringUtil);
        HashMap<String, Object> configurations = new HashMap<String, Object>();
        configurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, "");
        refactoring.refactor(vp, configurations);

        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPJavaSoftwareElement) vp.getLocation())
                .getJamoppElement();

        ClassMethod method = (ClassMethod) vpLocation.eContainer().eContainer();
        assertThat(method.getStatements().size(), equalTo(1));
        assertThat(method.getStatements().get(0), instanceOf(TryBlock.class));

        assertThat(vpLocation.getStatements().size(), equalTo(2));
        assertThat(vpLocation.getStatements().get(0), instanceOf(Condition.class));
        assertThat(vpLocation.getStatements().get(1), instanceOf(Condition.class));

        Condition cond1 = (Condition) vpLocation.getStatements().get(0);
        Condition cond2 = (Condition) vpLocation.getStatements().get(1);

        assertThat(((Block) cond1.getStatement()).getStatements().size(), equalTo(1));
        assertThat(((Block) cond2.getStatement()).getStatements().size(), equalTo(1));

        BigInteger val1 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) ((Block) cond1
                .getStatement()).getStatements().get(0));
        BigInteger val2 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) ((Block) cond2
                .getStatement()).getStatements().get(0));

        assertThat(val1, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val2, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val1, not(equalTo(val2)));
        
        RefactoringTestUtil.assertValidVPM(vp);
    }

    /**
     * Two variants that both have one different statement in a nested block (if-block). Two
     * conditions should be introduced in the block that execute the statements as configured.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseNestedCondition() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementNestedConditionCase(VariabilityType.OPTXOR);
        IfStaticConfigClassStatementInStatementListContainerOPTXOR refactoring = new IfStaticConfigClassStatementInStatementListContainerOPTXOR(ifElseRefactoringUtil);
        HashMap<String, Object> configurations = new HashMap<String, Object>();
        configurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, "");
        refactoring.refactor(vp, configurations);

        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPJavaSoftwareElement) vp.getLocation())
                .getJamoppElement();

        ClassMethod method = (ClassMethod) vpLocation.eContainer().eContainer();
        assertThat(method.getStatements().size(), equalTo(1));
        assertThat(method.getStatements().get(0), instanceOf(Condition.class));

        assertThat(vpLocation.getStatements().size(), equalTo(2));
        assertThat(vpLocation.getStatements().get(0), instanceOf(Condition.class));
        assertThat(vpLocation.getStatements().get(1), instanceOf(Condition.class));

        Condition cond1 = (Condition) vpLocation.getStatements().get(0);
        Condition cond2 = (Condition) vpLocation.getStatements().get(1);

        assertThat(((Block) cond1.getStatement()).getStatements().size(), equalTo(1));
        assertThat(((Block) cond2.getStatement()).getStatements().size(), equalTo(1));

        BigInteger val1 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) ((Block) cond1
                .getStatement()).getStatements().get(0));
        BigInteger val2 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) ((Block) cond2
                .getStatement()).getStatements().get(0));

        assertThat(val1, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val2, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val1, not(equalTo(val2)));
        
        RefactoringTestUtil.assertValidVPM(vp);
    }

    /**
     * Two variants that both have one different statement in a nested block (for). Two conditions
     * should be introduced in the block that execute the statements as configured.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseNestedFor() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementNestedForCase(VariabilityType.OPTXOR);
        IfStaticConfigClassStatementInStatementListContainerOPTXOR refactoring = new IfStaticConfigClassStatementInStatementListContainerOPTXOR(ifElseRefactoringUtil);
        HashMap<String, Object> configurations = new HashMap<String, Object>();
        configurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, "");
        refactoring.refactor(vp, configurations);

        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPJavaSoftwareElement) vp.getLocation())
                .getJamoppElement();

        ClassMethod method = (ClassMethod) vpLocation.eContainer().eContainer();
        assertThat(method.getStatements().size(), equalTo(1));
        assertThat(method.getStatements().get(0), instanceOf(ForLoop.class));

        assertThat(vpLocation.getStatements().size(), equalTo(2));
        assertThat(vpLocation.getStatements().get(0), instanceOf(Condition.class));
        assertThat(vpLocation.getStatements().get(1), instanceOf(Condition.class));

        Condition cond1 = (Condition) vpLocation.getStatements().get(0);
        Condition cond2 = (Condition) vpLocation.getStatements().get(1);

        assertThat(((Block) cond1.getStatement()).getStatements().size(), equalTo(1));
        assertThat(((Block) cond2.getStatement()).getStatements().size(), equalTo(1));

        BigInteger val1 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) ((Block) cond1
                .getStatement()).getStatements().get(0));
        BigInteger val2 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) ((Block) cond2
                .getStatement()).getStatements().get(0));

        assertThat(val1, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val2, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val1, not(equalTo(val2)));
        
        RefactoringTestUtil.assertValidVPM(vp);
    }

    /**
     * Two variants that both have one different statement in a nested block (while). Two conditions
     * should be introduced in the block that execute the statements as configured.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseNestedWhile() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementNestedWhileCase(VariabilityType.OPTXOR);
        IfStaticConfigClassStatementInStatementListContainerOPTXOR refactoring = new IfStaticConfigClassStatementInStatementListContainerOPTXOR(ifElseRefactoringUtil);
        HashMap<String, Object> configurations = new HashMap<String, Object>();
        configurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, "");
        refactoring.refactor(vp, configurations);

        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPJavaSoftwareElement) vp.getLocation())
                .getJamoppElement();

        ClassMethod method = (ClassMethod) vpLocation.eContainer().eContainer();
        assertThat(method.getStatements().size(), equalTo(1));
        assertThat(method.getStatements().get(0), instanceOf(WhileLoop.class));

        assertThat(vpLocation.getStatements().size(), equalTo(2));
        assertThat(vpLocation.getStatements().get(0), instanceOf(Condition.class));
        assertThat(vpLocation.getStatements().get(1), instanceOf(Condition.class));

        Condition cond1 = (Condition) vpLocation.getStatements().get(0);
        Condition cond2 = (Condition) vpLocation.getStatements().get(1);

        assertThat(((Block) cond1.getStatement()).getStatements().size(), equalTo(1));
        assertThat(((Block) cond2.getStatement()).getStatements().size(), equalTo(1));

        BigInteger val1 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) ((Block) cond1
                .getStatement()).getStatements().get(0));
        BigInteger val2 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) ((Block) cond2
                .getStatement()).getStatements().get(0));

        assertThat(val1, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val2, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val1, not(equalTo(val2)));
        
        RefactoringTestUtil.assertValidVPM(vp);
    }
    
    /**
     * Two variants that both have one different statement in a nested block (case). Two conditions
     * should be introduced in the block that execute the statements as configured.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseNestedSwitchCase() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementNestedSwitchCaseCase(VariabilityType.OPTXOR);
        IfStaticConfigClassStatementInStatementListContainerOPTXOR refactoring = new IfStaticConfigClassStatementInStatementListContainerOPTXOR(ifElseRefactoringUtil);
        HashMap<String, Object> configurations = new HashMap<String, Object>();
        configurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, "");
        refactoring.refactor(vp, configurations);

        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPJavaSoftwareElement) vp.getLocation())
                .getJamoppElement();

        ClassMethod method = (ClassMethod) vpLocation.eContainer().eContainer();
        assertThat(method.getStatements().size(), equalTo(1));
        assertThat(method.getStatements().get(0), instanceOf(Switch.class));

        assertThat(vpLocation.getStatements().size(), equalTo(3));
        assertThat(vpLocation.getStatements().get(0), instanceOf(Condition.class));
        assertThat(vpLocation.getStatements().get(1), instanceOf(Condition.class));

        Condition cond1 = (Condition) vpLocation.getStatements().get(0);
        Condition cond2 = (Condition) vpLocation.getStatements().get(1);

        assertThat(((Block) cond1.getStatement()).getStatements().size(), equalTo(1));
        assertThat(((Block) cond2.getStatement()).getStatements().size(), equalTo(1));

        BigInteger val1 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) ((Block) cond1
                .getStatement()).getStatements().get(0));
        BigInteger val2 = RefactoringTestUtil.getValueOfSysoExpression((ExpressionStatement) ((Block) cond2
                .getStatement()).getStatements().get(0));

        assertThat(val1, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val2, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val1, not(equalTo(val2)));
        
        RefactoringTestUtil.assertValidVPM(vp);
    }
}
