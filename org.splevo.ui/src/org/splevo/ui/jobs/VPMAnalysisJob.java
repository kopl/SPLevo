package org.splevo.ui.jobs;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.ui.workflow.VPMAnalysisConfiguration;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.VPMRefinementService;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * Job to perform set of configured vpm analysis, 
 * automatically apply the refinements and store the resulting vpm on the blackboard.
 */
public class VPMAnalysisJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

	/** The configuration of the analysis to perform. */
	private VPMAnalysisConfiguration analysisConfig;
	
	/**
	 * Constructor to set configuration of the analysis to perform. 
	 * 
	 * @param targetPath The configuration of the analysis to perform.
	 */
	public VPMAnalysisJob(VPMAnalysisConfiguration analysisConfig) {
		this.analysisConfig = analysisConfig;
	}

	/**
	 * Runs the long running operation
	 * 
	 * @param monitor
	 *            the progress monitor
	 */
	@Override
	public void execute(IProgressMonitor monitor) throws JobFailedException,
			UserCanceledException {

		logger.info("Load VPM Model");
		VariationPointModel vpm = getBlackboard().getVariationPointModel();

		logger.info("Analyze VPM Model");
		VPMRefinementService service = new VPMRefinementService();
		List<Refinement> refinements = service.identifyRefinements(vpm,analysisConfig.getAnalyzer());
		
		logger.info("Store the identified refinements in the blackboard");
		getBlackboard().getRefinements().put(analysisConfig, refinements);
		
		// finish run
		monitor.done();
	}

	@Override
	public void rollback(IProgressMonitor monitor)
			throws RollbackFailedException {
		// no rollback possible
	}

	/**
	 * Get the name of the job. 
	 */
	@Override
	public String getName() {
		return "Save feature model Job";
	}
}