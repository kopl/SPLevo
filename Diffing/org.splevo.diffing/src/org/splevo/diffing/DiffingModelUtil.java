package org.splevo.diffing;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.ComparePackage;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

/**
 * The Class SPLevoProjectUtil. Utility class to handle splevo project models.
 */
public class DiffingModelUtil {

    /**
     * Load diff model from the standard xmi file. The concrete file extension is determined from
     * the given file name.
     * 
     * 
     * @param modelFile
     *            The file object pointing to the diff model file
     * @return the loaded diff model
     * @throws IOException
     *             Identifies that the file could not be loaded
     */
    public static Comparison loadModel(File modelFile) throws IOException {

        // load the required meta class packages
        ComparePackage.eINSTANCE.eClass();

        String fileExtension = getFileExtension(modelFile);

        // register the factory to be able to read xmi files
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(fileExtension, new XMIResourceFactoryImpl());

        // load the resource and resolve the proxies
        ResourceSet rs = new ResourceSetImpl();
        Resource r = rs.createResource(URI.createPlatformResourceURI(modelFile.getPath(), true));
        r.load(null);
        EcoreUtil.resolveAll(rs);

        // convert the model to a java model
        EObject model = r.getContents().get(0);
        if (!(model instanceof Comparison)) {
            throw new IOException("Model is not a valid SPLevoProject: " + model.getClass().getName());
        }
        Comparison comparisionModel = (Comparison) model;

        return comparisionModel;
    }

    /**
     * Get the file extension of a file.
     * 
     * @param file
     *            The file object to get the extension for.
     * @return The file extension or null if none found.
     */
    private static String getFileExtension(File file) {
        String path = file.toString();
        if (path == null) {
            return null;
        }
        String fileExtension = path.substring(path.lastIndexOf('.') + 1);
        return fileExtension;
    }

    /**
     * Save a project model to a specified file.
     * 
     * @param comparisonModel
     *            The model to save.
     * @param filePath
     *            The eclipse workspace relative file path to save to.
     * @throws IOException
     *             identifies that the file could not be written.
     */
    public static void save(Comparison comparisonModel, File filePath) throws IOException {

        String fileExtension = getFileExtension(filePath);

        // try to write to the project file
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put(fileExtension, new XMIResourceFactoryImpl());
        ResourceSet resSet = new ResourceSetImpl();
        final Resource resource = resSet.createResource(URI.createPlatformResourceURI(filePath.getPath(), true));
        resource.getContents().add(comparisonModel);

        resource.save(Collections.EMPTY_MAP);
    }
}