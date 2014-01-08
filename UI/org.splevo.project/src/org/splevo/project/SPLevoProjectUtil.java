/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.project;

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

/**
 * The Class SPLevoProjectUtil. Utility class to handle splevo project models.
 */
public class SPLevoProjectUtil {

    /** The file extension to be used for splevo projects. */
    public static final String SPLEVO_FILE_EXTENSION = "splevoproject";

    /**
     * Load SPLevoProject model from the standard xmi file.
     * 
     * 
     * @param splevoProjectFile
     *            The file object pointing to the main model file
     * @return the loaded splevo project model
     * @throws IOException
     *             Identifies that the file could not be loaded
     */
    public static SPLevoProject loadSPLevoProjectModel(File splevoProjectFile) throws IOException {

        // load the required meta class packages
        ProjectPackage.eINSTANCE.eClass();

        // register the factory to be able to read xmi files
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(SPLEVO_FILE_EXTENSION, new XMIResourceFactoryImpl());

        // load the resource and resolve the proxies
        ResourceSet rs = new ResourceSetImpl();
        Resource r = rs.createResource(URI.createPlatformResourceURI(splevoProjectFile.getPath(), true));
        r.load(null);
        EcoreUtil.resolveAll(rs);

        // convert the model to a java model
        EObject model = r.getContents().get(0);
        if (!(model instanceof SPLevoProject)) {
            throw new IOException("Model is not a valid SPLevoProject: " + model.getClass().getName());
        }
        SPLevoProject splEvoProjectModel = (SPLevoProject) model;

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
    public static void save(SPLevoProject project, File filePath) throws IOException {
        
        // try to write to the project file
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put(SPLevoProjectUtil.SPLEVO_FILE_EXTENSION, new XMIResourceFactoryImpl());
        ResourceSet resSet = new ResourceSetImpl();
        final Resource resource = resSet.createResource(URI.createPlatformResourceURI(filePath.getPath(), true));
        resource.getContents().add(project);

        resource.save(Collections.EMPTY_MAP);
    }
}