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
package org.splevo.vpm.analyzer.config;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This is a container class for the configurations. Each configuration has a
 * group name. This is for structural reasons (better UI overview).
 * 
 * @author Daniel Kojic
 * 
 */
public class VPMAnalyzerConfigurationSet {

	/**
	 * Stores all configurations with their corresponding group names.
	 */
	private Map<String, List<AbstractVPMAnalyzerConfiguration<?>>> configurationsGroups;

	/**
	 * The main constructor.
	 */
	public VPMAnalyzerConfigurationSet() {
		configurationsGroups = new LinkedHashMap<String, List<AbstractVPMAnalyzerConfiguration<?>>>();
	}

	/**
	 * Adds various configurations. Those configurations are labeled on the UI with a group name.
	 * 
	 * @param groupName The group name.
	 * @param configs The configurations.
	 */
	public void addConfigurations(String groupName,
			AbstractVPMAnalyzerConfiguration<?>... configs) {
		if (!configurationsGroups.containsKey(groupName)) {
			configurationsGroups.put(groupName,
					new LinkedList<AbstractVPMAnalyzerConfiguration<?>>());
		}

		for (AbstractVPMAnalyzerConfiguration<?> vpmAnalyzerConfiguration : configs) {
			configurationsGroups.get(groupName)
					.add(vpmAnalyzerConfiguration);
		}
	}

	/**
	 * Gets all configurations with their corresponding group names.
	 * 
	 * @return The configurations.
	 */
	public Map<String, List<AbstractVPMAnalyzerConfiguration<?>>> getAllConfigurationsByGroupName() {
		return configurationsGroups;
	}
}
