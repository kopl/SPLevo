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
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.vpexplorer.util;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.commons.util.JobUtil;
import org.splevo.ui.vpexplorer.explorer.VPExplorer;
import org.splevo.vpm.VPMUtil;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Utility class for variation point model interaction in the ui.
 * 
 */
public final class VPMUIUtil {

    private static Logger logger = Logger.getLogger(VPMUIUtil.class);

    /** Disable constructor to use utility statically */
    private VPMUIUtil() {
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
    public static void openVPExplorer(final SPLevoProject splevoProject, final String vpmPath) {
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
                openViewPart(vpm, splevoProject);

                monitor.done();
                return Status.OK_STATUS;
            }

        };
        job.setUser(true);
        job.schedule();
    }

    /**
     * Open an already loaded VPM in the VPExplorer encapsulated in an Eclipse UI job to provide an
     * appropriate feedback about the processing to the user.
     * 
     * @param splevoProject
     *            The project that corresponds to the VPM.
     * @param vpm
     *            The variation point model to open.
     */
    public static void openVPExplorer(final SPLevoProject splevoProject, final VariationPointModel vpm) {
        Job job = new Job("Open VP Explorer") {

            @Override
            protected IStatus run(final IProgressMonitor monitor) {
                monitor.beginTask("Open VPExplorer", IProgressMonitor.UNKNOWN);

                monitor.subTask("Init VPM Access");
                initVPMAccess(monitor, vpm);

                monitor.subTask("Open View");
                openViewPart(vpm, splevoProject);

                monitor.done();
                return Status.OK_STATUS;
            }

        };
        job.setUser(true);
        job.schedule();
    }

    private static void openViewPart(final VariationPointModel vpm, final SPLevoProject splevoProject) {
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                try {
                    IWorkbenchWindow activeWorkbench = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                    IViewPart viewPart = activeWorkbench.getActivePage().showView(VPExplorer.VIEW_ID);
                    final VPExplorer explorer = (VPExplorer) viewPart;
                    explorer.setVPM(vpm, splevoProject);
                    if (splevoProject == null) {
                        logger.warn("The VPMExplorer is about to be loaded with an invalid (null) SPLevo project.");
                    }
                } catch (PartInitException e) {
                    logger.error("Could not create the VP explorer view", e);
                }
            }
        });
    }

    /**
     * Switches back the current VPM version of the project to the given version. The user can
     * cancel this operation, which means that the current VPM version remains the same.
     * 
     * @param splevoProject The SPLevo project for which the VPM version shall be switched.
     * @param vpmPath The file path of the VPM as noted in the project file.
     */
    public static void switchBackVPMVersion(final SPLevoProject splevoProject, final String vpmPath) {
        Job switchBackJob = new SwitchBackVPMJob(splevoProject, vpmPath);
        switchBackJob.setUser(true);
        switchBackJob.schedule();
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
    private static void initVPMAccess(final IProgressMonitor monitor, VariationPointModel vpm) {
        SubMonitor subMonitor = SubMonitor.convert(monitor, "Init VPM Access", vpm.getVariationPointGroups().size());
        for (VariationPointGroup vpg : vpm.getVariationPointGroups()) {
            for (VariationPoint vp : vpg.getVariationPoints()) {
                vp.getLocation().getSourceLocation();
            }
            subMonitor.worked(1);
        }
        subMonitor.done();
    }

    private static VariationPointModel loadVPM(final SPLevoProject splevoProject, final String vpmPath,
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
    private static void showErrorDialog(final String title, final String message) {
        Display.getDefault().syncExec(new Runnable() {
            @Override
            public void run() {
                Shell shell = Display.getDefault().getActiveShell();
                MessageDialog.openError(shell, title, message);
            }
        });
    }

}
