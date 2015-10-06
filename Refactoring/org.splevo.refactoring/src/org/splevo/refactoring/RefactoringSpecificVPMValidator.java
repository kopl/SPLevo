/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.refactoring;

import org.eclipse.emf.common.util.Diagnostic;
import org.splevo.vpm.VPMValidator;
import org.splevo.vpm.variability.VariationPoint;

/**
 * VPM validator with focus on constraints of refactorings.
 */
public class RefactoringSpecificVPMValidator implements VPMValidator {

    @Override
    public VPMValidationResult validate(VariationPoint vp) {
        VariabilityRefactoring ref = getVariabilityRefactoring(vp);
        if (ref != null && ref.canBeAppliedTo(vp).getSeverity() != Diagnostic.OK) {
            return new VPMValidationResult(false, String.format(
                    "The refactoring %s does not match the defined characteristics.", ref.getClass().getSimpleName()));
        }

        return new VPMValidationResult();
    }

    private VariabilityRefactoring getVariabilityRefactoring(VariationPoint vp) {
        if (vp.getVariabilityMechanism() == null) {
            return null;
        }
        String refactoringId = vp.getVariabilityMechanism().getRefactoringID();
        return VariabilityRefactoringRegistry.getInstance().getElementById(refactoringId);
    }

}
