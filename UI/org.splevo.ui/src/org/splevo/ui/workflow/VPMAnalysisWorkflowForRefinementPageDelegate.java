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
import org.splevo.ui.jobs.VPMAnalysisJob;
import org.splevo.vpm.analyzer.VPMAnalyzer;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.ParallelBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * A workflow delegate defining the workflow for the refinement of the variation point model.
 * 
 * @author Benjamin Klatt
 * @author Christian Busch
 */
public class VPMAnalysisWorkflowForRefinementPageDelegate extends
        AbstractWorkbenchDelegate<VPMAnalysisWorkflowConfiguration, UIBasedWorkflow<Blackboard<?>>> {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(VPMAnalysisWorkflowForRefinementPageDelegate.class);

    /** The configuration of the workflow. */
    private VPMAnalysisWorkflowConfiguration config = null;

    private SPLevoBlackBoard blackBoard = null;

    /**
     * Constructor requiring a diffing workflow configuration.
     * 
     * @param config
     *            The configuration of the workflow.
     */
    public VPMAnalysisWorkflowForRefinementPageDelegate(VPMAnalysisWorkflowConfiguration config) {
        this.config = config;
    }

    /**
     * Constructor requiring a diffing workflow configuration and a SPLevoBlackBoard.
     * 
     * @param config
     *            The configuration of the workflow.
     * @param blackBoard
     *            The BlackBoard to be used. If null a new one will be created.
     */
    public VPMAnalysisWorkflowForRefinementPageDelegate(VPMAnalysisWorkflowConfiguration config, SPLevoBlackBoard blackBoard) {
        this.config = config;
        this.blackBoard = blackBoard;
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

        if (blackBoard == null) {
            blackBoard = new SPLevoBlackBoard();
        }
        // initialize the basic elements
        SPLevoProject splevoProject = config.getSplevoProjectEditor().getSplevoProject();
        SequentialBlackboardInteractingJob<SPLevoBlackBoard> jobSequence = new SequentialBlackboardInteractingJob<SPLevoBlackBoard>();
        jobSequence.setBlackboard(blackBoard);

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

        DetectRefinementsJob createRefinementModelJob = new DetectRefinementsJob(config.getDetectionRules());
        jobSequence.add(createRefinementModelJob);

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
