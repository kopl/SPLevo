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
 *******************************************************************************/
package org.splevo.ui.vpexplorer.wizards.rename;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.jface.wizard.Wizard;
import org.splevo.vpm.variability.VariationPointGroup;

/**
 * This wizard allows the renaming of a VarionPointGroup.
 */
public class RenameVPGroupWizard extends Wizard {

    private static Logger logger = Logger.getLogger(RenameVPGroupWizard.class);

    private RenameVPGroupWizardPage page;

    private VariationPointGroup groupToRename;

    /**
     * Instantiates a new RenameVPGroupWizard.
     * 
     * @param groupToRename
     *            the group to rename
     */
    public RenameVPGroupWizard(VariationPointGroup groupToRename) {
        this.groupToRename = groupToRename;
        page = new RenameVPGroupWizardPage(groupToRename.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        assert (!page.getNewName().equals(""));
        
        groupToRename.setId(page.getNewName());
        
        try {
            groupToRename.eResource().save(null);
        } catch (IOException e) {
            logger.error("Could not save new group name!", e);
            e.printStackTrace();
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

    /**
     * Gets the groups new name.
     *
     * @return the groups new name.
     */
    public String getNewGroupName() {
        if (page != null) {
            return page.getNewName();
        } else {
            return null;
        }
    }
}
