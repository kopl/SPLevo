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
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.compare.MatchResource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.members.Field;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.diffing.jamoppdiff.FieldChange;
import org.splevo.jamopp.diffing.jamoppdiff.ImportChange;
import org.splevo.jamopp.diffing.jamoppdiff.StatementChange;
import org.splevo.jamopp.diffing.postprocessor.JaMoPPPostProcessor;

/**
 * Unit test to prove the derived copy handling of the differ.
 */
public class DerivedCopyTest {

    /**
     * Initialize the test by loading the resources once to be used by the several diff tests.
     *
     * @throws Exception
     *             A failed initialization
     */
    @BeforeClass
    public static void initTest() throws Exception {
        TestUtil.setUp();
    }

    /**
     * Test to detect the correctly matched original and derived class. Further aspects are tested
     * by separate tests.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDerivedCopyWithNoChange() throws Exception {

        String basePath = "testmodels/implementation/derivedcopy/";
        ResourceSet setA = TestUtil.extractModel(basePath + "a");
        ResourceSet setB = TestUtil.extractModel(basePath + "b");

        StringBuilder packageMapping = new StringBuilder();

        StringBuilder classifierNormalization = new StringBuilder();
        classifierNormalization.append("*Custom");

        JaMoPPDiffer differ = new JaMoPPDiffer();

        Map<String, String> diffOptions = TestUtil.getDiffOptions();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_PACKAGE_NORMALIZATION, packageMapping.toString());
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_CLASSIFIER_NORMALIZATION, classifierNormalization.toString());
        diffOptions.put(JaMoPPPostProcessor.OPTION_DIFF_CLEANUP_DERIVED_COPIES, "true");

        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        assertThat("One match of original and derived class", comparison.getMatchedResources().size(), is(1));

        MatchResource resourceMatch = comparison.getMatchedResources().get(0);
        assertThat("ResourceMatch must have left set", resourceMatch.getLeft(), notNullValue());
        assertThat("ResourceMatch must have right set", resourceMatch.getRight(), notNullValue());

        EList<Diff> differences = comparison.getDifferences();
        assertThat("No diff expected due to class match", differences.size(), is(0));

    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDerivedCopyWithIgnoreFields() throws Exception {

        String basePath = "testmodels/implementation/derivedcopyfield/";
        ResourceSet setA = TestUtil.extractModel(basePath + "a");
        ResourceSet setB = TestUtil.extractModel(basePath + "b");

        StringBuilder packageMapping = new StringBuilder();

        StringBuilder classifierNormalization = new StringBuilder();
        classifierNormalization.append("*Custom");

        JaMoPPDiffer differ = new JaMoPPDiffer();

        Map<String, String> diffOptions = TestUtil.getDiffOptions();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_PACKAGE_NORMALIZATION, packageMapping.toString());
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_CLASSIFIER_NORMALIZATION, classifierNormalization.toString());
        diffOptions.put(JaMoPPPostProcessor.OPTION_DIFF_CLEANUP_DERIVED_COPIES, "true");

        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        EList<Diff> differences = comparison.getDifferences();

        for (Diff diff : differences) {
            if (diff.getKind() == DifferenceKind.DELETE) {
                assertThat("DELETE of wrong type detected", diff, instanceOf(FieldChange.class));
                Field field = ((FieldChange) diff).getChangedField();
                assertThat("Unexpected field detected as DELETE", field.getName(), is("oldField"));

            } else if (diff.getKind() == DifferenceKind.ADD) {
                assertThat("DELETE of wrong type detected", diff, instanceOf(FieldChange.class));
                Field field = ((FieldChange) diff).getChangedField();
                assertThat("Unexpected method detected as ADD", field.getName(), is("newField"));

            } else {
                fail("Change of unexpected kind detected");
            }
        }

        assertThat("Exprected detection: 1 deleted private and 1 added public field", differences.size(), is(2));

    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDerivedCopyWithIgnoreImports() throws Exception {

        String basePath = "testmodels/implementation/derivedcopyimport/";
        ResourceSet setA = TestUtil.extractModel(basePath + "a");
        ResourceSet setB = TestUtil.extractModel(basePath + "b");

        StringBuilder packageMapping = new StringBuilder();

        StringBuilder classifierNormalization = new StringBuilder();
        classifierNormalization.append("*Custom");

        JaMoPPDiffer differ = new JaMoPPDiffer();

        Map<String, String> diffOptions = TestUtil.getDiffOptions();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_PACKAGE_NORMALIZATION, packageMapping.toString());
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_CLASSIFIER_NORMALIZATION, classifierNormalization.toString());
        diffOptions.put(JaMoPPPostProcessor.OPTION_DIFF_CLEANUP_DERIVED_COPIES, "true");

        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        EList<Diff> differences = comparison.getDifferences();
        assertThat("No diff because not present imports must not be detected as deleted", differences.size(), is(0));
    }

    /**
     * Test that the import ignoring is really working.
     *
     * Recheck the same test and expect an import delete.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDerivedCopyWithIgnoreImportsCounterpart() throws Exception {

        String basePath = "testmodels/implementation/derivedcopyimport/";
        ResourceSet setA = TestUtil.extractModel(basePath + "a");
        ResourceSet setB = TestUtil.extractModel(basePath + "b");

        StringBuilder packageMapping = new StringBuilder();

        StringBuilder classifierNormalization = new StringBuilder();
        classifierNormalization.append("*Custom");

        JaMoPPDiffer differ = new JaMoPPDiffer();

        Map<String, String> diffOptions = TestUtil.getDiffOptions();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_PACKAGE_NORMALIZATION, packageMapping.toString());
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_CLASSIFIER_NORMALIZATION, classifierNormalization.toString());
        diffOptions.put(JaMoPPPostProcessor.OPTION_DIFF_CLEANUP_DERIVED_COPIES, "true");
        diffOptions.put(JaMoPPPostProcessor.OPTION_DIFF_CLEANUP_DERIVED_COPIES_CLEAN_IMPORTS, null);

        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        EList<Diff> differences = comparison.getDifferences();
        assertThat("Import delete must be detected if filter is set to false", differences.size(), is(2));
        Diff diff = differences.get(0);
        assertThat("Wrong change detected", diff, instanceOf(ImportChange.class));
        assertThat("Wrong change kind detected", diff.getKind(), is(DifferenceKind.DELETE));
    }

    /**
     * Test method for a derived copy scenarios with derived, overloaded and not visible methods.
     * <ul>
     * <li>1 method derived</li>
     * <li>1 method overridden</li>
     * <li>1 method not visible</li>
     * </ul>
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDerivedCopyWithChangedMethod() throws Exception {

        String basePath = "testmodels/implementation/derivedcopymethod/";
        ResourceSet setA = TestUtil.extractModel(basePath + "a");
        ResourceSet setB = TestUtil.extractModel(basePath + "b");

        StringBuilder packageMapping = new StringBuilder();

        StringBuilder classifierNormalization = new StringBuilder();
        classifierNormalization.append("*Custom");

        JaMoPPDiffer differ = new JaMoPPDiffer();

        Map<String, String> diffOptions = TestUtil.getDiffOptions();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_PACKAGE_NORMALIZATION, packageMapping.toString());
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_CLASSIFIER_NORMALIZATION, classifierNormalization.toString());
        diffOptions.put(JaMoPPPostProcessor.OPTION_DIFF_CLEANUP_DERIVED_COPIES, "true");

        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        EList<Diff> differences = comparison.getDifferences();

        for (Diff diff : differences) {
            if (diff.getKind() == DifferenceKind.ADD) {
                assertThat("ADD of wrong type detected", diff, instanceOf(StatementChange.class));
            } else {
                fail("Change of unexpected kind detected: " + diff.getKind());
            }
        }

        assertThat("Wrong number of differences", differences.size(), is(1));
    }

    /**
     * Test that the derived method delete is not filtered if anyway.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDerivedCopyWithChangedMethodCounterpart() throws Exception {

        String basePath = "testmodels/implementation/derivedcopymethod/";
        ResourceSet setA = TestUtil.extractModel(basePath + "a");
        ResourceSet setB = TestUtil.extractModel(basePath + "b");

        StringBuilder packageMapping = new StringBuilder();

        StringBuilder classifierNormalization = new StringBuilder();
        classifierNormalization.append("*Custom");

        JaMoPPDiffer differ = new JaMoPPDiffer();

        Map<String, String> diffOptions = TestUtil.getDiffOptions();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_PACKAGE_NORMALIZATION, packageMapping.toString());
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_CLASSIFIER_NORMALIZATION, classifierNormalization.toString());
        diffOptions.put(JaMoPPPostProcessor.OPTION_DIFF_CLEANUP_DERIVED_COPIES, "true");
        diffOptions.put(JaMoPPPostProcessor.OPTION_DIFF_CLEANUP_DERIVED_COPIES_CLEAN_METHODS, null);

        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        EList<Diff> differences = comparison.getDifferences();
        assertThat("Wrong number of differences", differences.size(), is(3));
    }

    /**
     * Test that the derived method delete is not filtered if anyway.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDerivedCopyWithIgnoreConstructor() throws Exception {

        String basePath = "testmodels/implementation/derivedcopyconstructor/";
        ResourceSet setA = TestUtil.extractModel(basePath + "a");
        ResourceSet setB = TestUtil.extractModel(basePath + "b");

        StringBuilder packageMapping = new StringBuilder();

        StringBuilder classifierNormalization = new StringBuilder();
        classifierNormalization.append("*Custom");

        JaMoPPDiffer differ = new JaMoPPDiffer();

        Map<String, String> diffOptions = TestUtil.getDiffOptions();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_PACKAGE_NORMALIZATION, packageMapping.toString());
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_CLASSIFIER_NORMALIZATION, classifierNormalization.toString());
        diffOptions.put(JaMoPPPostProcessor.OPTION_DIFF_CLEANUP_DERIVED_COPIES, "true");

        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        EList<Diff> differences = comparison.getDifferences();
        assertThat("There should be no differences", differences.size(), is(0));
    }
}
