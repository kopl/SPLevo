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
