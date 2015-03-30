/**
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Benjamin Klatt
 */
package org.splevo.vpm.variability;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Customizable Description Having</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.splevo.vpm.variability.CustomizableDescriptionHaving#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @see org.splevo.vpm.variability.variabilityPackage#getCustomizableDescriptionHaving()
 * @model abstract="true"
 * @generated
 */
public interface CustomizableDescriptionHaving extends EObject {
    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The customizable description of the element.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.splevo.vpm.variability.variabilityPackage#getCustomizableDescriptionHaving_Description()
     * @model default=""
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link org.splevo.vpm.variability.CustomizableDescriptionHaving#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

} // CustomizableDescriptionHaving
