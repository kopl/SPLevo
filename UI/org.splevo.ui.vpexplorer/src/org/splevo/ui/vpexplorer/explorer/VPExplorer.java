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
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonNavigator;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.commons.tooltip.CustomizableDescriptionHavingTreeViewerToolTip;
import org.splevo.ui.vpexplorer.Activator;
import org.splevo.ui.vpexplorer.explorer.actions.ExpandAllAction;
import org.splevo.ui.vpexplorer.explorer.actions.ExpandAllAction.MODE;
import org.splevo.ui.vpexplorer.explorer.actions.SelectVisibleAction;
import org.splevo.ui.vpexplorer.linking.ILinkableNavigator;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * The VPExplorer displays a VP model in a tree structure.
 */
public class VPExplorer extends CommonNavigator implements ILinkableNavigator {

    /**
     * Meta data required for the VPExplorer. This meta data has to match the VPM set in the
     * VPExplorer.
     */
    public abstract static class VPExplorerMetaData {
        private final SPLevoProject splevoProject;

        /**
         * Constructs a new meta data object.
         * 
         * @param splevoProject
         *            A SPLevoProject that matches the VPM set in the VPExplorer.
         */
        public VPExplorerMetaData(SPLevoProject splevoProject) {
            this.splevoProject = splevoProject;
        }

        /**
         * @return The SPLevoProject that matches the VPM set in the VPExplorer.
         */
        public SPLevoProject getSPLevoProject() {
            return splevoProject;
        }

        /**
         * Switches back the VPM version to the given VPM.
         * 
         * @param vpmPath
         *            The path to the VPM as specified in the SPLevoProject.
         */
        public abstract void switchBackVPMVersion(String vpmPath);
    }

    /** Id to reference the view inside eclipse. */
    public static final String VIEW_ID = "org.splevo.ui.vpexplorer";

    private VPExplorerContent vpExplorerContent;

    private VPExplorerMetaData vpExplorerMetaData;

    private ExplorerMediator mediator;

    private boolean showGrouping = false;

    /**
     * Default explorer setting up the required dependencies.
     */
    public VPExplorer() {
        vpExplorerContent = new VPExplorerContent(this);
        mediator = Activator.EXPLORER_MEDIATOR;
        mediator.registerVPExplorer(this);
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.navigator.CommonNavigator#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);
        new CustomizableDescriptionHavingTreeViewerToolTip(getCommonViewer());
        IActionBars actionBars = getViewSite().getActionBars();
        IToolBarManager toolBar = actionBars.getToolBarManager();
        if (toolBar.getItems().length > 0) {
            toolBar.insertBefore(toolBar.getItems()[0].getId(), new ExpandAllAction(this));
            toolBar.insertBefore(toolBar.getItems()[0].getId(), new ExpandAllAction(this, MODE.VARIATIONPOINT));
            toolBar.insertBefore(toolBar.getItems()[0].getId(), new SelectVisibleAction(this));
            toolBar.insertBefore(toolBar.getItems()[0].getId(), new SwitchBackVPM(this));
        } else {
            toolBar.add(new ExpandAllAction(this));
            toolBar.add(new ExpandAllAction(this, MODE.VARIATIONPOINT));
            toolBar.add(new SelectVisibleAction(this));
            toolBar.add(new SwitchBackVPM(this));
        }
        getCommonViewer().addSelectionChangedListener(mediator);
    }

    /**
     * Toggles between "should display groupings" and "should not display groupings".
     */
    public void toggleShowGrouping() {
        showGrouping = !showGrouping;
        this.getCommonViewer().refresh();
    }

    /**
     * Returns if groupings should be shown.
     * 
     * @return true, if groupings should be shown.
     */
    public boolean getShowGrouping() {
        return showGrouping;
    }

    /**
     * Sets the VPM and corresponding meta data required for the VPExplorer.
     * 
     * @param vpm
     *            The new VPM.
     * @param metaData
     *            The meta data that corresponds to the VPM.
     */
    public void setVPM(VariationPointModel vpm, VPExplorerMetaData metaData) {
        vpExplorerContent.setVpm(vpm);
        this.vpExplorerMetaData = metaData;
        mediator.vpmAssigned();
    }

    /**
     * @return The meta data of the VPExplorer.
     */
    public VPExplorerMetaData getVPExplorerMetaData() {
        return vpExplorerMetaData;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.navigator.CommonNavigator#dispose()
     */
    @Override
    public void dispose() {
        mediator.deregisterVPExplorer();
        super.dispose();
    }

    @Override
    public void elementSelectedInOtherNavigator(Object selectedElement) {
        if (!this.isLinkingEnabled()) {
            return;
        }

        if (!(selectedElement instanceof VariationPoint)) {
            return;
        }

        ITreeContentProvider contentProvider = getNavigatorContentService().createCommonContentProvider();
        ILinkableNavigatorHelper.expandToObject(getCommonViewer(), contentProvider, (VariationPoint) selectedElement);
    }

}
