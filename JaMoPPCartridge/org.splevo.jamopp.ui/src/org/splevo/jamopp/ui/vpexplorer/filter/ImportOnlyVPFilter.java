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
package org.splevo.jamopp.ui.vpexplorer.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.imports.Import;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Viewer content filter for variation points with variants containing Import statements only.
 */
public class ImportOnlyVPFilter extends ViewerFilter {

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {

        if (!(element instanceof VariationPoint)) {
            return true;
        }

        VariationPoint variationPoint = (VariationPoint) element;

        if (!isInCompilationUnit(variationPoint)) {
            return false;
        }

        return onlyImports(variationPoint);
    }

    /**
     * Check if the variants of the variation point are about differing imports only.
     *
     * @param variationPoint
     *            The variation point to check the variants of.
     * @return True, if the variants are about imports only.
     */
    private boolean onlyImports(VariationPoint variationPoint) {
        for (Variant variant : variationPoint.getVariants()) {
            for (SoftwareElement implementingElement : variant.getImplementingElements()) {
                JaMoPPSoftwareElement jamoppElement = (JaMoPPSoftwareElement) implementingElement;
                if (!(jamoppElement.getJamoppElement() instanceof Import)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isInCompilationUnit(VariationPoint variationPoint) {

        if (!(variationPoint.getLocation() instanceof JaMoPPSoftwareElement)) {
            return false;
        }

        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        return (jamoppElement instanceof CompilationUnit);
    }

}
