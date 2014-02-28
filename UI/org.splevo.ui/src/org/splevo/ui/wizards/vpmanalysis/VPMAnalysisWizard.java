package org.splevo.ui.wizards.vpmanalysis;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jface.wizard.Wizard;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.listeners.WorkflowListenerUtil;
import org.splevo.ui.refinementbrowser.OpenRefinementEditorRunnable;
import org.splevo.ui.views.vpmgraph.OpenVPMGraphViewerRunnable;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration.ResultPresentation;
import org.splevo.ui.workflow.VPMAnalysisWorkflowDelegate;
import org.splevo.vpm.analyzer.VPMAnalyzer;

/**
 * Wizard to let the user configure a vpm analysis run.
 */
public class VPMAnalysisWizard extends Wizard {

    private Logger logger = Logger.getLogger(VPMAnalysisWizard.class);

    /** Wizard page to select the analysis to be performed. */
    private VPMAnalyzerConfigurationPage analyzerPage = null;

    /** Wizard page to configure the result handling. */
    private ResultHandlingConfigurationPage resultHandlingPage = null;

    /** Wizard page to browse and modify the result of the VPM analysis */
    private VPMRefinementPage vpmRefinementPage;

    /** The configuration object to be filled. */
    private VPMAnalysisWorkflowConfiguration analysisWorkflowConfiguration = null;

    /**
     * Constructor to make the basic wizard settings.
     *
     * @param configuration
     *            The workflow configuration to be filled on finished and to get required
     *            information from.
     */
    public VPMAnalysisWizard(VPMAnalysisWorkflowConfiguration configuration) {
        super();
        this.analysisWorkflowConfiguration = configuration;
        setWindowTitle("Configure VPM Analysis.");
        setNeedsProgressMonitor(false);
    }

    @Override
    public void addPages() {
        analyzerPage = new VPMAnalyzerConfigurationPage();
        resultHandlingPage = new ResultHandlingConfigurationPage(this.analysisWorkflowConfiguration);
        vpmRefinementPage = new VPMRefinementPage();

        addPage(analyzerPage);
        addPage(resultHandlingPage);
        addPage(vpmRefinementPage);

    }

    /**
     * Handle the click on the finish button, fill the configuration object and check it's validity.
     * {@inheritDoc}
     */
    @Override
    public boolean performFinish() {

        updateConfiguration();

        if (!analysisWorkflowConfiguration.isValid()) {
            return false;
        }

        if (getContainer().getCurrentPage().equals(resultHandlingPage)) {

            switch (analysisWorkflowConfiguration.getPresentation()) {
            case RELATIONSHIP_GRAPH_ONLY:
                runAnalysisAndOpenVPMGraphViewer();
                break;

            case REFINEMENT_BROWSER:
                runAnalysisAndOpenRefinementEditor();
                break;

            default:
                logger.error("Invalid result handling option");
                break;
            }
        }

        if (getContainer().getCurrentPage().equals(vpmRefinementPage)) {
            // TODO Christian B: Implement refinement application workflow
            logger.error("Refinement Page FINISH handling not yet implemented");
            throw new UnsupportedOperationException(
                    "The result processing of the refinement page is not yet implemented");
        }

        return true;
    }

    /**
     * Trigger the configured analysis and open the resulting refinement recommendations in the
     * refinement editor.
     */
    private void runAnalysisAndOpenRefinementEditor() {
        final SPLevoBlackBoard spLevoBlackBoard = new SPLevoBlackBoard();
        Runnable openRefinementEditorRunnable = new OpenRefinementEditorRunnable(
                analysisWorkflowConfiguration.getSplevoProjectEditor(), spLevoBlackBoard);
        VPMAnalysisWorkflowDelegate delegate = new VPMAnalysisWorkflowDelegate(analysisWorkflowConfiguration,
                spLevoBlackBoard, true);
        WorkflowListenerUtil.runWorkflowAndRunUITask(delegate, "Analyze VPM", openRefinementEditorRunnable);

    }

    /**
     * Run the analysis and trigger the vpm graph viewer to be displayed afterwards.
     */
    private void runAnalysisAndOpenVPMGraphViewer() {
        final SPLevoBlackBoard spLevoBlackBoard = new SPLevoBlackBoard();
        Runnable openGraphRunnable = new OpenVPMGraphViewerRunnable(spLevoBlackBoard);
        VPMAnalysisWorkflowDelegate delegate = new VPMAnalysisWorkflowDelegate(analysisWorkflowConfiguration,
                spLevoBlackBoard, false);
        WorkflowListenerUtil.runWorkflowAndRunUITask(delegate, "Analyze VPM", openGraphRunnable);
    }

    /**
     * Update the configuration object with the current wizard content.
     */
    public void updateConfiguration() {
        List<VPMAnalyzer> analyzers = analyzerPage.getAnalyzers();
        analysisWorkflowConfiguration.getAnalyzers().clear();
        analysisWorkflowConfiguration.getAnalyzers().addAll(analyzers);

        ResultPresentation resultPresentation = resultHandlingPage.getResultPresentation();
        analysisWorkflowConfiguration.setPresentation(resultPresentation);

        analysisWorkflowConfiguration.getDetectionRules().clear();
        analysisWorkflowConfiguration.getDetectionRules().addAll(resultHandlingPage.getDetectionRules());
    }

    /**
     * Get the workflow configuration managed by the wizard.
     *
     * Whenever this is accessed, the wizard first synchronizes the configuration
     * with the current ui settings.
     *
     * @return The workflow configuration.
     */
    public VPMAnalysisWorkflowConfiguration getAnalysisWorkflowConfiguration() {
        updateConfiguration();
        return analysisWorkflowConfiguration;
    }
}
