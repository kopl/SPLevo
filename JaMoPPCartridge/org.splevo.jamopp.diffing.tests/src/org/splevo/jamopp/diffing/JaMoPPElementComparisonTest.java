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

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.members.ClassMethod;
import org.junit.Test;
import org.splevo.jamopp.diffing.jamoppdiff.StatementChange;

import com.google.common.collect.Maps;

/**
 * Unit test to prove the direct element comparison instead of complete resource sets as input.
 */
public class JaMoPPElementComparisonTest {

    private static final String BASE_PATH = "testmodels/implementation/JaMoPPElementComparison/";

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(JaMoPPElementComparisonTest.class);

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDoDiff() throws Exception {

        TestUtil.setUp();
        ResourceSet setA = TestUtil.extractModel(BASE_PATH + "a");
        ResourceSet setB = TestUtil.extractModel(BASE_PATH + "b");

        ClassMethod methodA = searchMethodElement(setA);
        ClassMethod methodB = searchMethodElement(setB);

        JaMoPPDiffer differ = new JaMoPPDiffer();

        Map<String, String> diffOptions = Maps.newHashMap();
        Comparison comparison = differ.doDiff(methodA, methodB, diffOptions);

        EList<Diff> differences = comparison.getDifferences();
        for (Diff diffElement : differences) {
            logger.debug(diffElement.getClass().getSimpleName());
            if (diffElement instanceof StatementChange) {
                StatementChange change = (StatementChange) diffElement;
                logger.debug(TestUtil.printDiff(change));
            }
        }
        assertThat("Exactly one change should exist", differences.size(), is(1));
        Diff diff = differences.get(0);
        assertThat(diff, instanceOf(StatementChange.class));
    }

    /**
     * Get a {@link ClassMethod} element out of a resource set assuming there is only one resource in the set
     * containing exactly one method.
     *
     * @param resourceSet
     *            The set to search in.
     * @return The first method found.
     */
    private ClassMethod searchMethodElement(ResourceSet resourceSet) {
        Resource resource = resourceSet.getResources().get(0);
        TreeIterator<EObject> contentIterator = resource.getAllContents();
        ClassMethod methodUnderTest = null;
        while (contentIterator.hasNext()) {
            EObject next = contentIterator.next();
            if (next instanceof ClassMethod) {
                methodUnderTest = (ClassMethod) next;
                break;
            }
        }
        return methodUnderTest;
    }
}
