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
package org.splevo.ui.commons.wizard.description;

import org.splevo.ui.commons.wizard.ChangeSingleAttributeElementWrapper;
import org.splevo.ui.commons.wizard.ChangeSingleAttributeWizard;
import org.splevo.ui.commons.wizard.StringValueConverter;

/**
 * Wizard for editing a customizable description.
 */
public class EditCustomizableDescriptionWizard extends ChangeSingleAttributeWizard<String> {

    /**
     * Constructs a new description editing wizard.
     * @param elementToChange The element whichs description shall be changed.
     */
    public EditCustomizableDescriptionWizard(ChangeSingleAttributeElementWrapper<String> elementToChange) {
        this(elementToChange, true);
    }
    
    /**
     * Constructs a new description editing wizard.
     * @param elementToChange The element whichs description shall be changed.
     * @param persistChange Indicates whether the change shall be persisted.
     */
    public EditCustomizableDescriptionWizard(ChangeSingleAttributeElementWrapper<String> elementToChange, boolean persistChange) {
        super(elementToChange, "description", true, new StringValueConverter(), persistChange);
    }

}
