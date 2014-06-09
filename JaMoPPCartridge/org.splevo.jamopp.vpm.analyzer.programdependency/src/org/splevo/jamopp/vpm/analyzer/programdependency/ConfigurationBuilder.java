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

import java.util.Collections;
import java.util.List;

import org.splevo.jamopp.vpm.analyzer.programdependency.references.DependencyType;
import org.splevo.jamopp.vpm.analyzer.programdependency.references.ReferenceSelectorRegistry;
import org.splevo.vpm.analyzer.config.BooleanConfiguration;
import org.splevo.vpm.analyzer.config.ChoiceConfiguration;

import com.google.common.collect.Lists;

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

    public static final String CONFIG_ID_REFERENCE_SELECTOR = CONFIG_ID_BASE + "REFERENCE_SELECTOR";
    public static final String LABEL_REFERENCE_SELECTOR = "Reference Selector";
    public static final String EXPL_REFERENCE_SELECTOR = "Choose the selector to decide about which references between software elments should be considered.";
    public static final String DEFAULT_REFERENCE_SELECTOR = ReferenceSelectorRegistry.ROBILLARD_EXTENDED_SELECTOR;

    public static final String CONFIG_ID_DESIRED_DEPENDENCY_TYPE = CONFIG_ID_BASE + "DESIRED_DEPENDENCY_TYPE";
    public static final String LABEL_DESIRED_DEPENDENCY_TYPE = "Desired Dependency Type";
    public static final String EXPL_DESIRED_DEPENDENCY_TYPE = "Choose the desired dependency type to limit the analysis to.";
    public static final String DEFAULT_DESIRED_DEPENDENCY_TYPE = "ALL";

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

    /**
     * Create the configuration object to chose the reference selector to use.
     *
     * @return The prepared configuration object.
     */
    public static ChoiceConfiguration createReferenceSelectorConfig() {
        return new ChoiceConfiguration(CONFIG_ID_REFERENCE_SELECTOR, LABEL_REFERENCE_SELECTOR, EXPL_REFERENCE_SELECTOR,
                DEFAULT_REFERENCE_SELECTOR, ReferenceSelectorRegistry.getAvailableSelectorIds());
    }

    /**
     * Create the configuration object to chose a desired dependency type to focus the analysis on.
     *
     * @return The prepared configuration object.
     */
    public static ChoiceConfiguration createDesiredDependencyTypeConfig() {
        List<String> values = Lists.newArrayList(DEFAULT_DESIRED_DEPENDENCY_TYPE);

        for (DependencyType type : DependencyType.values()) {
            switch (type) {
            case SHARED:
            case IGNORE:
                break;

            default:
                values.add(type.toString());
            }

        }

        Collections.sort(values);

        return new ChoiceConfiguration(CONFIG_ID_DESIRED_DEPENDENCY_TYPE, LABEL_DESIRED_DEPENDENCY_TYPE,
                EXPL_DESIRED_DEPENDENCY_TYPE, DEFAULT_DESIRED_DEPENDENCY_TYPE, values);
    }

}
