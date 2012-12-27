package org.splevo.ui.workflow;

import java.util.ArrayList;
import java.util.List;

import org.splevo.vpm.analyzer.VPMAnalyzer;

/**
 * A configuration for a VPM analysis work flow to be executed.
 * A work flow contains a set of VPM analyzes recommending refinements to be performed. 
 */
public class VPMAnalysisWorkflowConfiguration extends
		BasicSPLevoWorkflowConfiguration {

	/** A list of analysis configurations to be executed. */ 
	private final List<VPMAnalyzer> analyzers = new ArrayList<VPMAnalyzer>();

	/**
	 * Get the list of analyzer instances to be executed.
	 * @return The analyzer instances to execute.
	 */
	public List<VPMAnalyzer> getAnalyzers() {
		return analyzers;
	}	
}
