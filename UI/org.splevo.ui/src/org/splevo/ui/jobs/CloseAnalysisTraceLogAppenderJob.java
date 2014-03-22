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

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.vpm.analyzer.VPMAnalyzer;

import de.uka.ipd.sdq.workflow.jobs.IJob;

/**
 * Job to close the analysis trace log appender including the log file reference.
 */
public class CloseAnalysisTraceLogAppenderJob implements IJob {

    /** The name to identify the VPM analysis trace log appender. */
    public static final String LOG_APPENDER_NAME = "VPM Analysis Trace Log Appender";

    /**
     * Close the analysis trace log specific file appender.
     *
     * {@inheritDoc}
     */
    @Override
    public void execute(IProgressMonitor monitor) {
        Logger.getLogger(VPMAnalyzer.LOG_CATEGORY).getAppender(LOG_APPENDER_NAME).close();
    }

    @Override
    public String getName() {
        return "Clean Analysis Trace Log Appender";
    }

	@Override
	public void cleanup(IProgressMonitor arg0) {
	}


}