/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Busch
 *******************************************************************************/
package org.splevo.jamopp.vpm.analyzer.clonedchange;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.emftext.commons.layout.LayoutInformation;
import org.emftext.language.java.commons.Commentable;
import org.graphstream.graph.Node;
import org.splevo.jamopp.algorithm.clones.baxtor.CloneDetectionType;
import org.splevo.jamopp.algorithm.clones.baxtor.CloneDetector;
import org.splevo.vpm.analyzer.AbstractVPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerException;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.config.NumericConfiguration;
import org.splevo.vpm.analyzer.config.Range;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * Variation Point Analyzer identifying variation points containing cloned changes.
 */
public class ClonedChangeAnalyzer extends AbstractVPMAnalyzer {

    private static final String ANALYZER_NAME = "Cloned Change Analyzer";
    private static final String RELATIONSHIP_LABEL = "ClonedChange";

    /** The configuration-object for the minimum number of elements to be involved in a clone configuration. */
    private NumericConfiguration minElementThresholdConfig = new NumericConfiguration(Config.CONFIG_ID_INVOLVED_ELEMENT_THRESHOLD,
            Config.LABEL_INVOLVED_ELEMENT_THRESHOLD, Config.EXPL_INVOLVED_ELEMENT_THRESHOLD, Config.DEFAULT_INVOLVED_ELEMENT_THRESHOLD, 1,
            new Range(1, -1), 0);

    /**
     * Specifies how many software elements must be equal to consider two variation points to
     * contain clones.
     */
    private int THRESHOLD = 10;

    /**
     * Analyze variation point relationship based on cloned changes between them.
     *
     * {@inheritDoc}
     *
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)
     */
    @Override
    public VPMAnalyzerResult analyze(VPMGraph vpmGraph) throws VPMAnalyzerException {
        VPMAnalyzerResult result = new VPMAnalyzerResult(this);

        List<VPMEdgeDescriptor> edges = findEdgesBetweenClonedChanges(vpmGraph);
        result.getEdgeDescriptors().addAll(edges);

        return result;
    }

    private List<VPMEdgeDescriptor> findEdgesBetweenClonedChanges(VPMGraph vpmGraph) {

        VPMEdgeDescriptor edge;
        CloneDetector cloneDetector = new CloneDetector(CloneDetectionType.STRUCTURAL);
        List<VPMEdgeDescriptor> descriptors = new ArrayList<VPMEdgeDescriptor>();
        List<String> edgeRegistry = new ArrayList<String>();

        int numNodes = vpmGraph.getNodeCount();

        for (int i = 0; i < numNodes; i++) {

            Node node1 = vpmGraph.getNode(i);
            VariationPoint vp1 = node1.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);
            EList<Variant> variants1 = vp1.getVariants();
            for (Variant v1 : variants1) {

                EList<SoftwareElement> elements1 = v1.getImplementingElements();
                List<Commentable> jamoppElements1 = buildJaMoPPElementList(elements1);
                int elementCount1 = countChildElements(jamoppElements1);
                // skip due to element threshold
                if (elementCount1 < THRESHOLD) {
                    continue;
                }

                for (int j = i + 1; j < numNodes; j++) {

                    Node node2 = vpmGraph.getNode(j);
                    VariationPoint vp2 = node2.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);
                    EList<Variant> variants2 = vp2.getVariants();

                    boolean cloneDetected = false;
                    int involvedElements = 0;
                    for (Variant v2 : variants2) {

                        // check only variants of the same copy
                        if (!v1.getId().equals(v2.getId())) {
                            continue;
                        }

                        EList<SoftwareElement> elements2 = v2.getImplementingElements();

                        // check only sets of elements of the same size
                        if (elements1.size() != elements2.size()) {
                            continue;
                        }

                        List<Commentable> jamoppElements2 = buildJaMoPPElementList(elements2);
                        int elementCount2 = countChildElements(jamoppElements2);

                        // Skip due to differing element count
                        if (elementCount1 != elementCount2) {
                            continue;
                        }

                        cloneDetected = cloneDetector.isClone(jamoppElements1, jamoppElements2);
                        if (cloneDetected) {
                            involvedElements = elementCount1;
                            break;
                        }
                    }

                    if(cloneDetected) {
                        String subLabel = "Clone with " + involvedElements + " elements involved";
                        edge = buildEdgeDescriptor(node1, node2, subLabel, edgeRegistry);
                        if (edge != null) {
                            descriptors.add(edge);
                        }
                    }
                }
            }
        }
        return descriptors;
    }

    private int countChildElements(List<Commentable> jamoppElements1) {
        int elementCount = 0;
        for (Commentable commentable : jamoppElements1) {
            for (EObject child : Lists.newArrayList(commentable.eAllContents())) {
                if (!(child instanceof LayoutInformation)) {
                    elementCount++;
                }
            }
        }
        return elementCount;
    }

    private List<Commentable> buildJaMoPPElementList(EList<SoftwareElement> elements1) {
        List<Commentable> jamoppElements1 = Lists.newArrayList();
        for (SoftwareElement se : elements1) {
            jamoppElements1.add((Commentable) se.getWrappedElement());
        }
        return jamoppElements1;
    }

    @Override
    public String getName() {
        return ANALYZER_NAME;
    }

    @Override
    public String getRelationshipLabel() {
        return RELATIONSHIP_LABEL;
    }

    @Override
    public VPMAnalyzerConfigurationSet getConfigurations() {
        VPMAnalyzerConfigurationSet configurations = new VPMAnalyzerConfigurationSet();
        configurations.addConfigurations(Config.CONFIG_GROUP_GENERAL, minElementThresholdConfig);
        return configurations;
    }

}
