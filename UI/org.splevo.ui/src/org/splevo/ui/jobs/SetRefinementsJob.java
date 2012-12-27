package org.splevo.ui.jobs;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.vpm.refinement.Refinement;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * Job to load a variation point model into the blackboard.
 */
public class SetRefinementsJob extends
		AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

	/** The refinements to set in the blackboard. */
	private List<Refinement> refinements;

	/**
	 * Constructor to set the refinements to be applied.
	 * 
	 * @param refinements
	 *            The reference to the refinements.
	 */
	public SetRefinementsJob(List<Refinement> refinements) {
		this.refinements = refinements;
	}

	/**
	 * Execute the job.
	 * 
	 * @param monitor
	 *            the progress monitor
	 */
	@Override
	public void execute(IProgressMonitor monitor) throws JobFailedException,
			UserCanceledException {

		logger.info("Set the refinements to perform in the blackboard");
		getBlackboard().getRefinementsToApply().addAll(refinements);
		
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
		return "Set refinements Job";
	}
}