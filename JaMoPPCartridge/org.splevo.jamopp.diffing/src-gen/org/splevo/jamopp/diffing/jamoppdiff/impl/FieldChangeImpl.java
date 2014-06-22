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
import org.emftext.language.java.members.Field;
import org.splevo.jamopp.diffing.jamoppdiff.FieldChange;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.impl.FieldChangeImpl#getChangedField <em>Changed Field</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FieldChangeImpl extends JaMoPPDiffImpl implements FieldChange {
    /**
     * The cached value of the '{@link #getChangedField() <em>Changed Field</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChangedField()
     * @generated
     * @ordered
     */
    protected Field changedField;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FieldChangeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return JaMoPPDiffPackage.Literals.FIELD_CHANGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Field getChangedField() {
        if (changedField != null && changedField.eIsProxy()) {
            InternalEObject oldChangedField = (InternalEObject) changedField;
            changedField = (Field) eResolveProxy(oldChangedField);
            if (changedField != oldChangedField) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            JaMoPPDiffPackage.FIELD_CHANGE__CHANGED_FIELD, oldChangedField, changedField));
            }
        }
        return changedField;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Field basicGetChangedField() {
        return changedField;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChangedField(Field newChangedField) {
        Field oldChangedField = changedField;
        changedField = newChangedField;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JaMoPPDiffPackage.FIELD_CHANGE__CHANGED_FIELD,
                    oldChangedField, changedField));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case JaMoPPDiffPackage.FIELD_CHANGE__CHANGED_FIELD:
            if (resolve)
                return getChangedField();
            return basicGetChangedField();
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
        case JaMoPPDiffPackage.FIELD_CHANGE__CHANGED_FIELD:
            setChangedField((Field) newValue);
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
        case JaMoPPDiffPackage.FIELD_CHANGE__CHANGED_FIELD:
            setChangedField((Field) null);
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
        case JaMoPPDiffPackage.FIELD_CHANGE__CHANGED_FIELD:
            return changedField != null;
        }
        return super.eIsSet(featureID);
    }

} //FieldChangeImpl
