package org.splevo.diffing;

import java.net.URI;
import java.util.Map;

import org.eclipse.emf.compare.diff.metamodel.DiffModel;

/**
 * A differ encapsulating the technology specific diffing logic. It is capable to select all
 * software model files from a supplied base directory, performs the diffing on it and returns a
 * DiffModel, probably with technology specific diff elements.
 */
public interface Differ {

    /**
     * Perform the diffing process for two modisco JavaApplicationModels.
     * 
     * @param leadingModelDirectory
     *            The base directory of the leading variant's software model.
     * @param integrationModelDirectory
     *            The base directory of the leading variant's software model.
     * @param diffingOptions
     *            The configuration options for the diffing process.
     * @return The difference model.
     * @throws DiffingException
     *             Identifying that the diffing could not be performed successfully.
     * @throws DiffingNotSupportedException
     *             The differ is not capable to process this input.
     */
    DiffModel doDiff(URI leadingModelDirectory, URI integrationModelDirectory, Map<String, Object> diffingOptions)
            throws DiffingException, DiffingNotSupportedException;

    /**
     * Get the identifier of the differ. This should be unique compared to all other loaded differs
     * in the same instance.
     * 
     * @return The internal id of the differ.
     */
    String getId();

    /**
     * The label of the differ to represent it for the user.
     * 
     * @return The representative label.
     */
    String getLabel();
}