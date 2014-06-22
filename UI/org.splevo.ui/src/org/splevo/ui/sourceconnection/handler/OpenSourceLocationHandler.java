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
package org.splevo.ui.sourceconnection.handler;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.splevo.ui.sourceconnection.SourceEditorConnector;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * Action to open the implementing locations of a selected variant in the java editor.
 */
public class OpenSourceLocationHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        ISelection curSelection = HandlerUtil.getCurrentSelection(event);
        if (curSelection != null && curSelection instanceof IStructuredSelection) {
            IStructuredSelection selection = (IStructuredSelection) curSelection;

            List<String> filesResetBefore = Lists.newArrayList();

            for (Object selectedItem : selection.toList()) {
                if (selectedItem instanceof Variant) {
                    SourceEditorConnector.openVariant((Variant) selectedItem, filesResetBefore);
                } else if (selectedItem instanceof VariationPoint) {
                    SourceEditorConnector.openVariationPoint((VariationPoint) selectedItem, filesResetBefore);
                }
            }

        }
        return null;
    }
}
