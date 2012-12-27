package org.splevo.vpm.analyzer.codestructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerConfigurationType;
import org.splevo.vpm.analyzer.graph.RelationshipEdge;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.variability.VariationPoint;

/**
 * A variation point analyzer identifying variation points located in the same code structure.
 * 
 * @author Benjamin Klatt
 * 
 */
public class CodeStructureVPMAnalyzer implements VPMAnalyzer {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(CodeStructureVPMAnalyzer.class);

    /** An internal map to group nodes with variation points in the same ASTNode. */
    private Map<ASTNode, List<Node>> structureMap = new HashMap<ASTNode, List<Node>>();

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
     */
    @Override
    public void analyze(VPMGraph vpmGraph) {

        fillStructureMap(vpmGraph);
        logger.debug("Grouped " + vpmGraph.getNodeCount() + " Nodes into " + structureMap.keySet().size() + " buckets.");

        for (ASTNode astNode : structureMap.keySet()) {
            if (structureMap.get(astNode).size() > 1) {
                buildNodeEdges(vpmGraph, structureMap.get(astNode), astNode);
            }
        }

    }

    /**
     * Build node edges in a graph between all nodes provided in the node list.
     * 
     * @param vpmGraph
     *            The graph to add the edge to.
     * @param nodeList
     *            The list of nodes to connect.
     * @param astNode
     *            The variation points of all nodes are located in.
     */
    private void buildNodeEdges(VPMGraph vpmGraph, List<Node> nodeList, ASTNode astNode) {

        // build edges for all pairs of nodes.
        for (Node node1 : nodeList) {
            for (Node node2 : nodeList) {

                // skip if both nodes are the same
                if (node1 == node2) {
                    continue;
                }

                // skip if code structure relationship already exists
                String edgeId = buildEdgeId(node1, node2);
                if (vpmGraph.getEdge(edgeId) != null) {
                    logger.debug("Edge [" + edgeId + "] already existing.");
                    continue;
                }
                
                // build edge
                RelationshipEdge edge = vpmGraph.addEdge(edgeId, node1, node2);
                edge.getRelationshipLabels().add(getRelationshipLabel());
            }
        }
    }

    /**
     * Build a unique identifier for the undirected edge between the nodes. The edge id is build
     * from the Relationship label and the nodes ids in ascending order.
     * 
     * @param node1
     *            The first node to connect.
     * @param node2
     *            The second node to connect.
     * @return The prepared edge id.
     */
    private String buildEdgeId(Node node1, Node node2) {
        if (node1.getId().compareTo(node2.getId()) < 0) {
            return getRelationshipLabel() + "#" + node1.getId() + "#" + node2.getId();
        } else {
            return getRelationshipLabel() + "#" + node2.getId() + "#" + node1.getId();
        }
    }

    /**
     * Fill the internal structure map and sort the graph nodes according to their variation point
     * locations.
     * 
     * @param vpmGraph
     *            The graph to analyze.
     */
    private void fillStructureMap(VPMGraph vpmGraph) {

        for (Node node : vpmGraph.getNodeSet()) {
            VariationPoint vp = node.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);
            if (vp != null) {
                if (!structureMap.containsKey(vp.getEnclosingSoftwareEntity())) {
                    structureMap.put(vp.getEnclosingSoftwareEntity(), new ArrayList<Node>());
                }
                structureMap.get(vp.getEnclosingSoftwareEntity()).add(node);
            }

        }
    }

    @Override
    public Map<String, VPMAnalyzerConfigurationType> getAvailableConfigurations() {
        Map<String, VPMAnalyzerConfigurationType> configurations = new HashMap<String, VPMAnalyzerConfigurationType>();
        return configurations;
    }

    @Override
    public Map<String, String> getConfigurationLabels() {
        Map<String, String> configurationLabels = new HashMap<String, String>();
        return configurationLabels;
    }

    @Override
    public Map<String, Object> getConfigurations() {
        Map<String, Object> configurations = new HashMap<String, Object>();
        return configurations;
    }

    @Override
    public String getName() {
        return "Code Structure VPM Analyzer";
    }

    @Override
    public String getRelationshipLabel() {
        return "CodeStructure";
    }

}
