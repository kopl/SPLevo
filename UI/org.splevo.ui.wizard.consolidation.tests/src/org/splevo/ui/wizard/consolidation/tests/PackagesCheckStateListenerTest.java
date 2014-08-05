/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt, Radoslav Yankov
 *******************************************************************************/
package org.splevo.ui.wizard.consolidation.tests;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.junit.Test;
import org.mockito.InOrder;
import org.splevo.ui.wizard.consolidation.listener.PackagesCheckStateListener;
import org.splevo.ui.wizard.consolidation.util.PackagesComparator;

/**
 * Unit test for the functions of the {@link PackagesCheckStateListener}.
 */
public class PackagesCheckStateListenerTest {
    
    /**
     * Test the check state of the subtree when a parent package has been chosen. 
     */    
    @Test
    public void testSubtreeCheckState() {
        PackagesCheckStateListener packagesCheckStateListener = new PackagesCheckStateListener();                
        
        IPackageFragment parentPackage = mock(IPackageFragment.class);
        when(parentPackage.getElementName()).thenReturn("org.splevo");

        IPackageFragment subPackage1 = mock(IPackageFragment.class);
        when(subPackage1.getElementName()).thenReturn("org.splevo.wizard");

        IPackageFragment subPackage2 = mock(IPackageFragment.class);
        when(subPackage2.getElementName()).thenReturn("org.splevo.test");
        
        @SuppressWarnings("unchecked")
        SortedSet<IPackageFragment> javaPackages = mock(SortedSet.class);
        @SuppressWarnings("unchecked")
        Iterator<IPackageFragment> iterator = mock(Iterator.class);

        when(javaPackages.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, true, true, false);
        when(iterator.next()).thenReturn(parentPackage, subPackage1, subPackage2);

        packagesCheckStateListener.setJavaPackages(javaPackages);
        
        CheckStateChangedEvent checkStateChangedEvent = mock(CheckStateChangedEvent.class);
        CheckboxTreeViewer packagesTreeViewer = mock(CheckboxTreeViewer.class);                
        
        when(checkStateChangedEvent.getSource()).thenReturn(packagesTreeViewer);        
        when(checkStateChangedEvent.getElement()).thenReturn(parentPackage);
        when(checkStateChangedEvent.getChecked()).thenReturn(true, false);                 
        
        packagesCheckStateListener.checkStateChanged(checkStateChangedEvent);                
        verify(packagesTreeViewer).setSubtreeChecked(parentPackage, true);
        
        packagesCheckStateListener.checkStateChanged(checkStateChangedEvent);
        verify(packagesTreeViewer).setSubtreeChecked(parentPackage, false);        
    }
    
    /**
     * Test the check state of a parent package.
     */    
    @Test
    public void testParentsCheckState() {
        PackagesCheckStateListener packagesCheckStateListener = new PackagesCheckStateListener();                
        
        IPackageFragment parentPackage = mock(IPackageFragment.class);
        when(parentPackage.getElementName()).thenReturn("org.splevo");

        IPackageFragment subPackage1 = mock(IPackageFragment.class);
        when(subPackage1.getElementName()).thenReturn("org.splevo.wizard");

        IPackageFragment subPackage2 = mock(IPackageFragment.class);
        when(subPackage2.getElementName()).thenReturn("org.splevo.test");
        
        PackagesComparator comparator = mock(PackagesComparator.class);
        when(comparator.compare(subPackage2, subPackage1)).thenReturn(2);
        when(comparator.compare(subPackage2, parentPackage)).thenReturn(2);
        when(comparator.compare(subPackage1, parentPackage)).thenReturn(2);
        
        SortedSet<IPackageFragment> javaPackages = new TreeSet<IPackageFragment>(comparator);
        javaPackages.add(parentPackage);
        javaPackages.add(subPackage1);
        javaPackages.add(subPackage2);
        
        packagesCheckStateListener.setJavaPackages(javaPackages);        
        
        CheckStateChangedEvent checkStateChangedEvent = mock(CheckStateChangedEvent.class);
        CheckboxTreeViewer packagesTreeViewer = mock(CheckboxTreeViewer.class);                
        
        when(checkStateChangedEvent.getSource()).thenReturn(packagesTreeViewer);        
        when(checkStateChangedEvent.getElement()).thenReturn(subPackage1);
        
        when(packagesTreeViewer.getChecked(subPackage1)).thenReturn(true, true, false);
        when(packagesTreeViewer.getChecked(subPackage2)).thenReturn(true, false, false);
        
        InOrder order = inOrder(packagesTreeViewer);
        
        packagesCheckStateListener.checkStateChanged(checkStateChangedEvent);                
        order.verify(packagesTreeViewer).setGrayed(parentPackage, false);
        order.verify(packagesTreeViewer).setChecked(parentPackage, true);                       
        
        packagesCheckStateListener.checkStateChanged(checkStateChangedEvent);                        
        order.verify(packagesTreeViewer).setChecked(parentPackage, true);
        order.verify(packagesTreeViewer).setGrayed(parentPackage, true);
        
        packagesCheckStateListener.checkStateChanged(checkStateChangedEvent);                        
        order.verify(packagesTreeViewer).setChecked(parentPackage, false);
        order.verify(packagesTreeViewer).setGrayed(parentPackage, false);
    }      
}
