package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;
import org.splevo.ui.commons.util.WorkspaceUtil;

/**
 * This class opens the open type dialog.
 */
@SuppressWarnings("restriction")
public abstract class OpenTypeDialogAction extends Action implements ICheatSheetAction {
	
	/**
	 * Get the name from a given file.
	 * @param chosenLicenseValidatorClass
	 * 			represents a file.
	 * @return
	 * 			returns the name of the file.
	 */
	protected String getNameFrom(IFile chosenLicenseValidatorClass) {
		return FilenameUtils.removeExtension(convertToFile(chosenLicenseValidatorClass).getName());
	}
	
	/**
	 * Converts a IFile object to a File object.
	 * @param chosenLicenseConstantClass
	 * 			given IFile object which has to be converted.
	 * @return 
	 * 			returns the converted File object.
	 */
	protected File convertToFile(IFile chosenLicenseConstantClass) {
		return chosenLicenseConstantClass.getRawLocation().makeAbsolute().toFile();
	}
	
	/**
	 * Opens the FilteredItemSelectionDialog.
	 * @return
	 * 			returns the dialog.
	 */
	protected FilteredItemsSelectionDialog initTypeDialog() {
		
		return new LeadingProjectFilteredSelectionDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), 
														 false, 
														 WorkspaceUtil.transformProjectNamesToProjects(CASLicenseHandlerConfiguration.getInstance().getLeadingProject().getLeadingProjects()));
	}
	
	/**
	 * Opens a error-message.
	 */
	protected void openErrorMessage() {
		MessageDialog.openError(new Shell(), "Error", "An error ocurred. Please restart the cheat sheet.");
	}

}
