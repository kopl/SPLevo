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

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.MemberContainer;
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
 * The code base container must contain all classes from the variants. Therefore, this refactoring
 * merges the classes from all variants into the base, if there are no interferences.
 */
public class IfStaticConfigClassClassInMemberContainer extends JaMoPPFullyAutomatedVariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF with Static Configuration Class: Class in MemberContainer";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassClassInMemberContainer";

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
                Class c = (Class) ((JaMoPPJavaSoftwareElement) se).getJamoppElement();

                if (!RefactoringUtil.containsClassInterfaceOrEnumWithName(vpLocation, c.getName())) {
                    vpLocation.getMembers().add(clone(c));
                }
            }
        }

        return Lists.newArrayList(vpLocation.eResource());
    }

    @Override
    public Diagnostic canBeAppliedTo(VariationPoint variationPoint) {
        Commentable jamoppElement = ((JaMoPPJavaSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        boolean correctLocation = jamoppElement instanceof MemberContainer;
        boolean allImplementingElementsAreClasses = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Class.class);
        
        String error = "If with Static Configuration Class Member Container: ";
        boolean correctInput = correctLocation && allImplementingElementsAreClasses;

        if (!correctInput) {
            return new BasicDiagnostic(Diagnostic.ERROR, null, 0, error + "Wrong Input", null);
        }

        if (RefactoringUtil.hasMembersWithConflictingNames(variationPoint)) {
            return new BasicDiagnostic(Diagnostic.ERROR, null, 0, error + "Has Members with Conflicting Names", null);
        }
        return new BasicDiagnostic(Diagnostic.OK, null, 0, "OK", null);
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
