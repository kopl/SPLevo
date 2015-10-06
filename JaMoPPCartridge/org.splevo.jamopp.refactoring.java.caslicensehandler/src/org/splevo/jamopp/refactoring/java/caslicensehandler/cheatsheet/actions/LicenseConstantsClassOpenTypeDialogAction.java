package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import org.emftext.language.java.classifiers.ConcreteClassifier;

/**
 * Specialized dialog which can be used to specify the license constant class.
 */
public class LicenseConstantsClassOpenTypeDialogAction extends OpenTypeDialogAction {

    /**
     * Constructs the dialog.
     */
    public LicenseConstantsClassOpenTypeDialogAction() {
        super("Selection of license constant class.");
    }

    @Override
    protected void processFoundClassifier(ConcreteClassifier classifier) {
        CASLicenseHandlerConfiguration.getInstance().setLicenseConstantClassifier(classifier);
    }
}
