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
package org.splevo.ui.views.vpmgraph;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.wb.swt.ResourceManager;
import org.graphstream.graph.Edge;
import org.splevo.ui.Activator;
import org.splevo.vpm.analyzer.graph.RelationshipEdge;

/**
 * Action to hide the labels of edges.
 *
 * This action only manipulates the visible graph by setting or removing the edge labels.
 *
 * @author Benjamin Klatt
 *
 */
class HideEdgeLabelAction extends Action {

    /** The attribute key for lables in the graph ui. */
    private static final String ATTRIBUTE_KEY_UI_LABEL = "ui.label";

    /** The inactive text for the action button. */
    private static final String TEXT_SHOW_EDGE_LABELS = "Show edge labels";

    /** The active text for the action button. */
    private static final String TEXT_HIDE_EDGE_LABELS = "Hide edge labels";

    /** Path to icon to use for the action. */
    private static final String ICON_ACTIVE = "icons/active/hideconnector.gif";

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(HideEdgeLabelAction.class);

    /**
     * The vpm graph to manipulate. It is necessary to work with the view and always request the
     * current instance of the graph because this might change during runtime. Otherwise, the action
     * might not manipulate the right graph instance.
     */
    private VPMGraphView vpmGraphView;

    /**
     * Constructor to set the necessary references.
     *
     * @param vpmGraphView
     *            The graph to manipulate
     */
    public HideEdgeLabelAction(VPMGraphView vpmGraphView) {
        super("Hide Edge Label Action", IAction.AS_CHECK_BOX);
        this.vpmGraphView = vpmGraphView;
        setToolTipText(TEXT_HIDE_EDGE_LABELS);
        setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator.PLUGIN_ID, ICON_ACTIVE));
        setChecked(false);
    }

    @Override
    public void run() {

        if (this.vpmGraphView.getVpmGraph() == null) {
            logger.warn("No VPMGraph present in VPMGraph view.");
            return;
        }
        toggleLabels();
    }

    /**
     * Toggle the edge labels.
     */
    private void toggleLabels() {

        if (isChecked()) {
            for (Edge currentEdge : this.vpmGraphView.getVpmGraph().getEdgeSet()) {
                currentEdge.removeAttribute(ATTRIBUTE_KEY_UI_LABEL);
            }
            setToolTipText(TEXT_HIDE_EDGE_LABELS);

        } else {
            for (Edge currentEdge : this.vpmGraphView.getVpmGraph().getEdgeSet()) {
                RelationshipEdge relEdge = (RelationshipEdge) currentEdge;
                if (relEdge.getRelationshipLabels().size() > 0) {
                    StringBuilder labelBuilder = new StringBuilder();
                    for (String label : relEdge.getRelationshipLabels()) {
                        labelBuilder.append(label);
                    }
                    currentEdge.addAttribute(ATTRIBUTE_KEY_UI_LABEL, labelBuilder.toString());
                }
            }
            setToolTipText(TEXT_SHOW_EDGE_LABELS);
        }
    }
}