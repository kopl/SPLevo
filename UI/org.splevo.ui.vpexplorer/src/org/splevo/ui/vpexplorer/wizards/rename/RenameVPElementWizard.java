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
package org.splevo.ui.vpexplorer.wizards.rename;

import org.eclipse.jface.wizard.Wizard;

/**
 * This wizard allows the renaming of a variation point model element.
 */
public class RenameVPElementWizard extends Wizard {

    private final RenameVPElementWizardPage page;
    private final RenameVPElementWrapper<?> elementToRename;

    /**
     * Instantiates a new RenameVPElementWizard.
     * 
     * @param elementToRename
     *            the element to rename
     */
    public RenameVPElementWizard(RenameVPElementWrapper<?> elementToRename) {
        this.elementToRename = elementToRename;
        page = new RenameVPElementWizardPage(elementToRename.getElementToRenameTypeName(), elementToRename.getElementName());
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
        elementToRename.saveCorrespondingModel();
        
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

//    /**
//     * Gets the groups new name.
//     *
//     * @return the groups new name.
//     */
//    public String getNewGroupName() {
//        if (page != null) {
//            return page.getNewName();
//        } else {
//            return null;
//        }
//    }
}
