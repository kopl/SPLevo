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

import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.junit.Test;
import org.splevo.ui.wizard.consolidation.provider.PackageLabelProvider;

/**
 * Unit test for the functions of the {@link PackageLabelProvider}.
 * 
 * @author Radoslav Yankov
 */
public class PackageLabelProviderTest {

    /**
     * Test if the method getText() returns a correct label.
     */
    @Test
    public void testGetText() {
        ColumnLabelProvider labelProvider = new PackageLabelProvider();

        IPackageFragment javaPackage = mock(IPackageFragment.class);
        when(javaPackage.getElementName()).thenReturn("correctName");

        assertThat("returned package name", labelProvider.getText(javaPackage), is("correctName"));
    }    
}
