package org.splevo.ui.jobs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.graph.VPMGraph;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

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
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        logger.info("Load VPM Graph");
        VPMGraph vpmGraph = getBlackboard().getVpmGraph();

        logger.info("Analyze VPM Graph");
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss:S");
        this.logger.info("VPM Analysis (" + analyzer.getName() + ") started at: " + (dateFormat.format(new Date())));
        VPMAnalyzerResult analyzerResult = analyzer.analyze(vpmGraph);
        this.logger.info("VPM Analysis (" + analyzer.getName() + ") finished at: " + (dateFormat.format(new Date())));
        getBlackboard().getVpmAnalyzerResults().add(analyzerResult);

        // finish run
        monitor.done();
    }

    @Override
    public void rollback(IProgressMonitor monitor) throws RollbackFailedException {
        // no rollback possible
    }

    @Override
    public String getName() {
        return "Analyze VPM Job";
    }
}
