/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.ui.nature;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.splevo.ui.perspective.SPLevoPerspectiveFactory;
import org.splevo.ui.wizards.vpmanalysis.VPMAnalyzerConfigurationPage;

/**
 * Action to switch a projects SPLevo project nature on and of.
 */
public class AddSPLevoNatureHandler extends AbstractHandler {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(VPMAnalyzerConfigurationPage.class);

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection != null & selection instanceof IStructuredSelection) {
            for (Iterator<?> it = ((IStructuredSelection) selection).iterator(); it.hasNext();) {
                Object element = it.next();
                IProject project = null;
                if (element instanceof IProject) {
                    project = (IProject) element;
                } else if (element instanceof IAdaptable) {
                    project = (IProject) ((IAdaptable) element).getAdapter(IProject.class);
                }
                if (project != null) {
                    addSPLevoNature(project);
                }
            }
        }

        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow activeWindow = workbench.getActiveWorkbenchWindow();
        Shell shell = activeWindow.getShell();
        boolean switchPerspective = MessageDialog.openConfirm(shell, "Open SPLevo Perspective",
                "SPLevo projects are associated with a specialized perspective.\n"
                        + "Do you want to switch to the SPLevo perspective?");
        if (switchPerspective) {
            try {
                workbench.showPerspective(SPLevoPerspectiveFactory.SPLEVO_PERSPECTIVE_ID, activeWindow);
            } catch (WorkbenchException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * Makes a project a SPLevo project including project file etc.
     *
     * @param project
     *            to have SPLevo nature added
     */
    private void addSPLevoNature(IProject project) {
        try {
            IProjectDescription description = project.getDescription();
            String[] natures = description.getNatureIds();

            for (int i = 0; i < natures.length; ++i) {
                if (SPLevoNature.NATURE_ID.equals(natures[i])) {
                    // Remove the nature
                    String[] newNatures = new String[natures.length - 1];
                    System.arraycopy(natures, 0, newNatures, 0, i);
                    System.arraycopy(natures, i + 1, newNatures, i, natures.length - i - 1);
                    description.setNatureIds(newNatures);
                    project.setDescription(description, null);
                    return;
                }
            }

            // Add the nature
            String[] newNatures = new String[natures.length + 1];
            System.arraycopy(natures, 0, newNatures, 0, natures.length);
            newNatures[natures.length] = SPLevoNature.NATURE_ID;
            description.setNatureIds(newNatures);
            project.setDescription(description, null);
        } catch (CoreException e) {
            logger.info("Failed to toggle the project's nature.", e);
        }
    }

}
