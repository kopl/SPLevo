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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.resource.java.IJavaLocationMap;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.emftext.language.java.resource.java.mopp.JavaDevNullLocationMap;
import org.emftext.language.java.resource.java.mopp.JavaResource;
import org.splevo.jamopp.util.JaMoPPElementUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwarePackage;
import org.splevo.vpm.software.SoftwareFactory;
import org.splevo.vpm.software.SourceLocation;
import com.google.common.collect.Maps;

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
public class JaMoPPSoftwareElementImpl extends MinimalEObjectImpl.Container implements JaMoPPSoftwareElement {
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
        switch (operationID) {
        case softwarePackage.JA_MO_PP_SOFTWARE_ELEMENT___GET_LABEL:
            return getLabel();
        case softwarePackage.JA_MO_PP_SOFTWARE_ELEMENT___GET_NAME:
            return getName();
        case softwarePackage.JA_MO_PP_SOFTWARE_ELEMENT___GET_SOURCE_LOCATION:
            return getSourceLocation();
        case softwarePackage.JA_MO_PP_SOFTWARE_ELEMENT___GET_WRAPPED_ELEMENT:
            return getWrappedElement();
        }
        return super.eInvoke(operationID, arguments);
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
        if (locationMap instanceof JavaDevNullLocationMap) {
            return reloadLocation(element, resource);
        } else {
            return buildLocation(element, resource);
        }
    }

    private SourceLocation buildLocation(Commentable element, JavaResource resource) {
        SourceLocation location = SoftwareFactory.eINSTANCE.createSourceLocation();
        location.setFilePath(resource.getURI().toFileString());

        IJavaLocationMap locationMap = resource.getLocationMap();
        location.setStartLine(locationMap.getLine(element));
        location.setStartPosition(locationMap.getCharStart(element));
        location.setEndPosition(locationMap.getCharEnd(element));
        return location;
    }

    /**
     * To get source locations for resources previously loaded with disabled location maps, try to
     * reload the according resource, find the current element in it and return the according source
     * location.
     * 
     * @param element
     *            The element to get the location info for.
     * @return The source location map for the element.
     */
    private SourceLocation reloadLocation(Commentable element, JavaResource resource) {

        JavaResource reloadedResource = (JavaResource) resource.getResourceSet().createResource(resource.getURI());
        LinkedHashMap<String, Object> options = Maps.newLinkedHashMap();
        options.put(IJavaOptions.DISABLE_LOCATION_MAP, Boolean.FALSE);
        try {
            reloadedResource.load(options);
        } catch (IOException e) {
            return null;
        }
        String uriFragment = resource.getURIFragment(element);
        Commentable reloadedElement = (Commentable) reloadedResource.getEObject(uriFragment);

        return buildLocation(reloadedElement, reloadedResource);
    }

    /**
     * <!-- begin-user-doc --> {@inheritDoc} <!-- end-user-doc -->
     * 
     * @generated not
     */
    @Override
    public EObject getWrappedElement() {
        return getJamoppElement();
    }

} // JaMoPPSoftwareElementImpl
