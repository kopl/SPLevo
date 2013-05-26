package org.splevo.vpm.analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.graphstream.graph.Node;

/**
 * An abstract variation point model analyzer providing some common infrastructure.
 * 
 * @author Benjamin Klatt
 * 
 */
public abstract class AbstractVPMAnalyzer implements VPMAnalyzer {

    @Override
    public abstract String getRelationshipLabel();

    /** Internal list to cache which edges have already been created. */
    protected List<String> edgeRegistry = new ArrayList<String>();

    /** The logger to store any log analysis information to. */
    private Logger analysisLogger = Logger.getLogger(VPMAnalyzer.LOG_CATEGORY);

    /**
     * Log information during the analysis for later evaluation.
     * An empty relationship info is added.
     * 
     * @param vp1ID
     *            An identifier for the first variation point of the relation.
     * @param vp2ID
     *            An identifier for the second variation point of the relation.
     * @param sourceInfo
     *            An info about the source of the relationship.
     * @param targetInfo
     *            An info about the target of the relationship.
     */
    protected void logAnalysisInfo(String vp1ID, String vp2ID, String sourceInfo, String targetInfo) {
        logAnalysisInfo(vp1ID, vp2ID, sourceInfo, targetInfo, "");
    }

    /**
     * Log some information during the analysis for later evaluation.
     * 
     * @param vp1ID
     *            An identifier for the first variation point of the relation.
     * @param vp2ID
     *            An identifier for the second variation point of the relation.
     * @param sourceInfo
     *            An info about the source of the relationship.
     * @param targetInfo
     *            An info about the target of the relationship.
     * @param remark
     *            An info field to store additional information about the relationship.
     */
    protected void logAnalysisInfo(String vp1ID, String vp2ID, String sourceInfo, String targetInfo,
            String remark) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append(getRelationshipLabel());
        logMessage.append(LOG_SEPARATOR);
        logMessage.append(vp1ID);
        logMessage.append(LOG_SEPARATOR);
        logMessage.append(vp2ID);
        logMessage.append(LOG_SEPARATOR);
        logMessage.append(sourceInfo);
        logMessage.append(LOG_SEPARATOR);
        logMessage.append(targetInfo);
        logMessage.append(LOG_SEPARATOR);
        logMessage.append(remark);
        analysisLogger.info(logMessage.toString());
    }

    /**
     * Default implementation returning an empty configuration map.
     * @return An emty configuration map.
     */
    @Override
    public Map<String, VPMAnalyzerConfigurationType> getAvailableConfigurations() {
        return new HashMap<String, VPMAnalyzerConfigurationType>();
    }

    /**
     * Build a unique internal identifier for the undirected edge between the nodes. This is done
     * ensuring an alphabetical order to be independent of the parameter order.
     * 
     * @param node1
     *            The first node to connect.
     * @param node2
     *            The second node to connect.
     * @return The prepared edge id.
     */
    protected String buildEdgeRegistryID(Node node1, Node node2) {
        if (node1.getId().compareTo(node2.getId()) < 0) {
            return node1.getId() + "#" + node2.getId();
        } else {
            return node2.getId() + "#" + node1.getId();
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
    public String buildEdgeId(Node node1, Node node2) {
        if (node1.getId().compareTo(node2.getId()) < 0) {
            return getRelationshipLabel() + "#" + node1.getId() + "#" + node2.getId();
        } else {
            return getRelationshipLabel() + "#" + node2.getId() + "#" + node1.getId();
        }
    }

    /**
     * Builds the edge descriptor.
     * 
     * @param node1
     *            the source node of the edge.
     * @param node2
     *            the target node of the edge.
     * @param relationshipSubLabel
     *            the relationship sub label
     * @return the VPM edge descriptor. Might be null if the edge already exists.
     */
    protected VPMEdgeDescriptor buildEdgeDescriptor(Node node1, Node node2, String relationshipSubLabel) {

        VPMEdgeDescriptor descriptor = null;

        // skip if code structure relationship already exists
        String edgeRegistryID = buildEdgeRegistryID(node1, node2);
        if (!edgeRegistry.contains(edgeRegistryID)) {
            edgeRegistry.add(edgeRegistryID);

            // build edge descriptor
            descriptor = new VPMEdgeDescriptor(getRelationshipLabel(), relationshipSubLabel, node1.getId(),
                    node2.getId());
        }

        return descriptor;
    }

}
