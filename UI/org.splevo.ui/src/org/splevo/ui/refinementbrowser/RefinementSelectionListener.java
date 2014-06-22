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

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.splevo.vpm.refinement.Refinement;

/**
 * Listener if a refinement has been selected and open it in the refinement detail view.
 */
public class RefinementSelectionListener implements ISelectionChangedListener {

    /** The composite to present the Refinement details in. */
    private RefinementDetailsView refinementDetailView;

    /**
     * Constructor to set the required references.
     *
     * @param refinementDetailsView
     *            The refinement area to present the details in.
     */
    public RefinementSelectionListener(RefinementDetailsView refinementDetailsView) {
        this.refinementDetailView = refinementDetailsView;
    }

    /**
     * Process the event of a selected refinement. Opens the detailed information about the
     * refinement in the details area.
     *
     * @param event
     *            The selection change event to react on.
     */
    @Override
    public void selectionChanged(SelectionChangedEvent event) {

        Object selectedElement = ((StructuredSelection) event.getSelection()).getFirstElement();

        // break if the event is not about an refinement
        if (!(selectedElement instanceof Refinement)) {
            return;
        }

        Refinement refinement = (Refinement) selectedElement;

        refinementDetailView.showRefinement(refinement);
    }
}
