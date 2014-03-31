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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.splevo.vpm.software.SoftwarePackage;
import org.splevo.vpm.variability.variabilityPackage;
import org.splevo.vrm.VariabilityMechanism;
import org.splevo.vrm.VariabilityRealizationConfiguration;
import org.splevo.vrm.VariabilityRealizationModel;
import org.splevo.vrm.vrmFactory;
import org.splevo.vrm.vrmPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class vrmPackageImpl extends EPackageImpl implements vrmPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass variabilityMechanismEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass variabilityRealizationConfigurationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass variabilityRealizationModelEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package package URI
     * value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init
     * init()}, which also performs initialization of the package, or returns the registered
     * package, if one already exists. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.splevo.vrm.vrmPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private vrmPackageImpl() {
        super(eNS_URI, vrmFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others
     * upon which it depends.
     * 
     * <p>
     * This method is used to initialize {@link vrmPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to
     * obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static vrmPackage init() {
        if (isInited)
            return (vrmPackage) EPackage.Registry.INSTANCE.getEPackage(vrmPackage.eNS_URI);

        // Obtain or create and register package
        vrmPackageImpl thevrmPackage = (vrmPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof vrmPackageImpl ? EPackage.Registry.INSTANCE
                .get(eNS_URI) : new vrmPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        variabilityPackage.eINSTANCE.eClass();
        SoftwarePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        thevrmPackage.createPackageContents();

        // Initialize created meta-data
        thevrmPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        thevrmPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(vrmPackage.eNS_URI, thevrmPackage);
        return thevrmPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getVariabilityMechanism() {
        return variabilityMechanismEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getVariabilityMechanism_RefactoringId() {
        return (EAttribute) variabilityMechanismEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getVariabilityMechanism_Name() {
        return (EAttribute) variabilityMechanismEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getVariabilityMechanism_Description() {
        return (EAttribute) variabilityMechanismEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getVariabilityMechanism_SupportedBindingTimes() {
        return (EAttribute) variabilityMechanismEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getVariabilityMechanism_SupportedExtensibilities() {
        return (EAttribute) variabilityMechanismEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getVariabilityMechanism_SupportedVariabilityTypes() {
        return (EAttribute) variabilityMechanismEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getVariabilityRealizationConfiguration() {
        return variabilityRealizationConfigurationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getVariabilityRealizationConfiguration_VariationPoint() {
        return (EReference) variabilityRealizationConfigurationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getVariabilityRealizationConfiguration_Mechanism() {
        return (EReference) variabilityRealizationConfigurationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getVariabilityRealizationModel() {
        return variabilityRealizationModelEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getVariabilityRealizationModel_RealizationConfigurations() {
        return (EReference) variabilityRealizationModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getVariabilityRealizationModel_Vpm() {
        return (EReference) variabilityRealizationModelEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public vrmFactory getvrmFactory() {
        return (vrmFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to have no affect on
     * any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createPackageContents() {
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        variabilityMechanismEClass = createEClass(VARIABILITY_MECHANISM);
        createEAttribute(variabilityMechanismEClass, VARIABILITY_MECHANISM__REFACTORING_ID);
        createEAttribute(variabilityMechanismEClass, VARIABILITY_MECHANISM__NAME);
        createEAttribute(variabilityMechanismEClass, VARIABILITY_MECHANISM__DESCRIPTION);
        createEAttribute(variabilityMechanismEClass, VARIABILITY_MECHANISM__SUPPORTED_BINDING_TIMES);
        createEAttribute(variabilityMechanismEClass, VARIABILITY_MECHANISM__SUPPORTED_EXTENSIBILITIES);
        createEAttribute(variabilityMechanismEClass, VARIABILITY_MECHANISM__SUPPORTED_VARIABILITY_TYPES);

        variabilityRealizationConfigurationEClass = createEClass(VARIABILITY_REALIZATION_CONFIGURATION);
        createEReference(variabilityRealizationConfigurationEClass,
                VARIABILITY_REALIZATION_CONFIGURATION__VARIATION_POINT);
        createEReference(variabilityRealizationConfigurationEClass, VARIABILITY_REALIZATION_CONFIGURATION__MECHANISM);

        variabilityRealizationModelEClass = createEClass(VARIABILITY_REALIZATION_MODEL);
        createEReference(variabilityRealizationModelEClass, VARIABILITY_REALIZATION_MODEL__REALIZATION_CONFIGURATIONS);
        createEReference(variabilityRealizationModelEClass, VARIABILITY_REALIZATION_MODEL__VPM);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This method is guarded to have
     * no affect on any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        variabilityPackage thevariabilityPackage = (variabilityPackage) EPackage.Registry.INSTANCE
                .getEPackage(variabilityPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes, features, and operations; add parameters
        initEClass(variabilityMechanismEClass, VariabilityMechanism.class, "VariabilityMechanism", !IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getVariabilityMechanism_RefactoringId(), theEcorePackage.getEString(), "refactoringId", null, 1,
                1, VariabilityMechanism.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVariabilityMechanism_Name(), ecorePackage.getEString(), "name", null, 1, 1,
                VariabilityMechanism.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVariabilityMechanism_Description(), theEcorePackage.getEString(), "description", null, 0, 1,
                VariabilityMechanism.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVariabilityMechanism_SupportedBindingTimes(), thevariabilityPackage.getBindingTime(),
                "supportedBindingTimes", null, 1, -1, VariabilityMechanism.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVariabilityMechanism_SupportedExtensibilities(), thevariabilityPackage.getExtensible(),
                "supportedExtensibilities", null, 1, -1, VariabilityMechanism.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVariabilityMechanism_SupportedVariabilityTypes(), thevariabilityPackage.getVariabilityType(),
                "supportedVariabilityTypes", null, 1, -1, VariabilityMechanism.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(variabilityRealizationConfigurationEClass, VariabilityRealizationConfiguration.class,
                "VariabilityRealizationConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVariabilityRealizationConfiguration_VariationPoint(),
                thevariabilityPackage.getVariationPoint(), null, "variationPoint", null, 1, 1,
                VariabilityRealizationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVariabilityRealizationConfiguration_Mechanism(), this.getVariabilityMechanism(), null,
                "mechanism", null, 1, 1, VariabilityRealizationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(variabilityRealizationModelEClass, VariabilityRealizationModel.class, "VariabilityRealizationModel",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVariabilityRealizationModel_RealizationConfigurations(),
                this.getVariabilityRealizationConfiguration(), null, "RealizationConfigurations", null, 0, -1,
                VariabilityRealizationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVariabilityRealizationModel_Vpm(), thevariabilityPackage.getVariationPointModel(), null,
                "vpm", null, 1, 1, VariabilityRealizationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} // vrmPackageImpl
