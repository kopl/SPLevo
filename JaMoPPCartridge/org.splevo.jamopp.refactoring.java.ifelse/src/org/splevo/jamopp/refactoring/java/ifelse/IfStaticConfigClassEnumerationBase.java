/*******************************************************************************
 * Copyright (c) 2015
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic - initial API and implementation and initial documentation
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.EnumConstant;
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
 * 
 * @param <T>
 *            The type of the container that contains the enumeration.
 */
public abstract class IfStaticConfigClassEnumerationBase<T extends Commentable> extends
        JaMoPPFullyAutomatedVariabilityRefactoring {

    private final Class<T> supportedParentType;
    private final String refactoringName;
    
    /**
     * Constructor for the base class that initializes the abstract refactoring.
     * @param supportedParentType The supported container type of the parent container.
     * @param refactoringName The name of the refactoring.
     */
    protected IfStaticConfigClassEnumerationBase(Class<T> supportedParentType, String refactoringName) {
        this.supportedParentType = supportedParentType;
        this.refactoringName = refactoringName;
    }
    
    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(refactoringName);
        variabilityMechanism.setRefactoringID(getId());
        return variabilityMechanism;
    }

    @Override
    protected List<Resource> refactorFullyAutomated(VariationPoint variationPoint, Map<String, Object> refactoringOptions) {
        Map<String, List<Enumeration>> enumerationsToName = Maps.newHashMap();
        Map<String, Boolean> leadingToEnumName = new HashMap<String, Boolean>();

        for (Variant variant : variationPoint.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Enumeration currentEnum = (Enumeration) ((JaMoPPJavaSoftwareElement) se).getJamoppElement();

                final String currentEnumName = currentEnum.getName();
                if (!enumerationsToName.containsKey(currentEnumName)) {
                    enumerationsToName.put(currentEnumName, new ArrayList<Enumeration>());
                }
                if (variant.getLeading()) {
                    enumerationsToName.get(currentEnumName).add(0, currentEnum);
                } else {
                    enumerationsToName.get(currentEnumName).add(currentEnum);
                }
                
                final boolean isEnumAlreadyInLeadingCopy = variant.getLeading()
                        || leadingToEnumName.containsKey(currentEnumName) && leadingToEnumName.get(currentEnumName);
                leadingToEnumName.put(currentEnum.getName(), isEnumAlreadyInLeadingCopy);
            }
        }

        @SuppressWarnings("unchecked") // we checked the type in canBeAppliedTo()
        final T vpLocation = (T) ((JaMoPPJavaSoftwareElement) variationPoint.getLocation())
                .getJamoppElement();

        for (String enumName : enumerationsToName.keySet()) {
            Enumeration enumeration = enumerationsToName.get(enumName).get(0);
            registerReplacement(enumerationsToName.get(enumName), enumeration);

            for (Enumeration currentEnumeration : enumerationsToName.get(enumName)) {
                for (EnumConstant constant : currentEnumeration.getConstants()) {
                    if (!hasConstantWithSameName(enumeration.getConstants(), constant.getName())) {
                        enumeration.getConstants().add(clone(constant));
                    }
                }
            }

            if (!leadingToEnumName.get(enumName)) {
                addToVPLocation(vpLocation, clone(enumeration));
            }
        }

        return Lists.newArrayList(vpLocation.eResource());
    }
    
    /**
     * Adds the given enumeration to the given location.
     * 
     * @param location
     *            The location to add the enumeration to.
     * @param enumeration
     *            The enumeration to be added.
     */
    protected abstract void addToVPLocation(T location, Enumeration enumeration);

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

        boolean correctLocation = supportedParentType.isAssignableFrom(jamoppElement.getClass());
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

    
}
