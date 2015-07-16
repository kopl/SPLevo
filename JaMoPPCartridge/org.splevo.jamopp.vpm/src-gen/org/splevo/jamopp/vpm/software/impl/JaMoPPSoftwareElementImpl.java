/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.jamopp.vpm.software.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.emftext.language.java.commons.Commentable;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwarePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Ja Mo PP Software Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.splevo.jamopp.vpm.software.impl.JaMoPPSoftwareElementImpl#getJamoppElement <em>
 * Jamopp Element</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class JaMoPPSoftwareElementImpl extends JaMoPPJavaSoftwareElementImpl implements JaMoPPSoftwareElement {
    /**
     * The cached value of the '{@link #getJamoppElement() <em>Jamopp Element</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getJamoppElement()
     * @generated
     * @ordered
     */
    protected Commentable jamoppElement;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected JaMoPPSoftwareElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return softwarePackage.Literals.JA_MO_PP_SOFTWARE_ELEMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Commentable getJamoppElement() {
        if (jamoppElement != null && jamoppElement.eIsProxy()) {
            InternalEObject oldJamoppElement = (InternalEObject) jamoppElement;
            jamoppElement = (Commentable) eResolveProxy(oldJamoppElement);
            if (jamoppElement != oldJamoppElement) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            softwarePackage.JA_MO_PP_SOFTWARE_ELEMENT__JAMOPP_ELEMENT, oldJamoppElement, jamoppElement));
            }
        }
        return jamoppElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Commentable basicGetJamoppElement() {
        return jamoppElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setJamoppElement(Commentable newJamoppElement) {
        Commentable oldJamoppElement = jamoppElement;
        jamoppElement = newJamoppElement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    softwarePackage.JA_MO_PP_SOFTWARE_ELEMENT__JAMOPP_ELEMENT, oldJamoppElement, jamoppElement));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case softwarePackage.JA_MO_PP_SOFTWARE_ELEMENT__JAMOPP_ELEMENT:
            if (resolve)
                return getJamoppElement();
            return basicGetJamoppElement();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case softwarePackage.JA_MO_PP_SOFTWARE_ELEMENT__JAMOPP_ELEMENT:
            setJamoppElement((Commentable) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case softwarePackage.JA_MO_PP_SOFTWARE_ELEMENT__JAMOPP_ELEMENT:
            setJamoppElement((Commentable) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case softwarePackage.JA_MO_PP_SOFTWARE_ELEMENT__JAMOPP_ELEMENT:
            return jamoppElement != null;
        }
        return super.eIsSet(featureID);
    }

    @Override
    public Commentable resolveJaMoPPElement() {
        return getJamoppElement();
    }

} // JaMoPPSoftwareElementImpl
