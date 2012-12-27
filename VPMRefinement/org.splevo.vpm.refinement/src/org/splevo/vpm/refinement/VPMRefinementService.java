package org.splevo.vpm.refinement;

import java.util.List;

import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Class providing refinement services for a variation point model.
 */
public class VPMRefinementService {

    /**
     * Apply a list of refinements to a variation point model.
     * 
     * TODO Implement refinement application for merge type
     * 
     * @param refinements
     *            The refinements to be applied.
     * @param vpm
     *            The variation model to apply the refinements to.
     * @return The refined variation point model.
     */
    public VariationPointModel applyRefinements(List<Refinement> refinements, VariationPointModel vpm) {

        for (Refinement refinement : refinements) {

            if (refinement.getType().equals(RefinementType.GROUPING)) {

                // The group of the first variation point is kept as surviving
                // group
                // The variation points of all others are moved to this group
                // and the other groups are removed.
                VariationPointGroup survivingGroup = refinement.getVariationPoints().get(0).getGroup();
                for (VariationPoint vp : refinement.getVariationPoints()) {
                    if (!vp.getGroup().equals(survivingGroup)) {
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
