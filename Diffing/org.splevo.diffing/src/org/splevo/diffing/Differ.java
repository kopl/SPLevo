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

import java.net.URI;
import java.util.Map;

import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * A differ encapsulating the technology specific diffing logic. It is capable to select all
 * software model files from a supplied base directory, performs the diffing on it and returns a
 * DiffModel, probably with technology specific diff elements.
 */
public interface Differ {

    /** Initialize the differ and any required resources (e.g. meta models etc.). */
    void init();

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
    Comparison doDiff(URI leadingModelDirectory, URI integrationModelDirectory, Map<String, String> diffingOptions)
            throws DiffingException, DiffingNotSupportedException;

    /**
     * Get the available configurations for a specific differ.
     *
     * All configurations are designed to have string values.<br>
     * The key of the map is the key of the configuration, the value is the default value.
     *
     * @return A map assigning the configurations key to it's default value.
     */
    Map<String, String> getAvailableConfigurations();

    /**
     * Perform the diffing process for two modisco JavaApplicationModels.
     *
     * @param resourceSetLeading
     *            The resource set containing the leading product variant's models.
     * @param resourceSetIntegration
     *            The resource set containing the to be integrated product variant's models.
     * @param diffingOptions
     *            The configuration options for the diffing process.
     * @return The difference model.
     * @throws DiffingException
     *             Identifying that the diffing could not be performed successfully.
     * @throws DiffingNotSupportedException
     *             The differ is not capable to process this input.
     */
    Comparison doDiff(ResourceSet resourceSetLeading, ResourceSet resourceSetIntegration,
            Map<String, String> diffingOptions) throws DiffingException, DiffingNotSupportedException;

    /**
     * Get the identifier of the differ. This should be unique compared to all other loaded differs
     * in the same instance.
     *
     * @return The internal id of the differ.
     */
    String getId();

    /**
     * The id to use for ordering a list of differs. This is a non unique value and two differs with
     * the same order id will be ordered randomly.
     *
     * @return The order id of the integer.
     */
    int getOrderId();

    /**
     * The label of the differ to represent it for the user.
     *
     * @return The representative label.
     */
    String getLabel();
}