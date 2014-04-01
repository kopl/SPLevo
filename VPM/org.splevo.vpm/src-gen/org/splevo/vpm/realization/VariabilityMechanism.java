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
package org.splevo.vpm.realization;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variability Mechanism</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A variability realization mechanism that is specified by a refactoring (identified by the refactoring id).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.vpm.realization.VariabilityMechanism#getName <em>Name</em>}</li>
 *   <li>{@link org.splevo.vpm.realization.VariabilityMechanism#getRefactoringID <em>Refactoring ID</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.vpm.realization.RealizationPackage#getVariabilityMechanism()
 * @model
 * @generated
 */
public interface VariabilityMechanism extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A name identifying the variability mechanism. Typically aligned with / specified by the refactoring referenced by the refactoring id.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.splevo.vpm.realization.RealizationPackage#getVariabilityMechanism_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.splevo.vpm.realization.VariabilityMechanism#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Refactoring ID</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The id of the refactoring to be used to implement this mechanims.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Refactoring ID</em>' attribute.
     * @see #setRefactoringID(String)
     * @see org.splevo.vpm.realization.RealizationPackage#getVariabilityMechanism_RefactoringID()
     * @model
     * @generated
     */
    String getRefactoringID();

    /**
     * Sets the value of the '{@link org.splevo.vpm.realization.VariabilityMechanism#getRefactoringID <em>Refactoring ID</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Refactoring ID</em>' attribute.
     * @see #getRefactoringID()
     * @generated
     */
    void setRefactoringID(String value);

} // VariabilityMechanism
