package org.splevo.fm.emffeaturemodel.builder;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.featuremodel.Feature;
import org.eclipse.featuremodel.FeatureModel;
import org.eclipse.featuremodel.FeatureModelFactory;
import org.eclipse.featuremodel.Group;
import org.eclipse.featuremodel.diagrameditor.FMEDiagramEditor;
import org.eclipse.featuremodel.diagrameditor.FMEDiagramEditorUtil;
import org.splevo.fm.builder.FeatureModelBuilder;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * A FeatureModel (FM) builder that derives the FM from an existing variation point model (VPM).
 *
 */
public class EMFFeatureModelBuilder implements FeatureModelBuilder<FeatureModel> {

    private static Logger logger = Logger.getLogger(EMFFeatureModelBuilder.class);

    /**
     * Build a feature model based on variation point model.
     *
     * The strategy used is very simple and builds one feature per VariationPoint and one
     * ChildFeature per Variant. The connection is always alternative (OR).
     *
     * @param vpm
     *            The variation point model to make use of.
     * @param rootFeatureName
     *            The name of the feature models root element.
     * @return The prepared feature model.
     */
    @Override
    public FeatureModel build(VariationPointModel vpm, String rootFeatureName) {

        FeatureModel fm = initFeatureModel(vpm, rootFeatureName);

        for (VariationPointGroup variationPointGroup : vpm.getVariationPointGroups()) {

            Feature vpFeature = createVariationPointGroupFeature(variationPointGroup, fm.getRoot());

            Group group = createMandatoryFeatureGroup();
            vpFeature.getChildren().add(group);

            Set<String> variantIds = new HashSet<String>();
            for (VariationPoint variationPoint : variationPointGroup.getVariationPoints()) {
                for (Variant variant : variationPoint.getVariants()) {
                    variantIds.add(variant.getVariantId());
                }
            }

            for (String variantId : variantIds) {
                Feature vFeature = createVariantFeature(variantId);
                group.getFeatures().add(vFeature);
            }

        }

        return fm;
    }

    /**
     * Create a feature for a supplied variant.
     *
     * @param variantId
     *            The variant to create the feature for.
     * @return The prepared feature
     */
    private Feature createVariantFeature(String variantId) {

        Feature feature = FeatureModelFactory.eINSTANCE.createFeature();
        feature.setName(variantId);
        feature.setId(EcoreUtil.generateUUID());

        return feature;
    }

    /**
     * Create a feature for a variation point and attach it with a mandatory group to the supplied
     * root.
     *
     * @param variationPointGroup
     *            The variation point to create the feature for.
     * @param parentFeature
     *            The feature to attach the group to.
     * @return The new feature element.
     */
    private Feature createVariationPointGroupFeature(VariationPointGroup variationPointGroup, Feature parentFeature) {
        Group group = createMandatoryFeatureGroup();
        parentFeature.getChildren().add(group);

        Feature feature = FeatureModelFactory.eINSTANCE.createFeature();
        feature.setName(variationPointGroup.getGroupId());
        feature.setId(EcoreUtil.generateUUID());
        group.getFeatures().add(feature);

        return feature;
    }

    /**
     * Create a mandatory / OR feature group.
     *
     * @return The instantiated and configured feature group.
     */
    private Group createMandatoryFeatureGroup() {
        Group group = FeatureModelFactory.eINSTANCE.createGroup();
        group.setId(EcoreUtil.generateUUID());
        group.setLower(1);
        group.setUpper(1);
        return group;
    }

    /**
     * Initialize the feature model itself.
     *
     * @param vpm
     *            The vpm to init the fm for.
     * @param rootFeatureName
     *            The name of the root feature.
     * @return The initialized fm.
     */
    private FeatureModel initFeatureModel(VariationPointModel vpm, String rootFeatureName) {
        FeatureModel fm = FeatureModelFactory.eINSTANCE.createFeatureModel();
        fm.setId(EcoreUtil.generateUUID());
        fm.setVersion("1.0");
        Feature rootFeature = FeatureModelFactory.eINSTANCE.createFeature();
        rootFeature.setName(rootFeatureName);
        rootFeature.setId(EcoreUtil.generateUUID());
        fm.setRoot(rootFeature);
        return fm;
    }

    @Override
    public String getId() {
        return "org.splevo.fm.builder.emffeaturemodel.builder";
    }

    @Override
    public String getLabel() {
        return "EMF Feature Model Builder";
    }

    @Override
    public void save(FeatureModel featureModel, String targetDirectory) {
        String modelPath = targetDirectory + "feature-model.featuremodel";
        String diagramPath = targetDirectory + "feature-model." + FMEDiagramEditor.DIAGRAM_FILE_EXTENSION;
        File file = new File(modelPath);
        URI featureModelURI = URI.createPlatformResourceURI(modelPath, false);
        URI featureDiagramURI = URI.createPlatformResourceURI(diagramPath, false);
        try {
            FeatureModelUtil.save(featureModel, file);
            FMEDiagramEditorUtil.createFeatureDiagram(featureDiagramURI, featureModelURI, new NullProgressMonitor());
        } catch (IOException e) {
            logger.error("Failed to save feature model", e);
        }
    }

}
