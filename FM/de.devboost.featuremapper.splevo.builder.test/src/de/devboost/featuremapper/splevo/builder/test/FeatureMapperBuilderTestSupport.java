package de.devboost.featuremapper.splevo.builder.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.featuremapper.models.feature.Feature;
import org.featuremapper.models.feature.FeatureModel;
import org.featuremapper.models.featuremapping.ElementMapping;
import org.featuremapper.models.featuremapping.FeatureMappingModel;
import org.featuremapper.models.featuremapping.FeatureRef;
import org.featuremapper.models.featuremapping.Mapping;
import org.featuremapper.models.featuremapping.SolutionModelRef;
import org.splevo.fm.builder.FeatureModelWrapper;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityFactory;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import de.devboost.featuremapper.splevo.builder.FeatureMapperModelSet;
import de.devboost.featuremapper.splevo.builder.FeatureMapperModelsBuilder;
import de.devboost.natspec.annotations.TextSyntax;

// CHECKSTYLE:OFF
public class FeatureMapperBuilderTestSupport {

	private variabilityFactory factory = variabilityFactory.eINSTANCE;

	@TextSyntax("#1")
	public void matchAllPattern(List<String> words) {
		System.out.println(Joiner.on(" ").join(words));
	}

	@TextSyntax("If there is for example a variation point model named #1")
	public VariationPointModel forAnExampleVariationPointModelNamed(String VPM1) {
		VariationPointModel vpm;
		vpm = factory.createVariationPointModel();
		return vpm;
	}

	@TextSyntax("With a variation point #1")
	public VariationPoint withAVariationPointGroupThatHasVariationsAnd(
			String pointName, VariationPointModel vpm) {
		VariationPointGroup vpg = factory.createVariationPointGroup();
		vpg.setName(pointName);
		vpm.getVariationPointGroups().add(vpg);

		EList<VariationPoint> variationPoints = vpg.getVariationPoints();
		VariationPoint vpa = factory.createVariationPoint();
		variationPoints.add(vpa);
		return vpa;
	}

	@TextSyntax("That has a variant #1 mapped to class #2 in resource #3")
	public void thatHasVariationMappedToClass(String variantName,
			String exampleClassName, String resourceName, VariationPoint point) {
		Variant aVariant = factory.createVariant();
		aVariant.setId(variantName);
		JaMoPPSoftwareElement softwareEntity = softwareFactory.eINSTANCE
				.createJaMoPPSoftwareElement();
		Class jamoppClass = ClassifiersFactory.eINSTANCE.createClass();
		jamoppClass.setName(exampleClassName);
		Resource res = new ResourceImpl();
		URI uri = URI.createURI(resourceName);
		res.setURI(uri);
		res.getContents().add(jamoppClass);
		softwareEntity.setJamoppElement(jamoppClass);
		aVariant.getImplementingElements().add(softwareEntity);
		// variantToEntityMap.put(variantA.getVariantId(), jamoppClass);
		point.getVariants().add(aVariant);
	}

	@TextSyntax("Then the FeatureMapper Models Builder generates")
	public FeatureMapperModelSet theModelsBuilderWillGenerate(
			VariationPointModel vpm) {
		FeatureMapperModelsBuilder builder = new FeatureMapperModelsBuilder();
		FeatureModelWrapper<FeatureMapperModelSet> fmWrapper = builder.build(vpm, "RootFeature");
		FeatureMapperModelSet featureModelSet = fmWrapper.getModel();
		return featureModelSet;
	}

	@TextSyntax("A Feature Model")
	public FeatureModel aFeatureModel(FeatureMapperModelSet set) {
		FeatureModel featureModel = set.getFeatureModel();
		assertNotNull(featureModel);
		return featureModel;
	}

	@TextSyntax("A Mapping Model")
	public FeatureMappingModel MappingModel(FeatureMapperModelSet set) {
		FeatureMappingModel mappingModel = set.getMappingModel();
		assertNotNull(mappingModel);
		return mappingModel;
	}

	@TextSyntax("With a root feature #1")
	public Feature withARootFeature(String rootFeatureName, FeatureModel model) {
		String foundName = model.getRoot().getName();
		assertEquals(rootFeatureName, foundName);
		return model.getRoot();
	}

	@TextSyntax("With #1 child features named #2")
	public void withChildFeaturesNamed(int childSize, List<String> childNames,
			Feature feature) {
		Iterable<String> childNamesFiltered = Splitter.on(" and ").split(
				Joiner.on(" ").join(childNames));
		EList<Feature> childFeatures = feature.getGroups().get(0)
				.getChildFeatures();
		assertEquals(childSize, childFeatures.size());
		List<String> foundChilds = new LinkedList<String>();
		for (Feature f : childFeatures) {
			String name = f.getName();
			foundChilds.add(name);
		}
		for (String expectedChild : childNamesFiltered) {
			assertTrue("Expected child " + expectedChild,
					foundChilds.contains(expectedChild));
		}
	}

	@TextSyntax("With feature #1 that contains #2 child features named #3")
	public void withFeatureThatContainsChildFeaturesNamedAnd(
			String featurename, int childSize, List<String> childNames,
			FeatureModel model) {
		EList<Feature> allFeatures = model.getAllFeatures();
		Feature parentFeature = null;
		for (Feature feature : allFeatures) {
			if (feature.getName().equals(featurename)) {
				parentFeature = feature;
				break;
			}
		}
		assertNotNull(parentFeature);
		withChildFeaturesNamed(childSize, childNames, parentFeature);
	}

	@TextSyntax("With a mapping of feature #1 to #2")
	public void withAMappingOfFeatureTo(String featureName,
			String artifactName, FeatureMappingModel mappingModel) {
		for (Mapping mapping : mappingModel.getMappings()) {
			if (!(mapping instanceof ElementMapping)) {
				continue;
			}
			ElementMapping em = (ElementMapping) mapping;
			assertTrue(em.getTerm() instanceof FeatureRef);
			FeatureRef featureRef = (FeatureRef) em.getTerm();
			String featureNameFound = featureRef.getFeature().getName();
			if (featureNameFound.equals(featureName)) {
				EObject element = em.getElement();
				assertEquals(artifactName, ((Class) element).getName());
				return;
			}
		}
		fail("No mapping found for feature " + featureName);
	}

	@TextSyntax("With solution space model #1")
	public void withSolutionSpaceModel(String resourceName,
			FeatureMappingModel model) {
		EList<SolutionModelRef> solutionModels = model.getSolutionModels();
		assertTrue("No solution space models set.", solutionModels.size() > 0);
		for (SolutionModelRef solutionModelRef : solutionModels) {
			Class jamoppClass = (Class) solutionModelRef.getValue();
			if (jamoppClass.eResource().getURI().toString().equals(resourceName)) {
				return; // correct resource found.
			}
		}
		fail("Could not find solution space model: " + resourceName);
	}

}
