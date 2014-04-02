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
package org.splevo.jamopp.refactoring.refactory.ifelse.basic;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Statement;
import org.emftext.refactoring.interpreter.internal.RefactoringInterpreter;
import org.emftext.refactoring.registry.refactoringspecification.exceptions.RefSpecAlreadyRegisteredException;
import org.emftext.refactoring.registry.rolemapping.exceptions.RoleMappingAlreadyRegistered;
import org.emftext.refactoring.registry.rolemodel.exceptions.RoleModelAlreadyRegisteredException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.refactory.ifelse.IfElseRefactoring;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.refactory.test.util.RefactoryTestUtil;
import org.splevo.refactoring.refactory.util.RefactoryUtil;
import org.splevo.refactoring.test.util.RefactoringTestUtil;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementFactory;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.refinement.VPMRefinementService;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Lists;

/**
 * Test the if else refactoring implementation.
 */
public class IfElseBasicRefactoringTest {

    /**
     * Prepare the test. Initializes a log4j logging environment.
     *
     * @throws FileNotFoundException
     *             Failed to load the required models.
     * @throws RoleModelAlreadyRegisteredException
     *             Role model already registered.
     * @throws RefSpecAlreadyRegisteredException
     *             Refactoring specification already registered.
     * @throws RoleMappingAlreadyRegistered
     *             Role mapping already registered.
     */
    @BeforeClass
    public static void setUp() throws FileNotFoundException, RoleModelAlreadyRegisteredException,
            RefSpecAlreadyRegisteredException, RoleMappingAlreadyRegistered {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));

        RefactoryUtil.initialize();
        @SuppressWarnings("unused")
        Class<RefactoringInterpreter> i1 = RefactoringInterpreter.class;
        String baseDirectory = "../org.splevo.jamopp.refactoring.refactory.ifelse/src/org/splevo/jamopp/refactoring/refactory/ifelse/basic/";
        File rolesText = new File(baseDirectory + "IfElseBasic.rolestext");
        File refspec = new File(baseDirectory + "IfElseBasic.refspec");
        File roleMapping = new File(baseDirectory + "IfElseBasic.rolemapping");

        RefactoryTestUtil.registerRoleModel(rolesText);
        RefactoryTestUtil.registerRefSpec(refspec);
        RefactoryTestUtil.registerRoleMapping(roleMapping);
    }

    /**
     * Test to build a vpm model from a diffing result.
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
    public void test() throws Exception {

        String basePath = "testcode/";

        VariationPointModel vpm = RefactoringTestUtil.initializeVariationPointModel(basePath);
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), is(2));

        // diffing imports are expected to be separate add and deletes, so merge
        // them.
        Refinement refinement = RefinementFactory.eINSTANCE.createRefinement();
        refinement.setType(RefinementType.MERGE);
        refinement.getVariationPoints().add(vpm.getVariationPointGroups().get(0).getVariationPoints().get(0));
        refinement.getVariationPoints().add(vpm.getVariationPointGroups().get(1).getVariationPoints().get(0));
        VPMRefinementService refinementService = new VPMRefinementService();
        refinementService.applyRefinements(Lists.newArrayList(refinement), vpm);
        assertThat("Wrong number of refined vpm groups", vpm.getVariationPointGroups().size(), is(1));

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);

        IfElseRefactoring refactoring = new IfElseRefactoring();
        refactoring.refactor(variationPoint);

        SoftwareElement locationElement = variationPoint.getLocation();
        if (locationElement instanceof JaMoPPSoftwareElement) {
            JaMoPPSoftwareElement jamoppLocationElement = (JaMoPPSoftwareElement) locationElement;
            Commentable jamoppElement = jamoppLocationElement.getJamoppElement();
            assertThat("Location is not a Class", jamoppElement, instanceOf(ClassMethod.class));

            ClassMethod cm = (ClassMethod) jamoppElement;
            Statement firstStatement = cm.getStatements().get(0);
            assertThat("Method has wrong statement.", firstStatement, instanceOf(Condition.class));
            Statement ifBlock = ((Condition) firstStatement).getStatement();
            assertThat("If block is not a Block.", ifBlock, instanceOf(Block.class));
            assertThat("If block is empty.", ((Block) ifBlock).getStatements().size(), is(1));
            Statement ifStatement = ((Block) ifBlock).getStatements().get(0);
            assertThat("Statement in if Block is not a VariableStatement.", ifStatement,
                    instanceOf(LocalVariableStatement.class));
            Statement elseStatement = ((Condition) firstStatement).getElseStatement();
            assertThat("Else statement is no VariableStatement.", elseStatement,
                    instanceOf(LocalVariableStatement.class));
        } else {
            fail("Unexpected Variation Point Location");
        }
    }

}
