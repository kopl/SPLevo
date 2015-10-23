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
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonNavigator;
import org.splevo.ui.commons.tooltip.CustomizableDescriptionHavingTreeViewerToolTip;
import org.splevo.ui.vpexplorer.Activator;
import org.splevo.ui.vpexplorer.explorer.ExplorerMediator;
import org.splevo.ui.vpexplorer.explorer.KeyFocusListener;
import org.splevo.ui.vpexplorer.explorer.VPExplorerContent;
import org.splevo.ui.vpexplorer.featureoutline.actions.NoVariabilityMechanismAction;
import org.splevo.ui.vpexplorer.featureoutline.actions.ShowVariantAction;
import org.splevo.ui.vpexplorer.linking.ILinkableNavigator;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Iterables;

/**
 * The Class VPGroupingExplorer.
 */
public class FeatureOutlineView extends CommonNavigator implements ILinkableNavigator {

    /** Id to reference the view inside eclipse. */
    public static final String VIEW_ID = "org.splevo.ui.vpexplorer.featureoutline";
    
    /** Id to reference the context of feature online inside eclipse. */
    private static final String CONTEXT_ID = "org.splevo.ui.vpexplorer.featureoutline.context";

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
    public void createPartControl(Composite aParent) {
        super.createPartControl(aParent);
        new CustomizableDescriptionHavingTreeViewerToolTip(getCommonViewer());
        getCommonViewer().addSelectionChangedListener(mediator);
        IActionBars actionBars = getViewSite().getActionBars();
        IToolBarManager toolBar = actionBars.getToolBarManager();
        toolBar.add(new NoVariabilityMechanismAction(this));    
        toolBar.add(new ShowVariantAction(this));    
        this.getCommonViewer().getTree().addFocusListener(new KeyFocusListener(CONTEXT_ID));
    }

    @Override
    public void dispose() {
        mediator.deregisterVPGRoupingExplorer();
        super.dispose();
    }

    @Override
    public void elementSelectedInOtherNavigator(Iterable<Object> selectedElements) {
        if (!this.isLinkingEnabled()) {
            return;
        }
        
        Iterable<VariationPoint> vps = Iterables.filter(selectedElements, VariationPoint.class);
        ITreeContentProvider contentProvider = getNavigatorContentService().createCommonContentProvider();
        ILinkableNavigatorHelper.expandToObject(getCommonViewer(), contentProvider, vps);
    }
}
