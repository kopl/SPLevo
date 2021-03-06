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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerException;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.graph.VPMGraph;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;

/**
 * Job to get the reference to a VPMGraph from the blackboard and execute a VPMAnalyzer on it. The
 * graph is directly modified (edges added) by the analyzer. So no need to actively store it back in
 * the blackboard.
 */
public class VPMAnalysisJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The configuration of the analysis to perform. */
    private VPMAnalyzer analyzer;

    /**
     * Constructor to set configuration of the analysis to perform.
     *
     * @param analyzer
     *            The configuration of the analysis to perform.
     */
    public VPMAnalysisJob(VPMAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException {

        logger.info("Load VPM Graph");
        VPMGraph vpmGraph = getBlackboard().getVpmGraph();

        logger.info("Analyze VPM Graph");
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss:S");
        this.logger.info("VPM Analysis (" + analyzer.getName() + ") started at: " + (dateFormat.format(new Date())));
        VPMAnalyzerResult analyzerResult;
        try {
            analyzerResult = analyzer.analyze(vpmGraph);
        } catch (VPMAnalyzerException e) {
            this.logger.error("VPM Analysis (" + analyzer.getName() + ") failed at: " + (dateFormat.format(new Date())));
            throw new JobFailedException("VPM Analysis failed (" + analyzer.getName() + ")", e);
        }
        this.logger.info("VPM Analysis (" + analyzer.getName() + ") finished at: " + (dateFormat.format(new Date())));
        this.logger.info("VPM Analysis (" + analyzer.getName() + ") relationships detected: "
                + analyzerResult.getEdgeDescriptors().size());

        // store the results
        getBlackboard().getVpmAnalyzerResults().add(analyzerResult);

        // finish run
        monitor.done();
    }

    @Override
    public String getName() {
        return "Analyze VPM Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) {
    }
}
