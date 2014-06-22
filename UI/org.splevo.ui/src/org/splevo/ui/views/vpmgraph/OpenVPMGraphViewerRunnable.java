/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    CONTRIBUTR_FIRST_AND_LAST_NAME - WORK_DONE (e.g. "initial API and implementation and/or initial documentation")
 *******************************************************************************/
package org.splevo.ui.views.vpmgraph;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.splevo.vpm.analyzer.graph.VPMGraph;

/**
 * A runnable to open a VPM Graph viewer with the graph accessed through an according provider.
 */
public class OpenVPMGraphViewerRunnable implements Runnable {

    private Logger logger = Logger.getLogger(OpenVPMGraphViewerRunnable.class);

    private VPMGraphProvider vpmGraphProvider;

    /**
     * Constructor requiring to set a provider to retrieve the graph from.
     *
     * @param vpmGraphProvider
     *            The provider to connect to.
     */
    public OpenVPMGraphViewerRunnable(VPMGraphProvider vpmGraphProvider) {
        this.vpmGraphProvider = vpmGraphProvider;
    }

    @Override
    public void run() {
        VPMGraph graph = vpmGraphProvider.getVpmGraph();
        if (graph == null) {
            logger.error("No VPMGraph loaded in blackboard.");
            Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
            MessageDialog.openError(shell, "VPMGraph not available",
                    "No Variation Point Graph detected in the SPLevo blackboard");
        }

        // open the viewer containing the graph
        IViewPart viewPart;
        try {
            viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .showView(VPMGraphView.ID, "vpmgraph" + graph.getId(), IWorkbenchPage.VIEW_ACTIVATE);
        } catch (PartInitException e) {
            logger.error("Unable to open VPM Graph view.", e);
            return;
        }

        final VPMGraphView view = (VPMGraphView) viewPart;
        view.showGraph(graph);

        PlatformUI.getWorkbench().addWorkbenchListener(new IWorkbenchListener() {

            @Override
            public boolean preShutdown(IWorkbench workbench, boolean forced) {
                IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                activePage.hideView(view);
                return true;
            }

            @Override
            public void postShutdown(IWorkbench workbench) {
            }
        });
    }

}
