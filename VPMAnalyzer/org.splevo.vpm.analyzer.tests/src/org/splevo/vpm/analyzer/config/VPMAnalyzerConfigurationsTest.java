/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic
 *******************************************************************************/
package org.splevo.vpm.analyzer.config;

import static org.junit.Assert.assertSame;

import org.junit.Test;

/**
 * Tests for the {@link VPMAnalyzerConfigurationSet} class.
 */
public class VPMAnalyzerConfigurationsTest {

    /**
     * Tests whether the map-size is 0 after initialization.
     */
    @Test
    public final void testVPMAnalyzerConfigurations() {
        VPMAnalyzerConfigurationSet configSet = new VPMAnalyzerConfigurationSet();
        int size = configSet.getAllConfigurationsByGroupName().size();
        assertSame("Map size should be 0 after initialization.", 0, size);
    }

    /**
     * Tests whether the map has the correct amount of elements.
     */
    @Test
    public final void testAddConfigurationsAndGetAllConfigurationsByGroupName1() {
        VPMAnalyzerConfigurationSet configSet = new VPMAnalyzerConfigurationSet();
        BooleanConfiguration conf1 = new BooleanConfiguration("ID1", "LABEL", "EXPLANATION", true);
        BooleanConfiguration conf2 = new BooleanConfiguration("ID2", "LABEL", "EXPLANATION", true);
        BooleanConfiguration conf3 = new BooleanConfiguration("ID3", "LABEL", "EXPLANATION", true);
        configSet.addConfigurations("Test", conf1, conf2, conf3);

        int size = configSet.getAllConfigurationsByGroupName().size();
        assertSame("Map size should be 1.", 1, size);
        int numElements = configSet.getAllConfigurationsByGroupName().get("Test").size();
        assertSame("This key should have 3 elements.", 3, numElements);
    }

    /**
     * Tests whether the map has the correct amount of elements.
     */
    @Test
    public final void testAddConfigurationsAndGetAllConfigurationsByGroupName2() {
        VPMAnalyzerConfigurationSet configSet = new VPMAnalyzerConfigurationSet();
        BooleanConfiguration conf1 = new BooleanConfiguration("ID1", "LABEL", "EXPLANATION", true);
        BooleanConfiguration conf2 = new BooleanConfiguration("ID2", "LABEL", "EXPLANATION", true);
        BooleanConfiguration conf3 = new BooleanConfiguration("ID3", "LABEL", "EXPLANATION", true);
        configSet.addConfigurations("Test1", conf1, conf2, conf3);

        BooleanConfiguration conf4 = new BooleanConfiguration("ID4", "LABEL", "EXPLANATION", true);
        BooleanConfiguration conf5 = new BooleanConfiguration("ID5", "LABEL", "EXPLANATION", true);
        BooleanConfiguration conf6 = new BooleanConfiguration("ID6", "LABEL", "EXPLANATION", true);
        configSet.addConfigurations("Test2", conf4, conf5, conf6);

        int size = configSet.getAllConfigurationsByGroupName().size();
        assertSame("Map size should be 1.", 2, size);
        int numElements1 = configSet.getAllConfigurationsByGroupName().get("Test1").size();
        int numElements2 = configSet.getAllConfigurationsByGroupName().get("Test2").size();
        assertSame("This key should have 3 elements.", 3, numElements1);
        assertSame("This key should have 3 elements.", 3, numElements2);
    }

    /**
     * Tests whether the map has the correct amount of elements.
     */
    @Test
    public final void testConfigIDUniqueInSet() {
        VPMAnalyzerConfigurationSet configSet = new VPMAnalyzerConfigurationSet();
        BooleanConfiguration conf1 = new BooleanConfiguration("ID1", "LABEL", "EXPLANATION", true);
        BooleanConfiguration conf2 = new BooleanConfiguration("ID1", "LABEL", "EXPLANATION", true);
        configSet.addConfigurations("Test1", conf1, conf2);

        int size = configSet.getAllConfigurationsByGroupName().size();
        assertSame("Map size should be 1.", 1, size);
        int count = configSet.getAllConfigurationsByGroupName().get("Test1").size();
        assertSame("This key should have 1 elements.", 1, count);
    }

}
