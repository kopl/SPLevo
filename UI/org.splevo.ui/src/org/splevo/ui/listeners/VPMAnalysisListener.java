package org.splevo.ui.listeners;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
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
import org.splevo.ui.wizards.vpmanalysis.ResultHandlingConfigurationPage;
import org.splevo.ui.wizards.vpmanalysis.VPMAnalysisWizard;
import org.splevo.ui.wizards.vpmanalysis.VPMAnalyzerConfigurationPage;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration.ResultPresentation;
import org.splevo.ui.workflow.VPMAnalysisWorkflowDelegate;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.codelocation.CodeLocationVPMAnalyzer;
import org.splevo.vpm.analyzer.programstructure.ProgramStructureVPMAnalyzer;
import org.splevo.vpm.analyzer.refinement.BasicDetectionRule;
import org.splevo.vpm.analyzer.refinement.DetectionRule;
import org.splevo.vpm.analyzer.semantic.SemanticVPMAnalyzer;
import org.splevo.vpm.refinement.RefinementType;

/**
 * Mouse adapter to listen for events which trigger the refinement process of a
 * variation point model.
 */
public class VPMAnalysisListener extends MouseAdapter {

	protected static final Object ResultHandlingConfigurationPage = null;

	/** The logger for this class. */
	private Logger logger = Logger.getLogger(VPMAnalysisListener.class);

	/** The internal reference to the SPLevo project editor to work with. */
	private SPLevoProjectEditor splevoProjectEditor = null;

	/**
	 * Constructor requiring the reference to a splevoProject.
	 * 
	 * @param splevoProjectEditor
	 *            The references to the splevo project editor.
	 */
	public VPMAnalysisListener(SPLevoProjectEditor splevoProjectEditor) {
		this.splevoProjectEditor = splevoProjectEditor;
	}

	@Override
	public void mouseUp(MouseEvent e) {

		// Initialize the requried data
		VPMAnalysisWorkflowConfiguration config = buildWorflowConfiguration();
		Shell shell = e.widget.getDisplay().getShells()[0];

		// trigger the wizard to configure the refinement process
		WizardDialog wizardDialog = new WizardDialog(shell,
				new VPMAnalysisWizard(config)) {
			@Override
			protected Control createDialogArea(Composite parent) {
				Control ctrl = super.createDialogArea(parent);
				getProgressMonitor();
				return ctrl;
			}

			@Override
			protected IProgressMonitor getProgressMonitor() {
				ProgressMonitorPart monitor = (ProgressMonitorPart) super
						.getProgressMonitor();
				GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
				gridData.heightHint = 0;
				monitor.setLayoutData(gridData);
				monitor.setVisible(false);
				return monitor;
			}
		};
		wizardDialog.addPageChangedListener(new IPageChangedListener() {
			private List<VPMAnalyzer> analyzers = null;
			private boolean clearRules = true;

			@Override
			public void pageChanged(PageChangedEvent event) {
				Object selectedPage = event.getSelectedPage();
				if (selectedPage instanceof VPMAnalyzerConfigurationPage) {
					VPMAnalyzerConfigurationPage configPage = (VPMAnalyzerConfigurationPage) selectedPage;
					analyzers = configPage.getAnalyzers();
				} else if (selectedPage instanceof ResultHandlingConfigurationPage) {
					ResultHandlingConfigurationPage resultPage = (ResultHandlingConfigurationPage) selectedPage;
					if (analyzers != null) {
						resultPage.prepareForInitialCall(analyzers, clearRules);
					}
					clearRules = false;
				}
			}
		});

		if (wizardDialog.open() != Window.OK) {
			logger.debug("Variation Point Analyses canceled");
			return;
		}

		// validate configuration
		if (!config.isValid()) {
			MessageDialog.openError(shell, "Invalid Project Configuration",
					config.getErrorMessage());
			return;
		}

		// trigger workflow

		VPMAnalysisWorkflowDelegate workflowDelegate = new VPMAnalysisWorkflowDelegate(
				config);
		IAction action = new Action("Refine VPM") {
		};
		workflowDelegate.run(action);

		splevoProjectEditor.enableButtonsIfInformationAvailable();
	}

	/**
	 * Build the configuration for the workflow.
	 * 
	 * @return The prepared configuration.
	 */
	private VPMAnalysisWorkflowConfiguration buildWorflowConfiguration() {
		VPMAnalysisWorkflowConfiguration config = new VPMAnalysisWorkflowConfiguration();
		config.setSplevoProjectEditor(splevoProjectEditor);

		// Set default presentation mode
		config.setPresentation(ResultPresentation.RELATIONSHIP_GRAPH_ONLY);

		// build the detection rules
		List<DetectionRule> detectionRules = new ArrayList<DetectionRule>();

		List<String> edgeLabels = new ArrayList<String>();
		edgeLabels
				.add(CodeLocationVPMAnalyzer.RELATIONSHIP_LABEL_CODE_LOCATION);
		edgeLabels
				.add(ProgramStructureVPMAnalyzer.RELATIONSHIP_LABEL_PROGRAM_STRUCTURE);
		edgeLabels.add(SemanticVPMAnalyzer.RELATIONSHIP_LABEL_SEMANTIC);

		detectionRules.add(new BasicDetectionRule(edgeLabels,
				RefinementType.MERGE));

		config.setDetectionRules(detectionRules);

		return config;
	}

}
