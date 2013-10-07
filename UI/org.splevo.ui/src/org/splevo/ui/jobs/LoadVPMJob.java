package org.splevo.ui.jobs;

import java.io.File;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityPackage;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Job to load a variation point model into the blackboard.
 */
public class LoadVPMJob extends
		AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

	/** The splevo project to get the vpm info from. */
	private SPLevoProject splevoProject;

	/**
	 * The index of the vpm to be loaded. If this is -1 the latest one will be
	 * loaded.
	 */
	private int targetVPMIndex = -1;

	/**
	 * Constructor to set a reference to the splevoproject.
	 * 
	 * @param splevoProject
	 *            The reference to the splevoproject.
	 */
	public LoadVPMJob(SPLevoProject splevoProject) {
		this.splevoProject = splevoProject;
		this.targetVPMIndex = -1;
	}

	/**
	 * Constructor to set a reference to the splevo project and the index of the
	 * vpm to be loaded.
	 * 
	 * @param splevoProject
	 *            The reference to the splevo project.
	 * @param index
	 *            The index of the vpm.
	 */
	public LoadVPMJob(SPLevoProject splevoProject, int index) {
		this.splevoProject = splevoProject;
		this.targetVPMIndex = index;
	}

	@Override
	public void execute(IProgressMonitor monitor) throws JobFailedException,
			UserCanceledException {

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		String basePath = workspace.getRoot().getRawLocation().toOSString();

		logger.info("Load vpm model");
		variabilityPackage.eINSTANCE.eClass();

		int index = targetVPMIndex;
		if (targetVPMIndex == -1) {
			index = splevoProject.getVpmModelPaths().size() - 1;
		}
		VariationPointModel vpm;
		try {
			File vpmFile = new File(basePath
					+ splevoProject.getVpmModelPaths().get(index));
			vpm = (VariationPointModel) ModelUtils.load(vpmFile,
					new ResourceSetImpl());
		} catch (Exception e) {
			throw new JobFailedException("Failed to load vpm model.", e);
		}

		logger.info("Put variation point model on the blackboard");
		getBlackboard().setVariationPointModel(vpm);

		// finish run
		monitor.done();
	}

	@Override
	public String getName() {
		return "Load VPM model Job";
	}

	@Override
	public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
	}
}
