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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.emftext.language.java.annotations.AnnotationsPackage;

import org.emftext.language.java.arrays.ArraysPackage;

import org.emftext.language.java.classifiers.ClassifiersPackage;

import org.emftext.language.java.commons.CommonsPackage;

import org.emftext.language.java.containers.ContainersPackage;

import org.emftext.language.java.expressions.ExpressionsPackage;

import org.emftext.language.java.generics.GenericsPackage;

import org.emftext.language.java.imports.ImportsPackage;

import org.emftext.language.java.instantiations.InstantiationsPackage;

import org.emftext.language.java.literals.LiteralsPackage;

import org.emftext.language.java.members.MembersPackage;

import org.emftext.language.java.modifiers.ModifiersPackage;

import org.emftext.language.java.operators.OperatorsPackage;

import org.emftext.language.java.parameters.ParametersPackage;

import org.emftext.language.java.references.ReferencesPackage;

import org.emftext.language.java.statements.StatementsPackage;

import org.emftext.language.java.types.TypesPackage;

import org.emftext.language.java.variables.VariablesPackage;

import org.splevo.jamopp.vpm.software.CommentableSoftwareElement;
import org.splevo.jamopp.vpm.software.JaMoPPJavaSoftwareElement;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;
import org.splevo.jamopp.vpm.software.softwarePackage;

import org.splevo.vpm.realization.RealizationPackage;
import org.splevo.vpm.software.SoftwarePackage;

