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

import org.eclipse.core.runtime.IProgressMonitor;
import org.graphstream.algorithm.ConnectedComponents;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMAnalyzerService;
import org.splevo.vpm.analyzer.graph.VPMGraph;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;

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
        String subgraphDegrees = getSubgraphDegrees(vpmGraph);

        logger.info("VPM Analysis Result: #Nodes: " + vpmGraph.getNodeCount());
        logger.info("VPM Analysis Result: #Edges: " + vpmGraph.getEdgeCount());
        logger.info("VPM Analysis Result: #Subgraphs: " + cc.getConnectedComponentsCount());
        logger.info("VPM Analysis Result: #SingleNodes: " + cc.getConnectedComponentsCount(0, 2));
        logger.info("VPM Analysis Result: Subgraph Degrees (Degree:SubGraphCount): [" + subgraphDegrees + "]");

    }

    /**
     * Get a string containing the degrees of identified subgraphs and how many subgraphs exist for
     * a specific degree.
     *
     * @param vpmGraph
     *            The graph to analyze the subgraphs for.
     * @return The formated string of degree statistics [Size:SubGraphCount].
     */
    private String getSubgraphDegrees(VPMGraph vpmGraph) {

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

    @Override
    public String getName() {
        return "Merge VPMGraph Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) {
    }
}
