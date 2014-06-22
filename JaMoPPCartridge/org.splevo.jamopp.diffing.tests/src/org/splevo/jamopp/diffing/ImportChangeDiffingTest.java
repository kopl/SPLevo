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

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.imports.ClassifierImport;
import org.junit.Test;
import org.splevo.jamopp.diffing.jamoppdiff.ImportChange;

import com.google.common.collect.Sets;

/**
 * Test the diffing of changed imports.
 */
public class ImportChangeDiffingTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(ImportChangeDiffingTest.class);

    private String basePath = "testmodels/implementation/import/";

    /**
     * Test method to detect changes in import declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDoDiff() throws Exception {

		TestUtil.setUp();
		File testFileA = new File(basePath + "a/ImportDiffing.java");
		File testFileB = new File(basePath + "b/ImportDiffing.java");
		ResourceSet rsA = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
		ResourceSet rsB = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

		JaMoPPDiffer differ = new JaMoPPDiffer();
		Comparison comparison = differ.doDiff(rsA, rsB, TestUtil.getDiffOptions());

		EList<Diff> differences = comparison.getDifferences();

        for (Diff diff : differences) {
            logger.debug(diff.getKind() + ": " + TestUtil.printDiff(diff));
        }

        assertThat("Wrong number of differences detected", differences.size(), is(2));

        assertThat("Wrong change kind", differences.get(0).getKind(), is(DifferenceKind.ADD));
        ImportChange change0 = (ImportChange) differences.get(0);
        ClassifierImport import0 = (ClassifierImport) change0.getChangedImport();
        String class0 = import0.getClassifier().getName();
        assertThat("BigDecimal should have been recognized as new import", class0, equalTo("BigDecimal"));

        assertThat("Wrong change kind", differences.get(1).getKind(), is(DifferenceKind.DELETE));
        ImportChange change1 = (ImportChange) differences.get(1);
        ClassifierImport import1 = (ClassifierImport) change1.getChangedImport();
        String class1 = import1.getClassifier().getName();
        assertThat("BigInteger should have been recognized as new import", class1, equalTo("BigInteger"));
    }
}
