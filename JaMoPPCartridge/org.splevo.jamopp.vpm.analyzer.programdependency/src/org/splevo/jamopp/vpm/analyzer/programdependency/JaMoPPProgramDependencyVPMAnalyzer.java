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

import org.eclipse.emf.ecore.EObject;
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
 * The logic contains the steps:
 * <ol>
 * <li>Index variation points and contained referencable software elements</li>
 * <li>For each variation point, check if it contains a software element that refers to one of the
 * indexed ones</li>
 * <li>If a hit is found, create a relationship between the according nodes.</li>
 * </ol>
 */
public class JaMoPPProgramDependencyVPMAnalyzer extends AbstractVPMAnalyzer {

    /** The relationship label of the analyzer. */
    public static final String RELATIONSHIP_LABEL_PROGRAM_STRUCTURE = "ProgramDependency";

    // ---------------------------------
    // CONFIGURATIONS
    // ---------------------------------

    /** The configuration-object for the log indexed terms configuration. */
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
    private ReferencedElementSelector referencedElementSelector = new DefaultReferencedElementSelector();

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

        for (Commentable referencedElement : referencedElementsIndex.keySet()) {
            Set<VariationPoint> referencingVPs = referencedElementsIndex.get(referencedElement);
            if (referencingVPs.size() > 1) {

                String referencedElementLabel = JaMoPPElementUtil.getLabel(referencedElement);

                // link all pairs of VPs referencing the same Commentable with each other
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

                        Node nodeVP1 = vp2GraphNodeIndex.get(vp1);
                        Node nodeVP2 = vp2GraphNodeIndex.get(vp2);

                        String vp1ID = nodeVP1.getId();
                        String vp2ID = nodeVP2.getId();
                        String sourceLabel = JaMoPPElementUtil.getLabel(refElementVP1);
                        String targetLabel = JaMoPPElementUtil.getLabel(refElementVP2);

                        boolean ignoreReference = ignoreReference(refElementVP1, refElementVP2, referencedElement);
                        if (ignoreReference) {
                            continue;
                        }

                        String subLabel;
                        if (refElementVP1 == referencedElement) {
                            subLabel = String.format("%s references %s", targetLabel, referencedElementLabel);

                        } else if (refElementVP2 == referencedElement) {
                            subLabel = String.format("%s references %s", sourceLabel, referencedElementLabel);

                        } else {
                            subLabel = String.format("%s and %s share references to %s", sourceLabel, targetLabel,
                                    referencedElementLabel);
                        }

                        VPMEdgeDescriptor edge = buildEdgeDescriptor(nodeVP1, nodeVP2, subLabel, edgeRegistry);
                        if (edge != null) {
                            logAnalysisInfo(vp1ID, vp2ID, sourceLabel, targetLabel, subLabel);
                            edges.add(edge);
                        }

                    }
                }

            }

        }

        return edges;
    }

    /**
     * Check if the reference between the two referring and the referenced element should be ignored
     * or not.
     *
     * If one of the source elements specifies the target, the reference must be included.
     *
     * @param source1
     *            The first referring element.
     * @param source2
     *            The second referring element.
     * @param target
     *            The reference target.
     * @return True if the reference should be ignored, false if it must be considered.
     */
    private boolean ignoreReference(Commentable source1, Commentable source2, Commentable target) {

        if (source1 == source2) {
            return true;
        }
        // TODO Implement ignore check
        if (isParentOf(source1, target)) {
            return false;
        }
        if (isParentOf(source2, target)) {
            return false;
        }

        return false;
    }

    /**
     * Check if a candidate is in the container hierarchy of the child.
     *
     * @param parentCandidate
     *            The candidate to check.
     * @param child
     *            The child to prove the hierarchy of.
     * @return True/false depending on parent relationship exists or not.
     */
    private boolean isParentOf(Commentable parentCandidate, Commentable child) {

        EObject container = child;
        while (container != null) {

            if (container == parentCandidate) {
                return true;
            }

            container = container.eContainer();
        }

        return false;
    }

    /**
     * Index all referable elements realizing one of the variation points variants linked to the
     * containing variation point.
     *
     * @param variationPoints
     *            The list of variation points to index the elements of.
     */
    private void indexReferableElements(Set<VariationPoint> variationPoints) {
        for (VariationPoint vp : variationPoints) {
            List<Commentable> jamoppElements = getJamoppElements(vp);
            for (Commentable jamoppElement : jamoppElements) {
                indexReferncedElements(vp, jamoppElement);
            }
        }
    }

    /**
     * Index all (sub-)elements of a JaMoPP element that can be referenced by another JaMoPP
     * element.
     *
     * That means:
     * <ol>
     * <li>if the element itself can be referenced by another software element it will be included
     * (e.g. an import or declared class)</li>
     * <li>if the element references another element, this other element will be included (e.g. a
     * variable or the import of a used class).</li>
     * </ol>
     *
     * @param vp
     *            The variation point the element is included.
     * @param referringElement
     *            The JaMoPP element to find referenced or referencable elements.
     */
    private void indexReferncedElements(VariationPoint vp, Commentable referringElement) {
        List<Commentable> referencedElements = referencedElementSelector.getReferencedElements(referringElement);
        for (Commentable referencedElement : referencedElements) {

            // filter non source code elements
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
     *            The variation point to get the ASTNodes for.
     * @return A list of all referenced ASTNodes.
     */
    public List<SoftwareElement> getVariantsSoftwareElements(VariationPoint vp) {
        List<SoftwareElement> softwareElements = new ArrayList<SoftwareElement>();

        for (Variant v : vp.getVariants()) {
            for (SoftwareElement softwareElement : v.getImplementingElements()) {
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
