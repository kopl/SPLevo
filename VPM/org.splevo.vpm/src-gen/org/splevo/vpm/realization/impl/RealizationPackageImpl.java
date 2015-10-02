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
package org.splevo.vpm.realization.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.featuremodel.FeatureModelPackage;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.RealizationPackage;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwarePackage;
import org.splevo.vpm.software.impl.SoftwarePackageImpl;
import org.splevo.vpm.variability.variabilityPackage;
import org.splevo.vpm.variability.impl.variabilityPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RealizationPackageImpl extends EPackageImpl implements RealizationPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass variabilityMechanismEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.splevo.vpm.realization.RealizationPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private RealizationPackageImpl() {
        super(eNS_URI, RealizationFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link RealizationPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static RealizationPackage init() {
        if (isInited)
            return (RealizationPackage) EPackage.Registry.INSTANCE.getEPackage(RealizationPackage.eNS_URI);

        // Obtain or create and register package
        RealizationPackageImpl theRealizationPackage = (RealizationPackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof RealizationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                : new RealizationPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        FeatureModelPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        variabilityPackageImpl thevariabilityPackage = (variabilityPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(variabilityPackage.eNS_URI) instanceof variabilityPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(variabilityPackage.eNS_URI) : variabilityPackage.eINSTANCE);
        SoftwarePackageImpl theSoftwarePackage = (SoftwarePackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(SoftwarePackage.eNS_URI) instanceof SoftwarePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(SoftwarePackage.eNS_URI) : SoftwarePackage.eINSTANCE);

        // Create package meta-data objects
        theRealizationPackage.createPackageContents();
        thevariabilityPackage.createPackageContents();
        theSoftwarePackage.createPackageContents();

        // Initialize created meta-data
        theRealizationPackage.initializePackageContents();
        thevariabilityPackage.initializePackageContents();
        theSoftwarePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theRealizationPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(RealizationPackage.eNS_URI, theRealizationPackage);
        return theRealizationPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getVariabilityMechanism() {
        return variabilityMechanismEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVariabilityMechanism_Name() {
        return (EAttribute) variabilityMechanismEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVariabilityMechanism_RefactoringID() {
        return (EAttribute) variabilityMechanismEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RealizationFactory getRealizationFactory() {
        return (RealizationFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        variabilityMechanismEClass = createEClass(VARIABILITY_MECHANISM);
        createEAttribute(variabilityMechanismEClass, VARIABILITY_MECHANISM__NAME);
        createEAttribute(variabilityMechanismEClass, VARIABILITY_MECHANISM__REFACTORING_ID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
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

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes, features, and operations; add parameters
        initEClass(variabilityMechanismEClass, VariabilityMechanism.class, "VariabilityMechanism", !IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getVariabilityMechanism_Name(), theEcorePackage.getEString(), "name", null, 0, 1,
                VariabilityMechanism.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVariabilityMechanism_RefactoringID(), theEcorePackage.getEString(), "refactoringID", null, 0,
                1, VariabilityMechanism.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //RealizationPackageImpl
