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

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;

/**
 * Factory to create adapter for content access.
 */
public class VariationPointAdapterFactory implements IAdapterFactory {

    @Override
    @SuppressWarnings("rawtypes")
    public Object getAdapter(Object adaptableObject, Class adapterType) {
        if (adapterType == IPropertySource.class) {
            if (adaptableObject instanceof VariationPoint) {
                return new VariationPointPropertySource((VariationPoint) adaptableObject);
            } else if (adaptableObject instanceof VariationPointGroup) {
                return new VariationPointGroupPropertySource((VariationPointGroup) adaptableObject);
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Class[] getAdapterList() {
        return new Class[] { IPropertySource.class };
    }

}
