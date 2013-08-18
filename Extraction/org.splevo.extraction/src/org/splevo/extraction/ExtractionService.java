package org.splevo.extraction;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.modisco.java.composition.discoverer.DiscoverKDMSourceAndJavaModelFromJavaProject;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.eclipse.modisco.java.discoverer.ElementsToAnalyze;

/**
 * Service to extract source models from a given input.
 * 
 */
public class ExtractionService {

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
     * @return The reference to the data model.
     * @throws Exception
     *             Identifies the extraction was not successful.
     */
    public JavaApplication extractProject(IJavaProject mainProject, List<IJavaProject> additionalProjects,
            IProgressMonitor monitor, URI targetURI) throws Exception {
        DiscoverKDMSourceAndJavaModelFromJavaProject discoverer = new DiscoverKDMSourceAndJavaModelFromJavaProject();
        discoverer.setSerializeTarget(true);
        discoverer.setTargetURI(targetURI);
        discoverer.discoverElement(mainProject, monitor);
        ElementsToAnalyze elementsToAnalyze = new ElementsToAnalyze(mainProject);
        for (IJavaProject additionalProject : additionalProjects) {
            elementsToAnalyze.addElementToDiscover(additionalProject);
        }
        discoverer.setElementsToAnalyze(elementsToAnalyze);
        Resource javaApplicationModel = discoverer.getTargetModel();
        return (JavaApplication) javaApplicationModel.getContents().get(0);
    }
}
