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
package org.splevo.ui.views.vpproperties;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * A connector providing access to the properties of a variation point representing the source.
 */
public class VariationPointPropertySource implements IPropertySource {

    private static Logger logger = Logger.getLogger(VariationPointPropertySource.class);

    private static final String PROPERTY_ID_EXTENSIBILITY = "extensibile";
    private static final String PROPERTY_ID_VARIABILITYTYPE = "variabilitytype";
    private static final String PROPERTY_ID_BINDINGTIME = "bindingtime";

    private final VariationPoint vp;

    private static List<String> variabilityTypes = Lists.newArrayList();
    private static List<String> bindingTimes = Lists.newArrayList();
    private static List<String> extensibilities = Lists.newArrayList();
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
    public Object getEditableValue() {
        return this;
    }

    @Override
    public IPropertyDescriptor[] getPropertyDescriptors() {

        return new IPropertyDescriptor[] {
                new ComboBoxPropertyDescriptor(PROPERTY_ID_VARIABILITYTYPE, "Variability Type",
                        variabilityTypes.toArray(new String[] {})),
                new ComboBoxPropertyDescriptor(PROPERTY_ID_BINDINGTIME, "Binding Time",
                        bindingTimes.toArray(new String[] {})),
                new ComboBoxPropertyDescriptor(PROPERTY_ID_EXTENSIBILITY, "Extensible",
                        extensibilities.toArray(new String[] {})) };
    }

    @Override
    public Object getPropertyValue(Object id) {
        if (id.equals(PROPERTY_ID_VARIABILITYTYPE)) {
            return vp.getVariabilityType().getValue();
        }
        if (id.equals(PROPERTY_ID_EXTENSIBILITY)) {
            return vp.getExtensibility().getValue();
        }
        if (id.equals(PROPERTY_ID_BINDINGTIME)) {
            return vp.getBindingTime().getValue();
        }

        return null;
    }

    @Override
    public boolean isPropertySet(Object id) {
        return (getPropertyValue(id) != null);
    }

    @Override
    public void resetPropertyValue(Object id) {
    }

    @Override
    public void setPropertyValue(Object id, Object value) {
        if (id.equals(PROPERTY_ID_VARIABILITYTYPE) && value instanceof Integer) {
            vp.setVariabilityType(VariabilityType.get((Integer) value));

        } else if (id.equals(PROPERTY_ID_EXTENSIBILITY) && value instanceof Integer) {
            vp.setExtensibility(Extensible.get((Integer) value));

        } else if (id.equals(PROPERTY_ID_BINDINGTIME) && value instanceof Integer) {
            vp.setBindingTime(BindingTime.get((Integer) value));

        } else {
            logger.warn("Unsupported property value set. Property ID: " + id + " Value: " + value);
        }

        saveVariationPoint();
    }

    private void saveVariationPoint() {
        if (vp.eResource() != null) {
            try {
                vp.eResource().save(null);
            } catch (IOException e) {
                logger.error("Failed to save VariationPoint modifications", e);
            }
        }

    }

}
