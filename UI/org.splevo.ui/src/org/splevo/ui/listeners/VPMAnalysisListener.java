package org.splevo.ui.listeners;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.ProgressMonitorPart;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.wizards.vpmanalysis.ResultHandlingConfigurationPage;
import org.splevo.ui.wizards.vpmanalysis.VPMAnalysisWizard;
import org.splevo.ui.wizards.vpmanalysis.VPMAnalyzerConfigurationPage;
import org.splevo.ui.wizards.vpmanalysis.VPMRefinementPage;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration.ResultPresentation;
import org.splevo.ui.workflow.VPMAnalysisWorkflowDelegate;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.refinement.Refinement;

/**
 * Mouse adapter to listen for events which trigger the refinement process of a variation point
 * model.
 */
public class VPMAnalysisListener extends MouseAdapter {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(VPMAnalysisListener.class);

    /** The internal reference to the SPLevo project editor to work with. */
    private SPLevoProjectEditor splevoProjectEditor = null;

    private VPMAnalysisWorkflowConfiguration config = null;

    private VPMAnalysisWizard vpmAnalysisWizard = null;

    /**
     * Constructor requiring the reference to a splevoProject.
     *
     * @param splevoProjectEditor
     *            The references to the splevo project editor.
     */
    public VPMAnalysisListener(SPLevoProjectEditor splevoProjectEditor) {
        this.splevoProjectEditor = splevoProjectEditor;
        this.config = buildWorflowConfiguration();
    }

    /**
     * Handle a button click to create and start the VPM Analysis wizard.
     *
     * @param event
     *            The mouse event to react on. All events are handled the same way.
     */
    @Override
    public void mouseUp(MouseEvent event) {

        Shell shell = event.widget.getDisplay().getShells()[0];

        // trigger the wizard to configure the refinement process
        vpmAnalysisWizard = new VPMAnalysisWizard(config);
        WizardDialog wizardDialog = createWizardDialog(shell, vpmAnalysisWizard);
        wizardDialog.addPageChangedListener(createWizardPageChangeListener());

        if (wizardDialog.open() != Window.OK) {
            logger.debug("Variation Point Analyses canceled");
            return;
        }

        // validate configuration
        if (!config.isValid()) {
            MessageDialog.openError(shell, "Invalid Project Configuration", config.getErrorMessage());
            return;
        }


    }

    /**
     * Create a listener to react on any changes from one wizard page to another.
     *
     * @return The prepared listener.
     */
    private IPageChangedListener createWizardPageChangeListener() {
        return new IPageChangedListener() {
            private List<VPMAnalyzer> analyzers = null;

            @Override
            public void pageChanged(PageChangedEvent event) {
                Object selectedPage = event.getSelectedPage();

                vpmAnalysisWizard.updateConfiguration();

                if (selectedPage instanceof VPMAnalyzerConfigurationPage) {
                    VPMAnalyzerConfigurationPage configPage = (VPMAnalyzerConfigurationPage) selectedPage;
                    analyzers = configPage.getAnalyzers();

                } else if (selectedPage instanceof ResultHandlingConfigurationPage) {
                    ResultHandlingConfigurationPage resultPage = (ResultHandlingConfigurationPage) selectedPage;
                    if (analyzers != null) {
                        resultPage.setSelectedAnalyzers(analyzers);
                    }

                } else if (selectedPage instanceof VPMRefinementPage) {

                    final VPMRefinementPage refinementPage = (VPMRefinementPage) selectedPage;

                    final SPLevoBlackBoard blackBoard = new SPLevoBlackBoard();
                    VPMAnalysisWorkflowDelegate delegate = new VPMAnalysisWorkflowDelegate(config, blackBoard, true);

//                    VPMAnalysisWorkflowForRefinementPageDelegate delegate = new VPMAnalysisWorkflowForRefinementPageDelegate(
//                            config, blackBoard);

                    Runnable uiProcess = new Runnable() {
                        @Override
                        public void run() {
                            EList<Refinement> refinements = blackBoard.getRefinementModel().getRefinements();
                            refinementPage.setRefinements(refinements);
                        }
                    };

                    WorkflowListenerUtil.runUIBlockingWorkflow(delegate, "Refine VPM", uiProcess);


                    // TODO: Read analysis result (refinements) from blackboard. Maybe via parameter
                    // of the workflow delegate?
                    // TODO: Pass analysis result (refinements) to VPMRefinementPage
                }
            }
        };
    }

    /**
     * Create the wizard dialog as a container of the vpm analysis wizard.
     *
     * @param shell
     *            The UI shell to access the ui.
     * @param vpmAnalysisWizard
     *            The wizard to display in the dialog.
     * @return The prepared wizard dialog.
     */
    private WizardDialog createWizardDialog(Shell shell, VPMAnalysisWizard vpmAnalysisWizard) {
        WizardDialog wizardDialog = new WizardDialog(shell, vpmAnalysisWizard) {
            @Override
            protected Control createDialogArea(Composite parent) {
                Control ctrl = super.createDialogArea(parent);
                getProgressMonitor();
                return ctrl;
            }

            @Override
            protected IProgressMonitor getProgressMonitor() {
                ProgressMonitorPart monitor = (ProgressMonitorPart) super.getProgressMonitor();
                GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
                gridData.heightHint = 0;
                monitor.setLayoutData(gridData);
                monitor.setVisible(false);
                return monitor;
            }
        };
        return wizardDialog;
    }

    /**
     * Build the configuration for the workflow.
     *
     * @return The prepared configuration.
     */
    private VPMAnalysisWorkflowConfiguration buildWorflowConfiguration() {
        VPMAnalysisWorkflowConfiguration defaultConfig = new VPMAnalysisWorkflowConfiguration();
        defaultConfig.setSplevoProjectEditor(splevoProjectEditor);

        // Set default presentation mode
        defaultConfig.setPresentation(ResultPresentation.REFINEMENT_BROWSER);

        return defaultConfig;
    }

}
