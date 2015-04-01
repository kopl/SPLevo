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

import org.splevo.ui.commons.wizard.ChangeSingleAttributeElementWrapper;
import org.splevo.ui.commons.wizard.ChangeSingleAttributeWizard;
import org.splevo.ui.commons.wizard.StringValueConverter;

/**
 * Wizard for renaming an element.
 */
public class RenameElementWizard extends ChangeSingleAttributeWizard<String> {

    /**
     * Constructs a new renaming wizard.
     * @param elementToRename The element to be renamed.
     */
    public RenameElementWizard(ChangeSingleAttributeElementWrapper<String> elementToRename) {
        this(elementToRename, true);
    }

    /**
     * Constructs a new renaming wizard.
     * @param elementToRename The element to be renamed.
     * @param persistChange Indicates whether the change shall be persisted.
     */
    public RenameElementWizard(ChangeSingleAttributeElementWrapper<String> elementToRename, boolean persistChange) {
        super(elementToRename, "name", false, new StringValueConverter(), persistChange);
    }

}
