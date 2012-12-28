package org.splevo.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * Job to set a variation point model in the blackboard.
 */
public class SetVPMJob extends
		AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

	/** The variation point model to set in the blackboard. */
	private VariationPointModel vpm;

	/**
	 * Constructor to set the variation point model.
	 * 
	 * @param vpm
	 *            The reference to the variation point model.
	 */
	public SetVPMJob(VariationPointModel vpm) {
		this.vpm = vpm;
	}

	@Override
	public void execute(IProgressMonitor monitor) throws JobFailedException,
			UserCanceledException {

		logger.info("Set the variation point model in the blackboard");
		getBlackboard().setVariationPointModel(vpm);
		
		// finish run
		monitor.done();
	}

	@Override
	public void rollback(IProgressMonitor monitor)
			throws RollbackFailedException {
		// no rollback possible
	}

	@Override
	public String getName() {
		return "Set VPM Job";
	}
}
