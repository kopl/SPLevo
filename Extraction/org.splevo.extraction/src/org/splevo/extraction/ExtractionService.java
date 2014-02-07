package org.splevo.extraction;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Service to run a software extraction.
 */
public interface ExtractionService {

    /**
     * Extract the source model of a list of java projects. One project is the main project while a
     * list of additional projects to analyze can be specified. The reason for one main project is,
     * that this one is used for example for the naming of the root inventory produced etc.
     *
     * @param extractorId
     *            The identifier for the extractor to be triggered.
     * @param projectPaths
     *            Source Paths of the projects to be extracted.
     * @param monitor
     *            The monitor to report the progress to.
     * @param sourceModelPath
     *            The absolute path to the directory to store information for extracted source model
     *            in.
     * @return The set of resources containing the extracted model.
     * @throws SoftwareModelExtractionException
     *             Identifies the extraction was not successful.
     */
    public ResourceSet extractSoftwareModel(String extractorId, List<String> projectPaths, IProgressMonitor monitor,
            String sourceModelPath) throws SoftwareModelExtractionException;

    /**
     * Load the software model extractor implementations registered for the according extension
     * point.
     *
     * @return The list of registered software model extractors.
     */
    public Map<String, SoftwareModelExtractor> getSoftwareModelExtractors();

    /**
     * Trigger all registered software model extractors to prepare a resource set according to their
     * needs.
     *
     * @param resourceSet
     *            The resource set to prepare.
     * @param sourceModelPaths
     *            The base directories of the source models.
     */
    public void prepareResourceSet(ResourceSet resourceSet, List<String> sourceModelPaths);
}
