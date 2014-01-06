/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.fm.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.featuremodel.FeatureModel;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Test to verify the functionality of the builder deriving a feature model from a variation point
 * model.
 */
public class VPM2FMBuilderTest {

    /** The logger for this test class. */
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(VPM2FMBuilderTest.class);

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        // set up a basic logging configuration for the test environment
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Basic test to create a vpm and derive a feature model.
     *
     * @throws Exception
     *             Failed to read the variation point model.
     */
    @Test
    public void testBuildFeatureModel() throws Exception {

        VariationPointModel vpm = SPLevoTestUtil.loadGCDVPMModel();

        VPM2FMBuilder builder = new VPM2FMBuilder();
        FeatureModel fm = builder.buildFeatureModel(vpm, "TestFeature");

        assertNotNull("No Feature Model Created", fm);
        assertNotNull("No root feature created", fm.getRoot());
        assertEquals("Wrong number of top level features", 7, fm.getRoot().getChildren().size());
    }
}
