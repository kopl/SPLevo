package org.splevo.jamopp.refactoring.java.ifelse.optxor;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.splevo.jamopp.refactoring.java.ifelse.util.SemiAutomatedIfElseRefactoringUtil;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Specialize the {@link IfStaticConfigClassOPTXOR} class to reuse the functionality
 * and make a few adjustments.
 */
public class SemiAutomatedIfStaticConfigClassOPTXOR extends IfStaticConfigClassOPTXOR {

	public SemiAutomatedIfStaticConfigClassOPTXOR(String validatorName, Map<String, String> variantToLicenseMap) {
    	super(new SemiAutomatedIfElseRefactoringUtil(validatorName, variantToLicenseMap));
    }
	
	@Override
    protected List<Resource> refactorFullyAutomated(VariationPoint variationPoint, Map<String, Object> refactoringOptions) {
		return super.refactorFullyAutomated(variationPoint, refactoringOptions);
	}
	
	 @Override
	 public boolean canBeAppliedTo(VariationPoint variationPoint) {
		 boolean correctBindingTime = (variationPoint.getBindingTime() == BindingTime.RUN_TIME);
		 boolean correctVariabilityType = variationPoint.getVariabilityType() == VariabilityType.OPTXOR;
		 boolean correctExtensibility = variationPoint.getExtensibility() == Extensible.NO;
		 boolean correctCharacteristics = correctBindingTime && correctVariabilityType && correctExtensibility;
		 
		 if (!correctCharacteristics) {
			 return false;
		 }

		 for (VariabilityRefactoring refactoring : this.availableRefactorings) {
			 if (refactoring.canBeAppliedTo(variationPoint)) {
				 return true;
			 }
		 }

	        return false;
	    }
}
