/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.vpm;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.splevo.commons.emf.SPLevoResourceSet;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
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
     * @param vpm
     *            The VPM to save.
     * @param filePath
     *            The eclipse workspace relative file path to save to.
     * @throws IOException
     *             identifies that the file could not be written.
     */
    public static void save(VariationPointModel vpm, File filePath) throws IOException {
        save(vpm, URI.createPlatformResourceURI(filePath.getPath(), true));
    }
    
    /**
     * Save a project model to a URI.
     *
     * @param vpm
     *            The VPM to save.
     * @param uri
     *            The URI to save to.
     * @throws IOException
     *             identifies that saving failed.
     */
    public static void save(VariationPointModel vpm, URI uri) throws IOException {
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put(VPMUtil.VPM_FILE_EXTENSION, new XMIResourceFactoryImpl());
        ResourceSet resSet = new SPLevoResourceSet();
        final Resource resource = resSet.createResource(uri);
        resource.getContents().add(vpm);

        resource.save(Collections.EMPTY_MAP);  
    }

    /**
     * Get the variant for a given id from a variation point.
     *
     * @param vp
     *            The variation point to check for the variant.
     * @param variantID
     *            The id of the variant to search for.
     * @return The variant or null of none could be found.
     */
    public static Variant getVariantFromVP(VariationPoint vp, String variantID) {

        for (Variant v : vp.getVariants()) {
            if (v.getId().equals(variantID)) {
                return v;
            }
        }

        return null;
    }
}