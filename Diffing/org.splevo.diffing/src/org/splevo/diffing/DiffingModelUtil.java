/*******************************************************************************
 * Copyright (c) 2013
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
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
     * @param filePath
     *            The file object pointing to the diff model file
     * @param rs
     *            The resource set to load the model into.
     * @return the loaded diff model
     * @throws IOException
     *             Identifies that the file could not be loaded
     */
    public static Comparison loadModel(File filePath, ResourceSet rs) throws IOException {

        // load the required meta class packages
        ComparePackage.eINSTANCE.eClass();

        // register the factory to be able to read xmi files
        String fileExtension = getFileExtension(filePath);
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(fileExtension, new XMIResourceFactoryImpl());

        // load the resource and resolve the proxies
        URI uri = null;
        if (filePath.isAbsolute()) {
            uri = URI.createFileURI(filePath.getPath());
        } else {
            uri = URI.createPlatformResourceURI(filePath.getPath(), false);
        }
        Resource r = rs.createResource(uri);
        r.load(null);

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
     * If the file path is absolute, it will be used as it is.
     * If not, a PlatformResourceURI is build (i.e. with the scheme platform:/resource/
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
        ResourceSet resSet = new ResourceSetImpl();
        Map<String, Object> m = resSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
        m.put(fileExtension, new XMIResourceFactoryImpl());

        URI uri = null;
        if (filePath.isAbsolute()) {
            uri = URI.createFileURI(filePath.getPath());
        } else {
            uri = URI.createPlatformResourceURI(filePath.getPath(), false);
        }
        final Resource resource = resSet.createResource(uri);
        resource.getContents().add(comparisonModel);

        resource.save(Collections.EMPTY_MAP);
    }
}