/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic - initial API and implementation and initial documentation
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse;

import java.util.Map;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.imports.Import;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * To allow for a complete single code base, all dependencies must be reflected and thus the
 * refactoring must carry over all imports.
 */
public class IfElseStaticConfigClassImport implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class: Import";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassImport";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public ResourceSet refactor(VariationPoint variationPoint, Map<String, String> refactoringOptions) {
        CompilationUnit vpLocation = (CompilationUnit) ((JaMoPPSoftwareElement) variationPoint.getLocation())
                .getJamoppElement();
        for (Variant variant : variationPoint.getVariants()) {
            if (variant.getLeading()) {
                continue;
            }
            for (SoftwareElement se : variant.getImplementingElements()) {
                Import i = (Import) ((JaMoPPSoftwareElement) se).getJamoppElement();
                if (!RefactoringUtil.containsImport(vpLocation, i)) {
                    vpLocation.getImports().add(EcoreUtil.copy(i));
                }
            }
        }

        return RefactoringUtil.wrapInNewResourceSet(vpLocation);
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        boolean correctLocation = jamoppElement instanceof CompilationUnit;
        boolean allImplementingElementsAreImports = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Import.class);

        return correctLocation && allImplementingElementsAreImports;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
