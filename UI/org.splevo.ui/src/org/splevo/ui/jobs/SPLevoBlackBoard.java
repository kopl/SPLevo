/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.ui.jobs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.splevo.ui.refinementbrowser.RefinementModelProvider;
import org.splevo.ui.views.vpmgraph.VPMGraphProvider;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementModel;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;

/**
 * The SPLevoBlackBoard providing access to the models to be processed.
 */
public class SPLevoBlackBoard extends Blackboard<Object> implements VPMGraphProvider, RefinementModelProvider {

	/** The resources of the leading variant. */
	private final ResourceSet resourceSetLeading = new ResourceSetImpl();

	/** The resource set of the integration variant. */
	private final ResourceSet resourceSetIntegration = new ResourceSetImpl();

    /** The diffing model. */
    private Comparison diffModel = null;

    /** The variation point model. */
    private VariationPointModel variationPointModel = null;

    /** The variation point graph to be analyzed. */
    private VPMGraph vpmGraph = null;

    /** The result list of applied VPMAnalyzers. */
    private final List<VPMAnalyzerResult> vpmAnalyzerResults = new ArrayList<VPMAnalyzerResult>();

    /** The map of analyzer - refinements pairs. */
    private final List<Refinement> analysisResults = new ArrayList<Refinement>();

    /** The refinement model representing the analysis results. */
    private RefinementModel refinementModel = null;

    /** The refinements to apply to the vpm model. */
    private final List<Refinement> refinementsToApply = new ArrayList<Refinement>();

    /**
     * Get the leading variant resources.
     * The returned ResourceSet can be modified but not be replaced.
     *
     * @return The current ResourceSet (never null).
     */
	public ResourceSet getResourceSetLeading() {
		return resourceSetLeading;
	}

    /**
     * Get the integration variant resources.
     * The returned ResourceSet can be modified but not be replaced.
     *
     * @return The current ResourceSet (never null).
     */
	public ResourceSet getResourceSetIntegration() {
		return resourceSetIntegration;
	}

    /**
     * Gets the diffing model.
     *
     * @return the diffModel
     */
    public Comparison getDiffModel() {
        return diffModel;
    }

    /**
     * Sets the diffing model.
     *
     * @param diffModel
     *            the diffModel to set
     */
    public void setDiffModel(Comparison diffModel) {
        this.diffModel = diffModel;
    }

    /**
     * Gets the variation point model.
     *
     * @return the variationPointModel
     */
    public VariationPointModel getVariationPointModel() {
        return variationPointModel;
    }

    /**
     * Sets the variation point model.
     *
     * @param variationPointModel
     *            the variationPointModel to set
     */
    public void setVariationPointModel(VariationPointModel variationPointModel) {
        this.variationPointModel = variationPointModel;
    }

    /**
     * Gets the map of analyzer - refinements pairs.
     *
     * @return the analysis results
     */
    public List<Refinement> getAnalysisResults() {
        return analysisResults;
    }

    /**
     * Gets the refinements to apply to the vpm model.
     *
     * @return the refinementsToApply
     */
    public List<Refinement> getRefinementsToApply() {
        return refinementsToApply;
    }

    @Override
    public RefinementModel getRefinementModel() {
        return refinementModel;
    }

    /**
     * Sets the refinement model representing the analysis results.
     *
     * @param refinementModel
     *            the refinementModel to set
     */
    public void setRefinementModel(RefinementModel refinementModel) {
        this.refinementModel = refinementModel;
    }

    @Override
    public VPMGraph getVpmGraph() {
        return vpmGraph;
    }

    /**
     * Sets the variation point graph to be analyzed.
     *
     * @param vpmGraph
     *            the new variation point graph to be analyzed
     */
    public void setVpmGraph(VPMGraph vpmGraph) {
        this.vpmGraph = vpmGraph;
    }

    /**
     * Gets the result list of applied VPMAnalyzers.
     *
     * @return the vpmAnalyzerResults
     */
    public List<VPMAnalyzerResult> getVpmAnalyzerResults() {
        return vpmAnalyzerResults;
    }

}
