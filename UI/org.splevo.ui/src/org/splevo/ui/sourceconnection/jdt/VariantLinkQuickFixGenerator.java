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
package org.splevo.ui.sourceconnection.jdt;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;
import org.splevo.vpm.variability.Variant;

import com.google.common.collect.Lists;

/**
 * Quick Fix generator for links to alternative variants' implementation
 */
public class VariantLinkQuickFixGenerator implements IMarkerResolutionGenerator {

    private Logger logger = Logger.getLogger(VariantLinkQuickFixGenerator.class);

    @Override
    public IMarkerResolution[] getResolutions(IMarker marker) {

        try {
            Object variantKey = marker.getAttribute(JavaEditorConnector.LOCATION_MARKER_VARIANT);

            if (variantKey instanceof String) {
                Variant variant = VariantRegistry.get((String) variantKey);
                if (variant != null) {
                    return buildAlternativeVariantLinks(variant);
                } else {
                    logger.warn(String.format("No variant registered for variant attribure: %s", variantKey));
                }
            }
        } catch (CoreException e) {
            logger.warn("Error accessing marker attribute", e);
        }

        return new IMarkerResolution[0];
    }

    /**
     * Build the link quick fixes to the alternatives of a variant.
     *
     * @param currentVariant
     *            The variant to get the alternatives for.
     * @return The array of quick links.
     */
    private IMarkerResolution[] buildAlternativeVariantLinks(Variant currentVariant) {

        List<IMarkerResolution> resolutions = Lists.newLinkedList();
        for (Variant alternativeVariant : currentVariant.getVariationPoint().getVariants()) {
            if (alternativeVariant != currentVariant) {
                resolutions.add(new VartiantLinkQuickFix(alternativeVariant));
            }
        }

        return resolutions.toArray(new IMarkerResolution[resolutions.size()]);
    }

}
