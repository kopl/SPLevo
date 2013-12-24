package org.splevo.ui.wizards.vpmanalysis;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * Wizard page to modify the results of the vpm analysis.
 */
public class VPMRefinementPage extends WizardPage {

    /**
     * Create the wizard page to let the user modify the found VPM.
     */
    public VPMRefinementPage() {
        super("VPMRefinementWizardPage");
        setTitle("VPM Refinement");
        setDescription("Inspect and modify the VPM.");
    }

    @Override
    public void createControl(Composite parent) {
        // TODO Insert actual content.
        Composite container = new Composite(parent, SWT.NULL);
        setControl(container);
    }

}
