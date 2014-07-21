/**
 * Copyright (c) 2014
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Benjamin Klatt - initial API and implementation and/or initial documentation
 */
package org.splevo.diffing.splevodiff;

import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SP Levo Diff</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A difference between software models.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.splevodiff.SPLevoDiff#getChangedElement <em>Changed Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.splevodiff.SPLevoDiffPackage#getSPLevoDiff()
 * @model
 * @generated
 */
public interface SPLevoDiff extends Diff {
    /**
     * Returns the value of the '<em><b>Changed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The changed element that exist in only one of the software models.
     * If an element exists in the leading copy, this referenced.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Changed Element</em>' reference.
     * @see #setChangedElement(EObject)
     * @see org.splevo.diffing.splevodiff.SPLevoDiffPackage#getSPLevoDiff_ChangedElement()
     * @model required="true"
     * @generated
     */
    EObject getChangedElement();

    /**
     * Sets the value of the '{@link org.splevo.diffing.splevodiff.SPLevoDiff#getChangedElement <em>Changed Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Changed Element</em>' reference.
     * @see #getChangedElement()
     * @generated
     */
    void setChangedElement(EObject value);

} // SPLevoDiff
