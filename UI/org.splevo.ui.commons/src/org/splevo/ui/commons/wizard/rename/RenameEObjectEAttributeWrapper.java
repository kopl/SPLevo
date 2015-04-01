/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.commons.wizard.rename;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.splevo.ui.commons.util.LabelUtils;
import org.splevo.ui.commons.wizard.EObjectEAttributeWrapper;

/**
 * Wrapper for EObject elements that are renamed via an EAttribute.
 */
public class RenameEObjectEAttributeWrapper extends EObjectEAttributeWrapper<String> {

    /**
     * Constructs a new wrapper for an EObject that is renamed via an EAttribute.
     * @param typeName The type name of the renamed element.
     * @param elementToRename The element to be renamed.
     * @param nameAttribute The EAttribute that will hold the new name.
     */
    public RenameEObjectEAttributeWrapper(String typeName, EObject elementToRename, EAttribute nameAttribute) {
        super(typeName, elementToRename, nameAttribute);
    }

    @Override
    public String getAttributeValue() {
        return LabelUtils.getItemProviderText(getElement());
    }

}
