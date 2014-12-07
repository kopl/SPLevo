/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Busch
 *******************************************************************************/
package org.splevo.jamopp.vpm.analyzer.clonedchange.test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.instanceOf;

import org.splevo.jamopp.vpm.analyzer.clonedchange.ClonedChangeAnalyzer;
import org.splevo.jamopp.vpm.analyzer.clonedchange.Config;
import org.splevo.vpm.analyzer.config.AbstractVPMAnalyzerConfiguration;
import org.splevo.vpm.analyzer.config.ChoiceConfiguration;
import org.splevo.vpm.analyzer.config.NumericConfiguration;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;

/**
 * Unit tests for the {@link ClonedChangeAnalyzer}.
 *
 */
public class ClonedChangeAnalyzerTest {
    private ClonedChangeAnalyzer analyzer;

    /**
     * Initialization before running the tests.
     */
    @Before
    public void initialize() {
        analyzer = new ClonedChangeAnalyzer();
    }

    /**
     * Check that the Analyzer has a name.
     */
    @Test
    public void testAnalyzerNameSet() {
        assertThat("Analyzer name", analyzer.getName(), is(notNullValue()));
        assertThat("Analyzer name", analyzer.getName(), is(not("")));
    }

    /**
     * Check that the Analyzer's relationship label is set.
     */
    @Test
    public void testAnalyzerRelationshipLabelSet() {
        assertThat("Analyzer relationship label", analyzer.getRelationshipLabel(), is(notNullValue()));
        assertThat("Analyzer relationship label", analyzer.getRelationshipLabel(), is(not("")));
    }

    /**
     * Check that the analyzer's configuration set contains all needed configurations and their
     * values are within reasonable limits.
     */
    @Test
    public void testConfigurations() {
        VPMAnalyzerConfigurationSet confSet = analyzer.getConfigurations();
        assertThat("Analyzer configuration", confSet, is(notNullValue()));
        checkMinElementThresholdConfig(confSet);
        checkDetectionTypeConfig(confSet);
    }

    private void checkMinElementThresholdConfig(VPMAnalyzerConfigurationSet confSet) {
        AbstractVPMAnalyzerConfiguration<?> threshConf = confSet.getConfiguration(Config.CONFIG_GROUP_GENERAL,
                Config.CONFIG_ID_INVOLVED_ELEMENT_THRESHOLD);

        assertThat("MinElementThresholdConfig", threshConf, is(notNullValue()));
        assertThat("MinElementThresholdConfig class", threshConf, is(instanceOf(NumericConfiguration.class)));

        NumericConfiguration numConf = (NumericConfiguration) threshConf;

        assertThat("ThresholdConfig label", numConf.getLabel(), is(notNullValue()));
        assertThat("ThresholdConfig label", numConf.getLabel(), is(not("")));

        assertThat("ThresholdConfig explanation", numConf.getExplanation(), is(notNullValue()));
        assertThat("ThresholdConfig explanation", numConf.getExplanation(), is(not("")));

        assertTrue("The lower boundary is non negative", numConf.getLowerBoundary() >= 0);
    }

    private void checkDetectionTypeConfig(VPMAnalyzerConfigurationSet confSet) {
        AbstractVPMAnalyzerConfiguration<?> detectionConf = confSet.getConfiguration(Config.CONFIG_GROUP_GENERAL,
                Config.CONFIG_ID_DETECTION_TYPE);

        assertThat("DetectionTypeConfig", detectionConf, is(notNullValue()));
        assertThat("DetectionTypeConfig class", detectionConf, is(instanceOf(ChoiceConfiguration.class)));

        ChoiceConfiguration choiceConf = (ChoiceConfiguration) detectionConf;

        assertThat("DetectionTypeConfig label", choiceConf.getLabel(), is(notNullValue()));
        assertThat("DetectionTypeConfig label", choiceConf.getLabel(), is(not("")));

        assertThat("DetectionTypeConfig explanation", choiceConf.getExplanation(), is(notNullValue()));
        assertThat("DetectionTypeConfig explanation", choiceConf.getExplanation(), is(not("")));

        assertThat("DetectionTypeConfig number of choices", choiceConf.getAvailableValues().size(), is(not(0)));
    }
}
