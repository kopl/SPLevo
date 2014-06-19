package org.splevo.jamopp.refactoring.java.ifelse.xor;

import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.members.Constructor;
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
 * <h1>Summary</h1> The code base class must contain all constructors from the variants. Therefore,
 * this refactoring merges the constructors from all variants into the base.
 */
public class IfElseStaticConfigClassConstructorXOR implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class (XOR): Constructor";
    private static final String REFACTORING_ID = 
            "org.splevo.jamopp.refactoring.java.ifelse.xor.IfElseStaticConfigClassConstructorXOR";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public void refactor(VariationPoint vp) {
        Class vpLocation = (Class) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        for (Variant variant : vp.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Constructor constructor = (Constructor) ((JaMoPPSoftwareElement) se).getJamoppElement();
                if (!RefactoringUtil.hasConstructorWithSameParameters(vpLocation, constructor)) {
                    vpLocation.getMembers().add(constructor);
                }
            }
        }
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        boolean correctBindingTime = variationPoint.getBindingTime() == BindingTime.COMPILE_TIME;
        boolean correctVariabilityType = variationPoint.getVariabilityType() == VariabilityType.XOR;
        boolean correctExtensibility = variationPoint.getExtensibility() == Extensible.NO;
        boolean correctCharacteristics = correctBindingTime && correctVariabilityType && correctExtensibility;

        boolean hasEnoughVariants = variationPoint.getVariants().size() > 0;
        boolean correctLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement() instanceof Class;
        boolean allImplementingElementsAreConstructors = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Constructor.class);
        boolean correctInput = hasEnoughVariants && correctLocation && allImplementingElementsAreConstructors;

        return correctCharacteristics && correctInput;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
