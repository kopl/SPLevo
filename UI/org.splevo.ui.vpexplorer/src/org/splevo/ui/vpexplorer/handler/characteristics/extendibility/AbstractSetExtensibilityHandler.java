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
package org.splevo.ui.vpexplorer.handler.characteristics.extendibility;

import org.splevo.ui.vpexplorer.handler.characteristics.AbstractChangeCharacteristicHandler;
import org.splevo.ui.vpexplorer.handler.characteristics.CheckCharacteristics;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Abstract handler for setting the {@link Extensibility} of a {@link VariationPoint}.
 */
public abstract class AbstractSetExtensibilityHandler extends AbstractChangeCharacteristicHandler {

    private Extensible ex;
    @Override
    protected boolean changeVariationPointCharacteristic(VariationPoint variationPoint) {

        Extensible extendibility = getTargetExtensibility();

        if (extendibility.equals(variationPoint.getExtensibility())) {
            return false;
        }
        ex = variationPoint.getExtensibility();
        variationPoint.setExtensibility(extendibility);
        
        if (!checkCharacteristic(variationPoint)) {
            return false;
        }
        return true;
    }

    /**
     * Get the {@link Extensible} to be set by this handler.
     *
     * @return The intended type.
     */
    protected abstract Extensible getTargetExtensibility();
    
    @Override    
    protected boolean checkCharacteristic(VariationPoint vp) {
        CheckCharacteristics check = new CheckCharacteristics();
        return check.checkVP(vp, ex);        
    }

}