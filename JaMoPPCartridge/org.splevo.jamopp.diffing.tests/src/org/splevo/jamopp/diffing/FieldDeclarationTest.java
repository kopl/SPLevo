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

import java.io.File;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.members.Field;
import org.junit.Test;
import org.splevo.jamopp.diffing.jamoppdiff.FieldChange;

import com.google.common.collect.Sets;

/**
 * Unit test to prove the differencing of field declarations.
 */
public class FieldDeclarationTest {

    /** Base path of the leading test code */
    private String basePathA = "testmodels/implementation/fielddeclaration/a/";
    /** Base path of the integration test code */
    private String basePathB = "testmodels/implementation/fielddeclaration/b/";

    /**
     * Test diffing of changed array field declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testArrayFieldDeclarationDiff() throws Exception {

        TestUtil.setUp();
        File testFileA = new File(basePathA + "ArrayFieldDeclarationChange.java");
        File testFileB = new File(basePathB + "ArrayFieldDeclarationChange.java");
        ResourceSet rsA = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
        ResourceSet rsB = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(rsA, rsB, TestUtil.getDiffOptions());

        EList<Diff> differences = comparison.getDifferences();

        assertThat("1 difference should be detected", differences.size(), is(1));
        FieldChange change = (FieldChange) differences.get(0);
        assertThat("Wrong diff kind", change.getKind(), is(DifferenceKind.CHANGE));
        assertThat("Diff should be FieldChange", change, is(instanceOf(FieldChange.class)));
        Field field = change.getChangedField();
        assertThat("Wrong field name", field.getName(), is("newValueArray"));
    }

    /**
     * Test new field declarations to ignore field order.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testNewInTheMiddleDiff() throws Exception {

        TestUtil.setUp();
        File testFileA = new File(basePathA + "NewInTheMiddle.java");
        File testFileB = new File(basePathB + "NewInTheMiddle.java");
        ResourceSet rsLeading = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
        ResourceSet rsIntegration = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(rsLeading, rsIntegration, TestUtil.getDiffOptions());

        EList<Diff> differences = comparison.getDifferences();

        assertThat("1 difference should be detected", differences.size(), is(1));
        FieldChange change = (FieldChange) differences.get(0);
        assertThat("Diff should be FieldChange", change, is(instanceOf(FieldChange.class)));
        assertThat("Wrong diff kind", change.getKind(), is(DifferenceKind.ADD));
        Field field = change.getChangedField();
        assertThat("Wrong field name", field.getName(), is("newField"));
    }

    /**
     * Test new field declarations to ignore field order.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testRemovedFromTheMiddleDiff() throws Exception {

        TestUtil.setUp();
        File testFileA = new File(basePathA + "RemovedFromTheMiddle.java");
        File testFileB = new File(basePathB + "RemovedFromTheMiddle.java");
        ResourceSet rsLeading = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
        ResourceSet rsIntegration = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(rsLeading, rsIntegration, TestUtil.getDiffOptions());

        EList<Diff> differences = comparison.getDifferences();

        assertThat("1 difference should be detected", differences.size(), is(1));
        FieldChange change = (FieldChange) differences.get(0);
        assertThat("Diff should be FieldChange", change, is(instanceOf(FieldChange.class)));
        assertThat("Wrong diff kind", change.getKind(), is(DifferenceKind.DELETE));
        Field field = change.getChangedField();
        assertThat("Wrong field name", field.getName(), is("removeField"));
    }
}
