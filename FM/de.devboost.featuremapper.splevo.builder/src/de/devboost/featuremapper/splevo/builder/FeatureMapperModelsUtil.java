package de.devboost.featuremapper.splevo.builder;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.featuremapper.models.feature.FeatureModel;
import org.featuremapper.models.feature.FeaturePackage;
import org.featuremapper.models.featuremapping.FeatureMappingModel;

/**
 * Utility class to handle FeatureMapper feature models.
 */
public class FeatureMapperModelsUtil {

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
	public static FeatureModel loadFeatureModel(File featureModelFile)
			throws IOException {

		// load the required meta class packages
		FeaturePackage.eINSTANCE.eClass();

		// register the factory to be able to read xmi files
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				FEATURE_MODEL_FILE_EXTENSION, new XMIResourceFactoryImpl());

		// load the resource and resolve the proxies
		ResourceSet rs = new ResourceSetImpl();
		Resource r = rs.createResource(URI.createPlatformResourceURI(
				featureModelFile.getPath(), true));
		r.load(null);
		EcoreUtil.resolveAll(rs);

		// convert the model to a feature model
		EObject model = r.getContents().get(0);
		if (!(model instanceof FeatureModel)) {
			throw new IOException("Model is not a valid feature model: "
					+ model.getClass().getName());
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
	public static void saveFeatureModel(FeatureModel featureModel, File filePath)
			throws IOException {
		String featureModelFileExtension = FEATURE_MODEL_FILE_EXTENSION;
		// try to write to the project file
		saveModelToPath(featureModel, filePath, "featureModel", featureModelFileExtension);
	}

	/**
	 * Save a feature mapping model to a specified file.
	 * 
	 * @param featureModel
	 *            The feature model to save.
	 * @param filePath
	 *            The eclipse workspace relative file path to save to.
	 * @throws IOException
	 *             identifies that the file could not be written.
	 */
	public static void saveMappingModel(FeatureMappingModel mapping,
			File filePath) throws IOException {
		String fileExtension = FEATURE_MAPPING_FILE_EXTENSION;
		// try to write to the project file
		saveModelToPath(mapping, filePath, "mappingModel", fileExtension);
	}

	private static void saveModelToPath(EObject model, File filePath,
			String filename, String fileExtension) throws IOException {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put(fileExtension, new XMIResourceFactoryImpl());
		ResourceSet resSet = new ResourceSetImpl();
		final Resource resource = resSet.createResource(URI
				.createPlatformResourceURI(filePath.getPath() + File.separator + filename + "."
						+ fileExtension, true));
		resource.getContents().add(model);

		resource.save(Collections.EMPTY_MAP);
	}

	public static void save(FeatureMapperModelSet modelSet, File filePath)
			throws IOException {
		saveFeatureModel(modelSet.getFeatureModel(), filePath);
		saveMappingModel(modelSet.getMappingModel(), filePath);
	}
}