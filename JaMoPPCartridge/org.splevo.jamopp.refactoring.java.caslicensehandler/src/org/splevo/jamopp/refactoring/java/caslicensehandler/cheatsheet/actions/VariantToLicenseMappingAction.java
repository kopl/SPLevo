package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;

/**
 * Opens the Mapping Dialog.
 */
public class VariantToLicenseMappingAction extends Action implements
		ICheatSheetAction {
	
	/**
	 * Main-method to start the dialog.
	 * @param params
	 * 			is not used in this context.
	 * @param manager
	 * 			is not used in this context.
	 */
	@Override
	public void run(String[] params, ICheatSheetManager manager) {
		MappingDialog dialog = new MappingDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell());
		dialog.open();
	}

}
