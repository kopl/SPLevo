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
package org.splevo.commons.registry;

import org.apache.log4j.Logger;
import org.splevo.commons.interfaces.IdHavingElement;

/**
 * Base class for registries of identifier having elements.
 * 
 * @param <T>
 *            The type of the contained elements.
 * @param <S>
 *            The type of the element's key.
 */
public abstract class IdBasedRegistryBase<T extends IdHavingElement<S>, S> extends RegistryBase<T> {

    private static final Logger LOGGER = Logger.getLogger(IdBasedRegistryBase.class);

    /**
     * Returns the the registered element with the given ID or null if no such element is
     * registered.
     * 
     * @param id
     *            The ID of the element.
     * @return The element with the given ID.
     */
    public T getElementById(S id) {
        for (T element : elements) {
            if (element.getId().equals(id)) {
                return element;
            }
        }
        return null;
    }

    @Override
    protected boolean isValid(T element) {
        if (element == null) {
            LOGGER.warn("Tried to register null element");
            return false;
        }
        if (element.getId() == null) {
            LOGGER.warn("Tried to register element without id " + element);
            return false;
        }
        return true;
    }

    /**
     * Detects equal elements for finding already registered elements. The default implementation
     * uses the IDs to compare the elements.
     * 
     * @param element1
     *            The first element.
     * @param element2
     *            The second element.
     * @return True if the elements are considered equal, False otherwise.
     */
    @Override
    protected boolean areElementsConsideredTheSame(T element1, T element2) {
        return element1.getId().equals(element2.getId());
    }

}
