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

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.featuremapper.interpreter.emf.EMFInterpreter;
import org.featuremapper.models.feature.Feature;
import org.featuremapper.models.feature.FeatureFactory;
import org.featuremapper.models.feature.FeatureModel;
import org.featuremapper.models.feature.FeaturePackage;
import org.featuremapper.models.feature.Group;
import org.featuremapper.models.featuremapping.ColorMapping;
import org.featuremapper.models.featuremapping.ElementMapping;
import org.featuremapper.models.featuremapping.FeatureMappingFactory;
import org.featuremapper.models.featuremapping.FeatureMappingModel;
import org.featuremapper.models.featuremapping.FeatureModelRef;
import org.featuremapper.models.featuremapping.FeatureRef;
import org.featuremapper.models.featuremapping.SolutionModelRef;
import org.splevo.fm.builder.FeatureModelBuilder;
import org.splevo.fm.builder.FeatureModelWrapper;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * A FeatureModel builder that derives the FeatureMapper FM from an existing variation point model (VPM).
 * 
 */
public class FeatureMapperModelsBuilder implements FeatureModelBuilder<FeatureMapperModelSet> {

	private static Logger logger = Logger.getLogger(FeatureMapperModelsBuilder.class);
	private Map<String, Feature> cachedVariantFeatures = new HashMap<String, Feature>();
	private Map<Feature, ColorMapping> colorMappings = new HashMap<Feature, ColorMapping>();

	private static final String[] indexcolors = new String[] { "#000000", "#FFFF00", "#1CE6FF", "#FF34FF", "#FF4A46",
			"#008941", "#006FA6", "#A30059", "#FFDBE5", "#7A4900", "#0000A6", "#63FFAC", "#B79762", "#004D43",
			"#8FB0FF", "#997D87", "#5A0007", "#809693", "#FEFFE6", "#1B4400", "#4FC601", "#3B5DFF", "#4A3B53",
			"#FF2F80", "#61615A", "#BA0900", "#6B7900", "#00C2A0", "#FFAA92", "#FF90C9", "#B903AA", "#D16100",
			"#DDEFFF", "#000035", "#7B4F4B", "#A1C299", "#300018", "#0AA6D8", "#013349", "#00846F", "#372101",
			"#FFB500", "#C2FFED", "#A079BF", "#CC0744", "#C0B9B2", "#C2FF99", "#001E09", "#00489C", "#6F0062",
			"#0CBD66", "#EEC3FF", "#456D75", "#B77B68", "#7A87A1", "#788D66", "#885578", "#FAD09F", "#FF8A9A",
			"#D157A0", "#BEC459", "#456648", "#0086ED", "#886F4C",

			"#34362D", "#B4A8BD", "#00A6AA", "#452C2C", "#636375", "#A3C8C9", "#FF913F", "#938A81", "#575329",
			"#00FECF", "#B05B6F", "#8CD0FF", "#3B9700", "#04F757", "#C8A1A1", "#1E6E00", "#7900D7", "#A77500",
			"#6367A9", "#A05837", "#6B002C", "#772600", "#D790FF", "#9B9700", "#549E79", "#FFF69F", "#201625",
			"#72418F", "#BC23FF", "#99ADC0", "#3A2465", "#922329", "#5B4534", "#FDE8DC", "#404E55", "#0089A3",
			"#CB7E98", "#A4E804", "#324E72", "#6A3A4C", "#83AB58", "#001C1E", "#D1F7CE", "#004B28", "#C8D0F6",
			"#A3A489", "#806C66", "#222800", "#BF5650", "#E83000", "#66796D", "#DA007C", "#FF1A59", "#8ADBB4",
			"#1E0200", "#5B4E51", "#C895C5", "#320033", "#FF6832", "#66E1D3", "#CFCDAC", "#D0AC94", "#7ED379",
			"#012C58" };

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
	 * The strategy used is very simple and builds one feature per VariationPoint and one ChildFeature per Variant. The
	 * connection is always alternative (OR).
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
		mappingModel.setComposerID(new EMFInterpreter().getID());
		mappingModel.setFeatureModel(fmreference);
		fmreference.setValue(featureModel);

