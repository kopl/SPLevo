/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic - initial API and implementation and/or initial documentation
 *    Benjamin Klatt
 *    Christian Busch
 *******************************************************************************/
package org.splevo.jamopp.vpm.analyzer.clonedchange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.splevo.jamopp.algorithm.clones.baxtor.CloneDetectionType;
import org.splevo.vpm.analyzer.config.ChoiceConfiguration;
import org.splevo.vpm.analyzer.config.NumericConfiguration;
import org.splevo.vpm.analyzer.config.Range;

/**
 * Container for configuration default values.
 */
public final class Config {

    private static final String CONFIG_ID_BASE = "org.splevo.jamopp.vpm.analyzer.clonedchange";

    /** Group identifier for general configurations. */
    public static final String CONFIG_GROUP_GENERAL = "General Configuations";

    // --------------------------
    // Minimum Involved Element Threshold
    // --------------------------

    /**
     * Identifier for the configuration of the minimum number of model elements involved in a clone.
     */
    public static final String CONFIG_ID_INVOLVED_ELEMENT_THRESHOLD = CONFIG_ID_BASE + "INVOLVED_ELEMENT_THRESHOLD";

    /** The configuration label for the minimum element threshold configuration. */
    public static final String LABEL_INVOLVED_ELEMENT_THRESHOLD = "Minimum element threshold";

    /** The explanation for the minimum element threshold configuration. */
    public static final String EXPL_INVOLVED_ELEMENT_THRESHOLD = "The minimum number of model elements participating in a clone.";

    /** The default configuration for the minimum element threshold configuration. */
    public static final int DEFAULT_INVOLVED_ELEMENT_THRESHOLD = 10;

    // --------------------------
    // Detection Type
    // --------------------------
    
    /** Identifier for the configuration of the clone detection type. */
    public static final String CONFIG_ID_DETECTION_TYPE = CONFIG_ID_BASE + "DETECTION_TYPE";
    
    /** The configuration label for the clone detection type configuration. */
    public static final String LABEL_DETECTION_TYPE = "Clone detection type";
    
    /** The explanation for the clone detection type configuration. */
    public static final String EXPL_DETECTION_TYPE = "The analysis type for clone detection";
    
    /** The default configuration for the clone detection type configuration. */
    public static final String DEFAULT_DETECTION_TYPE = CloneDetectionType.STRUCTURAL.toString();

    
    /** Disable constructor for static utility usage. */
    private Config() {
    }

    /**
     * Create the configuration object to chose the minimum element threshold to use in the analysis.
     *
     * @return The prepared configuration object.
     */
    public static NumericConfiguration createMinElementThresholdConfig() {
        return new NumericConfiguration(CONFIG_ID_INVOLVED_ELEMENT_THRESHOLD, LABEL_INVOLVED_ELEMENT_THRESHOLD,
                EXPL_INVOLVED_ELEMENT_THRESHOLD, DEFAULT_INVOLVED_ELEMENT_THRESHOLD, 1, new Range(1, -1), 0);
    }

    /**
     * Create the configuration object to chose a detection type to use in the analysis.
     *
     * @return The prepared configuration object.
     */
    public static ChoiceConfiguration createDetectionTypeConfig() {
        List<String> values = new ArrayList<String>();

        for (CloneDetectionType type : CloneDetectionType.values()) {
            values.add(type.toString());
        }

        Collections.sort(values);

        return new ChoiceConfiguration(CONFIG_ID_DETECTION_TYPE, LABEL_DETECTION_TYPE, EXPL_DETECTION_TYPE,
                DEFAULT_DETECTION_TYPE, values);
    }

}
