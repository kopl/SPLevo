/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.impl;

import org.eclipse.emf.compare.diff.metamodel.DiffPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.gmt.modisco.java.emf.JavaPackage;

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

		importDeclarationChangeEClass = createEClass(IMPORT_DECLARATION_CHANGE);

		importInsertEClass = createEClass(IMPORT_INSERT);
		createEReference(importInsertEClass, IMPORT_INSERT__IMPORT_LEFT);

		importDeleteEClass = createEClass(IMPORT_DELETE);
		createEReference(importDeleteEClass, IMPORT_DELETE__IMPORT_RIGHT);
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
		statementChangeEClass.getESuperTypes().add(theDiffPackage.getDiffGroup());
		importDeclarationChangeEClass.getESuperTypes().add(this.getJava2KDMDiffExtension());
		importInsertEClass.getESuperTypes().add(this.getImportDeclarationChange());
		importDeleteEClass.getESuperTypes().add(this.getImportDeclarationChange());

		// Initialize classes and features; add operations and parameters
		initEClass(java2KDMDiffExtensionEClass, Java2KDMDiffExtension.class, "Java2KDMDiffExtension", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(statementChangeEClass, StatementChange.class, "StatementChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStatementChange_StatementRight(), theJavaPackage.getStatement(), null, "statementRight", null, 0, 1, StatementChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(importDeclarationChangeEClass, ImportDeclarationChange.class, "ImportDeclarationChange", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(importInsertEClass, ImportInsert.class, "ImportInsert", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getImportInsert_ImportLeft(), theJavaPackage.getImportDeclaration(), null, "importLeft", null, 0, 1, ImportInsert.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(importDeleteEClass, ImportDelete.class, "ImportDelete", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getImportDelete_ImportRight(), theJavaPackage.getImportDeclaration(), null, "importRight", null, 0, 1, ImportDelete.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
