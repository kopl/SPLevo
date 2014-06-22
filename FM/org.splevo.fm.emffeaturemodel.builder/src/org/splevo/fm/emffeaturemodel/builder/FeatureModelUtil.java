package org.splevo.fm.emffeaturemodel.builder;

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
import org.eclipse.featuremodel.FeatureModel;
import org.eclipse.featuremodel.FeatureModelPackage;

/**
 * Utility class to handle feature models.
 */
public class FeatureModelUtil {

    /** The file extension to be used for refinement models. */
    public static final String FEATURE_MODEL_FILE_EXTENSION = "featuremodel";

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
        FeatureModelPackage.eINSTANCE.eClass();

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
     * Save a refinement model to a specified file.
     * 
     * @param featureModel
     *            The feature model to save.
     * @param filePath
     *            The eclipse workspace relative file path to save to.
     * @throws IOException
     *             identifies that the file could not be written.
     */
    public static void save(FeatureModel featureModel, File filePath) throws IOException {

        // try to write to the project file
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put(FeatureModelUtil.FEATURE_MODEL_FILE_EXTENSION, new XMIResourceFactoryImpl());
        ResourceSet resSet = new ResourceSetImpl();
        final Resource resource = resSet.createResource(URI.createPlatformResourceURI(filePath.getPath(), true));
        resource.getContents().add(featureModel);

        resource.save(Collections.EMPTY_MAP);
    }
}