package org.splevo.ui.jobs;

import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.featuremodel.FeatureModel;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.ui.workflow.VPMAnalysisConfiguration;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.Blackboard;

/**
 * The SPLevoBlackBoard providing access to the models to be processed.
 *
 */
public class SPLevoBlackBoard extends Blackboard<Object> {

	/**
	 * @return the refinements
	 */
	public HashMap<VPMAnalysisConfiguration, List<Refinement>> getRefinements() {
		return refinements;
	}

	/** The leading source model. */
	JavaApplication sourceModelLeading = null;

	/** The integration source model. */
	JavaApplication sourceModelIntegration = null;
	
	/** The diffing model. */
	DiffModel diffModel = null;
	
	/** The variation point model. */
	VariationPointModel variationPointModel = null;
	
	/** The feature model. */
	FeatureModel featureModel = null;
	
	/** The map of analyzer - refinements pairs. */
	HashMap<VPMAnalysisConfiguration, List<Refinement>> refinements = new HashMap<VPMAnalysisConfiguration, List<Refinement>>();

	/**
	 * @return the featureModel
	 */
	public FeatureModel getFeatureModel() {
		return featureModel;
	}

	/**
	 * @param featureModel the featureModel to set
	 */
	public void setFeatureModel(FeatureModel featureModel) {
		this.featureModel = featureModel;
	}

	/**
	 * @return the sourceModelLeading
	 */
	public JavaApplication getSourceModelLeading() {
		return sourceModelLeading;
	}

	/**
	 * @param sourceModelLeading the sourceModelLeading to set
	 */
	public void setSourceModelLeading(JavaApplication sourceModelLeading) {
		this.sourceModelLeading = sourceModelLeading;
	}

	/**
	 * @return the sourceModelIntegration
	 */
	public JavaApplication getSourceModelIntegration() {
		return sourceModelIntegration;
	}

	/**
	 * @param sourceModelIntegration the sourceModelIntegration to set
	 */
	public void setSourceModelIntegration(JavaApplication sourceModelIntegration) {
		this.sourceModelIntegration = sourceModelIntegration;
	}

	/**
	 * @return the diffModel
	 */
	public DiffModel getDiffModel() {
		return diffModel;
	}

	/**
	 * @param diffModel the diffModel to set
	 */
	public void setDiffModel(DiffModel diffModel) {
		this.diffModel = diffModel;
	}

	/**
	 * @return the variationPointModel
	 */
	public VariationPointModel getVariationPointModel() {
		return variationPointModel;
	}

	/**
	 * @param variationPointModel the variationPointModel to set
	 */
	public void setVariationPointModel(VariationPointModel variationPointModel) {
		this.variationPointModel = variationPointModel;
	}
	
}
