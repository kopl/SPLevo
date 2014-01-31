package de.devboost.featuremapper.splevo.builder;

import org.eclipse.emf.common.util.EList;
import org.featuremapper.models.feature.Feature;
import org.featuremapper.models.feature.FeatureFactory;
import org.featuremapper.models.feature.FeatureModel;
import org.featuremapper.models.feature.Group;
import org.featuremapper.models.featuremapping.ElementMapping;
import org.featuremapper.models.featuremapping.FeatureMappingFactory;
import org.featuremapper.models.featuremapping.FeatureMappingModel;
import org.featuremapper.models.featuremapping.FeatureModelRef;
import org.featuremapper.models.featuremapping.FeatureRef;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * A FeatureModel builder that derives the FeatureMapper FM from an existing
 * variation point model (VPM).
 * 
 */
public class VPM2FeatureMapperBuilder {

	/**
	 * Build a feature model and a mapping model based on variation point model.
	 * 
	 * The strategy used is very simple and builds one feature per
	 * VariationPoint and one ChildFeature per Variant. The connection is always
	 * alternative (OR).
	 * 
	 * In addition a corresponding mapping model is created.
	 * 
	 * @param vpm
	 *            The variation point model to make use of.
	 * @param rootFeatureName
	 *            The name of the feature models root element.
	 * @return The prepared feature model and mapping model.
	 */
	public FeatureMapperModelSet buildFeatureMapperModels(
			VariationPointModel vpm, String rootFeatureName) {

		FeatureModel featureModel = initFeatureModel(vpm, rootFeatureName);

		FeatureMappingFactory mappingFactory = FeatureMappingFactory.eINSTANCE;
		FeatureMappingModel mappingModel = mappingFactory
				.createFeatureMappingModel();

		FeatureModelRef fmreference = mappingFactory.createFeatureModelRef();
		mappingModel.setFeatureModel(fmreference);
		fmreference.setValue(featureModel);

		for (VariationPointGroup variationPointGroup : vpm
				.getVariationPointGroups()) {

			Feature vpFeature = createVariationPointGroupFeature(
					variationPointGroup, featureModel.getRoot());

			Group group = createMandatoryFeatureGroup();
			vpFeature.getGroups().add(group);

			for (VariationPoint variationPoint : variationPointGroup
					.getVariationPoints()) {
				for (Variant variant : variationPoint.getVariants()) {
					Feature vFeature = createVariantFeature(variant
							.getVariantId());
					group.getChildFeatures().add(vFeature);

					EList<SoftwareElement> softwareEntities = variant
							.getSoftwareEntities();
					for (SoftwareElement softwareElement : softwareEntities) {
						ElementMapping mapping = mappingFactory
								.createElementMapping();
						FeatureRef featureRef = mappingFactory
								.createFeatureRef();
						featureRef.setFeature(vFeature);
						mapping.setTerm(featureRef);
						
						// TODO CW add solution space model to mapping model
						
						// TODO BK, CM we need access to the actually source code
						// EObject element here,
						// I know this cast is really bad,
						mapping.setElement(((JaMoPPSoftwareElement) softwareElement)
								.getJamoppElement());
						mappingModel.getMappings().add(mapping);
					}

				}
			}

		}
		return new FeatureMapperModelSet(featureModel, mappingModel);
	}

	/**
	 * Create a feature for a supplied variant.
	 * 
	 * @param variantId
	 *            The variant to create the feature for.
	 * @return The prepared feature
	 */
	private Feature createVariantFeature(String variantId) {

		Feature feature = FeatureFactory.eINSTANCE.createFeature();
		feature.setName(variantId);
		// feature.setId(EcoreUtil.generateUUID());

		return feature;
	}

	/**
	 * Create a feature for a variation point and attach it with a mandatory
	 * group to the supplied root.
	 * 
	 * @param variationPointGroup
	 *            The variation point to create the feature for.
	 * @param parentFeature
	 *            The feature to attach the group to.
	 * @return The new feature element.
	 */
	private Feature createVariationPointGroupFeature(
			VariationPointGroup variationPointGroup, Feature parentFeature) {
		Group group = createMandatoryFeatureGroup();
		parentFeature.getGroups().add(group);

		Feature feature = FeatureFactory.eINSTANCE.createFeature();
		feature.setName(variationPointGroup.getGroupId());
		// feature.setId(EcoreUtil.generateUUID());
		group.getChildFeatures().add(feature);

		return feature;
	}

	/**
	 * Create a mandatory / OR feature group.
	 * 
	 * @return The instantiated and configured feature group.
	 */
	private Group createMandatoryFeatureGroup() {
		Group group = FeatureFactory.eINSTANCE.createGroup();
		// group.setId(EcoreUtil.generateUUID());
		group.setMinCardinality(1);
		group.setMaxCardinality(1);
		return group;
	}

	/**
	 * Initialize the feature model itself.
	 * 
	 * @param vpm
	 *            The vpm to init the fm for.
	 * @param rootFeatureName
	 *            The name of the root feature.
	 * @return The initialized fm.
	 */
	private FeatureModel initFeatureModel(VariationPointModel vpm,
			String rootFeatureName) {
		FeatureModel fm = FeatureFactory.eINSTANCE.createFeatureModel();
		// fm.setId(EcoreUtil.generateUUID());
		// fm.setVersion("1.0");
		Feature rootFeature = FeatureFactory.eINSTANCE.createFeature();
		rootFeature.setName(rootFeatureName);
		// rootFeature.setId(EcoreUtil.generateUUID());
		fm.setRoot(rootFeature);
		return fm;
	}

}
