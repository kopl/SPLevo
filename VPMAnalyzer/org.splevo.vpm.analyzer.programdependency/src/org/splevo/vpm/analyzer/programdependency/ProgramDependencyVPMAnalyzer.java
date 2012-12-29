/**
 * 
 */
package org.splevo.vpm.analyzer.programdependency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerConfigurationType;
import org.splevo.vpm.analyzer.graph.RelationshipEdge;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Program Dependency Analyzer analyzing the dependency graph and the included variation points,
 * respectively the ASTodes referenced by the Variation Points.
 * 
 * The analyzer uses different strategies to find dependent elements.
 * 
 * First an inverse variation point index is prepared to lookup the node for a variation point. This
 * allows to work with the variation points and if a matching pair is found, the nodes can be
 * retrieved to create an edge.
 * 
 * <strong>VariableDeclaration Forward Slice</strong>
 * <ul>
 * <li>
 * detect all variable declaration statements (VDS) in the variation points<br>
 * All ASTNodes referenced by all variants of a variation point filtered for
 * VariableDeclarationStatement type</li>
 * <li>
 * iterate over all VDS and for each detect all relevant ASTNodes influenced by the declared
 * variable(s).<br>
 * The detection traverse the ASTNode meta model. Currently only VariableStatements are returned.
 * Logger warnings are created if a currently not respected direction within the AST traversing is
 * identified. TODO: Check the completeness of the influenced AST node detection.</li>
 * <li>Lookup the variation points for the influenced ASTNodes.</li>
 * <li>Lookup the Nodes for the influenced variation points.</li>
 * <li>Create edges between all nodes influenced by the same variable declaration</li>
 * </ul>
 * 
 * 
 * 
 * @author Benjamin Klatt
 * 
 */
public class ProgramDependencyVPMAnalyzer implements VPMAnalyzer {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(ProgramDependencyVPMAnalyzer.class);

    /** The internal configurations map. */
    private Map<String, Object> configurations = new HashMap<String, Object>();

    /**
     * Internal map to simplify working with variation points and to reference back to nodes
     * afterwards.
     */
    private Map<VariationPoint, Node> variationPointIndex = null;

    /**
     * Internal index to simplify the lookup of variation points for an AST nodes.
     */
    private Map<ASTNode, VariationPoint> astNodeIndex = null;

    /**
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)
     */
    @Override
    public void analyze(VPMGraph vpmGraph) {

        variationPointIndex = buildVariationPointIndex(vpmGraph);
        astNodeIndex = buildASTNodeIndex(variationPointIndex.keySet());

        analyzeVariableUsages(vpmGraph);

    }

    /**
     * Analyzes variation points for pairs with a VariableDeclarationStatement that declares a
     * variable that is used by another node later on.
     * 
     * This has to work on the variant level to really analyze the variable declaration statement
     * and not the enclosing block.
     * 
     * TODO: Enhance dependency handling Currently, a very simplified handling of all software
     * entities referenced by a variant and all variants in a variation point is used.
     * 
     * @param vpmGraph
     *            The graph to analyze.
     */
    private void analyzeVariableUsages(VPMGraph vpmGraph) {
        for (VariationPoint vp : variationPointIndex.keySet()) {

            List<ASTNode> astNodes = astNodesOfVariationPoint(vp);
            for (ASTNode astNode : astNodes) {
                if (astNode instanceof VariableDeclarationStatement) {
                    List<VariationPoint> referencingVPs = searchForReferences((VariableDeclarationStatement) astNode);
                    for (VariationPoint referee : referencingVPs) {
                        Node sourceNode = variationPointIndex.get(vp);
                        Node targetNode = variationPointIndex.get(referee);

                        String edgeId = buildEdgeId(sourceNode, targetNode);

                        if (vpmGraph.getEdge(edgeId) != null) {
                            logger.debug("Edge [" + edgeId + "] already exists.");
                            continue;
                        }

                        // build edge
                        RelationshipEdge edge = vpmGraph.addEdge(edgeId, sourceNode, targetNode);
                        edge.getRelationshipLabels().add(getRelationshipLabel());
                    }
                }
            }

        }
    }

    /**
     * Search for variation points referencing AST nodes influenced by a variable declaration statement.
     * 
     * @param varDeclStmnt The variable declaration statement to find references for.
     * @return The list of referring variation points.
     */
    private List<VariationPoint> searchForReferences(VariableDeclarationStatement varDeclStmnt) {

        List<VariationPoint> variationPoints = new ArrayList<VariationPoint>();

        List<ASTNode> influencedAstNodes = findInfluencedASTNodes(varDeclStmnt);

        for (ASTNode astNode : influencedAstNodes) {
            if(astNodeIndex.containsKey(astNode)){
                VariationPoint vp = astNodeIndex.get(astNode);
                variationPoints.add(vp);
            }
        }
        
        return variationPoints;
    }

