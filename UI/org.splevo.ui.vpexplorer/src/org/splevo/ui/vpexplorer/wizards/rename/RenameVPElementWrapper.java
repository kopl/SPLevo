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
package org.splevo.ui.vpexplorer.wizards.rename;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

/**
 * Wrapper for a variation point element that shall be renamed.
 * 
 * @param <T> The type of the variation point element.
 */
public abstract class RenameVPElementWrapper<T extends EObject> {
    
    private static final Logger LOGGER = Logger.getLogger(RenameVPElementWrapper.class);
    
    protected T elementToRename;
    
    /**
     * Constructs a new wrapper for a variation point model element.
     * @param elementToRename The element to be wrapped.
     */
    public RenameVPElementWrapper(T elementToRename) {
        this.elementToRename = elementToRename;
    }
    
    /**
     * @return The type name of the element to be renamed.
     */
    public abstract String getElementToRenameTypeName();

    /**
     * Determines the current name of the element.
     * @return The name of the element.
     */
    public abstract String getElementName();
    
    /**
     * Sets the new name.
     * @param newName The new name for the element.
     */
    public abstract void setElementName(String newName);
    
    /**
     * Saves the model that corresponds to the changed element.
     */
    public void saveCorrespondingModel() {
        try {
            elementToRename.eResource().save(null);
        } catch (IOException e) {
            LOGGER.error("Could not save new group name!", e);
            e.printStackTrace();
        }
    }
}