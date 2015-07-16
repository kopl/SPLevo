/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Wende (DevBoost GmbH) - initial API and implementation and/or initial documentation
 *******************************************************************************/

package de.devboost.featuremapper.splevo.builder;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emftext.language.java.commons.Commentable;
import org.featuremapper.models.feature.Feature;
import org.featuremapper.models.feature.FeatureFactory;
import org.featuremapper.models.feature.FeatureModel;
import org.featuremapper.models.feature.FeaturePackage;
import org.featuremapper.models.feature.Group;
import org.featuremapper.models.featuremapping.ElementMapping;
import org.featuremapper.models.featuremapping.FeatureMappingFactory;
import org.featuremapper.models.featuremapping.FeatureMappingModel;
import org.featuremapper.models.featuremapping.FeatureModelRef;
import org.featuremapper.models.featuremapping.FeatureRef;
import org.featuremapper.models.featuremapping.SolutionModelRef;
import org.splevo.fm.builder.FeatureModelBuilder;
import org.splevo.fm.builder.FeatureModelWrapper;
import org.splevo.jamopp.vpm.software.JaMoPPJavaSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * A FeatureModel builder that derives the FeatureMapper FM from an existing variation point model
 * (VPM).
 *
 */
public class FeatureMapperModelsBuilder implements FeatureModelBuilder<FeatureMapperModelSet> {

    private static Logger logger = Logger.getLogger(FeatureMapperModelsBuilder.class);

    @Override
    public String getId() {
        return "de.devboost.featuremapper.splevo.builder";
    }

    @Override
    public String getLabel() {
        return "FeatureMapper Models Builder";
    }

    /**
     * Build a feature model and a mapping model based on variation point model.
     *
     * The strategy used is very simple and builds one feature per VariationPoint and one
     * ChildFeature per Variant. The connection is always alternative (OR).
     *
     * In addition a corresponding mapping model is created.
     *
     * @param vpm
     *            The variation point model to make use of.
     * @param rootFeatureName
     *            The name of the feature models root element.
     * @return The prepared feature model and mapping model.
     */
    @Override
    public FeatureModelWrapper<FeatureMapperModelSet> build(VariationPointModel vpm, String rootFeatureName) {

        FeatureModel featureModel = initFeatureModel(vpm, rootFeatureName);

        FeatureMappingFactory mappingFactory = FeatureMappingFactory.eINSTANCE;
        FeatureMappingModel mappingModel = mappingFactory.createFeatureMappingModel();

        FeatureModelRef fmreference = mappingFactory.createFeatureModelRef();
        mappingModel.setFeatureModel(fmreference);
        fmreference.setValue(featureModel);

        for (VariationPointGroup variationPointGroup : vpm.getVariationPointGroups()) {

            Feature vpFeature = createVariationPointGroupFeature(variationPointGroup, featureModel.getRoot());

            Group group = createMandatoryFeatureGroup();
            vpFeature.getGroups().add(group);

            List<Resource> solutionModelResources = new LinkedList<Resource>();

            for (VariationPoint variationPoint : variationPointGroup.getVariationPoints()) {
                for (Variant variant : variationPoint.getVariants()) {
                    Feature vFeature = createVariantFeature(variant.getId());
                    group.getChildFeatures().add(vFeature);

                    EList<SoftwareElement> softwareEntities = variant.getImplementingElements();

                    for (SoftwareElement softwareElement : softwareEntities) {
                        ElementMapping mapping = mappingFactory.createElementMapping();
                        FeatureRef featureRef = mappingFactory.createFeatureRef();
                        featureRef.setFeature(vFeature);
                        mapping.setTerm(featureRef);

                        // TODO BK, CM we need access to the actually source
                        // code
                        // EObject element here,
                        // I know this cast is really bad,
                        Commentable jamoppElement = ((JaMoPPJavaSoftwareElement) softwareElement).getJamoppElement();
                        mapping.setElement(jamoppElement);
                        solutionModelResources.add(jamoppElement.eResource());

                        mappingModel.getMappings().add(mapping);
                    }

                }
            }

            for (Resource resource : solutionModelResources) {
                if (resource != null && !resource.getContents().isEmpty()) {

                    EObject rootObject = resource.getContents().get(0);

                    SolutionModelRef solutionModelRef = mappingFactory.createSolutionModelRef();
                    solutionModelRef.setValue(rootObject);
                    mappingModel.getSolutionModels().add(solutionModelRef);

                } else {
                    String resourceIdentifier = "unknown resource uri";
                    if (resource != null && resource.getURI() != null) {
                        resourceIdentifier = resource.getURI().toFileString();
                    }
                    logger.error("Could not create solution model reference for resource " + resourceIdentifier);
                }
            }

        }

        FeatureMapperModelSet featureMapperModelSet = new FeatureMapperModelSet(featureModel, mappingModel);
        return new FeatureModelWrapper<FeatureMapperModelSet>(featureMapperModelSet, true);
    }

