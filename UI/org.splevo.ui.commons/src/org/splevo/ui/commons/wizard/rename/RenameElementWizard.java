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
package org.splevo.ui.commons.wizard.rename;

import org.eclipse.jface.wizard.Wizard;

/**
 * This wizard allows the renaming of an element.
 */
public class RenameElementWizard extends Wizard {

    private final boolean persistChange;
    private final RenameElementWizardPage page;
    private final RenameElementWrapper elementToRename;

    /**
     * Instantiates a new wizard for renaming an element.
     * 
     * @param elementToRename
     *            The element to rename
     */
    public RenameElementWizard(RenameElementWrapper elementToRename) {
        this(elementToRename, true);
    }
    
    /**
     * Instantiates a new wizard for renaming an element.
     * 
     * @param elementToRename
     *            The element to rename
     * @param persistChange
     *            Indicates if the changes shall be persisted afterwards (true is default).
     */
    public RenameElementWizard(RenameElementWrapper elementToRename, boolean persistChange) {
        this.elementToRename = elementToRename;
        this.persistChange = persistChange;
        page = new RenameElementWizardPage(elementToRename.getElementToRenameTypeName(),
                elementToRename.getElementName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        assert (!page.getNewName().equals(""));

        elementToRename.setElementName(page.getNewName());
        if (persistChange) {
            elementToRename.persistChange();
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
