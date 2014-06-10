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
package org.splevo.ui.refinementbrowser;

import java.util.Iterator;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IActionBars;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementType;

/**
 * A listener to print information about selected refinements on the IDEs action bar.
 */
public class RefinementActionBarListener implements ISelectionChangedListener {

    private IActionBars actionBars = null;

    /**
     * Constructor to bind the listener with the action bars to print to.
     *
     * @param actionBars
     *            The bars to set the message for.
     */
    public RefinementActionBarListener(IActionBars actionBars) {
        this.actionBars = actionBars;
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        ISelection selection = event.getSelection();
        if (selection instanceof IStructuredSelection) {
            int groupCount = 0;
            int mergeCount = 0;
            int subMergeCount = 0;
            int vpCount = 0;

            Iterator<?> iterator = ((IStructuredSelection) selection).iterator();
            while (iterator.hasNext()) {
                Object next = iterator.next();
                if (next instanceof Refinement) {
                    Refinement refinement = (Refinement) next;
                    RefinementType type = refinement.getType();
                    if (type == RefinementType.GROUPING) {
                        groupCount++;
                    } else if (type == RefinementType.MERGE) {
                        if (refinement.getParent() != null) {
                            subMergeCount++;
                        } else {
                            mergeCount++;
                        }
                    }
                    vpCount += refinement.getVariationPoints().size();
                }
            }

            String messageText = "Groupings: %s, SubMerges: %s | Merges: %s | VPs: %s";
            String message = String.format(messageText, groupCount, mergeCount, subMergeCount, vpCount);
            actionBars.getStatusLineManager().setMessage(message);
        }
    }

}
