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
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.project.utils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.splevo.commons.emf.SPLevoResourceSet;
import org.splevo.project.ProjectPackage;
import org.splevo.project.SPLevoProject;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * The Class SPLevoProjectUtil. Utility class to handle splevo project models.
 */
public class SPLevoProjectUtil {

    /** The file extension to be used for splevo projects. */
    public static final String SPLEVO_FILE_EXTENSION = "splevoproject";

    /** ID of this project nature. */
    public static final String NATURE_ID = "org.splevo.ui.splevonature";

    private static final Logger LOGGER = Logger.getLogger(SPLevoProjectUtil.class);

    /**
     * Loads a SPLevo project from a file resource.
     * 
     * @param consolidationProject
     *            The project that represents the consolidation project.
     * @return The loaded SPLevo project.
     * @throws IOException
     *             Thrown if the file could not be loaded.
     */
    public static SPLevoProject loadSPLevoProjectModel(IProject consolidationProject) throws IOException {
        Optional<IFile> projectFile = findSPLevoProjectFile(consolidationProject);
        if (projectFile.isPresent()) {
            return loadSPLevoProjectModel(projectFile.get());
        }
        return null;
    }

    /**
     * Loads a SPLevo project from a file resource.
     * 
     * @param splevoProjectFile
     *            The file resource referencing the project file.
     * @return The loaded SPLevo project.
     * @throws IOException
     *             Thrown if the file could not be loaded.
     */
    public static SPLevoProject loadSPLevoProjectModel(IFile splevoProjectFile) throws IOException {
        return loadSPLevoProjectModel(splevoProjectFile.getFullPath().toFile());
    }

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
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(SPLEVO_FILE_EXTENSION,
                new XMIResourceFactoryImpl());

        // load the resource and resolve the proxies
        ResourceSet rs = new SPLevoResourceSet();
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
        ResourceSet resSet = new SPLevoResourceSet();
        final Resource resource = resSet.createResource(URI.createPlatformResourceURI(filePath.getPath(), true));
        resource.getContents().add(project);

        resource.save(Collections.EMPTY_MAP);
    }

    /**
     * Checks if the given project is a SPLevoProject.
     * 
     * @param project
     *            The project to check.
     * @return True if the project is a SPLevoProject, False otherwise.
     */
    public static boolean isSPLevoProject(IProject project) {
        try {
            return project.hasNature(NATURE_ID);
        } catch (CoreException e) {
            return false;
        }
    }

    /**
     * Finds all SPLevoProjects in the workspace.
     * 
     * @param onlyOpenProjects
     *            If true, only opened projects are considers. If false, all projects in the
     *            workspace are considered.
     * @return The list of available SPLevoProjects in the workspace.
     */
    public static Iterable<IProject> findAllSPLevoProjectsInWorkspace(final boolean onlyOpenProjects) {
        return Iterables.filter(Lists.newArrayList(ResourcesPlugin.getWorkspace().getRoot().getProjects()),
                new Predicate<IProject>() {
                    @Override
                    public boolean apply(IProject arg0) {
                        return (!onlyOpenProjects || arg0.isOpen()) && isSPLevoProject(arg0);
                    }
                });
    }

    /**
     * Finds the SPLevoProject file in the given project.
     * 
     * @param project
     *            The project to be searched.
     * @return The found file as an {@link Optional}.
     */
    public static Optional<IFile> findSPLevoProjectFile(IProject project) {
        SPLevoProjectVisitor visitor = new SPLevoProjectVisitor();
        try {
            project.accept(visitor);
        } catch (CoreException e) {
            LOGGER.warn("Error looking for the SPLevo project model file.", e);
            return null;
        }
        return Optional.fromNullable(visitor.getSPLevoProjectFile());
    }

    /**
     * Resource visitor that finds a SPLevo project file and returns the first finding. If no
     * element is found, the returned file is null.
     */
    private static class SPLevoProjectVisitor implements IResourceVisitor {

        private IFile splevoProjectModelFile = null;

        @Override
        public boolean visit(IResource resource) throws CoreException {
            if (splevoProjectModelFile != null) {
                return false;
            }

            if ((resource.getType() & IResource.FILE) != 0) {
                if (resource.getName().endsWith(SPLevoProjectUtil.SPLEVO_FILE_EXTENSION)) {
                    splevoProjectModelFile = (IFile) resource;
                }
            }

            return true;
        }

        public IFile getSPLevoProjectFile() {
            return splevoProjectModelFile;
        }
    }

}