package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import org.eclipse.jdt.core.IType;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

/**
 * Specialized dialog which can be used to specify
 * the validator-class.
 */
public class ValidatorClassOpenTypeDialogAction extends OpenTypeDialogAction {

	/**
	 * Main-method to start the dialog.
	 * @param params
	 * 			is not used in this context.
	 * @param manager
	 * 			is not used in this context.
	 */
	@Override
	public void run(String[] params, ICheatSheetManager manager) {
		FilteredItemsSelectionDialog licenseValidatorDialog = initTypeDialog("Selection of license validator class.");
		
		if (licenseValidatorDialog.open() == Window.OK) {
		    IType type = (IType) licenseValidatorDialog.getResult()[0];
			CASLicenseHandlerConfiguration.getInstance().setLicenseValidatorType(type);
			return;
		}
		
		openErrorMessage();
	}
}
