package org.splevo.extraction;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Service to extract source models from a given input.
 * 
 */
public interface SoftwareModelExtractor {

    /**
     * Extract the source model of a list of java projects. One project is the main project while a
     * list of additional projects to analyze can be specified. The reason for one main project is,
     * that this one is used for example for the naming of the root inventory produced etc.
     * 
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
    public ResourceSet extractSoftwareModel(List<String> projectPaths, IProgressMonitor monitor, String sourceModelPath)
            throws SoftwareModelExtractionException;

    /**
     * Get the internal identifier of the extractor. This should be unique to distinguish it from
     * other extractors loaded.
     * 
     * @return The unique identifier.
     */
    public String getId();

    /**
     * A representative label for the software model extractor.
     * 
     * @return The label to represent to users.
     */
    public String getLabel();

    /**
     * Prepare a resource set for the specific software model under study. This method can be used
     * to register required resource factories etc.
     * 
     * @param resourceSet
     *            The resource set to prepare.
     * @param sourceModelPaths
     *            The paths of the source models covered in this resource set.
     * @param loadLayoutInformation
     *            Flag to activate loading of layout information.
     */
    public void prepareResourceSet(ResourceSet resourceSet, List<String> sourceModelPaths, boolean loadLayoutInformation);
}
