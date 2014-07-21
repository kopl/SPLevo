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

import org.eclipse.emf.compare.ComparePackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.splevo.diffing.splevodiff.SPLevoDiffFactory
 * @model kind="package"
 * @generated
 */
public interface SPLevoDiffPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "splevodiff";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.splevo.org/diff/1.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "splevodiff";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    SPLevoDiffPackage eINSTANCE = org.splevo.diffing.splevodiff.impl.SPLevoDiffPackageImpl.init();

    /**
     * The meta object id for the '{@link org.splevo.diffing.splevodiff.impl.SPLevoDiffImpl <em>SP Levo Diff</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.splevodiff.impl.SPLevoDiffImpl
     * @see org.splevo.diffing.splevodiff.impl.SPLevoDiffPackageImpl#getSPLevoDiff()
     * @generated
     */
    int SP_LEVO_DIFF = 0;

    /**
     * The feature id for the '<em><b>Match</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SP_LEVO_DIFF__MATCH = ComparePackage.DIFF__MATCH;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SP_LEVO_DIFF__REQUIRES = ComparePackage.DIFF__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SP_LEVO_DIFF__REQUIRED_BY = ComparePackage.DIFF__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SP_LEVO_DIFF__REFINES = ComparePackage.DIFF__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SP_LEVO_DIFF__REFINED_BY = ComparePackage.DIFF__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SP_LEVO_DIFF__KIND = ComparePackage.DIFF__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SP_LEVO_DIFF__SOURCE = ComparePackage.DIFF__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SP_LEVO_DIFF__STATE = ComparePackage.DIFF__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SP_LEVO_DIFF__EQUIVALENCE = ComparePackage.DIFF__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SP_LEVO_DIFF__CONFLICT = ComparePackage.DIFF__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SP_LEVO_DIFF__CHANGED_ELEMENT = ComparePackage.DIFF_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>SP Levo Diff</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SP_LEVO_DIFF_FEATURE_COUNT = ComparePackage.DIFF_FEATURE_COUNT + 1;

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.splevodiff.SPLevoDiff <em>SP Levo Diff</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>SP Levo Diff</em>'.
     * @see org.splevo.diffing.splevodiff.SPLevoDiff
     * @generated
     */
    EClass getSPLevoDiff();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.splevodiff.SPLevoDiff#getChangedElement <em>Changed Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Element</em>'.
     * @see org.splevo.diffing.splevodiff.SPLevoDiff#getChangedElement()
     * @see #getSPLevoDiff()
     * @generated
     */
    EReference getSPLevoDiff_ChangedElement();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    SPLevoDiffFactory getSPLevoDiffFactory();

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
         * The meta object literal for the '{@link org.splevo.diffing.splevodiff.impl.SPLevoDiffImpl <em>SP Levo Diff</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.splevodiff.impl.SPLevoDiffImpl
         * @see org.splevo.diffing.splevodiff.impl.SPLevoDiffPackageImpl#getSPLevoDiff()
         * @generated
         */
        EClass SP_LEVO_DIFF = eINSTANCE.getSPLevoDiff();

        /**
         * The meta object literal for the '<em><b>Changed Element</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SP_LEVO_DIFF__CHANGED_ELEMENT = eINSTANCE.getSPLevoDiff_ChangedElement();

    }

} //SPLevoDiffPackage
