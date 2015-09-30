package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import org.eclipse.jdt.core.IType;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

/**
 * Specialized dialog which can be used to specify the license-
 * constant-class.
 */
public class LicenseConstantsClassOpenTypeDialogAction extends OpenTypeDialogAction {
	
	/**
	 * Main-method to start the dialog.
	 * @param params
	 * 			is not used in this context.
	 * @param manager
	 * 			is not used in this context.
	 */
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
