package org.splevo.ui.jobs;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.vpm.analyzer.VPMAnalyzer;

import de.uka.ipd.sdq.workflow.IJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * Job to close the analysis trace log appender including the log file reference.
 * @author Benjamin Klatt
 *
 */
public class CloseAnalysisTraceLogAppenderJob implements IJob {

    /** The name to identify the VPM analysis trace log appender. */
    public static final String LOG_APPENDER_NAME = "VPM Analysis Trace Log Appender";
    
    /**
     * Close the analysis trace log specific file appender.
     * {@inheritDoc}
     */
    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        Logger.getLogger(VPMAnalyzer.LOG_CATEGORY).getAppender(LOG_APPENDER_NAME).close();
    }

    @Override
    public void rollback(IProgressMonitor monitor) throws RollbackFailedException {
    }

    @Override
    public String getName() {
        return "Clean Analysis Trace Log Appender";
    }


}