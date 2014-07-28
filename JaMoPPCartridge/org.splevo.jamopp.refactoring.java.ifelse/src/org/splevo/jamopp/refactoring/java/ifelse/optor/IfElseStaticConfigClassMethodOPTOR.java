package org.splevo.jamopp.refactoring.java.ifelse.optor;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.Method;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * The code base container must contain all methods from the variants. Therefore, this refactoring
 * merges the methods from all variants into the base, if there are no interferences.
 */
public class IfElseStaticConfigClassMethodOPTOR implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class (OPTOR): Method";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassMethodOPTOR";

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

        for (Variant variant : vp.getVariants()) {
            if (variant.getLeading()) {
                continue;
            }
            for (SoftwareElement se : variant.getImplementingElements()) {
                Method currentMethod = (Method) ((JaMoPPSoftwareElement) se).getJamoppElement();
                if (!RefactoringUtil.hasMethodWithEqualNameAndParameters(vpLocation, currentMethod)) {
                    vpLocation.getMembers().add(EcoreUtil.copy(currentMethod));
                }
            }
        }
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();

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
