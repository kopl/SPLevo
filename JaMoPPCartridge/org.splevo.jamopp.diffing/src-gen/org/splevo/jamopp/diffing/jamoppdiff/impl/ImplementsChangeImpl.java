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
import org.emftext.language.java.types.TypeReference;
import org.splevo.jamopp.diffing.jamoppdiff.ImplementsChange;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Implements Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.impl.ImplementsChangeImpl#getChangedReference <em>Changed Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImplementsChangeImpl extends JaMoPPDiffImpl implements ImplementsChange {
    /**
     * The cached value of the '{@link #getChangedReference() <em>Changed Reference</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChangedReference()
     * @generated
     * @ordered
     */
    protected TypeReference changedReference;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ImplementsChangeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return JaMoPPDiffPackage.Literals.IMPLEMENTS_CHANGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TypeReference getChangedReference() {
        if (changedReference != null && changedReference.eIsProxy()) {
            InternalEObject oldChangedReference = (InternalEObject) changedReference;
            changedReference = (TypeReference) eResolveProxy(oldChangedReference);
            if (changedReference != oldChangedReference) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            JaMoPPDiffPackage.IMPLEMENTS_CHANGE__CHANGED_REFERENCE, oldChangedReference,
                            changedReference));
            }
        }
        return changedReference;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TypeReference basicGetChangedReference() {
        return changedReference;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChangedReference(TypeReference newChangedReference) {
        TypeReference oldChangedReference = changedReference;
        changedReference = newChangedReference;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    JaMoPPDiffPackage.IMPLEMENTS_CHANGE__CHANGED_REFERENCE, oldChangedReference, changedReference));
    }

    @Override
    public EObject basicGetChangedElement() {
        return basicGetChangedReference();
    }

    @Override
    public void setChangedElement(EObject newChangedElement) {
        if(newChangedElement == null) {
            setChangedReference(null);
        } else if(newChangedElement instanceof TypeReference) {
            setChangedReference((TypeReference) newChangedElement);
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
        case JaMoPPDiffPackage.IMPLEMENTS_CHANGE__CHANGED_REFERENCE:
            if (resolve)
                return getChangedReference();
            return basicGetChangedReference();
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
        case JaMoPPDiffPackage.IMPLEMENTS_CHANGE__CHANGED_REFERENCE:
            setChangedReference((TypeReference) newValue);
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
        case JaMoPPDiffPackage.IMPLEMENTS_CHANGE__CHANGED_REFERENCE:
            setChangedReference((TypeReference) null);
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
        case JaMoPPDiffPackage.IMPLEMENTS_CHANGE__CHANGED_REFERENCE:
            return changedReference != null;
        }
        return super.eIsSet(featureID);
    }

} //ImplementsChangeImpl
