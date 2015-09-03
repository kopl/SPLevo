package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.splevo.jamopp.refactoring.java.ifelse.optxor.SemiAutomatedIfStaticConfigClassOPTXOR;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringFailedException;
import org.splevo.refactoring.VariabilityRefactoringService;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * This Action starts the fully-automated if-static-config-refactoring.
 */
public class StartRefactoryAction extends Action implements ICheatSheetAction {

    private static final Logger LOGGER = Logger.getLogger(StartRefactoryAction.class);
    
	@Override
	public void run(String[] params, ICheatSheetManager manager) {
		VariationPoint variationPoint = CASLicenseHandlerConfiguration.getVariationPoint();
		VariationPointModel vpm = CASLicenseHandlerConfiguration.getVariationPointModel();
		Map<String, Object> refactoringConfigurations = CASLicenseHandlerConfiguration.getRefactoringConfigurations();
		
		String validatorName = CASLicenseHandlerConfiguration.getLicenseValidatorName();
	
		VariabilityRefactoring refactoring = new SemiAutomatedIfStaticConfigClassOPTXOR(validatorName, 
													 									CASLicenseHandlerConfiguration.getVariantToLicenseMap());
		
		VariabilityRefactoringService refactoringService = new VariabilityRefactoringService();
		try {
            refactoringService.refactorFullyAutomated(refactoring, vpm, variationPoint, refactoringConfigurations);
        } catch (VariabilityRefactoringFailedException e) {
            LOGGER.error("The IfElseRefactoring failed.", e);
            // TODO inform user about failed refactoring
        }
		// TODO add finish action to the cheat sheet and move the following call into this action
		CASLicenseHandlerConfiguration.refactoringFinished();
	}

}
