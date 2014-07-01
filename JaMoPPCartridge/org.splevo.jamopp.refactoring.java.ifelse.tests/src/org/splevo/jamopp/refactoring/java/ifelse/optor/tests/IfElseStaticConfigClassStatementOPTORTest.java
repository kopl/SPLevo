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
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.literals.DecimalIntegerLiteral;
import org.emftext.language.java.literals.NullLiteral;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.Reference;
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
import org.emftext.language.java.types.Int;
import org.emftext.language.java.types.Short;
import org.emftext.language.java.variables.LocalVariable;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassConstructorOPTOR;
import org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassStatementOPTOR;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Contains the tests for the {@link IfElseStaticConfigClassStatementOPTOR} class.
 */
public class IfElseStaticConfigClassStatementOPTORTest {
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
     * <li>Variant 1: One statement</li>
     * <li>Variant 2: One statement</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>true</code>
     */
    @Test
    public void testIfCanBeAppliedWithValidVP() {
        Commentable location = MembersFactory.eINSTANCE.createClassMethod();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = StatementsFactory.eINSTANCE.createEmptyStatement();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassStatementOPTOR refactoring = new IfElseStaticConfigClassStatementOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(true));
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
     * <li>Variant 1: One statement</li>
     * <li>Variant 2: One statement</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidBindingTime() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = StatementsFactory.eINSTANCE.createEmptyStatement();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.LOAD_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassConstructorOPTOR refactoring = new IfElseStaticConfigClassConstructorOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
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
     * <li>Variant 1: One statement</li>
     * <li>Variant 2: One statement</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidExtensibility() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = StatementsFactory.eINSTANCE.createEmptyStatement();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.YES,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassConstructorOPTOR refactoring = new IfElseStaticConfigClassConstructorOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
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
     * <li>Variant 1: One statement</li>
     * <li>Variant 2: One statement</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidVarType() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = StatementsFactory.eINSTANCE.createEmptyStatement();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassConstructorOPTOR refactoring = new IfElseStaticConfigClassConstructorOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
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
     * <li>Variant 1: One statement</li>
     * <li>Variant 2: One statement</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidLocation() {
        Commentable location = ClassifiersFactory.eINSTANCE.createInterface();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = StatementsFactory.eINSTANCE.createEmptyStatement();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassConstructorOPTOR refactoring = new IfElseStaticConfigClassConstructorOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
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
     * <li>Variant 1: One statement</li>
     * <li>Variant 2: One method</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidVariantElements() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = StatementsFactory.eINSTANCE.createEmptyStatement();
        Commentable implEl2 = MembersFactory.eINSTANCE.createClassMethod();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassConstructorOPTOR refactoring = new IfElseStaticConfigClassConstructorOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * <strong>Description</strong><br/>
     * Checks whether the refactoring extracts the variant-specific methods and introduces the if
     * correctly.
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
     * <li>Variant 1: 3 statements, first local variable of type int</li>
     * <li>Variant 2: 3 statements, first local variable of type short</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * The location contains two conditions and all the original statements got extracted into
     * variant-specific methods.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseLocalVariableDiffTypes() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementLocalVarDiffTypesCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassStatementOPTOR refactoring = new IfElseStaticConfigClassStatementOPTOR();
        refactoring.refactor(vp);

        ClassMethod vpLocation = (ClassMethod) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        Class containingClass = (Class) vpLocation.eContainer();

        // must have three methods
        assertThat(containingClass.getMethods().size(), equalTo(3));

        // verify extracted methods and their contents
        ClassMethod firstVariantSpecificMethod = (ClassMethod) containingClass.getMethods().get(1);
        ClassMethod secondVariantSpecificMethod = (ClassMethod) containingClass.getMethods().get(2);
        assertThat(firstVariantSpecificMethod.getStatements().size(), equalTo(2));
        assertThat(secondVariantSpecificMethod.getStatements().size(), equalTo(2));
        assertThat(firstVariantSpecificMethod.getStatements().get(0), instanceOf(LocalVariableStatement.class));
        assertThat(secondVariantSpecificMethod.getStatements().get(0), instanceOf(LocalVariableStatement.class));
        LocalVariable firstVariableFirstVariant = ((LocalVariableStatement) firstVariantSpecificMethod.getStatements()
                .get(0)).getVariable();
        LocalVariable firstVariableSecondVariant = ((LocalVariableStatement) secondVariantSpecificMethod
                .getStatements().get(0)).getVariable();
        assertThat(firstVariableFirstVariant.getTypeReference().getTarget(),
                anyOf(instanceOf(Int.class), instanceOf(Short.class)));
        assertThat(firstVariableSecondVariant.getTypeReference().getTarget(),
                anyOf(instanceOf(Int.class), instanceOf(Short.class)));

        // verify vp location contents
        assertThat(vpLocation.getStatements().size(), equalTo(2));
        assertThat(vpLocation.getStatements().get(0), instanceOf(Condition.class));
        assertThat(vpLocation.getStatements().get(1), instanceOf(Condition.class));
        Condition firstCond = (Condition) vpLocation.getStatements().get(0);
        Condition secondCond = (Condition) vpLocation.getStatements().get(1);
        assertThat(((Block) firstCond.getStatement()).getStatements().size(), equalTo(1));
        assertThat(((Block) secondCond.getStatement()).getStatements().size(), equalTo(1));
        assertThat(((Block) firstCond.getStatement()).getStatements().get(0), instanceOf(ExpressionStatement.class));
        assertThat(((Block) secondCond.getStatement()).getStatements().get(0), instanceOf(ExpressionStatement.class));
        ExpressionStatement statementInFirstIf = (ExpressionStatement) ((Block) firstCond.getStatement())
                .getStatements().get(0);
        ExpressionStatement statementInSecondIf = (ExpressionStatement) ((Block) secondCond.getStatement())
                .getStatements().get(0);
        assertThat(statementInFirstIf.getExpression(), instanceOf(MethodCall.class));
        assertThat(statementInSecondIf.getExpression(), instanceOf(MethodCall.class));
        assertThat((ClassMethod) ((MethodCall) statementInFirstIf.getExpression()).getTarget(),
                equalTo(firstVariantSpecificMethod));
        assertThat((ClassMethod) ((MethodCall) statementInSecondIf.getExpression()).getTarget(),
                equalTo(secondVariantSpecificMethod));
    }

