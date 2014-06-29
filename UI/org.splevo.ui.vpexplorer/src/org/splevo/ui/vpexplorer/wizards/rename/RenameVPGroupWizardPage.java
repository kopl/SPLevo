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

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * This WizardPage allows for the input of a new ID of a Variation Point Group.
 */
public class RenameVPGroupWizardPage extends WizardPage {
    

    /** The new ID text field. */
    private Text newIdTextField;
    
    /** The group id. */
    private String groupID;
    
    
    /**
     * Instantiates a new rename vp group wizard page.
     *
     * @param groupID the group id to be changed.
     */
    protected RenameVPGroupWizardPage(String groupID) {
        super("VP Group rename page");
        setTitle("Variation Point Renaming");
        setDescription("Enter the new name for the Variation Point Group.");
        this.groupID = groupID;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;       
        container.setLayout(layout);
        container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        Label nameLabel = new Label(container, SWT.NONE);
        nameLabel.setText("Group ID: ");
        nameLabel.setFont(parent.getFont());
        
        newIdTextField = new Text(container, SWT.BORDER | SWT.SINGLE);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        newIdTextField.setLayoutData(data);
        newIdTextField.setFont(parent.getFont());
        
        newIdTextField.setText(groupID);
        
        newIdTextField.addModifyListener(new ModifyListener() {
            
            @Override
            public void modifyText(ModifyEvent e) {
                setPageComplete(isPageComplete());
            }
        });
        
        setControl(container);
        Dialog.applyDialogFont(container);
    }

    
    /**
     * Gets the new name the user specified for the VariationPointGroup.
     *
     * @return the new name
     */
    public String getNewName() {
        return newIdTextField.getText();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            newIdTextField.setFocus();
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
     */
    @Override
    public boolean isPageComplete() {
        return !newIdTextField.getText().equals("");
    }
}
