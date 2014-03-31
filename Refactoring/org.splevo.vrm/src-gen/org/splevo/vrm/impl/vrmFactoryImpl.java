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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.splevo.vrm.VariabilityMechanism;
import org.splevo.vrm.VariabilityRealizationConfiguration;
import org.splevo.vrm.VariabilityRealizationModel;
import org.splevo.vrm.vrmFactory;
import org.splevo.vrm.vrmPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class vrmFactoryImpl extends EFactoryImpl implements vrmFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static vrmFactory init() {
        try {
            vrmFactory thevrmFactory = (vrmFactory) EPackage.Registry.INSTANCE.getEFactory(vrmPackage.eNS_URI);
            if (thevrmFactory != null) {
                return thevrmFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new vrmFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public vrmFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case vrmPackage.VARIABILITY_MECHANISM:
            return createVariabilityMechanism();
        case vrmPackage.VARIABILITY_REALIZATION_CONFIGURATION:
            return createVariabilityRealizationConfiguration();
        case vrmPackage.VARIABILITY_REALIZATION_MODEL:
            return createVariabilityRealizationModel();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public VariabilityMechanism createVariabilityMechanism() {
        VariabilityMechanismImpl variabilityMechanism = new VariabilityMechanismImpl();
        return variabilityMechanism;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public VariabilityRealizationConfiguration createVariabilityRealizationConfiguration() {
        VariabilityRealizationConfigurationImpl variabilityRealizationConfiguration = new VariabilityRealizationConfigurationImpl();
        return variabilityRealizationConfiguration;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public VariabilityRealizationModel createVariabilityRealizationModel() {
        VariabilityRealizationModelImpl variabilityRealizationModel = new VariabilityRealizationModelImpl();
        return variabilityRealizationModel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public vrmPackage getvrmPackage() {
        return (vrmPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static vrmPackage getPackage() {
        return vrmPackage.eINSTANCE;
    }

} // vrmFactoryImpl
