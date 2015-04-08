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
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.sourceconnection.handler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.splevo.ui.vpexplorer.providers.VPExplorerContentProvider.VPExplorerContentFileWithChildReference;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Base class for action handlers that shall open the sources of selected elements. The supported
 * elements are Variants, VariationPoints, Refinements and VPExplorerContentFileWithChildReference.
 * The contained variants are extracted from these elements and passed to a handler function.
 * Derived classes have to override a handler method for these variants.
 */
public abstract class OpenSourceHandlerBase extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchPart sendingPart = HandlerUtil.getActivePart(event);
        ISelection curSelection = HandlerUtil.getCurrentSelection(event);
        if (curSelection != null && curSelection instanceof IStructuredSelection) {
            IStructuredSelection selection = (IStructuredSelection) curSelection;

            Set<Variant> variants = new HashSet<Variant>();

            @SuppressWarnings("unchecked")
            List<Object> queue = new LinkedList<Object>(selection.toList());
            while (!queue.isEmpty()) {
                Object selectedItem = queue.remove(0);
                if (selectedItem instanceof Variant) {
                    variants.add((Variant) selectedItem);
                } else if (selectedItem instanceof VariationPoint) {
                    queue.addAll(((VariationPoint) selectedItem).getVariants());
                } else if (selectedItem instanceof Refinement) {
                    Refinement refinement = (Refinement) selectedItem;
                    queue.addAll(refinement.getSubRefinements());
                    queue.addAll(refinement.getVariationPoints());
                } else if (selectedItem instanceof VPExplorerContentFileWithChildReference) {
                    VPExplorerContentFileWithChildReference file = (VPExplorerContentFileWithChildReference) selectedItem;
                    queue.addAll(Arrays.asList(file.getVPMChildren()));
                }
            }

            handle(variants, sendingPart);
        }
        return null;
    }

    /**
     * Handle the set of variants which have been selected by the user indirectly.
     * 
     * @param variants
     *            The set of selected variants.
     * @param sendingPart
     *            The workbench part that issued the handling.
     */
    protected abstract void handle(Set<Variant> variants, IWorkbenchPart sendingPart);

}
