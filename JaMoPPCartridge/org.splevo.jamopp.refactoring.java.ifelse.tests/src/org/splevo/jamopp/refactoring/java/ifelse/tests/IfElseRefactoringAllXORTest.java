/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *    Daniel Kojic
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Break;
import org.emftext.language.java.statements.CatchBlock;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.NormalSwitchCase;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.TryBlock;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseRefactoringAllXOR;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Test the java if-else refactoring implementation for XOR variability.
 */
public class IfElseRefactoringAllXORTest {

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:MethodBody</li>
     * <li>Localization:EntireMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Has an empty method body.
     * 
     * <strong>Integration Variant</strong><br>
     * Method with contents.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether refactoring can be applied.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testCanBeAppliedForCaseMethodBodyEntireMethodOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("MethodBody_EntireMethod");
        RefactoringTestUtil.performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0)
                .getVariationPoints().get(0), vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        assertThat("Wrong VariationPoint location.",
                ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement(),
                instanceOf(ClassMethod.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:MethodBody</li>
     * <li>Localization:EntireMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Has an empty method body.
     * 
     * <strong>Integration Variant</strong><br>
     * Method with contents.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the body of the leading method has the correct statements.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testMethodBodyForCaseMethodBodyEntireMethodOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("MethodBody_EntireMethod");
        RefactoringTestUtil.performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0)
                .getVariationPoints().get(0), vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        assertThat("Wrong VariationPoint location.",
                ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement(),
                instanceOf(ClassMethod.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        ClassMethod cm = (ClassMethod) ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat(cm.getStatements().size(), equalTo(1));
        assertThat(cm.getStatements().get(0), instanceOf(Condition.class));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:MethodBody</li>
     * <li>Localization:EntireMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Has an empty method body.
     * 
     * <strong>Integration Variant</strong><br>
     * Method with contents.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the condition was built correctly.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testConditionForCaseMethodBodyEntireMethodOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("MethodBody_EntireMethod");
        RefactoringTestUtil.performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0)
                .getVariationPoints().get(0), vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        assertThat("Wrong VariationPoint location.",
                ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement(),
                instanceOf(ClassMethod.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        EList<SoftwareElement> implementingElements = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0)
                .getVariants().get(0).getImplementingElements();

        Statement firstStatement = (Statement) ((JaMoPPSoftwareElement) implementingElements.get(0)).getJamoppElement();
        Statement secondStatement = (Statement) ((JaMoPPSoftwareElement) implementingElements.get(1))
                .getJamoppElement();

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        ClassMethod cm = (ClassMethod) ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        Condition condition = (Condition) cm.getStatements().get(0);

        assertThat(condition.getStatement(), instanceOf(Block.class));
        assertThat(condition.getElseStatement(), nullValue());
        assertThat(condition.getCondition(), notNullValue());

        Block ifBlock = (Block) condition.getStatement();
        assertThat(ifBlock.getStatements().size(), equalTo(2));
        assertThat(ifBlock.getStatements().get(0), equalTo(firstStatement));
        assertThat(ifBlock.getStatements().get(1), equalTo(secondStatement));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:BeforeReturn</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains two statements.
     * 
     * <strong>Integration Variant</strong><br>
     * Class with method that contains the two statements from the leading version with an
     * additional {@link Condition} in between.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether refactoring can be applied.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testCanBeAppliedForCaseStatementBeforeReturnOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_BeforeReturn");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        assertThat("Wrong VariationPoint location.",
                ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement(),
                instanceOf(ClassMethod.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:BeforeReturn</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains two statements.
     * 
     * <strong>Integration Variant</strong><br>
     * Class with method that contains the two statements from the leading version with an
     * additional {@link Condition} in between.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the integration method has the right statements.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testMethodStatementsForCaseStatementBeforeReturnOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_BeforeReturn");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        assertThat("Wrong VariationPoint location.",
                ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement(),
                instanceOf(ClassMethod.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        VariationPoint firstVariationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        ClassMethod leadingMethod = (ClassMethod) ((JaMoPPSoftwareElement) firstVariationPoint.getLocation())
                .getJamoppElement();
        Statement firstStatement = leadingMethod.getStatements().get(0);
        Statement returnStatement = leadingMethod.getStatements().get(1);

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        ClassMethod cm = (ClassMethod) ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat(cm.getStatements().size(), equalTo(3));
        assertThat(cm.getStatements().get(0), equalTo(firstStatement));
        assertThat(cm.getStatements().get(1), instanceOf(Condition.class));
        assertThat(cm.getStatements().get(2), equalTo(returnStatement));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:BeforeReturn</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains two statements.
     * 
     * <strong>Integration Variant</strong><br>
     * Class with method that contains the two statements from the leading version with an
     * additional {@link Condition} in between.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether ...
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testConditionForCaseStatementBeforeReturnOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_BeforeReturn");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        assertThat("Wrong VariationPoint location.",
                ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement(),
                instanceOf(ClassMethod.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        VariationPoint firstVariationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        ExpressionStatement integrationExpression = (ExpressionStatement) ((JaMoPPSoftwareElement) firstVariationPoint
                .getVariants().get(0).getImplementingElements().get(0)).getJamoppElement();

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        ClassMethod cm = (ClassMethod) ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        Condition condition = (Condition) cm.getStatements().get(1);
        assertThat(condition.getStatement(), instanceOf(Block.class));
        assertThat(condition.getElseStatement(), nullValue());
        assertThat(condition.getCondition(), notNullValue());

        Block ifBlock = (Block) condition.getStatement();
        assertThat(ifBlock.getStatements().size(), equalTo(1));
        assertThat(ifBlock.getStatements().get(0), equalTo((Statement) integrationExpression));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:EndMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a single statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Class with method that contains the statement from the leading version with an additional
     * statement at the end of the method.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the refactoring can be applied.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testCanBeAppliedForCaseStatementEndMethodOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_EndMethod");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:EndMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a single statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Class with method that contains the statement from the leading version with an additional
     * statement at the end of the method.
     * 
     * <strong>Test Details</strong><br>
     * Verifies the first statement of the output method.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testFirstStatementForCaseStatementEndMethodOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_EndMethod");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        ClassMethod cm = (ClassMethod) variationPointLocation;
        Statement leadingStatement = cm.getStatements().get(0);

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        assertThat(cm.getStatements().get(0), equalTo(leadingStatement));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:EndMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a single statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Class with method that contains the statement from the leading version with an additional
     * statement at the end of the method.
     * 
     * <strong>Test Details</strong><br>
     * Verifies the condition that was created during refactoring.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testConditionForCaseStatementEndMethodOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_EndMethod");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        VariationPoint firstVariationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        ClassMethod cm = (ClassMethod) variationPointLocation;
        Statement integrationStatement = (Statement) ((JaMoPPSoftwareElement) firstVariationPoint.getVariants().get(0)
                .getImplementingElements().get(0)).getJamoppElement();

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        assertThat(cm.getStatements().get(1), instanceOf(Condition.class));
        Condition condition = (Condition) cm.getStatements().get(1);
        assertThat(condition.getCondition(), notNullValue());
        assertThat(condition.getElseStatement(), nullValue());

        assertThat(condition.getStatement(), instanceOf(Block.class));
        Block ifBlock = (Block) condition.getStatement();
        assertThat(ifBlock.getStatements().size(), equalTo(1));
        assertThat(ifBlock.getStatements().get(0), equalTo(integrationStatement));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:Condition</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a single condition. The condition has one statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with one additional statement at the end of the condition block.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the refactoring can be applied.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testCanBeAppliedForCaseStatementNestedStatementConditionOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil
                .initializeVariationPointModel("Statement_NestedStatement_Condition");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:Condition</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a single condition. The condition has one statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with one additional statement at the end of the condition block.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the leading if block contains the right statements.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testNestedBlockForCaseStatementNestedStatementConditionOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil
                .initializeVariationPointModel("Statement_NestedStatement_Condition");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        Block leadingIfBlock = (Block) variationPointLocation;
        Statement leadingStatement = leadingIfBlock.getStatements().get(0);

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        assertThat(leadingIfBlock.getStatements().size(), equalTo(2));
        assertThat(leadingIfBlock.getStatements().get(0), equalTo(leadingStatement));
        assertThat(leadingIfBlock.getStatements().get(1), instanceOf(Condition.class));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:Condition</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a single condition. The condition has one statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with one additional statement at the end of the condition block.
     * 
     * <strong>Test Details</strong><br>
     * Verifies the condition that was created during refactoring.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testConditionForCaseStatementNestedStatementConditionOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil
                .initializeVariationPointModel("Statement_NestedStatement_Condition");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        Block leadingIfBlock = (Block) variationPointLocation;
        Statement integrationStatement = (Statement) ((JaMoPPSoftwareElement) variationPoint.getVariants().get(0)
                .getImplementingElements().get(0)).getJamoppElement();

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        Condition condition = (Condition) leadingIfBlock.getStatements().get(1);
        assertThat(condition.getCondition(), notNullValue());
        assertThat(condition.getElseStatement(), nullValue());
        assertThat(condition.getStatement(), instanceOf(Block.class));

        Block generatedBlock = (Block) condition.getStatement();
        assertThat(generatedBlock.getStatements().size(), equalTo(1));
        assertThat(generatedBlock.getStatements().get(0), equalTo(integrationStatement));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:For</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a single for loop. The for loop has one statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with one additional statement at the end of the for loop block.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the refactoring can be applied.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testCanBeAppliedForCaseStatementNestedStatementForOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_NestedStatement_For");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:For</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a single for loop. The for loop has one statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with one additional statement at the end of the for loop block.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the leading for block contains the right statements.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testNestedBlockForCaseStatementNestedStatementForOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_NestedStatement_For");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        Block leadingForBlock = (Block) variationPointLocation;
        Statement leadingStatement = leadingForBlock.getStatements().get(0);

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        assertThat(leadingForBlock.getStatements().size(), equalTo(2));
        assertThat(leadingForBlock.getStatements().get(0), equalTo(leadingStatement));
        assertThat(leadingForBlock.getStatements().get(1), instanceOf(Condition.class));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:For</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a single for loop. The for loop has one statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with one additional statement at the end of the for loop block.
     * 
     * <strong>Test Details</strong><br>
     * Verifies the condition that was created during refactoring.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testConditionForCaseStatementNestedStatementForOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_NestedStatement_For");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        Block leadingForBlock = (Block) variationPointLocation;
        Statement integrationStatement = (Statement) ((JaMoPPSoftwareElement) variationPoint.getVariants().get(0)
                .getImplementingElements().get(0)).getJamoppElement();

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        Condition condition = (Condition) leadingForBlock.getStatements().get(1);
        assertThat(condition.getCondition(), notNullValue());
        assertThat(condition.getElseStatement(), nullValue());
        assertThat(condition.getStatement(), instanceOf(Block.class));

        Block generatedBlock = (Block) condition.getStatement();
        assertThat(generatedBlock.getStatements().size(), equalTo(1));
        assertThat(generatedBlock.getStatements().get(0), equalTo(integrationStatement));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:While</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a single while loop. The for loop has one statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with one additional statement at the end of the while loop block.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the refactoring can be applied.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testCanBeAppliedForCaseStatementNestedStatementWhileOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_NestedStatement_While");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:While</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a single while loop. The for loop has one statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with one additional statement at the end of the while loop block.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the leading while block contains the right statements.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testNestedBlockForCaseStatementNestedStatementWhileOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_NestedStatement_While");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        Block leadingForBlock = (Block) variationPointLocation;
        Statement leadingStatement = leadingForBlock.getStatements().get(0);

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        assertThat(leadingForBlock.getStatements().size(), equalTo(2));
        assertThat(leadingForBlock.getStatements().get(0), equalTo(leadingStatement));
        assertThat(leadingForBlock.getStatements().get(1), instanceOf(Condition.class));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:While</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a single while loop. The for loop has one statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with one additional statement at the end of the while loop block.
     * 
     * <strong>Test Details</strong><br>
     * Verifies the condition that was created during refactoring.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testConditionForCaseStatementNestedStatementWhileOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_NestedStatement_While");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        Block leadingForBlock = (Block) variationPointLocation;
        Statement integrationStatement = (Statement) ((JaMoPPSoftwareElement) variationPoint.getVariants().get(0)
                .getImplementingElements().get(0)).getJamoppElement();

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        Condition condition = (Condition) leadingForBlock.getStatements().get(1);
        assertThat(condition.getCondition(), notNullValue());
        assertThat(condition.getElseStatement(), nullValue());
        assertThat(condition.getStatement(), instanceOf(Block.class));

        Block generatedBlock = (Block) condition.getStatement();
        assertThat(generatedBlock.getStatements().size(), equalTo(1));
        assertThat(generatedBlock.getStatements().get(0), equalTo(integrationStatement));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:Try</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a try-catch. Both, the try and the catch block have one
     * statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with one additional statement at the end of the try block.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the refactoring can be applied.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testCanBeAppliedForCaseStatementNestedStatementTryOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_NestedStatement_Try");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(TryBlock.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:Try</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a try-catch. Both, the try and the catch block have one
     * statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with one additional statement at the end of the try block.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the leading try block contains the right statements.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testNestedBlockForCaseStatementNestedStatementTryOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_NestedStatement_Try");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(TryBlock.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        TryBlock leadingTryBlock = (TryBlock) variationPointLocation;
        Statement leadingStatement = leadingTryBlock.getStatements().get(0);

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        assertThat(leadingTryBlock.getStatements().size(), equalTo(2));
        assertThat(leadingTryBlock.getStatements().get(0), equalTo(leadingStatement));
        assertThat(leadingTryBlock.getStatements().get(1), instanceOf(Condition.class));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:Try</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a try-catch. Both, the try and the catch block have one
     * statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with one additional statement at the end of the try block.
     * 
     * <strong>Test Details</strong><br>
     * Verifies the condition that was created during refactoring.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testConditionForCaseStatementNestedStatementTryOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_NestedStatement_Try");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(TryBlock.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        TryBlock leadingTryBlock = (TryBlock) variationPointLocation;
        Statement integrationStatement = (Statement) ((JaMoPPSoftwareElement) variationPoint.getVariants().get(0)
                .getImplementingElements().get(0)).getJamoppElement();

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        Condition condition = (Condition) leadingTryBlock.getStatements().get(1);
        assertThat(condition.getCondition(), notNullValue());
        assertThat(condition.getElseStatement(), nullValue());
        assertThat(condition.getStatement(), instanceOf(Block.class));

        Block generatedBlock = (Block) condition.getStatement();
        assertThat(generatedBlock.getStatements().size(), equalTo(1));
        assertThat(generatedBlock.getStatements().get(0), equalTo(integrationStatement));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:Catch</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a try-catch. Both, the try and the catch block have one
     * statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with a different exception being thrown in the catch block.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the refactoring can be applied.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testCanBeAppliedForCaseStatementNestedStatementCatchOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_NestedStatement_Catch");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(CatchBlock.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:Catch</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a try-catch. Both, the try and the catch block have one
     * statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with a different exception being thrown in the catch block.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the leading catch block contains the right statements.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testNestedBlockForCaseStatementNestedStatementCatchOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_NestedStatement_Catch");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(CatchBlock.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        CatchBlock leadingCatchBlock = (CatchBlock) variationPointLocation;

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        assertThat(leadingCatchBlock.getStatements().size(), equalTo(1));
        assertThat(leadingCatchBlock.getStatements().get(0), instanceOf(Condition.class));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:Catch</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a try-catch. Both, the try and the catch block have one
     * statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with a different exception being thrown in the catch block.
     * 
     * <strong>Test Details</strong><br>
     * Verifies the condition that was created during refactoring.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testConditionForCaseStatementNestedStatementCatchOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_NestedStatement_Catch");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(CatchBlock.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        CatchBlock leadingCatchBlock = (CatchBlock) variationPointLocation;
        Statement leadingStatement = (Statement) ((JaMoPPSoftwareElement) variationPoint.getVariants().get(0)
                .getImplementingElements().get(0)).getJamoppElement();
        Statement integrationStatement = (Statement) ((JaMoPPSoftwareElement) variationPoint.getVariants().get(1)
                .getImplementingElements().get(0)).getJamoppElement();

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        Condition condition = (Condition) leadingCatchBlock.getStatements().get(0);
        assertThat(condition.getCondition(), notNullValue());
        assertThat(condition.getStatement(), instanceOf(Block.class));
        assertThat(condition.getElseStatement(), instanceOf(Condition.class));

        Condition elseCondition = (Condition) condition.getElseStatement();
        assertThat(elseCondition.getCondition(), notNullValue());
        assertThat(elseCondition.getStatement(), instanceOf(Block.class));
        assertThat(elseCondition.getElseStatement(), nullValue());

        Block generatedIfBlock = (Block) condition.getStatement();
        assertThat(generatedIfBlock.getStatements().size(), equalTo(1));
        assertThat(generatedIfBlock.getStatements().get(0), equalTo(leadingStatement));

        Block generatedElseBlock = (Block) elseCondition.getStatement();
        assertThat(generatedElseBlock.getStatements().size(), equalTo(1));
        assertThat(generatedElseBlock.getStatements().get(0), equalTo(integrationStatement));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:SwitchCase</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a single switch case with one case that has one statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with one additional statement in the case block.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the refactoring can be applied.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testCanBeAppliedForCaseStatementNestedStatementSwitchCaseOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil
                .initializeVariationPointModel("Statement_NestedStatement_SwitchCase");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(NormalSwitchCase.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:SwitchCase</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a single switch case with one case that has one statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with one additional statement in the case block.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the leading switch case contains the right statements.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testNestedBlockForCaseStatementNestedStatementSwitchCaseOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil
                .initializeVariationPointModel("Statement_NestedStatement_SwitchCase");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(NormalSwitchCase.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        NormalSwitchCase switchCase = (NormalSwitchCase) variationPointLocation;
        Statement leadingStatement = switchCase.getStatements().get(0);

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        assertThat(switchCase.getStatements().size(), equalTo(3));
        assertThat(switchCase.getStatements().get(0), equalTo(leadingStatement));
        assertThat(switchCase.getStatements().get(1), instanceOf(Condition.class));
        assertThat(switchCase.getStatements().get(2), instanceOf(Break.class));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:NestedStatement</li>
     * <li>NestedStatement:SwitchCase</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains a single switch case with one case that has one statement.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as leading variant but with one additional statement in the case block.
     * 
     * <strong>Test Details</strong><br>
     * Verifies the condition that was created during refactoring.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testConditionForCaseStatementNestedStatementSwitchCaseOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil
                .initializeVariationPointModel("Statement_NestedStatement_SwitchCase");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(NormalSwitchCase.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        NormalSwitchCase normalSwitchCase = (NormalSwitchCase) variationPointLocation;
        Statement integrationStatement = (Statement) ((JaMoPPSoftwareElement) variationPoint.getVariants().get(0)
                .getImplementingElements().get(0)).getJamoppElement();

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        Condition condition = (Condition) normalSwitchCase.getStatements().get(1);
        assertThat(condition.getCondition(), notNullValue());
        assertThat(condition.getElseStatement(), nullValue());
        assertThat(condition.getStatement(), instanceOf(Block.class));

        Block generatedBlock = (Block) condition.getStatement();
        assertThat(generatedBlock.getStatements().size(), equalTo(1));
        assertThat(generatedBlock.getStatements().get(0), equalTo(integrationStatement));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:StartMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains one method call.
     * 
     * <strong>Integration Variant</strong><br>
     * Class with method that contains the method call from the leading version with two additional
     * method calls at the beginning of the method.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the refactoring can be applied.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testCanBeAppliedForCaseStatementStartMethodOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_StartMethod");
        RefactoringTestUtil.performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0)
                .getVariationPoints().get(0), vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:StartMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains one method call.
     * 
     * <strong>Integration Variant</strong><br>
     * Class with method that contains the method call from the leading version with two additional
     * method calls at the beginning of the method.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the leading method's statements are correct.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testFirstStatementForCaseStatementStartMethodOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_StartMethod");
        RefactoringTestUtil.performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0)
                .getVariationPoints().get(0), vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        ClassMethod leadingMethod = (ClassMethod) variationPointLocation;
        Statement leadingStatement = leadingMethod.getStatements().get(0);

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        assertThat(leadingMethod.getStatements().size(), equalTo(2));
        assertThat(leadingMethod.getStatements().get(0), instanceOf(Condition.class));
        assertThat(leadingMethod.getStatements().get(1), equalTo(leadingStatement));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:StartMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains one method call.
     * 
     * <strong>Integration Variant</strong><br>
     * Class with method that contains the method call from the leading version with two additional
     * method calls at the beginning of the method.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the generated condition was built correctly.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testConditionForCaseStatementStartMethodOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("Statement_StartMethod");
        RefactoringTestUtil.performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0)
                .getVariationPoints().get(0), vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        ClassMethod leadingMethod = (ClassMethod) variationPointLocation;
        EList<SoftwareElement> implementingElements = variationPoint.getVariants().get(0).getImplementingElements();
        Statement integrationMethodCall1 = (Statement) ((JaMoPPSoftwareElement) implementingElements.get(0))
                .getJamoppElement();
        Statement integrationMethodCall2 = (Statement) ((JaMoPPSoftwareElement) implementingElements.get(1))
                .getJamoppElement();

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        Condition condition = (Condition) leadingMethod.getStatements().get(0);
        assertThat(condition.getCondition(), notNullValue());
        assertThat(condition.getElseStatement(), nullValue());
        assertThat(condition.getStatement(), instanceOf(Block.class));

        Block generatedBlock = (Block) condition.getStatement();
        assertThat(generatedBlock.getStatements().size(), equalTo(2));
        assertThat(generatedBlock.getStatements().get(0), equalTo(integrationMethodCall1));
        assertThat(generatedBlock.getStatements().get(1), equalTo(integrationMethodCall2));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:StartMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains 3 statements.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as in leading but with a different initialization of the first statement.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the refactoring can be applied.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testCanBeAppliedForCaseReusedStatementSameTypeOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("ReusedStatementSameType");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:StartMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains 3 statements.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as in leading but with a different initialization of the first statement.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the leading method's statements are correct. The local variable should have
     * been split into initialization and assertion. The method body should contain the variable
     * initializtion directly.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testFirstStatementForCaseStatementReusedStatementSameTypeOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("ReusedStatementSameType");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        ClassMethod leadingMethod = (ClassMethod) variationPointLocation;
        Statement leadingStatement1 = leadingMethod.getStatements().get(1);
        Statement leadingStatement2 = leadingMethod.getStatements().get(2);

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        assertThat(leadingMethod.getStatements().size(), equalTo(4));
        assertThat(leadingMethod.getStatements().get(0), instanceOf(LocalVariableStatement.class));

        LocalVariableStatement generatedLocalVariable = (LocalVariableStatement) leadingMethod.getStatements().get(0);
        assertThat(generatedLocalVariable.getVariable().getName(), equalTo("a"));

        assertThat(leadingMethod.getStatements().get(1), instanceOf(Condition.class));
        assertThat(leadingMethod.getStatements().get(2), equalTo(leadingStatement1));
        assertThat(leadingMethod.getStatements().get(3), equalTo(leadingStatement2));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>VariabilityType:OPTXOR</li>
     * <li>GranularityType:Statement</li>
     * <li>Localization:StartMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains 3 statements.
     * 
     * <strong>Integration Variant</strong><br>
     * Same as in leading but with a different initialization of the first statement.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the generated condition was built correctly.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testConditionForCaseStatementReusedStatementSameTypeOPTXOR() throws Exception {
        // init test object
        VariabilityRefactoring objectUnderTest = new IfElseRefactoringAllXOR();

        // init vpm
        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel("ReusedStatementSameType");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        RefactoringTestUtil.setUpVariationPointForIfElseRefactoring(variationPoint, VariabilityType.OPTXOR);

        // extract information for later verification
        ClassMethod leadingMethod = (ClassMethod) variationPointLocation;

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        Condition condition = (Condition) leadingMethod.getStatements().get(1);

        assertThat(condition.getCondition(), notNullValue());
        assertThat(condition.getStatement(), instanceOf(Block.class));
        assertThat(condition.getElseStatement(), instanceOf(Condition.class));

        Block generatedIfBlock = (Block) condition.getStatement();
        assertThat(generatedIfBlock.getStatements().size(), equalTo(1));
        assertThat(generatedIfBlock.getStatements().get(0), instanceOf(ExpressionStatement.class));

        Condition elseCondition = (Condition) condition.getElseStatement();
        Block generatedElseBlock = (Block) elseCondition.getStatement();
        assertThat(generatedElseBlock.getStatements().size(), equalTo(1));
        assertThat(generatedElseBlock.getStatements().get(0), instanceOf(ExpressionStatement.class));
    }
}
