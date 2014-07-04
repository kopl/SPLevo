package org.splevo.jamopp.vpm.builder;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.emftext.language.java.resource.java.IJavaOptions;
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

    public static final String GROUP_ID_TOPLEVEL = "[TOP LEVEL GROUP]";

    /** The logger used by this class. */
    private Logger logger = Logger.getLogger(JaMoPPVPMBuilder.class);

    @Override
    public VariationPointModel buildVPM(Comparison comparisonModel, String variantIDLeading, String variantIDIntegration) {

        if (!diffModelIsValid(comparisonModel)) {
            return null;
        }

        improveResourceSetContext(comparisonModel);

        VariationPointModel vpm = variabilityFactory.eINSTANCE.createVariationPointModel();

        // visit the difference tree
        JaMoPPDiffVisitor jamoppDiffVisitor = new JaMoPPDiffVisitor(variantIDLeading, variantIDIntegration, vpm,
                comparisonModel);
        for (Diff diff : comparisonModel.getDifferences()) {
            VariationPoint vp = jamoppDiffVisitor.doSwitch(diff);
            if (vp != null) {
                VariationPointGroup group = createGroup(vp);
                vpm.getVariationPointGroups().add(group);
            } else {
                logger.warn("null VariationPoint created: " + diff);
            }
        }

        return vpm;

    }

    private VariationPointGroup createGroup(VariationPoint vp) {
        VariationPointGroup group = variabilityFactory.eINSTANCE.createVariationPointGroup();
        String groupID = buildGroupID(vp.getLocation());
        group.setGroupId(groupID);
        group.getVariationPoints().add(vp);
        return group;
    }

    /**
     * If the current comparison model is in a resource set context, tweak this ResourceSet for
     * faster resolving of java resources by not loading any layout or location information.
     *
     * Note: This disables the opportunity to link the model with the code!
     *
     * @param comparisonModel
     *            The model to check and improve the resource set of.
     */
    private void improveResourceSetContext(Comparison comparisonModel) {
        if (comparisonModel.eResource() != null && comparisonModel.eResource().getResourceSet() != null) {
            Map<Object, Object> loadOptions = comparisonModel.eResource().getResourceSet().getLoadOptions();
            loadOptions.put(IJavaOptions.DISABLE_LAYOUT_INFORMATION_RECORDING, Boolean.TRUE);
            loadOptions.put(IJavaOptions.DISABLE_LOCATION_MAP, Boolean.TRUE);
        }
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
            logger.warn("No enclosing element provided to derive group id");
            return GROUP_ID_TOPLEVEL;
        }

        String label = softwareElement.getLabel();
        return improveLabel(label);
    }

    private String improveLabel(String label) {
        if (label.endsWith("()")) {
            label = label.substring(0, label.length() - 2);
        } else if (label.lastIndexOf(".") != -1) {
            label = label.substring(0, label.lastIndexOf("."));
        }
        return label;
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
