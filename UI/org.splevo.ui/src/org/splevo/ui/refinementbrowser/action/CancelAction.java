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
package org.splevo.ui.refinementbrowser.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.splevo.ui.SPLevoUIPlugin;
import org.splevo.ui.refinementbrowser.VPMRefinementBrowser;

/**
 * Action to close the editor.
 */
public class CancelAction extends Action {

    /** The refinement viewer to access the selected refinements. */
    private VPMRefinementBrowser vpmRefinementBrowser = null;

    /**
     * Constructor requiring a reference to the viewer to get the refinements to be executed from as
     * well as the text of the action.
     *
     * @param vpmRefinementBrowser
     *            The reference to the viewer.
     * @param text
     *            The text for the action.
     */
    public CancelAction(VPMRefinementBrowser vpmRefinementBrowser, String text) {
        super(text);
        this.vpmRefinementBrowser = vpmRefinementBrowser;
    }

    /**
     * Close the editor.
     *
     * @param event
     *            The event that triggered the refinement action.
     */
    @Override
    public void runWithEvent(Event event) {

        // close the browser
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        IWorkbenchPage page = window.getActivePage();
        page.closeEditor(vpmRefinementBrowser, false);
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return SPLevoUIPlugin.getImageDescriptor("icons/cancel.gif");
    }
}
