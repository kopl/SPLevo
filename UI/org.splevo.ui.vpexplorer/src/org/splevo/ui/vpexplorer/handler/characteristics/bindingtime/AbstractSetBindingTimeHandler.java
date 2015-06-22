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

import org.splevo.ui.vpexplorer.handler.characteristics.AbstractChangeCharacteristicHandler;
import org.splevo.ui.vpexplorer.handler.characteristics.CheckCharacteristics;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Abstract handler for setting the {@link BindingTime} of a {@link VariationPoint}.
 */
public abstract class AbstractSetBindingTimeHandler extends AbstractChangeCharacteristicHandler {
    private BindingTime bt;
    @Override
    protected boolean changeVariationPointCharacteristic(VariationPoint variationPoint) {

        BindingTime bindingTime = getTargetBindingTime();

        if (bindingTime.equals(variationPoint.getBindingTime())) {
            return false;
        }
        bt = variationPoint.getBindingTime();
        variationPoint.setBindingTime(bindingTime);
        if (!checkCharacteristic(variationPoint)) {
            return false;
        }
        return true;
    }

    /**
     * Get the {@link BindingTime} to be set by this handler.
     *
     * @return The intended type.
     */
    protected abstract BindingTime getTargetBindingTime();
    
    @Override    
    protected boolean checkCharacteristic(VariationPoint vp) {
        CheckCharacteristics check = new CheckCharacteristics();
        return check.checkVP(vp, bt);        
    }

}