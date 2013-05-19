package org.splevo.vpm.analyzer;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.graph.RelationshipEdge;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.refinement.DetectionRule;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * The default vpm analyzer service implementation.
 * 
 * @author Benjamin Klatt
 * 
 */
public class DefaultVPMAnalyzerService implements VPMAnalyzerService {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(DefaultVPMAnalyzerService.class);

    /**
     * @see org.splevo.vpm.analyzer.VPMAnalyzerService#initVPMGraph(org.splevo.vpm.variability.
     * VariationPointModel)
     * {@inheritDoc}
     */
    @Override
    public VPMGraph initVPMGraph(VariationPointModel vpm) {

        VPMGraph graph = new VPMGraph("VPMGraph");

        // iterate over variation groups and points and
        // init nodes for each of them.
        int nodeIndex = 0;
        for (VariationPointGroup vpGroup : vpm.getVariationPointGroups()) {
            for (VariationPoint vp : vpGroup.getVariationPoints()) {
                
                // At this time, the referenced ASTNode might be a proxy.
                // To prevent issues in the later processing, it is important
                // to resolve the proxy here before pushing the variation point 
                // into the graph node.
                EcoreUtil.resolveAll(vp);
                
                String nodeID = "VP" + (nodeIndex++);
                Node n = graph.addNode(nodeID);
                n.addAttribute(VPMGraph.GS_LABEL, nodeID);
                n.addAttribute(VPMGraph.VARIATIONPOINT, vp);
            }
        }

        return graph;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.splevo.vpm.analyzer.VPMAnalyzerService#mergeGraphs(java.util.List)
     */
    @SuppressWarnings("unchecked")
    @Override
    public VPMGraph mergeGraphs(List<VPMGraph> graphs) {

        if (graphs.size() == 0) {
            return null;
        }

        VPMGraph graph = graphs.get(0);

        for (VPMGraph currentGraph : graphs) {

            if (currentGraph == graph) {
                continue;
            }

            for (Edge edge : currentGraph.getEachEdge()) {
                String edgeId = edge.getId();
                String sourceNodeId = edge.getSourceNode().getId();
                String targetNodeId = edge.getTargetNode().getId();

                Node sourceNode = graph.getNode(sourceNodeId);
                Edge mergeEdge = sourceNode.getEdgeBetween(targetNodeId);
                if (mergeEdge == null) {
                    mergeEdge = graph.addEdge(edgeId, sourceNodeId, targetNodeId);
                    for (String key : edge.getAttributeKeySet()) {
                        mergeEdge.addAttribute(key, edge.getAttribute(key));
                    }
                } else {
                    List<String> labels = mergeEdge.getAttribute(RelationshipEdge.RELATIONSHIP_LABEL, List.class);
                    labels.addAll(edge.getAttribute(RelationshipEdge.RELATIONSHIP_LABEL, List.class));
                    mergeEdge.setAttribute(RelationshipEdge.RELATIONSHIP_LABEL, labels);
                }

            }
        }

        return graph;
    }

    /**
     * @see org.splevo.vpm.analyzer.VPMAnalyzerService#mergeGraphs(java.util.List)
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void mergeGraphEdges(VPMGraph vpmGraph) {

        for (Edge edge : vpmGraph.getEachEdge()) {

            Node sourceNode = edge.getSourceNode();
            Node targetNode = edge.getTargetNode();

            // enrich the merge edge or create a new one
            String edgeId = buildEdgeId(sourceNode, targetNode);
            Edge mergeEdge = vpmGraph.getEdge(edgeId);
            if (mergeEdge == null) {
                mergeEdge = vpmGraph.addEdge(edgeId, sourceNode, targetNode);
                for (String key : edge.getAttributeKeySet()) {
                    mergeEdge.addAttribute(key, edge.getAttribute(key));
                }
            } else {
                List<String> labels = mergeEdge.getAttribute(RelationshipEdge.RELATIONSHIP_LABEL, List.class);
                labels.addAll(edge.getAttribute(RelationshipEdge.RELATIONSHIP_LABEL, List.class));
                mergeEdge.setAttribute(RelationshipEdge.RELATIONSHIP_LABEL, labels);
            }

            // remove the old merged node
            vpmGraph.removeEdge(edge);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createGraphEdges(VPMGraph vpmGraph, List<VPMAnalyzerResult> analyzerResults) {
        for (VPMAnalyzerResult analyzerResult : analyzerResults) {

            for (VPMEdgeDescriptor edgeDescriptor : analyzerResult.getEdgeDescriptors()) {

                String sourceNodeId = edgeDescriptor.getSourceNodeID();
                String targetNodeId = edgeDescriptor.getTargetNodeID();

                // enrich the merge edge or create a new one
                String edgeId = buildEdgeId(sourceNodeId, targetNodeId);
                Edge mergeEdge = vpmGraph.getEdge(edgeId);
                if (mergeEdge == null) {
                    mergeEdge = vpmGraph.addEdge(edgeId, sourceNodeId, targetNodeId);
                    List<String> labels = new ArrayList<String>();
                    labels.add(edgeDescriptor.getRelationshipLabel());
                    mergeEdge.setAttribute(RelationshipEdge.RELATIONSHIP_LABEL, labels);
                } else {
                    List<String> labels = mergeEdge.getAttribute(RelationshipEdge.RELATIONSHIP_LABEL, List.class);
                    labels.add(edgeDescriptor.getRelationshipLabel());
                    mergeEdge.setAttribute(RelationshipEdge.RELATIONSHIP_LABEL, labels);
                }
            }
        }
    }

    /**
     * Build a simple edge id by using the ids of the connected nodes only.
     * 
     * @param node1
     *            The first node.
     * @param node2
     *            The second node.
     * @return The prepared edge id.
     */
    public String buildEdgeId(Node node1, Node node2) {
        return buildEdgeId(node1.getId(), node2.getId());
    }

    /**
     * Build a simple edge id by using the ids of the connected nodes only.
     * 
     * The method ensures an alphanumeric ascending order of the node ids.
     * 
     * @param node1ID
     *            The id of the first node.
     * @param node2ID
     *            The id of the second node.
     * @return The prepared edge id.
     */
    public String buildEdgeId(String node1ID, String node2ID) {
        if (node1ID.compareTo(node2ID) <= 0) {
            return node1ID + "#" + node2ID;
        } else {
            return node2ID + "#" + node1ID;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Refinement> deriveRefinements(VPMGraph vpmGraph, List<DetectionRule> detectionRules) {

        List<Refinement> refinements = new ArrayList<Refinement>();

        for (DetectionRule rule : detectionRules) {
            List<Refinement> detectedRefinements = rule.detect(vpmGraph);
            refinements.addAll(detectedRefinements);
        }

        return refinements;

    }

    /**
     * Load the vpm analyzer implementations registered for the according extension point.
     * 
     * {@inheritDoc}
     */
    @Override
    public List<VPMAnalyzer> getAvailableAnalyzers() {
        ArrayList<VPMAnalyzer> refinementAnalyser = new ArrayList<VPMAnalyzer>();
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry == null) {
            logger.warn("No extension point registry available.");
            return null;
        }
        IExtensionPoint extensionPoint = registry.getExtensionPoint(VPM_ANALYZER_EXTENSION_POINT_ID);

        if (extensionPoint == null) {
            logger.warn("No extension point found for the ID " + VPM_ANALYZER_EXTENSION_POINT_ID);
            return null;
        }
        IExtension[] extensions = extensionPoint.getExtensions();
        for (IExtension extension : extensions) {
            IConfigurationElement[] configurations = extension.getConfigurationElements();
            for (IConfigurationElement element : configurations) {
                try {
                    Object o = element.createExecutableExtension(EXTENSION_POINT_ATTR_ANALYZER_CLASS);
                    if ((o != null) && (o instanceof VPMAnalyzer)) {
                        VPMAnalyzer analyzer = (VPMAnalyzer) o;
                        refinementAnalyser.add(analyzer);
                    }
                } catch (CoreException e) {
                    logger.error("Failed to load VPM analyzer extension", e);
                }
            }
        }
        return refinementAnalyser;
    }
}
