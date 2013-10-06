package org.splevo.vpm.analyzer;

import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurations;
import org.splevo.vpm.analyzer.graph.VPMGraph;

/**
 * Analyzer to identify relationships between Variation Points in a variation point model. An
 * analyzer works on a graph representation of a variation point model.
 * 
 * @author Benjamin Klatt
 */
public interface VPMAnalyzer {

    /** Identifier for the vpm analysis log category. */
    String LOG_CATEGORY = "org.splevo.vpm.analyzer.analysislog";
    
    /** The character to use for separating several logging fields. */
    String LOG_SEPARATOR = ",";

    /**
     * Analyze a variation point model graph for relationships between the variation points and add
     * edges for the those identified.
     * 
     * Instead of directly manipulating the graph, a VPMAnalyzer returns a result object containing 
     * descriptors for the identified relationship edges to create. This is done to prevent any
     * race conditions when multiple analyzers are executed in parallel.
     * 
     * @param vpmGraph
     *            The graph to analyze.
     * @return The VPM analyzer result
     */
    public VPMAnalyzerResult analyze(final VPMGraph vpmGraph);

    /**
     * Get the name of the analyzer to be displayed.
     * 
     * @return The name of the analyzer to be presented to the user.
     */
    public String getName();
    
    /**
     * Get the label of the relations created by this analyzer.
     * @return The relationship label.
     */
    public String getRelationshipLabel();
    
    /**
     * Get the available configurations for this VPM Analyzer.
     * @return All available configurations.
     */
    public VPMAnalyzerConfigurations getConfigurations();
}
