package org.splevo.vpm.analyzer;

import java.util.List;

import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.refinement.DetectionRule;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Service class to manage the variation point model analysis.
 * 
 * The service provides a graph-based composed relationship analysis of a variation point model.
 * 
 * @author Benjamin Klatt
 * 
 */
public interface VPMAnalyzerService {

    /** The id of the analyzer extension point. */
    String VPM_ANALYZER_EXTENSION_POINT_ID = "org.splevo.vpm.analyzer.analyzer";

    /** The id of the implementing class attribute of the analyzer extension point. */
    String EXTENSION_POINT_ATTR_ANALYZER_CLASS = "analyzer.class";

    /**
     * Initialize a graph representation for a variation point model.
     * 
     * @param vpm
     *            The variation point model to build the graph for.
     * @return The prepared graph.
     */
    public abstract VPMGraph initVPMGraph(VariationPointModel vpm);

    /**
     * Merge a list of graphs by merging the edges of all graphs into the first graph within the
     * list.
     * 
     * @param vpmGraphs
     *            The list of graphs to merge.
     * @return The prepared graph.
     */
    public abstract VPMGraph mergeGraphs(List<VPMGraph> vpmGraphs);
    
    /**
     * A VPM is not a multi graph (multiple edges between two nodes), 
     * but it is possible to add multiple edges between the same pair of nodes. 
     * This enables a marking of relationships by multiple analyzers in parallel.
     * For later processing, it is important to merge those edges later on.
     * 
     * This is done by merging their relationship label attributes in one edge and stripping
     * the analyzer prefix from the edges id.
     * 
     * @param vpmGraph
     *            The list of graphs to merge.
     */
    public abstract void mergeGraphEdges(VPMGraph vpmGraph);
    
    /**
     * Derive a list of refinements from the vpm graph by applying a list of detection rules to it.
     * 
     * @param vpmGraph
     *            The graph containing the relationships.
     * @param detectionRules
     *            The detection rules to apply.
     * @return The identified refinements.
     */
    public abstract List<Refinement> deriveRefinements(VPMGraph vpmGraph, List<DetectionRule> detectionRules);

    /**
     * Get the list of available variation point analyzers.
     * 
     * @return The list of registered analyzers
     */
    public abstract List<VPMAnalyzer> getAvailableAnalyzers();
}
