package org.splevo.vpm.refinement;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.splevo.vpm.software.SoftwareElement;
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

            applyRefinements(refinement.getSubRefinements(), vpm);

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
                if (variantMap.containsKey(variant.getId())) {
                    EList<SoftwareElement> swElements = variant.getImplementingElements();
                    variantMap.get(variant.getId()).getImplementingElements().addAll(swElements);
                } else {
                    variantMap.put(variant.getId(), variant);
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
     * The group of the first variation point is kept as surviving group.<br>
     * The variation points of all others are moved to this group and the other groups are removed.
     *
     * Sub refinements are also triggered.
     *
     * @param vpm
     *            The variation point model to refine.
     * @param refinement
     *            The refinement to apply.
     */
    private void applyGrouping(VariationPointModel vpm, Refinement refinement) {

        EList<VariationPoint> variationPoints = refinement.getVariationPoints();
        for (Refinement subRef : refinement.getSubRefinements()) {
            if (subRef.getVariationPoints().size() > 0) {
                VariationPoint subRefRepresentingVP = subRef.getVariationPoints().get(0);
                variationPoints.add(subRefRepresentingVP);
            }
        }

        if (variationPoints.size() == 0) {
            throw new RuntimeException("Tried to apply completely empty grouping.");
        }

        combineGroups(vpm, variationPoints);
    }

    /**
     * Combine the groups of the variation points and clean up the remaining empty groups.
     *
     * @param vpm The variation point model to manipulate.
     * @param variationPoints The variation points to combine the groups of.
     */
    private void combineGroups(VariationPointModel vpm, EList<VariationPoint> variationPoints) {
        VariationPointGroup survivingGroup = variationPoints.get(0).getGroup();
        for (VariationPoint vp : variationPoints) {
            if (!vp.getGroup().equals(survivingGroup)) {
                VariationPointGroup oldGroup = vp.getGroup();
                vp.setGroup(survivingGroup);
                vpm.getVariationPointGroups().remove(oldGroup);
            }
        }
    }
}
