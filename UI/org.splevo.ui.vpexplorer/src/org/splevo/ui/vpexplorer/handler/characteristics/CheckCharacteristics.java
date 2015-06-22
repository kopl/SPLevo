/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thomas Czogalik
 *******************************************************************************/
package org.splevo.ui.vpexplorer.handler.characteristics;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringRegistry;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Class to check if a variation point can be applied. Generate error message if not. 
 */
public class CheckCharacteristics {

    /**
     * Checks if the given variation point can be applied.
     * @param vp the variation point to check
     * @param bt the old binding time, in case the variation point can't be applied
     * @return true if the variation point can be applied, false otherwise
     */
    public boolean checkVP(VariationPoint vp, BindingTime bt) {
        VariabilityRefactoring refactoring = getVariabilityRefactoring(vp);
        checkVP(refactoring, vp);
        vp.setBindingTime(bt);
        showError("binding");
        return false;
    }
    /**
     * Checks if the given variation point can be applied.
     * @param vp the variation point to check
     * @param ex the old extensibility, in case the variation point can't be applied
     * @return true if the variation point can be applied, false otherwise
     */
    public boolean checkVP(VariationPoint vp, Extensible ex) {
        VariabilityRefactoring refactoring = getVariabilityRefactoring(vp);
        checkVP(refactoring, vp);
        vp.setExtensibility(ex);
        showError("extensibility");
        return false;
    }
    /**
     * Checks if the given variation point can be applied.
     * @param vp the variation point to check
     * @param vt the old variability type, in case the variation point can't be applied
     * @return true if the variation point can be applied, false otherwise
     */
    public boolean checkVP(VariationPoint vp, VariabilityType vt) {
        VariabilityRefactoring refactoring = getVariabilityRefactoring(vp);
        checkVP(refactoring, vp);
        vp.setVariabilityType(vt);
        showError("variability type");
        return false;
    }
    /**
     * Checks if the given variation point can be applied.
     * @param vp the variation point to check
     * @param vm the old variability mechanism, in case the variation point can't be applied
     * @return true if the variation point can be applied, false otherwise
     */
    public boolean checkVP(VariationPoint vp, VariabilityMechanism vm) {
        VariabilityRefactoring refactoring = getVariabilityRefactoring(vp);         
        checkVP(refactoring, vp);
        vp.setVariabilityMechanism(vm);
        showError("refactoring");
        return false;
    }
    
    private boolean checkVP(VariabilityRefactoring refactoring, VariationPoint vp) {
        if (refactoring == null) {
            return true;
        }
        if (refactoring.canBeAppliedTo(vp)) {
            return true;
        }
        return false;
    }
    
    private VariabilityRefactoring getVariabilityRefactoring(VariationPoint vp) {
        if (vp.getVariabilityMechanism() == null) {
            return null;
        }
        String refactoringId = vp.getVariabilityMechanism().getRefactoringID();
        return VariabilityRefactoringRegistry.getInstance().getElementById(refactoringId);
    }
    
    private void showError(String msg) {
        MessageDialog.openError(Display.getDefault().getShells()[0], 
                "Apply Error" , "can't apply the choosen " + msg);
    }
    
}
