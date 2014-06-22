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

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange;

/**
 * Unit test to prove the mapping options of the diffing.
 */
public class SimilarClassesDifferentPackagesTest {

    private static final String BASE_PATH = "testmodels/implementation/similarclasses-differentpackages/";

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(SimilarClassesDifferentPackagesTest.class);

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
        setA = TestUtil.extractModel(BASE_PATH + "a");
        setB = TestUtil.extractModel(BASE_PATH + "b");
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDoDiff() throws Exception {

        TestUtil.setUp();

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Map<String, String> diffOptions = TestUtil.getDiffOptions();
        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        assertThat("Wrong number of matched resources", comparison.getMatchedResources().size(), is(2));

        EList<Diff> differences = comparison.getDifferences();
        for (Diff diffElement : differences) {
            logger.debug(diffElement.getClass().getSimpleName());
            if (diffElement instanceof CompilationUnitChange) {
                CompilationUnitChange change = (CompilationUnitChange) diffElement;
                logger.debug(change.getChangedCompilationUnit().getName());
            }
        }
        assertThat("No difference should exist",  comparison.getMatches().size(), is(0));

    }
}
