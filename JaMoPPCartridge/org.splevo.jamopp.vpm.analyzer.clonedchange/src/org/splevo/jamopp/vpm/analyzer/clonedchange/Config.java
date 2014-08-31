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
 *******************************************************************************/
package org.splevo.jamopp.vpm.analyzer.clonedchange;

/**
 * Container for configuration default values.
 */
public final class Config {

    private static final String CONFIG_ID_BASE = "org.splevo.jamopp.vpm.analyzer.clonedchange";

    /** Group identifier for general configurations. */
    public static final String CONFIG_GROUP_GENERAL = "General Configuations";

    // --------------------------
    // Shared Term Minimum
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

}
