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
package org.splevo.ui.jobs;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.graphstream.algorithm.ConnectedComponents;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMAnalyzerService;
import org.splevo.vpm.analyzer.graph.VPMGraph;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;

/**
 * Job to create edges for the VPMAnalyzer results in the VPMGraph.
 *
 * The job
 * <ul>
 * <li>Read the VPMAnalyzerResults from the black board</li>
 * <li>Read the VPMGraph from the black board</li>
 * <li>Create edges for all edge descriptors in the analyzer results</li>
 * </ul>
 */
public class MergeVPMAnalyzerResultsIntoGraphJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The analyzer service for the merge. */
    private VPMAnalyzerService analyzerService = new DefaultVPMAnalyzerService();

    /**
     * Default constructor.
     */
    public MergeVPMAnalyzerResultsIntoGraphJob() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(IProgressMonitor monitor) {

        logger.info("Load VPM Graph");
        VPMGraph vpmGraph = getBlackboard().getVpmGraph();

        logger.info("Load VPM analyzer results");
        List<VPMAnalyzerResult> analyzerResults = getBlackboard().getVpmAnalyzerResults();

        logger.info("Merge analyzer results into graph.");
        if (analyzerResults.size() == 0) {
            logger.warn("No VPMAnalyzer results found in the blackboard");
        }
        analyzerService.createGraphEdges(vpmGraph, analyzerResults);

        logStatistics(vpmGraph);

        // finish run
        monitor.done();
    }

    /**
     * Log the statistics for a variation point graph.
     *
     * @param vpmGraph
     *            The vpm graph to log the statics for.
     */
    private void logStatistics(VPMGraph vpmGraph) {
        // number of subgraphs
        ConnectedComponents cc = new ConnectedComponents();
        cc.init(vpmGraph);

        int nodeCount = vpmGraph.getNodeCount();
        int edgeCount = vpmGraph.getEdgeCount();
        String clusterDegrees = getClusterDegrees(cc, nodeCount);
        String nodeDegrees = getNodeDegrees(vpmGraph);

        logger.info("VPM Analysis Result: #Nodes: " + nodeCount);
        logger.info("VPM Analysis Result: #Edges: " + edgeCount);
        logger.info("VPM Analysis Result: #Subgraphs: " + cc.getConnectedComponentsCount());
        logger.info("VPM Analysis Result: #SingleNodes: " + cc.getConnectedComponentsCount(1, 2));
        logger.info("VPM Analysis Result: Node Degrees (#EdgesPerNode:#Instances): [" + nodeDegrees + "]");
        logger.info("VPM Analysis Result: Cluster Degrees (NodesPerSubgraph:#Instances): [" + clusterDegrees + "]");

    }

    /**
     * Get a string containing the degrees of identified subgraphs and how many subgraphs exist for
     * a specific degree.
     *
     * @param vpmGraph
     *            The graph to analyze the subgraphs for.
     * @return The formated string of degree statistics [Size:SubGraphCount].
     */
    private String getNodeDegrees(VPMGraph vpmGraph) {

        LinkedHashMultimap<Integer, Node> degreeStatistics = LinkedHashMultimap.create();
        for (Node node : vpmGraph.getNodeSet()) {
            degreeStatistics.get(node.getDegree()).add(node);
        }

        StringBuilder degreePrint = new StringBuilder();
        List<Integer> degrees = Lists.newLinkedList(degreeStatistics.keySet());
        Collections.sort(degrees);
        for (Integer degree : degrees) {
            if (degreePrint.length() > 0) {
                degreePrint.append("|");
            }
            degreePrint.append(degree + ":" + degreeStatistics.get(degree).size());
        }
        return degreePrint.toString();
    }

    /**
     * Get a string containing the degrees of detected subgraphs and how many subgraphs exist for a
     * specific degree.
     *
     * @param cc
     *            An analysis container providing structure information about the graph.
     * @param maxDegree
     *            The maximum degree to search for.
     * @return The formated string of degree statistics [Size:SubGraphCount].
     */
    private String getClusterDegrees(ConnectedComponents cc, int maxDegree) {

        Map<Integer, Integer> degreeStatistics = Maps.newLinkedHashMap();

        for (int degree = 1; degree <= maxDegree; degree++) {
            int degreeCount = cc.getConnectedComponentsCount(degree, degree + 1);
            if (degreeCount != 0) {
                degreeStatistics.put(degree, degreeCount);
            }
        }

        StringBuilder degreePrint = new StringBuilder();
        // List<Integer> degrees = Lists.newLinkedList(degreeStatistics.keySet());
        // Collections.sort(degrees);
        for (Integer degree : degreeStatistics.keySet()) {
            if (degreePrint.length() > 0) {
                degreePrint.append("|");
            }
            degreePrint.append(degree + ":" + degreeStatistics.get(degree));
        }
        return degreePrint.toString();
    }

    @Override
    public String getName() {
        return "Merge VPMGraph Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) {
    }
}
