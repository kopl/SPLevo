package org.splevo.jamopp.refactoring.java.caslicensehandler;

import java.util.Map;

import org.splevo.jamopp.refactoring.java.JaMoPPSemiAutomatedVariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

public class CASLicenseHandlerVariabilityRefactoring extends JaMoPPSemiAutomatedVariabilityRefactoring {

	private static final String REFACTORING_NAME = "CAS License Handler";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.caslicensehandler.CASLicenseHandler";
	
	@Override
	public VariabilityMechanism getVariabilityMechanism() {
		VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
	}

	@Override
	public boolean canBeAppliedTo(VariationPoint variationPoint) {
		boolean correctBindingTime = variationPoint.getBindingTime() == BindingTime.RUN_TIME;
        boolean correctVariabilityType = variationPoint.getVariabilityType() == VariabilityType.OPTXOR;
        boolean correctExtensibility = variationPoint.getExtensibility() == Extensible.NO;

        return (correctBindingTime && correctVariabilityType && correctExtensibility);
	}

	@Override
	public String getId() {
		return REFACTORING_ID;
	}

	@Override
	public void startManualRefactoring(VariationPoint variationPoint,
			Map<String, Object> refactoringConfigurations) {
		// TODO Auto-generated method stub
		
	}

}
