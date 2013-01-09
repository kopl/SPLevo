/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.impl;

import org.eclipse.emf.compare.diff.metamodel.DiffPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;
import org.splevo.diffing.emfcompare.java2kdmdiff.ClassSignatureChange;
import org.splevo.diffing.emfcompare.java2kdmdiff.FieldChange;
import org.splevo.diffing.emfcompare.java2kdmdiff.FieldDelete;
import org.splevo.diffing.emfcompare.java2kdmdiff.FieldInsert;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceDelete;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceInsert;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImportDeclarationChange;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImportInsert;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffExtension;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffFactory;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange;

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
	private EClass importDeclarationChangeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass importInsertEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass importDeleteEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass classSignatureChangeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass implementsInterfaceInsertEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass implementsInterfaceDeleteEClass = null;

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
    private EClass fieldInsertEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass fieldDeleteEClass = null;

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
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#eNS_URI
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
        if (isInited) return (Java2KDMDiffPackage)EPackage.Registry.INSTANCE.getEPackage(Java2KDMDiffPackage.eNS_URI);

        // Obtain or create and register package
        Java2KDMDiffPackageImpl theJava2KDMDiffPackage = (Java2KDMDiffPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Java2KDMDiffPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Java2KDMDiffPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        DiffPackage.eINSTANCE.eClass();
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
	public EReference getStatementChange_StatementRight() {
        return (EReference)statementChangeEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getStatementChange_StatementLeft() {
        return (EReference)statementChangeEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getImportDeclarationChange() {
        return importDeclarationChangeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getImportInsert() {
        return importInsertEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getImportInsert_ImportLeft() {
        return (EReference)importInsertEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getImportDelete() {
        return importDeleteEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getImportDelete_ImportRight() {
        return (EReference)importDeleteEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getClassSignatureChange() {
        return classSignatureChangeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getImplementsInterfaceInsert() {
        return implementsInterfaceInsertEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getImplementsInterfaceInsert_ImplementedInterface() {
        return (EReference)implementsInterfaceInsertEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getImplementsInterfaceInsert_ChangedClass() {
        return (EReference)implementsInterfaceInsertEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getImplementsInterfaceDelete() {
        return implementsInterfaceDeleteEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getImplementsInterfaceDelete_ImplementedInterface() {
        return (EReference)implementsInterfaceDeleteEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getImplementsInterfaceDelete_ChangedClass() {
        return (EReference)implementsInterfaceDeleteEClass.getEStructuralFeatures().get(1);
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
    public EClass getFieldInsert() {
        return fieldInsertEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFieldInsert_FieldLeft() {
        return (EReference)fieldInsertEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFieldDelete() {
        return fieldDeleteEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFieldDelete_FieldRight() {
        return (EReference)fieldDeleteEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Java2KDMDiffFactory getJava2KDMDiffFactory() {
        return (Java2KDMDiffFactory)getEFactoryInstance();
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
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        java2KDMDiffExtensionEClass = createEClass(JAVA2_KDM_DIFF_EXTENSION);

        statementChangeEClass = createEClass(STATEMENT_CHANGE);
        createEReference(statementChangeEClass, STATEMENT_CHANGE__STATEMENT_RIGHT);
        createEReference(statementChangeEClass, STATEMENT_CHANGE__STATEMENT_LEFT);

        importDeclarationChangeEClass = createEClass(IMPORT_DECLARATION_CHANGE);

        importInsertEClass = createEClass(IMPORT_INSERT);
        createEReference(importInsertEClass, IMPORT_INSERT__IMPORT_LEFT);

        importDeleteEClass = createEClass(IMPORT_DELETE);
        createEReference(importDeleteEClass, IMPORT_DELETE__IMPORT_RIGHT);

        classSignatureChangeEClass = createEClass(CLASS_SIGNATURE_CHANGE);

        implementsInterfaceInsertEClass = createEClass(IMPLEMENTS_INTERFACE_INSERT);
        createEReference(implementsInterfaceInsertEClass, IMPLEMENTS_INTERFACE_INSERT__IMPLEMENTED_INTERFACE);
        createEReference(implementsInterfaceInsertEClass, IMPLEMENTS_INTERFACE_INSERT__CHANGED_CLASS);

        implementsInterfaceDeleteEClass = createEClass(IMPLEMENTS_INTERFACE_DELETE);
        createEReference(implementsInterfaceDeleteEClass, IMPLEMENTS_INTERFACE_DELETE__IMPLEMENTED_INTERFACE);
        createEReference(implementsInterfaceDeleteEClass, IMPLEMENTS_INTERFACE_DELETE__CHANGED_CLASS);

        fieldChangeEClass = createEClass(FIELD_CHANGE);

        fieldInsertEClass = createEClass(FIELD_INSERT);
        createEReference(fieldInsertEClass, FIELD_INSERT__FIELD_LEFT);

        fieldDeleteEClass = createEClass(FIELD_DELETE);
        createEReference(fieldDeleteEClass, FIELD_DELETE__FIELD_RIGHT);
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
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        DiffPackage theDiffPackage = (DiffPackage)EPackage.Registry.INSTANCE.getEPackage(DiffPackage.eNS_URI);
        JavaPackage theJavaPackage = (JavaPackage)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        java2KDMDiffExtensionEClass.getESuperTypes().add(theDiffPackage.getAbstractDiffExtension());
        java2KDMDiffExtensionEClass.getESuperTypes().add(theDiffPackage.getDiffElement());
        statementChangeEClass.getESuperTypes().add(this.getJava2KDMDiffExtension());
        importDeclarationChangeEClass.getESuperTypes().add(this.getJava2KDMDiffExtension());
        importInsertEClass.getESuperTypes().add(this.getImportDeclarationChange());
        importDeleteEClass.getESuperTypes().add(this.getImportDeclarationChange());
        classSignatureChangeEClass.getESuperTypes().add(this.getJava2KDMDiffExtension());
        implementsInterfaceInsertEClass.getESuperTypes().add(this.getClassSignatureChange());
        implementsInterfaceDeleteEClass.getESuperTypes().add(this.getClassSignatureChange());
        fieldChangeEClass.getESuperTypes().add(this.getJava2KDMDiffExtension());
        fieldInsertEClass.getESuperTypes().add(this.getFieldChange());
        fieldDeleteEClass.getESuperTypes().add(this.getFieldChange());

        // Initialize classes and features; add operations and parameters
        initEClass(java2KDMDiffExtensionEClass, Java2KDMDiffExtension.class, "Java2KDMDiffExtension", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(statementChangeEClass, StatementChange.class, "StatementChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getStatementChange_StatementRight(), theJavaPackage.getStatement(), null, "statementRight", null, 0, 1, StatementChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getStatementChange_StatementLeft(), theJavaPackage.getStatement(), null, "statementLeft", null, 0, 1, StatementChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(importDeclarationChangeEClass, ImportDeclarationChange.class, "ImportDeclarationChange", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(importInsertEClass, ImportInsert.class, "ImportInsert", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getImportInsert_ImportLeft(), theJavaPackage.getImportDeclaration(), null, "importLeft", null, 0, 1, ImportInsert.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(importDeleteEClass, ImportDelete.class, "ImportDelete", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getImportDelete_ImportRight(), theJavaPackage.getImportDeclaration(), null, "importRight", null, 0, 1, ImportDelete.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(classSignatureChangeEClass, ClassSignatureChange.class, "ClassSignatureChange", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(implementsInterfaceInsertEClass, ImplementsInterfaceInsert.class, "ImplementsInterfaceInsert", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getImplementsInterfaceInsert_ImplementedInterface(), theJavaPackage.getInterfaceDeclaration(), null, "implementedInterface", null, 1, 1, ImplementsInterfaceInsert.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getImplementsInterfaceInsert_ChangedClass(), theJavaPackage.getClassDeclaration(), null, "changedClass", null, 1, 1, ImplementsInterfaceInsert.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(implementsInterfaceDeleteEClass, ImplementsInterfaceDelete.class, "ImplementsInterfaceDelete", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getImplementsInterfaceDelete_ImplementedInterface(), theJavaPackage.getInterfaceDeclaration(), null, "implementedInterface", null, 1, 1, ImplementsInterfaceDelete.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getImplementsInterfaceDelete_ChangedClass(), theJavaPackage.getClassDeclaration(), null, "changedClass", null, 0, 1, ImplementsInterfaceDelete.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fieldChangeEClass, FieldChange.class, "FieldChange", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(fieldInsertEClass, FieldInsert.class, "FieldInsert", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFieldInsert_FieldLeft(), theJavaPackage.getFieldDeclaration(), null, "fieldLeft", null, 0, 1, FieldInsert.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fieldDeleteEClass, FieldDelete.class, "FieldDelete", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFieldDelete_FieldRight(), theJavaPackage.getFieldDeclaration(), null, "fieldRight", null, 0, 1, FieldDelete.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
        addAnnotation
          (getStatementChange_StatementRight(), 
           source, 
           new String[] {
             "name", "statementRight",
             "namespace", ""
           });				
        addAnnotation
          (importDeclarationChangeEClass, 
           source, 
           new String[] {
             "name", "ImportDeclarationChange"
           });		
    }

} //Java2KDMDiffPackageImpl
