/**
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Benjamin Klatt
 */
package org.splevo.vrm.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.splevo.vpm.variability.VariationPointModel;

import org.splevo.vrm.VariabilityRealizationConfiguration;
import org.splevo.vrm.VariabilityRealizationModel;
import org.splevo.vrm.vrmPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Variability Realization Model</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.splevo.vrm.impl.VariabilityRealizationModelImpl#getRealizationConfigurations <em>
 * Realization Configurations</em>}</li>
 * <li>{@link org.splevo.vrm.impl.VariabilityRealizationModelImpl#getVpm <em>Vpm</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class VariabilityRealizationModelImpl extends MinimalEObjectImpl.Container implements
        VariabilityRealizationModel {
    /**
     * The cached value of the '{@link #getRealizationConfigurations()
     * <em>Realization Configurations</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getRealizationConfigurations()
     * @generated
     * @ordered
     */
    protected EList<VariabilityRealizationConfiguration> realizationConfigurations;

    /**
     * The cached value of the '{@link #getVpm() <em>Vpm</em>}' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getVpm()
     * @generated
     * @ordered
     */
    protected VariationPointModel vpm;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected VariabilityRealizationModelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return vrmPackage.Literals.VARIABILITY_REALIZATION_MODEL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<VariabilityRealizationConfiguration> getRealizationConfigurations() {
        if (realizationConfigurations == null) {
            realizationConfigurations = new EObjectContainmentEList<VariabilityRealizationConfiguration>(
                    VariabilityRealizationConfiguration.class, this,
                    vrmPackage.VARIABILITY_REALIZATION_MODEL__REALIZATION_CONFIGURATIONS);
        }
        return realizationConfigurations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public VariationPointModel getVpm() {
        if (vpm != null && vpm.eIsProxy()) {
            InternalEObject oldVpm = (InternalEObject) vpm;
            vpm = (VariationPointModel) eResolveProxy(oldVpm);
            if (vpm != oldVpm) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            vrmPackage.VARIABILITY_REALIZATION_MODEL__VPM, oldVpm, vpm));
            }
        }
        return vpm;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public VariationPointModel basicGetVpm() {
        return vpm;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setVpm(VariationPointModel newVpm) {
        VariationPointModel oldVpm = vpm;
        vpm = newVpm;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, vrmPackage.VARIABILITY_REALIZATION_MODEL__VPM,
                    oldVpm, vpm));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case vrmPackage.VARIABILITY_REALIZATION_MODEL__REALIZATION_CONFIGURATIONS:
            return ((InternalEList<?>) getRealizationConfigurations()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case vrmPackage.VARIABILITY_REALIZATION_MODEL__REALIZATION_CONFIGURATIONS:
            return getRealizationConfigurations();
        case vrmPackage.VARIABILITY_REALIZATION_MODEL__VPM:
            if (resolve)
                return getVpm();
            return basicGetVpm();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case vrmPackage.VARIABILITY_REALIZATION_MODEL__REALIZATION_CONFIGURATIONS:
            getRealizationConfigurations().clear();
            getRealizationConfigurations().addAll((Collection<? extends VariabilityRealizationConfiguration>) newValue);
            return;
        case vrmPackage.VARIABILITY_REALIZATION_MODEL__VPM:
            setVpm((VariationPointModel) newValue);
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
        case vrmPackage.VARIABILITY_REALIZATION_MODEL__REALIZATION_CONFIGURATIONS:
            getRealizationConfigurations().clear();
            return;
        case vrmPackage.VARIABILITY_REALIZATION_MODEL__VPM:
            setVpm((VariationPointModel) null);
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
        case vrmPackage.VARIABILITY_REALIZATION_MODEL__REALIZATION_CONFIGURATIONS:
            return realizationConfigurations != null && !realizationConfigurations.isEmpty();
        case vrmPackage.VARIABILITY_REALIZATION_MODEL__VPM:
            return vpm != null;
        }
        return super.eIsSet(featureID);
    }

} // VariabilityRealizationModelImpl
