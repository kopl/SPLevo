package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.splevo.jamopp.refactoring.java.ifelse.optxor.SemiAutomatedIfStaticConfigClassOPTXOR;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringFailedException;
import org.splevo.refactoring.VariabilityRefactoringService;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * This Action starts the fully-automated if-static-config-refactoring.
 */
public class StartCASLicenseHandlerRefactoringAction extends Action implements ICheatSheetAction {

    private static final Logger LOGGER = Logger.getLogger(StartCASLicenseHandlerRefactoringAction.class);
    
    /**
	 * Main-method to start the refactoring.
	 * @param params
	 * 			is not used in this context.
	 * @param manager
	 * 			is not used in this context.
	 */
	@Override
	public void run(String[] params, ICheatSheetManager manager) {
		CASLicenseHandlerConfiguration config = CASLicenseHandlerConfiguration.getInstance();		
		VariationPoint variationPoint = config.getVariationPoint();
		VariationPointModel vpm = config.getVariationPointModel();
		Map<String, Object> refactoringConfigurations = config.getRefactoringConfigurations();
		
		String validatorName = config.getLicenseValidatorType().getElementName(); // TODO replace name with ConcreteClassifier
	
		VariabilityRefactoring refactoring = new SemiAutomatedIfStaticConfigClassOPTXOR(validatorName, 
																						config.getVariantToLicenseMap());
		
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
