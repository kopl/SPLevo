package org.splevo.vpm.analyzer.codelocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.IfStatement;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.AbstractVPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerConfigurationType;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.graph.VPMGraph;
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
     * @return the VPM analyzer result
     */
    @Override
    public VPMAnalyzerResult analyze(final VPMGraph vpmGraph) {

        VPMAnalyzerResult result = new VPMAnalyzerResult(this);

        fillStructureMap(vpmGraph);
        logger.debug("Grouped " + vpmGraph.getNodeCount() + " Nodes into " + structureMap.keySet().size() + " buckets.");

        for (ASTNode astNode : structureMap.keySet()) {
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
     * @param astNode
     *            The variation points of all nodes are located in.
     */
    private void buildNodeEdgeDescriptors(VPMAnalyzerResult result, List<Node> nodeList, ASTNode astNode) {

        // build edges for all pairs of nodes.
        for (Node node1 : nodeList) {
            for (Node node2 : nodeList) {

                // skip if both nodes are the same
                if (node1 == node2) {
                    continue;
                }

                VPMEdgeDescriptor descriptor = buildEdgeDescriptor(node1, node2, "");
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
     */
    private void fillStructureMap(VPMGraph vpmGraph) {

        for (Node node : vpmGraph.getNodeSet()) {
            VariationPoint vp = node.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);
            if (vp != null) {

                ASTNode astNode = vp.getEnclosingSoftwareEntity();
                astNode = chooseEnclosingNode(astNode);

                if (!structureMap.containsKey(astNode)) {
                    structureMap.put(astNode, new ArrayList<Node>());
                }

                // temporary test logging
                if (astNode instanceof NamedElement) {
                    logger.debug("CODE MAP: " + astNode.hashCode() + "|" + ((NamedElement) astNode).getName() + "\t|\t"
                            + node.getId() + "\t" + EcoreUtil.getIdentification(astNode));
                }
                structureMap.get(astNode).add(node);
            }

        }
    }

    /**
     * Choose the ast node to use as code location.
     * 
     * This method handles special ast node types as code location:
     * <ul>
     * <li>ifStatement: Traverse parent notes until the first enclosing non-ifstatement is reached.</li>
     * </ul>
     * 
     * @param astNode
     *            The parent ast node to choose the real enclosing node for.
     * @return The identified enclosing AST node.
     */
    private ASTNode chooseEnclosingNode(ASTNode astNode) {

        if (astNode instanceof Block || astNode instanceof IfStatement) {
            return chooseEnclosingNode((ASTNode) astNode.eContainer());
        }

        return astNode;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#getAvailableConfigurations()
     */
    @Override
    public Map<String, VPMAnalyzerConfigurationType> getAvailableConfigurations() {
        Map<String, VPMAnalyzerConfigurationType> configurations = new HashMap<String, VPMAnalyzerConfigurationType>();
        return configurations;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#getConfigurationLabels()
     */
    @Override
    public Map<String, String> getConfigurationLabels() {
        Map<String, String> configurationLabels = new HashMap<String, String>();
        return configurationLabels;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#getConfigurations()
     */
    @Override
    public Map<String, Object> getConfigurations() {
        Map<String, Object> configurations = new HashMap<String, Object>();
        return configurations;
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

}
