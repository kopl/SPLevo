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
package org.splevo.diffing.splevodiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.compare.impl.DiffImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.splevo.diffing.splevodiff.SPLevoDiff;
import org.splevo.diffing.splevodiff.SPLevoDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SP Levo Diff</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.diffing.splevodiff.impl.SPLevoDiffImpl#getChangedElement <em>Changed Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SPLevoDiffImpl extends DiffImpl implements SPLevoDiff {
    /**
     * The cached value of the '{@link #getChangedElement() <em>Changed Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChangedElement()
     * @generated
     * @ordered
     */
    protected EObject changedElement;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SPLevoDiffImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SPLevoDiffPackage.Literals.SP_LEVO_DIFF;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject getChangedElement() {
        if (changedElement != null && changedElement.eIsProxy()) {
            InternalEObject oldChangedElement = (InternalEObject) changedElement;
            changedElement = eResolveProxy(oldChangedElement);
            if (changedElement != oldChangedElement) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            SPLevoDiffPackage.SP_LEVO_DIFF__CHANGED_ELEMENT, oldChangedElement, changedElement));
            }
        }
        return changedElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject basicGetChangedElement() {
        return changedElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChangedElement(EObject newChangedElement) {
        EObject oldChangedElement = changedElement;
        changedElement = newChangedElement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SPLevoDiffPackage.SP_LEVO_DIFF__CHANGED_ELEMENT,
                    oldChangedElement, changedElement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case SPLevoDiffPackage.SP_LEVO_DIFF__CHANGED_ELEMENT:
            if (resolve)
                return getChangedElement();
            return basicGetChangedElement();
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
        case SPLevoDiffPackage.SP_LEVO_DIFF__CHANGED_ELEMENT:
            setChangedElement((EObject) newValue);
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
        case SPLevoDiffPackage.SP_LEVO_DIFF__CHANGED_ELEMENT:
            setChangedElement((EObject) null);
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
        case SPLevoDiffPackage.SP_LEVO_DIFF__CHANGED_ELEMENT:
            return changedElement != null;
        }
        return super.eIsSet(featureID);
    }

} //SPLevoDiffImpl
