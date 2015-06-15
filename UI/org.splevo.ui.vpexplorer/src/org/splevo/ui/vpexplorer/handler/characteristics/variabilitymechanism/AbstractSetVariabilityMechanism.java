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
 * Abstract handler for setting the {@link VariabilityRefactoring} of a {@link VariationPoint}.
 */
public abstract class AbstractSetVariabilityMechanism extends AbstractChangeCharacteristicHandler {

    @Override
    protected boolean changeVariationPointCharacteristic(VariationPoint variationPoint) {
        
        VariabilityRefactoring mechanism = getTargetVariabilityMechanism();
        //no change happened
        if (mechanism.getVariabilityMechanism().equals(variationPoint.getVariabilityMechanism())) {
            return false;
        }
        //checks if the mechanism can be applied with the actual properties
        if (!mechanism.canBeAppliedTo(variationPoint)) {
            return false;
        }        
        variationPoint.setVariabilityMechanism(mechanism.getVariabilityMechanism());
        return true;
    }
    
    /**
     * Get the {@link VariabilityRefactoring} to be set by this handler.
     *
     * @return The intended type.
     */
    protected abstract VariabilityRefactoring getTargetVariabilityMechanism(); 
}
