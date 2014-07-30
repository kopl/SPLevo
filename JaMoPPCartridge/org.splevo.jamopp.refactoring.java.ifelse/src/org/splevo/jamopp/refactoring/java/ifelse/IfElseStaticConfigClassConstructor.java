package org.splevo.jamopp.refactoring.java.ifelse;

import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.Constructor;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * The code base class must contain all constructors from the variants. Therefore, this refactoring
 * merges the constructors from all variants into the base.
 */
public class IfElseStaticConfigClassConstructor implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class: Constructor";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassConstructor";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public void refactor(VariationPoint variationPoint, Map<String, String> refactoringOptions) {
        Class vpLocation = (Class) ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        for (Variant variant : variationPoint.getVariants()) {
            if (variant.getLeading()) {
                continue;
            }
            for (SoftwareElement se : variant.getImplementingElements()) {
                Constructor constructor = (Constructor) ((JaMoPPSoftwareElement) se).getJamoppElement();
                if (!RefactoringUtil.hasConstructorWithEqualParameters(vpLocation, constructor)) {
                    vpLocation.getMembers().add(EcoreUtil.copy(constructor));
                }
            }
        }
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        boolean correctLocation = jamoppElement instanceof Class;
        boolean allImplementingElementsAreConstructors = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Constructor.class);

        return correctLocation && allImplementingElementsAreConstructors;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
