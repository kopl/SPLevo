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
package org.splevo.ui.refinementbrowser;

import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.draw2d.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
import org.splevo.ui.SPLevoUIPlugin;
import org.splevo.ui.sourceconnection.SourceEditorConnector;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementReason;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

/**
 * Widget for a graphical presentation of a refinement recommendation.
 */
public class RefinementGraph extends Graph {

    private static final Color COLOR_CONNECTION_HIGHLIGHT = SPLevoUIPlugin.COLOR_ORANGE;
    private static final Color COLOR_CONNECTION_FOCUSED = SPLevoUIPlugin.COLOR_MEDIUM_BLUE;
    private static final Color COLOR_CONNECTION_NOTFOCUSED = SPLevoUIPlugin.COLOR_LIGHT_BLUE;
    private static final Color COLOR_NODE_HIGHLIGHT = SPLevoUIPlugin.COLOR_ORANGE;
    private static final Color COLOR_NODE_BACKGROUND_FOCUSED = SPLevoUIPlugin.COLOR_MEDIUM_BLUE;
    private static final Color COLOR_NODE_BACKGROUND_NOTFOCUSED = SPLevoUIPlugin.COLOR_LIGHT_BLUE;

    private static final String NODE_DATA_KEY_VP = "node.data.key.vp";

    private LinkedHashMap<VariationPoint, GraphNode> nodeIndex = Maps.newLinkedHashMap();
    private Refinement focusedRefinement = null;
    private Multimap<GraphNode, GraphNode> connectedNodes = LinkedHashMultimap.create();

    /**
     * Instantiates a new refinement graph.
     *
     * @param parent
     *            the parent
     * @param style
     *            SWT style settings to apply to the container.
     */
    RefinementGraph(Composite parent, int style) {
        super(parent, style);

        addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                for (Object conObject : getConnections()) {
                    GraphConnection con = (GraphConnection) conObject;
                    con.unhighlight();
                }

                for (Object selectedItem : getSelection()) {
                    if (selectedItem instanceof GraphNode) {
                        GraphNode node = (GraphNode) selectedItem;
                        hightlightConnections(node);
                    }
                }
            }

            @SuppressWarnings("unchecked")
            private void hightlightConnections(GraphNode node) {
                List<Object> connections = Lists.newArrayList();
                connections.addAll(node.getSourceConnections());
                connections.addAll(node.getTargetConnections());
                for (Object conObject : connections) {
                    GraphConnection con = (GraphConnection) conObject;
                    con.highlight();
                }
            }

        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDoubleClick(MouseEvent e) {
                List<String> openFiles = Lists.newArrayList();
                for (Object selectedItem : getSelection()) {
                    if (selectedItem instanceof GraphNode) {
                        GraphNode node = (GraphNode) selectedItem;
                        if (node.getData(NODE_DATA_KEY_VP) != null) {
                            VariationPoint vp = (VariationPoint) node.getData(NODE_DATA_KEY_VP);
                            SourceEditorConnector.openVariationPoint(vp, openFiles);
                        }
                    }
                }

            }
        });
    }

    /**
     * Show a refinement in the graph view.
     *
     * If a sub refinement is selected, the complete parent refinement will be displayed but only
     * the sub refinement is focused.
     *
     * @param refinement
     *            The refinement to present
     */
    public void show(Refinement refinement) {
        clear();
        focusedRefinement = refinement;
        if (refinement.eContainer() instanceof Refinement) {
            createGraphNodes((Refinement) refinement.eContainer());
        } else {
            createGraphNodes(refinement);
        }
        this.setLayoutAlgorithm(new SpringLayoutAlgorithm(LayoutStyles.ENFORCE_BOUNDS
                | LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
        this.layout();
    }

    private void createGraphNodes(Refinement refinement) {

        for (VariationPoint vp : refinement.getVariationPoints()) {
            createNode(vp);
        }

        for (Refinement subRefinement : refinement.getSubRefinements()) {
            createGraphNodes(subRefinement);
        }

        createConnections(refinement);
    }

    private void createNode(VariationPoint vp) {
        GraphNode vpNode = new GraphNode(this, ZestStyles.NODES_HIDE_TEXT, "VP", SPLevoUIPlugin.KOPL_ICON);
        vpNode.setData(NODE_DATA_KEY_VP, vp);

        if (isFocused(vp)) {
            vpNode.setBackgroundColor(COLOR_NODE_BACKGROUND_FOCUSED);
        } else {
            vpNode.setBackgroundColor(COLOR_NODE_BACKGROUND_NOTFOCUSED);
        }
        vpNode.setHighlightColor(COLOR_NODE_HIGHLIGHT);
        vpNode.setBorderHighlightColor(vpNode.getBorderColor());
        vpNode.setTooltip(new Label(vp.getLocation().getLabel()));
        nodeIndex.put(vp, vpNode);
    }

    private void createConnections(Refinement refinement) {
        int connectionStyle = ZestStyles.CONNECTIONS_DIRECTED;
        for (RefinementReason reason : refinement.getReasons()) {

            VariationPoint sourceVP = reason.getSource();
            VariationPoint targetVP = reason.getTarget();

            GraphNode source = nodeIndex.get(sourceVP);
            GraphNode target = nodeIndex.get(targetVP);

            if (!isValidNewConnection(source, target)) {
                continue;
            }

            GraphConnection connection = new GraphConnection(this, connectionStyle, source, target);
            connection.setHighlightColor(COLOR_CONNECTION_HIGHLIGHT);
            if (!areFocused(sourceVP, targetVP)) {
                connection.setLineStyle(SWT.LINE_DASH);
                connection.setLineColor(COLOR_CONNECTION_NOTFOCUSED);
            } else {
                connection.setLineColor(COLOR_CONNECTION_FOCUSED);
            }
            connection.setTooltip(new Label(reason.getReason()));
            connectedNodes.put(source, target);
        }
    }

    private boolean isValidNewConnection(GraphNode source, GraphNode target) {
        if (source == null || target == null) {
            return false;
        }
        if (connectedNodes.get(source).contains(target)) {
            return false;
        }
        if (connectedNodes.get(target).contains(source)) {
            return false;
        }
        return true;
    }

    private boolean areFocused(VariationPoint vp1, VariationPoint vp2) {
        return isFocused(vp1) && isFocused(vp2);
    }

    private boolean isFocused(VariationPoint vp) {
        return focusedRefinement.getVariationPoints().contains(vp);
    }

    /**
     * Clear the graph.
     */
    public void clear() {
        nodeIndex.clear();
        connectedNodes.clear();
        focusedRefinement = null;

        Object[] nodeArray = getNodes().toArray();
        for (Object nodeObj : nodeArray) {
            ((GraphNode) nodeObj).dispose();
        }
        Object[] connectionArray = getConnections().toArray();
        for (Object connectionObj : connectionArray) {
            ((GraphConnection) connectionObj).dispose();
        }

        this.layout();

    }

}
