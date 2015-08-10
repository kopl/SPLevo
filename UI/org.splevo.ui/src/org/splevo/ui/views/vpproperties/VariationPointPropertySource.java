/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt, Thomas Czogalik
 *******************************************************************************/
package org.splevo.ui.views.vpproperties;

import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringRegistry;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.base.Objects;


/**
 * A connector providing access to the properties of a variation point representing the source.
 */
public class VariationPointPropertySource extends PropertySource {

    private final VariationPoint vp; 
    
    /**
     * Constructor to couple the connector with the variation point.
     *
     * @param vp
     *            The variation point to provide access to.
     */
    public VariationPointPropertySource(VariationPoint vp) {
        this.vp = vp;
    }
    
    @Override
    public Object getPropertyValue(Object id) {
        if (id.equals(PROPERTY_ID_VARIABILITYTYPE)) {
            return variabilityTypes.indexOf(vp.getVariabilityType().getName());
        }
        if (id.equals(PROPERTY_ID_EXTENSIBILITY)) {
            return extensibilities.indexOf(vp.getExtensibility().getName());
        }
        if (id.equals(PROPERTY_ID_BINDINGTIME)) {
            return bindingTimes.indexOf(vp.getBindingTime().getName());
        }
        if (id.equals(PROPERTY_ID_VARIABILITY_MECHANISM)) {
            if (vp.getVariabilityMechanism() == null) {
                return null;
            }

            String refactoringId = vp.getVariabilityMechanism().getRefactoringID();
            VariabilityRefactoring refactoring = VariabilityRefactoringRegistry.getInstance().getElementById(refactoringId);
            return refactoring;
        }

        return null;
    }
    
    @Override
    public void setPropertyValue(final Object id, final Object value) {
        final Object oldValue = getPropertyValue(id);
        if (Objects.equal(oldValue, value)) {
            return;
        }        
        setPropertyValue(id, value, oldValue, vp);
    }
}
