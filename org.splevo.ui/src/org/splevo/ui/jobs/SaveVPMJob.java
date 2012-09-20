package org.splevo.ui.jobs;

import java.io.IOException;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.compare.util.ModelUtils;
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * Job to extract a software model from an eclipse java project 
 */
public class SaveVPMJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

	/** The splevo project to store the required data to. */
	private SPLevoProject splevoProject;
	
	/** The path to write the model to. */
	private String targetPath;
	
	/**
	 * Constructor to set the reference to the splevo project
	 * and the target path to write the model to. 
	 * 
	 * @param splevoProject The project to update.
	 * @param targetPath The eclipse workspace relative path to write to.
	 */
	public SaveVPMJob(SPLevoProject splevoProject, String targetPath) {
		this.splevoProject = splevoProject;
		this.targetPath = targetPath;
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

		VariationPointModel vpm = getBlackboard().getVariationPointModel();

		logger.info("Save VPM Model");
		try {
			String modelPath = basePath + targetPath;			
			ModelUtils.save(vpm, modelPath);
			splevoProject.getVpmModelPaths().add(targetPath);
		} catch (IOException e) {
			throw new JobFailedException("Failed to save vpm model.",e);
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
		return "Init VPM model Job";
	}
}