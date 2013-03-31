package org.splevo.vpm.refinement;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Class providing refinement services for a variation point model.
 */
public class VPMRefinementService {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(VPMRefinementService.class);

    /**
     * Apply a list of refinements to a variation point model.
     * 
     * @param refinements
     *            The refinements to be applied.
     * @param vpm
     *            The variation model to apply the refinements to.
     * @return The refined variation point model.
     */
    public VariationPointModel applyRefinements(List<Refinement> refinements, VariationPointModel vpm) {

        for (Refinement refinement : refinements) {

            switch (refinement.getType()) {

            case GROUPING:
                applyGrouping(vpm, refinement);
                break;

            case MERGE:
                applyMerging(vpm, refinement);
                break;

            default:
                logger.warn("Unsupported refinement type: " + refinement.getType());
                break;
            }
        }

        return vpm;
    }

    /**
     * VariationPoints are merged by consolidating their Variant elements and the referenced
     * software entities in only one of the VariationPoint elements. First, one VariationPoint
     * element that should survive is selected. Then, for all Variant elements in the
     * VariationPoints, contributing to the same child feature, the ASTNode references are merged
     * into one of the Variant elements. It is ensured, that this Variant element is contained in
     * the surviving VariationPoint. Finally,
     * 
     * the remaining empty Variant and VariationPoint elements are removed from the model.
     * 
     * @param vpm
     *            The variation point model to refine.
     * @param refinement
     *            The refinement to apply.
     */
    private void applyMerging(VariationPointModel vpm, Refinement refinement) {

        VariationPoint survivingVP = refinement.getVariationPoints().get(0);

        HashMap<String, Variant> variantMap = new HashMap<String, Variant>();
        for (VariationPoint vp : refinement.getVariationPoints()) {
            for (Variant variant : vp.getVariants()) {
                if (variantMap.containsKey(variant.getVariantId())) {
                    EList<ASTNode> swEntities = variant.getSoftwareEntities();
                    variantMap.get(variant.getVariantId()).getSoftwareEntities().addAll(swEntities);
                } else {
                    variantMap.put(variant.getVariantId(), variant);
                }
            }

            if (!vp.equals(survivingVP)) {
                VariationPointGroup oldGroup = vp.getGroup();
                oldGroup.getVariationPoints().remove(vp);
            }
        }

        // clean up empty variation point groups
        LinkedList<VariationPointGroup> vpGroupsToDelete = new LinkedList<VariationPointGroup>();
        for (VariationPointGroup oldGroup : vpm.getVariationPointGroups()) {
            if (oldGroup.getVariationPoints().size() == 0) {
                vpGroupsToDelete.add(oldGroup);
            }
        }
        vpm.getVariationPointGroups().removeAll(vpGroupsToDelete);

        // ensure only the merged variants are present in the surviving variation point.
        survivingVP.getVariants().clear();
        survivingVP.getVariants().addAll(variantMap.values());

    }

    /**
     * Refine the variation point model by applying a refinement.
     * 
     * @param vpm
     *            The variation point model to refine.
     * @param refinement
     *            The refinement to apply.
     */
    private void applyGrouping(VariationPointModel vpm, Refinement refinement) {
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
