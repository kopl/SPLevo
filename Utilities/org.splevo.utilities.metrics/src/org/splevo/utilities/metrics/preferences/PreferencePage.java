package org.splevo.utilities.metrics.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.splevo.utilities.metrics.Activator;
import org.splevo.utilities.metrics.util.Constants;

/**
 * Class for Preferences Pages in Eclipse Menu.
 * 
 * @author Bodo Vossen
 * 
 */
public class PreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    /**
     * Constructor for the PreferencePage Class.
     */
    public PreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("Preferences Page for the Metrics View");
    }

    /**
     * Create the Preferences Pages with its entries.
     */
    public void createFieldEditors() {
        BooleanFieldEditor boolField = new BooleanFieldEditor(Constants.METRICS_VIEW_ACTIVE, "Metric View Active: ",
                getFieldEditorParent());

        StringFieldEditor stringField = new StringFieldEditor(Constants.METRICS, "Enter a Metric:",
                getFieldEditorParent());

        Label stringLabel = stringField.getLabelControl(getFieldEditorParent());
        stringLabel.setToolTipText("Enter Metrics separated only by comma and without a Whitespace."
                + " Calculaation is not case sensitive.");

        addField(boolField);
        addField(stringField);
    }

    @Override
    public void init(IWorkbench workbench) {
    }
}