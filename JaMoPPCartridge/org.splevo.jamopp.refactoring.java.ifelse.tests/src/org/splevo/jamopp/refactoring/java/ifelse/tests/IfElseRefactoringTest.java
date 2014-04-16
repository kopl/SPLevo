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

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
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
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseRefactoring;
import org.splevo.jamopp.vpm.builder.JaMoPPVPMBuilder;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementFactory;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.refinement.VPMRefinementService;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Test the if else refactoring implementation.
 */
public class IfElseRefactoringTest {

    // Paths to the testcode
    private static final String PATH_TESTCODE_FOLDER = "testcode/";

    // The object of the class under test
    private IfElseRefactoring objectUnderTest = null;

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Initializes the test object.
     */
    @Before
    public void before() {
        this.objectUnderTest = new IfElseRefactoring();
    }

    /**
     * Reset test object
     */
    @After
    public void after() {
        this.objectUnderTest = null;
    }

    /**
     * Tests whether the refactoring can be applied.
     * 
     * <strong>Test Input</strong><br>
     * Two classes with a differing import (BigInteger vs. BigDecimal)
     * 
     * <strong>Test Result</strong><br>
     * The compilation unit of the leading variant (the location of the variation point) must
     * contain two imports (BigInteger and BigDecimal)
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testCanBeAppliedForImportMerge() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("MergeImports");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertThat("Wrong number of refined vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        assertThat(((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement(),
                instanceOf(CompilationUnit.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * Tests whether the leading compilation unit the correct number of refactorings.
     * 
     * <strong>Test Input</strong><br>
     * Two classes with a differing import (BigInteger vs. BigDecimal)
     * 
     * <strong>Test Result</strong><br>
     * The compilation unit of the leading variant (the location of the variation point) must
     * contain two imports (BigInteger and BigDecimal)
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testMergeImports() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("MergeImports");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertThat("Wrong number of refined vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        assertThat(((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement(),
                instanceOf(CompilationUnit.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

        // excute test
        objectUnderTest.refactor(variationPoint);

        // verification
        CompilationUnit cu = (CompilationUnit) ((JaMoPPSoftwareElement) variationPoint.getLocation())
                .getJamoppElement();
        assertThat("CompilationUnit should have two imports.", cu.getImports().size(), equalTo(2));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
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
    public void testCanBeAppliedForCaseMethodBodyEntireMethod() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("MethodBody_EntireMethod");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        assertThat("Wrong VariationPoint location.",
                ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement(),
                instanceOf(ClassMethod.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
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
    public void testMethodBodyForCaseMethodBodyEntireMethod() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("MethodBody_EntireMethod");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        assertThat("Wrong VariationPoint location.",
                ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement(),
                instanceOf(ClassMethod.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
    public void testConditionForCaseMethodBodyEntireMethod() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("MethodBody_EntireMethod");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        assertThat("Wrong VariationPoint location.",
                ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement(),
                instanceOf(ClassMethod.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
    public void testCanBeAppliedForCaseStatementBeforeReturn() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_BeforeReturn");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        assertThat("Wrong VariationPoint location.",
                ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement(),
                instanceOf(ClassMethod.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
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
    public void testMethodStatementsForCaseStatementBeforeReturn() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_BeforeReturn");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        assertThat("Wrong VariationPoint location.",
                ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement(),
                instanceOf(ClassMethod.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
    public void testConditionForCaseStatementBeforeReturn() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_BeforeReturn");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        assertThat("Wrong VariationPoint location.",
                ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement(),
                instanceOf(ClassMethod.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
    public void testCanBeAppliedForCaseStatementEndMethod() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_EndMethod");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
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
    public void testFirstStatementForCaseStatementEndMethod() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_EndMethod");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
    public void testConditionForCaseStatementEndMethod() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_EndMethod");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
    public void testCanBeAppliedForCaseStatementNestedStatementCondition() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_Condition");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
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
    public void testNestedBlockForCaseStatementNestedStatementCondition() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_Condition");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
    public void testConditionForCaseStatementNestedStatementCondition() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_Condition");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
    public void testCanBeAppliedForCaseStatementNestedStatementFor() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_For");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
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
    public void testNestedBlockForCaseStatementNestedStatementFor() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_For");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
    public void testConditionForCaseStatementNestedStatementFor() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_For");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
    public void testCanBeAppliedForCaseStatementNestedStatementWhile() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_While");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
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
    public void testNestedBlockForCaseStatementNestedStatementWhile() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_While");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
    public void testConditionForCaseStatementNestedStatementWhile() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_While");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(Block.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
    public void testCanBeAppliedForCaseStatementNestedStatementTry() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_Try");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(TryBlock.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
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
    public void testNestedBlockForCaseStatementNestedStatementTry() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_Try");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(TryBlock.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
    public void testConditionForCaseStatementNestedStatementTry() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_Try");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(TryBlock.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
     * Same as leading variant but with another exception being thrown in the catch block.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the refactoring can be applied.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testCanBeAppliedForCaseStatementNestedStatementCatch() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_Catch");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(CatchBlock.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
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
     * Same as leading variant but with another exception being thrown in the catch block.
     * 
     * <strong>Test Details</strong><br>
     * Tests whether the leading catch block contains the right statements.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testNestedBlockForCaseStatementNestedStatementCatch() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_Catch");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(CatchBlock.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
     * Same as leading variant but with another exception being thrown in the catch block.
     * 
     * <strong>Test Details</strong><br>
     * Verifies the condition that was created during refactoring.
     * 
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void testConditionForCaseStatementNestedStatementCatch() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_Catch");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(CatchBlock.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
    public void testCanBeAppliedForCaseStatementNestedStatementSwitchCase() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_SwitchCase");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(NormalSwitchCase.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
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
    public void testNestedBlockForCaseStatementNestedStatementSwitchCase() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_SwitchCase");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(NormalSwitchCase.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
    public void testConditionForCaseStatementNestedStatementSwitchCase() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_NestedStatement_SwitchCase");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(NormalSwitchCase.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
    public void testCanBeAppliedForCaseStatementStartMethod() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_StartMethod");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
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
    public void testFirstStatementForCaseStatementStartMethod() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_StartMethod");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
    public void testConditionForCaseStatementStartMethod() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("Statement_StartMethod");
        performRefinement(vpm, RefinementType.MERGE, vpm.getVariationPointGroups().get(0).getVariationPoints().get(0),
                vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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
     * <li>GranularityType:Statement</li>
     * <li>Localization:StartMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains 4 statements.
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
    public void testCanBeAppliedForCaseReusedStatementSameType() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("ReusedStatementSameType");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

        // verification
        assertThat("Refactoring cannot be applied to this variation point.",
                objectUnderTest.canBeAppliedTo(variationPoint), equalTo(true));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>GranularityType:Statement</li>
     * <li>Localization:StartMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains 4 statements.
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
    public void testFirstStatementForCaseStatementReusedStatementSameType() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("ReusedStatementSameType");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

        // extract information for later verification
        ClassMethod leadingMethod = (ClassMethod) variationPointLocation;
        LocalVariableStatement leadingStatement0 = (LocalVariableStatement) leadingMethod.getStatements().get(0);
        Statement leadingStatement1 = leadingMethod.getStatements().get(1);
        Statement leadingStatement2 = leadingMethod.getStatements().get(2);

        // execute test
        objectUnderTest.refactor(variationPoint);

        // verification
        assertThat(leadingMethod.getStatements().size(), equalTo(4));
        assertThat(leadingMethod.getStatements().get(0), instanceOf(LocalVariableStatement.class));

        LocalVariableStatement generatedLocalVariable = (LocalVariableStatement) leadingMethod.getStatements().get(0);
        assertThat(generatedLocalVariable.getVariable().getName(), equalTo(leadingStatement0.getVariable().getName()));

        assertThat(leadingMethod.getStatements().get(1), instanceOf(Condition.class));
        assertThat(leadingMethod.getStatements().get(2), equalTo(leadingStatement1));
        assertThat(leadingMethod.getStatements().get(3), equalTo(leadingStatement2));
    }

    /**
     * <strong>Test-case</strong><br>
     * <ul>
     * <li>GranularityType:Statement</li>
     * <li>Localization:StartMethod</li>
     * </ul>
     * 
     * <strong>Leading Variant</strong><br>
     * Class with method that contains 4 statements.
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
    public void testConditionForCaseStatementReusedStatementSameType() throws Exception {
        // init vpm
        VariationPointModel vpm = initializeVariationPointModel("ReusedStatementSameType");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), equalTo(1));
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        Commentable variationPointLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        assertThat("Wrong VariationPoint location.", variationPointLocation, instanceOf(ClassMethod.class));

        // set up variation point
        setUpVariationPointForXOR(variationPoint);

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

    /**
     * Initialize the variation point model to refactor. Extract, Diff and init VPM.
     * 
     * @param folderName
     *            The name of the folder (within the testcode folder) that contains the code to load
     *            (must contain subdirectories leading and integration).
     * @return The initialized VPM based on the source code differences.
     * @throws Exception
     *             Failed to initialize the model.
     */
    private static VariationPointModel initializeVariationPointModel(String folderName) throws Exception {
        String leadingPath = PATH_TESTCODE_FOLDER + folderName + "/leading/";
        String integrationPath = PATH_TESTCODE_FOLDER + folderName + "/integration/";

        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        List<String> urisA = Lists.newArrayList(new File(leadingPath).getAbsolutePath());
        List<String> urisB = Lists.newArrayList(new File(integrationPath).getAbsolutePath());
        NullProgressMonitor monitor = new NullProgressMonitor();

        ResourceSet setA = extractor.extractSoftwareModel(urisA, monitor, null);
        ResourceSet setB = extractor.extractSoftwareModel(urisB, monitor, null);

        String ignorePackages = buildIgnorePackages();

        Map<String, String> diffOptions = Maps.newLinkedHashMap();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_IGNORE_PACKAGES, ignorePackages);

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        JaMoPPVPMBuilder builder = new JaMoPPVPMBuilder();
        VariationPointModel vpm = builder.buildVPM(comparison, "leading", "integration");
        return vpm;
    }

    /**
     * Build the configuration string for the packages to ignore.
     * 
     * @return The regular expressions for the packages to ignore.
     */
    private static String buildIgnorePackages() {
        StringBuilder sb = new StringBuilder();
        sb.append("java.*");
        sb.append(System.getProperty("line.separator"));
        String ignorePackages = sb.toString();
        return ignorePackages;
    }

    /**
     * Executes a refinement of a given {@link RefinementType} on specified VariationPoints.
     * 
     * @param vpm
     *            The base {@link VariationPointModel}.
     * @param refinementType
     *            The {@link RefinementType}.
     * @param variationPoints
     *            The {@link VariationPoint}s to be refined.
     */
    private void performRefinement(VariationPointModel vpm, RefinementType refinementType,
            VariationPoint... variationPoints) {
        Refinement refinement = RefinementFactory.eINSTANCE.createRefinement();
        refinement.setType(refinementType);

        for (VariationPoint variationPoint : variationPoints) {
            refinement.getVariationPoints().add(variationPoint);
        }

        VPMRefinementService refinementService = new VPMRefinementService();
        refinementService.applyRefinements(Lists.newArrayList(refinement), vpm);
    }

    private void setUpVariationPointForXOR(VariationPoint variationPoint) {
        variationPoint.setBindingTime(BindingTime.RUN_TIME);
        variationPoint.setExtensibility(Extensible.NO);
        variationPoint.setVariabilityMechanism(objectUnderTest.getVariabilityMechanism());
        variationPoint.setVariabilityType(VariabilityType.XOR);
    }
}
