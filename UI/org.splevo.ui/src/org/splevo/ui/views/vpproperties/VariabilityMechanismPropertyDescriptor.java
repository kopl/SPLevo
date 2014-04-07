/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.ui.views.vpproperties;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringRegistry;

import com.google.common.collect.Lists;

/**
 * A property descriptor to select variability mechanisms.
 */
public class VariabilityMechanismPropertyDescriptor extends PropertyDescriptor {

    private static Logger logger = Logger.getLogger(VariabilityMechanismPropertyDescriptor.class);

    private final List<VariabilityRefactoring> refactorings;

    /**
     * Constructor for the required identification in the properties view.
     *
     * @param id
     *            The id of the editor.
     * @param displayName
     *            The name to display to the user.
     */
    public VariabilityMechanismPropertyDescriptor(Object id, String displayName) {
        super(id, displayName);
        refactorings = Lists.newArrayList();
        refactorings.add(null);
        refactorings.addAll(VariabilityRefactoringRegistry.getRefactorings());
    }

    /**
     * The <code>VariabilityMechanismPropertyDescriptor</code> implementation of this
     * <code>IPropertyDescriptor</code> method creates and returns a new
     * <code>ComboBoxCellEditor</code>.
     * <p>
     * The editor is configured with the current validator if there is one.
     * </p>
     *
     * {@inheritDoc}
     */
    @Override
    public CellEditor createPropertyEditor(Composite parent) {
        ExtendedComboBoxCellEditor editor = new ExtendedComboBoxCellEditor(parent, refactorings, getLabelProvider(),
                true);
        if (getValidator() != null) {
            editor.setValidator(getValidator());
        }
        return editor;
    }

    @Override
    public ILabelProvider getLabelProvider() {
        return new LabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof VariabilityRefactoring) {
                    VariabilityRefactoring refactoring = (VariabilityRefactoring) element;
                    if (refactoring.getVariabilityMechanism() != null) {
                        return refactoring.getVariabilityMechanism().getName();
                    } else {
                        logger.warn(String.format("Refactoring without variability mechanism (id=%s, class=%s)",
                                refactoring.getId(), refactoring.getClass().getSimpleName()));
                    }
                }
                return super.getText(element);
            }
        };
    }

    @Override
    protected ICellEditorValidator getValidator() {
        return new ICellEditorValidator() {

            @Override
            public String isValid(Object value) {
                if (value == null || refactorings.contains(value)) {
                    return null;
                } else {
                    return String.format("%s is no available variability mechanism", value);
                }
            }
        };
    }

}
