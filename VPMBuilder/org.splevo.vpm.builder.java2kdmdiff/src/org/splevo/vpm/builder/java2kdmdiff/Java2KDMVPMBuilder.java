package org.splevo.vpm.builder.java2kdmdiff;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityFactory;

/**
 * A builder to generate a Variation Point Model (VPM) based on a java2kdm diff-model, i.e. an
 * extended EMF Compare diff-model.
 * 
 */
public class Java2KDMVPMBuilder {

    /** Group id identifying variation points located under the AST model root. */
    public static final String GROUP_ID_TOPLEVEL = "TOPLEVEL";

    /** The logger used by this class. */
    private Logger logger = Logger.getLogger(Java2KDMVPMBuilder.class);

    /** The id to set for leading variants. */
    private String variantIDLeading = null;

    /** The id to set for integration variants. */
    private String variantIDIntegration = null;

    /**
     * Constructor to set the variant ids.
     * 
     * @param variantIDLeading
     *            The id for the leading variants.
     * @param variantIDIntegration
     *            The id for the integration variants.
     */
    public Java2KDMVPMBuilder(String variantIDLeading, String variantIDIntegration) {

    	// Make sure the meta model is loaded.
        Java2KDMDiffPackage.eINSTANCE.eClass();
        
        this.variantIDIntegration = variantIDIntegration;
        this.variantIDLeading = variantIDLeading;
    }

    /**
     * Build a new VariationPointModel based on a DiffModel.
     * 
     * The provided diff model should be the result of a JavaModel diffing. This will be checked by
     * the builder and null will be returned if this is not valid.
     * 
     * @param diffModel
     *            The diff model to interpret.
     * @return The resulting VariationPointModel or null if the DiffModel is not a about a modisco
     *         JavaModel.
     * 
     */
    public VariationPointModel buildVPM(DiffModel diffModel) {

        if (!diffModelIsValid(diffModel)) {
            return null;
        }

        VariationPointModel vpm = initVPM(diffModel);

        // visit the difference tree
        Java2KDMDiffVisitor java2KDMDiffVisitor = new Java2KDMDiffVisitor(variantIDLeading, variantIDIntegration);
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
     * @param node
     *            The AST node containing the variability.
     * @return The derived group id.
     */
    private String buildGroupID(ASTNode node) {
        String groupID = null;

        // handle empty nodes. This might be the case if a varying element
        // is located directly on the model root such as a compilation unit or
        // a top level package
        if (node == null) {
            return GROUP_ID_TOPLEVEL;
        }

        // get the containing elements name in case of a block
        if (node instanceof Block) {
            EObject parent = node.eContainer();
            if (parent instanceof MethodDeclaration) {
                groupID = ((MethodDeclaration) parent).getName() + "()";
            } else if (node instanceof NamedElement) {
                groupID = ((NamedElement) node).getName();
            }

        }

        // use the name of named elements
        if (node instanceof NamedElement) {
            groupID = ((NamedElement) node).getName();
        }

        // use the meta class name as fall back
        if (groupID == null) {
            groupID = node.getClass().getSimpleName();
        }
        return groupID;
    }

    /**
     * Init the instance of the variation point model.
     * 
     * @param diffModel
     *            The diff model to get the diffed models from.
     * @return The initialized variation point model.
     */
    private VariationPointModel initVPM(DiffModel diffModel) {

        JavaApplication leadingModel = selectJavaAppModel(diffModel.getRightRoots());
        JavaApplication integrationModel = selectJavaAppModel(diffModel.getLeftRoots());

        VariationPointModel vpm = variabilityFactory.eINSTANCE.createVariationPointModel();
        vpm.setLeadingModel(leadingModel);
        vpm.setIntegrationModel(integrationModel);

        return vpm;
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

}
