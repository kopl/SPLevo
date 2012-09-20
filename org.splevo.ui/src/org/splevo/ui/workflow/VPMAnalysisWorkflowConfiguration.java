package org.splevo.ui.workflow;

import java.util.ArrayList;
import java.util.List;

/**
 * A configuration for a VPM analysis work flow to be executed.
 * A workflow contains a set of VPM analyses recommending refinements to be performed. 
 */
public class VPMAnalysisWorkflowConfiguration extends
		BasicSPLevoWorkflowConfiguration {

	/** A list of analysis configurations to be executed. */ 
	private final List<VPMAnalysisConfiguration> analysesConfiguration = new ArrayList<VPMAnalysisConfiguration>();

	/**
	 * Get the list of analysis configurations to be executed.
	 * @return the analyses
	 */
	public List<VPMAnalysisConfiguration> getAnalyses() {
		return analysesConfiguration;
	}	
}
