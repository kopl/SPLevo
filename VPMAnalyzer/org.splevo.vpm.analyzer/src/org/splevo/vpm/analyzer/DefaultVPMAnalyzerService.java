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
package org.splevo.vpm.analyzer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.graph.RelationshipEdge;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.mergedecider.MergeDecider;
import org.splevo.vpm.analyzer.mergedecider.MergeDeciderRegistry;
import org.splevo.vpm.analyzer.refinement.DetectionRule;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementFactory;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * The default vpm analyzer service implementation.
 */
public class DefaultVPMAnalyzerService implements VPMAnalyzerService {

    private static Logger logger = Logger.getLogger(DefaultVPMAnalyzerService.class);
    private static final String NEW_LINE = System.getProperty("line.separator");

    /**
     * @see org.splevo.vpm.analyzer.VPMAnalyzerService#initVPMGraph(org.splevo.vpm.variability.
     *      VariationPointModel) {@inheritDoc}
     */
    @Override
    public VPMGraph initVPMGraph(VariationPointModel vpm) {

        VPMGraph graph = new VPMGraph("VPMGraph");

        // iterate over variation groups and points and
        // init nodes for each of them.
        int nodeIndex = 0;
        for (VariationPointGroup vpGroup : vpm.getVariationPointGroups()) {
            for (VariationPoint vp : vpGroup.getVariationPoints()) {

                // At this time, the referenced ASTNode might be a proxy.
                // To prevent issues in the later processing, it is important
                // to resolve the proxy here before pushing the variation point
                // into the graph node.
                // EcoreUtil.resolveAll(vp);

                String nodeID = "VP" + (nodeIndex++);
                Node n = graph.addNode(nodeID);
                n.addAttribute(VPMGraph.GS_LABEL, nodeID);
                n.addAttribute(VPMGraph.VARIATIONPOINT, vp);
            }
        }

        return graph;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.splevo.vpm.analyzer.VPMAnalyzerService#mergeGraphs(java.util.List)
     */
    @Override
    public VPMGraph mergeGraphs(List<VPMGraph> graphs) {

        if (graphs.size() == 0) {
            return null;
        }

        VPMGraph graph = graphs.get(0);

        for (VPMGraph currentGraph : graphs) {

            if (currentGraph == graph) {
                continue;
            }

            for (Edge edge : currentGraph.getEachEdge()) {
                String edgeId = edge.getId();
                String sourceNodeId = edge.getSourceNode().getId();
                String targetNodeId = edge.getTargetNode().getId();

                Node sourceNode = graph.getNode(sourceNodeId);
                Edge mergeEdge = sourceNode.getEdgeBetween(targetNodeId);
                if (mergeEdge == null) {
                    mergeEdge = graph.addEdge(edgeId, sourceNodeId, targetNodeId);
                    for (String key : edge.getAttributeKeySet()) {
                        mergeEdge.addAttribute(key, edge.getAttribute(key));
                    }

                } else {
                    RelationshipEdge relEdge = (RelationshipEdge) edge;
                    RelationshipEdge relMergeEdge = (RelationshipEdge) mergeEdge;
                    relMergeEdge.getRelationshipInfos().addAll(relEdge.getRelationshipInfos());
                    relMergeEdge.getRelationshipLabels().addAll(relEdge.getRelationshipLabels());
                }

            }
        }

        return graph;
    }

    /**
     * @see org.splevo.vpm.analyzer.VPMAnalyzerService#mergeGraphs(java.util.List)
     *
     *      {@inheritDoc}
     */
    @Override
    public void mergeGraphEdges(VPMGraph vpmGraph) {

        for (Edge edge : vpmGraph.getEachEdge()) {

            Node sourceNode = edge.getSourceNode();
            Node targetNode = edge.getTargetNode();

            // enrich the merge edge or create a new one
            String edgeId = buildEdgeId(sourceNode, targetNode);
            Edge mergeEdge = vpmGraph.getEdge(edgeId);
            if (mergeEdge == null) {
                mergeEdge = vpmGraph.addEdge(edgeId, sourceNode, targetNode);
                for (String key : edge.getAttributeKeySet()) {
                    mergeEdge.addAttribute(key, edge.getAttribute(key));
                }
            } else {
                RelationshipEdge relEdge = (RelationshipEdge) edge;
                RelationshipEdge relMergeEdge = (RelationshipEdge) mergeEdge;
                relMergeEdge.getRelationshipInfos().addAll(relEdge.getRelationshipInfos());
                relMergeEdge.getRelationshipLabels().addAll(relEdge.getRelationshipLabels());
            }

            // remove the old merged node
            vpmGraph.removeEdge(edge);
        }
    }

    @Override
    public void createGraphEdges(VPMGraph vpmGraph, List<VPMAnalyzerResult> analyzerResults) {
        for (VPMAnalyzerResult analyzerResult : analyzerResults) {

            for (VPMEdgeDescriptor edgeDescriptor : analyzerResult.getEdgeDescriptors()) {

                // enrich the merge edge or create a new one
                String sourceNodeId = edgeDescriptor.getSourceNodeID();
                String targetNodeId = edgeDescriptor.getTargetNodeID();
                String edgeId = buildEdgeId(sourceNodeId, targetNodeId);
                Edge mergeEdge = vpmGraph.getEdge(edgeId);
                RelationshipEdge relationShipEdge;
                if (mergeEdge == null) {
                    relationShipEdge = (RelationshipEdge) vpmGraph.addEdge(edgeId, sourceNodeId, targetNodeId);
                } else {
                    relationShipEdge = (RelationshipEdge) mergeEdge;
                }
                relationShipEdge.addRelationshipLabel(edgeDescriptor.getRelationshipLabel());
                relationShipEdge.addRelationshipInfo(edgeDescriptor.getRelationshipSubLabel());
            }
        }
    }

    /**
     * Build a simple edge id by using the ids of the connected nodes only.
     *
     * @param node1
     *            The first node.
     * @param node2
     *            The second node.
     * @return The prepared edge id.
     */
    private String buildEdgeId(Node node1, Node node2) {
        return buildEdgeId(node1.getId(), node2.getId());
    }

    /**
     * Build a simple edge id by using the ids of the connected nodes only.
     *
     * The method ensures an alphanumeric ascending order of the node ids.
     *
     * @param node1ID
     *            The id of the first node.
     * @param node2ID
     *            The id of the second node.
     * @return The prepared edge id.
     */
    private String buildEdgeId(String node1ID, String node2ID) {
        if (node1ID.compareTo(node2ID) <= 0) {
            return node1ID + "#" + node2ID;
        } else {
            return node2ID + "#" + node1ID;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Refinement> deriveRefinements(VPMGraph vpmGraph, List<DetectionRule> detectionRules,
            boolean useMergeDetection) {

        List<Refinement> refinements = new ArrayList<Refinement>();

        for (DetectionRule rule : detectionRules) {
            List<Refinement> ruleRefinements = rule.detect(vpmGraph);
            if (useMergeDetection) {
                ruleRefinements = mergeDetection(ruleRefinements);
            }
            ruleRefinements = filterUnreasonable(ruleRefinements);

            refinements.addAll(ruleRefinements);
        }

        return refinements;

    }

    /**
     * Filter refinements that have no effect on the VPM. For example recommending the grouping of
     * VPs contained in the same group.
     *
     * @param ruleRefinements
     *            The list of refinements to check.
     * @return The list of accepted refinements.
     */
    private List<Refinement> filterUnreasonable(List<Refinement> ruleRefinements) {
        List<Refinement> filteredRefinements = Lists.newLinkedList();

        for (Refinement refinement : ruleRefinements) {
            if (isGroupingOfAlllreadyGroupedVPs(refinement)) {
                continue;
            }
            filteredRefinements.add(refinement);
        }

        return filteredRefinements;
    }

    private boolean isGroupingOfAlllreadyGroupedVPs(Refinement refinement) {
        boolean sameGroup = false;
        if (refinement.getType() == RefinementType.GROUPING) {
            Set<VariationPointGroup> groups = Sets.newLinkedHashSet();
            for (VariationPoint vp : refinement.getVariationPoints()) {
                groups.add(vp.getGroup());
            }
            if (groups.size() == 1) {
                sameGroup = true;
            }
        }
        return sameGroup;
    }

    /**
     * Check the refinements if the aggregated variation points or at least a some of them can be
     * merged instead of just grouped.
     *
     * If only a subgroup of the VPs can be merged, the refinement will be split to merge and later
     * on refine the VPs.
     *
     * @param detectedRefinements
     * @return
     */
    private List<Refinement> mergeDetection(List<Refinement> detectedRefinements) {

        logger.info("Run merge detection");

        List<Refinement> refinedRefinements = Lists.newLinkedList();

        for (Refinement origRefinement : detectedRefinements) {
            EList<VariationPoint> variationPoints = origRefinement.getVariationPoints();

            // The indexes are used to
            // i) build groups of refinements to merge
            // ii) fast lookup of which group a VP currently belongs to
            LinkedHashMultimap<VariationPoint, VariationPoint> mergeVPBuckets = LinkedHashMultimap.create();
            LinkedHashMap<VariationPoint, VariationPoint> invertedBucketIndex = Maps.newLinkedHashMap();

            VariationPoint[] vpArray = variationPoints.toArray(new VariationPoint[] {});

            // iterate over all pairs
            // the inner loop ensures no double check is performed
            for (int i = 0; i < vpArray.length; i++) {
                VariationPoint vp1 = vpArray[i];

                for (int j = i + 1; j < vpArray.length; j++) {

                    VariationPoint vp2 = vpArray[j];

                    if (vp1 == vp2) {
                        logger.error("Comparing a VP with itself should not happen");
                        continue;
                    }

                    if (canBeMerged(vp1, vp2)) {
                        VariationPoint bucketKey = chooseBucket(mergeVPBuckets, invertedBucketIndex, vp1, vp2);

                        invertedBucketIndex.put(vp2, bucketKey);
                        mergeVPBuckets.get(bucketKey).add(vp2);
                        invertedBucketIndex.put(vp1, bucketKey);
                        mergeVPBuckets.get(bucketKey).add(vp1);
                    }
                }
            }

            // check if the same vp is contained in several buckets
            List<VariationPoint> vpHits = Lists.newArrayList();
            for (VariationPoint vp : mergeVPBuckets.values()) {
                if (vpHits.contains(vp)) {
                    logger.error("VP contained twice: " + vp);
                } else {
                    vpHits.add(vp);
                }
            }

            // if no merges detected move on to the next
            if (mergeVPBuckets.size() == 0) {
                refinedRefinements.add(origRefinement);
                continue;
            }

            List<VariationPoint> vpsNotMerged = Lists.newArrayList(origRefinement.getVariationPoints());

            // create a merge refinement per bucket
            for (VariationPoint bucketKey : mergeVPBuckets.keySet()) {
                Refinement mergeRefinement = buildMergeRefinementForBucket(origRefinement,
                        mergeVPBuckets.get(bucketKey), bucketKey);
                vpsNotMerged.removeAll(mergeRefinement.getVariationPoints());
                refinedRefinements.add(mergeRefinement);
            }

            // add a grouping if more than one refinement is left
            if (vpsNotMerged.size() > 0 || refinedRefinements.size() > 1) {
                Refinement groupRefinement = buildOverarchingGroupRefinement(origRefinement, mergeVPBuckets.keySet(),
                        vpsNotMerged);
                refinedRefinements.add(groupRefinement);
            }
        }

        return refinedRefinements;
    }

    /**
     * Build a group refinement according to the original refinement that aggregates the new VPs
     * resulting from the merge operations as well as the not mergeable variation points.
     *
     * @param originalRefinement
     *            The original refinement to get the meta data from.
     * @param mergedVPs
     *            The primary VPs of the merges.
     * @param vpsNotMerged
     *            The VPs not merged.
     * @return The prepared group refinement.
     */
    private Refinement buildOverarchingGroupRefinement(Refinement originalRefinement,
            Collection<VariationPoint> mergedVPs, List<VariationPoint> vpsNotMerged) {
        Refinement groupRefinement = RefinementFactory.eINSTANCE.createRefinement();
        groupRefinement.setType(RefinementType.GROUPING);
        groupRefinement.setRefinementModel(originalRefinement.getRefinementModel());
        groupRefinement.getVariationPoints().addAll(mergedVPs);
        groupRefinement.getVariationPoints().addAll(vpsNotMerged);

        StringBuilder source = new StringBuilder();
        source.append("Group refinement to connect related VPs that could be partially merged");
        source.append(NEW_LINE);
        source.append(originalRefinement.getSource());
        groupRefinement.setSource(source.toString());

        return groupRefinement;
    }

    /**
     * Build the merge refinement for a specific bucket or mergeable refinements.<br>
     * The meta information such as source etc. are copied over from the original refinement.
     *
     * @param originalRefinement
     *            The original refinement to derive the merge from.
     * @param vpsToMergeIn
     *            The VPs to merge with the leading one.
     * @param firstVP
     *            The leading variation point of the mergeable ones.
     * @return The prepared merge refinement.
     */
    private Refinement buildMergeRefinementForBucket(Refinement originalRefinement, Set<VariationPoint> vpsToMergeIn,
            VariationPoint firstVP) {
        Refinement mergeRefinement = RefinementFactory.eINSTANCE.createRefinement();
        mergeRefinement.setType(RefinementType.MERGE);
        mergeRefinement.setRefinementModel(originalRefinement.getRefinementModel());

        mergeRefinement.getVariationPoints().add(firstVP);
        mergeRefinement.getVariationPoints().addAll(vpsToMergeIn);

        int mergedVPCount = mergeRefinement.getVariationPoints().size();
        int origVPCount = originalRefinement.getVariationPoints().size();

        StringBuilder source = new StringBuilder();
        source.append(mergedVPCount + " of " + origVPCount + " VPs detected to mergeable.");
        source.append(NEW_LINE);
        source.append(originalRefinement.getSource());
        mergeRefinement.setSource(source.toString());

        return mergeRefinement;
    }

    /**
     * Choose in which bucket of merge able variation points a variation point should be inserted.
     *
     * possible options are:
     * <ul>
     * <li>The reference VP AND VP under study are both already in one or more buckets</li>
     * <li>The reference VP is already in one or more buckets</li>
     * <li>The VP under study is already in one or more buckets</li>
     * <li>None is in a bucket so a new one is created.</li>
     * </ul>
     *
     * FIXME: The bucket selection also merges existing buckets which is not efficient at the moment
     * due to a lot of iterations
     *
     * DesignDecision If mergeable VPs are already located in different buckets, they are merged
     * because mergeability is defined as a transitive relationship!
     *
     *
     * @param mergeVPBuckets
     *            The map of existing buckets.
     * @param invertedBucketIndex
     *            The inverted index in which bucket a vp is located in.
     * @param vpReference
     *            The variation point the variation point under study is checked for mergeability.
     * @param vpUnderStudy
     *            The variation point under study that can be merged with the reference variation
     *            point with.
     * @return The key of the bucket the vp under study should be put into.
     */
    private VariationPoint chooseBucket(LinkedHashMultimap<VariationPoint, VariationPoint> mergeVPBuckets,
            LinkedHashMap<VariationPoint, VariationPoint> invertedBucketIndex, VariationPoint vpReference,
            VariationPoint vpUnderStudy) {

        VariationPoint bucketKeyReference = invertedBucketIndex.get(vpReference);
        VariationPoint bucketKeyUnderStudy = invertedBucketIndex.get(vpUnderStudy);
        boolean referenceIsInBucket = bucketKeyReference != null;
        boolean underStudyIsInBucket = bucketKeyUnderStudy != null;

        if (referenceIsInBucket && underStudyIsInBucket) {
            return mergeBuckets(mergeVPBuckets, vpUnderStudy, vpReference);

        } else if (referenceIsInBucket) {
            return bucketKeyReference;

        } else if (underStudyIsInBucket) {
            return bucketKeyUnderStudy;

        } else {
            return vpReference;
        }
    }

    /**
     * Merge buckets that contain at least on of the provided {@link VariationPoint}s.
     *
     * The merge is valid as the mergeability is assumed to be a transitive relationship.
     *
     * @param vpBuckets
     *            The buckets of variation points that can be merged.
     * @param vp1
     *            The first vp to check the buckets for.
     * @param vp2
     *            The second vp to check the buckets for.
     * @return
     */
    private VariationPoint mergeBuckets(LinkedHashMultimap<VariationPoint, VariationPoint> vpBuckets,
            VariationPoint vp1, VariationPoint vp2) {

        List<VariationPoint> mergedBucketVPs = Lists.newArrayList();
        List<VariationPoint> toBeRemoved = Lists.newArrayList();
        VariationPoint newBucketKey = null;

        for (VariationPoint key : vpBuckets.keySet()) {
            Set<VariationPoint> vpList = vpBuckets.get(key);
            if ((vpList.contains(vp2)) || (vpList.contains(vp1))) {
                if (newBucketKey == null) {
                    newBucketKey = key;
                }
                mergedBucketVPs.addAll(vpList);
                toBeRemoved.add(key);
            }
        }

        if (mergedBucketVPs.size() > 0) {
            for (VariationPoint removeKey : toBeRemoved) {
                vpBuckets.removeAll(removeKey);
            }
            vpBuckets.putAll(newBucketKey, mergedBucketVPs);
        }
        return newBucketKey;
    }

    /**
     * Check if the two variation points can be merged. It is important that this check is
     * transitive:<br>
     * If VP1+VP2 can be merged AND VP2+VP3 can be merged, than VP1+VP3 can be merged, too.
     *
     * @param vp1
     *            First variation point to check.
     * @param vp2
     *            Second variation point to check.
     * @return True, if the variation points can be merged. False if not or undecidable.
     */
    private boolean canBeMerged(VariationPoint vp1, VariationPoint vp2) {

        if (vp1 == null || vp2 == null) {
            logger.error("Mergeability checked for at least one VP being null");
        }

        for (MergeDecider decider : MergeDeciderRegistry.getMergeDecider()) {
            boolean checkResult = decider.canBeMerged(vp1, vp2);
            if (checkResult) {
                return true;
            }
        }
        return false;
    }

    /**
     * Load the vpm analyzer implementations registered for the according extension point.
     *
     * {@inheritDoc}
     */
    @Override
    public List<VPMAnalyzer> getAvailableAnalyzers() {
        return VPMAnalyzerRegistry.getVPMAnalyzers();
    }
}
