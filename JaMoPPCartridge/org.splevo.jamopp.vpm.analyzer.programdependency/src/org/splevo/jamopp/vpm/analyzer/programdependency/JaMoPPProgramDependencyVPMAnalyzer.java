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

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.commons.Commentable;
import org.graphstream.graph.Node;
import org.splevo.jamopp.util.JaMoPPElementUtil;
import org.splevo.jamopp.vpm.analyzer.programdependency.references.DependencyType;
import org.splevo.jamopp.vpm.analyzer.programdependency.references.Reference;
import org.splevo.jamopp.vpm.analyzer.programdependency.references.ReferenceSelector;
import org.splevo.jamopp.vpm.analyzer.programdependency.references.ReferenceSelectorRegistry;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.analyzer.AbstractVPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerException;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.config.BooleanConfiguration;
import org.splevo.vpm.analyzer.config.ChoiceConfiguration;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
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

    private static Logger logger = Logger.getLogger(JaMoPPProgramDependencyVPMAnalyzer.class);

    /** The relationship label of the analyzer. */
    public static final String RELATIONSHIP_LABEL_PROGRAM_STRUCTURE = "ProgramDependency";

    /** Key of the edge descriptor info containing the dependency type. */
    public static final String EDGE_INFO_DEPENDENCY_TYPE = "dependency.type";

    // ---------------------------------
    // CONFIGURATIONS
    // ---------------------------------

    /** The configuration-object for filtering shared dependencies to external elements. */
    private BooleanConfiguration filterExternalsConfig = ConfigurationBuilder.createFilterExternalsConfig();

    /** The configuration-object for the reference selector to use. */
    private ChoiceConfiguration referenceSelectorConfig = ConfigurationBuilder.createReferenceSelectorConfig();

    /** If not null, a specific dependency type to focus on. */
    private ChoiceConfiguration desiredDependencyTypeConfig = ConfigurationBuilder.createDesiredDependencyTypeConfig();

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

        String selectorId = referenceSelectorConfig.getCurrentValue();
        ReferenceSelector referenceSelector = ReferenceSelectorRegistry.getReferenceSelector(selectorId);

        VPReferenceIndex index = indexVPReferences(vpmGraph, referenceSelector);

        List<VPMEdgeDescriptor> descriptors = identifyDependencies(referenceSelector, index);

        VPMAnalyzerResult result = new VPMAnalyzerResult(this);
        result.getEdgeDescriptors().addAll(descriptors);
        logger.info("ReferenceSelector Mode: " + selectorId);
        return result;
    }

    private VPReferenceIndex indexVPReferences(final VPMGraph vpmGraph, ReferenceSelector referenceSelector) {
        VPReferenceIndex index = new VPReferenceIndex();
        indexGraphNodes(vpmGraph, index);
        indexReferencedElements(referenceSelector, index);
        return index;
    }

    /**
     * Identify the dependencies between the variation points based on referring and referred
     * elements.<br>
     * Build and return an edge descriptor for each of those pairs.
     *
     * @param referenceSelector
     *            The selector for the references to consider.
     * @param index
     *            The index containing previously identified element references.
     * @return The list of identified edge descriptors.
     */
    private List<VPMEdgeDescriptor> identifyDependencies(ReferenceSelector referenceSelector, VPReferenceIndex index) {
        List<VPMEdgeDescriptor> edges = Lists.newArrayList();
        List<String> edgeRegistry = new ArrayList<String>();
        Multiset<DependencyType> statistics = LinkedHashMultiset.create();

        for (Commentable element : index.referencedElementsIndex.keySet()) {
            List<VPMEdgeDescriptor> vpEdges = identifyRelatedVPsForReferencedElement(edgeRegistry, element,
                    referenceSelector, index, statistics);
            edges.addAll(vpEdges);
        }

        printStatistics(statistics);
        return edges;
    }

    private void printStatistics(Multiset<DependencyType> statistics) {
        StringBuilder builder = new StringBuilder();
        builder.append("Statistics:");
        for (DependencyType type : statistics.elementSet()) {
            builder.append("\n");
            builder.append(type + "\t" + statistics.count(type));
        }
        logger.debug(builder.toString());
    }

    private List<VPMEdgeDescriptor> identifyRelatedVPsForReferencedElement(List<String> edgeRegistry,
            Commentable referencedElement, ReferenceSelector referenceSelector, VPReferenceIndex index,
            Multiset<DependencyType> statistics) {

        List<VPMEdgeDescriptor> referencedElementEdges = Lists.newArrayList();

        Set<VariationPoint> referencingVPs = index.referencedElementsIndex.get(referencedElement);

        if (referencingVPs.size() > 1) {

            VariationPoint[] vpList = referencingVPs.toArray(new VariationPoint[referencingVPs.size()]);
            for (int i = 0; i < vpList.length; i++) {
                for (int j = i + 1; j < vpList.length; j++) {
                    VariationPoint vp1 = vpList[i];
                    VariationPoint vp2 = vpList[j];

                    if (vp1 == null || vp2 == null || vp2 == vp1) {
                        continue;
                    }

                    // every VP can have more than one reference to a referenced element
                    // so we need to search for acceptable ones
                    // To track statistics about the analysis, we must scan all dependencies even
                    // if the first one would be enough for identifying a dependency.
                    // FIXME: Implementation must be cleaned up
                    DependencyType dependencyType = DependencyType.IGNORE;
                    Reference referenceVP1 = null;
                    Reference referenceVP2 = null;
                    for (Reference refVP1 : index.getIndexedReferences(vp1, referencedElement)) {
                        for (Reference refVP2 : index.getIndexedReferences(vp2, referencedElement)) {
                            DependencyType type = referenceSelector
                                    .getDependencyType(refVP1, refVP2, referencedElement);
                            if (isDesiredType(type)) {
                                statistics.add(type);
                                dependencyType = type;
                                referenceVP1 = refVP1;
                                referenceVP2 = refVP2;
                            }
                        }
                    }
                    // if we did not find a reasonable VP dependency continue with the next VP pair.
                    if (dependencyType == DependencyType.IGNORE) {
                        continue;
                    }

                    Node nodeVP1 = index.vp2GraphNodeIndex.get(vp1);
                    Node nodeVP2 = index.vp2GraphNodeIndex.get(vp2);
                    String vp1ID = nodeVP1.getId();
                    String vp2ID = nodeVP2.getId();

                    // the source of the second VP forms the target label as we know
                    // talk about source and target of the VP relationship and no longer about
                    // dependencies between the software elements.
                    String sourceLabel = JaMoPPElementUtil.getLabel(referenceVP1.getSource());
                    String targetLabel = JaMoPPElementUtil.getLabel(referenceVP2.getSource());

                    String subLabel = getSubLabel(referencedElement, referenceVP1.getSource(),
                            referenceVP2.getSource(), sourceLabel, targetLabel);

                    VPMEdgeDescriptor edge = buildEdgeDescriptor(nodeVP1, nodeVP2, subLabel, edgeRegistry);
                    if (edge != null) {
                        edge.getRelationShipInfos().put(EDGE_INFO_DEPENDENCY_TYPE, dependencyType);
                        logAnalysisInfo(vp1ID, vp2ID, sourceLabel, targetLabel, subLabel);
                        referencedElementEdges.add(edge);
                    }
                }
            }
        }

        return referencedElementEdges;
    }

    /**
     * Check if a dependency type is desired to be analyzed.
     *
     * If the type is ignore, or a desired type is configured but not matched, false will be
     * returned -- true otherwise.
     *
     * @param type
     *            The type to check.
     * @return True if the type is desired.
     */
    private boolean isDesiredType(DependencyType type) {

        if (type == DependencyType.IGNORE) {
            return false;
        }

        String desiredType = desiredDependencyTypeConfig.getCurrentValue();
        if (desiredType == desiredDependencyTypeConfig.getDefaultValue()) {
            return true;
        }

        return type == DependencyType.valueOf(desiredType);
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
     * @param referenceSelector
     *            The selector for the references to consider.
     */
    private void indexReferencedElements(ReferenceSelector referenceSelector, VPReferenceIndex index) {
        Set<VariationPoint> variationPoints = index.vp2GraphNodeIndex.keySet();
        for (VariationPoint vp : variationPoints) {
            List<Commentable> jamoppElements = getJamoppElements(vp);
            for (Commentable jamoppElement : jamoppElements) {
                indexReferencedElements(vp, jamoppElement, referenceSelector, index);
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
     * @param referenceSelector
     *            The selector for the references to consider.
     * @param index
     *            The index to fill with the variation point's references.
     */
    private void indexReferencedElements(VariationPoint vp, Commentable referringElement,
            ReferenceSelector referenceSelector, VPReferenceIndex index) {
        List<Reference> referencedElements = referenceSelector.getReferencedElements(referringElement);
        for (Reference reference : referencedElements) {

            // filter external elements (i.e. contained in binary resources)
            if (filterExternalsConfig.getCurrentValue()) {
                Resource resource = reference.getTarget().eResource();
                if (resource != null && "pathmap".equals(resource.getURI().scheme())) {
                    continue;
                }
            }

            index.referencedElementsIndex.get(reference.getTarget()).add(vp);
            index.getIndexedReferences(vp, reference.getTarget()).add(reference);
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
    private void indexGraphNodes(VPMGraph vpmGraph, VPReferenceIndex index) {

        for (Node node : vpmGraph.getNodeSet()) {
            VariationPoint vp = node.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);
            index.vp2GraphNodeIndex.put(vp, node);
        }
    }

    @Override
    public VPMAnalyzerConfigurationSet getConfigurations() {
        VPMAnalyzerConfigurationSet configurations = new VPMAnalyzerConfigurationSet();
        configurations.addConfigurations(ConfigurationBuilder.CONFIG_GROUP_DEPENDENCIES, filterExternalsConfig,
                referenceSelectorConfig, desiredDependencyTypeConfig);
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

    /**
     * An index data structure for tracking and analyzing variation point element references.
     */
    private class VPReferenceIndex {

        /**
         * Index which graph node contains a specific variation point.
         */
        private final Map<VariationPoint, Node> vp2GraphNodeIndex = new LinkedHashMap<VariationPoint, Node>();

        /**
         * The index which element is referenced by which variation points.
         */
        private final LinkedHashMultimap<Commentable, VariationPoint> referencedElementsIndex = LinkedHashMultimap
                .create();

        /**
         * Index for references per variation point and referenced element (target).
         *
         * A table to link:
         * <code> {@link VariationPoint} + a referenced {@link Commentable} => referring {@link Commentable}</code>
         * <br>
         * This is used to later lookup which element of a variation point holds the reference to
         * the referred one.
         *
         * @return The index structure.
         */
        private final Table<VariationPoint, Commentable, List<Reference>> referringElementIndex = HashBasedTable
                .create();

        /**
         * Get the list of indexed references. It will always return at least an empty list to work
         * with.
         *
         * @param vp
         *            The referencing variation point containg the source element.
         * @param target
         *            The referenced target element.
         * @return The list of connecting references (never null).
         */
        public List<Reference> getIndexedReferences(VariationPoint vp, Commentable target) {
            List<Reference> references = referringElementIndex.get(vp, target);
            if (references == null) {
                referringElementIndex.put(vp, target, new ArrayList<Reference>());
            }
            return referringElementIndex.get(vp, target);
        }

    }

}
