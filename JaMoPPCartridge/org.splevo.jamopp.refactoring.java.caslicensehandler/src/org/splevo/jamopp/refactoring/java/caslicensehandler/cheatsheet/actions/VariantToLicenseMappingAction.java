package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;

/**
 * Opens the Mapping Dialog.
 */
public class VariantToLicenseMappingAction extends Action implements ICheatSheetAction {

    /**
     * Main-method to start the dialog.
     * 
     * @param params
     *            is not used in this context.
     * @param manager
     *            is not used in this context.
     */
    @Override
    public void run(String[] params, ICheatSheetManager manager) {
        MappingDialog dialog = new MappingDialog(Display.getCurrent().getActiveShell());
        int returnCode = dialog.open();

        if (returnCode == MappingDialog.OK) {
            notifyResult(true);
            return;
        }

        if (returnCode == MappingDialog.DATA_INCOMPLETE) {
            MessageDialog.openWarning(Display.getCurrent().getActiveShell(), "Incomplete Mapping Information",
                    "You did not provide all necessary mapping information. Please repeat this step.");
        }
        notifyResult(false);
    }

}
