package org.splevo.ui.listeners;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.wizards.vpmanalysis.VPMAnalysisWizard;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration;
import org.splevo.ui.workflow.VPMAnalysisWorkflowDelegate;
import org.splevo.vpm.analyzer.refinement.BasicDetectionRule;
import org.splevo.vpm.analyzer.refinement.DetectionRule;
import org.splevo.vpm.refinement.RefinementType;

/**
 * Mouse adapter to listen for events which trigger the refinement process of a variation point
 * model.
 */
public class VPMAnalysisListener extends MouseAdapter {

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
        WizardDialog wizardDialog = new WizardDialog(shell, new VPMAnalysisWizard(config));
        if (wizardDialog.open() != Window.OK) {
            logger.debug("Variation Point Analyses canceled");
            return;
        }

        // validate configuration
        if (!config.isValid()) {
            MessageDialog.openError(shell, "Invalid Project Configuration", config.getErrorMessage());
            return;
        }

        // trigger workflow

        VPMAnalysisWorkflowDelegate workflowDelegate = new VPMAnalysisWorkflowDelegate(config);
        IAction action = new Action("Refine VPM") {
        };
        workflowDelegate.run(action);
    }

    /**
     * Build the configuration for the workflow.
     * 
     * @return The prepared configuration.
     */
    private VPMAnalysisWorkflowConfiguration buildWorflowConfiguration() {
        VPMAnalysisWorkflowConfiguration config = new VPMAnalysisWorkflowConfiguration();
        config.setSplevoProjectEditor(splevoProjectEditor);
        
        // build the detection rules
        List<DetectionRule> detectionRules = new ArrayList<DetectionRule>();

        List<String> edgeLabels = new ArrayList<String>();
        edgeLabels.add("CodeLocation");
        detectionRules.add(new BasicDetectionRule(edgeLabels, RefinementType.MERGE));
        
        List<String> edgeLabelsStructure = new ArrayList<String>();
        edgeLabelsStructure.add("ProgramStructure");
        detectionRules.add(new BasicDetectionRule(edgeLabelsStructure, RefinementType.MERGE));
        
        config.setDetectionRules(detectionRules);
        
        return config;
    }

}
