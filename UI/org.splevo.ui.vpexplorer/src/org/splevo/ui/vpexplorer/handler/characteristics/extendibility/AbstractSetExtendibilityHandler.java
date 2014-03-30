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
import org.splevo.vpm.variability.Extendibility;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Abstract handler for setting the {@link Extendibility} of a {@link VariationPoint}.
 */
public abstract class AbstractSetExtendibilityHandler extends AbstractChangeCharacteristicHandler {

    @Override
    protected boolean changeVariationPointCharacteristic(VariationPoint variationPoint) {

        Extendibility extendibility = getTargetExtendibility();

        if (extendibility.equals(variationPoint.getExtendibility())) {
            return false;
        }

        variationPoint.setExtendibility(extendibility);
        return true;
    }

    /**
     * Get the {@link Extendibility} to be set by this handler.
     *
     * @return The intended type.
     */
    protected abstract Extendibility getTargetExtendibility();

}