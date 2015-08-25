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
package org.splevo.project.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.splevo.project.ProjectPackage;
import org.splevo.project.VPMModelReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>VPM Model Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.project.impl.VPMModelReferenceImpl#getPath <em>Path</em>}</li>
 *   <li>{@link org.splevo.project.impl.VPMModelReferenceImpl#isRefactoringStarted <em>Refactoring Started</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VPMModelReferenceImpl extends EObjectImpl implements VPMModelReference {
    /**
     * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPath()
     * @generated
     * @ordered
     */
    protected static final String PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPath()
     * @generated
     * @ordered
     */
    protected String path = PATH_EDEFAULT;

    /**
     * The default value of the '{@link #isRefactoringStarted() <em>Refactoring Started</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isRefactoringStarted()
     * @generated
     * @ordered
     */
    protected static final boolean REFACTORING_STARTED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isRefactoringStarted() <em>Refactoring Started</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isRefactoringStarted()
     * @generated
     * @ordered
     */
    protected boolean refactoringStarted = REFACTORING_STARTED_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected VPMModelReferenceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ProjectPackage.Literals.VPM_MODEL_REFERENCE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPath() {
        return path;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPath(String newPath) {
        String oldPath = path;
        path = newPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.VPM_MODEL_REFERENCE__PATH, oldPath, path));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isRefactoringStarted() {
        return refactoringStarted;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRefactoringStarted(boolean newRefactoringStarted) {
        boolean oldRefactoringStarted = refactoringStarted;
        refactoringStarted = newRefactoringStarted;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.VPM_MODEL_REFERENCE__REFACTORING_STARTED, oldRefactoringStarted, refactoringStarted));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ProjectPackage.VPM_MODEL_REFERENCE__PATH:
                return getPath();
            case ProjectPackage.VPM_MODEL_REFERENCE__REFACTORING_STARTED:
                return isRefactoringStarted();
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
            case ProjectPackage.VPM_MODEL_REFERENCE__PATH:
                setPath((String)newValue);
                return;
            case ProjectPackage.VPM_MODEL_REFERENCE__REFACTORING_STARTED:
                setRefactoringStarted((Boolean)newValue);
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
            case ProjectPackage.VPM_MODEL_REFERENCE__PATH:
                setPath(PATH_EDEFAULT);
                return;
            case ProjectPackage.VPM_MODEL_REFERENCE__REFACTORING_STARTED:
                setRefactoringStarted(REFACTORING_STARTED_EDEFAULT);
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
            case ProjectPackage.VPM_MODEL_REFERENCE__PATH:
                return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
            case ProjectPackage.VPM_MODEL_REFERENCE__REFACTORING_STARTED:
                return refactoringStarted != REFACTORING_STARTED_EDEFAULT;
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
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (path: ");
        result.append(path);
        result.append(", refactoringStarted: ");
        result.append(refactoringStarted);
        result.append(')');
        return result.toString();
    }

} //VPMModelReferenceImpl
