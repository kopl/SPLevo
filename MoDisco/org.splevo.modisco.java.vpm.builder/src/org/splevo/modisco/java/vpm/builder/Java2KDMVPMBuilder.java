package org.splevo.modisco.java.vpm.builder;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.vpm.builder.VPMBuilder;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityFactory;

/**
 * A builder to generate a Variation Point Model (VPM) based on a java2kdm diff-model, i.e. an
 * extended EMF Compare diff-model.
 * 
 */
public class Java2KDMVPMBuilder implements VPMBuilder {

    /** Group id identifying variation points located under the AST model root. */
    public static final String GROUP_ID_TOPLEVEL = "TOPLEVEL";

    /** The logger used by this class. */
    private Logger logger = Logger.getLogger(Java2KDMVPMBuilder.class);
    
    @Override
    public VariationPointModel buildVPM(DiffModel diffModel, String variantIDLeading, String variantIDIntegration) {

        if (!diffModelIsValid(diffModel)) {
            return null;
        }


        JavaApplication leadingModel = selectJavaAppModel(diffModel.getRightRoots());
        JavaApplication integrationModel = selectJavaAppModel(diffModel.getLeftRoots());

        VariationPointModel vpm = variabilityFactory.eINSTANCE.createVariationPointModel();

        // visit the difference tree
        Java2KDMDiffVisitor java2KDMDiffVisitor = new Java2KDMDiffVisitor(leadingModel, integrationModel, variantIDLeading, variantIDIntegration, vpm);
        for (DiffElement diffElement : diffModel.getDifferences()) {
            VariationPoint vp = java2KDMDiffVisitor.doSwitch(diffElement);
            if (vp != null) {
                VariationPointGroup group = variabilityFactory.eINSTANCE.createVariationPointGroup();

                // set the group id to the class of the software entity
                // except it is a block surrounded by a method
                String groupID = buildGroupID(vp.getEnclosingSoftwareEntity());
                group.setGroupId(groupID);
                group.getVariationPoints().add(vp);
                vpm.getVariationPointGroups().add(group);
            } else {
                logger.warn("null VariationPoint created: " + diffElement);
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
     * Get a JavaApplication model from a list of model elements.
     * 
     * @param eObjectList
     *            The list to try to find the JavaApplication element in.
     * @return The first found JavaApplication or null if none is in the list.
     */
    private JavaApplication selectJavaAppModel(EList<EObject> eObjectList) {
        for (EObject root : eObjectList) {
            if (root instanceof JavaApplication) {
                return (JavaApplication) root;
            }
        }
        return null;
    }

    /**
     * Check that the DiffModel is a valid input. This means, that the diffed models must be modisco
     * discovered java models and contain a JavaApplication model
     * 
     * @param diffModel
     *            The diff model to check
     * @return true/false depending whether the diff model is valid.
     */
    private boolean diffModelIsValid(DiffModel diffModel) {

        boolean valid = true;

        if (selectJavaAppModel(diffModel.getLeftRoots()) == null) {
            logger.warn("Diff model invalid: No valid JavaApplication integration root (left) available");
            valid = false;
        }

        if (selectJavaAppModel(diffModel.getRightRoots()) == null) {
            logger.warn("Diff model contains no valid JavaApplication leading root (right) available");
            valid = false;
        }

        return valid;
    }

    @Override
    public String getId() {
        return getClass().getSimpleName();
    }

    @Override
    public String getLabel() {
        return "MoDisco Java VPM Builder";
    }

}
