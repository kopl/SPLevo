package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import org.emftext.language.java.classifiers.ConcreteClassifier;

/**
 * Specialized dialog which can be used to specify the validator class.
 */
public class ValidatorClassOpenTypeDialogAction extends OpenTypeDialogAction {

    /**
     * Constructs the dialog.
     */
    public ValidatorClassOpenTypeDialogAction() {
        super("Selection of license validator class.");
    }

    @Override
    protected void processFoundClassifier(ConcreteClassifier classifier) {
        CASLicenseHandlerConfiguration.getInstance().setLicenseValidatorClassifier(classifier);
    }
}
