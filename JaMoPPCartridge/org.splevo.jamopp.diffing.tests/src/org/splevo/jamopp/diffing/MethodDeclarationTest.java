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
import static org.junit.Assert.fail;

import java.io.File;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.parameters.Parameter;
import org.junit.Test;
import org.splevo.jamopp.diffing.jamoppdiff.ConstructorChange;
import org.splevo.jamopp.diffing.jamoppdiff.MethodChange;

import com.google.common.collect.Sets;

/**
 * Unit test to prove the differencing of method declarations.
 */
public class MethodDeclarationTest {

    private static final String BASE_PATH = "testmodels/implementation/methoddeclaration/";

    /**
     * Test method to detect changes in the method declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDoDiff() throws Exception {

        TestUtil.setUp();
        File testFileA = new File(BASE_PATH + "a/ClassA.java");
        File testFileB = new File(BASE_PATH + "b/ClassA.java");
        ResourceSet rsA = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
        ResourceSet rsB = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(rsA, rsB, TestUtil.getDiffOptions());

        EList<Diff> differences = comparison.getDifferences();

        assertThat("Wrong number of differences detected", differences.size(), is(1));
        assertThat("Wrong change type", differences.get(0), instanceOf(MethodChange.class));
        MethodChange methodChange = ((MethodChange) differences.get(0));
        assertThat("Wrong DifferenceKind", methodChange.getKind(), is(DifferenceKind.ADD));
        Method method = methodChange.getChangedMethod();
        assertThat("Wrong method", method.getName(), is("newMethod"));
    }

    /**
     * Test method to detect changes in the method declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testConstructorDiff() throws Exception {

        TestUtil.setUp();
        File testFileA = new File(BASE_PATH + "a/Constructor.java");
        File testFileB = new File(BASE_PATH + "b/Constructor.java");
        ResourceSet rsA = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
        ResourceSet rsB = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(rsA, rsB, TestUtil.getDiffOptions());

        EList<Diff> differences = comparison.getDifferences();

        assertThat("Wrong number of differences detected", differences.size(), is(2));
        for (Diff diff : differences) {
            assertThat("Wrong change type", diff, instanceOf(ConstructorChange.class));
            ConstructorChange change = ((ConstructorChange) differences.get(0));
            Constructor constructor = change.getChangedConstructor();
            EList<Parameter> params = constructor.getParameters();
            if (change.getKind() == DifferenceKind.ADD) {
                assertThat("Wrong number of differences detected", params.size(), is(1));
            } else if (change.getKind() == DifferenceKind.DELETE) {
                assertThat("Wrong number of differences detected", params.size(), is(0));
            } else {
                fail("Unexpected diff kind " + change.getKind());
            }

        }
    }
}