    /**
     * <strong>Description</strong><br/>
     * Checks whether the refactoring for the case of variables of the same type that are shared
     * among multiple variants. Local variable declarations must be split into declaration and
     * assignment if they have an initial value. The declaration must be moved in front of the first
     * if while the assignment must be contained in the variant-specific if.
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
     * <li>Variant 1: 2 statements, first is a local variable with initial value of 1</li>
     * <li>Variant 2: 2 statements, first is a local variable with initial value of 2</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * The location contains two conditions and all the original statements got extracted into
     * variant-specific methods.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseLocalVariableEqualType() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementLocalVarEqualTypeCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassStatementOPTOR refactoring = new IfElseStaticConfigClassStatementOPTOR();
        refactoring.refactor(vp);

        ClassMethod vpLocation = (ClassMethod) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();

        assertThat(vpLocation.getStatements().size(), equalTo(4));
        assertThat(vpLocation.getStatements().get(0), instanceOf(LocalVariableStatement.class));
        assertThat(vpLocation.getStatements().get(1), instanceOf(Condition.class));
        assertThat(vpLocation.getStatements().get(2), instanceOf(Condition.class));
        assertThat(vpLocation.getStatements().get(3), instanceOf(ExpressionStatement.class));

        BigInteger val = getValueOfSysoExpression((ExpressionStatement) vpLocation.getStatements().get(3));
        assertThat(val, equalTo(BigInteger.valueOf(1)));

        LocalVariableStatement localVarStat = (LocalVariableStatement) vpLocation.getStatements().get(0);
        Condition firstCond = (Condition) vpLocation.getStatements().get(1);
        Condition secondCond = (Condition) vpLocation.getStatements().get(2);

        assertThat(localVarStat.getVariable().getName(), equalTo("a"));
        assertThat(localVarStat.getVariable().getInitialValue(), instanceOf(NullLiteral.class));

        assertThat(firstCond.getStatement(), instanceOf(Block.class));
        assertThat(secondCond.getStatement(), instanceOf(Block.class));
        EList<Statement> firstCondIfBlockStatements = ((Block) firstCond.getStatement()).getStatements();
        EList<Statement> secondCondIfBlockStatements = ((Block) secondCond.getStatement()).getStatements();
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

        DecimalIntegerLiteral firstAssignmentExpr = 
                (DecimalIntegerLiteral) ((AssignmentExpression) ((ExpressionStatement) firstCondIfBlockStatements
                .get(0)).getExpression()).getValue();
        DecimalIntegerLiteral secondAssignmentExpr = 
                (DecimalIntegerLiteral) ((AssignmentExpression) ((ExpressionStatement) secondCondIfBlockStatements
                .get(0)).getExpression()).getValue();

        assertThat(firstAssignmentExpr.getDecimalValue(),
                anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(secondAssignmentExpr.getDecimalValue(),
                anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(firstAssignmentExpr.getDecimalValue(), not(equalTo(secondAssignmentExpr.getDecimalValue())));
    }

    /**
     * <strong>Description</strong><br/>
     * Two variants that both share a statement. The Integration variant has one additional
     * statement at the end.
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
     * <li>Variant 1: 1 statement</li>
     * <li>Variant 2: 2 statements, first is equal to the other variant.</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * The location contains the common statement, followed by a condition.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseOneAdd() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementOneAddCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassStatementOPTOR refactoring = new IfElseStaticConfigClassStatementOPTOR();
        refactoring.refactor(vp);

        ClassMethod vpLocation = (ClassMethod) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();

        assertThat(vpLocation.getStatements().size(), equalTo(2));
        assertThat(vpLocation.getStatements().get(0), instanceOf(ExpressionStatement.class));
        assertThat(vpLocation.getStatements().get(1), instanceOf(Condition.class));

        Condition cond = (Condition) vpLocation.getStatements().get(1);
        assertThat(cond.getStatement(), instanceOf(Block.class));
        assertThat(((Block) cond.getStatement()).getStatements().size(), equalTo(1));

        BigInteger val1 = getValueOfSysoExpression((ExpressionStatement) vpLocation.getStatements().get(0));
        BigInteger val2 = getValueOfSysoExpression((ExpressionStatement) ((Block) cond.getStatement()).getStatements()
                .get(0));

        assertThat(val1, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val2, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val1, not(equalTo(val2)));
    }

    /**
     * <strong>Description</strong><br/>
     * Two variants that both have a different statements. Conditions must be introduced to decide
     * between the statements.
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
     * <li>Variant 1: 1 statement</li>
     * <li>Variant 2: 1 statement</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * The location contains two conditions, each having the according statement in it's if block.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseOneEither() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementOneEitherCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassStatementOPTOR refactoring = new IfElseStaticConfigClassStatementOPTOR();
        refactoring.refactor(vp);

        ClassMethod vpLocation = (ClassMethod) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();

        assertThat(vpLocation.getStatements().size(), equalTo(2));
        assertThat(vpLocation.getStatements().get(0), instanceOf(Condition.class));
        assertThat(vpLocation.getStatements().get(1), instanceOf(Condition.class));

        Condition cond1 = (Condition) vpLocation.getStatements().get(0);
        Condition cond2 = (Condition) vpLocation.getStatements().get(1);
        assertThat(cond1.getStatement(), instanceOf(Block.class));
        assertThat(cond2.getStatement(), instanceOf(Block.class));
        assertThat(((Block) cond1.getStatement()).getStatements().size(), equalTo(1));
        assertThat(((Block) cond2.getStatement()).getStatements().size(), equalTo(1));

        BigInteger val1 = getValueOfSysoExpression((ExpressionStatement) ((Block) cond1.getStatement()).getStatements()
                .get(0));
        BigInteger val2 = getValueOfSysoExpression((ExpressionStatement) ((Block) cond2.getStatement()).getStatements()
                .get(0));

        assertThat(val1, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val2, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val1, not(equalTo(val2)));
    }

    /**
     * <strong>Description</strong><br/>
     * Checks whether the refactoring handles nested variability (try-block) correctly.
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
     * <li>Variant 1: try-catch with one statement in the try-block</li>
     * <li>Variant 2: try-catch with a different statement in the try-block</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * The location contains the try-catch. While the try stays unchanged, the catch-block has two
     * conditions that each has the according statement in its if block.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseNestedTry() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementNestedTryCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassStatementOPTOR refactoring = new IfElseStaticConfigClassStatementOPTOR();
        refactoring.refactor(vp);

        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) vp.getLocation())
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

        BigInteger val1 = getValueOfSysoExpression((ExpressionStatement) ((Block) cond1.getStatement()).getStatements()
                .get(0));
        BigInteger val2 = getValueOfSysoExpression((ExpressionStatement) ((Block) cond2.getStatement()).getStatements()
                .get(0));

        assertThat(val1, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val2, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val1, not(equalTo(val2)));
    }

    /**
     * <strong>Description</strong><br/>
     * Checks whether the refactoring handles nested variability (catch-block) correctly.
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
     * <li>Variant 1: try-catch with one statement in the catch-block</li>
     * <li>Variant 2: try-catch with a different statement in the catch-block</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * The location contains the try-catch. While the try stays unchanged, the catch-block has two
     * conditions that each has the according statement in its if block.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseNestedCatch() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementNestedCatchCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassStatementOPTOR refactoring = new IfElseStaticConfigClassStatementOPTOR();
        refactoring.refactor(vp);

        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) vp.getLocation())
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

        BigInteger val1 = getValueOfSysoExpression((ExpressionStatement) ((Block) cond1.getStatement()).getStatements()
                .get(0));
        BigInteger val2 = getValueOfSysoExpression((ExpressionStatement) ((Block) cond2.getStatement()).getStatements()
                .get(0));

        assertThat(val1, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val2, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val1, not(equalTo(val2)));
    }

    /**
     * <strong>Description</strong><br/>
     * Checks whether the refactoring handles nested variability (condition-if-block) correctly.
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
     * <li>Variant 1: condition with one statement in the if-block</li>
     * <li>Variant 2: condition with a different statement in the if-block</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * The location contains the condition. The if-block has two conditions that each has the
     * according statement in its if block.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseNestedCondition() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementNestedConditionCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassStatementOPTOR refactoring = new IfElseStaticConfigClassStatementOPTOR();
        refactoring.refactor(vp);

        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) vp.getLocation())
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

        BigInteger val1 = getValueOfSysoExpression((ExpressionStatement) ((Block) cond1.getStatement()).getStatements()
                .get(0));
        BigInteger val2 = getValueOfSysoExpression((ExpressionStatement) ((Block) cond2.getStatement()).getStatements()
                .get(0));

        assertThat(val1, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val2, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val1, not(equalTo(val2)));
    }

    /**
     * <strong>Description</strong><br/>
     * Checks whether the refactoring handles nested variability (for-block) correctly.
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
     * <li>Variant 1: for containing one statement</li>
     * <li>Variant 2: for containing a different statement</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * The location contains the for. The for contains two conditions that each has the according
     * statement in its if block.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseNestedFor() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementNestedForCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassStatementOPTOR refactoring = new IfElseStaticConfigClassStatementOPTOR();
        refactoring.refactor(vp);

        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) vp.getLocation())
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

        BigInteger val1 = getValueOfSysoExpression((ExpressionStatement) ((Block) cond1.getStatement()).getStatements()
                .get(0));
        BigInteger val2 = getValueOfSysoExpression((ExpressionStatement) ((Block) cond2.getStatement()).getStatements()
                .get(0));

        assertThat(val1, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val2, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val1, not(equalTo(val2)));
    }

    /**
     * <strong>Description</strong><br/>
     * Checks whether the refactoring handles nested variability (while-block) correctly.
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
     * <li>Variant 1: for containing one statement</li>
     * <li>Variant 2: for containing a different statement</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * The location contains the while. The while contains two conditions that each has the
     * according statement in its if block.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseNestedWhile() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementNestedWhileCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassStatementOPTOR refactoring = new IfElseStaticConfigClassStatementOPTOR();
        refactoring.refactor(vp);

        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) vp.getLocation())
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

        BigInteger val1 = getValueOfSysoExpression((ExpressionStatement) ((Block) cond1.getStatement()).getStatements()
                .get(0));
        BigInteger val2 = getValueOfSysoExpression((ExpressionStatement) ((Block) cond2.getStatement()).getStatements()
                .get(0));

        assertThat(val1, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val2, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val1, not(equalTo(val2)));
    }

    /**
     * <strong>Description</strong><br/>
     * Checks whether the refactoring handles nested variability (switch-case) correctly.
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
     * <li>Variant 1: case block containing one statement</li>
     * <li>Variant 2: case block containing a different statement</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * The location contains the switch-case. The only case block contains two conditions that each
     * has the according statement in its if block.
     * 
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testRefactorCaseNestedSwitchCase() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getStatementNestedSwitchCaseCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassStatementOPTOR refactoring = new IfElseStaticConfigClassStatementOPTOR();
        refactoring.refactor(vp);

        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) vp.getLocation())
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

        BigInteger val1 = getValueOfSysoExpression((ExpressionStatement) ((Block) cond1.getStatement()).getStatements()
                .get(0));
        BigInteger val2 = getValueOfSysoExpression((ExpressionStatement) ((Block) cond2.getStatement()).getStatements()
                .get(0));

        assertThat(val1, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val2, anyOf(equalTo(BigInteger.valueOf(1)), equalTo(BigInteger.valueOf(2))));
        assertThat(val1, not(equalTo(val2)));
    }

    private BigInteger getValueOfSysoExpression(ExpressionStatement statement) {
        assertThat(statement, instanceOf(ExpressionStatement.class));
        Reference out = ((IdentifierReference) ((ExpressionStatement) statement).getExpression()).getNext();
        assertThat(out, notNullValue());
        Reference println = out.getNext();
        assertThat(println, notNullValue());
        assertThat(println, instanceOf(MethodCall.class));
        assertThat(((MethodCall) println).getArguments().size(), equalTo(1));
        assertThat(((MethodCall) println).getArguments().get(0), instanceOf(DecimalIntegerLiteral.class));

        BigInteger value = ((DecimalIntegerLiteral) ((MethodCall) println).getArguments().get(0)).getDecimalValue();
        return value;
    }
}
