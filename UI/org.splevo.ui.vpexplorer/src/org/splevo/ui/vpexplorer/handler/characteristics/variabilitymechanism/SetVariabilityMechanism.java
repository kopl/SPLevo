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
import org.splevo.ui.commons.vpm.VPMAttributeSetter;
import org.splevo.ui.commons.vpm.VPMAttributeSetter.SetAndRevertAction;
import org.splevo.ui.vpexplorer.handler.characteristics.AbstractChangeCharacteristicHandler;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.base.Objects;

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
        final VariabilityMechanism targetVariabilityMechanism = refactoring == null ? null : refactoring.getVariabilityMechanism();
        final VariabilityMechanism oldVariabilityMechanism = variationPoint.getVariabilityMechanism();
        
        if (Objects.equal(variationPoint.getVariabilityMechanism(), targetVariabilityMechanism)) {
            return false;
        }

        
        return VPMAttributeSetter.applyIfPossible(new SetAndRevertAction<VariationPoint>() {
            
            @Override
            public void set(VariationPoint vp) {
                vp.setVariabilityMechanism(targetVariabilityMechanism);
            }
            
            @Override
            public void revert(VariationPoint vp) {
                vp.setVariabilityMechanism(oldVariabilityMechanism);
            }
        }, variationPoint);
    }
    
}
