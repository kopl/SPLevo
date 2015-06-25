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

import org.splevo.ui.commons.vpm.VPMAttributeSetter;
import org.splevo.ui.commons.vpm.VPMAttributeSetter.SetAndRevertAction;
import org.splevo.ui.vpexplorer.handler.characteristics.AbstractChangeCharacteristicHandler;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Abstract handler for setting the {@link Extensibility} of a {@link VariationPoint}.
 */
public abstract class AbstractSetExtensibilityHandler extends AbstractChangeCharacteristicHandler {

    @Override
    protected boolean changeVariationPointCharacteristic(VariationPoint variationPoint) {

        final Extensible extendibility = getTargetExtensibility();
        final Extensible oldExtensibility = variationPoint.getExtensibility();

        if (extendibility.equals(variationPoint.getExtensibility())) {
            return false;
        }
        
        return VPMAttributeSetter.applyIfPossible(new SetAndRevertAction<VariationPoint>() {
            
            @Override
            public void set(VariationPoint vp) {
                vp.setExtensibility(extendibility);
            }
            
            @Override
            public void revert(VariationPoint vp) {
                vp.setExtensibility(oldExtensibility);
            }
        }, variationPoint);
    }

    /**
     * Get the {@link Extensible} to be set by this handler.
     *
     * @return The intended type.
     */
    protected abstract Extensible getTargetExtensibility();

}