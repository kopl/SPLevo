/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.ui.vpexplorer.handler.characteristics.variabilitytype;

import org.splevo.ui.commons.vpm.VPMAttributeSetter;
import org.splevo.ui.commons.vpm.VPMAttributeSetter.SetAndRevertAction;
import org.splevo.ui.vpexplorer.handler.characteristics.AbstractChangeCharacteristicHandler;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Abstract handler for setting the {@link VariabilityType} of a {@link VariationPoint}.
 */
public abstract class AbstractSetVariabilityTypeHandler extends AbstractChangeCharacteristicHandler {

    @Override
    protected boolean changeVariationPointCharacteristic(VariationPoint variationPoint) {

        final VariabilityType targetType = getTargetVariabilityType();
        final VariabilityType oldVariabilityType = variationPoint.getVariabilityType();

        if (targetType.equals(variationPoint.getVariabilityType())) {
            return false;
        }
        
        return VPMAttributeSetter.applyIfPossible(new SetAndRevertAction<VariationPoint>() {
            
            @Override
            public void set(VariationPoint vp) {
                vp.setVariabilityType(targetType);
            }
            
            @Override
            public void revert(VariationPoint vp) {
                vp.setVariabilityType(oldVariabilityType);
            }
        }, variationPoint);
    }

    /**
     * Get the {@link VariabilityType} to be set by this handler.
     *
     * @return The intended type.
     */
    protected abstract VariabilityType getTargetVariabilityType();

}