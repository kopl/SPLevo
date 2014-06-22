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
import org.eclipse.ui.handlers.HandlerUtil;
import org.splevo.vpm.variability.VariationPoint;

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

        Set<Resource> resourceToSave = Sets.newLinkedHashSet();
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection != null && selection instanceof IStructuredSelection) {
            IStructuredSelection strucSelection = (IStructuredSelection) selection;
            for (Object element : strucSelection.toList()) {
                if (element instanceof VariationPoint) {
                    boolean changed = changeVariationPointCharacteristic((VariationPoint) element);
                    if (changed) {
                        resourceToSave.add(((VariationPoint) element).eResource());
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

        return null;
    }

    /**
     * Change a variation points characteristic.
     *
     * @param variationPoint
     *            The variation point to modify.
     * @return Flag if change was performed.
     */
    protected abstract boolean changeVariationPointCharacteristic(VariationPoint variationPoint);

}
