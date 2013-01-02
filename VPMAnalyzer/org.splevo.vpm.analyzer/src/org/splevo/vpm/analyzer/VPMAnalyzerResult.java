/**
 * 
 */
package org.splevo.vpm.analyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * The result returned by a VPMAnalyzer.
 * This is used instead of directly manipulating the VPM graph to prevent
 * race-conditions or synchronization effects when executing VPMAnalyzers in parallel.
 * 
 * @author Benjamin Klatt
 *
 */
public class VPMAnalyzerResult {
    
    /** The identified relationships. */
    private List<VPMEdgeDescriptor> edgeDescriptors = new ArrayList<VPMEdgeDescriptor>();

    /** The analyzer that produced this result. */
    private VPMAnalyzer vpmAnalyzer = null;
    
    /**
     * Instantiates a new vPM analyzer result.
     * 
     * @param vpmAnalyzer
     *            the vpm analyzer that produced this result.
     */
    public VPMAnalyzerResult(VPMAnalyzer vpmAnalyzer) {
        this.vpmAnalyzer = vpmAnalyzer;
    }
    
    /**
     * Gets the edge descriptors.
     *
     * @return the edgeDescriptors
     */
    public List<VPMEdgeDescriptor> getEdgeDescriptors() {
        return edgeDescriptors;
    }

    /**
     * Gets the analyzer that produced this result.
     * 
     * @return the analyzer that produced this result
     */
    public VPMAnalyzer getVpmAnalyzer() {
        return vpmAnalyzer;
    }
    
    
}
