package org.splevo.vpm.refinement;

import java.util.List;

import org.splevo.vpm.refinement.Refinement;
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
	 * Get the name of the analysis to be displayed.
	 * @return The name of the analysis to be presented to the user.
	 */
	public String getName();

}