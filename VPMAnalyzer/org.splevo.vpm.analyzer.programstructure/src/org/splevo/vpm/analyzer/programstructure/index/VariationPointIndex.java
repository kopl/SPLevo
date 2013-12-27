/*******************************************************************************
 * Copyright (c) 2013
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.vpm.analyzer.programstructure.index;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.programstructure.Activator;
import org.splevo.vpm.analyzer.programstructure.ProgramStructureProvider;
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
    private Map<VariationPoint, Node> vp2GraphNodeIndex = null;

    /** An index mapping all AST nodes enclosed by a variation point to the enclosing variation point. */
    private Map<SoftwareElement, VariationPoint> softwareElement2VPIndex = null;

    /**
     * Index the nodes and variation points of the supplied variation point model graph.
     *
     * @param vpmGraph
     *            The graph to fill the index from.
     */
    public void index(VPMGraph vpmGraph) {
        logger.info("Start build graph node index");
        vp2GraphNodeIndex = buildGraphNodeIndex(vpmGraph);
        logger.info("Start build enclosed ast node index");
        softwareElement2VPIndex = buildEnclosedASTNodeIndex(vp2GraphNodeIndex.keySet());
    }

    /**
     * Get a list of all indexed variation points.
     *
     * @return The variation point list.
     */
    public List<VariationPoint> getVariationPoints() {
        return new ArrayList<VariationPoint>(vp2GraphNodeIndex.keySet());
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
        return vp2GraphNodeIndex.get(vp);
    }

    /**
     * Build the inverted index mapping a variation point to the graph node it is contained in.
     *
     * @param vpmGraph
     *            The graph to index.
     * @return The prepared inverted index.
     */
    private Map<VariationPoint, Node> buildGraphNodeIndex(VPMGraph vpmGraph) {
        Map<VariationPoint, Node> index = new LinkedHashMap<VariationPoint, Node>();

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
    private Map<SoftwareElement, VariationPoint> buildEnclosedASTNodeIndex(Collection<VariationPoint> variationPoints) {

        Map<SoftwareElement, VariationPoint> index = new LinkedHashMap<SoftwareElement, VariationPoint>();

        for (VariationPoint vp : variationPoints) {
            for (SoftwareElement se : getVariantsSoftwareElements(vp)) {
                indexEnclosedSoftwareElements(index, se, vp);
            }
        }

        return index;
    }

    /**
     * Software elements can have child elements (e.g. a method containing statements). This method
     * indexes all sub elements.
     *
     * @param index
     *            The index map to add the node to.
     * @param softwareElement
     *            The AST node to index.
     * @param vp
     *            The variation point to map the node to.
     */
    private void indexEnclosedSoftwareElements(Map<SoftwareElement, VariationPoint> index,
            SoftwareElement softwareElement, VariationPoint vp) {
        index.put(softwareElement, vp);

        for (ProgramStructureProvider provider : Activator.getProgramStructureProviders()) {
            List<SoftwareElement> subElements = provider.getRelevantSubElements(softwareElement);
            for (SoftwareElement subElement : subElements) {
                indexEnclosedSoftwareElements(index, subElement, vp);
            }
        }
    }

    /**
     * Collect all AST nodes implementing a variant of a Variation Point.
     *
     * @param vp
     *            The variation point to get the ASTNodes for.
     * @return A list of all referenced ASTNodes.
     */
    public List<SoftwareElement> getVariantsSoftwareElements(VariationPoint vp) {
        List<SoftwareElement> softwareElements = new ArrayList<SoftwareElement>();

        for (Variant v : vp.getVariants()) {

            for (SoftwareElement softwareElement : v.getSoftwareEntities()) {
                softwareElements.add(softwareElement);
            }
        }

        return softwareElements;
    }

    /**
     * Lookup the enclosing variation point for an AST node. This might be null if the provided AST
     * nodes is not part of a variation point.
     *
     * @param softwareElement
     *            The SoftwareElement to look up the variation point for.
     * @return The enclosing variation point or null if none exist.
     */
    public VariationPoint getEnclosingVariationPoint(SoftwareElement softwareElement) {
        return softwareElement2VPIndex.get(softwareElement);
    }

}
