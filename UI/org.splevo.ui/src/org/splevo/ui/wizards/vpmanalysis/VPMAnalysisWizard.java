package org.splevo.ui.wizards.vpmanalysis;

import java.util.Set;

import org.eclipse.jface.wizard.Wizard;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration.ResultPresentation;
import org.splevo.vpm.analyzer.VPMAnalyzer;

/**
 * Wizard to let the user configure a vpm analysis run.
 */
public class VPMAnalysisWizard extends Wizard {

    /** Wizard page to select the analysis to be performed. */
    private VPMAnalyzerConfigurationPage analyzerPage = null;

    /** Wizard page to configure the result handling. */
    private ResultHandlingConfigurationPage resultHandlingPage = null;

    /** The configuration object to be filled. */
    private VPMAnalysisWorkflowConfiguration configuration = null;

    /**
     * Constructor to make the basic wizard settings.
     * 
     * @param configuration
     *            The workflow configuration to be filled on finished and to get required
     *            information from.
     */
    public VPMAnalysisWizard(VPMAnalysisWorkflowConfiguration configuration) {
        super();
        this.configuration = configuration;
        setWindowTitle("Configure VPM Analysis.");
        setNeedsProgressMonitor(true);
    }

    @Override
    public void addPages() {
        analyzerPage = new VPMAnalyzerConfigurationPage();
        addPage(analyzerPage);

        resultHandlingPage = new ResultHandlingConfigurationPage();
        resultHandlingPage.setPreviousPage(analyzerPage);
        addPage(resultHandlingPage);

    }

    /**
     * Handle the click on the finish button, fill the configuration object and check it's validity.
     * {@inheritDoc}
     */
    @Override
	public boolean performFinish() {
		
		Set<VPMAnalyzer> analyzers = analyzerPage.getAnalyzers();
		configuration.getAnalyzers().addAll(analyzers);
		
		ResultPresentation resultPresentation = resultHandlingPage.getResultPresentation();
		configuration.setPresentation(resultPresentation);
		
		return configuration.isValid();
	}
}