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

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.emftext.language.java.commons.Commentable;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Filter which checks the type of the variation point location as well of the implementing
 * elements.
 *
 * If all variation points of a parent element are filtered, the parent element is filtered as well.
 *
 * A concrete filter must provide the classes to search for.
 */
public abstract class AbstractJaMoPPVPFilter extends ViewerFilter {

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {

        if (element instanceof Variant || element instanceof Commentable) {
            return true;
        } else if (element instanceof VariationPoint) {
            return select((VariationPoint) element);

        } else {
            StructuredViewer sviewer = (StructuredViewer) viewer;
            ITreeContentProvider provider = (ITreeContentProvider) sviewer.getContentProvider();
            for (Object child : provider.getChildren(element)) {
                if (select(viewer, element, child)) {
                    return true;
                }
            }
            return false;
        }
    }

    private boolean select(VariationPoint variationPoint) {

        if (!isValidLocation(variationPoint)) {
            return false;
        }

        return validImplementationElements(variationPoint);
    }

    /**
     * Check if the variants of the variation point are about differing imports only.
     *
     * If at least one implementing element
     * <ul>
     * <li>does not conform to the include type or</li>
     * <li>conforms to the exclude type</li>
     * </ul>
     * The variant is not of the expected implementation type.
     *
     * @param variationPoint
     *            The variation point to check the variants of.
     * @return True, if the variants are about imports only.
     */
    private boolean validImplementationElements(VariationPoint variationPoint) {

        Class<?> includeClass = getImplementingElementClass();
        Class<?> excludeClass = getExcludeImplementingElementClass();

        for (Variant variant : variationPoint.getVariants()) {
            for (SoftwareElement implementingElement : variant.getImplementingElements()) {
                JaMoPPSoftwareElement jamoppElement = (JaMoPPSoftwareElement) implementingElement;
                if (!includeClass.isAssignableFrom(jamoppElement.getJamoppElement().getClass())) {
                    return false;
                }
                if (excludeClass != null && excludeClass.isAssignableFrom(jamoppElement.getJamoppElement().getClass())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidLocation(VariationPoint variationPoint) {

        if (!(variationPoint.getLocation() instanceof JaMoPPSoftwareElement)) {
            return false;
        }

        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        Class<?> variationPointClass = getExpectedLocationClass();
        if (variationPointClass != null) {
            return variationPointClass.isAssignableFrom(jamoppElement.getClass());
        }

        return true;
    }

    /**
     * Get the expected class for the variation point location. If null, no check will be performed.
     *
     * @return The class expected for the variation point.
     */
    protected abstract Class<?> getExpectedLocationClass();

    /**
     * Get the expected class for the variant implementing element.
     *
     * @return The class expected for the elements.
     */
    protected abstract Class<?> getImplementingElementClass();

    /**
     * Get the class to exclude from allowed implementing elements.
     *
     * Subclasses can override this method, if variation points with variants being implemented by
     * one of these types should be excluded.
     *
     * @return The class to ignore. Nothing to ignore in case of null (default).
     */
    protected Class<?> getExcludeImplementingElementClass() {
        return null;
    }

}