/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.refinementbrowser.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.splevo.ui.SPLevoUIPlugin;
import org.splevo.ui.refinementbrowser.VPMRefinementBrowser;

/**
 * An action to toggle the graph visualization of the refinement browser.
 */
public class ToggleVisualizationAction extends Action {

    private static final String ENABLE_TEXT = "Enable Visualization";
    private static final String DISABLE_TEXT = "Disable Visualization";
    
    private final VPMRefinementBrowser refinementBrowser;
    
    /**
     * Constructs a new toggle action for the graph visualization.
     * @param refinementBrowser The refinement browser that contains the visualization.
     * @param initialState The initial state of the toggle.
     */
    public ToggleVisualizationAction(VPMRefinementBrowser refinementBrowser, boolean initialState) {
        super("", AS_CHECK_BOX);
        this.refinementBrowser = refinementBrowser;
        this.setChecked(initialState);
    }
   
    @Override
    public ImageDescriptor getImageDescriptor() {
        return SPLevoUIPlugin.getImageDescriptor("icons/refinement-graph-visualization.gif");
    }

    @Override
    public void run() {
        refinementBrowser.setEnableVisualization(isChecked());
        if (isChecked()) {
            setText(DISABLE_TEXT);
        } else {
            setText(ENABLE_TEXT);
        }
    }

}
