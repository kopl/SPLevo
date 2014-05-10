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
package org.splevo.ui.vpexplorer.handler.vpmedit;

import java.io.IOException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;

import com.google.common.collect.Sets;

/**
 * Handler to delete the selected variation points.
 *
 * Concrete characteristic changing handlers must implement the
 * {@link DeleteVariationPointHandler#changeVariationPointCharacteristic(VariationPoint)} handler.
 *
 */
public class DeleteVariationPointHandler extends AbstractHandler {

    private static Logger logger = Logger.getLogger(DeleteVariationPointHandler.class);

    /**
     * Delete the selected variation point(s) and save the model.
     *
     * {@inheritDoc}
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        Set<Resource> resourceToSave = Sets.newLinkedHashSet();
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection != null && selection instanceof IStructuredSelection) {
            IStructuredSelection strucSelection = (IStructuredSelection) selection;
            if (strucSelection.toList().size() > 0) {
                Shell activeShell = HandlerUtil.getActiveShell(event);
                boolean proceed = MessageDialog.openConfirm(activeShell, "Delete Variation Point(s)",
                        "Are you sure to delete this variation point(s)?");

                if (!proceed) {
                    return null;
                }
            }
            for (Object element : strucSelection.toList()) {
                if (element instanceof VariationPoint) {
                    VariationPoint vp = (VariationPoint) element;
                    logger.info("MANUAL VP DELETE in " + vp.getLocation().getLabel());
                    Resource resource = vp.eResource();
                    VariationPointGroup group = vp.getGroup();
                    group.getVariationPoints().remove(vp);
                    resourceToSave.add(resource);
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

}
