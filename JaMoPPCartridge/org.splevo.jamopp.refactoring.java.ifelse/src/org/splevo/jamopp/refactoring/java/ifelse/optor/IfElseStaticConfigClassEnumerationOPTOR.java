package org.splevo.jamopp.refactoring.java.ifelse.optor;

import java.util.HashMap;
import java.util.Map;

import org.emftext.language.java.classifiers.ClassifiersFactory;
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
    private static final String REFACTORING_ID = 
            "org.splevo.jamopp.refactoring.java.ifelse.xor.IfElseStaticConfigClassEnumerationOPTOR";

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

        Enumeration enumeration = null;
        String enumerationName = null;
        Map<String, EnumConstant> constantToName = new HashMap<String, EnumConstant>();

        boolean hasLeadingVariant = false;

        for (Variant variant : vp.getVariants()) {
            if (variant.getLeading()) {
                hasLeadingVariant = true;
            }
            for (SoftwareElement se : variant.getImplementingElements()) {
                Enumeration currentEnum = (Enumeration) ((JaMoPPSoftwareElement) se).getJamoppElement();

                if (enumerationName == null) {
                    enumerationName = currentEnum.getName();
                }

                if (variant.getLeading()) {
                    enumeration = currentEnum;
                }

                for (EnumConstant constant : currentEnum.getConstants()) {
                    constantToName.put(constant.getName(), constant);
                }
            }
        }

        if (enumeration == null) {
            enumeration = ClassifiersFactory.eINSTANCE.createEnumeration();
            enumeration.setName(enumerationName);
        }

        enumeration.getConstants().addAll(constantToName.values());

        if (!hasLeadingVariant) {
            vpLocation.getMembers().add(enumeration);
        }
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
        boolean correctInput = hasEnoughVariants && correctLocation && allImplementingElementsAreEnums;

        if (!correctInput) {
            return false;
        }

        return !RefactoringUtil.hasConflictingEnums(variationPoint);
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
