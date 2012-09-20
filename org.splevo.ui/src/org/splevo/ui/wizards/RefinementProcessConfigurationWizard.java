package org.splevo.ui.wizards;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.List;
import org.splevo.ui.workflow.VPMAnalysisConfiguration;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration;
import org.splevo.vpm.refinement.VPMRefinementAnalyzer;

/**
 * Wizard to let the user configure a vpm analysis run.
 */
public class RefinementProcessConfigurationWizard extends Wizard {

	/** Wizardpage to select the analysis to be performed. */
	private VPMAnalyzerSelectionPage myAnalysisSelectionPage = null;

	/** The configuration object to be filled. */
	private VPMAnalysisWorkflowConfiguration configuration = null;

	/**
	 * Constructor to make the basic wizard settings.
	 * 
	 * @param configuration
	 *            The workflow configuration to be filled on finished and to get
	 *            required information from.
	 */
	public RefinementProcessConfigurationWizard(
			VPMAnalysisWorkflowConfiguration configuration) {
		super();
		this.configuration = configuration;
		setWindowTitle("Configure VPM Analyses.");
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		myAnalysisSelectionPage = new VPMAnalyzerSelectionPage();
		addPage(myAnalysisSelectionPage);
	}

	/**
	 * Handle the click on the finish button, 
	 * fill the configuration object and check it's validity.
	 */
	@Override
	public boolean performFinish() {
		
		ListViewer analysisListViewer = myAnalysisSelectionPage.getListViewerAnalysis();
		List labelList = analysisListViewer.getList();
		for(int key = 0; key < labelList.getItems().length; key++){
			Object element = analysisListViewer.getElementAt(key);
			VPMAnalysisConfiguration config = new VPMAnalysisConfiguration();
			config.setAnalyzer((VPMRefinementAnalyzer) element);
			configuration.getAnalyses().add(config);
		}
		
		return configuration.isValid();
	}

}
