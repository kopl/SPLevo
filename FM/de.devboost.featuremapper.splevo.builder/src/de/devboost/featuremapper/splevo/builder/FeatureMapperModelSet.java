/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Wende (DevBoost GmbH) - initial API and implementation and/or initial documentation
 *******************************************************************************/

package de.devboost.featuremapper.splevo.builder;

import org.featuremapper.models.feature.FeatureModel;
import org.featuremapper.models.featuremapping.FeatureMappingModel;

/**
 * A container class for all models representing a complete FeatureMapper model.
 */
public class FeatureMapperModelSet {

    private FeatureMappingModel mappingModel;
    private FeatureModel featureModel;

    /**
     * Constructor for immediate wrapper initialization.
     *
     * @param featureModel
     *            The model specifying the feature themselves.
     * @param mappingModel
     *            The model mapping the features to their implementing code.
     */
    public FeatureMapperModelSet(FeatureModel featureModel, FeatureMappingModel mappingModel) {
        super();
        this.featureModel = featureModel;
        this.mappingModel = mappingModel;
    }

    /**
     * Get the model mapping features to their implementation.
     *
     * @return The wrapped mapping model.
     */
    public FeatureMappingModel getMappingModel() {
        return mappingModel;
    }

    /**
     * Get the model specifying the features themselves.
     *
     * @return The feature describing model.
     */
    public FeatureModel getFeatureModel() {
        return featureModel;
    }

}