		List<Resource> solutionModelResources = new ArrayList<Resource>();

		for (VariationPointGroup variationPointGroup : vpm.getVariationPointGroups()) {
			cachedVariantFeatures.clear();

			if (hasMultipleDifferentVariationPoints(variationPointGroup)) {
				Feature vpFeature = createVariationPointGroupFeature(variationPointGroup, featureModel.getRoot(), true);

				Group group = createFeatureGroup();
				vpFeature.getGroups().add(group);

				// make group mandatory and child groups alternative
				group.setMinCardinality(1);
				group.setMaxCardinality(1);

				for (VariationPoint variationPoint : variationPointGroup.getVariationPoints()) {
					if (!variationPoint.isRefactored()) {
						continue;
					}

					for (Variant variant : variationPoint.getVariants()) {
						Feature vFeature = createVariantFeature(variationPointGroup, variant);
						vFeature.setMaxCardinality(1);
						group.getChildFeatures().add(vFeature);
						addFeatureMapping(mappingFactory, mappingModel, solutionModelResources, variant, vFeature);
					}
				}

			} else {
				Feature vpFeature = createVariationPointGroupFeature(variationPointGroup, featureModel.getRoot(), false);

				for (VariationPoint variationPoint : variationPointGroup.getVariationPoints()) {
					if (!variationPoint.isRefactored()) {
						continue;
					}

					for (Variant variant : variationPoint.getVariants()) {
						addFeatureMapping(mappingFactory, mappingModel, solutionModelResources, variant, vpFeature);
					}
				}
			}
		}

