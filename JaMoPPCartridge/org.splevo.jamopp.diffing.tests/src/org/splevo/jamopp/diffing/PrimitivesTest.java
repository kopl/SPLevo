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
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Sets;

/**
 * Unit test to prove the differencing of method declarations.
 */
public class PrimitivesTest {

    /** Base path of the test code */
    private String basePath = "testmodels/implementation/primitives/";

    /**
     * Initialize the test infrastructure.
     */
    @BeforeClass
    public static void setUp() {
        TestUtil.setUp();
    }

    /**
     * Test primitive declarations
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testPrimitivesDiff() throws Exception {

        File testFileA = new File(basePath + "a/Primitives.java");
        File testFileB = new File(basePath + "b/Primitives.java");
        ResourceSet rsA = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
        ResourceSet rsB = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(rsA, rsB, TestUtil.getDiffOptions());

        EList<Diff> differences = comparison.getDifferences();

        assertThat("Wrong number of differences", differences.size(), is(2));
    }
}
