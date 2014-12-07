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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Sets;

/**
 * UI command handler to merge a set of selected {@link VariationPointGroup}s.
 */
public class CombineGroupsHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        ISelection curSelection = HandlerUtil.getCurrentSelection(event);
        if (curSelection != null && curSelection instanceof IStructuredSelection) {
            IStructuredSelection selection = (IStructuredSelection) curSelection;

            Set<VariationPointGroup> groupsToMerge = Sets.newLinkedHashSet();
            for (Object selectedItem : selection.toList()) {
                if (selectedItem instanceof VariationPointGroup) {
                    groupsToMerge.add((VariationPointGroup) selectedItem);
                }
            }

            combineGroups(groupsToMerge);

        }

        return null;
    }

    /**
     * Combine a set of variation point groups and update the enclosing resources.
     *
     * @param groupsToMerge
     *            The VPGs to merge.
     * @throws ExecutionException
     *             if failed to combine the groups.
     */
    private void combineGroups(Set<VariationPointGroup> groupsToMerge) throws ExecutionException {
        Set<Resource> resourcesToSave = Sets.newLinkedHashSet();
        VariationPointGroup survivingGroup = groupsToMerge.iterator().next();
        VariationPointModel vpm = survivingGroup.getModel();
        for (VariationPointGroup vpg : groupsToMerge) {
            resourcesToSave.add(vpg.eResource());
            combineGroup(survivingGroup, vpg, vpm);
        }

        updateResources(resourcesToSave);
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
            try {
                resource.save(null);
            } catch (IOException e) {
                throw new ExecutionException("Failed to save modified resource", e);
            }
        }
    }

    /**
     * Consolidate a group into a surviving one.
     *
     * @param survivingGroup
     *            The surviving group.
     * @param vpg
     *            The group to consolidate.
     * @param vpm
     *            The enclosing model to update.
     */
    private void combineGroup(VariationPointGroup survivingGroup, VariationPointGroup vpg, VariationPointModel vpm) {
        if (!vpg.equals(survivingGroup)) {
            survivingGroup.getVariationPoints().addAll(vpg.getVariationPoints());
            vpm.getVariationPointGroups().remove(vpg);
        }
    }

}
