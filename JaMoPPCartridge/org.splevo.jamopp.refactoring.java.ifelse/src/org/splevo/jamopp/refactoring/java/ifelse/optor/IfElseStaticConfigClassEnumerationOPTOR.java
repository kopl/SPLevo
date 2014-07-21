package org.splevo.jamopp.refactoring.java.ifelse.optor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.EnumConstant;
import org.emftext.language.java.members.MemberContainer;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * The code base container must contain all enumerations from the variants. Therefore, this
 * refactoring merges the enumerations from all variants into the base, if there are no
 * interferences.
 */
public class IfElseStaticConfigClassEnumerationOPTOR implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class (OPTOR): Enumeration";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.xor.IfElseStaticConfigClassEnumerationOPTOR";

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
        Map<String, List<EnumConstant>> constantsToEnumName = new HashMap<String, List<EnumConstant>>();

        for (Variant variant : vp.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Enumeration currentEnum = (Enumeration) ((JaMoPPSoftwareElement) se).getJamoppElement();

                if (variant.getLeading()) {
                    vpLocation.getMembers().remove(currentEnum);
                }

                if (!enumerationsToName.containsKey(currentEnum.getName())) {
                    Enumeration enumCpy = EcoreUtil.copy(currentEnum);
                    enumCpy.getConstants().clear();
                    enumerationsToName.put(enumCpy.getName(), enumCpy);
                }

                if (!constantsToEnumName.containsKey(currentEnum.getName())) {
                    constantsToEnumName.put(currentEnum.getName(), new LinkedList<EnumConstant>());
                }
                List<EnumConstant> constantsList = constantsToEnumName.get(currentEnum.getName());

                for (EnumConstant constant : currentEnum.getConstants()) {
                    if (!hasConstantWithSameName(constantsList, constant.getName())) {
                        constantsList.add(EcoreUtil.copy(constant));
                    }
                }
            }
        }

        for (String enumName : enumerationsToName.keySet()) {
            Enumeration enumeration = enumerationsToName.get(enumName);
            List<EnumConstant> constants = constantsToEnumName.get(enumName);

            enumeration.getConstants().addAll(constants);

            vpLocation.getMembers().add(enumeration);
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
        boolean correctBindingTime = variationPoint.getBindingTime() == BindingTime.COMPILE_TIME;
        boolean correctVariabilityType = variationPoint.getVariabilityType() == VariabilityType.OPTOR;
        boolean correctExtensibility = variationPoint.getExtensibility() == Extensible.NO;
        boolean correctCharacteristics = correctBindingTime && correctVariabilityType && correctExtensibility;

        if (!correctCharacteristics) {
            return false;
        }

        boolean hasEnoughVariants = variationPoint.getVariants().size() > 0;
        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        boolean correctLocation = jamoppElement instanceof MemberContainer;
        boolean allImplementingElementsAreEnums = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Enumeration.class);
        return hasEnoughVariants && correctLocation && allImplementingElementsAreEnums;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
