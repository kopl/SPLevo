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
package org.splevo.ui.handler.vpm;

import java.io.IOException;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.splevo.vpm.VPMUtil;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.variabilityFactory;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

/**
 * UI command handler to merge a set of selected {@link VariationPoint}s.
 */
public class MergeVariationPointsHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        Shell activeShell = HandlerUtil.getActiveShell(event);
        boolean proceed = MessageDialog
                .openConfirm(
                        activeShell,
                        "Merge Variation Points",
                        "There is no technical check performed if the "
                        + "selected variation points can be merged or not.\n"
                        + "Proceed anyway?");

        if (!proceed) {
            return null;
        }

        ISelection curSelection = HandlerUtil.getCurrentSelection(event);
        if (curSelection != null && curSelection instanceof IStructuredSelection) {
            IStructuredSelection selection = (IStructuredSelection) curSelection;

            Set<VariationPoint> vpsToMerge = Sets.newLinkedHashSet();
            for (Object selectedItem : selection.toList()) {
                if (selectedItem instanceof VariationPoint) {
                    vpsToMerge.add((VariationPoint) selectedItem);
                }
            }

            mergeVPs(vpsToMerge);

            Set<Resource> resourcesToSave = Sets.newLinkedHashSet();
            for (VariationPoint vp : vpsToMerge) {
                resourcesToSave.add(vp.eResource());
            }
            updateResources(resourcesToSave);

        }

        return null;
    }

    /**
     * Merge a set of variation points by putting all software elements realizing the same variant
     * (identified by it's id) in the same variant element.
     *
     * @param vpsToMerge
     *            The {@link VariationPoint}s to merge.
     * @throws ExecutionException
     *             if failed to combine the groups.
     */
    private void mergeVPs(Set<VariationPoint> vpsToMerge) throws ExecutionException {
        VariationPoint survivingVP = vpsToMerge.iterator().next();
        Multimap<String, SoftwareElement> variantSoftwareElements = collectSoftwareElementsToMove(vpsToMerge,
                survivingVP);
        buildVariantsInSurvivingVP(survivingVP, variantSoftwareElements);

        for (VariationPoint vp : vpsToMerge) {
            if (vp == survivingVP) {
                continue;
            }
            EcoreUtil.remove(vp);
        }
    }

    private void buildVariantsInSurvivingVP(VariationPoint survivingVP,
            Multimap<String, SoftwareElement> variantSoftwareElements) {
        for (String variantID : variantSoftwareElements.keys()) {
            Variant variantForId = VPMUtil.getVariantFromVP(survivingVP, variantID);
            if (variantForId == null) {
                variantForId = variabilityFactory.eINSTANCE.createVariant();
            }

            variantForId.getImplementingElements().addAll(variantSoftwareElements.get(variantID));
        }
    }

    private Multimap<String, SoftwareElement> collectSoftwareElementsToMove(Set<VariationPoint> vpsToMerge,
            VariationPoint survivingVP) {
        Multimap<String, SoftwareElement> variantSoftwareElements = LinkedListMultimap.create();
        for (VariationPoint vp : vpsToMerge) {

            // skip the surviving vp to not modify the emf elements
            if (survivingVP == vp) {
                continue;
            }

            for (Variant variant : vp.getVariants()) {
                variantSoftwareElements.putAll(variant.getId(), variant.getImplementingElements());
            }
        }
        return variantSoftwareElements;
    }

    /**
     * Update the physical model resources.
     *
     * @param resourcesToSave
     *            The set of resources to save.
     * @throws ExecutionException
     *             Failed to save a resource.
     */
    private void updateResources(Set<Resource> resourcesToSave) throws ExecutionException {
        for (Resource resource : resourcesToSave) {

            // Variationpoints might have been
            // removed in the meantime so their resources are.
            if (resource == null) {
                continue;
            }

            try {
                resource.save(null);
            } catch (IOException e) {
                throw new ExecutionException("Failed to save modified resource", e);
            }
        }
    }

}
