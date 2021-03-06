package de.devboost.featuremapper.splevo.builder.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @generated
 */
public class FeatureMapperModelsBuilderTest {

	protected FeatureMapperBuilderTestSupport featureMapperBuilderTestSupport = null;

	@Test
	public void executeScript() throws Exception {
		// The code in this method is generated from: /de.devboost.featuremapper.splevo.builder.test/src/de/devboost/featuremapper/splevo/builder/test/FeatureMapperModelsBuilderTest.natspec
		// Never change this method or any contents of this file, all local changes will be overwritten.
		// Change _NatSpecTemplate.java instead.
		
		// Objective of FeatureMapper Models Builder:
		featureMapperBuilderTestSupport.matchAllPattern(java.util.Arrays.asList(new String[] {"Objective", "of", "FeatureMapper", "Models", "Builder:"}));
		// The FeatureMapper Models Builder transforms a given SPLevo
		featureMapperBuilderTestSupport.matchAllPattern(java.util.Arrays.asList(new String[] {"The", "FeatureMapper", "Models", "Builder", "transforms", "a", "given", "SPLevo"}));
		// Variation Point Model to a feature model and a
		featureMapperBuilderTestSupport.matchAllPattern(java.util.Arrays.asList(new String[] {"Variation", "Point", "Model", "to", "a", "feature", "model", "and", "a"}));
		// mapping model for FeatureMapper
		featureMapperBuilderTestSupport.matchAllPattern(java.util.Arrays.asList(new String[] {"mapping", "model", "for", "FeatureMapper"}));
		// If there is for example a variation point model named VPM1
		org.splevo.vpm.variability.VariationPointModel variationPointModel_VPM1 = featureMapperBuilderTestSupport.forAnExampleVariationPointModelNamed("VPM1");
		// With a variation point PointP
		org.splevo.vpm.variability.VariationPoint variationPoint_PointP = featureMapperBuilderTestSupport.withAVariationPointGroupThatHasVariationsAnd("PointP", variationPointModel_VPM1);
		// That has a variant A mapped to class ExampleClassA in resource ClassA_Resource
		featureMapperBuilderTestSupport.thatHasVariationMappedToClass("A", "ExampleClassA", "ClassA_Resource", variationPoint_PointP);
		// That has a variant B mapped to class ExampleClassB in resource ClassB_Resource
		featureMapperBuilderTestSupport.thatHasVariationMappedToClass("B", "ExampleClassB", "ClassB_Resource", variationPoint_PointP);
		// With a variation point PointP2
		org.splevo.vpm.variability.VariationPoint variationPoint_PointP2 = featureMapperBuilderTestSupport.withAVariationPointGroupThatHasVariationsAnd("PointP2", variationPointModel_VPM1);
		// That has a variant C mapped to class ExampleClassC in resource ClassC_Resource
		featureMapperBuilderTestSupport.thatHasVariationMappedToClass("C", "ExampleClassC", "ClassC_Resource", variationPoint_PointP2);
		// Then the FeatureMapper Models Builder generates
		de.devboost.featuremapper.splevo.builder.FeatureMapperModelSet featureMapperModelSet_ = featureMapperBuilderTestSupport.theModelsBuilderWillGenerate(variationPointModel_VPM1);
		// A Feature Model
		org.featuremapper.models.feature.FeatureModel featureModel_ = featureMapperBuilderTestSupport.aFeatureModel(featureMapperModelSet_);
		// With a root feature RootFeature
		org.featuremapper.models.feature.Feature feature_RootFeature = featureMapperBuilderTestSupport.withARootFeature("RootFeature", featureModel_);
		// With 2 child features named PointP and PointP2
		featureMapperBuilderTestSupport.withChildFeaturesNamed(2, java.util.Arrays.asList(new java.lang.String[] {"PointP", "and", "PointP2"}), feature_RootFeature);
		// With feature PointP that contains 2 child features named PointP_A and PointP_B
		featureMapperBuilderTestSupport.withFeatureThatContainsChildFeaturesNamedAnd("PointP", 2, java.util.Arrays.asList(new java.lang.String[] {"PointP_A", "and", "PointP_B"}), featureModel_);
		// With feature PointP2 that contains 0 children
		featureMapperBuilderTestSupport.withChildFeaturesNamed("PointP2", featureModel_);
		// A Mapping Model
		org.featuremapper.models.featuremapping.FeatureMappingModel featureMappingModel_ = featureMapperBuilderTestSupport.MappingModel(featureMapperModelSet_);
		// With solution space model ClassA_Resource
		featureMapperBuilderTestSupport.withSolutionSpaceModel("ClassA_Resource", featureMappingModel_);
		// With solution space model ClassB_Resource
		featureMapperBuilderTestSupport.withSolutionSpaceModel("ClassB_Resource", featureMappingModel_);
		// With a mapping of feature PointP_A to ExampleClassA
		featureMapperBuilderTestSupport.withAMappingOfFeatureTo("PointP_A", "ExampleClassA", featureMappingModel_);
		// With a mapping of feature PointP_B to ExampleClassB
		featureMapperBuilderTestSupport.withAMappingOfFeatureTo("PointP_B", "ExampleClassB", featureMappingModel_);
		
	}

	@Before
	public void setUp() {
		featureMapperBuilderTestSupport = new FeatureMapperBuilderTestSupport();
	}

	@After
	public void shutdown() {
	}
}