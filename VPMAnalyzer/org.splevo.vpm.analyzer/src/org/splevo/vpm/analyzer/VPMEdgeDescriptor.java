/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.vpm.analyzer;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * A descriptor for a variation point model graph (VPMGraph) edge.
 *
 * Such descriptors represent the required information to crate an edge and allow VPMGraph analyzers
 * to identify new relationship edges in parallel but do not require to manipulate the main graph in
 * parallel.
 */
public class VPMEdgeDescriptor {

    /** The main edge label for the type of identified relationship. */
    private String relationshipLabel = null;

    /** An addition label to further describe the identified relationship. */
    private String relationshipSubLabel = null;

    /** A map to store additional infos by the analyzer. */
    private Map<String, Object> relationShipInfos = Maps.newLinkedHashMap();

    /** The node to start the edge at. */
    private String sourceNodeID = null;

    /** The node to connect the edge to. */
    private String targetNodeID = null;

    /** Default constructor. */
    public VPMEdgeDescriptor() {
    }

    /**
     * Instantiates a new VPM edge descriptor.
     *
     * @param relationshipLabel
     *            the relationship label
     * @param relationshipSubLabel
     *            the relationship sub label
     * @param sourceNodeID
     *            the source node id
     * @param targetNodeID
     *            the target node id
     */
    public VPMEdgeDescriptor(String relationshipLabel, String relationshipSubLabel, String sourceNodeID,
            String targetNodeID) {
        this.relationshipLabel = relationshipLabel;
        this.relationshipSubLabel = relationshipSubLabel;
        this.sourceNodeID = sourceNodeID;
        this.targetNodeID = targetNodeID;
    }

    /**
     * Gets the main edge label for the type of identified relationship.
     *
     * @return the main edge label for the type of identified relationship
     */
    public String getRelationshipLabel() {
        return relationshipLabel;
    }

    /**
     * Sets the main edge label for the type of identified relationship.
     *
     * @param relationshipLabel
     *            the new main edge label for the type of identified relationship
     */
    public void setRelationshipLabel(String relationshipLabel) {
        this.relationshipLabel = relationshipLabel;
    }

    /**
     * Gets the an addition label to further describe the identified relationship.
     *
     * @return the an addition label to further describe the identified relationship
     */
    public String getRelationshipSubLabel() {
        return relationshipSubLabel;
    }

    /**
     * Sets the an addition label to further describe the identified relationship.
     *
     * @param relationshipSubLabel
     *            the new an addition label to further describe the identified relationship
     */
    public void setRelationshipSubLabel(String relationshipSubLabel) {
        this.relationshipSubLabel = relationshipSubLabel;
    }

    /**
     * Get the map to store additional infos to the relation ship. This can be used internally by
     * the analyzer or by any other edge analyzing code.
     *
     * It must not be assumed that this information will be presented to the user.
     *
     * @return The map to store additional info to the edge.
     */
    public Map<String, Object> getRelationShipInfos() {
        return relationShipInfos;
    }

    /**
     * Gets the node id to start the edge at.
     *
     * @return the node to start the edge at
     */
    public String getSourceNodeID() {
        return sourceNodeID;
    }

    /**
     * Sets the node id to start the edge at.
     *
     * @param sourceNodeID
     *            the new node to start the edge at
     */
    public void setSourceNodeID(String sourceNodeID) {
        this.sourceNodeID = sourceNodeID;
    }

    /**
     * Gets the node id to connect the edge to.
     *
     * @return the node to connect the edge to
     */
    public String getTargetNodeID() {
        return targetNodeID;
    }

    /**
     * Sets the node id to connect the edge to.
     *
     * @param targetNodeID
     *            the new node to connect the edge to
     */
    public void setTargetNodeID(String targetNodeID) {
        this.targetNodeID = targetNodeID;
    }

}
