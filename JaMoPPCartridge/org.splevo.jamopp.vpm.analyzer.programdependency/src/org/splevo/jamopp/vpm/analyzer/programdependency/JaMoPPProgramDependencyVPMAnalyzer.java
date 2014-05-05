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

import org.eclipse.emf.ecore.resource.Resource;
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

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

/**
 * Variation Point Analyzer identifying references between the software elements of variation points
 * (VP).
 *
 * The Analyzers main logic contains two steps:
 * <ol>
 * <li>Index referenced elements with the referencing variation point</li>
 * <li>Find clusters of variation points with the similar element reference not to be ignored</li>
 * </ol>
 *
 * Referenced elements are derived from the elements implementing the variants of a variation point.
 * This includes:
 * <ol>
 * <li>sub elements of the referring element that can be referenced by others (e.g. a method)</li>
 * <li>elements referenced by the referring element (e.g. a variable declared somewhere else).</li>
 * </ol>
 *
 * Which elements are treated as "referenced elements" is decided by the concrete
 * {@link ReferenceSelector} used.
 *
 */
public class JaMoPPProgramDependencyVPMAnalyzer extends AbstractVPMAnalyzer {

    /** The relationship label of the analyzer. */
    public static final String RELATIONSHIP_LABEL_PROGRAM_STRUCTURE = "ProgramDependency";

    // ---------------------------------
    // CONFIGURATIONS
    // ---------------------------------

    /** The configuration-object for filtering shared dependencies to external elements. */
    private BooleanConfiguration filterExternalsConfig = ConfigurationBuilder.createFilterExternalsConfig();

    /**
     * Internal map to simplify working with variation points and to reference back to nodes
     * afterwards.
     */
    private Map<VariationPoint, Node> vp2GraphNodeIndex;

    /** Index of which element is referenced by which variation points. */
    private LinkedHashMultimap<Commentable, VariationPoint> referencedElementsIndex;

    /**
     * A table to link:
     * <code> {@link VariationPoint} + a referenced {@link Commentable} => referring {@link Commentable}</code>
     * <br>
     * This is used to later lookup which element of a variation point holds the reference to the
     * referred one.
     */
    private Table<VariationPoint, Commentable, Commentable> referringElementIndex;

    /** Selector to get elements referenced from the sub elements of a AST node. */
    private ReferenceSelector referenceSelector = new DefaultReferenceSelector();

    /**
     * Analyze variation point dependencies based on program dependencies between them.
     *
     * DesignDecision: Using synchronized as analyzer instances are reused and this one is not
     * thread safe yet.
     *
     * {@inheritDoc}
     *
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)
     */
    @Override
    public synchronized VPMAnalyzerResult analyze(final VPMGraph vpmGraph) throws VPMAnalyzerException {

        referencedElementsIndex = LinkedHashMultimap.create();
        referringElementIndex = HashBasedTable.create();

        VPMAnalyzerResult result = new VPMAnalyzerResult(this);

        vp2GraphNodeIndex = buildGraphNodeIndex(vpmGraph);
        indexReferencedElements(vp2GraphNodeIndex.keySet());

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

        for (Commentable element : referencedElementsIndex.keySet()) {
            List<VPMEdgeDescriptor> vpEdges = identifyRelatedVPsForReferencedElement(edgeRegistry, element);
            edges.addAll(vpEdges);
        }

        return edges;
    }

