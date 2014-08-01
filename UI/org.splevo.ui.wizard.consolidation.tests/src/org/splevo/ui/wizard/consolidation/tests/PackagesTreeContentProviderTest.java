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

import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.junit.Test;
import org.splevo.ui.wizard.consolidation.provider.PackagesTreeContentProvider;
import org.splevo.ui.wizard.consolidation.util.PackagesComparator;


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
        
        IPackageFragment[] rootPackages = {javaPackage1, javaPackage2};
        
        Object[] result = packagesTreeContentProvider.getElements(rootPackages);
                                
        assertThat("returned root packages", (IPackageFragment[]) result, is(rootPackages));
    }
    
    /**
     * Test if the method getChildren() returns a correct result.
     */
    @Test
    public void testGetChildren() {
        PackagesTreeContentProvider packagesTreeContentProvider = new PackagesTreeContentProvider();
        
        IPackageFragment parentPackage = mock(IPackageFragment.class);        
        when(parentPackage.getElementName()).thenReturn("org.splevo");
        
        IPackageFragment javaPackage1 = mock(IPackageFragment.class);
        when(javaPackage1.getElementName()).thenReturn("org.splevo.wizard");
        
        IPackageFragment javaPackage2 = mock(IPackageFragment.class);
        when(javaPackage2.getElementName()).thenReturn("org.splevo.test");
        
//        PackagesComparator packagesComparator = mock(PackagesComparator.class);
//        when(packagesComparator.compare(javaPackage1, javaPackage2)).thenReturn(2);
                       
        SortedSet<IPackageFragment> javaPackages = new TreeSet<IPackageFragment>(new PackagesComparator());   
        javaPackages.add(javaPackage1);
        javaPackages.add(javaPackage2);
                    
        packagesTreeContentProvider.setJavaPackages(javaPackages);
                        
        Object[] result = packagesTreeContentProvider.getChildren(parentPackage);        
        IPackageFragment[] subPackages = {javaPackage2, javaPackage1};                
                       
        assertThat("returned sub packages", (IPackageFragment[]) result, is(subPackages));
    }     
}
