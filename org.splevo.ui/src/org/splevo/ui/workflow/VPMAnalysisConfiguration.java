package org.splevo.ui.workflow;

import java.util.HashMap;
import java.util.Map;

import org.splevo.vpm.refinement.VPMRefinementAnalyzer;

/**
 * Configuration object for a single vpm analysis.
 */
public class VPMAnalysisConfiguration {
	
	/** The vpm analyzer to execute. */
	private VPMRefinementAnalyzer analyzer = null;
	
	/** The map of parameters to configure the analysis. */
	private Map<String,String> optionMap = new HashMap<String,String>();

	/**
	 * @return the optionMap
	 */
	public Map<String, String> getOptionMap() {
		return optionMap;
	}

	/**
	 * @param optionMap the optionMap to set
	 */
	public void setOptionMap(Map<String, String> optionMap) {
		this.optionMap = optionMap;
	}

	/**
	 * @return the analyzer
	 */
	public VPMRefinementAnalyzer getAnalyzer() {
		return analyzer;
	}

	/**
	 * @param analyzer the analyzer to set
	 */
	public void setAnalyzer(VPMRefinementAnalyzer analyzer) {
		this.analyzer = analyzer;
	}

}
