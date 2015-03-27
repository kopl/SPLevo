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

/**
 * Wrapper for EObject elements that are renamed via an EAttribute.
 */
public class RenameEObjectEAttributeWrapper extends RenameEObjectWrapper<EObject> {

    private final EAttribute nameAttribute;

    /**
     * Constructs a new wrapper for an EObject that is renamed via an EAttribute.
     * @param typeName The type name of the renamed element.
     * @param elementToRename The element to be renamed.
     * @param nameAttribute The EAttribute that will hold the new name.
     */
    public RenameEObjectEAttributeWrapper(String typeName, EObject elementToRename, EAttribute nameAttribute) {
        super(typeName, elementToRename);
        this.nameAttribute = nameAttribute;
    }

    @Override
    public String getElementName() {
        return LabelUtils.getItemProviderText(getElement());
    }

    @Override
    public void setElementName(String newName) {
        getElement().eSet(nameAttribute, newName);
    }

}