import org.splevo.vpm.variability.variabilityPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class softwarePackageImpl extends EPackageImpl implements softwarePackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass jaMoPPSoftwareElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass commentableSoftwareElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass jaMoPPJavaSoftwareElementEClass = null;

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
     * @see org.splevo.jamopp.vpm.software.softwarePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private softwarePackageImpl() {
        super(eNS_URI, softwareFactory.eINSTANCE);
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
     * This method is used to initialize {@link softwarePackage#eINSTANCE} when that field is
     * accessed. Clients should not invoke it directly. Instead, they should simply access that
     * field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static softwarePackage init() {
        if (isInited)
            return (softwarePackage) EPackage.Registry.INSTANCE.getEPackage(softwarePackage.eNS_URI);

        // Obtain or create and register package
        softwarePackageImpl thesoftwarePackage = (softwarePackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof softwarePackageImpl ? EPackage.Registry.INSTANCE
                .get(eNS_URI) : new softwarePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        AnnotationsPackage.eINSTANCE.eClass();
        ArraysPackage.eINSTANCE.eClass();
        ClassifiersPackage.eINSTANCE.eClass();
        CommonsPackage.eINSTANCE.eClass();
        ContainersPackage.eINSTANCE.eClass();
        ExpressionsPackage.eINSTANCE.eClass();
        GenericsPackage.eINSTANCE.eClass();
        ImportsPackage.eINSTANCE.eClass();
        InstantiationsPackage.eINSTANCE.eClass();
        LiteralsPackage.eINSTANCE.eClass();
        MembersPackage.eINSTANCE.eClass();
        ModifiersPackage.eINSTANCE.eClass();
        OperatorsPackage.eINSTANCE.eClass();
        ParametersPackage.eINSTANCE.eClass();
        ReferencesPackage.eINSTANCE.eClass();
        StatementsPackage.eINSTANCE.eClass();
        TypesPackage.eINSTANCE.eClass();
        VariablesPackage.eINSTANCE.eClass();
        variabilityPackage.eINSTANCE.eClass();
        SoftwarePackage.eINSTANCE.eClass();
        RealizationPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        thesoftwarePackage.createPackageContents();

        // Initialize created meta-data
        thesoftwarePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        thesoftwarePackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(softwarePackage.eNS_URI, thesoftwarePackage);
        return thesoftwarePackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getJaMoPPSoftwareElement() {
        return jaMoPPSoftwareElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getJaMoPPSoftwareElement_JamoppElement() {
        return (EReference) jaMoPPSoftwareElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCommentableSoftwareElement() {
        return commentableSoftwareElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCommentableSoftwareElement_Id() {
        return (EAttribute) commentableSoftwareElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getCommentableSoftwareElement_CompilationUnit() {
        return (EReference) commentableSoftwareElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCommentableSoftwareElement_Type() {
        return (EAttribute) commentableSoftwareElementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getJaMoPPJavaSoftwareElement() {
        return jaMoPPJavaSoftwareElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EOperation getJaMoPPJavaSoftwareElement__GetJamoppElement() {
        return jaMoPPJavaSoftwareElementEClass.getEOperations().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EOperation getJaMoPPJavaSoftwareElement__GetLabel_1() {
        return jaMoPPJavaSoftwareElementEClass.getEOperations().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EOperation getJaMoPPJavaSoftwareElement__GetName_1() {
        return jaMoPPJavaSoftwareElementEClass.getEOperations().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EOperation getJaMoPPJavaSoftwareElement__GetWrappedElement_1() {
        return jaMoPPJavaSoftwareElementEClass.getEOperations().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public softwareFactory getsoftwareFactory() {
        return (softwareFactory) getEFactoryInstance();
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
        jaMoPPSoftwareElementEClass = createEClass(JA_MO_PP_SOFTWARE_ELEMENT);
        createEReference(jaMoPPSoftwareElementEClass, JA_MO_PP_SOFTWARE_ELEMENT__JAMOPP_ELEMENT);

        commentableSoftwareElementEClass = createEClass(COMMENTABLE_SOFTWARE_ELEMENT);
        createEAttribute(commentableSoftwareElementEClass, COMMENTABLE_SOFTWARE_ELEMENT__ID);
        createEReference(commentableSoftwareElementEClass, COMMENTABLE_SOFTWARE_ELEMENT__COMPILATION_UNIT);
        createEAttribute(commentableSoftwareElementEClass, COMMENTABLE_SOFTWARE_ELEMENT__TYPE);

        jaMoPPJavaSoftwareElementEClass = createEClass(JA_MO_PP_JAVA_SOFTWARE_ELEMENT);
        createEOperation(jaMoPPJavaSoftwareElementEClass, JA_MO_PP_JAVA_SOFTWARE_ELEMENT___GET_JAMOPP_ELEMENT);
        createEOperation(jaMoPPJavaSoftwareElementEClass, JA_MO_PP_JAVA_SOFTWARE_ELEMENT___GET_LABEL);
        createEOperation(jaMoPPJavaSoftwareElementEClass, JA_MO_PP_JAVA_SOFTWARE_ELEMENT___GET_NAME);
        createEOperation(jaMoPPJavaSoftwareElementEClass, JA_MO_PP_JAVA_SOFTWARE_ELEMENT___GET_WRAPPED_ELEMENT);
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
        CommonsPackage theCommonsPackage = (CommonsPackage) EPackage.Registry.INSTANCE
                .getEPackage(CommonsPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        ContainersPackage theContainersPackage = (ContainersPackage) EPackage.Registry.INSTANCE
                .getEPackage(ContainersPackage.eNS_URI);
        SoftwarePackage theSoftwarePackage = (SoftwarePackage) EPackage.Registry.INSTANCE
                .getEPackage(SoftwarePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        jaMoPPSoftwareElementEClass.getESuperTypes().add(this.getJaMoPPJavaSoftwareElement());
        commentableSoftwareElementEClass.getESuperTypes().add(this.getJaMoPPJavaSoftwareElement());
        jaMoPPJavaSoftwareElementEClass.getESuperTypes().add(theSoftwarePackage.getJavaSoftwareElement());

        // Initialize classes, features, and operations; add parameters
        initEClass(jaMoPPSoftwareElementEClass, JaMoPPSoftwareElement.class, "JaMoPPSoftwareElement", !IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getJaMoPPSoftwareElement_JamoppElement(), theCommonsPackage.getCommentable(), null,
                "jamoppElement", null, 1, 1, JaMoPPSoftwareElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(commentableSoftwareElementEClass, CommentableSoftwareElement.class, "CommentableSoftwareElement",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCommentableSoftwareElement_Id(), theEcorePackage.getEString(), "id", null, 1, 1,
                CommentableSoftwareElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCommentableSoftwareElement_CompilationUnit(), theContainersPackage.getCompilationUnit(),
                null, "compilationUnit", null, 1, 1, CommentableSoftwareElement.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        EGenericType g1 = createEGenericType(ecorePackage.getEJavaClass());
        EGenericType g2 = createEGenericType();
        g1.getETypeArguments().add(g2);
        EGenericType g3 = createEGenericType(theCommonsPackage.getCommentable());
        g2.setEUpperBound(g3);
        initEAttribute(getCommentableSoftwareElement_Type(), g1, "type",
                "org.emftext.language.java.commons.Commentable", 1, 1, CommentableSoftwareElement.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(jaMoPPJavaSoftwareElementEClass, JaMoPPJavaSoftwareElement.class, "JaMoPPJavaSoftwareElement",
                IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEOperation(getJaMoPPJavaSoftwareElement__GetJamoppElement(), theCommonsPackage.getCommentable(),
                "getJamoppElement", 1, 1, IS_UNIQUE, IS_ORDERED);

        initEOperation(getJaMoPPJavaSoftwareElement__GetLabel_1(), ecorePackage.getEString(), "getLabel", 0, 1,
                IS_UNIQUE, IS_ORDERED);

        initEOperation(getJaMoPPJavaSoftwareElement__GetName_1(), ecorePackage.getEString(), "getName", 0, 1,
                IS_UNIQUE, IS_ORDERED);

        initEOperation(getJaMoPPJavaSoftwareElement__GetWrappedElement_1(), theEcorePackage.getEObject(),
                "getWrappedElement", 0, 1, IS_UNIQUE, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} // softwarePackageImpl
