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

import java.util.Set;

import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeItem;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementReason;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Sets;

/**
 * Listener to highlight direct connected variation points for a
 * variation point selected in the refinement view.
 */
public class HighlightConnectedVPListener implements ISelectionChangedListener {

    @Override
    public void selectionChanged(SelectionChangedEvent event) {

        TreeViewer treeViewer = RefinementBrowserUtil.getTreeViewer(event);
        VariationPoint selectedVP = getSelectedVP(event);
        Refinement refinement = RefinementBrowserUtil.getRefinement(treeViewer);
        if (treeViewer == null || selectedVP == null || refinement == null) {
            return;
        }

        Set<VariationPoint> connectedVPs = getDirectConnectedVPs(selectedVP, refinement);

        highlightConnectedTreeNodes(treeViewer, selectedVP, connectedVPs);
    }

    private void highlightConnectedTreeNodes(TreeViewer treeViewer, VariationPoint selectedVP,
            Set<VariationPoint> connectedVPs) {

        Font fontBold = getFont(treeViewer, SWT.BOLD);
        Font fontBoldItalic = getFont(treeViewer, SWT.BOLD | SWT.ITALIC);
        Font fontNormal = getFont(treeViewer, SWT.NORMAL);

        for (TreeItem treeItem : treeViewer.getTree().getItems()) {
            if (selectedVP.equals(treeItem.getData())) {
                treeItem.setFont(fontBoldItalic);
            } else if (connectedVPs.contains(treeItem.getData())) {
                treeItem.setFont(fontBold);
            } else {
                treeItem.setFont(fontNormal);
            }
        }
    }

    private Set<VariationPoint> getDirectConnectedVPs(VariationPoint selectedVP, Refinement refinement) {
        Set<VariationPoint> connectedVPs = Sets.newLinkedHashSet();
        for (RefinementReason reason : refinement.getReasons()) {
            if (reason.getSource() == selectedVP) {
                connectedVPs.add(reason.getTarget());
            } else if (reason.getTarget() == selectedVP) {
                connectedVPs.add(reason.getSource());
            }
        }
        return connectedVPs;
    }

    private Font getFont(TreeViewer treeViewer, int style) {
        Control control = treeViewer.getTree();
        return getFont(control, style);
    }

    private Font getFont(Control control, int style) {
        FontDescriptor fontDescriptor = FontDescriptor.createFrom(control.getFont());
        Font styledFont = fontDescriptor.setStyle(style).createFont(Display.getCurrent());
        return styledFont;
    }

    private VariationPoint getSelectedVP(SelectionChangedEvent event) {
        Object selectedElement = ((StructuredSelection) event.getSelection()).getFirstElement();
        if (selectedElement instanceof VariationPoint) {
            return (VariationPoint) selectedElement;
        } else {
            return null;
        }
    }

}
