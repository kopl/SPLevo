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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.SortedSet;

import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.junit.Test;
import org.splevo.ui.wizard.consolidation.provider.PackagesTreeContentProvider;

/**
 * Unit test for the functions of the {@link PackagesTreeContentProvider}.
 * 
 * @author Radoslav Yankov
 */
public class PackagesTreeContentProviderTest {

    /**
     * Test if the method getElements() returns a correct result.
     */
    @Test
    public void testGetElements() {
        ITreeContentProvider packagesTreeContentProvider = new PackagesTreeContentProvider();

        IPackageFragment javaPackage1 = mock(IPackageFragment.class);
        IPackageFragment javaPackage2 = mock(IPackageFragment.class);

        IPackageFragment[] rootPackages = { javaPackage1, javaPackage2 };

        Object[] result = packagesTreeContentProvider.getElements(rootPackages);

        assertThat("returned root packages", (IPackageFragment[]) result, is(rootPackages));
    }

    /**
     * Test if the method getChildren() returns a correct result.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetChildren() {
        PackagesTreeContentProvider packagesTreeContentProvider = new PackagesTreeContentProvider();

        IPackageFragment parentPackage = mock(IPackageFragment.class);
        when(parentPackage.getElementName()).thenReturn("org.splevo");

        IPackageFragment javaPackage1 = mock(IPackageFragment.class);
        when(javaPackage1.getElementName()).thenReturn("org.splevo.wizard");

        IPackageFragment javaPackage2 = mock(IPackageFragment.class);
        when(javaPackage2.getElementName()).thenReturn("org.splevo.test");

        SortedSet<IPackageFragment> javaPackages = mock(SortedSet.class);
        Iterator<IPackageFragment> iterator = mock(Iterator.class);

        when(javaPackages.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, true, true, false);
        when(iterator.next()).thenReturn(parentPackage, javaPackage1, javaPackage2);

        packagesTreeContentProvider.setJavaPackages(javaPackages);

        Object[] result = packagesTreeContentProvider.getChildren(parentPackage);
        IPackageFragment[] subPackages = { javaPackage1, javaPackage2 };

        assertThat("returned sub packages", (IPackageFragment[]) result, is(subPackages));
    }

    /**
     * Test if the method getParent() returns a correct result.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetParent() {
        PackagesTreeContentProvider packagesTreeContentProvider = new PackagesTreeContentProvider();

        IPackageFragment childPackage = mock(IPackageFragment.class);
        when(childPackage.getElementName()).thenReturn("org.splevo.wizard");

        IPackageFragment parentPackage = mock(IPackageFragment.class);
        when(parentPackage.getElementName()).thenReturn("org.splevo");

        IPackageFragment anotherPackage = mock(IPackageFragment.class);
        when(anotherPackage.getElementName()).thenReturn("org.splevo.ui");

        SortedSet<IPackageFragment> javaPackages = mock(SortedSet.class);
        Iterator<IPackageFragment> iterator = mock(Iterator.class);

        when(javaPackages.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, true, true, false);
        when(iterator.next()).thenReturn(childPackage, parentPackage, anotherPackage);

        packagesTreeContentProvider.setJavaPackages(javaPackages);

        assertThat("returned parent package", (IPackageFragment) packagesTreeContentProvider.getParent(childPackage),
                is(parentPackage));
    }

    /**
     * Test if the method hasChildren() returns a correct result.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testHasChildren() {
        PackagesTreeContentProvider packagesTreeContentProvider = new PackagesTreeContentProvider();

        IPackageFragment javaPackage1 = mock(IPackageFragment.class);
        when(javaPackage1.getElementName()).thenReturn("org.splevo");

        IPackageFragment javaPackage2 = mock(IPackageFragment.class);
        when(javaPackage2.getElementName()).thenReturn("org.splevo.wizard");

        IPackageFragment javaPackage3 = mock(IPackageFragment.class);
        when(javaPackage3.getElementName()).thenReturn("org.splevo.test");

        SortedSet<IPackageFragment> javaPackages = mock(SortedSet.class);
        Iterator<IPackageFragment> iterator = mock(Iterator.class);

        when(javaPackages.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, true, true, false);
        when(iterator.next()).thenReturn(javaPackage1, javaPackage2, javaPackage3);

        packagesTreeContentProvider.setJavaPackages(javaPackages);

        assertThat("returned result", packagesTreeContentProvider.hasChildren(javaPackage1), is(true));
        assertThat("returned result", packagesTreeContentProvider.hasChildren(javaPackage2), is(false));
    }
}
