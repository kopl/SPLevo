package org.splevo.ui.jobs;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.diffing.Java2KDMDiffingService;
import org.splevo.diffing.kdm.KDMUtil;
import org.splevo.project.SPLevoProject;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * Job to extract a software model from an eclipse java project 
 */
public class DiffingJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

	/** The splevo project to store the required data to. */
	private SPLevoProject splevoProject;
	/**
	 * LongRunningOperation constructor
	 * 
	 * @param indeterminate
	 *            whether the animation is unknown
	 */
	public DiffingJob(SPLevoProject splevoProject) {
		this.splevoProject = splevoProject;
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

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		String basePath = workspace.getRoot().getRawLocation().toOSString();

		logger.info("Load source models");
		JavaApplication leadingModel = null;
		JavaApplication integrationModel = null;
		try {
			
			leadingModel = KDMUtil.loadKDMModel(new File(basePath+splevoProject.getSourceModelPathLeading()));
			integrationModel = KDMUtil.loadKDMModel(new File(basePath+splevoProject.getSourceModelPathIntegration()));
		} catch (IOException e) {
			throw new JobFailedException("Failed to load source models",e);
		}
		
		logger.info("Init diffing service");
		Java2KDMDiffingService diffingService = new Java2KDMDiffingService();
		String diffingRuleRaw = splevoProject.getDiffingFilterRules();
		String[] parts = diffingRuleRaw.split(System.getProperty("line.separator"));
		for (String rule : parts) {
			diffingService.getIgnorePackages().add(rule);
		}
		
		logger.info("Execute diffing");
		DiffModel diffModel = null;
		try {
			diffModel = diffingService.doDiff(integrationModel,leadingModel);
		} catch (InterruptedException e) {
			throw new JobFailedException("Failed to process diffing.",e);
		}
		
		// check if the process was canceled
		if (monitor.isCanceled()) {
			monitor.done();
			return;
		}

		logger.info("Save Diff Model");
		try {
			String targetPath = basePath
									+ splevoProject.getWorkspace()
									+ "models/diffmodel/diffModel.java2kdmdiff";
			
			ModelUtils.save(diffModel, targetPath);
			splevoProject.setDiffingModelPath(targetPath);
		} catch (IOException e) {
			throw new JobFailedException("Failed to save diff model.",e);
		}
		
		// refresh workspace
		try {
			workspace.getRoot().refreshLocal(IResource.DEPTH_ONE, monitor);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
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
		return "Diff source models Job";
	}
}