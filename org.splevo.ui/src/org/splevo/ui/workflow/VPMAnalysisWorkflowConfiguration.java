package org.splevo.ui.workflow;

import java.util.ArrayList;
import java.util.List;

import org.splevo.vpm.refinement.VPMRefinementAnalyzer;

/**
 * A configuration for a VPM analysis work flow to be executed.
 * A workflow contains a set of VPM analyses recommending refinements to be performed. 
 */
public class VPMAnalysisWorkflowConfiguration extends
		BasicSPLevoWorkflowConfiguration {

	/** A list of analysis configurations to be executed. */ 
	private final List<VPMRefinementAnalyzer> analyzers = new ArrayList<VPMRefinementAnalyzer>();

	/**
	 * Get the list of analyzer instances to be executed.
	 * @return The analyzer instances to execute.
	 */
	public List<VPMRefinementAnalyzer> getAnalyzers() {
		return analyzers;
	}	
}
