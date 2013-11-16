package org.splevo.modisco.java.diffing.java2kdmdiff.impl;

import org.eclipse.emf.compare.ComparePackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;
import org.splevo.modisco.java.diffing.java2kdmdiff.ClassChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.EnumChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffExtension;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffFactory;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.modisco.java.diffing.java2kdmdiff.MethodChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.PackageChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Java2KDMDiffPackageImpl extends EPackageImpl implements Java2KDMDiffPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass java2KDMDiffExtensionEClass = null;

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
    private EClass enumChangeEClass = null;

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
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private Java2KDMDiffPackageImpl() {
        super(eNS_URI, Java2KDMDiffFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link Java2KDMDiffPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static Java2KDMDiffPackage init() {
        if (isInited)
            return (Java2KDMDiffPackage) EPackage.Registry.INSTANCE.getEPackage(Java2KDMDiffPackage.eNS_URI);

        // Obtain or create and register package
        Java2KDMDiffPackageImpl theJava2KDMDiffPackage = (Java2KDMDiffPackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof Java2KDMDiffPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                : new Java2KDMDiffPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        ComparePackage.eINSTANCE.eClass();
        JavaPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theJava2KDMDiffPackage.createPackageContents();

        // Initialize created meta-data
        theJava2KDMDiffPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theJava2KDMDiffPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(Java2KDMDiffPackage.eNS_URI, theJava2KDMDiffPackage);
        return theJava2KDMDiffPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getJava2KDMDiffExtension() {
        return java2KDMDiffExtensionEClass;
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
    public Java2KDMDiffFactory getJava2KDMDiffFactory() {
        return (Java2KDMDiffFactory) getEFactoryInstance();
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
        java2KDMDiffExtensionEClass = createEClass(JAVA2_KDM_DIFF_EXTENSION);

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

        enumChangeEClass = createEClass(ENUM_CHANGE);
        createEReference(enumChangeEClass, ENUM_CHANGE__CHANGED_ENUM);
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
        JavaPackage theJavaPackage = (JavaPackage) EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        java2KDMDiffExtensionEClass.getESuperTypes().add(theComparePackage.getDiff());
        statementChangeEClass.getESuperTypes().add(this.getJava2KDMDiffExtension());
        importChangeEClass.getESuperTypes().add(this.getJava2KDMDiffExtension());
        classChangeEClass.getESuperTypes().add(this.getJava2KDMDiffExtension());
        fieldChangeEClass.getESuperTypes().add(this.getJava2KDMDiffExtension());
        packageChangeEClass.getESuperTypes().add(this.getJava2KDMDiffExtension());
        methodChangeEClass.getESuperTypes().add(this.getJava2KDMDiffExtension());
        enumChangeEClass.getESuperTypes().add(this.getJava2KDMDiffExtension());

        // Initialize classes and features; add operations and parameters
        initEClass(java2KDMDiffExtensionEClass, Java2KDMDiffExtension.class, "Java2KDMDiffExtension", IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(statementChangeEClass, StatementChange.class, "StatementChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getStatementChange_ChangedStatement(), theJavaPackage.getStatement(), null, "changedStatement",
                null, 1, 1, StatementChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(importChangeEClass, ImportChange.class, "ImportChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getImportChange_ChangedImport(), theJavaPackage.getImportDeclaration(), null, "changedImport",
                null, 1, 1, ImportChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(classChangeEClass, ClassChange.class, "ClassChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getClassChange_ChangedClass(), theJavaPackage.getClassDeclaration(), null, "changedClass", null,
                1, 1, ClassChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fieldChangeEClass, FieldChange.class, "FieldChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFieldChange_ChangedField(), theJavaPackage.getFieldDeclaration(), null, "changedField", null,
                1, 1, FieldChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(packageChangeEClass, PackageChange.class, "PackageChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPackageChange_ChangedPackage(), theJavaPackage.getPackage(), null, "changedPackage", null, 1,
                1, PackageChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(methodChangeEClass, MethodChange.class, "MethodChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMethodChange_ChangedMethod(), theJavaPackage.getAbstractMethodDeclaration(), null,
                "changedMethod", null, 1, 1, MethodChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(enumChangeEClass, EnumChange.class, "EnumChange", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEnumChange_ChangedEnum(), theJavaPackage.getEnumDeclaration(), null, "changedEnum", null, 1,
                1, EnumChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createExtendedMetaDataAnnotations() {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
        addAnnotation(importChangeEClass, source, new String[] { "name", "ImportDeclarationChange" });
    }

} //Java2KDMDiffPackageImpl
