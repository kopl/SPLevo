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
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;

import org.junit.Test;
import org.splevo.ui.wizard.consolidation.NewConsolidationProjectWizard;

/**
 * Unit test for basic functions of the main wizard.
 */
public class NewConsolidationProjectWizardTest {

    /**
     * Test the wizards constants as an example unit test.
     *
     * <strong>Input</strong><br>
     * None
     *
     * <strong>Expected Result</strong><br>
     * Wizard constants contain valid values.
     */
    @Test
    public void testConstructor() {
        assertThat("Wizard name field should not be null", NewConsolidationProjectWizard.WIZARD_NAME, is(notNullValue()));
        assertThat("Wizard name field should not be empty", NewConsolidationProjectWizard.WIZARD_NAME, not(eq("")));
    }

}