    /**
     * Find all relevant ASTNodes influenced by a variable declaration statement.
     * 
     * A forward slice to find all influenced elements.
     * 
     * @param variableDeclarationStatement
     *            The statement to find influenced elements for.
     * @return The detected ast nodes.
     */
    public List<ASTNode> findInfluencedASTNodes(VariableDeclarationStatement variableDeclarationStatement) {

        List<ASTNode> influencedASTNodes = new ArrayList<ASTNode>();

        for (VariableDeclarationFragment fragment : variableDeclarationStatement.getFragments()) {

            for (SingleVariableAccess variableAccess : fragment.getUsageInVariableAccess()) {
                EObject variableAccessContainer = variableAccess.eContainer();
                if (variableAccessContainer instanceof MethodInvocation) {

                    MethodInvocation methodInvocation = (MethodInvocation) variableAccessContainer;
                    EObject methodContainer = methodInvocation.eContainer();
                    if (methodContainer instanceof VariableDeclarationFragment) {
                        VariableDeclarationFragment methodVariableDecl = (VariableDeclarationFragment) methodContainer;
                        EObject varDeclContainer = methodVariableDecl.eContainer();
                        if (varDeclContainer instanceof VariableDeclarationStatement) {
                            VariableDeclarationStatement influencedVarDeclStatement = (VariableDeclarationStatement) varDeclContainer;
                            influencedASTNodes.add(influencedVarDeclStatement);

                        } else {
                            logger.warn("Not yet handled variable declaration container: " + varDeclContainer);
                        }

                    } else {
                        logger.warn("Not yet handled method invocation container: " + methodContainer);
                    }

                } else {
                    logger.warn("Not yet handled variable access container: " + variableAccessContainer);
                }
            }
        }

        return influencedASTNodes;
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
     * Gather all ASTNodes referenced by any variant of the variation point.
     * 
     * @param vp
     *            The variation point to get the ASTNodes for.
     * @return A list of all referenced ASTNodes.
     */
    private List<ASTNode> astNodesOfVariationPoint(VariationPoint vp) {
        List<ASTNode> astNodes = new ArrayList<ASTNode>();

        for (Variant v : vp.getVariants()) {
            astNodes.addAll(v.getSoftwareEntities());
        }

        return astNodes;
    }

    /**
     * Build a variation point index to simplify node lookup afterwards.
     * 
     * @param vpmGraph
     *            The graph to fill the index from.
     * @return The prepared variation point index.
     */
    private Map<VariationPoint, Node> buildVariationPointIndex(VPMGraph vpmGraph) {
        Map<VariationPoint, Node> variationPointIndex = new HashMap<VariationPoint, Node>();
        for (Node node : vpmGraph.getNodeSet()) {
            VariationPoint vp = node.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);
            variationPointIndex.put(vp, node);
        }
        return variationPointIndex;
    }

    /**
     * Build an AST node index to simplify the variation point for AST node lookup.
     * 
     * @param variationPoints
     *            The variation point collection to build the AST node index for.
     * @return The prepared AST node index.
     */
    public Map<ASTNode, VariationPoint> buildASTNodeIndex(Collection<VariationPoint> variationPoints) {

        Map<ASTNode, VariationPoint> astNodeIndex = new HashMap<ASTNode, VariationPoint>();

        for (VariationPoint vp : variationPoints) {
            for (ASTNode astNodes : astNodesOfVariationPoint(vp)) {
                astNodeIndex.put(astNodes, vp);
            }
        }

        return astNodeIndex;
    }

    @Override
    public Map<String, VPMAnalyzerConfigurationType> getAvailableConfigurations() {
        Map<String, VPMAnalyzerConfigurationType> availableConfigurations = new HashMap<String, VPMAnalyzerConfigurationType>();
        return availableConfigurations;
    }

    @Override
    public Map<String, String> getConfigurationLabels() {
        Map<String, String> configurationLabels = new HashMap<String, String>();
        return configurationLabels;
    }

    /**
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#getConfigurations()
     */
    @Override
    public Map<String, Object> getConfigurations() {
        return configurations;
    }

    @Override
    public String getName() {
        return "Program Dependency VPM Analyzer";
    }

    @Override
    public String getRelationshipLabel() {
        return "ProgramDependency";
    }

}
