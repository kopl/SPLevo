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

package org.splevo.ui.vpexplorer.featureoutline;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.navigator.CommonNavigator;
import org.splevo.ui.vpexplorer.Activator;
import org.splevo.ui.vpexplorer.explorer.ExplorerMediator;
import org.splevo.ui.vpexplorer.explorer.VPExplorerContent;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * The Class VPGroupingExplorer.
 */
public class FeatureOutlineView extends CommonNavigator {

    /** Id to reference the view inside eclipse. */
    public static final String VIEW_ID = "org.splevo.ui.vpexplorer.featureoutline";

    /** The mediator. */
    private ExplorerMediator mediator = Activator.EXPLORER_MEDIATOR;

    /** The vp explorer content. */
    private VPExplorerContent vpExplorerContent;

    /**
     * Default explorer setting up the required dependencies.
     */
    public FeatureOutlineView() {
        vpExplorerContent = new VPExplorerContent(this);
        mediator.registerVPGroupingExplorer(this);
    }

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
     * @param vpm
     *            the new vpm
     */
    public void setVPM(VariationPointModel vpm) {
        this.vpExplorerContent.setVpm(vpm);
    }

    @Override
    public void dispose() {
        mediator.deregisterVPGRoupingExplorer();
        super.dispose();
    }
}
