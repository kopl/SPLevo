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

import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

/**
 * Wrapper for an EObject whichs attribute is about to be changed.
 *
 * @param <ElementType> The type of the element about to be changed
 * @param <AttributeType> The type of the attribute about to be changed.
 */
public abstract class ChangeSingleAttributeEObjectWrapper<ElementType extends EObject, AttributeType>
    implements ChangeSingleAttributeElementWrapper<AttributeType> {

    private static final Logger LOGGER = Logger.getLogger(ChangeSingleAttributeElementWrapper.class);
    private final String typeName;
    private final ElementType element;
    
    /**
     * Constructs a new wrapper for EObjects.
     * @param typeName The type name of the changed element.
     * @param elementToRename The element to be changed.
     */
    protected ChangeSingleAttributeEObjectWrapper(String typeName, ElementType elementToRename) {
        this.element = elementToRename;
        this.typeName = typeName;
    }
    
    /**
     * @return The element to be changed.
     */
    protected ElementType getElement() {
        return element;
    }

    @Override
    public String getElementTypeName() {
        return typeName;
    }

    /**
     * Saves the model that corresponds to the changed element.
     */
    public void persistChange() {
        try {
            element.eResource().save(null);
        } catch (IOException e) {
            LOGGER.error("Could not save element!", e);
            e.printStackTrace();
        }
    }

}