		List<String> knownResourceUris = new ArrayList<String>();
		for (Resource resource : solutionModelResources) {
			if (resource != null && !resource.getContents().isEmpty()) {
				String uriString = resource.getURI().toString();
				if (knownResourceUris.contains(uriString)) {
					continue;
				}
				knownResourceUris.add(uriString);
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
		initialiseColorMappings(colorMappings.values());
		FeatureMapperModelSet featureMapperModelSet = new FeatureMapperModelSet(featureModel, mappingModel);
		return new FeatureModelWrapper<FeatureMapperModelSet>(featureMapperModelSet, true);
	}

	private void addFeatureMapping(FeatureMappingFactory mappingFactory, FeatureMappingModel mappingModel,
			List<Resource> solutionModelResources, Variant variant, Feature vFeature) {
		EList<SoftwareElement> softwareEntities = variant.getImplementingElements();

		for (SoftwareElement softwareElement : softwareEntities) {
			getOrCreateColorMapping(vFeature, mappingModel);
			ElementMapping mapping = mappingFactory.createElementMapping();
			FeatureRef featureRef = mappingFactory.createFeatureRef();
			featureRef.setFeature(vFeature);
			mapping.setTerm(featureRef);

			EObject wrappedElement = softwareElement.getWrappedElement();

			mapping.setElement(wrappedElement);
			Resource containingResource = wrappedElement.eResource();
			URI fixedUri = getFixedURI(containingResource.getURI());
			containingResource.setURI(fixedUri);
			solutionModelResources.add(containingResource);

			mappingModel.getMappings().add(mapping);
		}
	}

	private boolean hasMultipleDifferentVariationPoints(VariationPointGroup variationPointGroup) {
		EList<VariationPoint> variationPoints = variationPointGroup.getVariationPoints();
		Set<String> ids = new HashSet<String>();
		for (VariationPoint variationPoint : variationPoints) {
			EList<Variant> variants = variationPoint.getVariants();
			for (Variant variant : variants) {
				String id = variant.getId();
				ids.add(id);
			}
			if (ids.size() > 1) {
				return true;
			}
		}
		return false;
	}

	private void initialiseColorMappings(Collection<ColorMapping> values) {
		List<Color> colors = createUniqueColors(values.size());
		int i = 0;
		for (ColorMapping colorMapping : values) {
			Color color = colors.get(i);
			colorMapping.setColor((color.getRed() << 16) + (color.getGreen() << 8) + color.getBlue());
			i++;
		}
	}

	private List<Color> createUniqueColors(int size) {
		List<Color> colors = new ArrayList<Color>();
		for (int i = 0; i < size; i++) {
			String hexColor = indexcolors[i + 1 % 128];
			Color color = Color.decode(hexColor);
			colors.add(color);
		}
		return colors;
	}

	private void getOrCreateColorMapping(Feature vFeature, FeatureMappingModel mappingModel) {
		if (colorMappings.get(vFeature) != null) {
			return;
		}
		FeatureMappingFactory mappingFactory = FeatureMappingFactory.eINSTANCE;

		ColorMapping colorMapping = mappingFactory.createColorMapping();
		FeatureRef term = mappingFactory.createFeatureRef();
		term.setFeature(vFeature);
		colorMapping.setTerm(term);
		mappingModel.getColorMappings().add(colorMapping);
		colorMappings.put(vFeature, colorMapping);
	}

	/**
	 * fixes absolute file paths to workspace relative ones
	 * 
	 * @param uri
	 *            the uri to fix
	 * @return the fixed uri
	 */
	protected URI getFixedURI(URI uri) {
		URI fixedUri = uri;
		if (uri.isFile()) {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();

			IPath location = Path.fromOSString(uri.toFileString());
			final IFile file = workspace.getRoot().getFileForLocation(location);
			fixedUri = URI.createPlatformResourceURI(file.getProject().getName() + File.separator
					+ file.getProjectRelativePath().toString(), true);

		}

		return fixedUri;
	}

	/**
	 * Create a feature for a supplied variant.
	 * 
	 * @param variant
	 *            The variant to create the feature for.
	 * @return The prepared feature
	 */
	private Feature createVariantFeature(VariationPointGroup variationPointGroup, Variant variant) {
		String variantFeatureName = variationPointGroup.getName() + "_" + variant.getId();
		Feature feature = cachedVariantFeatures.get(variantFeatureName);
		if (feature == null) {
			feature = FeatureFactory.eINSTANCE.createFeature();
			feature.setName(variantFeatureName);
			cachedVariantFeatures.put(variantFeatureName, feature);
		}

		return feature;
	}

	/**
	 * Create a feature for a variation point and attach it with a mandatory group to the supplied root.
	 * 
	 * @param variationPointGroup
	 *            The variation point to create the feature for.
	 * @param parentFeature
	 *            The feature to attach the group to.
	 * @return The new feature element.
	 */
	private Feature createVariationPointGroupFeature(VariationPointGroup variationPointGroup, Feature parentFeature,
			boolean mandatory) {
		Group group = createFeatureGroup();
		if (mandatory) {
			group.setMinCardinality(1);
		} else {
			group.setMinCardinality(0);
		}
		group.setMaxCardinality(1);

		parentFeature.getGroups().add(group);

		Feature feature = FeatureFactory.eINSTANCE.createFeature();
		feature.setName(variationPointGroup.getName());
		// feature.setId(EcoreUtil.generateUUID());
		group.getChildFeatures().add(feature);
		if (mandatory) {
			feature.setMinCardinality(1);
		} else {
			feature.setMinCardinality(0);
		}
		feature.setMaxCardinality(1);
		return feature;
	}

	/**
	 * Create a mandatory / OR feature group.
	 * 
	 * @return The instantiated and configured feature group.
	 */
	private Group createFeatureGroup() {
		Group group = FeatureFactory.eINSTANCE.createGroup();

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
