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

/**
 * Wrapper for an element that shall be renamed. The wrapper includes the element itself as well as
 * the techniques to retrieve the current, set the new name and persist the changes.
 */
public interface RenameElementWrapper {

    /**
     * @return The type name of the element to be renamed.
     */
    public String getElementToRenameTypeName();

    /**
     * Determines the current name of the element.
     * 
     * @return The name of the element.
     */
    public String getElementName();

    /**
     * Sets the new name.
     * 
     * @param newName
     *            The new name for the element.
     */
    public void setElementName(String newName);

    /**
     * Saves the model that corresponds to the changed element.
     */
    public void persistChange();
}