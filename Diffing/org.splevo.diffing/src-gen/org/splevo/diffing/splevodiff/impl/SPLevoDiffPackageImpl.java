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

import org.eclipse.emf.compare.ComparePackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.splevo.diffing.splevodiff.SPLevoDiff;
import org.splevo.diffing.splevodiff.SPLevoDiffFactory;
import org.splevo.diffing.splevodiff.SPLevoDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SPLevoDiffPackageImpl extends EPackageImpl implements SPLevoDiffPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass spLevoDiffEClass = null;

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
     * @see org.splevo.diffing.splevodiff.SPLevoDiffPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private SPLevoDiffPackageImpl() {
        super(eNS_URI, SPLevoDiffFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link SPLevoDiffPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static SPLevoDiffPackage init() {
        if (isInited)
            return (SPLevoDiffPackage) EPackage.Registry.INSTANCE.getEPackage(SPLevoDiffPackage.eNS_URI);

        // Obtain or create and register package
        SPLevoDiffPackageImpl theSPLevoDiffPackage = (SPLevoDiffPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SPLevoDiffPackageImpl ? EPackage.Registry.INSTANCE
                .get(eNS_URI) : new SPLevoDiffPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        ComparePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theSPLevoDiffPackage.createPackageContents();

        // Initialize created meta-data
        theSPLevoDiffPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theSPLevoDiffPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(SPLevoDiffPackage.eNS_URI, theSPLevoDiffPackage);
        return theSPLevoDiffPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSPLevoDiff() {
        return spLevoDiffEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSPLevoDiff_ChangedElement() {
        return (EReference) spLevoDiffEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SPLevoDiffFactory getSPLevoDiffFactory() {
        return (SPLevoDiffFactory) getEFactoryInstance();
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
        spLevoDiffEClass = createEClass(SP_LEVO_DIFF);
        createEReference(spLevoDiffEClass, SP_LEVO_DIFF__CHANGED_ELEMENT);
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
        ComparePackage theComparePackage = (ComparePackage) EPackage.Registry.INSTANCE
                .getEPackage(ComparePackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        spLevoDiffEClass.getESuperTypes().add(theComparePackage.getDiff());

        // Initialize classes and features; add operations and parameters
        initEClass(spLevoDiffEClass, SPLevoDiff.class, "SPLevoDiff", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSPLevoDiff_ChangedElement(), theEcorePackage.getEObject(), null, "changedElement", null, 1,
                1, SPLevoDiff.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //SPLevoDiffPackageImpl
