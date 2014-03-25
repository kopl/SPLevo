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
package org.splevo.jamopp.refactoring.refactory.ifelse;

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.imports.Import;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vrm.impl.VariabilityRealizationTechniqueImpl;

/**
 * A variability realized using if else technique.
 */
public class IfElseVariabilityRealizationTechnique extends VariabilityRealizationTechniqueImpl {

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {

        Boolean importDecision = checkForImportVariationPoint(variationPoint);
        if (importDecision != null) {
            return importDecision;
        }

        return false;
    }

    /**
     * Check of the variation point describes changed imports of a compilation unit.
     *
     * @param variationPoint
     *            The VP to check the location and implementing variants for.
     * @return True if it can be refactored, false if it definitely can not, Null if no decision
     *         possible.
     */
    private Boolean checkForImportVariationPoint(VariationPoint variationPoint) {

        SoftwareElement locationElement = variationPoint.getLocation();
        if (!(locationElement instanceof JaMoPPSoftwareElement)) {
            return null;
        }

        JaMoPPSoftwareElement jamoppLocationElement = (JaMoPPSoftwareElement) locationElement;
        Commentable commentable = jamoppLocationElement.getJamoppElement();
        if (!(commentable instanceof CompilationUnit)) {
            return null;
        }

        EList<Variant> variants = variationPoint.getVariants();
        for (Variant variant : variants) {
            EList<SoftwareElement> implementingElements = variant.getImplementingElements();
            for (SoftwareElement implementatingElement : implementingElements) {

                if (!(implementatingElement instanceof JaMoPPSoftwareElement)) {
                    return Boolean.TRUE;
                }

                JaMoPPSoftwareElement jamoppImplementingElement = (JaMoPPSoftwareElement) implementatingElement;
                Commentable jamoppImplElement = jamoppImplementingElement.getJamoppElement();
                if (!(jamoppImplElement instanceof Import)) {
                    return Boolean.FALSE;
                }
            }

        }

        return Boolean.TRUE;
    }
}
