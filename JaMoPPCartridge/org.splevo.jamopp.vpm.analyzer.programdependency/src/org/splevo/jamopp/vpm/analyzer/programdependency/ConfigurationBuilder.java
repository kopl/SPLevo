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
package org.splevo.jamopp.vpm.analyzer.programdependency;

import org.splevo.vpm.analyzer.config.BooleanConfiguration;

/**
 * Utility class for creating the analyzers configuration.
 */
public final class ConfigurationBuilder {

    private static final String CONFIG_ID_BASE = "org.splevo.jamopp.vpm.analyzer.programdependency";
    public static final String CONFIG_GROUP_DEPENDENCIES = "Program Dependencies";

    public static final String CONFIG_ID_FILTER_EXTERNAL_DEPENDENCIES = CONFIG_ID_BASE + "FILTER_EXTERNAL_DEPENDENCIES";
    public static final String LABEL_FILTER_EXTERNAL_DEPENDENCIES = "Filter external dependencies";
    public static final String EXPL_FILTER_EXTERNAL_DEPENDENCIES = "Filter shared dependencies to software elements (e.g. classes or methods) located in external libraries (jar files).";
    public static final boolean DEFAULT_FILTER_EXTERNAL_DEPENDENCIES = true;

    /** Disable constructor for static utility usage. */
    private ConfigurationBuilder() {
    }

    /**
     * Create the configuration object to filter dependencies on external elements or not.
     *
     * @return The prepared configuration object.
     */
    public static BooleanConfiguration createFilterExternalsConfig() {
        return new BooleanConfiguration(CONFIG_ID_FILTER_EXTERNAL_DEPENDENCIES, LABEL_FILTER_EXTERNAL_DEPENDENCIES,
                EXPL_FILTER_EXTERNAL_DEPENDENCIES, DEFAULT_FILTER_EXTERNAL_DEPENDENCIES);
    }

}
