package org.splevo.modisco.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.eclipse.modisco.java.composition.javaapplication.JavaapplicationPackage;

/**
 * The Class KDMUtils. Utility class to handle omg kdm models in general and java specific extended
 * models in specific.
 */
public class KDMUtil {

    /**
     * Load Java2KDM model from the standard java2kdm file set.
     * 
     * Based on a supplied main file loads the complete resource set, resolves all proxies and
     * returns the JavaApplication model.
     * 
     * Note that the method is limited to java specific application models because their is no other
     * discoverer available at the moment.
     * 
     * @param java2kdmModelFile
     *            The file object pointing to the main model file
     * @return the loaded java application model
     * @throws IOException
     *             Identifies that the file could not be loaded
     */
    public static JavaApplication loadKDMModel(File java2kdmModelFile) throws IOException {

        ResourceSet rs = loadResourceSet(java2kdmModelFile);

        Map<String, Object> options = new HashMap<String, Object>();
        options.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl(10, true));
        options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, true);
        Resource modelResource = rs.getResources().get(0);
        modelResource.load(options);
        EcoreUtil.resolveAll(rs);

        // convert the model to a java model
        EObject model = modelResource.getContents().get(0);
        if (!(model instanceof JavaApplication)) {
            throw new IllegalArgumentException("Model is not a JavaApplication: " + model.getClass().getName());
        }
        JavaApplication javaApplicationModel = (JavaApplication) model;

        return javaApplicationModel;
    }

    /**
     * Recursively load all files within a directory into a resource set.
     * 
     * @param baseDirectory
     *            The root directory to search files in.
     * @return The prepared resource set.
     */
    public static ResourceSet loadResourceSetRecursively(java.net.URI baseDirectory) {

        // load the required meta class packages
        JavaapplicationPackage.eINSTANCE.eClass();
        JavaPackage.eINSTANCE.eClass();

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

        Collection<File> files = FileUtils.listFiles(new File(baseDirectory), new String[] { "xmi" }, true);
        for (File file : files) {
            URI uri = URI.createFileURI(file.getAbsolutePath());
            resourceSet.getResource(uri, true);
        }

        return resourceSet;
    }

    /**
     * Load the resource set of the available models.
     * 
     * @param java2kdmModelFile
     *            The file of the resource to load.
     * @return The prepared resource set.
     * @throws IOException
     *             A failed loading.
     */
    public static ResourceSet loadResourceSet(File java2kdmModelFile) throws IOException {
        // check that the file is accessible
        if (!java2kdmModelFile.canRead()) {
            throw new IOException("cannot read model file " + java2kdmModelFile);
        }

        // load the required meta class packages
        JavaapplicationPackage.eINSTANCE.eClass();
        JavaPackage.eINSTANCE.eClass();

        // register the factory to be able to read xmi files
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,
                new XMIResourceFactoryImpl());

        // load the resource and resolve the proxies
        ResourceSet rs = new ResourceSetImpl();
        rs.getResource(URI.createFileURI(java2kdmModelFile.getAbsolutePath()), true);
        return rs;
    }
}
