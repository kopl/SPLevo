package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.internal.ui.dialogs.OpenTypeSelectionDialog;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;
import org.splevo.ui.commons.util.WorkspaceUtil;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * This class opens the open type dialog.
 */
@SuppressWarnings("restriction")
public abstract class OpenTypeDialogAction extends Action implements ICheatSheetAction {

    /**
     * Get the name from a given file.
     * 
     * @param chosenLicenseValidatorClass
     *            represents a file.
     * @return returns the name of the file.
     */
    protected String getNameFrom(IFile chosenLicenseValidatorClass) {
        return FilenameUtils.removeExtension(convertToFile(chosenLicenseValidatorClass).getName());
    }

    /**
     * Converts a IFile object to a File object.
     * 
     * @param chosenLicenseConstantClass
     *            given IFile object which has to be converted.
     * @return returns the converted File object.
     */
    protected File convertToFile(IFile chosenLicenseConstantClass) {
        return chosenLicenseConstantClass.getRawLocation().makeAbsolute().toFile();
    }

    /**
     * Opens the FilteredItemSelectionDialog.
     * @param dialogTitle
     * 			represent the title of the dialog.
     * @return returns the dialog.
     */
    protected FilteredItemsSelectionDialog initTypeDialog(String dialogTitle) {
        final Shell parentShell = Display.getCurrent().getActiveShell();
        final Iterable<IProject> leadingProjects = WorkspaceUtil
                .transformProjectNamesToProjects(CASLicenseHandlerConfiguration.getInstance().getLeadingProject()
                        .getLeadingProjects());
        return new ScopedOpenTypeSelectionDialog(parentShell, dialogTitle, leadingProjects);
    }

    /**
     * Opens a error-message.
     */
    protected void openErrorMessage() {
        MessageDialog.openError(new Shell(), "Error", "An error ocurred. Please restart the cheat sheet.");
    }

    /**
     * Scoped dialog for opening the Java type selection dialog. In contrast to the regular dialog,
     * this dialog is scoped to the given projects. Therefore, only types located in these projects
     * are considered.
     */
    private static class ScopedOpenTypeSelectionDialog extends OpenTypeSelectionDialog {

        public ScopedOpenTypeSelectionDialog(Shell parent, String dialogTitle, Iterable<IProject> projects) {
            super(parent, false, PlatformUI.getWorkbench().getProgressService(), createSearchScope(projects),
                    IJavaSearchConstants.TYPE);
            setTitle(dialogTitle);
        }

        private static IJavaSearchScope createSearchScope(Iterable<IProject> projects) {
            Iterable<IJavaProject> javaProjects = Iterables.filter(
                    Iterables.transform(projects, new Function<IProject, IJavaProject>() {
                        @Override
                        public IJavaProject apply(IProject input) {
                            return JavaCore.create(input);
                        }
                    }), Predicates.notNull());
            return SearchEngine.createJavaSearchScope(Iterables.toArray(javaProjects, IJavaProject.class));
        }

    }

}
