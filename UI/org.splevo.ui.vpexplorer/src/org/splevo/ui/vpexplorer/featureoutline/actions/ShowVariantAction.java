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
import org.splevo.ui.vpexplorer.Activator;
import org.splevo.ui.vpexplorer.featureoutline.FeatureOutlineContentProvider;
import org.splevo.ui.vpexplorer.featureoutline.FeatureOutlineView;
import org.splevo.ui.vpexplorer.featureoutline.content.GetVariationPointGroupChildrenBase;
import org.splevo.ui.vpexplorer.featureoutline.content.GetVariationPointGroupVariations;

/**
 * Action to show variants available for each feature.
 *
 */
public class ShowVariantAction extends Action {
    
    private static final String ICON = "icons/variants.png";
    private static final String EXTENSION_ID = "org.splevo.ui.vpexplorer.featureoutline.content";
    private FeatureOutlineView feature;
    
    /**
     * Instantiates a new toggle group action.
     * @param feature the explorer to be toggled
     */
    public ShowVariantAction(FeatureOutlineView feature) {
        super("Show variants", IAction.AS_CHECK_BOX);
        this.feature = feature;
        setImageDescriptor(Activator.getImageDescriptor(ICON));
    }
    
    @Override
    public void run() {
        FeatureOutlineContentProvider contentProvider = (FeatureOutlineContentProvider) feature.getCommonViewer()
                .getNavigatorContentService().getContentExtensionById(EXTENSION_ID).getContentProvider();
        if (this.isChecked()) {
            contentProvider.setGetChildren(new GetVariationPointGroupVariations());
        } else {
            contentProvider.setGetChildren(new GetVariationPointGroupChildrenBase());
        }    
        feature.getCommonViewer().getNavigatorContentService().update();
        
        
    }
}
