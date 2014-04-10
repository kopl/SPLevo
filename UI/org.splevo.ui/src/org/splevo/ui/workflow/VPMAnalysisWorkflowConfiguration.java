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
package org.splevo.ui.workflow;

import java.util.ArrayList;
import java.util.List;

import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.refinement.DetectionRule;

/**
 * A configuration for a VPM analysis work flow to be executed. A work flow contains a set of VPM
 * analyzes recommending refinements to be performed.
 */
public class VPMAnalysisWorkflowConfiguration extends BasicSPLevoWorkflowConfiguration {

    /** A list of analysis configurations to be executed. */
    private final List<VPMAnalyzer> analyzers = new ArrayList<VPMAnalyzer>();

    /** The presentation mode for the analysis. */
    private ResultPresentation presentation = ResultPresentation.REFINEMENT_BROWSER;

    /** The detection rules to be applied. */
    private final List<DetectionRule> detectionRules = new ArrayList<DetectionRule>();

    /** Option to let the analysis detect related variation points that can be merged. */
    private boolean useMergeDetection = true;

    /**
     * Get the list of analyzer instances to be executed.
     *
     * @return The analyzer instances to execute.
     */
    public List<VPMAnalyzer> getAnalyzers() {
        return analyzers;
    }

    /**
     * Get the presentation mode for the analysis.
     *
     * @return the presentation
     */
    public ResultPresentation getPresentation() {
        return presentation;
    }

    /**
     * Get the presentation mode for the analysis.
     *
     * @param presentation
     *            The presentation mode to set.
     */
    public void setPresentation(ResultPresentation presentation) {
        this.presentation = presentation;
    }

    /**
     * Get the detection rules that should be applied to identify refinements.
     *
     * @return the detectionRules to be applied
     */
    public List<DetectionRule> getDetectionRules() {
        return detectionRules;
    }

    /**
     * @return the useMergeDetection
     */
    public boolean isUseMergeDetection() {
        return useMergeDetection;
    }

    /**
     * @param useMergeDetection
     *            the useMergeDetection to set
     */
    public void setUseMergeDetection(boolean useMergeDetection) {
        this.useMergeDetection = useMergeDetection;
    }

    /**
     * Enumeration specifying the options to present the analysis results.
     *
     * @author Benjamin Klatt
     *
     */
    public enum ResultPresentation {

        /** Option to open the refinement browser. */
        REFINEMENT_BROWSER,

        /** Option to open only the relationship graph. */
        RELATIONSHIP_GRAPH_ONLY
    }
}
