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
 *******************************************************************************/
package de.devboost.featuremapper.splevo.builder.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.featuremapper.models.feature.Feature;
import org.featuremapper.models.feature.FeatureModel;
import org.featuremapper.models.feature.Group;
import org.featuremapper.models.featuremapping.ElementMapping;
import org.featuremapper.models.featuremapping.FeatureMappingModel;
import org.featuremapper.models.featuremapping.FeatureRef;
import org.featuremapper.models.featuremapping.Mapping;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityFactory;

import de.devboost.featuremapper.splevo.builder.FeatureMapperModelSet;
import de.devboost.featuremapper.splevo.builder.VPM2FeatureMapperBuilder;

/**
 * Test to verify the functionality of the builder deriving a feature model from
 * a variation point model.
 */
public class VPM2FeatureMapperBuilderTest {

	/** The logger for this test class. */
	@SuppressWarnings("unused")
	private Logger logger = Logger
			.getLogger(VPM2FeatureMapperBuilderTest.class);
	private Map<String, Commentable> variantToEntityMap = new HashMap<String, Commentable>();

	/**
	 * Prepare the test. Initializes a log4j logging environment.
	 */
	@BeforeClass
	public static void setUp() {
		// set up a basic logging configuration for the test environment
		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure(new ConsoleAppender(new PatternLayout(
				"%m%n")));
	}

	/**
	 * Basic test to create a vpm and derive a feature model.
	 * 
	 * @throws Exception
	 *             Failed to read the variation point model.
	 */
	@Test
	public void testBuildFeatureModel() throws Exception {

		// VariationPointModel vpm = SPLevoTestUtil.loadGCDVPMModel();
		VariationPointModel vpm = getTestVPM();

		VPM2FeatureMapperBuilder builder = new VPM2FeatureMapperBuilder();
		FeatureMapperModelSet fms = builder.buildFeatureMapperModels(vpm,
				"RootFeature");

		FeatureModel featureModel = fms.getFeatureModel();
		validateGeneratedFeatureModel(featureModel);
		FeatureMappingModel mappingModel = fms.getMappingModel();
		validateGeneratedMappingModel(mappingModel, featureModel);
	}

	private void validateGeneratedMappingModel(
			FeatureMappingModel mappingModel, FeatureModel featureModel) {
		assertNotNull("No Mapping Model Created", mappingModel);
		assertEquals("Wrong feature model set for mapping model.",
				featureModel, mappingModel.getFeatureModel().getValue());
		assertEquals("Wrong number of mappings", mappingModel.getMappings()
				.size(), 6);
		for (Mapping mapping : mappingModel.getMappings()) {
			assertTrue("Expected element mappings",
					mapping instanceof ElementMapping);
			ElementMapping em = (ElementMapping) mapping;
			assertTrue(em.getTerm() instanceof FeatureRef);
			FeatureRef featureRef = (FeatureRef) em.getTerm();
			String featureName = featureRef.getFeature().getName();
			assertTrue(variantToEntityMap.containsKey(featureName));
			Commentable mappedElement = variantToEntityMap.get(featureName);
			assertEquals(mappedElement, em.getElement());
		}
	}

	private void validateGeneratedFeatureModel(FeatureModel fm) {
		assertNotNull("No Feature Model Created", fm);
		assertNotNull("No root feature created", fm.getRoot());
		assertEquals("Wrong number of top level features", 3, fm.getRoot()
				.getGroups().size());
		for (Group group : fm.getRoot().getGroups()) {
			assertEquals(
					"Wrong number of child features for group of variation point group features: "
							+ group, group.getChildFeatures().size(), 1);
			Feature variationParent = group.getChildFeatures().get(0);
			assertEquals(
					"Wrong number of child groups for variation point group features",
					1, variationParent.getGroups().size());
			Group variationChildGroup = variationParent.getGroups().get(0);
			assertEquals(
					"Wrong number of child features for group of variation point features: "
							+ variationChildGroup, variationChildGroup
							.getChildFeatures().size(), 2);

		}
	}

	/**
	 * Creates a test variation point model with 3 variation points and 2
	 * variants each.
	 */
	private VariationPointModel getTestVPM() {
		variabilityFactory factory = variabilityFactory.eINSTANCE;
		VariationPointModel vpm;
		vpm = factory.createVariationPointModel();
		for (int i = 0; i < 3; i++) {
			String featurename = "Feature_" + i;

			VariationPointGroup vpg = factory.createVariationPointGroup();
			vpm.getVariationPointGroups().add(vpg);

			EList<VariationPoint> variationPoints = vpg.getVariationPoints();
			VariationPoint vpa = factory.createVariationPoint();
			variationPoints.add(vpa);

			Variant variantA = createVariantWithId(factory, featurename, "A");
			vpa.getVariants().add(variantA);

			Variant variantB = createVariantWithId(factory, featurename, "B");
			vpa.getVariants().add(variantB);

		}
		return vpm;
	}

	private Variant createVariantWithId(variabilityFactory factory,
			String featurename, String variantID) {
		Variant variantA = factory.createVariant();
		String variantName = featurename + "_V_" + variantID;
		variantA.setVariantId(variantName);
		JaMoPPSoftwareElement softwareEntity = softwareFactory.eINSTANCE
				.createJaMoPPSoftwareElement();
		Class jamoppClass = ClassifiersFactory.eINSTANCE.createClass();
		jamoppClass.setName("Class_" + variantName);
		softwareEntity.setJamoppElement(jamoppClass);
		variantA.getSoftwareEntities().add(softwareEntity);
		variantToEntityMap.put(variantA.getVariantId(), jamoppClass);
		return variantA;
	}
}
