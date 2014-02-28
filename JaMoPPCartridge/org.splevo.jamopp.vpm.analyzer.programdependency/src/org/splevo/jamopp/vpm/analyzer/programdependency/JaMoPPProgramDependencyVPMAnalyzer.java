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
package org.splevo.jamopp.vpm.analyzer.programdependency;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.emftext.language.java.commons.Commentable;
import org.graphstream.graph.Node;
import org.splevo.jamopp.util.JaMoPPElementUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.analyzer.AbstractVPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerException;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.config.BooleanConfiguration;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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
public class JaMoPPProgramDependencyVPMAnalyzer extends AbstractVPMAnalyzer {

    private static final boolean DEFAULT_CONFIG_FULL_REFERRED_TREE = true;

    /** Label of the configuration to consider the full sub AST for the referred VP. */
    private static final String CONFIG_LABEL_FULL_REFERRED_TREE = "Check Full Referred AST";

    /** The relationship label of the analyzer. */
    public static final String RELATIONSHIP_LABEL_PROGRAM_STRUCTURE = "ProgramDependency";
    /**
     * Internal map to simplify working with variation points and to reference back to nodes
     * afterwards.
     */
    private Map<VariationPoint, Node> vp2GraphNodeIndex = null;

    /** The child node traverser identifying the refereable sub elements. */
    private RefereableElementSelector refereableElementSelector = new RefereableElementSelector();

    /** The index of variation points and AST nodes. **/
    private Map<Commentable, VariationPoint> referableElementIndex = Maps.newLinkedHashMap();

    /** Selector to get elements referenced from the sub elements of a AST node. */
    private ReferencedElementSelector referencedElementSelector = new ReferencedElementSelector();

    /**
     * {@inheritDoc}
     *
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)
     */
    @Override
    public VPMAnalyzerResult analyze(final VPMGraph vpmGraph) throws VPMAnalyzerException {

        VPMAnalyzerResult result = new VPMAnalyzerResult(this);

        vp2GraphNodeIndex = buildGraphNodeIndex(vpmGraph);
        indexReferableElements(vp2GraphNodeIndex.keySet());

        List<VPMEdgeDescriptor> descriptors = identifyDependencies(vp2GraphNodeIndex.keySet());
        result.getEdgeDescriptors().addAll(descriptors);

        return result;
    }

    /**
     * Identify the dependencies between the variation points based on referring and referred
     * elements.<br>
     * Build and return an edge descriptor for each of those pairs.
     *
     * @param vps
     *            The variation points to search related ones for.
     * @return The list of identified edge descriptors.
     */
    private List<VPMEdgeDescriptor> identifyDependencies(Set<VariationPoint> vps) {
        List<VPMEdgeDescriptor> edges = Lists.newArrayList();
        List<String> edgeRegistry = new ArrayList<String>();
        for (VariationPoint referringVP : vps) {
            List<Commentable> jamoppElements = getJamoppElements(referringVP);
            for (Commentable referringElement : jamoppElements) {
                List<Commentable> referencedElements = referencedElementSelector
                        .getReferencedElements(referringElement);
                for (Commentable referredElement : referencedElements) {
                    VariationPoint referredVP = referableElementIndex.get(referredElement);
                    if (referredVP != null && referredVP != referringVP) {

                        String vp1ID = vp2GraphNodeIndex.get(referringVP).getId();
                        String vp2ID = vp2GraphNodeIndex.get(referredVP).getId();
                        String sourceLabel = JaMoPPElementUtil.getLabel(referringElement);
                        String targetLabel = JaMoPPElementUtil.getLabel(referredElement);
                        logAnalysisInfo(vp1ID, vp2ID, sourceLabel, targetLabel);

                        Node sourceNode = vp2GraphNodeIndex.get(referredVP);
                        Node targetNode = vp2GraphNodeIndex.get(referringVP);
                        VPMEdgeDescriptor edge = buildEdgeDescriptor(sourceNode, targetNode, targetLabel, edgeRegistry);
                        if (edge != null) {
                            edges.add(edge);
                        }

                    }
                }
            }
        }
        return edges;
    }

    /**
     * Index all referable elements realizing on of the variation points variants linked to the
     * containing variaition point.
     *
     * @param variationPoints
     *            The list of variation points to index the elements of.
     */
    private void indexReferableElements(Set<VariationPoint> variationPoints) {
        for (VariationPoint vp : variationPoints) {
            List<Commentable> jamoppElements = getJamoppElements(vp);
            for (Commentable jamoppElement : jamoppElements) {
                indexReferableElements(vp, jamoppElement);
            }
        }
    }

    /**
     * Index all referable (sub-)elements of a JaMoPP element with a reference to the variation
     * point they are included in.
     *
     * @param vp
     *            The variation point the element is included.
     * @param jamoppElement
     *            The JaMoPP element to analyze for referencable elements.
     */
    private void indexReferableElements(VariationPoint vp, Commentable jamoppElement) {
        List<Commentable> referableElements = refereableElementSelector.getReferableElements(jamoppElement);
        for (Commentable commentable : referableElements) {
            referableElementIndex.put(commentable, vp);
        }
    }

    /**
     * Get all JaMoPP elements referenced by software elements implementing a variant of a variation
     * point.
     *
     * @param vp
     *            The variation point to get the elements for.
     * @return The list of referenced JaMoPP elements.
     */
    private List<Commentable> getJamoppElements(VariationPoint vp) {
        List<SoftwareElement> softwareElements = getVariantsSoftwareElements(vp);
        List<Commentable> jamoppElements = Lists.newArrayList();
        for (SoftwareElement softwareElement : softwareElements) {
            if (softwareElement instanceof JaMoPPSoftwareElement) {
                jamoppElements.add(((JaMoPPSoftwareElement) softwareElement).getJamoppElement());
            }
        }
        return jamoppElements;
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
        return "JaMoPP Program Dependency Analyzer";
    }

    @Override
    public String getRelationshipLabel() {
        return RELATIONSHIP_LABEL_PROGRAM_STRUCTURE;
    }

}
