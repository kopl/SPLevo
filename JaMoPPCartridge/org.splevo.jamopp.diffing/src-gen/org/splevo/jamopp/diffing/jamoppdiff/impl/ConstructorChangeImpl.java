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
import org.emftext.language.java.members.Constructor;
import org.splevo.jamopp.diffing.jamoppdiff.ConstructorChange;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constructor Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.impl.ConstructorChangeImpl#getChangedConstructor <em>Changed Constructor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstructorChangeImpl extends JaMoPPDiffImpl implements ConstructorChange {
    /**
     * The cached value of the '{@link #getChangedConstructor() <em>Changed Constructor</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChangedConstructor()
     * @generated
     * @ordered
     */
    protected Constructor changedConstructor;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ConstructorChangeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return JaMoPPDiffPackage.Literals.CONSTRUCTOR_CHANGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Constructor getChangedConstructor() {
        if (changedConstructor != null && changedConstructor.eIsProxy()) {
            InternalEObject oldChangedConstructor = (InternalEObject) changedConstructor;
            changedConstructor = (Constructor) eResolveProxy(oldChangedConstructor);
            if (changedConstructor != oldChangedConstructor) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            JaMoPPDiffPackage.CONSTRUCTOR_CHANGE__CHANGED_CONSTRUCTOR, oldChangedConstructor,
                            changedConstructor));
            }
        }
        return changedConstructor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Constructor basicGetChangedConstructor() {
        return changedConstructor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChangedConstructor(Constructor newChangedConstructor) {
        Constructor oldChangedConstructor = changedConstructor;
        changedConstructor = newChangedConstructor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    JaMoPPDiffPackage.CONSTRUCTOR_CHANGE__CHANGED_CONSTRUCTOR, oldChangedConstructor,
                    changedConstructor));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case JaMoPPDiffPackage.CONSTRUCTOR_CHANGE__CHANGED_CONSTRUCTOR:
            if (resolve)
                return getChangedConstructor();
            return basicGetChangedConstructor();
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
        case JaMoPPDiffPackage.CONSTRUCTOR_CHANGE__CHANGED_CONSTRUCTOR:
            setChangedConstructor((Constructor) newValue);
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
        case JaMoPPDiffPackage.CONSTRUCTOR_CHANGE__CHANGED_CONSTRUCTOR:
            setChangedConstructor((Constructor) null);
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
        case JaMoPPDiffPackage.CONSTRUCTOR_CHANGE__CHANGED_CONSTRUCTOR:
            return changedConstructor != null;
        }
        return super.eIsSet(featureID);
    }

} //ConstructorChangeImpl
