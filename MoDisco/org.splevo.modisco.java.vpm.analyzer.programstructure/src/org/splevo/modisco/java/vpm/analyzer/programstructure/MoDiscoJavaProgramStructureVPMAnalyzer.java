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
package org.splevo.modisco.java.vpm.analyzer.programstructure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.AbstractVPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerException;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.config.BooleanConfiguration;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Variation Point Analyzer identifying references between the software elements of variation points
 * (VP).
 *
 * The logic contains the steps:
 * <ol>
 * <li>Index variation points and contained refereable software elements</li>
 * <li>For each variation point, check if it contains a software element that refers to one of the
 * indexed ones</li>
 * <li>If a hit is found, create a relationship between the according nodes.</li>
 * </ol>
 */
public class MoDiscoJavaProgramStructureVPMAnalyzer extends AbstractVPMAnalyzer {

    private static final boolean DEFAULT_CONFIG_FULL_REFERRED_TREE = true;

    /** Label of the configuration to consider the full sub AST for the referred VP. */
    private static final String CONFIG_LABEL_FULL_REFERRED_TREE = "Check Full Referred AST";

    /** The relationship label of the analyzer. */
    public static final String RELATIONSHIP_LABEL_PROGRAM_STRUCTURE = "ProgramStructure";

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(MoDiscoJavaProgramStructureVPMAnalyzer.class);

    /**
     * Internal map to simplify working with variation points and to reference back to nodes
     * afterwards.
     */
    private Map<VariationPoint, Node> vp2GraphNodeIndex = null;

    /** The index of variation points and AST nodes. **/
    private ReferableSoftwareElementIndex referableElementIndex = null;

    /**
     * Constructor initializing the variation point analyzer.
     */
    public MoDiscoJavaProgramStructureVPMAnalyzer() {
        referableElementIndex = new ReferableSoftwareElementIndex();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)
     */
    @Override
    public VPMAnalyzerResult analyze(final VPMGraph vpmGraph) throws VPMAnalyzerException {
        vp2GraphNodeIndex = buildGraphNodeIndex(vpmGraph);
        referableElementIndex.index(vp2GraphNodeIndex.keySet());
        return detectReferringVPs();
    }

    /**
     * Analyzes variation points for pairs with a VariableDeclarationStatement that declares a
     * variable that is used by another node later on.
     *
     * This has to work on the variant level to really analyze the variable declaration statement
     * and not the enclosing block.
     *
     * @return The result of the analysis.
     */
    private VPMAnalyzerResult detectReferringVPs() {

        VPMAnalyzerResult result = new VPMAnalyzerResult(this);

        for (VariationPoint referencedVP : vp2GraphNodeIndex.keySet()) {

            List<SoftwareElement> referableElements = referableElementIndex.getVariantsSoftwareElements(referencedVP);

            for (SoftwareElement referable : referableElements) {
                List<VPMEdgeDescriptor> descriptors = identifyReferingRelationships(referencedVP, referable);
                result.getEdgeDescriptors().addAll(descriptors);
            }
        }

        return result;
    }

    /**
     * Identify references to a variation point based on a specific refered software element.
     *
     * @param referredVP
     *            The refered variation point.
     * @param referredElement
     *            The refered element.
     * @return The list of identified reference edge descriptors.
     */
    private List<VPMEdgeDescriptor> identifyReferingRelationships(VariationPoint referredVP,
            SoftwareElement referredElement) {

        List<VPMEdgeDescriptor> descriptors = new ArrayList<VPMEdgeDescriptor>();

        List<VariationPoint> referringVPs = findReferringVariationPoints(referredVP, referredElement);
        List<String> edgeRegistry = new ArrayList<String>();
        for (VariationPoint referringVP : referringVPs) {
            Node sourceNode = vp2GraphNodeIndex.get(referredVP);
            Node targetNode = vp2GraphNodeIndex.get(referringVP);

            VPMEdgeDescriptor descriptor = buildEdgeDescriptor(sourceNode, targetNode, referredElement.getLabel(), edgeRegistry);
            if (descriptor != null) {
                descriptors.add(descriptor);
            }
        }
        return descriptors;
    }

    /**
     * Search for variation points referencing AST nodes influenced by a variable declaration
     * statement.
     *
     * @param referredVP
     *            The variation point to find relationships for.
     * @param referredElement
     *            The AST node to find references for.
     * @return The list of referring variation points.
     */
    private List<VariationPoint> findReferringVariationPoints(VariationPoint referredVP, SoftwareElement referredElement) {

        List<VariationPoint> variationPoints = new ArrayList<VariationPoint>();

        List<SoftwareElement> referringElements = new ArrayList<SoftwareElement>();
        MoDiscoJavaProgramStructureProvider provider = new MoDiscoJavaProgramStructureProvider();
        List<SoftwareElement> elements = provider.getReferringSoftwareElements(referredElement);
        referringElements.addAll(elements);

        for (SoftwareElement referringSoftwareElement : referringElements) {
            VariationPoint relatedVariationPoint = referableElementIndex
                    .getEnclosingVariationPoint(referringSoftwareElement);
            if (relatedVariationPoint != null) {
                variationPoints.add(relatedVariationPoint);

                String vp1ID = vp2GraphNodeIndex.get(referredVP).getId();
                String vp2ID = vp2GraphNodeIndex.get(relatedVariationPoint).getId();
                String sourceInfo = referredElement.getLabel();
                String targetInfo = referringSoftwareElement.getLabel();
                logAnalysisInfo(vp1ID, vp2ID, sourceInfo, targetInfo);
            } else {
                logger.warn("Referring SoftwareElement not in index: " + referredElement.getLabel());
            }
        }

        return variationPoints;
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

    @Override
    public VPMAnalyzerConfigurationSet getConfigurations() {
        VPMAnalyzerConfigurationSet configurations = new VPMAnalyzerConfigurationSet();
        BooleanConfiguration fullReferredTreeConfig = new BooleanConfiguration(CONFIG_LABEL_FULL_REFERRED_TREE, null,
                DEFAULT_CONFIG_FULL_REFERRED_TREE);
        configurations.addConfigurations("General Settings", fullReferredTreeConfig);

        return configurations;
    }

    @Override
    public String getName() {
        return "MoDisco Program Structure VPM Analyzer";
    }

    @Override
    public String getRelationshipLabel() {
        return RELATIONSHIP_LABEL_PROGRAM_STRUCTURE;
    }

}
