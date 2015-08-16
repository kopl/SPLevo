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

import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.splevo.ui.vpexplorer.Activator;
import org.splevo.ui.vpexplorer.featureoutline.FeatureOutlineView;
import org.splevo.ui.vpexplorer.linking.ILinkableNavigator.ILinkableNavigatorHelper;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * This mediator coordinates the interactions between a VP explorer and a VP grouping explorer.
 */
public class ExplorerMediator implements ISelectionChangedListener {

    private static Logger logger = Logger.getLogger(ExplorerMediator.class);

    private VPExplorer vpExplorer;
    private FeatureOutlineView vpGroupingExplorer;

    /**
     * Registers the VP explorer at this mediator.
     * 
     * @param vpExplorer
     *            the VP explorer to register.
     */
    public void registerVPExplorer(VPExplorer vpExplorer) {
        this.vpExplorer = vpExplorer;
    }

    /**
     * Deregisters the VP explorer. Will have no effect if no VP explorer was registered beforehand.
     */
    public void deregisterVPExplorer() {
        vpExplorer = null;
    }

    /**
     * Registers the VP grouping explorer at this mediator.
     * 
     * @param groupingExplorer
     *            the VP grouping explorer explorer to register.
     */
    public void registerVPGroupingExplorer(FeatureOutlineView groupingExplorer) {
        this.vpGroupingExplorer = groupingExplorer;
        
    }

    /**
     * Deregisters the VP grouping explorer. Will have no effect if no VP grouping explorer was
     * registered beforehand.
     */
    public void deregisterVPGRoupingExplorer() {
        vpGroupingExplorer = null;
    }

    /**
     * Sends the VPM of the VP explorer to the VP grouping explorer.
     */
    public void vpmAssigned() {
        if (vpExplorer != null) {
            if (vpGroupingExplorer != null) {
                vpGroupingExplorer.setVPM(vpExplorer.getVpExplorerContent().getVpm());
            } else {
                openVPGroupingExplorer(vpExplorer.getVpExplorerContent().getVpm());
            }
        } else {
            logger.warn("Tried to distribute VP Explorer Content without registered VP Explorer");
        }
    }

    private void openVPGroupingExplorer(final VariationPointModel vpm) {
        Display.getDefault().syncExec(new Runnable() {
            @Override
            public void run() {
                try {
                    IWorkbenchWindow activeWorkbench = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                    IViewPart viewPart = activeWorkbench.getActivePage().showView(FeatureOutlineView.VIEW_ID);
                    final FeatureOutlineView explorer = (FeatureOutlineView) viewPart;
                    if (vpm != null) {
                        explorer.setVPM(vpm);
                    }
                } catch (PartInitException e) {
                    logger.error("Could not create the VP grouping explorer view", e);
                }
            }
        });
    }
    
    public VariationPointModel getCurrentVPM() {
        if (vpGroupingExplorer != null && vpGroupingExplorer.getVpExplorerContent() != null) {
            return vpGroupingExplorer.getVpExplorerContent().getVpm();
        }
        if (vpExplorer != null && vpExplorer.getVpExplorerContent() != null) {
            return vpExplorer.getVpExplorerContent().getVpm();
        }
        return null;
    }
    
    private Iterable<Object> preprocessSelectedObjects(Iterable<Object> selectedObjects) {
        final VariationPointModel currentVPM = getCurrentVPM();
        if (currentVPM == null) {
            return selectedObjects;
        }
        
        Iterable<VariationPoint> selectedVPs = Iterables.filter(selectedObjects, VariationPoint.class);
        Iterable<VariationPoint> mappedVPs = Iterables.transform(selectedVPs, new Function<VariationPoint, VariationPoint>() {
            private VariationPointModel getParentVPM(VariationPoint vpm) {
                EObject parent = vpm.eContainer();
                while (parent != null && !(parent instanceof VariationPointModel)) {
                    parent = parent.eContainer();
                }
                return (VariationPointModel) parent;
            }
            @Override
            public VariationPoint apply(VariationPoint arg0) {
                if (getParentVPM(arg0) == currentVPM) {
                    return arg0;
                }
                Optional<VariationPoint> vp = ILinkableNavigatorHelper.find(arg0, currentVPM);
                if (vp.isPresent()) {
                    return vp.get();
                }
                return null;
            } });
        Iterable<VariationPoint> selectedAndAvailableVPs = Iterables.filter(mappedVPs, Predicates.notNull());
        
        Set<Object> noVPObjects = Sets.difference(Sets.newHashSet(selectedObjects), Sets.newHashSet(selectedVPs));
        return Iterables.concat(noVPObjects, selectedAndAvailableVPs);
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        if (!(event.getSelection() instanceof IStructuredSelection)) {
            return;
        }
                
        IStructuredSelection selection = (IStructuredSelection) event.getSelection();
        @SuppressWarnings("unchecked")
        Iterable<Object> selectedObjects = preprocessSelectedObjects(Lists.newArrayList(selection.iterator()));
        
        if (vpExplorer != null && event.getSource() != vpExplorer.getCommonViewer()) {
            vpExplorer.elementSelectedInOtherNavigator(selectedObjects);
        }
        
        if (vpGroupingExplorer != null && event.getSource() != vpGroupingExplorer.getCommonViewer()) {
            vpGroupingExplorer.elementSelectedInOtherNavigator(selectedObjects);
        }
    }

    /**
     * @return The singleton instance of the mediator or null if no such instance has been initialized yet.
     */
    public static ExplorerMediator getInstance() {
        return Activator.EXPLORER_MEDIATOR;
    }
}
