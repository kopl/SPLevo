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

import org.eclipse.ui.navigator.CommonNavigator;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * The VPExplorer displays a VP model in a tree structure.
 */
public class VPExplorer extends CommonNavigator {

    /** Id to reference the view inside eclipse. */
    public static final String VIEW_ID = "org.splevo.ui.vpexplorer";

    private static VPExplorerContent vpExplorerContent = new VPExplorerContent();

    @Override
    protected Object getInitialInput() {
        return vpExplorerContent;
    }


    public static VPExplorerContent getVpExplorerContent() {
        return vpExplorerContent;
    }

}
