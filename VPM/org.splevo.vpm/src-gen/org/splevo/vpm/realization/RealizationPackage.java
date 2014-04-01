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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.splevo.vpm.realization.RealizationFactory
 * @model kind="package"
 * @generated
 */
public interface RealizationPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "realization";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://splevo.org/vpm/1.0/realization";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "realization";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    RealizationPackage eINSTANCE = org.splevo.vpm.realization.impl.RealizationPackageImpl.init();

    /**
     * The meta object id for the '{@link org.splevo.vpm.realization.impl.VariabilityMechanismImpl <em>Variability Mechanism</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.vpm.realization.impl.VariabilityMechanismImpl
     * @see org.splevo.vpm.realization.impl.RealizationPackageImpl#getVariabilityMechanism()
     * @generated
     */
    int VARIABILITY_MECHANISM = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VARIABILITY_MECHANISM__NAME = 0;

    /**
     * The feature id for the '<em><b>Refactoring ID</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VARIABILITY_MECHANISM__REFACTORING_ID = 1;

    /**
     * The number of structural features of the '<em>Variability Mechanism</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VARIABILITY_MECHANISM_FEATURE_COUNT = 2;

    /**
     * Returns the meta object for class '{@link org.splevo.vpm.realization.VariabilityMechanism <em>Variability Mechanism</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Variability Mechanism</em>'.
     * @see org.splevo.vpm.realization.VariabilityMechanism
     * @generated
     */
    EClass getVariabilityMechanism();

    /**
     * Returns the meta object for the attribute '{@link org.splevo.vpm.realization.VariabilityMechanism#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.splevo.vpm.realization.VariabilityMechanism#getName()
     * @see #getVariabilityMechanism()
     * @generated
     */
    EAttribute getVariabilityMechanism_Name();

    /**
     * Returns the meta object for the attribute '{@link org.splevo.vpm.realization.VariabilityMechanism#getRefactoringID <em>Refactoring ID</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Refactoring ID</em>'.
     * @see org.splevo.vpm.realization.VariabilityMechanism#getRefactoringID()
     * @see #getVariabilityMechanism()
     * @generated
     */
    EAttribute getVariabilityMechanism_RefactoringID();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    RealizationFactory getRealizationFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link org.splevo.vpm.realization.impl.VariabilityMechanismImpl <em>Variability Mechanism</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.vpm.realization.impl.VariabilityMechanismImpl
         * @see org.splevo.vpm.realization.impl.RealizationPackageImpl#getVariabilityMechanism()
         * @generated
         */
        EClass VARIABILITY_MECHANISM = eINSTANCE.getVariabilityMechanism();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute VARIABILITY_MECHANISM__NAME = eINSTANCE.getVariabilityMechanism_Name();

        /**
         * The meta object literal for the '<em><b>Refactoring ID</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute VARIABILITY_MECHANISM__REFACTORING_ID = eINSTANCE.getVariabilityMechanism_RefactoringID();

    }

} //RealizationPackage
