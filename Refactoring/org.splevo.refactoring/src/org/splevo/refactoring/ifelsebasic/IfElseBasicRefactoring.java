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
package org.splevo.refactoring.ifelsebasic;

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.imports.Import;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vrm.VariabilityRealizationTechnique;

/**
 * Refactoring a variation point into a single code base aligned with the leading variant using if
 * else condition of the given java code base.
 */
public class IfElseBasicRefactoring implements VariabilityRefactoring {

    @Override
    public void refactor(VariationPoint vp) {

        SoftwareElement locationElement = vp.getLocation();
        if (locationElement instanceof JaMoPPSoftwareElement) {
            JaMoPPSoftwareElement jamoppLocationElement = (JaMoPPSoftwareElement) locationElement;

            Commentable jamoppElement = jamoppLocationElement.getJamoppElement();
            if (jamoppElement instanceof CompilationUnit) {
                CompilationUnit cu = (CompilationUnit) jamoppElement;

                EList<Variant> variants = vp.getVariants();
                for (Variant variant : variants) {
                    if (!variant.getLeading()) {

                        EList<SoftwareElement> implementingElements = variant.getImplementingElements();
                        for (SoftwareElement softwareElement : implementingElements) {
                            if (softwareElement instanceof JaMoPPSoftwareElement) {
                                JaMoPPSoftwareElement jamoppImplElement = (JaMoPPSoftwareElement) softwareElement;
                                Commentable jamoppElement2 = jamoppImplElement.getJamoppElement();
                                if (jamoppElement2 instanceof Import) {
                                    Import importElement = (Import) jamoppElement2;
                                    cu.getImports().add(importElement);
                                }

                            }
                        }

                    }
                }
            }

        }

    }

    @Override
    public VariabilityRealizationTechnique getSupportedVariabilityRealizationTechnique() {
        return new IfElseVariabilityRealizationTechnique();
    }

    @Override
    public String getLabel() {
        return "IfElse Basic Refactoring";
    }

    @Override
    public String getId() {
        return "org.splevo.refactoring.ifelsebasic";
    }

}
