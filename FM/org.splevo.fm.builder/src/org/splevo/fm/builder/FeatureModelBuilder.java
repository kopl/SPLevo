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
 *    Daniel Kojic - adapted for usage with the feature model wrapper
 *******************************************************************************/
package org.splevo.fm.builder;

import org.splevo.vpm.variability.VariationPointModel;

/**
 * Interface to build a specific feature model from a provided Comparison model.
 *
 * @param <Model>
 *            The specific type of the feature model, the builder is able to produce.
 */
public interface FeatureModelBuilder<Model> {

    /**
     * Get the identifier of this builder. This should be unique in your installation.
     *
     * @return The string identifying the builder.
     */
    public String getId();

    /**
     * Get a human readable label for the builder.
     *
     * @return The label.
     */
    public String getLabel();

    /**
     * Build a feature model according to the specified model type of the concrete builder.
     *
     * @param vpm
     *            The variation point model to derive the feature model from.
     *
     * @param rootFeatureName
     *            The name of the feature models root element.
     * @return A {@link FeatureModelWrapper} containing the prepared feature model.
     */
    public FeatureModelWrapper<Model> build(VariationPointModel vpm, String rootFeatureName);

    /**
     * Save a feature model of the builder's specific model type.
     *
     * @param featureModel
     *            The model to save.
     * @param targetDirectory
     *            The path to the directory to save to.
     */
    public void save(Model featureModel, String targetDirectory);
}
