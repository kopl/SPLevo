package org.splevo.vpm.refinement;

import java.util.ArrayList;
import java.util.List;

import org.splevo.vpm.refinement.analyzer.VPLocationAnalyzer;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Class providing refinement services for a variation point model.
 */
public class VPMRefinementService {
	
	/**
	 * Identify the refinements recommended for the variation point model.
	 * 
	 * @param vpm The model to be analyzed.
	 * @return The list of recommended refinements.
	 */
	public List<Refinement> identifyRefinements(VariationPointModel vpm){
		List<Refinement> refinements = new ArrayList<Refinement>();
		
		VPLocationAnalyzer analyzer = new VPLocationAnalyzer();
		List<Refinement> locationBasedRefinements = analyzer.analyze(vpm);
		refinements.addAll(locationBasedRefinements);
		
		return refinements;
	}
	
	/**
	 * Apply a list of refinements to a variation point model.
	 * 
	 * @param refinements The refinements to be applied.
	 * @param vpm The variation model to apply the refinements to.
	 */
	public VariationPointModel applyRefinements(List<Refinement> refinements, VariationPointModel vpm){
	
		for (Refinement refinement : refinements) {
			
			if(refinement instanceof Grouping){
				Grouping grouping = (Grouping) refinement;

				// The group of the first variation point is kept as surviving group  
				// The variation points of all others are moved to this group
				// and the other groups are removed.
				VariationPointGroup survivingGroup = grouping.getVariationPoints().get(0).getGroup();
				for (VariationPoint vp : grouping.getVariationPoints()) {
					if(!vp.getGroup().equals(survivingGroup)){
						VariationPointGroup oldGroup = vp.getGroup();
						vp.setGroup(survivingGroup);
						vpm.getVariationPointGroups().remove(oldGroup);
					}
				}
			}
		}
		
		return vpm;
	}
}
