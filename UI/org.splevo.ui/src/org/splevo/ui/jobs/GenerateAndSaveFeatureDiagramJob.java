package org.splevo.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.featuremodel.diagrameditor.FMEDiagramEditorUtil;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * This job creates a Feature Diagram from a given Feature Model.
 * 
 * @author Daniel Kojic
 *
 */
public class GenerateAndSaveFeatureDiagramJob extends
		AbstractBlackboardInteractingJob<SPLevoBlackBoard> {
	

	/** The URI for the Feature Diagram. */
	private URI featureDiagramURI;
	
	/** The URI for of the given Feature Model. */
	private URI featureModelURI;

	/**
	 * Constructor.
	 * 
	 * @param featureDiagramURI The URI for the Feature Diagram.
	 * @param featureModelURI The URI for of the given Feature Model.
	 */
	public GenerateAndSaveFeatureDiagramJob(URI featureDiagramURI, URI featureModelURI) {
		this.featureDiagramURI = featureDiagramURI;
		this.featureModelURI = featureModelURI;
	}

	@Override
	public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
		// Nothing to clean		
	}

	@Override
	public void execute(IProgressMonitor arg0) throws JobFailedException,
			UserCanceledException {
		logger.info("Creating Feature Diagram.");
		FMEDiagramEditorUtil.createFeatureDiagram(featureDiagramURI, featureModelURI, arg0);
		logger.info("Feature Diagram created.");
	}

	@Override
	public String getName() {
		return "Generate and save Feature Diagram Job";
	}

}
