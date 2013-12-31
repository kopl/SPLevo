package org.splevo.ui.workflow;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.featuremodel.diagrameditor.FMEDiagramEditor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.jobs.GenerateAndSaveFeatureDiagramJob;
import org.splevo.ui.jobs.GenerateFeatureModelJob;
import org.splevo.ui.jobs.LoadVPMJob;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.jobs.SaveFeatureModelJob;
import org.splevo.ui.jobs.UpdateUIJob;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;
import de.uka.ipd.sdq.workflow.workbench.AbstractWorkbenchDelegate;

/**
 * Delegate defining a work flow to initialize a feature model based on the
 * latest variation point model.
 */
public class GenerateFeatureModelWorkflowDelegate
		extends
		AbstractWorkbenchDelegate<BasicSPLevoWorkflowConfiguration, UIBasedWorkflow<Blackboard<?>>> {

	/** The configuration of the workflow. */
	private BasicSPLevoWorkflowConfiguration config = null;

	/**
	 * Constructor requiring a diffing workflow configuration.
	 * 
	 * @param config
	 *            The configuration of the workflow.
	 */
	public GenerateFeatureModelWorkflowDelegate(
			BasicSPLevoWorkflowConfiguration config) {
		this.config = config;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IJob createWorkflowJob(BasicSPLevoWorkflowConfiguration config) {

		SequentialBlackboardInteractingJob<SPLevoBlackBoard> jobSequence = 
				new SequentialBlackboardInteractingJob<SPLevoBlackBoard>();
		jobSequence.setBlackboard(new SPLevoBlackBoard());

		SPLevoProject splevoProject = config.getSplevoProjectEditor()
				.getSplevoProject();

		// load the diff model
		LoadVPMJob loadVPMJob = new LoadVPMJob(splevoProject);
		jobSequence.add(loadVPMJob);

		// init the vpm
		GenerateFeatureModelJob generateFMJob = new GenerateFeatureModelJob(
				splevoProject);
		jobSequence.add(generateFMJob);

		// save the model
		IPath featureModelPath = getModelFilePath(splevoProject);
		SaveFeatureModelJob saveFMJob = new SaveFeatureModelJob(
				featureModelPath.toString());
		jobSequence.add(saveFMJob);

		// generate and save feature diagram
		URI featureDiagramURI = getFeatureDiagramURI(splevoProject);
		GenerateAndSaveFeatureDiagramJob generateAndSaveFDJob = new GenerateAndSaveFeatureDiagramJob(
				featureDiagramURI, getFeatureModelURI(splevoProject));
		jobSequence.add(generateAndSaveFDJob);

		// init the ui update job
		UpdateUIJob updateUiJob = new UpdateUIJob(
				config.getSplevoProjectEditor(),
				"Feature model and diagram generated");
		jobSequence.add(updateUiJob);

		// return the prepared workflow
		return jobSequence;
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// nothing to do here
	}

	@Override
	protected boolean useSeparateConsoleForEachJobRun() {
		return false;
	}

	@Override
	protected BasicSPLevoWorkflowConfiguration getConfiguration() {
		return this.config;
	}

	/**
	 * Return the Feature Diagram file URI.
	 * 
	 * @param splevoProject
	 *            The {@link SPLevoProject} to get the workspace from.
	 * @return the file URI
	 */
	public URI getFeatureDiagramURI(SPLevoProject splevoProject) {
		// replace Feature Model file extension with Feature Diagram file
		// extension
		IPath path = getModelFilePath(splevoProject).removeFileExtension();
		path = path.addFileExtension(FMEDiagramEditor.DIAGRAM_FILE_EXTENSION);

		return URI.createPlatformResourceURI(path.toString(), false);
	}

	/**
	 * Return the Feature Model file URI.
	 * 
	 * @param splevoProject
	 *            The {@link SPLevoProject} to get the workspace from.
	 * @return the file URI
	 */
	public URI getFeatureModelURI(SPLevoProject splevoProject) {
		IPath path = getModelFilePath(splevoProject);
		return URI.createPlatformResourceURI(path.toString(), false);
	}

	/**
	 * Return the Feature Model file path.
	 * 
	 * @param splevoProject
	 *            The {@link SPLevoProject} to get the workspace from.
	 * @return the file {@link IPath}
	 */
	private IPath getModelFilePath(SPLevoProject splevoProject) {
		String path = splevoProject.getWorkspace() + "models" + File.separator
				+ "fm" + File.separator + "feature-model.featuremodel";
		return new Path(path);
	}

}
