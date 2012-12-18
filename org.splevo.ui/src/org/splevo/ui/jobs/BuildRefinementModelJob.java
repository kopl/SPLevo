package org.splevo.ui.jobs;

import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.vpm.refinement.AnalyzerConfiguration;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementFactory;
import org.splevo.vpm.refinement.RefinementModel;
import org.splevo.vpm.refinement.VPMRefinementAnalyzer;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * Job to build a refinement model by taking the map of refinements from the
 * splevo blackboard.
 * 
 * The prepared refinement model is placed back on the blackboard.
 */
public class BuildRefinementModelJob extends
		AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

	/**
	 * Default constructor for the job.
	 */
	public BuildRefinementModelJob() {}

	/**
	 * Runs the long running operation
	 * 
	 * @param monitor
	 *            the progress monitor
	 */
	@Override
	public void execute(IProgressMonitor monitor) throws JobFailedException,
			UserCanceledException {

		RefinementModel refinementModel = RefinementFactory.eINSTANCE.createRefinementModel();
		
		logger.info("Fill refinement model");
		HashMap<VPMRefinementAnalyzer, List<Refinement>> resultMap = getBlackboard().getAnalysisResults(); 
		for(VPMRefinementAnalyzer analyzer : resultMap.keySet()){
			AnalyzerConfiguration configuration = buildConfiguration(analyzer);
			for(Refinement refinement : resultMap.get(analyzer)){
				configuration.getRefinements().add(refinement);
			}
			refinementModel.getAnalyzerConfigurations().add(configuration);
			logger.info("Refinements added for "+analyzer.getName()+" ("+resultMap.get(analyzer).size()+" refinements)");
		}
		
		// check if the process was canceled
		if (monitor.isCanceled()) {
			monitor.done();
			logger.info("Workflow cancled.");
			return;
		}

		logger.info("Store Refinement model in blackboard");
		getBlackboard().setRefinementModel(refinementModel);
		
		// finish run
		monitor.done();
	}

	/**
	 * Build an analyzer configuration element for a given analyzer.
	 * @param analyzer The analyzer instance to process.
	 * @return The prepared analyzer configuration element.
	 */
	private AnalyzerConfiguration buildConfiguration(
			VPMRefinementAnalyzer analyzer) {
		AnalyzerConfiguration config = RefinementFactory.eINSTANCE.createAnalyzerConfiguration();
		config.setAnalyzerID(analyzer.getName());
		config.setSettings(new HashMap<String, String>());
		for(String key : analyzer.getConfigurations().keySet()){
			String value = analyzer.getConfigurations().get(key).toString();
			config.getSettings().put(key, value);
		}
		return config;
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
		return "Build Refinement Model Job";
	}
}