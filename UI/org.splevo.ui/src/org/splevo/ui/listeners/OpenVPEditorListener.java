/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Busch
 *******************************************************************************/

package org.splevo.ui.listeners;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.jobs.JobUtil;
import org.splevo.ui.vpexplorer.explorer.VPExplorer;
import org.splevo.vpm.VPMUtil;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Mouse adapter to listen for events which trigger the extraction of the source projects.
 */
public class OpenVPEditorListener extends MouseAdapter {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(OpenVPEditorListener.class);

    /** The internal reference to the splevo project editor to work with. */
    private SPLevoProjectEditor splevoProjectEditor = null;

    /**
     * A listener to open the VPExplorer with a reasonable user feedback. It always openes the
     * latest vpm of the SPLevo project under control of a splevoProjectEditor.
     *
     * The editor is bound to the vpm instead of the splevo project or the vpm itself, as both might
     * been target of change between the listener intialization and assignment to the editor and the
     * click by the user.
     *
     * @param splevoProjectEditor
     *            The reference to the splevo project editor.
     */
    public OpenVPEditorListener(SPLevoProjectEditor splevoProjectEditor) {
        this.splevoProjectEditor = splevoProjectEditor;
    }

    @Override
    public void mouseUp(MouseEvent e) {

        SPLevoProject splevoProject = splevoProjectEditor.getSplevoProject();
        EList<String> vpmModelPaths = splevoProject.getVpmModelPaths();
        if (vpmModelPaths.size() == 0) {
            Shell shell = Display.getDefault().getShells()[0];
            MessageDialog.openError(shell, "No VPM to open", "There is Variation Point Model to be opened");
            logger.error("Tried to open VPM from a SPLevoProject that does not contain any");
            return;
        }

        String vpmPath = vpmModelPaths.get(vpmModelPaths.size() - 1);
        openVPExplorer(splevoProject, vpmPath);
    }

    /**
     * Open the VPExplorer encapsulated in an Eclipse UI job to provide an appropriate feedback
     * about the processing to the user.
     *
     * @param splevoProject
     *            The project to initialize the {@link ResourceSet}, e.g. for cache improved loading
     *            etc.
     * @param vpmPath
     *            The variation point model to open.
     */
    private void openVPExplorer(final SPLevoProject splevoProject, final String vpmPath) {
        Job job = new Job("Open VP Explorer") {

            @Override
            protected IStatus run(final IProgressMonitor monitor) {
                monitor.beginTask("Open VPExplorer", IProgressMonitor.UNKNOWN);

                monitor.subTask("Load Variation Point Model");
                VariationPointModel vpm = loadVPM(splevoProject, vpmPath, monitor);
                if (vpm == null) {
                    return Status.CANCEL_STATUS;
                }

                monitor.subTask("Init VPM Access");
                initVPMAccess(monitor, vpm);

                monitor.subTask("Open View");
                openViewPart(vpm);

                monitor.done();
                return Status.OK_STATUS;
            }

        };
        job.setUser(true);
        job.schedule();
    }

    /**
     * Initialize the access to the variation point model. This makes use of internal knowledge
     * about how the editor will access the variation points and accordingly initializes this
     * access.
     *
     * It is not yet moved to the editor as it reports to a monitor for better user experience.
     *
     * @param monitor
     *            The monitor to report to (using a submonitor).
     * @param vpm
     *            The variation point model to initialize.
     */
    private void initVPMAccess(final IProgressMonitor monitor, VariationPointModel vpm) {
        SubMonitor subMonitor = SubMonitor.convert(monitor, "Init VPM Access", vpm.getVariationPointGroups().size());
        for (VariationPointGroup vpg : vpm.getVariationPointGroups()) {
            for (VariationPoint vp : vpg.getVariationPoints()) {
                vp.getLocation().getSourceLocation();
            }
            subMonitor.worked(1);
        }
        subMonitor.done();
    }

    private void openViewPart(final VariationPointModel vpm) {
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                try {
                    IWorkbenchWindow activeWorkbench = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                    IViewPart viewPart = activeWorkbench.getActivePage().showView(VPExplorer.VIEW_ID);
                    final VPExplorer explorer = (VPExplorer) viewPart;
                    explorer.getVpExplorerContent().setVpm(vpm);
                } catch (PartInitException e) {
                    logger.error("Could not create the VP explorer view", e);
                }
            }
        });
    }

    private VariationPointModel loadVPM(final SPLevoProject splevoProject, final String vpmPath,
            final IProgressMonitor monitor) {
        VariationPointModel vpm = null;
        ResourceSet resSet = JobUtil.initResourceSet(splevoProject);
        try {
            vpm = VPMUtil.loadVariationPointModel(new File(vpmPath), resSet);
        } catch (IOException ioe) {
            monitor.setCanceled(true);
            showErrorDialog("Failed to open VPM", "An error occured while opening the VPM: " + ioe.getMessage());
            logger.error("Failed to load variation point model.", ioe);
        }
        return vpm;
    }

    /**
     * Open an error dialog synchronized with the UI thread to access the shell.
     *
     * @param title
     *            The title of the dialog.
     * @param message
     *            The message to display in the dialog.
     */
    private void showErrorDialog(final String title, final String message) {
        Display.getDefault().syncExec(new Runnable() {
            @Override
            public void run() {
                Shell shell = Display.getDefault().getShells()[0];
                MessageDialog.openError(shell, title, message);
            }
        });
    }
}