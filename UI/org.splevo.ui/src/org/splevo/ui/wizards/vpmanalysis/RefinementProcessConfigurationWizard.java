package org.splevo.ui.wizards.vpmanalysis;

import java.util.Set;

import org.eclipse.jface.wizard.Wizard;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration;
import org.splevo.vpm.analyzer.VPMAnalyzer;

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
	 * {@inheritDoc}
	 */
	@Override
	public boolean performFinish() {
		
		Set<VPMAnalyzer> analyzers = myAnalysisSelectionPage.getAnalyzers();
		configuration.getAnalyzers().addAll(analyzers);
		
		return configuration.isValid();
	}

}
