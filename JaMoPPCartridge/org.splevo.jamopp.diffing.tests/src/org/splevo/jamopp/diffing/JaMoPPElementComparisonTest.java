/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.jamopp.diffing;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.literals.BooleanLiteral;
import org.emftext.language.java.literals.LiteralsFactory;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementsFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.diffing.jamoppdiff.StatementChange;

import com.google.common.collect.Maps;

/**
 * Unit test to prove the direct element comparison instead of complete resource sets as input.
 */
public class JaMoPPElementComparisonTest {

    private static final String BASE_PATH = "testmodels/implementation/JaMoPPElementComparison/";

    /**
     * Initialize the required test infrastructure.
     */
    @BeforeClass
    public static void setUp() {
        TestUtil.setUp();
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testMethodsWithChangedBody() throws Exception {

        TestUtil.setUp();
        ResourceSet setA = TestUtil.extractModel(BASE_PATH + "MethodsWithChangedBody/a");
        ResourceSet setB = TestUtil.extractModel(BASE_PATH + "MethodsWithChangedBody/b");

        ClassMethod methodA = searchMethodElement(setA);
        ClassMethod methodB = searchMethodElement(setB);

        JaMoPPDiffer differ = new JaMoPPDiffer();

        Map<String, String> diffOptions = Maps.newHashMap();
        Comparison comparison = differ.doDiff(methodA, methodB, diffOptions);

        EList<Diff> differences = comparison.getDifferences();
        assertThat("Exactly one change should exist", differences.size(), is(1));
        Diff diff = differences.get(0);
        assertThat(diff, instanceOf(StatementChange.class));
    }

    /**
     * Tests whether the differ finds differences of two different statements in case the model of
     * one statement was artificially modified.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testNotMatchingStatements() throws Exception {

        TestUtil.setUp();
        ResourceSet setA = TestUtil.extractModel(BASE_PATH + "NotMatchingStatements/a");
        ResourceSet setB = TestUtil.extractModel(BASE_PATH + "NotMatchingStatements/b");

        ClassMethod methodA = searchMethodElement(setA);
        ClassMethod methodB = searchMethodElement(setB);

        // Wrap a copy of the statement from method b into a condition and add it to then end of
        // method a
        Statement statement = methodB.getStatements().get(0);
        Condition currentCondition = StatementsFactory.eINSTANCE.createCondition();
        BooleanLiteral booleanLiteral = LiteralsFactory.eINSTANCE.createBooleanLiteral();
        booleanLiteral.setValue(true);
        currentCondition.setCondition(booleanLiteral);
        Block currentBlock = StatementsFactory.eINSTANCE.createBlock();
        currentCondition.setStatement(currentBlock);
        currentBlock.getStatements().add(EcoreUtil.copy(statement));
        EList<Statement> statements = methodA.getStatements();
        statements.add(currentCondition);

        JaMoPPDiffer differ = new JaMoPPDiffer();

        // here we match a expression statement with a condition which should result in diffs
        Map<String, String> diffOptions = Maps.newHashMap();
        Comparison comparison = differ.doDiff(statements.get(0), statements.get(1), diffOptions);

        EList<Diff> differences = comparison.getDifferences();
        assertThat("Exactly two changes should exist", differences.size(), is(2));
        Diff diff1 = differences.get(0);
        assertThat(diff1, instanceOf(StatementChange.class));
        Diff diff2 = differences.get(1);
        assertThat(diff2, instanceOf(StatementChange.class));
    }

    /**
     * Get a {@link ClassMethod} element out of a resource set assuming there is only one resource
     * in the set containing exactly one method.
     *
     * @param resourceSet
     *            The set to search in.
     * @return The first method found.
     */
    private ClassMethod searchMethodElement(ResourceSet resourceSet) {
        Resource resource = resourceSet.getResources().get(0);
        TreeIterator<EObject> contentIterator = resource.getAllContents();
        ClassMethod methodUnderTest = null;
        while (contentIterator.hasNext()) {
            EObject next = contentIterator.next();
            if (next instanceof ClassMethod) {
                methodUnderTest = (ClassMethod) next;
                break;
            }
        }
        return methodUnderTest;
    }
}
