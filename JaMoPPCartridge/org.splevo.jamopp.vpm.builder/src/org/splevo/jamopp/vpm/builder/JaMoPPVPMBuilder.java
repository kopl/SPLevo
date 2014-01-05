package org.splevo.jamopp.vpm.builder;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiff;
import org.splevo.vpm.builder.VPMBuilder;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityFactory;

import com.google.common.collect.Lists;

/**
 * A builder to generate a Variation Point Model (VPM) based on a JaMoPP diff-model, i.e. an
 * extended EMF Compare diff-model.
 *
 */
public class JaMoPPVPMBuilder implements VPMBuilder {

    /** Group id identifying variation points located under the AST model root. */
    public static final String GROUP_ID_TOPLEVEL = "TOPLEVEL";

    /** The logger used by this class. */
    private Logger logger = Logger.getLogger(JaMoPPVPMBuilder.class);

    @Override
    public VariationPointModel buildVPM(Comparison comparisonModel, String variantIDLeading, String variantIDIntegration) {

        if (!diffModelIsValid(comparisonModel)) {
            return null;
        }

        VariationPointModel vpm = variabilityFactory.eINSTANCE.createVariationPointModel();

        // visit the difference tree
        JaMoPPDiffVisitor jamoppDiffVisitor = new JaMoPPDiffVisitor(variantIDLeading, variantIDIntegration, vpm,
                comparisonModel);
        for (Diff diff : comparisonModel.getDifferences()) {
            VariationPoint vp = jamoppDiffVisitor.doSwitch(diff);
            if (vp != null) {
                VariationPointGroup group = variabilityFactory.eINSTANCE.createVariationPointGroup();

                // set the group id to the class of the software entity
                // except it is a block surrounded by a method
                String groupID = buildGroupID(vp.getEnclosingSoftwareEntity());
                group.setGroupId(groupID);
                group.getVariationPoints().add(vp);
                vpm.getVariationPointGroups().add(group);
            } else {
                logger.warn("null VariationPoint created: " + diff);
            }
        }

        return vpm;

    }

    /**
     * Get the id for variation point group based on the ASTNode specifying the variability
     * location.
     *
     * @param softwareElement
     *            The AST node containing the variability.
     * @return The derived group id.
     */
    private String buildGroupID(SoftwareElement softwareElement) {

        // handle empty nodes. This might be the case if a varying element
        // is located directly on the model root such as a compilation unit or
        // a top level package
        if (softwareElement == null) {
            return GROUP_ID_TOPLEVEL;
        }

        return softwareElement.getLabel();
    }

    /**
     * Check if at least one JaMoPP specific Diff element exisits in the comparision model.
     *
     * @param compareModel
     *            The diff model to check
     * @return true/false depending whether the diff model is valid.
     */
    private boolean diffModelIsValid(Comparison compareModel) {

        List<Diff> differences = Lists.newArrayList(compareModel.getDifferences());
        for (Diff diff : differences) {
            if (diff instanceof JaMoPPDiff) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getId() {
        return getClass().getSimpleName();
    }

    @Override
    public String getLabel() {
        return "JaMoPP VPM Builder";
    }

}
