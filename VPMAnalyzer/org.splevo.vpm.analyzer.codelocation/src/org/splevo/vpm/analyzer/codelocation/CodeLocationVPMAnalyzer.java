package org.splevo.vpm.analyzer.codelocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.AbstractVPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.VariationPoint;

/**
 * A variation point analyzer identifying variation points located in the same code structure.
 *
 * Algorithm:
 * <ol>
 * <li>filling an internal structure map Map<ASTNode, List<Node>> [Complexity: O(n)]</li>
 * <li>Searching the map for entries with more than one node [Complexity: O(n)]</li>
 * <li>Creating a relationship edge for each of them [Complexity: O(n)]</li>
 * </ol>
 * Overall Algorithm Complexity: O(n)
 *
 *
 * @author Benjamin Klatt
 *
 */
public class CodeLocationVPMAnalyzer extends AbstractVPMAnalyzer {

    /** The relationship label of the analyzer. */
    public static final String RELATIONSHIP_LABEL_CODE_LOCATION = "CodeLocation";

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(CodeLocationVPMAnalyzer.class);

    /**
     * Analyze the vpm graph.
     *
     * Process:
     * <ol>
     * <li>filling an internal structure map</li>
     * <li>Searching the map for entries with more than one node</li>
     * <li>Creating a relationship edge for each of them</li>
     * </ol>
     *
     * @param vpmGraph
     *            The graph to analyze and store the edges in.
     * @return the VPM analyzer result
     */
    @Override
    public VPMAnalyzerResult analyze(final VPMGraph vpmGraph) {

        VPMAnalyzerResult result = new VPMAnalyzerResult(this);

        Map<SoftwareElement, List<Node>> structureMap = fillStructureMap(vpmGraph);
        logger.debug("Grouped " + vpmGraph.getNodeCount() + " Nodes into " + structureMap.keySet().size() + " buckets.");

        for (SoftwareElement astNode : structureMap.keySet()) {
            if (structureMap.get(astNode).size() > 1) {
                buildNodeEdgeDescriptors(result, structureMap.get(astNode), astNode);
            }
        }

        return result;

    }

    /**
     * Build node edges in a graph between all nodes provided in the node list.
     *
     * @param result
     *            The vpm analyzer result object.
     * @param nodeList
     *            The list of nodes to connect.
     * @param softwareElement
     *            The variation points of all nodes are located in.
     */
    private void buildNodeEdgeDescriptors(VPMAnalyzerResult result, List<Node> nodeList, SoftwareElement softwareElement) {

        List<String> edgeRegistry = new ArrayList<String>();

        // build edges for all pairs of nodes.
        for (Node node1 : nodeList) {
            for (Node node2 : nodeList) {

                // skip if both nodes are the same
                if (node1 == node2) {
                    continue;
                }

                VPMEdgeDescriptor descriptor = buildEdgeDescriptor(node1, node2, "", edgeRegistry);
                if (descriptor != null) {
                    result.getEdgeDescriptors().add(descriptor);
                }
            }
        }
    }

    /**
     * Fill the internal structure map and sort the graph nodes according to their variation point
     * locations.
     *
     * @param vpmGraph
     *            The graph to analyze.
     * @return The prepared structure map.
     */
    private Map<SoftwareElement, List<Node>> fillStructureMap(VPMGraph vpmGraph) {

        Map<SoftwareElement, List<Node>> structureMap = new HashMap<SoftwareElement, List<Node>>();

        for (Node node : vpmGraph.getNodeSet()) {
            VariationPoint vp = node.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);
            if (vp != null) {
                SoftwareElement softwareElement = vp.getEnclosingSoftwareEntity();

                if (softwareElement != null) {

                    if (!structureMap.containsKey(softwareElement)) {
                        structureMap.put(softwareElement, new ArrayList<Node>());
                    }

                    structureMap.get(softwareElement).add(node);
                }
            }
        }

        return structureMap;

    }

    /*
     * (non-Javadoc)
     *
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#getName()
     */
    @Override
    public String getName() {
        return "Code Location VPM Analyzer";
    }

    /*
     * (non-Javadoc)
     *
     * @see org.splevo.vpm.analyzer.AbstractVPMAnalyzer#getRelationshipLabel()
     */
    @Override
    public String getRelationshipLabel() {
        return RELATIONSHIP_LABEL_CODE_LOCATION;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#getConfigurations()
     */
    @Override
    public VPMAnalyzerConfigurationSet getConfigurations() {
        return new VPMAnalyzerConfigurationSet();
    }

}
