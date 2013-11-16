package org.splevo.modisco.java.vpm.builder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.MatchResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.eclipse.modisco.java.composition.javaapplication.JavaapplicationPackage;
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
    public VariationPointModel buildVPM(Comparison comparisonModel, String variantIDLeading, String variantIDIntegration) {

        if (!diffModelIsValid(comparisonModel)) {
            return null;
        }

        JavaApplication leadingModel = selectJavaAppModel(comparisonModel, true);
        JavaApplication integrationModel = selectJavaAppModel(comparisonModel, false);

        VariationPointModel vpm = variabilityFactory.eINSTANCE.createVariationPointModel();

        // visit the difference tree
        Java2KDMDiffVisitor java2KDMDiffVisitor = new Java2KDMDiffVisitor(leadingModel, integrationModel,
                variantIDLeading, variantIDIntegration, vpm, comparisonModel);
        for (Diff diff : comparisonModel.getDifferences()) {
            VariationPoint vp = java2KDMDiffVisitor.doSwitch(diff);
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
     * Get a JavaApplication model from a list of model elements.
     * 
     * @param comparisonModel
     *            The comparison model to find the JavaApplication element in.
     * @param left
     *            true if left models should be loaded, false if right ones.
     * @return The first found JavaApplication or null if none is in the list.
     */
    private JavaApplication selectJavaAppModel(Comparison comparisonModel, boolean left) {

        for (MatchResource matchResource : comparisonModel.getMatchedResources()) {

            Resource originalResource = getMatchedResource(matchResource, left);
            if (originalResource == null) {
                continue;
            }

            EList<EObject> eObjectList = originalResource.getContents();
            for (EObject root : eObjectList) {
                if (root instanceof JavaApplication) {
                    return (JavaApplication) root;
                }
            }
        }
        return null;
    }

    /**
     * Get the matched resource object. Try to access the direct resource reference. If not
     * available, try to load the resource from the given uri.
     * 
     * @param matchResource
     *            The match object for the resource.
     * @param left
     *            The flag if the left or right resource is of interest.
     * @return The resource or null if it could not be laoded.
     */
    private Resource getMatchedResource(MatchResource matchResource, boolean left) {
        Resource originalResource = null;
        if (left) {
            originalResource = matchResource.getLeft();
            if (originalResource == null) {
                originalResource = loadResource(matchResource.getLeftURI());
                matchResource.setLeft(originalResource);
            }
        } else {
            originalResource = matchResource.getRight();
            if (originalResource == null) {
                originalResource = loadResource(matchResource.getRightURI());
                matchResource.setRight(originalResource);
            }
        }
        return originalResource;
    }

    /**
     * Load the resource for a given URI.
     * 
     * @param uriString
     *            The string representation of the uri.
     * @return The resource or null if it could not be loaded.
     */
    private Resource loadResource(String uriString) {
        if (uriString == null) {
            return null;
        }

        // load the required meta class packages
        JavaapplicationPackage.eINSTANCE.eClass();
        JavaPackage.eINSTANCE.eClass();

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

        URI uri = URI.createURI(uriString);
        Resource resource = resourceSet.createResource(uri);

        Map<Object, Object> loadOptions = ((XMLResourceImpl) resource).getDefaultLoadOptions();
        loadOptions.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
        loadOptions.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, new HashMap<Object, Object>());

        try {
            resource.load(null);
        } catch (IOException e) {
            logger.error("Failed to load resource: " + uriString);
        }
        return resource;
    }

    /**
     * Check that the DiffModel is a valid input. This means, that the diffed models must be modisco
     * discovered java models and contain a JavaApplication model
     * 
     * @param compareModel
     *            The diff model to check
     * @return true/false depending whether the diff model is valid.
     */
    private boolean diffModelIsValid(Comparison compareModel) {

        boolean valid = true;

        if (selectJavaAppModel(compareModel, true) == null) {
            logger.warn("Diff model invalid: No valid JavaApplication integration root (left) available");
            valid = false;
        }

        if (selectJavaAppModel(compareModel, false) == null) {
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
