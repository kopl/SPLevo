package org.splevo.extraction.java.modisco;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.modisco.java.composition.discoverer.DiscoverKDMSourceAndJavaModelFromJavaProject;
import org.eclipse.modisco.java.discoverer.ElementsToAnalyze;
import org.splevo.extraction.SoftwareModelExtractionException;
import org.splevo.extraction.SoftwareModelExtractor;

/**
 * Service to extract source models from a given input.
 */
public class MoDiscoSoftwareModelExtractor implements SoftwareModelExtractor {

    private static final String EXTRACTOR_ID = "MoDiscoSoftwareModelExtractor";
    private static final String EXTRACTOR_LABEL = "MoDisco Java Software Model Extractor";
    private Logger logger = Logger.getLogger(MoDiscoSoftwareModelExtractor.class);

    @Override
    public ResourceSet extractSoftwareModel(List<URI> projectPaths, IProgressMonitor monitor, URI targetURI)
            throws SoftwareModelExtractionException {

        URI fullTargetURI = targetURI.appendSegment("modisco_java2kdm.xmi");

        IJavaProject mainProject = null;
        try {
            mainProject = getJavaProject(projectPaths.get(0).lastSegment());
        } catch (CoreException ce) {
            logger.error("Failed to load main project", ce);
            throw new SoftwareModelExtractionException("Failed to load main project", ce);
        }

        List<IJavaProject> additionalProjects = new ArrayList<IJavaProject>();
        List<String> additionalProjectNames = new ArrayList<String>();
        for (int i = 1; i < projectPaths.size(); i++) {
            try {
                additionalProjects.add(getJavaProject(projectPaths.get(i).lastSegment()));
                additionalProjectNames.add(projectPaths.get(i).lastSegment());
            } catch (CoreException ce) {
                throw new SoftwareModelExtractionException("Failed to load java project", ce);
            }
        }

        ResourceSet modelResourceSet = null;
        try {
            modelResourceSet = extractProject(mainProject, additionalProjects, monitor, fullTargetURI);
        } catch (Exception e) {
            throw new SoftwareModelExtractionException("Failed to run extraction", e);
        }
        return modelResourceSet;
    }

    /**
     * Get the JavaProject for a specific project name.
     * 
     * @param projectName
     *            The name to search for.
     * @return The Identified project if a java one is found. Null otherwise.
     * @throws CoreException
     *             Identifies that the project's nature could not be checked.
     */
    private IJavaProject getJavaProject(String projectName) throws CoreException {
        IJavaProject javaProject = null;
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IProject project = workspace.getRoot().getProject(projectName);
        if (project.hasNature(JavaCore.NATURE_ID)) {
            javaProject = JavaCore.create(project);
        }
        return javaProject;
    }

    /**
     * Extract the source model of a list of java projects. One project is the main project while a
     * list of additional projects to analyze can be specified. The reason for one main project is,
     * that this one is used for example for the naming of the root inventory produced etc.
     * 
     * @param mainProject
     *            The project to extract.
     * @param additionalProjects
     *            Projects that should be analyzed in addition to the main one.
     * @param monitor
     *            The monitor to report the progress to.
     * @param targetURI
     *            The target uri to store the model to.
     * @return The resource set of the extracted project.
     * @throws Exception
     *             Identifies the extraction was not successful.
     */
    public ResourceSet extractProject(IJavaProject mainProject, List<IJavaProject> additionalProjects,
            IProgressMonitor monitor, URI targetURI) throws Exception {
        DiscoverKDMSourceAndJavaModelFromJavaProject discoverer = new DiscoverKDMSourceAndJavaModelFromJavaProject();
        discoverer.setSerializeTarget(true);
        discoverer.setTargetURI(targetURI);
        ElementsToAnalyze elementsToAnalyze = new ElementsToAnalyze(mainProject);
        for (IJavaProject additionalProject : additionalProjects) {
            elementsToAnalyze.addElementToDiscover(additionalProject);
        }
        discoverer.setElementsToAnalyze(elementsToAnalyze);

        discoverer.discoverElement(mainProject, monitor);

        Resource javaApplicationModel = discoverer.getTargetModel();
        return javaApplicationModel.getResourceSet();
    }

    @Override
    public String getId() {
        return EXTRACTOR_ID;
    }

    @Override
    public String getLabel() {
        return EXTRACTOR_LABEL;
    }
}
