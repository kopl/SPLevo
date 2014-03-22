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
import org.graphstream.graph.Node;
import org.splevo.ui.Activator;

/**
 * Action to filter the single nodes without any relationships from a graph.
 * 
 * This action only manipulates the visible graph by setting or removing the ui.hide attribute from
 * those nodes without any edges.
 * 
 * @author Benjamin Klatt
 * 
 */
class FilterSingleNodeAction extends Action {

    /** Path to icon to use for the action. */
    private static final String ICON_ACTIVE = "icons/active/filter-empty-nodes.png";

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(FilterSingleNodeAction.class);

    /**
     * The vpm graph to manipulate. It is necessary to work with the view and always request the
     * current instance of the graph because this might change during runtime. Otherwise, the action
     * might not manipulate the right graph instance.
     */
    private VPMGraphView vpmGraphView;

    /** The attribute id to hide a node in the view. */
    private static final String NODE_ATTRIBUTE_HIDE = "ui.hide";

    /**
     * Constructor to set the necessary references.
     * 
     * @param vpmGraphView
     *            The graph to manipulate
     */
    public FilterSingleNodeAction(VPMGraphView vpmGraphView) {
        super("Filter Single Nodes Action", IAction.AS_CHECK_BOX);
        this.vpmGraphView = vpmGraphView;
        setToolTipText("Show single nodes without relationships");
        setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator.PLUGIN_ID, ICON_ACTIVE));
        setChecked(false);
    }

    @Override
    public void run() {

        if (this.vpmGraphView.getVpmGraph() == null) {
            logger.warn("No VPMGraph present in VPMGraph view.");
            return;
        }
        toggleNodes();
    }

    /**
     * Toggle the nodes ui.hide attribute. Add or remove the attribute depending on the provided
     * parameter.
     */
    private void toggleNodes() {

        if (isChecked()) {
            for (Node currentNode : this.vpmGraphView.getVpmGraph().getNodeSet()) {
                if (currentNode.getDegree() == 0) {
                    currentNode.addAttribute(NODE_ATTRIBUTE_HIDE);
                }
            }
        } else {
            for (Node currentNode : this.vpmGraphView.getVpmGraph().getNodeSet()) {
                if (currentNode.getDegree() == 0) {
                    currentNode.removeAttribute(NODE_ATTRIBUTE_HIDE);
                }
            }
        }
    }
}