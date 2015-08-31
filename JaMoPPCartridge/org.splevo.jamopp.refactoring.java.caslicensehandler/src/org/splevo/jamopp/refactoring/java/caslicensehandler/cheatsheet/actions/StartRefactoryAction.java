package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.splevo.jamopp.refactoring.java.ifelse.optxor.SemiAutomatedIfStaticConfigClassOPTXOR;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringService;
import org.splevo.vpm.variability.VariationPoint;

/**
 * This Action starts the fully-automated if-static-config-refactoring.
 */
public class StartRefactoryAction extends Action implements ICheatSheetAction {

	@Override
	public void run(String[] params, ICheatSheetManager manager) {
		VariationPoint variationPoint = CASLicenseHandlerConfiguration.getVariationPoint();
		//Map<String, Object> refactoringConfigurations = CASLicenseHandlerConfiguration.getRefactoringConfigurations();
		Map<String, Object> refactoringConfigurations = new HashMap<String, Object>();
		refactoringConfigurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, 
				                      "");
		
		String validatorName = CASLicenseHandlerConfiguration.getLicenseValidatorName();
	
		VariabilityRefactoring refactoring = new SemiAutomatedIfStaticConfigClassOPTXOR(validatorName, 
													 									CASLicenseHandlerConfiguration.getVariantToLicenseMap());
		
		VariabilityRefactoringService refactoringService = new VariabilityRefactoringService();
		refactoringService.ifElseRefactoring(variationPoint, refactoringConfigurations, refactoring);
		
		variationPoint.setRefactored(true);
		RefactoringUtil.deleteCommentFrom(variationPoint);
	}

}
