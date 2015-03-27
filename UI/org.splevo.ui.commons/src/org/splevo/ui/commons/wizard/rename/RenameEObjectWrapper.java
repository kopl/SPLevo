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

import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.splevo.ui.commons.util.LabelUtils;

/**
 * Wrapper for EObject elements about to be renamed.
 *
 * @param <T> The type of the renamed element.
 */
public abstract class RenameEObjectWrapper<T extends EObject> implements RenameElementWrapper {

    private static final Logger LOGGER = Logger.getLogger(RenameElementWrapper.class);
    private final String typeName;
    private final T element;
    
    /**
     * Constructs a new wrapper for EObjects.
     * @param typeName The type name of the element to be renamed.
     * @param elementToRename The element to be renamed.
     */
    protected RenameEObjectWrapper(String typeName, T elementToRename) {
        this.element = elementToRename;
        this.typeName = typeName;
    }
    
    /**
     * @return The element to be renamed.
     */
    protected T getElement() {
        return element;
    }

    @Override
    public String getElementToRenameTypeName() {
        return typeName;
    }
    
    @Override
    public String getElementName() {
        return LabelUtils.getItemProviderText(element);
    }

    /**
     * Saves the model that corresponds to the changed element.
     */
    public void persistChange() {
        try {
            element.eResource().save(null);
        } catch (IOException e) {
            LOGGER.error("Could not save new name!", e);
            e.printStackTrace();
        }
    }

}
