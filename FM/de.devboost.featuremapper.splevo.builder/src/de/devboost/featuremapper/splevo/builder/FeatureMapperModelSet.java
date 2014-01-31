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

public class FeatureMapperModelSet {

	private FeatureMappingModel mappingModel;

	private FeatureModel featureModel;

	public FeatureMapperModelSet(FeatureModel featureModel,
			FeatureMappingModel mappingModel) {
		super();
		this.featureModel = featureModel;
		this.mappingModel = mappingModel;
	}

	public FeatureMappingModel getMappingModel() {
		return mappingModel;
	}

	public FeatureModel getFeatureModel() {
		return featureModel;
	}

}
