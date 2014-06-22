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
package org.splevo.vpm.realization.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.splevo.vpm.realization.RealizationPackage;
import org.splevo.vpm.realization.VariabilityMechanism;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variability Mechanism</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.vpm.realization.impl.VariabilityMechanismImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.splevo.vpm.realization.impl.VariabilityMechanismImpl#getRefactoringID <em>Refactoring ID</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariabilityMechanismImpl extends MinimalEObjectImpl.Container implements VariabilityMechanism {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getRefactoringID() <em>Refactoring ID</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRefactoringID()
     * @generated
     * @ordered
     */
    protected static final String REFACTORING_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRefactoringID() <em>Refactoring ID</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRefactoringID()
     * @generated
     * @ordered
     */
    protected String refactoringID = REFACTORING_ID_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected VariabilityMechanismImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RealizationPackage.Literals.VARIABILITY_MECHANISM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RealizationPackage.VARIABILITY_MECHANISM__NAME,
                    oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getRefactoringID() {
        return refactoringID;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRefactoringID(String newRefactoringID) {
        String oldRefactoringID = refactoringID;
        refactoringID = newRefactoringID;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    RealizationPackage.VARIABILITY_MECHANISM__REFACTORING_ID, oldRefactoringID, refactoringID));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case RealizationPackage.VARIABILITY_MECHANISM__NAME:
            return getName();
        case RealizationPackage.VARIABILITY_MECHANISM__REFACTORING_ID:
            return getRefactoringID();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case RealizationPackage.VARIABILITY_MECHANISM__NAME:
            setName((String) newValue);
            return;
        case RealizationPackage.VARIABILITY_MECHANISM__REFACTORING_ID:
            setRefactoringID((String) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case RealizationPackage.VARIABILITY_MECHANISM__NAME:
            setName(NAME_EDEFAULT);
            return;
        case RealizationPackage.VARIABILITY_MECHANISM__REFACTORING_ID:
            setRefactoringID(REFACTORING_ID_EDEFAULT);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case RealizationPackage.VARIABILITY_MECHANISM__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        case RealizationPackage.VARIABILITY_MECHANISM__REFACTORING_ID:
            return REFACTORING_ID_EDEFAULT == null ? refactoringID != null : !REFACTORING_ID_EDEFAULT
                    .equals(refactoringID);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: ");
        result.append(name);
        result.append(", refactoringID: ");
        result.append(refactoringID);
        result.append(')');
        return result.toString();
    }

} //VariabilityMechanismImpl
