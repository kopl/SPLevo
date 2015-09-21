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
	
	protected String getNameFrom(IFile chosenLicenseValidatorClass) {
		return FilenameUtils.removeExtension(getFileFrom(chosenLicenseValidatorClass).getName());
	}

	protected File getFileFrom(IFile chosenLicenseConstantClass) {
		return chosenLicenseConstantClass.getRawLocation().makeAbsolute().toFile();
	}
	
	protected FilteredItemsSelectionDialog initTypeDialog() {
		
		return new LeadingProjectFilteredSelectionDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), 
														 false, 
														 WorkspaceUtil.transformProjectNamesToProjects(CASLicenseHandlerConfiguration.getLeadingProject().getLeadingProjects()));
	}
	
	protected void openErrorMessage() {
		MessageDialog.openError(new Shell(), "Error", "An error ocurred. Please restart the cheat sheet.");
	}

}
