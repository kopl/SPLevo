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

import org.eclipse.emf.compare.impl.DiffImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
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
        EObject changedElement = basicGetChangedElement();
        return changedElement != null && changedElement.eIsProxy() ? eResolveProxy((InternalEObject) changedElement)
                : changedElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject basicGetChangedElement() {
        // TODO: implement this method to return the 'Changed Element' reference
        // -> do not perform proxy resolution
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChangedElement(EObject newChangedElement) {
        // TODO: implement this method to set the 'Changed Element' reference
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
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
            return basicGetChangedElement() != null;
        }
        return super.eIsSet(featureID);
    }

} //SPLevoDiffImpl
