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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.EnumConstant;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.MembersFactory;
import org.splevo.jamopp.refactoring.java.JaMoPPFullyAutomatedVariabilityRefactoring;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.vpm.software.JaMoPPJavaSoftwareElement;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * The code base container must contain all enumerations from the variants. Therefore, this
 * refactoring merges the enumerations from all variants into the base, if there are no
 * interferences.
 */
public class IfStaticConfigClassEnumerationInMemberContainer extends JaMoPPFullyAutomatedVariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF with Static Configuration Class: Enumeration in MemberContainer";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassEnumerationInMemberContainer";

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

        Map<String, List<Enumeration>> enumerationsToName = Maps.newHashMap();
        Map<String, Set<String>> constantsToEnumName = new HashMap<String, Set<String>>();
        Map<String, Boolean> leadingToEnumName = new HashMap<String, Boolean>();

        for (Variant variant : variationPoint.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Enumeration currentEnum = (Enumeration) ((JaMoPPJavaSoftwareElement) se).getJamoppElement();
//                if (!variant.getLeading()) {
//                    currentEnum = clone(currentEnum);
//                }

                if (!enumerationsToName.containsKey(currentEnum.getName())) {
                    enumerationsToName.put(currentEnum.getName(), new ArrayList<Enumeration>());
                }
                if (variant.getLeading()) {
                    enumerationsToName.get(currentEnum.getName()).add(0, currentEnum);
                } else {
                    enumerationsToName.get(currentEnum.getName()).add(currentEnum);
                }
                leadingToEnumName.put(currentEnum.getName(), variant.getLeading());

                if (!constantsToEnumName.containsKey(currentEnum.getName())) {
                    constantsToEnumName.put(currentEnum.getName(), new HashSet<String>());
                }
                Set<String> constantsList = constantsToEnumName.get(currentEnum.getName());
                for (EnumConstant constant : currentEnum.getConstants()) {
                    constantsList.add(constant.getName());
                }
            }
        }

        for (String enumName : enumerationsToName.keySet()) {
            Enumeration enumeration = enumerationsToName.get(enumName).get(0);
            registerReplacement(enumerationsToName.get(enumName), enumeration);
            Set<String> constants = constantsToEnumName.get(enumName);

            for (String constName : constants) {
                if (!hasConstantWithSameName(enumeration.getConstants(), constName)) {
                    EnumConstant enumConstant = MembersFactory.eINSTANCE.createEnumConstant();
                    enumConstant.setName(constName);
                    enumeration.getConstants().add(enumConstant);
                }
            }

            if (!leadingToEnumName.get(enumName)) {
                vpLocation.getMembers().add(enumeration);
            }
        }

        return Lists.newArrayList(vpLocation.eResource());
    }

    private boolean hasConstantWithSameName(List<EnumConstant> constantsList, String name) {
        for (EnumConstant enumConstant : constantsList) {
            if (enumConstant.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Diagnostic canBeAppliedTo(VariationPoint variationPoint) {
        Commentable jamoppElement = ((JaMoPPJavaSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        boolean correctLocation = jamoppElement instanceof MemberContainer;
        boolean allImplementingElementsAreEnums = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Enumeration.class);        
        boolean correctInput = correctLocation && allImplementingElementsAreEnums;

        String error = "If with Static Configuration Class Enumeration: ";
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
