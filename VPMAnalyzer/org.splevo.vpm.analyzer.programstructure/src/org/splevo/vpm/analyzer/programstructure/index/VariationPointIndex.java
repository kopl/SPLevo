package org.splevo.vpm.analyzer.programstructure.index;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.graphstream.graph.Node;
import org.splevo.modisco.java.vpm.software.MoDiscoJavaSoftwareElement;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * An index for storing and looking up variation points and their contained AST nodes.
 * 
 * 
 * @author Benjamin Klatt
 * 
 */
public class VariationPointIndex {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(VariationPointIndex.class);

    /**
     * Internal map to simplify working with variation points and to reference back to nodes
     * afterwards.
     */
    private Map<VariationPoint, Node> graphNodeIndex = null;

    /** an index mapping all AST nodes enclosed by a variation point to this variation point. */
    private Map<ASTNode, VariationPoint> enclosedASTNodeIndex = null;

    /** The AST node traverser for building the AST node index. */
    private ASTNodeChildrenSelector astNodeTraverser = new ASTNodeChildrenSelector();

    /**
     * Index the nodes and variation points of the supplied variation point model graph.
     * 
     * @param vpmGraph
     *            The graph to fill the index from.
     */
    public void index(VPMGraph vpmGraph) {
        logger.info("Start build graph node index");
        graphNodeIndex = buildGraphNodeIndex(vpmGraph);
        logger.info("Start build enclosed ast node index");
        enclosedASTNodeIndex = buildEnclosedASTNodeIndex(graphNodeIndex.keySet());
    }

    /**
     * Get a list of all indexed variation points.
     * 
     * @return The variation point list.
     */
    public List<VariationPoint> getVariationPoints() {
        return new ArrayList<VariationPoint>(graphNodeIndex.keySet());
    }

    /**
     * Get the graph node representing a variation point.
     * 
     * Theoretically, this might return null. If the same data was used to fill the index and to
     * retrieve the node now, this would not happen.
     * 
     * @param vp
     *            The variation point to look up the node for.
     * @return The referencing node.
     */
    public Node getGraphNode(VariationPoint vp) {
        return graphNodeIndex.get(vp);
    }

    /**
     * Build the inverted index mapping a variation point to the graph node it is contained in.
     * 
     * @param vpmGraph
     *            The graph to index.
     * @return The prepared inverted index.
     */
    private Map<VariationPoint, Node> buildGraphNodeIndex(VPMGraph vpmGraph) {
        Map<VariationPoint, Node> index = new HashMap<VariationPoint, Node>();

        for (Node node : vpmGraph.getNodeSet()) {
            VariationPoint vp = node.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);
            index.put(vp, node);
        }

        return index;
    }

    /**
     * Build an AST node index to simplify the variation point for AST node lookup.
     * 
     * @param variationPoints
     *            The variation point collection to build the AST node index for.
     * @return The prepared AST node index.
     */
    public Map<ASTNode, VariationPoint> buildEnclosedASTNodeIndex(Collection<VariationPoint> variationPoints) {

        Map<ASTNode, VariationPoint> index = new HashMap<ASTNode, VariationPoint>();

        for (VariationPoint vp : variationPoints) {
            for (ASTNode se : getVariantSoftwareEntities(vp)) {
                indexEnclosedAstNode(index, se, vp);
            }
        }

        return index;
    }

    /**
     * Index an AST node and it's sub nodes recursively.
     * 
     * @param index
     *            The index map to add the node to.
     * @param astNode
     *            The AST node to index.
     * @param vp
     *            The variation point to map the node to.
     */
    private void indexEnclosedAstNode(Map<ASTNode, VariationPoint> index, ASTNode astNode, VariationPoint vp) {
        index.put(astNode, vp);
        for (ASTNode subNode : astNodeTraverser.doSwitch(astNode)) {
            indexEnclosedAstNode(index, subNode, vp);
        }
    }

    /**
     * Collect all AST nodes implementing a variant of a Variation Point.
     * 
     * @param vp
     *            The variation point to get the ASTNodes for.
     * @return A list of all referenced ASTNodes.
     */
    public List<ASTNode> getVariantSoftwareEntities(VariationPoint vp) {
        List<ASTNode> astNodes = new ArrayList<ASTNode>();

        for (Variant v : vp.getVariants()) {

            for (SoftwareElement softwareElement : v.getSoftwareEntities()) {
                
                // TODO Release MoDisco Dependency
                if (softwareElement instanceof MoDiscoJavaSoftwareElement) {
                    ASTNode astNode = ((MoDiscoJavaSoftwareElement) softwareElement).getAstNode();
                    astNodes.add(astNode);
                } else {
                    logger.error("Unsupported SoftwareElement Type: " + softwareElement);
                }
            }
        }

        return astNodes;
    }

    /**
     * Lookup the enclosing variation point for an AST node. This might be null if the provided AST
     * nodes is not part of a variation point.
     * 
     * @param astNode
     *            The AST node to lookup the variation point for.
     * @return The enclosing variation point or null if none exist.
     */
    public VariationPoint getEnclosingVariationPoint(ASTNode astNode) {
        return enclosedASTNodeIndex.get(astNode);
    }

}
