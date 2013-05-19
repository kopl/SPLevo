package org.splevo.ui.workflow;

import java.util.ArrayList;
import java.util.List;

import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.refinement.DetectionRule;

/**
 * A configuration for a VPM analysis work flow to be executed.
 * A work flow contains a set of VPM analyzes recommending refinements to be performed. 
 */
public class VPMAnalysisWorkflowConfiguration extends
		BasicSPLevoWorkflowConfiguration {

    /** A list of analysis configurations to be executed. */ 
	private final List<VPMAnalyzer> analyzers = new ArrayList<VPMAnalyzer>();
	
	/** The presentation mode for the analysis. */
	private ResultPresentation presentation = ResultPresentation.REFINEMENT_BROWSER;

    /** The detection rules to be applied. */
    private List<DetectionRule> detectionRules = new ArrayList<DetectionRule>();

	/**
	 * Get the list of analyzer instances to be executed.
	 * @return The analyzer instances to execute.
	 */
	public List<VPMAnalyzer> getAnalyzers() {
		return analyzers;
	}	

    /**
     * Get the presentation mode for the analysis.
     * @return the presentation
     */
    public ResultPresentation getPresentation() {
        return presentation;
    }

    /**
     * Get the presentation mode for the analysis.
     * @param presentation The presentation mode to set.
     */
    public void setPresentation(ResultPresentation presentation) {
        this.presentation = presentation;
    }

    /**
     * Get the detection rules that should be applied to identify refinements.
     * @return the detectionRules to be applied
     */
    public List<DetectionRule> getDetectionRules() {
        return detectionRules;
    }

    /**
     * Set the detection rules that should be applied to identify refinements.
     * @param detectionRules the detectionRules to set
     */
    public void setDetectionRules(List<DetectionRule> detectionRules) {
        this.detectionRules = detectionRules;
    }

    /**
     * Enumeration specifying the options to present the 
     * analysis results.
     * 
     * @author Benjamin Klatt
     *
     */
    public enum ResultPresentation {
        
        /** Option to open the refinement browser. */
        REFINEMENT_BROWSER,
        
        /** Option to open only the relationship graph. */
        RELATIONSHIP_GRAPH_ONLY
    }
}
