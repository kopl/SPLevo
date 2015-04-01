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
package org.splevo.ui.commons.wizard;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

/**
 * Wrapper for an EObject of which a single attribute shall be changed. This class uses the
 * EAttribute to get and set the new value.
 * 
 * @param <T> The type of the attribute (Java compatible type, not the EMF type).
 */
public class EObjectEAttributeWrapper<T> extends ChangeSingleAttributeEObjectWrapper<EObject, T> {

    private final EAttribute changedAttribute;

    /**
     * Constructs a new wrapper for an EObject of which an EAttribute is about to be changed.
     * 
     * @param typeName
     *            The type name of the element.
     * @param elementToRename
     *            The element to be renamed.
     * @param changedAttribute
     *            The EAttribute that will be changed.
     */
    public EObjectEAttributeWrapper(String typeName, EObject elementToRename, EAttribute changedAttribute) {
        super(typeName, elementToRename);
        this.changedAttribute = changedAttribute;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getAttributeValue() {
        return (T) getElement().eGet(changedAttribute);
    }

    @Override
    public void setAttributeValue(T newName) {
        getElement().eSet(changedAttribute, newName);
    }

}
