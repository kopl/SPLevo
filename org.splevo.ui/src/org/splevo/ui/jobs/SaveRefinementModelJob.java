package org.splevo.ui.jobs;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.compare.util.ModelUtils;
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.refinement.RefinementModel;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * Job to read the refinement model from the blackboard and save it at a given path.
 */
public class SaveRefinementModelJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

	/** The relative path to write the model to. */
	private String targetPath = null;
	
	/** The splevo project to work in. */
	private SPLevoProject splevoProject = null;
	
	/**
	 * Constructor to set the target path to write the model to. 
	 * 
	 * @param splevoProject The project to get the workspace from.
	 * @param targetPath The eclipse workspace relative path to write to.
	 */
	public SaveRefinementModelJob(SPLevoProject splevoProject, String targetPath) {
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

		RefinementModel refModel = getBlackboard().getRefinementModel();

		if(targetPath == null){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
			Calendar cal = Calendar.getInstance();
			targetPath = "models/analyses-results/"+dateFormat.format(cal.getTime())+".refinement";
		}
		
		logger.info("Save Refinement Model");
		try {
			String modelPath = basePath + splevoProject.getWorkspace() + targetPath;			
			ModelUtils.save(refModel, modelPath);
		} catch (IOException e) {
			throw new JobFailedException("Failed to save refinement model.",e);
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
		return "Save Refinement Model Job";
	}
}