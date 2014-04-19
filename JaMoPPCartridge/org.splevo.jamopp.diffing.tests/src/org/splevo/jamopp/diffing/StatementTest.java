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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.resource.JavaSourceOrClassFileResource;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Sets;

/**
 * Unit test to prove the differencing of method declarations.
 */
public class StatementTest {

    /** Base path of the test code */
    private String basePath = "testmodels/implementation/statements/";

    /**
     * Initialize the test infrastructure.
     */
    @BeforeClass
    public static void setUp() {
        TestUtil.setUp();
    }

    /**
     * Test diffing of changed array field declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testArrayAccessesDiff() throws Exception {

        File testFileA = new File(basePath + "a/ArrayAccesses.java");
        File testFileB = new File(basePath + "b/ArrayAccesses.java");
        ResourceSet rsA = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
        ResourceSet rsB = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(rsA, rsB, TestUtil.DIFF_OPTIONS);

        EList<Diff> differences = comparison.getDifferences();

        assertThat("Wrong number of differences", differences.size(), is(0));
    }

    /**
     * Test insertion of new statements and order changes
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testClassStatementInsertDiff() throws Exception {

        File testFileA = new File(basePath + "a/ClassStatementInsert.java");
        File testFileB = new File(basePath + "b/ClassStatementInsert.java");
        ResourceSet rsA = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
        ResourceSet rsB = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(rsA, rsB, TestUtil.DIFF_OPTIONS);

        EList<Diff> differences = comparison.getDifferences();

        assertThat("Wrong number of differences", differences.size(), is(7));
    }

    /**
     * Test insertion of new statements
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testIfStatementDiff() throws Exception {

        File testFileA = new File(basePath + "a/IfStatements.java");
        File testFileB = new File(basePath + "b/IfStatements.java");
        ResourceSet rsA = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
        ResourceSet rsB = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(rsA, rsB, TestUtil.DIFF_OPTIONS);

        EList<Diff> differences = comparison.getDifferences();

        assertThat("Wrong number of differences", differences.size(), is(5));
    }

    /**
     * Test classic for loop with object (iterator) initialization inside.
     *
     * Due to the hierarchical design of the match engine, this is a special case because the
     * variable initialization and the reference to the variable are contained within the same
     * container.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testForLoopWithIterator() throws Exception {

        File testFileA = new File(basePath + "a/ForLoopWithIterator.java");
        File testFileB = new File(basePath + "b/ForLoopWithIterator.java");
        ResourceSet rsA = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
        ResourceSet rsB = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(rsA, rsB, TestUtil.DIFF_OPTIONS);

        EList<Diff> differences = comparison.getDifferences();

        assertThat("Should return no difference", differences.size(), is(0));
    }

    /**
     * Test insertion of new statements
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testLoopStatementDiff() throws Exception {

        File testFileA = new File(basePath + "a/LoopStatements.java");
        File testFileB = new File(basePath + "b/LoopStatements.java");
        ResourceSet rsA = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
        ResourceSet rsB = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(rsA, rsB, TestUtil.DIFF_OPTIONS);

        EList<Diff> differences = comparison.getDifferences();

        assertThat("Wrong number of differences", differences.size(), is(3));
    }

    /**
     * Test insertion of new statements
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testChangeInsideSiblingBlockDiff() throws Exception {

        File testFileA = new File(basePath + "a/ChangeInsideSiblingBlock.java");
        File testFileB = new File(basePath + "b/ChangeInsideSiblingBlock.java");
        ResourceSet rsA = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
        ResourceSet rsB = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

        JavaSourceOrClassFileResource resA = (JavaSourceOrClassFileResource) rsA.getResources().get(0);
        assertThat("Errors during Extraction " + resA.getErrors(), resA.getErrors().size(), is(0));
        JavaSourceOrClassFileResource resB = (JavaSourceOrClassFileResource) rsA.getResources().get(0);
        assertThat("Errors during Extraction " + resB.getErrors(), resB.getErrors().size(), is(0));

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(rsA, rsB, TestUtil.DIFF_OPTIONS);

        EList<Diff> differences = comparison.getDifferences();

        assertThat("Wrong number of differences", differences.size(), is(2));
    }

    /**
     * Test insertion of new statements
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testReturnStatementsDiff() throws Exception {

        File testFileA = new File(basePath + "a/ReturnStatementChanges.java");
        File testFileB = new File(basePath + "b/ReturnStatementChanges.java");
        ResourceSet rsA = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
        ResourceSet rsB = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(rsA, rsB, TestUtil.DIFF_OPTIONS);

        EList<Diff> differences = comparison.getDifferences();

        assertThat("Wrong number of differences", differences.size(), is(4));
    }

    /**
     * Test insertion of new statements
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testThrowStatementsDiff() throws Exception {

        File testFileA = new File(basePath + "a/ThrowStatements.java");
        File testFileB = new File(basePath + "b/ThrowStatements.java");
        ResourceSet rsA = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
        ResourceSet rsB = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(rsA, rsB, TestUtil.DIFF_OPTIONS);

        EList<Diff> differences = comparison.getDifferences();

        assertThat("Wrong number of differences", differences.size(), is(3));
    }

    /**
     * Test insertion of new statements
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testTryCatchDiff() throws Exception {

        File testFileA = new File(basePath + "a/TryCatch.java");
        File testFileB = new File(basePath + "b/TryCatch.java");
        ResourceSet rsA = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
        ResourceSet rsB = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(rsA, rsB, TestUtil.DIFF_OPTIONS);

        EList<Diff> differences = comparison.getDifferences();

        assertThat("Wrong number of differences", differences.size(), is(1));
    }

    /**
     * Test insertion of new statements
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testVariableDeclarationDiff() throws Exception {

        File testFileA = new File(basePath + "a/VariableDeclarationStatements.java");
        File testFileB = new File(basePath + "b/VariableDeclarationStatements.java");
        ResourceSet rsA = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
        ResourceSet rsB = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(rsA, rsB, TestUtil.DIFF_OPTIONS);

        EList<Diff> differences = comparison.getDifferences();

        assertThat("Wrong number of differences", differences.size(), is(1));
    }

    /**
     * Test statement changes in a static initialization block.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testStaticInitializationDiff() throws Exception {

        File testFileA = new File(basePath + "a/StaticInitialization.java");
        File testFileB = new File(basePath + "b/StaticInitialization.java");
        ResourceSet rsA = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
        ResourceSet rsB = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(rsA, rsB, TestUtil.DIFF_OPTIONS);

        EList<Diff> differences = comparison.getDifferences();

        assertThat("Wrong number of differences", differences.size(), is(2));
        // TODO Implement static initializer test
        // StatementChange change = (StatementChange) differences.get(1);
        // LocalVariableStatement statement = (LocalVariableStatement) change.getChangedStatement();
        // assertThat("Wrong var detected as changed.", statement.getVariable().getName(),
        // equalTo("varMiddle"));

    }
}
