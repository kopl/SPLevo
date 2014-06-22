/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.vpm.analyzer;

import java.util.List;

import org.apache.log4j.Logger;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;

/**
 * An abstract variation point model analyzer providing some common infrastructure.
 */
public abstract class AbstractVPMAnalyzer implements VPMAnalyzer {

    @Override
    public abstract String getRelationshipLabel();

    @Override
    public abstract VPMAnalyzerConfigurationSet getConfigurations();

    /** The logger to store any log analysis information to. */
    private Logger analysisLogger = Logger.getLogger(VPMAnalyzer.LOG_CATEGORY);

    /**
     * Log information during the analysis for later evaluation. An empty relationship info is
     * added.
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
    protected void logAnalysisInfo(String vp1ID, String vp2ID, String sourceInfo, String targetInfo, String remark) {
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
        logMessage.append('"');
        logMessage.append(remark);
        logMessage.append('"');
        analysisLogger.info(logMessage.toString());
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
     * @param edgeRegistry
     *            to prove if this descriptor has already been created.
     * @return the VPM edge descriptor. Might be null if the edge already exists.
     */
    public VPMEdgeDescriptor buildEdgeDescriptor(Node node1, Node node2, String relationshipSubLabel,
            List<String> edgeRegistry) {

        VPMEdgeDescriptor descriptor = null;

        String edgeRegistryID = buildEdgeRegistryID(node1, node2);
        if (!edgeRegistry.contains(edgeRegistryID)) {
            edgeRegistry.add(edgeRegistryID);
            descriptor = new VPMEdgeDescriptor(getRelationshipLabel(), relationshipSubLabel, node1.getId(),
                    node2.getId());
        }

        return descriptor;
    }

}
