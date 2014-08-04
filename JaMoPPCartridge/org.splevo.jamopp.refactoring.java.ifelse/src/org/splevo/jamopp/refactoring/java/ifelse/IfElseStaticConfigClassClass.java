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
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.MemberContainer;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * The code base container must contain all classes from the variants. Therefore, this refactoring
 * merges the classes from all variants into the base, if there are no interferences.
 */
public class IfElseStaticConfigClassClass implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class: Class";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassClass";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public ResourceSet refactor(VariationPoint variationPoint, Map<String, String> refactoringOptions) {
        MemberContainer vpLocation = (MemberContainer) ((JaMoPPSoftwareElement) variationPoint.getLocation())
                .getJamoppElement();

        for (Variant variant : variationPoint.getVariants()) {
            if (variant.getLeading()) {
                continue;
            }
            for (SoftwareElement se : variant.getImplementingElements()) {
                Class c = (Class) ((JaMoPPSoftwareElement) se).getJamoppElement();

                if (!RefactoringUtil.containsClassInterfaceOrEnumWithName(vpLocation, c.getName())) {
                    vpLocation.getMembers().add(EcoreUtil.copy(c));
                }
            }
        }

        return RefactoringUtil.wrapInNewResourceSet(vpLocation);
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        boolean correctLocation = jamoppElement instanceof MemberContainer;
        boolean allImplementingElementsAreClasses = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Class.class);

        boolean correctInput = correctLocation && allImplementingElementsAreClasses;

        if (!correctInput) {
            return false;
        }

        return !RefactoringUtil.hasMembersWithConflictingNames(variationPoint);
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
