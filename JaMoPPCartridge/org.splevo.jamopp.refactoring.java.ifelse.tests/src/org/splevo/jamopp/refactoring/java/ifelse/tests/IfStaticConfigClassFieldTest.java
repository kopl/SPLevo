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
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.util.HashMap;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.literals.DecimalIntegerLiteral;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.types.Int;
import org.hamcrest.Matcher;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassField;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.jamopp.refactoring.java.ifelse.util.FullyAutomatedIfElseRefactoringUtil;
import org.splevo.jamopp.vpm.software.JaMoPPJavaSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoringService;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Contains the tests for the {@link IfStaticConfigClassField} class.
 */
public class IfStaticConfigClassFieldTest {
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
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Field fieldMock = RefactoringTestUtil.getFieldMock();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, fieldMock, fieldMock);

        IfStaticConfigClassField refactoring = new IfStaticConfigClassField(ifElseRefactoringUtil);

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(true));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have a location
     * that is not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidLocation() {
        Commentable location = MembersFactory.eINSTANCE.createClassMethod();
        Commentable implEl1 = MembersFactory.eINSTANCE.createField();
        Commentable implEl2 = MembersFactory.eINSTANCE.createField();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfStaticConfigClassField refactoring = new IfStaticConfigClassField(ifElseRefactoringUtil);

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have variants
     * with implementing elements that are not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidVariantElements() {
        Commentable location = ClassifiersFactory.eINSTANCE.createClass();
        Commentable implEl1 = MembersFactory.eINSTANCE.createField();
        Commentable implEl2 = MembersFactory.eINSTANCE.createClassMethod();
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        IfStaticConfigClassField refactoring = new IfStaticConfigClassField(ifElseRefactoringUtil);

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * Tests whether the refactoring adds a missing field from the integration variant correctly
     * into the base.
     * 
     * @throws Exception
     *             In case of an unexpected exception.
     */
    @Test
    public void testRefactorCaseFieldAdd() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getFieldAddCase(VariabilityType.OPTXOR);
        IfStaticConfigClassField refactoring = new IfStaticConfigClassField(ifElseRefactoringUtil);
        HashMap<String, Object> configurations = new HashMap<String, Object>();
        configurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, "");
        refactoring.refactor(vp, configurations);
        
        // location has two fields
        Class vpLocation = (Class) ((JaMoPPJavaSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getMembers().size(), equalTo(2));
        assertThat(vpLocation.getMembers().get(0), instanceOf(Field.class));
        assertThat(vpLocation.getMembers().get(1), instanceOf(Field.class));

        Field field1 = (Field) vpLocation.getMembers().get(0);
        Field field2 = (Field) vpLocation.getMembers().get(1);

        // fields have correct names and types
        assertThat(field1.getName(), anyOf(equalTo("a"), equalTo("b")));
        assertThat(field2.getName(), anyOf(equalTo("a"), equalTo("b")));
        assertThat(field1.getName(), not(equalTo(field2.getName())));
        assertThat(field1.getTypeReference().getTarget(), instanceOf(Int.class));
        assertThat(field2.getTypeReference().getTarget(), instanceOf(Int.class));
        
        // verify correct VPM
        RefactoringTestUtil.assertValidVPM(vp);
    }

    /**
     * Tests whether the refactoring merges common fields with different initial values correctly.
     * Refactoring should build a block to do the initializations.
     * 
     * @throws Exception
     *             An unexpected exception occurred.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testRefactorCaseFieldDifferentInitialValues() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getFieldDifferentInitialValuesCase(VariabilityType.OPTXOR);
        IfStaticConfigClassField refactoring = new IfStaticConfigClassField(ifElseRefactoringUtil);
        HashMap<String, Object> configurations = new HashMap<String, Object>();
        configurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, "");
        refactoring.refactor(vp, configurations);

        // location has two members where one is a field
        Class vpLocation = (Class) ((JaMoPPJavaSoftwareElement) vp.getLocation()).getJamoppElement();
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

        // correct field name and initial value
        assertThat(field.getName(), equalTo("a"));

        // correct static block
        assertThat(block.getModifiers().size(), equalTo(0));
        assertThat(block.getStatements().size(), equalTo(2));
        assertThat(block.getStatements().get(0), instanceOf(Condition.class));
        assertThat(block.getStatements().get(1), instanceOf(Condition.class));

        Condition cond1 = (Condition) block.getStatements().get(0);
        Condition cond2 = (Condition) block.getStatements().get(1);

        // correct static block contents
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

        // assert correct values of the assignments
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

        BigInteger decimalValue1 = ((DecimalIntegerLiteral) ((AssignmentExpression) exprStatement1.getExpression())
                .getValue()).getDecimalValue();
        BigInteger decimalValue2 = ((DecimalIntegerLiteral) ((AssignmentExpression) exprStatement2.getExpression())
                .getValue()).getDecimalValue();
        
        assertThat(decimalValue1, anyOf(equalTo(BigInteger.valueOf(0)), equalTo(BigInteger.valueOf(1))));
        assertThat(decimalValue2, anyOf(equalTo(BigInteger.valueOf(0)), equalTo(BigInteger.valueOf(1))));
        assertThat(decimalValue1, not(equalTo(decimalValue2)));
        
        // verify correct VPM
        RefactoringTestUtil.assertValidVPM(vp);
    }
}