    private List<VPMEdgeDescriptor> identifyRelatedVPsForReferencedElement(List<String> edgeRegistry,
            Commentable referencedElement) {

        List<VPMEdgeDescriptor> referencedElementEdges = Lists.newArrayList();

        Set<VariationPoint> referencingVPs = referencedElementsIndex.get(referencedElement);
        if (referencingVPs.size() > 1) {

            VariationPoint[] vpList = referencingVPs.toArray(new VariationPoint[referencingVPs.size()]);
            for (int i = 0; i < vpList.length; i++) {
                for (int j = i + 1; j < vpList.length; j++) {
                    VariationPoint vp1 = vpList[i];
                    VariationPoint vp2 = vpList[j];

                    if (vp1 == null || vp2 == null || vp2 == vp1) {
                        continue;
                    }

                    Commentable refElementVP1 = referringElementIndex.get(vp1, referencedElement);
                    Commentable refElementVP2 = referringElementIndex.get(vp2, referencedElement);

                    boolean ignoreReference = referenceSelector.ignoreReference(refElementVP1, refElementVP2, referencedElement);
                    if (ignoreReference) {
                        continue;
                    }

                    Node nodeVP1 = vp2GraphNodeIndex.get(vp1);
                    Node nodeVP2 = vp2GraphNodeIndex.get(vp2);
                    String vp1ID = nodeVP1.getId();
                    String vp2ID = nodeVP2.getId();
                    String sourceLabel = JaMoPPElementUtil.getLabel(refElementVP1);
                    String targetLabel = JaMoPPElementUtil.getLabel(refElementVP2);

                    String subLabel = getSubLabel(referencedElement, refElementVP1, refElementVP2, sourceLabel,
                            targetLabel);

                    VPMEdgeDescriptor edge = buildEdgeDescriptor(nodeVP1, nodeVP2, subLabel, edgeRegistry);
                    if (edge != null) {
                        logAnalysisInfo(vp1ID, vp2ID, sourceLabel, targetLabel, subLabel);
                        referencedElementEdges.add(edge);
                    }
                }
            }
        }
        return referencedElementEdges;
    }

    private String getSubLabel(Commentable referencedElement, Commentable refElementVP1, Commentable refElementVP2,
            String sourceLabel, String targetLabel) {
        String subLabel = null;
        String referencedElementLabel = JaMoPPElementUtil.getLabel(referencedElement);
        if (refElementVP1 == referencedElement) {
            subLabel = String.format("%s references %s", targetLabel, referencedElementLabel);

        } else if (refElementVP2 == referencedElement) {
            subLabel = String.format("%s references %s", sourceLabel, referencedElementLabel);

        } else {
            subLabel = String.format("%s and %s share references to %s", sourceLabel, targetLabel,
                    referencedElementLabel);
        }
        return subLabel;
    }

    /**
     * Index all elements referenced by the elements implementing the variants of a variation point.
     * This is done separately for each variation point of the provided set.
     *
     * @param variationPoints
     *            The set of variation points to index the implementing elements of.
     */
    private void indexReferencedElements(Set<VariationPoint> variationPoints) {
        for (VariationPoint vp : variationPoints) {
            List<Commentable> jamoppElements = getJamoppElements(vp);
            for (Commentable jamoppElement : jamoppElements) {
                indexReferencedElements(vp, jamoppElement);
            }
        }
    }

    /**
     * Index all referenced elements of a referring JaMoPP element.
     *
     * This includes:
     * <ol>
     * <li>sub elements of the referring element that can be referenced by others (e.g. a method)</li>
     * <li>elements referenced by the referring element (e.g. a variable declared somewhere else).</li>
     * </ol>
     *
     * @param vp
     *            The variation point the element is included.
     * @param referringElement
     *            The JaMoPP element to find referenced or referencable elements.
     */
    private void indexReferencedElements(VariationPoint vp, Commentable referringElement) {
        List<Commentable> referencedElements = referenceSelector.getReferencedElements(referringElement);
        for (Commentable referencedElement : referencedElements) {

            // filter external elements (i.e. contained in binary resources)
            if (filterExternalsConfig.getCurrentValue()) {
                Resource resource = referencedElement.eResource();
                if (resource != null && "pathmap".equals(resource.getURI().scheme())) {
                    continue;
                }
            }

            referencedElementsIndex.get(referencedElement).add(vp);
            referringElementIndex.put(vp, referencedElement, referringElement);
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
     *            The variation point to get the software elements for.
     * @return The list of implementing software elments.
     */
    private List<SoftwareElement> getVariantsSoftwareElements(VariationPoint vp) {
        List<SoftwareElement> softwareElements = new ArrayList<SoftwareElement>();

        for (Variant v : vp.getVariants()) {
            for (SoftwareElement softwareElement : v.getImplementingElements()) {
                softwareElements.add(softwareElement);
            }
        }

        return softwareElements;
    }

    /**
     * Build the inverted index, mapping a variation point to the graph node it is contained in.
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
        configurations.addConfigurations(ConfigurationBuilder.CONFIG_GROUP_DEPENDENCIES, filterExternalsConfig);
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
