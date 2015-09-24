package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

public class ValidatorClassOpenTypeDialogAction extends OpenTypeDialogAction {

	@Override
	public void run(String[] params, ICheatSheetManager manager) {
		FilteredItemsSelectionDialog licenseValidatorDialog = initTypeDialog();
		if (null == licenseValidatorDialog) {
			return;
		}
		
		if (licenseValidatorDialog.open() == Window.OK) {
			CASLicenseHandlerConfiguration.getInstance().setLicenseValidatorName(getNameFrom((IFile) licenseValidatorDialog.getResult()[0]));
			return;
		}
		
		openErrorMessage();
	}
}
