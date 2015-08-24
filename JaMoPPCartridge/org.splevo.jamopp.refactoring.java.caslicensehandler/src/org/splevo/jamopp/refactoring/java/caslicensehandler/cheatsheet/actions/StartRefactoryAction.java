package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.splevo.jamopp.refactoring.java.ifelse.optxor.SemiAutomatedIfStaticConfigClassOPTXOR;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringService;
import org.splevo.vpm.variability.VariationPoint;

public class StartRefactoryAction extends Action implements ICheatSheetAction {

	@Override
	public void run(String[] params, ICheatSheetManager manager) {
		VariationPoint variationPoint = CASLicenseHandlerConfiguration.getVariationPoint();
		Map<String, Object> refactoringConfigurations = CASLicenseHandlerConfiguration.getRefactoringConfigurations();
		
		String validatorName = CASLicenseHandlerConfiguration.getLicenseValidatorName();
		/*SemiAutomatedIfElseRefactoring semiRefactoring = new SemiAutomatedIfElseRefactoring(
				validatorName, 
				CASLicenseHandlerConfiguration.getVariantToLicenseMap());*/
	
		VariabilityRefactoring refactoring = new SemiAutomatedIfStaticConfigClassOPTXOR(validatorName, CASLicenseHandlerConfiguration.getVariantToLicenseMap());
		
		VariabilityRefactoringService refactoringService = new VariabilityRefactoringService();
		refactoringService.ifElseRefactoring(variationPoint, refactoringConfigurations, refactoring);
		
	}

}
