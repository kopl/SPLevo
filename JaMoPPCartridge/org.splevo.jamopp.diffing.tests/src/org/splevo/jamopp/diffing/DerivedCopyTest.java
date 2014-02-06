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

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;

import com.google.common.collect.Lists;

/**
 * Unit test to prove the derived copy handling of the differ.
 */
public class DerivedCopyTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(DerivedCopyTest.class);

    private static ResourceSet setA;
    private static ResourceSet setB;

    /**
     * Initialize the test by loading the resources once to be used by the several diff tests.
     *
     * @throws Exception
     *             A failed initialization
     */
    @BeforeClass
    public static void initTest() throws Exception {

        TestUtil.setUp();

        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        String basePath = "testmodels/implementation/derivedcopy/";
        List<URI> urisA = Lists.asList(URI.createFileURI(basePath + "a"), new URI[] {});
        List<URI> urisB = Lists.asList(URI.createFileURI(basePath + "b"), new URI[] {});
        NullProgressMonitor monitor = new NullProgressMonitor();
        setA = extractor.extractSoftwareModel(urisA, monitor, null);
        setB = extractor.extractSoftwareModel(urisB, monitor, null);
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    @Ignore
    public void testDoDiff() throws Exception {

        TestUtil.setUp();

        StringBuilder packageMapping = new StringBuilder();

        StringBuilder classifierNormalization = new StringBuilder();
        classifierNormalization.append("*Custom");

        JaMoPPDiffer differ = new JaMoPPDiffer();

        Map<String, String> diffOptions = TestUtil.DIFF_OPTIONS;
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_PACKAGE_NORMALIZATION, packageMapping.toString());
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_CLASSIFIER_NORMALIZATION, classifierNormalization.toString());
        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        assertThat("Should only match two test classes", comparison.getMatchedResources().size(), is(2));

        EList<Diff> differences = comparison.getDifferences();
        assertThat("No diff because 1 method unchanged and 1 derived", differences.size(), is(0));

    }
}
