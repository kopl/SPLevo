package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.jface.window.Window;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaUIMessages;
import org.eclipse.jdt.internal.ui.dialogs.OpenTypeSelectionDialog;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.eclipse.ui.dialogs.SelectionDialog;

/**
 * This class opens the open type dialog.
 */
@SuppressWarnings("restriction")
public class OpenTypeDialogAction extends Action implements ICheatSheetAction {
	
	@Override 
	public void run(String[] params, ICheatSheetManager manager) {
		SelectionDialog dialog = initTypeDialog();
		if (null == dialog) {
			return;
		}
		
		if (dialog.open() == Window.OK) {
			CASLicenseHandlerConfiguration.setLicenseConstant(getFileFrom((IType) dialog.getResult()[0]));
			
			if (dialog.open() == Window.OK) {
				CASLicenseHandlerConfiguration.setLicenseValidatorName(getNameFrom((IType) dialog.getResult()[0]));
				return;
			}
		}
		
		MessageDialog.openError(new Shell(), "Error", "An error ocurred. Please restart the cheat sheet.");
	}
	
	private String getNameFrom(IType chosenLicenseValidatorClass) {
		return FilenameUtils.removeExtension(getFileFrom(chosenLicenseValidatorClass).getName());
	}

	private File getFileFrom(IType chosenLicenseConstantClass) {
		return chosenLicenseConstantClass.getResource().getRawLocation().makeAbsolute().toFile();
	}
	
	private SelectionDialog initTypeDialog() {
		
		//Shell mit PlatformUI.getWorkbench().getDisplay().getactiveshell...
		SelectionDialog dialog = new OpenTypeSelectionDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), true,
			    PlatformUI.getWorkbench().getProgressService(), null, 
			    IJavaSearchConstants.TYPE);
		dialog.setTitle(JavaUIMessages.OpenTypeAction_dialogTitle);
		dialog.setMessage(JavaUIMessages.OpenTypeAction_dialogMessage);
			
		return dialog;
	}

}
