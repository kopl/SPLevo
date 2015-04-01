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

/**
 * Wrapper for an element that shall be renamed. The wrapper includes the element itself as well as
 * the techniques to retrieve the current, set the new name and persist the changes.
 * @param <T> The type of the attribute to change.
 */
public interface ChangeSingleAttributeElementWrapper<T> {

    /**
     * @return The type name of the element to be renamed.
     */
    public String getElementTypeName();

    /**
     * Determines the current name of the element.
     * 
     * @return The name of the element.
     */
    public T getAttributeValue();

    /**
     * Sets the new name.
     * 
     * @param newName
     *            The new name for the element.
     */
    public void setAttributeValue(T newName);

    /**
     * Saves the model that corresponds to the changed element.
     */
    public void persistChange();
}