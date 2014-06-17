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

package org.splevo.ui.vpexplorer.explorer;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * This mediator coordinates the interactions between a VP explorer and a VP grouping explorer.
 */
public class ExplorerMediator {

    private static Logger logger = Logger.getLogger(ExplorerMediator.class);

    private VPExplorer vpExplorer;
    private VPGroupingExplorer vpGroupingExplorer;

    private boolean shouldUpdateVPM = false;

    /**
     * Registers the VP explorer at this mediator.
     * 
     * @param vpExplorer
     *            the VP explorer to register.
     */
    public void registerVPExplorer(VPExplorer vpExplorer) {
        this.vpExplorer = vpExplorer;
    }

    /**
     * Deregisters the VP explorer. Will have no effect if no VP explorer was registered
     * beforehand.
     */
    public void deregisterVPExplorer() {
        vpExplorer = null;
    }

    /**
     * Registers the VP grouping explorer at this mediator.
     * 
     * @param groupingExplorer
     *            the VP grouping explorer explorer to register.
     */
    public void registerVPGroupingExplorer(VPGroupingExplorer groupingExplorer) {
        this.vpGroupingExplorer = groupingExplorer;
        if (shouldUpdateVPM) {
            updateVPM();
        }
    }

    /**
     * Deregisters the VP grouping explorer. Will have no effect if no VP grouping explorer was
     * registered beforehand.
     */
    public void deregisterVPGRoupingExplorer() {
        vpGroupingExplorer = null;
    }

    /**
     * Sends the VPM of the VP explorer to the VP grouping explorer.
     */
    public void updateVPM() {
        if (vpExplorer != null) {
            if (vpGroupingExplorer != null) {
                vpGroupingExplorer.setVPM(vpExplorer.getVpExplorerContent().getVpm());
            } else {
                shouldUpdateVPM = true;
                openVPGroupingExplorer(vpExplorer.getVpExplorerContent().getVpm());
            }
        } else {
            logger.warn("Tried to distribute VP Explorer Content without registered VP Explorer");
        }
    }

    private void openVPGroupingExplorer(final VariationPointModel vpm) {
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                try {
                    IWorkbenchWindow activeWorkbench = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                    IViewPart viewPart = activeWorkbench.getActivePage().showView(VPGroupingExplorer.VIEW_ID);
                    final VPGroupingExplorer explorer = (VPGroupingExplorer) viewPart;
                    if (vpm != null) {
                        explorer.setVPM(vpm);
                    }
                } catch (PartInitException e) {
                    logger.error("Could not create the VP grouping explorer view", e);
                }
            }
        });
    }
}
