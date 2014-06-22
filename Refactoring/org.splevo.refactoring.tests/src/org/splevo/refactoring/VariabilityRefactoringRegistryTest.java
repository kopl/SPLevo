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
package org.splevo.refactoring;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;

/**
 * Test case for the {@link VariabilityRefactoringRegistry}
 */
public class VariabilityRefactoringRegistryTest {

    /**
     * Reset the registry to ensure a fresh setup.
     */
    @Before
    @After
    public void cleanUp() {
        VariabilityRefactoringRegistry.getRefactorings().clear();
    }

    /**
     * Test the basic registry interaction.
     */
    @Test
    public void test() {

        VariabilityMechanism mechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        mechanism.setName("TestMechanism");

        VariabilityRefactoring refactoring = mock(VariabilityRefactoring.class);
        when(refactoring.getId()).thenReturn("TESTID");
        when(refactoring.getVariabilityMechanism()).thenReturn(mechanism);
        VariabilityRefactoringRegistry.registerRefactoring(refactoring);

        List<VariabilityRefactoring> refactorings = VariabilityRefactoringRegistry.getRefactorings();
        assertThat("Wrong number of registered refactorings", refactorings.size(), is(1));
    }

}
