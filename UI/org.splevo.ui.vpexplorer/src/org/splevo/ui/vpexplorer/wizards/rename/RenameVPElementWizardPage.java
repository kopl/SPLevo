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
 * This WizardPage allows for the input of a new name of a Variation Point element.
 */
public class RenameVPElementWizardPage extends WizardPage {
    

    /** The new ID text field. */
    private Text newNameTextField;
    
    /** The group id. */
    private String oldName;
    
    
    /**
     * Instantiates a new rename vp group wizard page.
     * 
     * @param elementTypeName The name of the element's type.
     * @param oldName the group id to be changed.
     */
    protected RenameVPElementWizardPage(final String elementTypeName, String oldName) {
        super(String.format("%s rename page", elementTypeName));
        setTitle(String.format("%s Renaming", elementTypeName));
        setDescription(String.format("Enter the new name for the %s.", elementTypeName));
        this.oldName = oldName;
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
        nameLabel.setText("New name: ");
        nameLabel.setFont(parent.getFont());
        
        newNameTextField = new Text(container, SWT.BORDER | SWT.SINGLE);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        newNameTextField.setLayoutData(data);
        newNameTextField.setFont(parent.getFont());
        
        newNameTextField.setText(oldName);
        
        newNameTextField.addModifyListener(new ModifyListener() {
            
            @Override
            public void modifyText(ModifyEvent e) {
                setPageComplete(isPageComplete());
            }
        });
        
        setControl(container);
        Dialog.applyDialogFont(container);
    }

    
    /**
     * Gets the new name the user specified for the variation point model element.
     *
     * @return the new name
     */
    public String getNewName() {
        return newNameTextField.getText();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            newNameTextField.setFocus();
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
     */
    @Override
    public boolean isPageComplete() {
        return !newNameTextField.getText().equals("");
    }
}
