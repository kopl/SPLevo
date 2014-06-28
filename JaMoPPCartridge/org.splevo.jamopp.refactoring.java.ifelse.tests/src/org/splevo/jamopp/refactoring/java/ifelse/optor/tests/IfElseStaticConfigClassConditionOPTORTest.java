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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

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
     * <li>Location: Condition</li>
     * <li>Variant 1: Has a statement</li>
     * <li>Variant 2: Has a statement</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>true</code>
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
     * <strong>Description</strong><br/>
     * Checks whether the method handles an incorrect binding time correctly.
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
     * <li>Location: Condition</li>
     * <li>Variant 1: Has a statement</li>
     * <li>Variant 2: Has a statement</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
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
     * <strong>Description</strong><br/>
     * Checks whether the method handles an incorrect extensibility correctly.
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
     * <li>Location: Condition</li>
     * <li>Variant 1: Has a statement</li>
     * <li>Variant 2: Has a statement</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
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
     * <strong>Description</strong><br/>
     * Checks whether the method handles an incorrect variability type correctly.
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
     * <li>Location: Condition</li>
     * <li>Variant 1: Has a statement</li>
     * <li>Variant 2: Has a statement</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
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
     * <strong>Description</strong><br/>
     * Checks whether the method handles an incorrect variation point location correctly.
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
     * <li>Variant 1: Has a statement</li>
     * <li>Variant 2: Has a statement</li>
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

        IfElseStaticConfigClassConditionOPTOR refactoring = new IfElseStaticConfigClassConditionOPTOR();

        assertThat(refactoring.canBeAppliedTo(vpMock), equalTo(false));
    }

    /**
     * <strong>Description</strong><br/>
     * Checks whether the method handles incorrect variant elements correctly.
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
     * <li>Location: Condition</li>
     * <li>Variant 1: Has a statement</li>
     * <li>Variant 2: Has a method</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * <code>false</code>
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
     * <strong>Description</strong><br/>
     * Merges conditions with different else statements.
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
     * <li>Location: Condition</li>
     * <li>Variant 1: 2 conditions in a chain.</li>
     * <li>Variant 2: 3 conditions in a chain where the second is new.</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * Three conditions in a chain: The default condition, the leading variant condition and the
     * integration variant condition. The leading condition has one condition in its if block, the
     * integration has two.
     * 
     * @throws Exception
     *             An unexpected exception occurred.
     */
    @Test
    public void testRefactorCaseConditionAddCond() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getConditionAddCondCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassConditionOPTOR refactoring = new IfElseStaticConfigClassConditionOPTOR();
        refactoring.refactor(vp);

        Condition vpLocation = (Condition) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getStatement(), instanceOf(Block.class));
        assertThat(vpLocation.getElseStatement(), instanceOf(Condition.class));

        Condition varCond1 = (Condition) vpLocation.getElseStatement();
        assertThat(varCond1.getStatement(), instanceOf(Block.class));
        Block varCondBlock1 = (Block) varCond1.getStatement();
        assertThat(varCondBlock1.getStatements().size(), equalTo(1));
        assertThat(varCondBlock1.getStatements().get(0), instanceOf(Condition.class));
        Condition block1Cond = (Condition) varCondBlock1.getStatements().get(0);
        assertThat(((Block) block1Cond.getStatement()).getStatements().size(), equalTo(1));

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
     * <strong>Description</strong><br/>
     * Merges conditions with different else statements.
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
     * <li>Location: Condition</li>
     * <li>Variant 1: 2 conditions in a chain.</li>
     * <li>Variant 2: 3 conditions in a chain where the second is new.</li>
     * </ul>
     * 
     * <strong>Expected Output</strong><br/>
     * Three conditions in a chain: The default condition, the leading variant condition and the
     * integration variant condition. The leading condition has one condition in its if block, the
     * integration has two.
     * 
     * @throws Exception
     *             An unexpected exception occurred.
     */
    @Test
    public void testRefactorCaseConditionAddStatement() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getConditionAddStatementCase(VariabilityType.OPTOR);
        IfElseStaticConfigClassConditionOPTOR refactoring = new IfElseStaticConfigClassConditionOPTOR();
        refactoring.refactor(vp);

        Condition vpLocation = (Condition) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getStatement(), instanceOf(Block.class));
        assertThat(vpLocation.getElseStatement(), instanceOf(Condition.class));

        Condition varCond1 = (Condition) vpLocation.getElseStatement();
        assertThat(varCond1.getStatement(), instanceOf(Block.class));
        Block varCondBlock1 = (Block) varCond1.getStatement();
        assertThat(varCondBlock1.getStatements().size(), equalTo(1));
        assertThat(varCondBlock1.getStatements().get(0), instanceOf(ExpressionStatement.class));

        assertThat(varCond1.getElseStatement(), instanceOf(Condition.class));
        Condition varCond2 = (Condition) varCond1.getElseStatement();
        assertThat(varCond2.getStatement(), instanceOf(Block.class));
        Block varCondBlock2 = (Block) varCond2.getStatement();
        assertThat(varCondBlock2.getStatements().size(), equalTo(1));
        assertThat(varCondBlock2.getStatements().get(0), instanceOf(ExpressionStatement.class));
    }
}
