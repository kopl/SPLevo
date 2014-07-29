package org.splevo.jamopp.refactoring.java.ifelse;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.EnumConstant;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.MembersFactory;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * The code base container must contain all enumerations from the variants. Therefore, this
 * refactoring merges the enumerations from all variants into the base, if there are no
 * interferences.
 */
public class IfElseStaticConfigClassEnumeration implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class: Enumeration";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassEnumeration";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public void refactor(VariationPoint vp) {
        MemberContainer vpLocation = (MemberContainer) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();

        Map<String, Enumeration> enumerationsToName = new HashMap<String, Enumeration>();
        Map<String, Set<String>> constantsToEnumName = new HashMap<String, Set<String>>();
        Map<String, Boolean> leadingToEnumName = new HashMap<String, Boolean>();

        for (Variant variant : vp.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Enumeration currentEnum = (Enumeration) ((JaMoPPSoftwareElement) se).getJamoppElement();
                if (!variant.getLeading()) {
                    currentEnum = EcoreUtil.copy(currentEnum);
                }

                if (!enumerationsToName.containsKey(currentEnum.getName()) || variant.getLeading()) {
                    enumerationsToName.put(currentEnum.getName(), currentEnum);
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
            Enumeration enumeration = enumerationsToName.get(enumName);
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
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        boolean correctLocation = jamoppElement instanceof MemberContainer;
        boolean allImplementingElementsAreEnums = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Enumeration.class);
        return correctLocation && allImplementingElementsAreEnums;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
