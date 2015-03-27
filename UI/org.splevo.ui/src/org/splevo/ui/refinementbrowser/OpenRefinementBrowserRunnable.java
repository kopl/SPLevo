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
package org.splevo.ui.refinementbrowser;

import org.apache.log4j.Logger;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.vpm.refinement.RefinementModel;

/**
 * Task to open the refinement editor with the results of a a list of recommended refinements.
 */
public class OpenRefinementBrowserRunnable implements Runnable {

    private static Logger logger = Logger.getLogger(OpenRefinementBrowserRunnable.class);

    private SPLevoProjectEditor splevoProjectEditor;
    private RefinementModelProvider refinementModelProvider;

    /**
     * Constructor to set the required dependencies.
     *
     * @param splevoProjectEditor
     *            The splevo editor to link the refinement editor with.
     * @param refinementModelProvider
     *            The provider for the refinements to display.
     */
    public OpenRefinementBrowserRunnable(SPLevoProjectEditor splevoProjectEditor,
            RefinementModelProvider refinementModelProvider) {
        this.refinementModelProvider = refinementModelProvider;
        this.splevoProjectEditor = splevoProjectEditor;
    }

    @Override
    public void run() {
        try {
            RefinementModel refinementModel = refinementModelProvider.getRefinementModel();
            VPMRefinementBrowserInput input = new VPMRefinementBrowserInput(refinementModel, splevoProjectEditor);
            IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            activePage.openEditor(input, VPMRefinementBrowser.ID);
        } catch (PartInitException e) {
            logger.error("Editor could not be opened", e);
        }
    }

}
