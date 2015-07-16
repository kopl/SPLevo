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

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.Method;
import org.splevo.jamopp.refactoring.java.JaMoPPFullyAutomatedVariabilityRefactoring;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.vpm.software.JaMoPPJavaSoftwareElement;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * The code base container must contain all methods from the variants. Therefore, this refactoring
 * merges the methods from all variants into the base, if there are no interferences.
 */
public class IfStaticConfigClassMethod extends JaMoPPFullyAutomatedVariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF with Static Configuration Class: Method";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassMethod";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    protected List<Resource> refactorFullyAutomated(VariationPoint variationPoint, Map<String, Object> refactoringOptions) {
        MemberContainer vpLocation = (MemberContainer) ((JaMoPPJavaSoftwareElement) variationPoint.getLocation())
                .getJamoppElement();

        for (Variant variant : variationPoint.getVariants()) {
            if (variant.getLeading()) {
                continue;
            }
            for (SoftwareElement se : variant.getImplementingElements()) {
                Method currentMethod = (Method) ((JaMoPPJavaSoftwareElement) se).getJamoppElement();
                if (!RefactoringUtil.hasMethodWithEqualNameAndParameters(vpLocation, currentMethod)) {
                    vpLocation.getMembers().add(clone(currentMethod));
                }
            }
        }

        return Lists.newArrayList(vpLocation.eResource());
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        Commentable jamoppElement = ((JaMoPPJavaSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        boolean correctLocation = jamoppElement instanceof MemberContainer;
        boolean allImplementingElementsAreMethods = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Method.class);
        boolean correctInput = correctLocation && allImplementingElementsAreMethods;

        if (!correctInput) {
            return false;
        }

        return !RefactoringUtil.hasConflictingMethods(variationPoint);
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
