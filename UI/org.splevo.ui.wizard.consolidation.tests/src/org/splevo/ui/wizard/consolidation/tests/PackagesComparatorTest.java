/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.ui.wizard.consolidation.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.eclipse.jdt.core.IPackageFragment;
import org.junit.Test;
import org.splevo.ui.wizard.consolidation.util.PackagesComparator;

/**
 * Unit test for the functions of the {@link PackagesComparator}.
 * 
 * @author Radoslav Yankov
 */
public class PackagesComparatorTest {
    
    /**
     * Test if the method compare() returns the correct result if the name of the first package is
     * lexicographically less than the name of the another one.
     */
    @Test
    public void testLessThan() {
        Comparator<IPackageFragment> packagesComparator = new PackagesComparator();

        IPackageFragment javaPackage1 = mock(IPackageFragment.class);
        IPackageFragment javaPackage2 = mock(IPackageFragment.class);

        when(javaPackage1.getElementName()).thenReturn("a");
        when(javaPackage2.getElementName()).thenReturn("b");
        
        int result = packagesComparator.compare(javaPackage1, javaPackage2);
        
        assertTrue("returned number should be negative", result < 0);
    }

    /**
     * Test if the method compare() returns the correct result if the names of the packages are the
     * same.
     */
    @Test
    public void testEqual() {
        Comparator<IPackageFragment> packagesComparator = new PackagesComparator();

        IPackageFragment javaPackage1 = mock(IPackageFragment.class);
        IPackageFragment javaPackage2 = mock(IPackageFragment.class);

        when(javaPackage1.getElementName()).thenReturn("same name");
        when(javaPackage2.getElementName()).thenReturn("same name");
        
        assertThat("returned number", packagesComparator.compare(javaPackage1, javaPackage2), is(0));
    }
    
    /**
     * Test if the method compare() returns the correct result if the name of the first package is
     * lexicographically greater than the name of the another one.
     */
    @Test
    public void testGreaterThan() {
        Comparator<IPackageFragment> packagesComparator = new PackagesComparator();

        IPackageFragment javaPackage1 = mock(IPackageFragment.class);
        IPackageFragment javaPackage2 = mock(IPackageFragment.class);

        when(javaPackage1.getElementName()).thenReturn("b");
        when(javaPackage2.getElementName()).thenReturn("a");
        
        int result = packagesComparator.compare(javaPackage1, javaPackage2);
        
        assertTrue("returned number should be positive", result > 0);
    }
}
