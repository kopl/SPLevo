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
package org.splevo.ui.commons.wizard;

import org.apache.commons.lang.StringUtils;
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
 * This WizardPage allows for the input of a new attribute of an element.
 * 
 * @param <T>
 *            The type of the attribute to be changed.
 */
public class ChangeSingleAttributeWizardPage<T> extends WizardPage {

    /**
     * A converter between a type to be specified and a string. This class is used to convert
     * attribute values to strings in order to display them in an dialog. It also parses a string
     * into the wanted type again after editing.
     * 
     * @param <T>
     *            The type of an element to be converted.
     */
    public interface ValueConverter<T> {

        /**
         * Convert a string into a given type.
         * 
         * @param str
         *            The string to use as input for the conversion.
         * @return The representation of the given string in the target type.
         */
        public T convertFromString(String str);

        /**
         * Converts a given type to a string.
         * 
         * @param t
         *            The element to be converted.
         * @return The string representation of the given element.
         */
        public String convertFromType(T t);

        /**
         * Checks whether a given element is valid. The validation can ensure customizable semantic
         * constraints.
         * 
         * @param t
         *            The element to check.
         * @return True if the element is valid, False otherwise.
         */
        public boolean isValid(T t);
    }

    private final ValueConverter<T> converter;
    private final String attributeName;
    private Text newElementTextField;
    private T oldElement;
    private final boolean multilineEditControl;

    /**
     * Instantiates a new single attribute editing wizard page.
     * 
     * @param elementTypeName
     *            The name of the element's type.
     * @param attributeName
     *            The name of the attribute to be changed.
     * @param multilineEdit
     *            Indicates if a multiline text edit control shall be used for the edit operation.
     *            False means that a single line edit control ist used.
     * @param converter
     *            Converter for the attributes value to string and vice versa.
     * @param oldElement
     *            the old attribut's value.
     */
    public ChangeSingleAttributeWizardPage(final String elementTypeName, final String attributeName,
            final boolean multilineEdit, final ValueConverter<T> converter, T oldElement) {
        super(String.format("%s %s editing page", elementTypeName, attributeName));
        setTitle(String.format("%s %s Editing", elementTypeName, StringUtils.capitalize(attributeName)));
        setDescription(String.format("Enter the new %s for the %s.", attributeName, elementTypeName));
        this.oldElement = oldElement;
        this.converter = converter;
        this.attributeName = attributeName;
        this.multilineEditControl = multilineEdit;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);

        int labelGridData = 0;
        int editBoxGridData = GridData.FILL_HORIZONTAL;
        int editBoxStyle = SWT.SINGLE;
        if (multilineEditControl) {
            editBoxStyle = SWT.MULTI;
            editBoxGridData = GridData.FILL_BOTH;
            labelGridData = GridData.VERTICAL_ALIGN_BEGINNING;
        }

        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        container.setLayout(layout);
        container.setLayoutData(new GridData(editBoxGridData));

        Label nameLabel = new Label(container, SWT.TOP);
        nameLabel.setLayoutData(new GridData(labelGridData));
        nameLabel.setText(String.format("New %s: ", StringUtils.capitalize(attributeName)));
        nameLabel.setFont(parent.getFont());

        newElementTextField = new Text(container, SWT.BORDER | editBoxStyle);
        GridData data = new GridData(editBoxGridData);
        newElementTextField.setLayoutData(data);
        newElementTextField.setFont(parent.getFont());

        newElementTextField.setText(converter.convertFromType(oldElement));

        newElementTextField.addModifyListener(new ModifyListener() {

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
    public T getNewElement() {
        return converter.convertFromString(newElementTextField.getText());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            newElementTextField.setFocus();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
     */
    @Override
    public boolean isPageComplete() {
        T element = converter.convertFromString(newElementTextField.getText());
        return element != null && converter.isValid(element);
    }
}
