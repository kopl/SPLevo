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
import org.splevo.jamopp.diffing.jamoppdiff.ExtendsChange;

/**
 * Unit test to prove the differencing of changed extends relationships.
 */
public class ExtendsTest {

    private static final String BASE_PATH = "testmodels/implementation/ExtendsChange/";

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
    public void testClassExtends() throws Exception {

        ResourceSet setA = TestUtil.extractModel(BASE_PATH + "classextends/" + "a");
        ResourceSet setB = TestUtil.extractModel(BASE_PATH + "classextends/" + "b");

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Map<String, String> diffOptions = TestUtil.getDiffOptions();
        diffOptions.put(JaMoPPDiffer.OPTION_JAMOPP_IGNORE_FILES, "");
        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        EList<Diff> differences = comparison.getDifferences();
        assertThat("Wrong number of differences", differences.size(), is(4));

        for (Diff diffElement : differences) {

            if (diffElement instanceof ExtendsChange) {
                ExtendsChange extendsChange = ((ExtendsChange) diffElement);
                NamedElement target = (NamedElement) extendsChange.getChangedReference().getTarget();
                if (extendsChange.getKind() == DifferenceKind.ADD) {
                    assertThat(target.getName(), equalTo("String"));
                } else if (extendsChange.getKind() == DifferenceKind.DELETE) {
                    assertThat(target.getName(), equalTo("Integer"));
                } else {
                    fail("Unexpected Difference Kind: " + extendsChange.getKind());
                }
            } else {
                fail("No other diff elements than ExtendsChange should have been detected: " + diffElement);
            }
        }
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testInterfaceExtends() throws Exception {

        ResourceSet setA = TestUtil.extractModel(BASE_PATH + "interfaceextends/" + "a");
        ResourceSet setB = TestUtil.extractModel(BASE_PATH + "interfaceextends/" + "b");

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Map<String, String> diffOptions = TestUtil.getDiffOptions();
        diffOptions.put(JaMoPPDiffer.OPTION_JAMOPP_IGNORE_FILES, "");
        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        EList<Diff> differences = comparison.getDifferences();
        assertThat("Wrong number of differences", differences.size(), is(4));

        for (Diff diffElement : differences) {

            if (diffElement instanceof ExtendsChange) {
                ExtendsChange extendsChange = ((ExtendsChange) diffElement);
                NamedElement target = (NamedElement) extendsChange.getChangedReference().getTarget();
                if (extendsChange.getKind() == DifferenceKind.ADD) {
                    assertThat(target.getName(), equalTo("Runnable"));
                } else if (extendsChange.getKind() == DifferenceKind.DELETE) {
                    assertThat(target.getName(), equalTo("Cloneable"));
                } else {
                    fail("Unexpected Difference Kind: " + extendsChange.getKind());
                }
            } else {
                fail("No other diff elements than ExtendsChange should have been detected: " + diffElement);
            }
        }
    }
}
