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
package org.splevo.ui.vpexplorer.handler.characteristics.variabilitymechanism;

import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.ui.vpexplorer.handler.characteristics.AbstractChangeCharacteristicHandler;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Handler for setting the {@link VariabilityRefactoring} of a {@link VariationPoint}.
 */
public class SetVariabilityMechanism extends AbstractChangeCharacteristicHandler {
    
    private VariabilityRefactoring refactoring;
    
    /**
     * Creates a new SetVariabilityMechanism object.
     * @param refactoring the VariabilityRefactoring for the variation point
     */
    public SetVariabilityMechanism(VariabilityRefactoring refactoring) {
        this.refactoring = refactoring;
    }

    @Override
    protected boolean changeVariationPointCharacteristic(VariationPoint variationPoint) {        
        if (refactoring.canBeAppliedTo(variationPoint)) {
            variationPoint.setVariabilityMechanism(refactoring.getVariabilityMechanism());
            return true;
        }
        return false;
    }
    
}