    /**
     * Create a feature for a supplied variant.
     *
     * @param variantId
     *            The variant to create the feature for.
     * @return The prepared feature
     */
    private Feature createVariantFeature(String variantId) {

        Feature feature = FeatureFactory.eINSTANCE.createFeature();
        feature.setName(variantId);
        // feature.setId(EcoreUtil.generateUUID());

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
        parentFeature.getGroups().add(group);

        Feature feature = FeatureFactory.eINSTANCE.createFeature();
        feature.setName(variationPointGroup.getName());
        // feature.setId(EcoreUtil.generateUUID());
        group.getChildFeatures().add(feature);

        return feature;
    }

    /**
     * Create a mandatory / OR feature group.
     *
     * @return The instantiated and configured feature group.
     */
    private Group createMandatoryFeatureGroup() {
        Group group = FeatureFactory.eINSTANCE.createGroup();
        // group.setId(EcoreUtil.generateUUID());
        group.setMinCardinality(1);
        group.setMaxCardinality(1);
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
        FeatureModel fm = FeatureFactory.eINSTANCE.createFeatureModel();
        // fm.setId(EcoreUtil.generateUUID());
        // fm.setVersion("1.0");
        Feature rootFeature = FeatureFactory.eINSTANCE.createFeature();
        rootFeature.setName(rootFeatureName);
        // rootFeature.setId(EcoreUtil.generateUUID());
        fm.setRoot(rootFeature);
        return fm;
    }

    /** The file extension to be used for refinement models. */
    public static final String FEATURE_MODEL_FILE_EXTENSION = "feature";
    public static final String FEATURE_MAPPING_FILE_EXTENSION = "featuremapping";

    /**
     * Load refinement model from the standard xmi file.
     *
     *
     * @param featureModelFile
     *            The file object pointing to the main model file
     * @return the loaded refinement model
     * @throws IOException
     *             Identifies that the file could not be loaded
     */
    public static FeatureModel loadFeatureModel(File featureModelFile) throws IOException {

        // load the required meta class packages
        FeaturePackage.eINSTANCE.eClass();

        // register the factory to be able to read xmi files
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(FEATURE_MODEL_FILE_EXTENSION,
                new XMIResourceFactoryImpl());

        // load the resource and resolve the proxies
        ResourceSet rs = new ResourceSetImpl();
        Resource r = rs.createResource(URI.createPlatformResourceURI(featureModelFile.getPath(), true));
        r.load(null);
        EcoreUtil.resolveAll(rs);

        // convert the model to a feature model
        EObject model = r.getContents().get(0);
        if (!(model instanceof FeatureModel)) {
            throw new IOException("Model is not a valid feature model: " + model.getClass().getName());
        }
        FeatureModel featureModel = (FeatureModel) model;

        return featureModel;
    }

    /**
     * Save a feature model model to a specified file.
     *
     * @param featureModel
     *            The feature model to save.
     * @param filePath
     *            The eclipse workspace relative file path to save to.
     * @throws IOException
     *             identifies that the file could not be written.
     */
    public static void saveFeatureModel(FeatureModel featureModel, File filePath) throws IOException {
        String featureModelFileExtension = FEATURE_MODEL_FILE_EXTENSION;
        // try to write to the project file
        saveModelToPath(featureModel, filePath, "featureModel", featureModelFileExtension);
    }

    /**
     * Save a feature mapping model to a specified file.
     *
     * @param mapping
     *            The feature mapper mapping model to save.
     * @param filePath
     *            The eclipse workspace relative file path to save to.
     * @throws IOException
     *             identifies that the file could not be written.
     */
    public static void saveMappingModel(FeatureMappingModel mapping, File filePath) throws IOException {
        String fileExtension = FEATURE_MAPPING_FILE_EXTENSION;
        // try to write to the project file
        saveModelToPath(mapping, filePath, "mappingModel", fileExtension);
    }

    private static void saveModelToPath(EObject model, File filePath, String filename, String fileExtension)
            throws IOException {
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put(fileExtension, new XMIResourceFactoryImpl());
        ResourceSet resSet = new ResourceSetImpl();
        final Resource resource = resSet.createResource(URI.createPlatformResourceURI(filePath.getPath()
                + File.separator + filename + "." + fileExtension, true));
        resource.getContents().add(model);

        resource.save(Collections.EMPTY_MAP);
    }

    @Override
    public void save(FeatureMapperModelSet modelSet, String targetDirectory) {

        File filePath = new File(targetDirectory);
        try {
            saveFeatureModel(modelSet.getFeatureModel(), filePath);
            saveMappingModel(modelSet.getMappingModel(), filePath);
        } catch (IOException e) {
            logger.error("Failed to save feature model", e);
        }
    }

}
