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

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;
import com.google.common.collect.Lists;

/**
 * A connector providing access to the properties of a variation point or 
 * variation point group representing the source.
 */
public abstract class PropertySource implements IPropertySource {

    private static final String CATEGORY_REALIZATION = "Variability Realization";

    private static final String CATEGORY_CHARACTERISTICS = "Variability Characteristics";

    private static Logger logger = Logger.getLogger(VariationPointPropertySource.class);

    protected static final String PROPERTY_ID_EXTENSIBILITY = "extensibile";
    protected static final String PROPERTY_ID_VARIABILITYTYPE = "variabilitytype";
    protected static final String PROPERTY_ID_BINDINGTIME = "bindingtime";
    protected static final String PROPERTY_ID_VARIABILITY_MECHANISM = "variabilitymechanism";

    protected static List<String> variabilityTypes = Lists.newArrayList();
    protected static List<String> bindingTimes = Lists.newArrayList();
    protected static List<String> extensibilities = Lists.newArrayList();
    static {
        for (VariabilityType vt : VariabilityType.VALUES) {
            variabilityTypes.add(vt.getName());
        }
        for (BindingTime bt : BindingTime.VALUES) {
            bindingTimes.add(bt.getName());
        }
        for (Extensible ex : Extensible.VALUES) {
            extensibilities.add(ex.getName());
        }
    }

    @Override
    public Object getEditableValue() {
        return this;
    }

    @Override
    public IPropertyDescriptor[] getPropertyDescriptors() {

        ComboBoxPropertyDescriptor variabilityTypeDescriptor = new ComboBoxPropertyDescriptor(
                PROPERTY_ID_VARIABILITYTYPE, "Variability Type", variabilityTypes.toArray(new String[] {}));
        variabilityTypeDescriptor.setCategory(CATEGORY_CHARACTERISTICS);

        ComboBoxPropertyDescriptor bindingTimeDescriptor = new ComboBoxPropertyDescriptor(PROPERTY_ID_BINDINGTIME,
                "Binding Time", bindingTimes.toArray(new String[] {}));
        bindingTimeDescriptor.setCategory(CATEGORY_CHARACTERISTICS);

        ComboBoxPropertyDescriptor extensibilityDescriptor = new ComboBoxPropertyDescriptor(PROPERTY_ID_EXTENSIBILITY,
                "Extensible", extensibilities.toArray(new String[] {}));
        extensibilityDescriptor.setCategory(CATEGORY_CHARACTERISTICS);

        VariabilityMechanismPropertyDescriptor variabilityMechanismDescriptor = new VariabilityMechanismPropertyDescriptor(
                PROPERTY_ID_VARIABILITY_MECHANISM, "Variability Mechanism");
        variabilityMechanismDescriptor.setCategory(CATEGORY_REALIZATION);

        return new IPropertyDescriptor[] { variabilityTypeDescriptor, bindingTimeDescriptor, extensibilityDescriptor,
                variabilityMechanismDescriptor };
    }    

    @Override
    public boolean isPropertySet(Object id) {
        return (getPropertyValue(id) != null);
    }

    @Override
    public void resetPropertyValue(Object id) {
    }
    
    /**
     * Changes the characteristics of a variation point.
     * @param variationPoint the variation point to edit
     * @param id the property id
     * @param value the index of the combo box
     */
    protected void setPropertyInternal(VariationPoint variationPoint, Object id, Object value) {
        if (id.equals(PROPERTY_ID_VARIABILITYTYPE) && value instanceof Integer) {
            VariabilityType type = VariabilityType.getByName(variabilityTypes.get((Integer) value));
            variationPoint.setVariabilityType(type);

        } else if (id.equals(PROPERTY_ID_EXTENSIBILITY) && value instanceof Integer) {
            Extensible extensibility = Extensible.getByName(extensibilities.get((Integer) value));
            variationPoint.setExtensibility(extensibility);

        } else if (id.equals(PROPERTY_ID_BINDINGTIME) && value instanceof Integer) {
            BindingTime bindingTime = BindingTime.getByName(bindingTimes.get((Integer) value));
            variationPoint.setBindingTime(bindingTime);          

        } else if (id.equals(PROPERTY_ID_VARIABILITY_MECHANISM)) {
            if (value == null) {
                variationPoint.setVariabilityMechanism(null);
            } else if (value instanceof VariabilityRefactoring) {
                VariabilityRefactoring refactoring = (VariabilityRefactoring) value;
                variationPoint.setVariabilityMechanism(refactoring.getVariabilityMechanism());
            }

        } else {
            logger.warn("Unsupported property value set. Property ID: " + id + " Value: " + value);
        }
    }

    /**
     * Saves the variation point.
     * @param vp the variation point to save
     */
    protected void saveVariationPoint(VariationPoint vp) {
        if (vp.eResource() != null) {
            try {
                vp.eResource().save(null);
            } catch (IOException e) {
                logger.error("Failed to save VariationPoint modifications", e);
            }
        }
    }

}
