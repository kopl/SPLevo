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
package org.splevo.ui.vpexplorer.handler.characteristics;

import java.io.IOException;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.views.properties.PropertySheet;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;

import com.google.common.collect.Sets;

/**
 * Abstract handler for changing variation point characteristics.
 *
 * Concrete characteristic changing handlers must implement the
 * {@link AbstractChangeCharacteristicHandler#changeVariationPointCharacteristic(VariationPoint)}
 * handler.
 *
 */
public abstract class AbstractChangeCharacteristicHandler extends AbstractHandler {

    
    /**
     * Change a characteristic of the selected variation points and save the resources of each
     * modified variation point.
     *
     * {@inheritDoc}
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        return execute(selection);
    }
        
    /**
     * Change a characteristic of the selected variation points and save the resources of each
     * modified variation point. 
     * @param selection the current selected items in the vpexplorer/feature outline
     * @return null
     * @throws ExecutionException if it fails to save the modified resource
     */
    public Object execute(ISelection selection) throws ExecutionException {
        Set<Resource> resourceToSave = Sets.newLinkedHashSet();
        
        if (selection != null && selection instanceof IStructuredSelection) {
            IStructuredSelection strucSelection = (IStructuredSelection) selection;
            for (Object element : strucSelection.toList()) {
                if (element instanceof VariationPoint) {
                    VariationPoint vp = (VariationPoint) element;
                    if (changeVariationPointCharacteristic(vp)) {
                        resourceToSave.add((vp).eResource());                        
                    }
                } else if (element instanceof VariationPointGroup) {
                    VariationPointGroup group = (VariationPointGroup) element;
                    for (VariationPoint vp : group.getVariationPoints()) {
                        if (changeVariationPointCharacteristic(vp)) {
                            resourceToSave.add(vp.eResource());  
                            
                        }
                    }
                }
            }
        }
        for (Resource resource : resourceToSave) {
            try {
                resource.save(null);
            } catch (IOException e) {
                throw new ExecutionException("Failed to save modified resource", e);
            }
        }
        refreshPropertySheet(selection);
        return null;
    }
    
    private void refreshPropertySheet(ISelection selection) {
        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IViewPart view = page.findView("org.eclipse.ui.views.PropertySheet");
        
        if (!(view instanceof PropertySheet)) {
            return;
        }
        PropertySheet pSheet = (PropertySheet) view;        
        
        pSheet.selectionChanged(page.getActivePart(), StructuredSelection.EMPTY);
        pSheet.selectionChanged(page.getActivePart(), selection);
    }
    
    /**
     * Change a variation points characteristic.
     *
     * @param variationPoint
     *            The variation point to modify.
     * @return True if variation point has been changed, False otherwise.
     */
    protected abstract boolean changeVariationPointCharacteristic(VariationPoint variationPoint);

}
