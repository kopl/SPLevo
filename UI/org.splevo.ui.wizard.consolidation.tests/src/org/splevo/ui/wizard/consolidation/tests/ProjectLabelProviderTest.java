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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.core.resources.IProject;
import org.junit.Test;
import org.splevo.ui.wizard.consolidation.provider.ProjectLabelProvider;


/**
 * Unit test for the functions of the {@link ProjectLabelProvider}.
 */
public class ProjectLabelProviderTest {
    
    /**
     * Test if the method getText() returns a correct label.
     */
    @Test
    public void testGetText() {
        ProjectLabelProvider projectLabelProvider = new ProjectLabelProvider();
        IProject project = mock(IProject.class);
        
        when(project.getName()).thenReturn("projectLabel");
        
        assertThat("returned project label", projectLabelProvider.getText(project), is("projectLabel"));       
    }
}
