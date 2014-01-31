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
