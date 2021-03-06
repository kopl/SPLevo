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
package org.splevo.ui.vpexplorer.explorer;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.ui.navigator.CommonNavigator;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * A wrapper for the content to be displayed in the VP explorer.<br>
 * The wrapper is necessary to actively change what is displayed in the explorer.
 *
 * DesignDecision: Register explorer component and not viewer to be refreshed. The explorer itself
 * is registered to refresh it's CommonViewer whenever the VPM to display is changed. It's not
 * possible to register the common viewer itself as it is not instantiated and available when the
 * explorer instance is created and must be registered.
 */
public class VPExplorerContent extends PlatformObject {

    private static Logger logger = Logger.getLogger(VPExplorerContent.class);

    private VariationPointModel vpm;

    /**
     * The {@link VPExplorer} instance to refresh when the vpm to display is changed.
     */
    private CommonNavigator navigatorToRefresh;

    /**
     * Constructor to bound the content wrapper to a specific explorer.
     *
     * @param navigator
     *            The explorer to notify about input changes.
     */
    public VPExplorerContent(CommonNavigator navigator) {
        if (navigator == null) {
            logger.warn("Tried to register null as VPExplorer");
            throw new IllegalArgumentException("Tried to register null as VPExplorer");
        }

        this.navigatorToRefresh = navigator;
    }

    /**
     * Access the variation point model wrapped by the content.
     *
     * @return The vpm to present.
     */
    public VariationPointModel getVpm() {
        return vpm;
    }

    /**
     * Set the variation point model wrapped by the content.
     *
     * DesignDecision: When a VPM is set, the content wrapper registers for any modifications of the
     * EMF resource containing the VPM model.
     *
     * @param vpm
     *            The vpm to present.
     */
    public void setVpm(VariationPointModel vpm) {
        this.vpm = vpm;
        if (navigatorToRefresh != null) {
            navigatorToRefresh.getCommonViewer().refresh();

            AdapterImpl changeListener = new AdapterImpl() {
                @Override
                public void notifyChanged(Notification notification) {
                    navigatorToRefresh.getCommonViewer().refresh();
                }
            };
            if (vpm.eResource() != null) {
                vpm.eResource().eAdapters().add(changeListener);
            }
        }
    }

}
