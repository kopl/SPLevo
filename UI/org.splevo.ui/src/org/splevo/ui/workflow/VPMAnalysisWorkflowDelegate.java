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
package org.splevo.ui.workflow;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.jobs.CloseAnalysisTraceLogAppenderJob;
import org.splevo.ui.jobs.DetectRefinementsJob;
import org.splevo.ui.jobs.InitVPMGraphJob;
import org.splevo.ui.jobs.LoadVPMJob;
import org.splevo.ui.jobs.MergeVPMAnalyzerResultsIntoGraphJob;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.jobs.SaveRefinementModelJob;
import org.splevo.ui.jobs.SaveRefinementModelJob.FORMAT;
import org.splevo.ui.jobs.VPMAnalysisJob;
import org.splevo.vpm.analyzer.VPMAnalyzer;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.ParallelBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * A workflow delegate defining the workflow for the refinement of the variation point model.
 */
public class VPMAnalysisWorkflowDelegate extends
        AbstractWorkbenchDelegate<VPMAnalysisWorkflowConfiguration, UIBasedWorkflow<Blackboard<?>>> {

    private Logger logger = Logger.getLogger(VPMAnalysisWorkflowDelegate.class);
    private VPMAnalysisWorkflowConfiguration config;
    private SPLevoBlackBoard blackboard;
    private boolean executeRefinementDetection;

    /**
     * Constructor requiring a difference analysis workflow configuration.
     *
     * @param config
     *            The configuration of the workflow.
     * @param blackboard
     *            The blackboard to communicate through.
     * @param executeRefinementDetection
     *            Flag if the refinement detection should be performed or not.
     */
    public VPMAnalysisWorkflowDelegate(VPMAnalysisWorkflowConfiguration config, SPLevoBlackBoard blackboard,
            boolean executeRefinementDetection) {
        this.config = config;
        this.blackboard = blackboard;
        this.executeRefinementDetection = executeRefinementDetection;
    }

    /**
     * Create the work flow.
     *
     * @param config
     *            The configuration object for this work flow.
     * @return The prepared job.
     */
    @Override
    protected IJob createWorkflowJob(VPMAnalysisWorkflowConfiguration config) {

        // initialize the basic elements
        SPLevoProject splevoProject = config.getSplevoProjectEditor().getSplevoProject();
        SequentialBlackboardInteractingJob<SPLevoBlackBoard> jobSequence = new SequentialBlackboardInteractingJob<SPLevoBlackBoard>();
        jobSequence.setBlackboard(blackboard);

        // load the latest vpm model
        LoadVPMJob loadVPMJob = new LoadVPMJob(splevoProject);
        jobSequence.add(loadVPMJob);

        // build vpm graph
        InitVPMGraphJob initVPMGraphJob = new InitVPMGraphJob();
        jobSequence.add(initVPMGraphJob);

        // trigger the configured refinement analysis
        ParallelBlackboardInteractingJob<SPLevoBlackBoard> parallelJob = new ParallelBlackboardInteractingJob<SPLevoBlackBoard>();
        if (config.getAnalyzers().size() < 1) {
            logger.error("No analysis to perform configured.");
            return null;
        }

        logger.info("Intialize Analysis Log");
        initializeAnalysisLog();

        for (VPMAnalyzer analyzerInstance : config.getAnalyzers()) {
            VPMAnalysisJob vpmAnalysisJob = new VPMAnalysisJob(analyzerInstance);
            parallelJob.add(vpmAnalysisJob);
        }
        jobSequence.add(parallelJob);

        MergeVPMAnalyzerResultsIntoGraphJob mergeVPMAnalyzerResultsJob = new MergeVPMAnalyzerResultsIntoGraphJob();
        jobSequence.add(mergeVPMAnalyzerResultsJob);

        // decide about the workflow to be executed
        if (executeRefinementDetection) {
            addRefinementDetectionJobs(jobSequence, splevoProject);
        }

        // clean up appender when workflow is done
        jobSequence.add(new CloseAnalysisTraceLogAppenderJob());

        // return the prepared workflow
        return jobSequence;
    }

    /**
     * Initialize the log4j logging infrastructure for the analysis run.
     */
    private void initializeAnalysisLog() {

        // build the path of the log file
        // inside a log directory of the analysis workflow
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        String basePath = workspace.getRoot().getRawLocation().toOSString();
        String logDirectory = basePath + config.getSplevoProjectEditor().getSplevoProject().getWorkspace();
        DateFormat logDateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String logFile = logDirectory + "logs/vpm-analysis-" + (logDateFormat.format(new Date())) + ".csv";

        FileAppender fa = new FileAppender();
        fa.setName(CloseAnalysisTraceLogAppenderJob.LOG_APPENDER_NAME);
        fa.setFile(logFile);
        fa.setLayout(new PatternLayout("%m%n"));
        fa.setThreshold(Level.DEBUG);
        fa.setAppend(false);
        fa.activateOptions();
        Logger.getLogger(VPMAnalyzer.LOG_CATEGORY).removeAllAppenders();
        Logger.getLogger(VPMAnalyzer.LOG_CATEGORY).addAppender(fa);

        // insert header row
        Logger.getLogger(VPMAnalyzer.LOG_CATEGORY).info("Analyzer,VP1, VP2, SourceInfo, TargetInfo, Remark");

    }

    /**
     * Add the jobs of the refinement browser work flow to the composite job.
     *
     * @param compositeJob
     *            The composite job to add the jobs to.
     * @param splevoProject
     *            The splevo project to interact with.
     */
    private void addRefinementDetectionJobs(SequentialJob compositeJob, SPLevoProject splevoProject) {

        DetectRefinementsJob createRefinementModelJob = new DetectRefinementsJob(config.getDetectionRules(), config.isUseMergeDetection());
        compositeJob.add(createRefinementModelJob);

        SaveRefinementModelJob saveRefinementModelJob = new SaveRefinementModelJob(splevoProject, FORMAT.CSV);
        compositeJob.add(saveRefinementModelJob);
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        // nothing to do here
    }

    @Override
    protected boolean useSeparateConsoleForEachJobRun() {
        return false;
    }

    @Override
    protected VPMAnalysisWorkflowConfiguration getConfiguration() {
        return this.config;
    }
}
