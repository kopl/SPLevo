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
package org.splevo.vpm.analyzer.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.graphstream.graph.implementations.AbstractEdge;
import org.graphstream.graph.implementations.AbstractNode;

import com.google.common.base.Strings;

/**
 * A variation point relationship edge. It allows to store the type of relationships detected
 * (labels) and an arbitrary number of additional infos (e.g. for further specification of the
 * relationship detected).
 */
public class RelationshipEdge extends AbstractEdge {

    /**
     * The key to store and access the label of a relationship.
     *
     * Note: This ID should be used with care. The getter and setter should be prefferred and this
     * internal id is only for raw graph stream interactions.
     */
    public static final String RELATIONSHIP_LABEL = "vp.relationship.label";

    /**
     * The key to store and access the info about the relationship.
     *
     * Note: This ID should be used with care. The getter and setter should be prefferred and this
     * internal id is only for raw graph stream interactions.
     */
    public static final String RELATIONSHIP_INFO = "vp.relationship.info";

    /**
     * Constructor requiring the initial edge data.
     *
     * This also initializes the relationship label list.
     *
     * @param id
     *            The internal id of the edge.
     * @param source
     *            The source node.
     * @param target
     *            The target node.
     * @param directed
     *            The flag whether this is a directed edge.
     */
    public RelationshipEdge(String id, AbstractNode source, AbstractNode target, boolean directed) {
        super(id, source, target, directed);

        setAttribute(RelationshipEdge.RELATIONSHIP_LABEL, new ArrayList<String>());
    }

    /**
     * Add a relationship label to the edge.
     *
     * @param label
     *            The label to add. Null values are ignored.
     */
    @SuppressWarnings("unchecked")
    public void addRelationshipLabel(String label) {
        if (!Strings.isNullOrEmpty(label)) {
            getAttribute(RelationshipEdge.RELATIONSHIP_LABEL, List.class).add(label);
        }
    }

    /**
     * Get the list of relationship labels.
     *
     * @return The final list of relationship labels.
     */
    @SuppressWarnings("unchecked")
    public final List<String> getRelationshipLabels() {
        if (hasAttribute(RelationshipEdge.RELATIONSHIP_LABEL)) {
            return getAttribute(RelationshipEdge.RELATIONSHIP_LABEL, List.class);
        } else {
            return new ArrayList<String>();
        }
    }

    /**
     * Add an additional info about the detected relationship the edge.
     *
     * @param info
     *            The additional info to add. Null values are ignored.
     */
    @SuppressWarnings("unchecked")
    public void addRelationshipInfo(String info) {
        if (!Strings.isNullOrEmpty(info)) {
            if (!hasAttribute(RelationshipEdge.RELATIONSHIP_INFO)) {
                setAttribute(RelationshipEdge.RELATIONSHIP_INFO, new LinkedList<String>());
            }
            getAttribute(RelationshipEdge.RELATIONSHIP_INFO, List.class).add(info);
        }
    }

    /**
     * Get the list of info about the relationship.
     *
     * @return The final list of relationship info.
     */
    @SuppressWarnings("unchecked")
    public final List<String> getRelationshipInfos() {
        if (hasAttribute(RelationshipEdge.RELATIONSHIP_INFO)) {
            return getAttribute(RelationshipEdge.RELATIONSHIP_INFO, List.class);
        } else {
            return new ArrayList<String>();
        }
    }
}
