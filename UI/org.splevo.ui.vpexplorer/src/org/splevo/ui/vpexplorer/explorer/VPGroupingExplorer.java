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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.navigator.CommonNavigator;
import org.splevo.ui.vpexplorer.Activator;
import org.splevo.vpm.variability.VariationPointModel;

// TODO: Auto-generated Javadoc
/**
 * The Class VPGroupingExplorer.
 */
public class VPGroupingExplorer extends CommonNavigator {

    /** Id to reference the view inside eclipse. */
    public static final String VIEW_ID = "org.splevo.ui.vpxplorer.grouping";

    /** The mediator. */
    private ExplorerMediator mediator = Activator.EXPLORER_MEDIATOR;
    
    /** The vp explorer content. */
    private VPExplorerContent vpExplorerContent;

    /**
     * Default explorer setting up the required dependencies.
     */
    public VPGroupingExplorer() {
        vpExplorerContent = new VPExplorerContent(this);
        mediator.registerVPGroupingExplorer(this);
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.navigator.CommonNavigator#getInitialInput()
     */
    @Override
    protected IAdaptable getInitialInput() {
        this.getCommonViewer().refresh();
        return vpExplorerContent;
    }

    /**
     * Access the static content element.
     * 
     * @return The singleton content element.
     */
    public VPExplorerContent getVpExplorerContent() {
        return vpExplorerContent;
    }

    
    /**
     * Sets the vpm.
     *
     * @param vpm the new vpm
     */
    public void setVPM(VariationPointModel vpm) {
        this.vpExplorerContent.setVpm(vpm);
//        this.getCommonViewer().refresh();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.navigator.CommonNavigator#dispose()
     */
    @Override
    public void dispose() {
        mediator.deregisterVPGRoupingExplorer();
        super.dispose();
    }
}
