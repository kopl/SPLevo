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
import org.splevo.vpm.refinement.RefinementReason;

/**
 * Listener to update the refinement info panel.
 */
public class RefinementInfoSelectionListener implements ISelectionChangedListener {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /** The composite to present the Refinement details in. */
    private RefinementDetailsView refinementDetailView;

    /**
     * Constructor to set the required references.
     *
     * @param refinementDetailsView
     *            The refinement area to present the details in.
     */
    public RefinementInfoSelectionListener(RefinementDetailsView refinementDetailsView) {
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

        String headline = "Refinement Infos";
        String subHeadlineReason = "Source: ";

        Refinement refinement = (Refinement) selectedElement;
        StringBuilder buffer = new StringBuilder();
        buffer.append(refinement.getSource());
        buffer.append(LINE_SEPARATOR);

        for (RefinementReason reason : refinement.getReasons()) {
            buffer.append(LINE_SEPARATOR);
            buffer.append(reason.getReason());
        }

        String text = buffer.toString();

        refinementDetailView.displayRefinementInfo(headline, subHeadlineReason, text);
    }
}
