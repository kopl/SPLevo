package org.splevo.jamopp.refactoring.java.ifelse.optxor;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.splevo.jamopp.refactoring.java.ifelse.util.SemiAutomatedIfElseRefactoringUtil;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Specialize the {@link IfStaticConfigClassOPTXOR} class to reuse the functionality in
 * semi-automated refactorings.
 */
public class SemiAutomatedIfStaticConfigClassOPTXOR extends IfStaticConfigClassOPTXOR {

    /**
     * Constructs the refactoring.
     * 
     * @param validator
     *            The name of the license validator class.
     * @param variantToLicenseMap
     *            The mapping between variants and license constant names.
     */
    public SemiAutomatedIfStaticConfigClassOPTXOR(ConcreteClassifier validator, Map<String, String> variantToLicenseMap) {
        super(new SemiAutomatedIfElseRefactoringUtil(validator, variantToLicenseMap));
    }

    @Override
    protected List<Resource> refactorFullyAutomated(VariationPoint variationPoint,
            Map<String, Object> refactoringOptions) {
        return super.refactorFullyAutomated(variationPoint, refactoringOptions);
    }

    @Override
    protected boolean hasCorrectCharacteristics(VariationPoint variationPoint) {
        boolean correctBindingTime = (variationPoint.getBindingTime() == BindingTime.RUN_TIME);
        boolean correctVariabilityType = variationPoint.getVariabilityType() == VariabilityType.OPTXOR;
        boolean correctExtensibility = variationPoint.getExtensibility() == Extensible.NO;
        return correctBindingTime && correctVariabilityType && correctExtensibility;
    }

}
