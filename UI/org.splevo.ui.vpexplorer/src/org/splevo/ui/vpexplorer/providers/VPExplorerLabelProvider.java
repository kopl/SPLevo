/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Busch
 *******************************************************************************/

package org.splevo.ui.vpexplorer.providers;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.emftext.language.java.containers.CompilationUnit;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.ui.vpexplorer.treeitems.CULocationNameTreeItem;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.software.SourceLocation;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;

/**
 * This LabelProvider provides Labels for elements of a VPM.
 */
public class VPExplorerLabelProvider extends LabelProvider {

    @Override
    public Image getImage(Object element) {
        return null;
    }

    @Override
    public String getText(Object element) {

        if (element instanceof VariationPointGroup) {
            return "Variation Point Group: " + ((VariationPointGroup) element).getGroupId();
        } else if (element instanceof VariationPoint) {
            return buildVariationPointLabel((VariationPoint) element);
        } else if (element instanceof Variant) {
            return "Variant: " + ((Variant) element).getVariantId();
        } else if (element instanceof JaMoPPSoftwareElement) {
            return ((JaMoPPSoftwareElement) element).getJamoppElement().getContainingCompilationUnit().getName();
        } else if (element instanceof SoftwareElement) {
            return ((SoftwareElement) element).getLabel();
        } else if (element instanceof SourceLocation) {
            // return ((SourceLocation) element).getFilePath();
            return "Source Location";
        } else if (element instanceof CompilationUnit) {
            CompilationUnit cu = (CompilationUnit) element;
            return cu.getName();
        } else if (element instanceof CULocationNameTreeItem) {
            return ((CULocationNameTreeItem) element).getCULocationName();
        }
        return null;
    }

    /**
     * Builds the variation point label.
     *
     * @param variationPoint
     *            the element
     * @return the string
     */
    private String buildVariationPointLabel(final VariationPoint variationPoint) {
        final SoftwareElement softwareElement = variationPoint.getLocation();
        return String.format("VariationPoint in %s", softwareElement.getLabel());
    }

}
