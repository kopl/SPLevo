/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Busch
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.commons.wizard;

import org.eclipse.jface.wizard.Wizard;
import org.splevo.ui.commons.wizard.ChangeSingleAttributeWizardPage.ValueConverter;

/**
 * This wizard allows changing a single attribute of an element.
 * 
 * @param <T>
 *            The type of the changed attribute.
 */
public class ChangeSingleAttributeWizard<T> extends Wizard {

    private final boolean persistChange;
    private final ChangeSingleAttributeWizardPage<T> page;
    private final ChangeSingleAttributeElementWrapper<T> elementToChange;

    /**
     * Instantiates a new wizard for changing an attribute of an element.
     * 
     * @param elementToChange
     *            The element to rename
     * @param attributeName
     *            The name of the attribute to be changed (necessary for the UI)
     * @param multilineEdit
     *            Indicates if the attribute shall be edited with a multiline text box. If false, a
     *            single line text box will be used.
     * @param valueConverter
     *            A converter between the attribute value and displayable text.
     * @param persistChange
     *            Indicates if the changes shall be persisted afterwards (true is default).
     */
    public ChangeSingleAttributeWizard(ChangeSingleAttributeElementWrapper<T> elementToChange, String attributeName,
            boolean multilineEdit, ValueConverter<T> valueConverter, boolean persistChange) {
        this.elementToChange = elementToChange;
        this.persistChange = persistChange;
        page = new ChangeSingleAttributeWizardPage<T>(elementToChange.getElementTypeName(), attributeName,
                multilineEdit, valueConverter, elementToChange.getAttributeValue());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        elementToChange.setAttributeValue(page.getNewElement());
        if (persistChange) {
            elementToChange.persistChange();
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        addPage(page);
    }

}
