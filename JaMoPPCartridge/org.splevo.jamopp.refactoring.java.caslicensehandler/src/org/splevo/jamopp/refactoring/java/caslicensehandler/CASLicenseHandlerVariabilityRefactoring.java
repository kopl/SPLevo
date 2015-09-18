package org.splevo.jamopp.refactoring.java.caslicensehandler;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.cheatsheets.OpenCheatSheetAction;
import org.splevo.jamopp.refactoring.java.JaMoPPSemiAutomatedVariabilityRefactoring;
import org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions.CASLicenseHandlerConfiguration;
import org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions.CASLicenseHandlerMetaInf;
import org.splevo.refactoring.VariabilityRefactoringFailedException;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * Implements the cas license handler semi-automated refactoring
 */
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
	public List<Resource> startManualRefactoringInternal(VariationPoint variationPoint, Map<String, Object> refactoringConfigurations) throws VariabilityRefactoringFailedException {
	    CASLicenseHandlerConfiguration.setRefactoringConfigurations(refactoringConfigurations);
	    CASLicenseHandlerConfiguration.setVariationPoint(variationPoint);
	    CASLicenseHandlerConfiguration.refactoringStarted();
		final OpenCheatSheetAction action = new OpenCheatSheetAction(CASLicenseHandlerMetaInf.CAS_LICENSE_HANDLER_CHEAT_SHEET_ID);
		Display.getDefault().syncExec(new Runnable() {
            @Override
            public void run() {
                action.run();
            }});
	    
	    try {
	        CASLicenseHandlerConfiguration.waitForRefactoringToBeFinished();	        
	    } catch (InterruptedException e) {
	        throw new VariabilityRefactoringFailedException("The refactoring has been aborted before it has been finished.", e);
	    }
	    return Lists.newArrayList();
	}

}
