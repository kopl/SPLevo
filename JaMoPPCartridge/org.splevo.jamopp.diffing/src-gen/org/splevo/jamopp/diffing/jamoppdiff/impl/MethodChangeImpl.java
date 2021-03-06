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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.emftext.language.java.members.Method;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage;
import org.splevo.jamopp.diffing.jamoppdiff.MethodChange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.impl.MethodChangeImpl#getChangedMethod <em>Changed Method</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodChangeImpl extends JaMoPPDiffImpl implements MethodChange {
    /**
     * The cached value of the '{@link #getChangedMethod() <em>Changed Method</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChangedMethod()
     * @generated
     * @ordered
     */
    protected Method changedMethod;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MethodChangeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return JaMoPPDiffPackage.Literals.METHOD_CHANGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Method getChangedMethod() {
        if (changedMethod != null && changedMethod.eIsProxy()) {
            InternalEObject oldChangedMethod = (InternalEObject) changedMethod;
            changedMethod = (Method) eResolveProxy(oldChangedMethod);
            if (changedMethod != oldChangedMethod) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            JaMoPPDiffPackage.METHOD_CHANGE__CHANGED_METHOD, oldChangedMethod, changedMethod));
            }
        }
        return changedMethod;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Method basicGetChangedMethod() {
        return changedMethod;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChangedMethod(Method newChangedMethod) {
        Method oldChangedMethod = changedMethod;
        changedMethod = newChangedMethod;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JaMoPPDiffPackage.METHOD_CHANGE__CHANGED_METHOD,
                    oldChangedMethod, changedMethod));
    }

    @Override
    public EObject basicGetChangedElement() {
        return basicGetChangedMethod();
    }

    @Override
    public void setChangedElement(EObject newChangedElement) {
        if(newChangedElement == null) {
            setChangedMethod(null);
        } else if(newChangedElement instanceof Method) {
            setChangedMethod((Method) newChangedElement);
        } else {
            throw new IllegalArgumentException("Tried to set invalid class type: " + newChangedElement.getClass().getSimpleName());
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case JaMoPPDiffPackage.METHOD_CHANGE__CHANGED_METHOD:
            if (resolve)
                return getChangedMethod();
            return basicGetChangedMethod();
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
        case JaMoPPDiffPackage.METHOD_CHANGE__CHANGED_METHOD:
            setChangedMethod((Method) newValue);
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
        case JaMoPPDiffPackage.METHOD_CHANGE__CHANGED_METHOD:
            setChangedMethod((Method) null);
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
        case JaMoPPDiffPackage.METHOD_CHANGE__CHANGED_METHOD:
            return changedMethod != null;
        }
        return super.eIsSet(featureID);
    }

} //MethodChangeImpl
