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
package org.splevo.jamopp.diffing.jamoppdiff.impl;

import org.eclipse.emf.compare.ComparePackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
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
import org.splevo.jamopp.diffing.jamoppdiff.ClassChange;
import org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange;
import org.splevo.jamopp.diffing.jamoppdiff.ConstructorChange;
import org.splevo.jamopp.diffing.jamoppdiff.EnumChange;
import org.splevo.jamopp.diffing.jamoppdiff.ExtendsChange;
import org.splevo.jamopp.diffing.jamoppdiff.FieldChange;
import org.splevo.jamopp.diffing.jamoppdiff.ImplementsChange;
import org.splevo.jamopp.diffing.jamoppdiff.ImportChange;
import org.splevo.jamopp.diffing.jamoppdiff.InterfaceChange;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiff;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffFactory;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage;
import org.splevo.jamopp.diffing.jamoppdiff.MethodChange;
import org.splevo.jamopp.diffing.jamoppdiff.PackageChange;
import org.splevo.jamopp.diffing.jamoppdiff.StatementChange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JaMoPPDiffPackageImpl extends EPackageImpl implements JaMoPPDiffPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass jaMoPPDiffEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass statementChangeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass importChangeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass classChangeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass fieldChangeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass packageChangeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass methodChangeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass constructorChangeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass enumChangeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass compilationUnitChangeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass interfaceChangeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass implementsChangeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass extendsChangeEClass = null;

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
     * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private JaMoPPDiffPackageImpl() {
        super(eNS_URI, JaMoPPDiffFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link JaMoPPDiffPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static JaMoPPDiffPackage init() {
        if (isInited)
            return (JaMoPPDiffPackage) EPackage.Registry.INSTANCE.getEPackage(JaMoPPDiffPackage.eNS_URI);

        // Obtain or create and register package
        JaMoPPDiffPackageImpl theJaMoPPDiffPackage = (JaMoPPDiffPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof JaMoPPDiffPackageImpl ? EPackage.Registry.INSTANCE
                .get(eNS_URI) : new JaMoPPDiffPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        ComparePackage.eINSTANCE.eClass();
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

        // Create package meta-data objects
        theJaMoPPDiffPackage.createPackageContents();

        // Initialize created meta-data
        theJaMoPPDiffPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theJaMoPPDiffPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(JaMoPPDiffPackage.eNS_URI, theJaMoPPDiffPackage);
        return theJaMoPPDiffPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getJaMoPPDiff() {
        return jaMoPPDiffEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getStatementChange() {
        return statementChangeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getStatementChange_ChangedStatement() {
        return (EReference) statementChangeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getImportChange() {
        return importChangeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getImportChange_ChangedImport() {
        return (EReference) importChangeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getClassChange() {
        return classChangeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getClassChange_ChangedClass() {
        return (EReference) classChangeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFieldChange() {
        return fieldChangeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFieldChange_ChangedField() {
        return (EReference) fieldChangeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPackageChange() {
        return packageChangeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPackageChange_ChangedPackage() {
        return (EReference) packageChangeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMethodChange() {
        return methodChangeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMethodChange_ChangedMethod() {
        return (EReference) methodChangeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getConstructorChange() {
        return constructorChangeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getConstructorChange_ChangedConstructor() {
        return (EReference) constructorChangeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEnumChange() {
        return enumChangeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEnumChange_ChangedEnum() {
        return (EReference) enumChangeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCompilationUnitChange() {
        return compilationUnitChangeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCompilationUnitChange_ChangedCompilationUnit() {
        return (EReference) compilationUnitChangeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getInterfaceChange() {
        return interfaceChangeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInterfaceChange_ChangedInterface() {
        return (EReference) interfaceChangeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getImplementsChange() {
        return implementsChangeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getImplementsChange_ChangedReference() {
        return (EReference) implementsChangeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExtendsChange() {
        return extendsChangeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExtendsChange_ChangedReference() {
        return (EReference) extendsChangeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public JaMoPPDiffFactory getJaMoPPDiffFactory() {
        return (JaMoPPDiffFactory) getEFactoryInstance();
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
        jaMoPPDiffEClass = createEClass(JA_MO_PP_DIFF);

        statementChangeEClass = createEClass(STATEMENT_CHANGE);
        createEReference(statementChangeEClass, STATEMENT_CHANGE__CHANGED_STATEMENT);

        importChangeEClass = createEClass(IMPORT_CHANGE);
        createEReference(importChangeEClass, IMPORT_CHANGE__CHANGED_IMPORT);

        classChangeEClass = createEClass(CLASS_CHANGE);
        createEReference(classChangeEClass, CLASS_CHANGE__CHANGED_CLASS);

        fieldChangeEClass = createEClass(FIELD_CHANGE);
        createEReference(fieldChangeEClass, FIELD_CHANGE__CHANGED_FIELD);

        packageChangeEClass = createEClass(PACKAGE_CHANGE);
        createEReference(packageChangeEClass, PACKAGE_CHANGE__CHANGED_PACKAGE);

        methodChangeEClass = createEClass(METHOD_CHANGE);
        createEReference(methodChangeEClass, METHOD_CHANGE__CHANGED_METHOD);

        constructorChangeEClass = createEClass(CONSTRUCTOR_CHANGE);
        createEReference(constructorChangeEClass, CONSTRUCTOR_CHANGE__CHANGED_CONSTRUCTOR);

        enumChangeEClass = createEClass(ENUM_CHANGE);
        createEReference(enumChangeEClass, ENUM_CHANGE__CHANGED_ENUM);

        compilationUnitChangeEClass = createEClass(COMPILATION_UNIT_CHANGE);
        createEReference(compilationUnitChangeEClass, COMPILATION_UNIT_CHANGE__CHANGED_COMPILATION_UNIT);

        interfaceChangeEClass = createEClass(INTERFACE_CHANGE);
        createEReference(interfaceChangeEClass, INTERFACE_CHANGE__CHANGED_INTERFACE);

        implementsChangeEClass = createEClass(IMPLEMENTS_CHANGE);
        createEReference(implementsChangeEClass, IMPLEMENTS_CHANGE__CHANGED_REFERENCE);

        extendsChangeEClass = createEClass(EXTENDS_CHANGE);
        createEReference(extendsChangeEClass, EXTENDS_CHANGE__CHANGED_REFERENCE);
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
        StatementsPackage theStatementsPackage = (StatementsPackage) EPackage.Registry.INSTANCE
                .getEPackage(StatementsPackage.eNS_URI);
        ImportsPackage theImportsPackage = (ImportsPackage) EPackage.Registry.INSTANCE
                .getEPackage(ImportsPackage.eNS_URI);
        ClassifiersPackage theClassifiersPackage = (ClassifiersPackage) EPackage.Registry.INSTANCE
                .getEPackage(ClassifiersPackage.eNS_URI);
        MembersPackage theMembersPackage = (MembersPackage) EPackage.Registry.INSTANCE
                .getEPackage(MembersPackage.eNS_URI);
        ContainersPackage theContainersPackage = (ContainersPackage) EPackage.Registry.INSTANCE
                .getEPackage(ContainersPackage.eNS_URI);
        TypesPackage theTypesPackage = (TypesPackage) EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        jaMoPPDiffEClass.getESuperTypes().add(theComparePackage.getDiff());
        statementChangeEClass.getESuperTypes().add(this.getJaMoPPDiff());
        importChangeEClass.getESuperTypes().add(this.getJaMoPPDiff());
        classChangeEClass.getESuperTypes().add(this.getJaMoPPDiff());
        fieldChangeEClass.getESuperTypes().add(this.getJaMoPPDiff());
        packageChangeEClass.getESuperTypes().add(this.getJaMoPPDiff());
        methodChangeEClass.getESuperTypes().add(this.getJaMoPPDiff());
        constructorChangeEClass.getESuperTypes().add(this.getJaMoPPDiff());
        enumChangeEClass.getESuperTypes().add(this.getJaMoPPDiff());
        compilationUnitChangeEClass.getESuperTypes().add(this.getJaMoPPDiff());
        interfaceChangeEClass.getESuperTypes().add(this.getJaMoPPDiff());
        implementsChangeEClass.getESuperTypes().add(this.getJaMoPPDiff());
        extendsChangeEClass.getESuperTypes().add(this.getJaMoPPDiff());

        // Initialize classes and features; add operations and parameters
        initEClass(jaMoPPDiffEClass, JaMoPPDiff.class, "JaMoPPDiff", IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);

        initEClass(statementChangeEClass, StatementChange.class, "StatementChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getStatementChange_ChangedStatement(), theStatementsPackage.getStatement(), null,
                "changedStatement", null, 1, 1, StatementChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(importChangeEClass, ImportChange.class, "ImportChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getImportChange_ChangedImport(), theImportsPackage.getImport(), null, "changedImport", null, 1,
                1, ImportChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(classChangeEClass, ClassChange.class, "ClassChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getClassChange_ChangedClass(), theClassifiersPackage.getClass_(), null, "changedClass", null, 1,
                1, ClassChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fieldChangeEClass, FieldChange.class, "FieldChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFieldChange_ChangedField(), theMembersPackage.getField(), null, "changedField", null, 1, 1,
                FieldChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(packageChangeEClass, PackageChange.class, "PackageChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPackageChange_ChangedPackage(), theContainersPackage.getPackage(), null, "changedPackage",
                null, 1, 1, PackageChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(methodChangeEClass, MethodChange.class, "MethodChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMethodChange_ChangedMethod(), theMembersPackage.getMethod(), null, "changedMethod", null, 1,
                1, MethodChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(constructorChangeEClass, ConstructorChange.class, "ConstructorChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getConstructorChange_ChangedConstructor(), theMembersPackage.getConstructor(), null,
                "changedConstructor", null, 1, 1, ConstructorChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(enumChangeEClass, EnumChange.class, "EnumChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEnumChange_ChangedEnum(), theClassifiersPackage.getEnumeration(), null, "changedEnum", null,
                1, 1, EnumChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(compilationUnitChangeEClass, CompilationUnitChange.class, "CompilationUnitChange", !IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCompilationUnitChange_ChangedCompilationUnit(), theContainersPackage.getCompilationUnit(),
                null, "changedCompilationUnit", null, 1, 1, CompilationUnitChange.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(interfaceChangeEClass, InterfaceChange.class, "InterfaceChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getInterfaceChange_ChangedInterface(), theClassifiersPackage.getInterface(), null,
                "changedInterface", null, 1, 1, InterfaceChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(implementsChangeEClass, ImplementsChange.class, "ImplementsChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getImplementsChange_ChangedReference(), theTypesPackage.getTypeReference(), null,
                "changedReference", null, 0, 1, ImplementsChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(extendsChangeEClass, ExtendsChange.class, "ExtendsChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getExtendsChange_ChangedReference(), theTypesPackage.getTypeReference(), null,
                "changedReference", null, 0, 1, ExtendsChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //JaMoPPDiffPackageImpl
