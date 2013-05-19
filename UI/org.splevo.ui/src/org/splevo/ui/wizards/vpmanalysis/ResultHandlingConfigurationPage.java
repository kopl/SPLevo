package org.splevo.ui.wizards.vpmanalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration.ResultPresentation;
import org.splevo.vpm.analyzer.refinement.BasicDetectionRule;
import org.splevo.vpm.analyzer.refinement.DetectionRule;
import org.splevo.vpm.refinement.RefinementType;

/**
 * Wizard page to configure how the analysis result should be presented.
 * 
 * @author Benjamin Klatt
 * 
 */
public class ResultHandlingConfigurationPage extends WizardPage {

    /** The options for the result presentation dialog. */
    private static final String[] RESULT_PRESENTATION_OPTIONS = new String[] { "Refinement Browser", "VPMGraph Only" };

    /** The combo box for the result presentation options. */
    private Combo presentationCombo = null;

    /** The text input to configure one detection rule per line. */
    private Text detectionRulesText;
    
    /** The default configuration to initialize the page. */
    private VPMAnalysisWorkflowConfiguration defaultConfiguration;

    /**
     * Create the wizard.
     * @param defaultConfiguration The default configuration to initialize the page.
     */
    public ResultHandlingConfigurationPage(VPMAnalysisWorkflowConfiguration defaultConfiguration) {
        super("ResultHandlingConfiguration");
        setTitle("Analysis Result Handling");
        setDescription("Configure how you would like to handle the analysis result.");
        this.defaultConfiguration = defaultConfiguration;
    }

    /**
     * Create contents of the wizard.
     * 
     * @param parent
     *            The parent ui element to place this one into.
     */
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);

        setControl(container);

        Label lblResultPresentation = new Label(container, SWT.NONE);
        lblResultPresentation.setBounds(10, 10, 135, 20);
        lblResultPresentation.setText("Result Presentation:");

        ComboViewer comboViewer = new ComboViewer(container, SWT.NONE);
        presentationCombo = comboViewer.getCombo();
        presentationCombo.setItems(RESULT_PRESENTATION_OPTIONS);
        presentationCombo
                .setToolTipText("Select how to present the analysis result.\r\nIn case of the \"VPMGraph only\" option, the refinement detection will be skipped.");
        presentationCombo.setBounds(145, 7, 288, 28);
        presentationCombo.select(1);

        Label lblDetectionRules = new Label(container, SWT.NONE);
        lblDetectionRules.setBounds(10, 36, 130, 20);
        lblDetectionRules.setText("Detection Rules:");

        detectionRulesText = new Text(container, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        detectionRulesText.setBounds(10, 62, 552, 65);
        detectionRulesText.setText(buildDetectionRuleText(defaultConfiguration.getDetectionRules()));

        Label lblRuleInfo = new Label(container, SWT.NONE);
        lblRuleInfo.setBounds(10, 133, 552, 94);
        lblRuleInfo
                .setText("Rules are only applied for the option \"Refinement Browser\". \r\nAdd one rule per line in the format \"Label1,Label2|Operation\" \r\nlabels = relationship label\r\noperation = \"MERGE\" or \"GROUP\"");
    }

    /**
     * Get the result presentation configured on the wizard page.
     * 
     * @return The result presentation option.
     */
    public ResultPresentation getResultPresentation() {
        int selected = presentationCombo.getSelectionIndex();

        ResultPresentation presentation = null;

        switch (selected) {
        case 0:
            presentation = ResultPresentation.REFINEMENT_BROWSER;
            break;

        case 1:
        default:
            presentation = ResultPresentation.RELATIONSHIP_GRAPH_ONLY;
            break;
        }

        return presentation;
    }

    /**
     * Get the detection rules configured by the user.
     * 
     * @return The list of prepared detection rules.
     */
    public List<DetectionRule> getDetectionRules() {
        List<DetectionRule> rules = new ArrayList<DetectionRule>();

        String completeText = detectionRulesText.getText();
        for (String line : completeText.split("\n")) {
            String[] chunks = line.split("|");
            List<String> labels = Arrays.asList(chunks[0].split(","));
            DetectionRule rule = new BasicDetectionRule(labels, RefinementType.get(chunks[1]));
            rules.add(rule);
        }

        return rules;
    }

    /** 
     * Convert a list of detection rules into textual representation.
     * 
     * @param detectionRules The list of detection rules.
     * @return The textual representation of the detection rules.
     */
    private String buildDetectionRuleText(List<DetectionRule> detectionRules) {

        StringBuilder configBuffer = new StringBuilder();

        for (DetectionRule rule : detectionRules) {
            boolean first = true;
            for (String label : rule.getEdgeLabels()) {
                if (!first) {
                    configBuffer.append(",");
                }
                configBuffer.append(label);
                first = false;
            }

            configBuffer.append("|");
            configBuffer.append(rule.getRefinementType().toString());
            configBuffer.append("\n");
        }
        
        return configBuffer.toString();

    }
}
