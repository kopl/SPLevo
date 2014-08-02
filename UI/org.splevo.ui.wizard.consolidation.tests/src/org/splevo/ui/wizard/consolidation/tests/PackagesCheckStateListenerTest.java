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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.SortedSet;

import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.junit.Test;
import org.splevo.ui.wizard.consolidation.listener.PackagesCheckStateListener;

/**
 * Unit test for the functions of the {@link PackagesCheckStateListener}.
 * 
 * @author Radoslav Yankov
 */
public class PackagesCheckStateListenerTest {
    
    /**
     * Test the behavior of the listener. 
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testCheckStateChanged() {
        PackagesCheckStateListener packagesCheckStateListener = new PackagesCheckStateListener();                
        
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

        packagesCheckStateListener.setJavaPackages(javaPackages);
        
        CheckStateChangedEvent checkStateChangedEvent = mock(CheckStateChangedEvent.class);
        CheckboxTreeViewer packagesTreeViewer = mock(CheckboxTreeViewer.class);
        
        when(checkStateChangedEvent.getSource()).thenReturn(packagesTreeViewer);        
        when(checkStateChangedEvent.getElement()).thenReturn(javaPackage1);
        when(checkStateChangedEvent.getChecked()).thenReturn(true);    
                
        verify(packagesTreeViewer).setSubtreeChecked(javaPackage1, true);        
    }
}
