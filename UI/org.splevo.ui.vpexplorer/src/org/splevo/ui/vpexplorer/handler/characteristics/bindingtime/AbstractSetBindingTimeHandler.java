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
package org.splevo.ui.vpexplorer.handler.characteristics.bindingtime;

import org.splevo.ui.commons.vpm.VPMAttributeSetter;
import org.splevo.ui.commons.vpm.VPMAttributeSetter.SetAndRevertAction;
import org.splevo.ui.vpexplorer.handler.characteristics.AbstractChangeCharacteristicHandler;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Abstract handler for setting the {@link BindingTime} of a {@link VariationPoint}.
 */
public abstract class AbstractSetBindingTimeHandler extends AbstractChangeCharacteristicHandler {

    @Override
    protected boolean changeVariationPointCharacteristic(VariationPoint variationPoint) {
        final BindingTime bindingTime = getTargetBindingTime();
        final BindingTime oldBindingTime = variationPoint.getBindingTime();
        
        if (bindingTime.equals(variationPoint.getBindingTime())) {
            return false;
        }

        return VPMAttributeSetter.applyIfPossible(new SetAndRevertAction<VariationPoint>() {
            
            @Override
            public void set(VariationPoint vp) {
                vp.setBindingTime(bindingTime);
            }
            
            @Override
            public void revert(VariationPoint vp) {
                vp.setBindingTime(oldBindingTime);
            }
        }, variationPoint);
    }

    /**
     * Get the {@link BindingTime} to be set by this handler.
     *
     * @return The intended type.
     */
    protected abstract BindingTime getTargetBindingTime();

}