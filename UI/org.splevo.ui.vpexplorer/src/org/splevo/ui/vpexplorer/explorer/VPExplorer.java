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
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.vpexplorer.explorer;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonNavigator;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.commons.tooltip.CustomizableDescriptionHavingTreeViewerToolTip;
import org.splevo.ui.vpexplorer.Activator;
import org.splevo.ui.vpexplorer.explorer.LoadVPMCompositeHandler.VPMLoader;
import org.splevo.ui.vpexplorer.explorer.actions.ExpandAllAction;
import org.splevo.ui.vpexplorer.explorer.actions.ExpandAllAction.MODE;
import org.splevo.ui.vpexplorer.explorer.actions.SelectVisibleAction;
import org.splevo.ui.vpexplorer.linking.ILinkableNavigator;
import org.splevo.ui.vpexplorer.util.VPMUIUtil;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Iterables;

/**
 * The VPExplorer displays a VP model in a tree structure.
 */
public class VPExplorer extends CommonNavigator implements ILinkableNavigator, VPMLoader {

    /**
     * Helper class for switching the displayed composite in the VPExplorer. Basically, there are
     * two composites to be displayed: The tree viewer for the VPM and the VPM loading page if no
     * VPM has been loaded yet. The helper can be used to switch to the tree viewer after loading a
     * VPM.
     */
    private class DisplayedCompositeSwitcher {
        private final StackLayout layout;
        private final Composite parentToRefresh;

        public DisplayedCompositeSwitcher(StackLayout layout, Composite parentToRefresh) {
            this.layout = layout;
            this.parentToRefresh = parentToRefresh;
        }

        public void switchToVPMTree() {
            this.layout.topControl = getCommonViewer().getControl();
            parentToRefresh.layout(true);
            loadVPMCompositeHandler.disable();
        }
    }

    /** Id to reference the view inside eclipse. */
    public static final String VIEW_ID = "org.splevo.ui.vpexplorer";

    private static final Logger LOGGER = Logger.getLogger(VPExplorer.class);

    private VPExplorerContent vpExplorerContent;

    private SPLevoProject splevoProject;

    private ExplorerMediator mediator;

    private final LoadVPMCompositeHandler loadVPMCompositeHandler = new LoadVPMCompositeHandler(this);

    private DisplayedCompositeSwitcher displayedCompositeSwitcher;

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
        Composite intermediateParent = new Composite(parent, SWT.NONE);
        intermediateParent.setLayoutData(new GridData(GridData.FILL_BOTH));
        final StackLayout layout = new StackLayout();
        intermediateParent.setLayout(layout);
        Composite vpmLoadingComposite = loadVPMCompositeHandler.createControl(intermediateParent);
        layout.topControl = vpmLoadingComposite;

        displayedCompositeSwitcher = new DisplayedCompositeSwitcher(layout, intermediateParent);

        super.createPartControl(intermediateParent);
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
        
        getCommonViewer().addTreeListener(new ITreeViewerListener() {
            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
            }

            @Override
            public void treeExpanded(TreeExpansionEvent event) {        
                
                if (this != null && event.getSource() == getCommonViewer()) {
                    expandTree(event.getElement());
                }
                
            }            
        });
        getCommonViewer().addOpenListener(new IOpenListener() {
            @Override
            public void open(OpenEvent event) {                 
                if (!(event.getSelection() instanceof IStructuredSelection)) {
                    return;
                }
                Object[] selection = ((IStructuredSelection) event.getSelection()).toArray();
                if (!(selection.length > 0)) {
                    return;
                }
                if (!getCommonViewer().getExpandedState(selection[0])) {
                    return;
                }
                if (this != null && event.getSource() == getCommonViewer()) {
                    expandTree(selection[0]);
                }
            }
        });
    }

    @Override
    public void loadVPM(SPLevoProject project, String vpmPath) {
        LOGGER.info("Loading VPM");
        VPMUIUtil.openVPExplorer(project, vpmPath);
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
     * Sets the VPM and corresponding SPLevo project required for the VPExplorer.
     * 
     * @param vpm
     *            The new VPM.
     * @param splevoProject
     *            The meta data that corresponds to the VPM.
     */
    public void setVPM(VariationPointModel vpm, SPLevoProject splevoProject) {
        vpExplorerContent.setVpm(vpm);
        this.splevoProject = splevoProject;
        mediator.vpmAssigned();
        displayedCompositeSwitcher.switchToVPMTree();
    }

    /**
     * @return The current SPLevo project.
     */
    public SPLevoProject getSPLevoProject() {
        return splevoProject;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.navigator.CommonNavigator#dispose()
     */
    @Override
    public void dispose() {
        mediator.deregisterVPExplorer();
        loadVPMCompositeHandler.disable();
        super.dispose();
    }

    @Override
    public void elementSelectedInOtherNavigator(Iterable<Object> selectedElement) {
        if (!this.isLinkingEnabled()) {
            return;
        }

        Iterable<VariationPoint> vps = Iterables.filter(selectedElement, VariationPoint.class);
        ITreeContentProvider contentProvider = getNavigatorContentService().createCommonContentProvider();
        ILinkableNavigatorHelper.expandToObject(getCommonViewer(), contentProvider, vps);
    }
    /**
     * Expanding an element and all children that are the only entry in their parent.
     * @param selectedElement the element to expand
     */
    private void expandTree(final Object selectedElement) {
        getCommonViewer().getControl().getShell().getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
                ITreeContentProvider contentProvider = getNavigatorContentService().createCommonContentProvider();
                Object[] elements = contentProvider.getChildren(selectedElement);   
                
                while (elements.length == 1) {
                    elements = contentProvider.getChildren(elements[0]);
                }            
                if (elements.length == 0) {
                    return;
                }
                getCommonViewer().refresh();
                getCommonViewer().expandToLevel(elements[0], 0);                
            }           
        });
    }
}
