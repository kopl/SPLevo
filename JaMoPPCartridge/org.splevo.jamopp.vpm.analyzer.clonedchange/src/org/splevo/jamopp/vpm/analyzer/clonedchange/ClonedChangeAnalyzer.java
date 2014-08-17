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
import org.emftext.language.java.commons.Commentable;
import org.graphstream.graph.Node;
import org.splevo.jamopp.algorithm.clones.baxtor.CloneDetector;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.analyzer.AbstractVPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerException;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Variation Point Analyzer identifying variation points containing cloned changes.
 */
public class ClonedChangeAnalyzer extends AbstractVPMAnalyzer {

    private static final String ANALYZER_NAME = "Cloned Change Analyzer";
    private static final String RELATIONSHIP_LABEL = "ClonedChange";

    /**
     * Specifies how many software elements must be equal to consider two variation points to
     * contain clones.
     */
    private static final int THRESHOLD = 0;

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
        int numClones;

        VPMEdgeDescriptor edge;
        CloneDetector cloneDetector = new CloneDetector();
        List<VPMEdgeDescriptor> descriptors = new ArrayList<VPMEdgeDescriptor>();
        List<String> edgeRegistry = new ArrayList<String>();

        int numNodes = vpmGraph.getNodeCount();

        for (int i = 0; i < numNodes; i++) {
            for (int j = i + 1; j < numNodes; j++) {
                Node node1 = vpmGraph.getNode(i);
                Node node2 = vpmGraph.getNode(j);

                VariationPoint vp1 = node1.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);
                VariationPoint vp2 = node2.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);

                edge = null;
                numClones = countClonesInVariationPoints(vp1, vp2, cloneDetector);

                String subLabel = String.valueOf(numClones);
                
                if (numClones > THRESHOLD) {
                    edge = buildEdgeDescriptor(node1, node2, subLabel, edgeRegistry);
                }

                if (edge != null) {
                    descriptors.add(edge);
                }
            }
        }
        return descriptors;
    }

    private int countClonesInVariationPoints(VariationPoint vp1, VariationPoint vp2, CloneDetector cloneDetector) {
        int numClones = 0;

        EList<Variant> vp1Variants = vp1.getVariants();
        EList<Variant> vp2Variants = vp2.getVariants();

        for (Variant vp1Variant : vp1Variants) {
            for (Variant vp2Variant : vp2Variants) {
                numClones += countClonesInVariants(vp1Variant, vp2Variant, cloneDetector);
            }

        }

        return numClones;
    }

    private int countClonesInVariants(Variant vp1Variant, Variant vp2Variant, CloneDetector cloneDetector) {
        int numClones = 0;

        EList<SoftwareElement> vp1VariantElements = vp1Variant.getImplementingElements();
        EList<SoftwareElement> vp2VariantElements = vp2Variant.getImplementingElements();

        for (SoftwareElement vp1VariantElement : vp1VariantElements) {
            for (SoftwareElement vp2VariantElement : vp2VariantElements) {
                if (vp1VariantElement instanceof JaMoPPSoftwareElement
                        && vp2VariantElement instanceof JaMoPPSoftwareElement) {

                    Commentable commentable1 = ((JaMoPPSoftwareElement) vp1VariantElement).getJamoppElement();
                    Commentable commentable2 = ((JaMoPPSoftwareElement) vp2VariantElement).getJamoppElement();

                    if (cloneDetector.isClone(commentable1, commentable2)) {
                        numClones++;
                    }
                }
            }
        }

        return numClones;
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
        return new VPMAnalyzerConfigurationSet();
    }

}
