package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import org.eclipse.jdt.core.IType;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

public class ValidatorClassOpenTypeDialogAction extends OpenTypeDialogAction {

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
