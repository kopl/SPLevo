package org.splevo.vpm.analyzer;

import java.util.Map;

import org.splevo.vpm.analyzer.graph.VPMGraph;

/**
 * Analyzer to identify relationships between Variation Points in a variation point model. An
 * analyzer works on a graph representation of a variation point model.
 * 
 * @author Benjamin Klatt
 */
public interface VPMAnalyzer {

    /**
     * Analyze a variation point model graph for relationships between the variation points and add
     * edges for the those identified.
     * 
     * The analyzer directly adds the edges to the graph.
     * 
     * @param vpmGraph
     *            The graph to analyze.
     */
    public void analyze(VPMGraph vpmGraph);

    /**
     * Get a map of the possible configuration settings for the analyzer.
     * 
     * @return A map with the setting IDs and their according data type for each available setting.
     */
    public Map<String, VPMAnalyzerConfigurationType> getAvailableConfigurations();

    /**
     * Get the labels to display for the available configuration settings.
     * 
     * @return A map with setting IDs and the according labels.
     */
    public Map<String, String> getConfigurationLabels();

    /**
     * Get the configuration map of the analyzer instance.
     * 
     * @return The map of settings with the id of the setting and it's value.
     */
    public Map<String, Object> getConfigurations();

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
}
