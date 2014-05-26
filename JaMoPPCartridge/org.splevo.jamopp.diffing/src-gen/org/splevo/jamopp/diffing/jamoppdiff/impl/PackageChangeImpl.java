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
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage;
import org.splevo.jamopp.diffing.jamoppdiff.PackageChange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.impl.PackageChangeImpl#getChangedPackage <em>Changed Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageChangeImpl extends JaMoPPDiffImpl implements PackageChange {
    /**
     * The cached value of the '{@link #getChangedPackage() <em>Changed Package</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChangedPackage()
     * @generated
     * @ordered
     */
    protected org.emftext.language.java.containers.Package changedPackage;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PackageChangeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return JaMoPPDiffPackage.Literals.PACKAGE_CHANGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public org.emftext.language.java.containers.Package getChangedPackage() {
        if (changedPackage != null && changedPackage.eIsProxy()) {
            InternalEObject oldChangedPackage = (InternalEObject) changedPackage;
            changedPackage = (org.emftext.language.java.containers.Package) eResolveProxy(oldChangedPackage);
            if (changedPackage != oldChangedPackage) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            JaMoPPDiffPackage.PACKAGE_CHANGE__CHANGED_PACKAGE, oldChangedPackage, changedPackage));
            }
        }
        return changedPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public org.emftext.language.java.containers.Package basicGetChangedPackage() {
        return changedPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChangedPackage(org.emftext.language.java.containers.Package newChangedPackage) {
        org.emftext.language.java.containers.Package oldChangedPackage = changedPackage;
        changedPackage = newChangedPackage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JaMoPPDiffPackage.PACKAGE_CHANGE__CHANGED_PACKAGE,
                    oldChangedPackage, changedPackage));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case JaMoPPDiffPackage.PACKAGE_CHANGE__CHANGED_PACKAGE:
            if (resolve)
                return getChangedPackage();
            return basicGetChangedPackage();
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
        case JaMoPPDiffPackage.PACKAGE_CHANGE__CHANGED_PACKAGE:
            setChangedPackage((org.emftext.language.java.containers.Package) newValue);
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
        case JaMoPPDiffPackage.PACKAGE_CHANGE__CHANGED_PACKAGE:
            setChangedPackage((org.emftext.language.java.containers.Package) null);
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
        case JaMoPPDiffPackage.PACKAGE_CHANGE__CHANGED_PACKAGE:
            return changedPackage != null;
        }
        return super.eIsSet(featureID);
    }

} //PackageChangeImpl
