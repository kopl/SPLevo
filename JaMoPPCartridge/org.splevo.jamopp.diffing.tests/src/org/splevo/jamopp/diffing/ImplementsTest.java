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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.commons.NamedElement;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.diffing.jamoppdiff.ImplementsChange;

/**
 * Unit test to prove the differencing of changed implements relationships.
 */
public class ImplementsTest {

    private static final String BASE_PATH = "testmodels/implementation/ImplementsChange/";

    /**
     * Initialize the test by loading the resources once to be used by the severall diff tests.
     *
     * @throws Exception
     *             A failed initialization
     */
    @BeforeClass
    public static void initTest() throws Exception {
        TestUtil.setUp();
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testClassImplements() throws Exception {

        ResourceSet setA = TestUtil.extractModel(BASE_PATH + "classimplements/" + "a");
        ResourceSet setB = TestUtil.extractModel(BASE_PATH + "classimplements/" + "b");

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Map<String, String> diffOptions = TestUtil.getDiffOptions();
        diffOptions.put(JaMoPPDiffer.OPTION_JAMOPP_IGNORE_FILES, "");
        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        EList<Diff> differences = comparison.getDifferences();
        assertThat("Wrong number of differences", differences.size(), is(4));

        for (Diff diffElement : differences) {

            if (diffElement instanceof ImplementsChange) {
                ImplementsChange implementsChange = ((ImplementsChange) diffElement);
                NamedElement target = (NamedElement) implementsChange.getChangedReference().getTarget();
                if (implementsChange.getKind() == DifferenceKind.ADD) {
                    assertThat(target.getName(), equalTo("InterfaceA"));
                } else if (implementsChange.getKind() == DifferenceKind.DELETE) {
                    assertThat(target.getName(), equalTo("InterfaceB"));
                } else {
                    fail("Unexpected Difference Kind: " + implementsChange.getKind());
                }
            } else {
                fail("No other diff elements than ExtendsChange should have been detected: " + diffElement);
            }
        }
    }
}
