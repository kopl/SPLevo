package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaUIMessages;
import org.eclipse.jdt.internal.ui.dialogs.OpenTypeSelectionDialog;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.eclipse.ui.dialogs.SelectionDialog;

@SuppressWarnings("restriction")
public class OpenTypeDialogAction extends Action implements ICheatSheetAction {

	@Override
	public void run(String[] params, ICheatSheetManager manager) {
		SelectionDialog dialog = initTypeDialog();
		if(null == dialog) {
			return;
		}
		
		dialog.open();

		LicenseConstants.setLicenseConstant(getFileFrom((IType) dialog.getResult()[0]));
	}
	
	private File getFileFrom(IType chosenLicenseConstantClass) {
		return chosenLicenseConstantClass.getResource().getRawLocation().makeAbsolute().toFile();
	}
	
	private SelectionDialog initTypeDialog() {
		SelectionDialog dialog = new OpenTypeSelectionDialog(JavaPlugin.getActiveWorkbenchShell(), true,
			    PlatformUI.getWorkbench().getProgressService(), null, 
			    IJavaSearchConstants.TYPE);
		dialog.setTitle(JavaUIMessages.OpenTypeAction_dialogTitle);
		dialog.setMessage(JavaUIMessages.OpenTypeAction_dialogMessage);
			
		return dialog;
	}

}
