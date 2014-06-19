package org.splevo.jamopp.refactoring.java.ifelse.xor;

import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.imports.Import;
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
 * <h1>Summary</h1> To allow for a complete single code base, all dependencies must be reflected and
 * thus the refactoring must carry over all imports.
 */
public class IfElseStaticConfigClassImportXOR implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class (XOR): Import";
    private static final String REFACTORING_ID = 
            "org.splevo.jamopp.refactoring.java.ifelse.xor.IfElseStaticConfigClassImportXOR";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public void refactor(VariationPoint vp) {
        CompilationUnit vpLocation = (CompilationUnit) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        for (Variant variant : vp.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Import i = (Import) ((JaMoPPSoftwareElement) se).getJamoppElement();
                if (!vpLocation.getImports().contains(i)) {
                    vpLocation.getImports().add(i);
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
        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        boolean correctLocation = jamoppElement instanceof CompilationUnit;
        boolean allImplementingElementsAreImports = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Import.class);
        boolean correctInput = hasEnoughVariants && correctLocation && allImplementingElementsAreImports;

        return correctCharacteristics && correctInput;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
