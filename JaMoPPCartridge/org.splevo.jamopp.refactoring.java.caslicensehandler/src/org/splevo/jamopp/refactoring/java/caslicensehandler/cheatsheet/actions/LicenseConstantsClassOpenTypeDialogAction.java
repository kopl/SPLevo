package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import org.eclipse.jdt.core.IType;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

public class LicenseConstantsClassOpenTypeDialogAction extends OpenTypeDialogAction {
	@Override
	public void run(String[] params, ICheatSheetManager manager) {
		FilteredItemsSelectionDialog licenseConstantDialog = initTypeDialog("Selection of license constant class.");

		if (licenseConstantDialog.open() == Window.OK) {
		    IType type = (IType) licenseConstantDialog.getResult()[0];
			CASLicenseHandlerConfiguration.getInstance().setLicenseConstantType(type);
			return;
		}
		
		openErrorMessage();
	}
}
