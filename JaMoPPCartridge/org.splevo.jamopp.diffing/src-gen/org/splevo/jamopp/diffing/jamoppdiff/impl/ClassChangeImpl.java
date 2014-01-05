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
package org.splevo.jamopp.diffing.jamoppdiff.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.splevo.jamopp.diffing.jamoppdiff.ClassChange;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.impl.ClassChangeImpl#getChangedClass <em>Changed Class</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassChangeImpl extends JaMoPPDiffImpl implements ClassChange {
    /**
     * The cached value of the '{@link #getChangedClass() <em>Changed Class</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChangedClass()
     * @generated
     * @ordered
     */
    protected org.emftext.language.java.classifiers.Class changedClass;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ClassChangeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return JaMoPPDiffPackage.Literals.CLASS_CHANGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public org.emftext.language.java.classifiers.Class getChangedClass() {
        if (changedClass != null && changedClass.eIsProxy()) {
            InternalEObject oldChangedClass = (InternalEObject) changedClass;
            changedClass = (org.emftext.language.java.classifiers.Class) eResolveProxy(oldChangedClass);
            if (changedClass != oldChangedClass) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            JaMoPPDiffPackage.CLASS_CHANGE__CHANGED_CLASS, oldChangedClass, changedClass));
            }
        }
        return changedClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public org.emftext.language.java.classifiers.Class basicGetChangedClass() {
        return changedClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChangedClass(org.emftext.language.java.classifiers.Class newChangedClass) {
        org.emftext.language.java.classifiers.Class oldChangedClass = changedClass;
        changedClass = newChangedClass;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JaMoPPDiffPackage.CLASS_CHANGE__CHANGED_CLASS,
                    oldChangedClass, changedClass));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case JaMoPPDiffPackage.CLASS_CHANGE__CHANGED_CLASS:
            if (resolve)
                return getChangedClass();
            return basicGetChangedClass();
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
        case JaMoPPDiffPackage.CLASS_CHANGE__CHANGED_CLASS:
            setChangedClass((org.emftext.language.java.classifiers.Class) newValue);
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
        case JaMoPPDiffPackage.CLASS_CHANGE__CHANGED_CLASS:
            setChangedClass((org.emftext.language.java.classifiers.Class) null);
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
        case JaMoPPDiffPackage.CLASS_CHANGE__CHANGED_CLASS:
            return changedClass != null;
        }
        return super.eIsSet(featureID);
    }

} //ClassChangeImpl
