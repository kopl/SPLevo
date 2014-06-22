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
 *    Benjamin Klatt - enhancement and maintencance
 *******************************************************************************/
package org.splevo.vpm.analyzer.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a container class for VPM analyzer configurations.
 *
 * Configurations are grouped and unique according to their configuration id within such a group.
 * This is for structural reasons (better UI overview).
 */
public class VPMAnalyzerConfigurationSet {

    /**
     * Stores all configurations with their corresponding group names.
     */
    // TODO: Refactor to google guava table
    private Map<String, Map<String, AbstractVPMAnalyzerConfiguration<?>>> configurationsGroups;

    /**
     * The main constructor.
     */
    public VPMAnalyzerConfigurationSet() {
        configurationsGroups = new LinkedHashMap<String, Map<String, AbstractVPMAnalyzerConfiguration<?>>>();
    }

    /**
     * Adds various configurations. Those configurations are labeled on the UI with a group name.
     *
     * @param groupName
     *            The group name.
     * @param configs
     *            The configurations.
     */
    public void addConfigurations(String groupName, AbstractVPMAnalyzerConfiguration<?>... configs) {
        if (!configurationsGroups.containsKey(groupName)) {
            configurationsGroups.put(groupName, new LinkedHashMap<String, AbstractVPMAnalyzerConfiguration<?>>());
        }

        for (AbstractVPMAnalyzerConfiguration<?> config : configs) {
            configurationsGroups.get(groupName).put(config.getId(), config);
        }
    }

    /**
     * Gets all configurations with their corresponding group names.
     *
     * @return The configurations.
     */
    public Map<String, List<AbstractVPMAnalyzerConfiguration<?>>> getAllConfigurationsByGroupName() {
        Map<String, List<AbstractVPMAnalyzerConfiguration<?>>> resultMap = new LinkedHashMap<String, List<AbstractVPMAnalyzerConfiguration<?>>>();
        for (String groupId : configurationsGroups.keySet()) {
            Collection<AbstractVPMAnalyzerConfiguration<?>> configs = configurationsGroups.get(groupId).values();
            resultMap.put(groupId, new ArrayList<AbstractVPMAnalyzerConfiguration<?>>(configs));
        }
        return resultMap;
    }

    /**
     * Get the configuration for a specific group and config id. If one or both of them are not set,
     * nothing will be returned.
     *
     * @param groupId
     *            The group to search in.
     * @param configId
     *            The config to search for.
     * @return The configuration object or null if none found.
     */
    public AbstractVPMAnalyzerConfiguration<?> getConfiguration(String groupId, String configId) {
        if (configurationsGroups.containsKey(groupId)) {
            return configurationsGroups.get(groupId).get(configId);
        } else {
            return null;
        }
    }
}
