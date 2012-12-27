package org.splevo.ui.jobs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.featuremodel.FeatureModel;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementModel;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.Blackboard;

/**
 * The SPLevoBlackBoard providing access to the models to be processed.
 * 
 */
public class SPLevoBlackBoard extends Blackboard<Object> {

    /** The leading source model. */
    private JavaApplication sourceModelLeading = null;

    /** The integration source model. */
    private JavaApplication sourceModelIntegration = null;

    /** The diffing model. */
    private DiffModel diffModel = null;

    /** The variation point model. */
    private VariationPointModel variationPointModel = null;
    
    /** The variation point graph to be analyzed. */
    private VPMGraph vpmGraph = null;

    /** The feature model. */
    private FeatureModel featureModel = null;

    /** The map of analyzer - refinements pairs. */
    private List<Refinement> analysisResults = new ArrayList<Refinement>();

    /** The refinement model representing the analysis results. */
    private RefinementModel refinementModel = null;

    /** The refinements to apply to the vpm model. */
    private List<Refinement> refinementsToApply = new ArrayList<Refinement>();

    /**
     * @return the featureModel
     */
    public FeatureModel getFeatureModel() {
        return featureModel;
    }

    /**
     * @param featureModel
     *            the featureModel to set
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
     * @param sourceModelLeading
     *            the sourceModelLeading to set
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
     * @param sourceModelIntegration
     *            the sourceModelIntegration to set
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
     * @param diffModel
     *            the diffModel to set
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
     * @param variationPointModel
     *            the variationPointModel to set
     */
    public void setVariationPointModel(VariationPointModel variationPointModel) {
        this.variationPointModel = variationPointModel;
    }

    /**
     * @return the analysis results
     */
    public List<Refinement> getAnalysisResults() {
        return analysisResults;
    }

    /**
     * @return the refinementsToApply
     */
    public List<Refinement> getRefinementsToApply() {
        return refinementsToApply;
    }

    /**
     * @return the refinementModel
     */
    public RefinementModel getRefinementModel() {
        return refinementModel;
    }

    /**
     * @param refinementModel
     *            the refinementModel to set
     */
    public void setRefinementModel(RefinementModel refinementModel) {
        this.refinementModel = refinementModel;
    }

    /**
     * @return the vpmGraph
     */
    public VPMGraph getVpmGraph() {
        return vpmGraph;
    }

    /**
     * @param vpmGraph the vpmGraph to set
     */
    public void setVpmGraph(VPMGraph vpmGraph) {
        this.vpmGraph = vpmGraph;
    }

}
