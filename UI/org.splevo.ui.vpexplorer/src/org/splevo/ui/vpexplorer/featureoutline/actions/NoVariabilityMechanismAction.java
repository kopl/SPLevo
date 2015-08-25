/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thomas Czogalik
 *******************************************************************************/

package org.splevo.ui.vpexplorer.featureoutline.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.navigator.CommonViewer;
import org.splevo.ui.vpexplorer.Activator;
import org.splevo.ui.vpexplorer.featureoutline.FeatureOutlineView;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;

/**
 * Action to filter all the variation points, which have no variability mechanism.
 */
public class NoVariabilityMechanismAction extends Action {
    
    private static final String ICON = "icons/filter.png";
    private FeatureOutlineView feature;
    /** The ViewerFilter to filter the viewer. **/
    private static ViewerFilter filter;
    
    static {
        filter = new ViewerFilter() {
            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {                
                if (element instanceof VariationPoint && ((VariationPoint) element).getVariabilityMechanism() == null) {
                    return true;
                } else if (element instanceof VariationPointGroup) {
                    ITreeContentProvider contentProvider = (ITreeContentProvider) ((CommonViewer) viewer).getContentProvider();
                    for (Object child : contentProvider.getChildren(element)) {
                        if (select(viewer, element, child)) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };
    }
    
    /**
     * Instantiates a new toggle group action.
     * @param feature the explorer to be toggled
     */
    public NoVariabilityMechanismAction(FeatureOutlineView feature) {
        super("Show variation points with no variability mechanism", IAction.AS_CHECK_BOX);
        this.feature = feature;
        setImageDescriptor(Activator.getImageDescriptor(ICON));
    }
    
    @Override
    public void run() {        
        if (this.isChecked()) {
            feature.getCommonViewer().addFilter(filter);
        } else {
            feature.getCommonViewer().removeFilter(filter);
        }        
    }
}
