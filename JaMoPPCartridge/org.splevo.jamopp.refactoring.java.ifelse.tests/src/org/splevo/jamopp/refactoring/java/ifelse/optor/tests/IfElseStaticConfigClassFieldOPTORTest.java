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
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.literals.DecimalIntegerLiteral;
import org.emftext.language.java.literals.NullLiteral;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.modifiers.Static;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.types.Int;
import org.hamcrest.Matcher;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassFieldOPTOR;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Contains the tests for the {@link IfElseStaticConfigClassFieldOPTOR} class.
 */
public class IfElseStaticConfigClassFieldOPTORTest {

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
     * <li>Variant 1: Has a field</li>
     * <li>Variant 2: Has a field</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>true</code>
     */
    @Test
    public void testIfCanBeAppliedWithValidVP() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Field fieldMock = RefactoringTestUtil.getFieldMock();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, fieldMock, fieldMock);

        IfElseStaticConfigClassFieldOPTOR refactoring = new IfElseStaticConfigClassFieldOPTOR();

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
     * <li>Variant 1: Has a field</li>
     * <li>Variant 2: Has a field</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidBindingTime() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = MembersFactory.eINSTANCE.createField();
        Commentable implEl2 = MembersFactory.eINSTANCE.createField();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.LOAD_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassFieldOPTOR refactoring = new IfElseStaticConfigClassFieldOPTOR();

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
     * <li>Variant 1: Has a field</li>
     * <li>Variant 2: Has a field</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidExtensibility() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = MembersFactory.eINSTANCE.createField();
        Commentable implEl2 = MembersFactory.eINSTANCE.createField();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.YES,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassFieldOPTOR refactoring = new IfElseStaticConfigClassFieldOPTOR();

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
     * <li>Variant 1: Has a field</li>
     * <li>Variant 2: Has a field</li>
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

        IfElseStaticConfigClassFieldOPTOR refactoring = new IfElseStaticConfigClassFieldOPTOR();

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
     * <li>Variant 1: Has a field</li>
     * <li>Variant 2: Has a field</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidLocation() {
        Commentable location = MembersFactory.eINSTANCE.createClassMethod();
        Commentable implEl1 = MembersFactory.eINSTANCE.createField();
        Commentable implEl2 = MembersFactory.eINSTANCE.createField();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassFieldOPTOR refactoring = new IfElseStaticConfigClassFieldOPTOR();

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
     * <li>Variant 1: Has a field</li>
     * <li>Variant 2: Has a method</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
     */
    @Test
    public void testIfCanBeAppliedWithInvalidVariantElements() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = MembersFactory.eINSTANCE.createField();
        Commentable implEl2 = MembersFactory.eINSTANCE.createClassMethod();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfElseStaticConfigClassFieldOPTOR refactoring = new IfElseStaticConfigClassFieldOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * <strong>Description</strong><br/>
     * Merges variable fields (of different names) into the base.
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
     * <li>Variant 1: private int a = 0;</li>
     * <li>Variant 2: private int b = 0;</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * Both fields must be contained in the base.
     * 
     * @throws Exception
     *             An unexpected exception occurred.
     */
    @Test
    public void testRefactorCaseFieldDefault() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getFieldDefaultCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassFieldOPTOR refactoring = new IfElseStaticConfigClassFieldOPTOR();
        refactoring.refactor(vp);

        Class vpLocation = (Class) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getMembers().size(), equalTo(2));
        assertThat(vpLocation.getMembers().get(0), instanceOf(Field.class));
        assertThat(vpLocation.getMembers().get(1), instanceOf(Field.class));
        Field field1 = (Field) vpLocation.getMembers().get(0);
        Field field2 = (Field) vpLocation.getMembers().get(1);
        assertThat(field1.getName(), anyOf(equalTo("a"), equalTo("b")));
        assertThat(field2.getName(), anyOf(equalTo("a"), equalTo("b")));
        assertThat(field1.getName(), not(equalTo(field2.getName())));
        assertThat(field1.getTypeReference().getTarget(), instanceOf(Int.class));
        assertThat(field2.getTypeReference().getTarget(), instanceOf(Int.class));
    }

    /**
     * <strong>Description</strong><br/>
     * Merges variable fields (same name and type but different initial values) into the base.
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
     * <li>Variant 1: private int a = 0;</li>
     * <li>Variant 2: private int b = 1;</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * The base must contain one declaration and a static block that has two conditions that
     * initialize the field with the according value.
     * 
     * @throws Exception
     *             An unexpected exception occurred.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testRefactorCaseFieldDifferentInitialValues() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getFieldDifferentInitialValuesCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassFieldOPTOR refactoring = new IfElseStaticConfigClassFieldOPTOR();
        refactoring.refactor(vp);

        Class vpLocation = (Class) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getMembers().size(), equalTo(2));
        Matcher<Iterable<Member>> hasItems = hasItems(instanceOf(Field.class), instanceOf(Block.class));
        assertThat(vpLocation.getMembers(), hasItems);

        Field field = null;
        Block block = null;

        if (vpLocation.getMembers().get(0) instanceof Field) {
            field = (Field) vpLocation.getMembers().get(0);
            block = (Block) vpLocation.getMembers().get(1);
        } else {
            field = (Field) vpLocation.getMembers().get(1);
            block = (Block) vpLocation.getMembers().get(0);
        }

        assertThat(field.getName(), equalTo("a"));
        assertThat(field.getInitialValue(), instanceOf(NullLiteral.class));

        assertThat(block.getModifiers().size(), equalTo(1));
        assertThat(block.getModifiers().get(0), instanceOf(Static.class));
        assertThat(block.getStatements().size(), equalTo(2));
        assertThat(block.getStatements().get(0), instanceOf(Condition.class));
        assertThat(block.getStatements().get(1), instanceOf(Condition.class));

        Condition cond1 = (Condition) block.getStatements().get(0);
        Condition cond2 = (Condition) block.getStatements().get(1);

        assertThat(cond1.getCondition(), notNullValue());
        assertThat(cond2.getCondition(), notNullValue());
        assertThat(cond1.getElseStatement(), nullValue());
        assertThat(cond2.getElseStatement(), nullValue());
        assertThat(cond1.getStatement(), instanceOf(Block.class));
        assertThat(cond2.getStatement(), instanceOf(Block.class));
        assertThat(((Block) cond1.getStatement()).getStatements().size(), equalTo(1));
        assertThat(((Block) cond2.getStatement()).getStatements().size(), equalTo(1));
        assertThat(((Block) cond1.getStatement()).getStatements().get(0), instanceOf(ExpressionStatement.class));
        assertThat(((Block) cond2.getStatement()).getStatements().get(0), instanceOf(ExpressionStatement.class));

        ExpressionStatement exprStatement1 = (ExpressionStatement) ((Block) cond1.getStatement()).getStatements()
                .get(0);
        ExpressionStatement exprStatement2 = (ExpressionStatement) ((Block) cond2.getStatement()).getStatements()
                .get(0);

        assertThat(exprStatement1.getExpression(), instanceOf(AssignmentExpression.class));
        assertThat(exprStatement2.getExpression(), instanceOf(AssignmentExpression.class));
        assertThat(((AssignmentExpression) exprStatement1.getExpression()).getValue(),
                instanceOf(DecimalIntegerLiteral.class));
        assertThat(((AssignmentExpression) exprStatement2.getExpression()).getValue(),
                instanceOf(DecimalIntegerLiteral.class));

        assertThat(
                ((DecimalIntegerLiteral) ((AssignmentExpression) exprStatement1.getExpression()).getValue())
                        .getDecimalValue(),
                anyOf(equalTo(BigInteger.valueOf(0)), equalTo(BigInteger.valueOf(1))));
        assertThat(
                ((DecimalIntegerLiteral) ((AssignmentExpression) exprStatement2.getExpression()).getValue())
                        .getDecimalValue(),
                anyOf(equalTo(BigInteger.valueOf(0)), equalTo(BigInteger.valueOf(1))));
        assertThat(
                ((DecimalIntegerLiteral) ((AssignmentExpression) exprStatement1.getExpression()).getValue())
                        .getDecimalValue(),
                not(equalTo(((DecimalIntegerLiteral) ((AssignmentExpression) exprStatement2.getExpression()).getValue())
                        .getDecimalValue())));
    }
}
