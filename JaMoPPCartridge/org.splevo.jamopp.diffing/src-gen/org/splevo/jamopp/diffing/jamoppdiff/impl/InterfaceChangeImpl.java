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
import org.emftext.language.java.classifiers.Interface;
import org.splevo.jamopp.diffing.jamoppdiff.InterfaceChange;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interface Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.impl.InterfaceChangeImpl#getChangedInterface <em>Changed Interface</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InterfaceChangeImpl extends JaMoPPDiffImpl implements InterfaceChange {
    /**
     * The cached value of the '{@link #getChangedInterface() <em>Changed Interface</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChangedInterface()
     * @generated
     * @ordered
     */
    protected Interface changedInterface;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected InterfaceChangeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return JaMoPPDiffPackage.Literals.INTERFACE_CHANGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Interface getChangedInterface() {
        if (changedInterface != null && changedInterface.eIsProxy()) {
            InternalEObject oldChangedInterface = (InternalEObject) changedInterface;
            changedInterface = (Interface) eResolveProxy(oldChangedInterface);
            if (changedInterface != oldChangedInterface) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            JaMoPPDiffPackage.INTERFACE_CHANGE__CHANGED_INTERFACE, oldChangedInterface,
                            changedInterface));
            }
        }
        return changedInterface;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Interface basicGetChangedInterface() {
        return changedInterface;
    }

    /**
     * <!-- begin-user-doc -->
     * {@inheritDoc}
     * <!-- end-user-doc -->
     * @generated not
     */
    public void setChangedInterface(Interface newChangedInterface) {

        // adapted to set changed element in the background
        setChangedElement(newChangedInterface);
        // end of custom code

        Interface oldChangedInterface = changedInterface;
        changedInterface = newChangedInterface;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    JaMoPPDiffPackage.INTERFACE_CHANGE__CHANGED_INTERFACE, oldChangedInterface, changedInterface));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case JaMoPPDiffPackage.INTERFACE_CHANGE__CHANGED_INTERFACE:
            if (resolve)
                return getChangedInterface();
            return basicGetChangedInterface();
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
        case JaMoPPDiffPackage.INTERFACE_CHANGE__CHANGED_INTERFACE:
            setChangedInterface((Interface) newValue);
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
        case JaMoPPDiffPackage.INTERFACE_CHANGE__CHANGED_INTERFACE:
            setChangedInterface((Interface) null);
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
        case JaMoPPDiffPackage.INTERFACE_CHANGE__CHANGED_INTERFACE:
            return changedInterface != null;
        }
        return super.eIsSet(featureID);
    }

} //InterfaceChangeImpl
