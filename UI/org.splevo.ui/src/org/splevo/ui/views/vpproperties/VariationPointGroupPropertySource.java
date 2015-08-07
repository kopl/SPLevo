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

import java.util.List;

import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringRegistry;
import org.splevo.ui.commons.vpm.VPMAttributeSetter;
import org.splevo.ui.commons.vpm.VPMAttributeSetter.SetAndRevertAction;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;

import com.google.common.base.Objects;

/**
 * A connector providing access to the properties of a variation point group representing the source.
 */
public class VariationPointGroupPropertySource extends PropertySource {

    private final List<VariationPoint> vps;
    
    /**
     * Constructor to couple the connector with the variation point group.
     *
     * @param vpg
     *            The variation point group to provide access to.
     */
    public VariationPointGroupPropertySource(VariationPointGroup vpg) {
        this.vps = vpg.getVariationPoints();
    }

    @Override
    public Object getPropertyValue(Object id) {
        
        if (id.equals(PROPERTY_ID_VARIABILITYTYPE)) {
            return variabilityTypes.indexOf(getVariabilityType());
        }
        if (id.equals(PROPERTY_ID_EXTENSIBILITY)) {
            return extensibilities.indexOf(getExtensibility());
        }
        if (id.equals(PROPERTY_ID_BINDINGTIME)) {
            return bindingTimes.indexOf(getBindingTime());
        }
        if (id.equals(PROPERTY_ID_VARIABILITY_MECHANISM)) {
            return getVariabilityMechanism();
        }

        return null;
    }
    
    private String getVariabilityType() {
        
        for (int i = 0; i < vps.size(); i++) {
            if (!vps.get(0).getVariabilityType().equals(vps.get(i).getVariabilityType())) {
                return null;
            }
        }
        return vps.get(0).getVariabilityType().getName();
    }
    
    private String getExtensibility() {
        for (int i = 0; i < vps.size(); i++) {
            if (!vps.get(0).getExtensibility().equals(vps.get(i).getExtensibility())) {
                return null;
            }
        }
        return vps.get(0).getExtensibility().getName();
    }
    
    private String getBindingTime() {
        for (int i = 0; i < vps.size(); i++) {
            if (!vps.get(0).getBindingTime().equals(vps.get(i).getBindingTime())) {
                return null;
            }
        }
        return vps.get(0).getBindingTime().getName();
    }
    
    private VariabilityRefactoring getVariabilityMechanism() {        
        for (int i = 0; i < vps.size(); i++) {
            if (vps.get(i).getVariabilityMechanism() == null 
                    || !vps.get(0).getVariabilityMechanism().getRefactoringID()
                    .equals(vps.get(i).getVariabilityMechanism().getRefactoringID())) {
                return null;
            }
        }
        String refactoringId = vps.get(0).getVariabilityMechanism().getRefactoringID();
        VariabilityRefactoring refactoring = VariabilityRefactoringRegistry.getInstance().getElementById(refactoringId);
        return refactoring;
    }

    @Override
    public void setPropertyValue(final Object id, final Object value) {
        final Object oldValue = getPropertyValue(id);
        if (Objects.equal(oldValue, value)) {
            return;
        }
        boolean[] changed = new boolean[vps.size()];
        for (int i = 0; i < vps.size(); i++) {
            VariationPoint vp = vps.get(i);
            changed[i] = VPMAttributeSetter.applyIfPossible(new SetAndRevertAction<VariationPoint>() {                
                @Override
                public void set(VariationPoint vp) {
                    setPropertyInternal(vp, id, value);
                }
                
                @Override
                public void revert(VariationPoint vp) {
                    setPropertyInternal(vp, id, oldValue);
                }
            }, vp);
        }

        for (int i = 0; i < vps.size(); i++) {
            if (changed[i]) {
                saveVariationPoint(vps.get(i));
            }
        }
    }
        
}
