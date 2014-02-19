package org.splevo.vpm;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityPackage;

/**
 * Utility class to handle VariationPointModel models.
 */
public class VPMUtil {

    /** The file extension to be used for splevo projects. */
    public static final String VPM_FILE_EXTENSION = "vpm";

    /**
     * Load a variation model from a given VPM model file.
     *
     * <p>
     * A resource set providing the context to load the vpm in can be provided. For example, the
     * resource set can be prepared to properly load any referenced source models.
     * </p>
     *
     * @param vpmFile
     *            The file object pointing to the main model file
     * @param rs
     *            The resource set to load the model into.
     * @return the loaded vpm model
     * @throws IOException
     *             Identifies that the file could not be loaded
     */
    public static VariationPointModel loadVariationPointModel(File vpmFile, ResourceSet rs) throws IOException {

        // Prepare the resource set for the required resource types
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put(VPM_FILE_EXTENSION, new XMIResourceFactoryImpl());

        // load the required meta class packages
        variabilityPackage.eINSTANCE.eClass();
        Resource r = rs.createResource(URI.createPlatformResourceURI(vpmFile.getPath(), true));
        r.load(null);

        // convert the model to a java model
        EObject model = r.getContents().get(0);
        if (!(model instanceof VariationPointModel)) {
            throw new IOException("Model is not a valid VariationPointModel: " + model.getClass().getName());
        }
        VariationPointModel splEvoProjectModel = (VariationPointModel) model;

        return splEvoProjectModel;
    }

    /**
     * Save a project model to a specified file.
     *
     * @param project
     *            The project to save.
     * @param filePath
     *            The eclipse workspace relative file path to save to.
     * @throws IOException
     *             identifies that the file could not be written.
     */
    public static void save(VariationPointModel project, File filePath) throws IOException {

        // try to write to the project file
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put(VPMUtil.VPM_FILE_EXTENSION, new XMIResourceFactoryImpl());
        ResourceSet resSet = new ResourceSetImpl();
        final Resource resource = resSet.createResource(URI.createPlatformResourceURI(filePath.getPath(), true));
        resource.getContents().add(project);

        resource.save(Collections.EMPTY_MAP);
    }
}