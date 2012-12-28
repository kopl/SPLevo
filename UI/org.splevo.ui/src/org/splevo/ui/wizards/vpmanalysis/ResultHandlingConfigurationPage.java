package org.splevo.ui.wizards.vpmanalysis;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration.ResultPresentation;

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

    /**
     * Create the wizard.
     */
    public ResultHandlingConfigurationPage() {
        super("ResultHandlingConfiguration");
        setTitle("Analysis Result Handling");
        setDescription("Configure how you would like to handle the analysis result.");
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
        lblResultPresentation.setBounds(10, 10, 158, 20);
        lblResultPresentation.setText("Result Presentation:");

        ComboViewer comboViewer = new ComboViewer(container, SWT.NONE);
        presentationCombo = comboViewer.getCombo();
        presentationCombo.setItems(RESULT_PRESENTATION_OPTIONS);
        presentationCombo
                .setToolTipText("Select how to present the analysis result.\r\nIn case of the \"VPMGraph only\" option, the refinement detection will be skipped.");
        presentationCombo.setBounds(10, 36, 288, 28);
        presentationCombo.select(0);
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
}
