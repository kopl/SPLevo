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
import org.emftext.language.java.resource.java.IJavaLocationMap;
import org.emftext.language.java.resource.java.mopp.JavaResource;
import org.splevo.jamopp.util.JaMoPPElementUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwarePackage;
import org.splevo.vpm.software.SoftwareFactory;
import org.splevo.vpm.software.SourceLocation;
import org.splevo.vpm.software.impl.JavaSoftwareElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Ja Mo PP Software Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.splevo.jamopp.vpm.software.impl.JaMoPPSoftwareElementImpl#getJamoppElement <em>Jamopp
 * Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JaMoPPSoftwareElementImpl extends JavaSoftwareElementImpl implements JaMoPPSoftwareElement {
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

    /**
     * <!-- begin-user-doc --> {@inheritDoc} <!-- end-user-doc -->
     *
     * @generated not
     */
    @Override
    public String getLabel() {
        return JaMoPPElementUtil.getLabel(getJamoppElement());
    }

    /**
     * <!-- begin-user-doc --> {@inheritDoc} <!-- end-user-doc -->
     *
     * @generated not
     */
    @Override
    public String getName() {
        return JaMoPPElementUtil.getName(getJamoppElement());
    }

    /**
     * <!-- begin-user-doc --> {@inheritDoc} <!-- end-user-doc -->
     *
     * @generated not
     */
    @Override
    public SourceLocation getSourceLocation() {

        Commentable element = getJamoppElement();
        if (!(element.eResource() instanceof JavaResource)) {
            return null;
        }

        JavaResource resource = (JavaResource) element.eResource();
        IJavaLocationMap locationMap = resource.getLocationMap();
        if (locationMap == null) {
            return null;
        }

        SourceLocation location = SoftwareFactory.eINSTANCE.createSourceLocation();
        location.setFilePath(resource.getURI().toFileString());
        location.setStartLine(locationMap.getLine(element));
        location.setStartPosition(locationMap.getCharStart(element));
        location.setEndPosition(locationMap.getCharEnd(element));

        return location;
    }

} // JaMoPPSoftwareElementImpl
