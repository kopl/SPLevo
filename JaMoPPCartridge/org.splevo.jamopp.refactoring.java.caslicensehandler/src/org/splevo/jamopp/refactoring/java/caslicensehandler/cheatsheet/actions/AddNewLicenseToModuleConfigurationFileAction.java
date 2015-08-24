package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaUIMessages;
import org.eclipse.jdt.internal.ui.dialogs.OpenTypeSelectionDialog;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.ide.IDE;

public class AddNewLicenseToModuleConfigurationFileAction extends Action implements ICheatSheetAction {

	@Override
	public void run(String[] params, ICheatSheetManager manager) {
		SelectionDialog dialog = initTypeDialog();
		if (null == dialog) {
			return;
		}
		
		if (dialog.open() == Window.OK) {
			IFileStore fileStore = EFS.getLocalFileSystem().getStore(getFileFrom((IType) dialog.getResult()[0]).toURI());
		    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		 
		    try {
		        IDE.openEditorOnFileStore( page, fileStore );
		    } catch ( PartInitException e ) {
		        //Put your exception handler here if you wish to
		    }
		}
		
		MessageDialog.openError(new Shell(), "Error", "An error ocurred. Please restart the cheat sheet.");
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
