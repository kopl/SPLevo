package org.splevo.vpm.refinement;

import java.util.List;
import java.util.Map;

import org.splevo.vpm.variability.VariationPointModel;

public interface VPMRefinementAnalyzer {

	/**
	 * Analyze a variation point model and return a list of 
	 * recommended refinements.
	 * 
	 * @param vpm The variation point model to analyze.
	 * @return The list of recommended refinements.
	 */
	public List<Refinement> analyze(VariationPointModel vpm);
	
	/**
	 * Get a map of the possible settings for the analyzer.
	 * @return A map with IDs and the according data type for each available setting.
	 */
	public Map<String,AnalyzerConfigurationType> getAvailableConfigurations();
	
	/**
	 * Get the labels to display for the available configurations.
	 * @return A map with configuration ids and the according labels.
	 */
	public Map<String,String> getConfigurationLabels();
	
	/**
	 * Get the configuration map of the analyzer instance.
	 * @return The map of settings with the id of the setting and it's value.
	 */
	public Map<String,Object> getConfigurations();
	
	/**
	 * Get the name of the analysis to be displayed.
	 * @return The name of the analysis to be presented to the user.
	 */
	public String getName();

}