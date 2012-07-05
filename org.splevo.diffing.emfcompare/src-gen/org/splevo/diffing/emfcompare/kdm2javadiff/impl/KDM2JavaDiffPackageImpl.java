/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.impl;

import org.eclipse.emf.compare.diff.metamodel.DiffPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.gmt.modisco.java.emf.JavaPackage;

import org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.ClassDelete;
import org.splevo.diffing.emfcompare.kdm2javadiff.ClassInsert;
import org.splevo.diffing.emfcompare.kdm2javadiff.ClassModifierChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.ImportDelete;
import org.splevo.diffing.emfcompare.kdm2javadiff.ImportInsert;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffExtension;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffFactory;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodDelete;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodInsert;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodModifierChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodParameterChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.ReturnTypeChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.StatementDelete;
import org.splevo.diffing.emfcompare.kdm2javadiff.StatementInsert;
import org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove;
import org.splevo.diffing.emfcompare.kdm2javadiff.StatementOrderChange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KDM2JavaDiffPackageImpl extends EPackageImpl implements KDM2JavaDiffPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kdm2JavaDiffExtensionEClass = null;

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
	private EClass statementOrderChangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass statementInsertEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass statementDeleteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass statementMoveEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classDeclarationChangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classInsertEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classDeleteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classModifierChangeEClass = null;

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
	private EClass methodDeclarationChangeEClass = null;

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
	private EClass methodChangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodModifierChangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass returnTypeChangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodParameterChangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodInsertEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodDeleteEClass = null;

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
	private EClass packageChangeEClass = null;

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
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private KDM2JavaDiffPackageImpl() {
		super(eNS_URI, KDM2JavaDiffFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link KDM2JavaDiffPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static KDM2JavaDiffPackage init() {
		if (isInited) return (KDM2JavaDiffPackage)EPackage.Registry.INSTANCE.getEPackage(KDM2JavaDiffPackage.eNS_URI);

		// Obtain or create and register package
		KDM2JavaDiffPackageImpl theKDM2JavaDiffPackage = (KDM2JavaDiffPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof KDM2JavaDiffPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new KDM2JavaDiffPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		DiffPackage.eINSTANCE.eClass();
		JavaPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theKDM2JavaDiffPackage.createPackageContents();

		// Initialize created meta-data
		theKDM2JavaDiffPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theKDM2JavaDiffPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(KDM2JavaDiffPackage.eNS_URI, theKDM2JavaDiffPackage);
		return theKDM2JavaDiffPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKDM2JavaDiffExtension() {
		return kdm2JavaDiffExtensionEClass;
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
	public EReference getStatementChange_StatementLeft() {
		return (EReference)statementChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStatementChange_StatementRight() {
		return (EReference)statementChangeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStatementChange_MethodChange() {
		return (EReference)statementChangeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStatementOrderChange() {
		return statementOrderChangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStatementOrderChange_StatementIndexNew() {
		return (EAttribute)statementOrderChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStatementOrderChange_StatementIndexOld() {
		return (EAttribute)statementOrderChangeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStatementInsert() {
		return statementInsertEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStatementDelete() {
		return statementDeleteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStatementMove() {
		return statementMoveEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStatementMove_ParentLeft() {
		return (EReference)statementMoveEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStatementMove_ParentRight() {
		return (EReference)statementMoveEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassDeclarationChange() {
		return classDeclarationChangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassDeclarationChange_ClassLeft() {
		return (EReference)classDeclarationChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassDeclarationChange_ClassRight() {
		return (EReference)classDeclarationChangeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassDeclarationChange_ClassChange() {
		return (EReference)classDeclarationChangeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassInsert() {
		return classInsertEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassDelete() {
		return classDeleteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassModifierChange() {
		return classModifierChangeEClass;
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
	public EReference getImportDeclarationChange_ImportLeft() {
		return (EReference)importDeclarationChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImportDeclarationChange_ImportRight() {
		return (EReference)importDeclarationChangeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImportDeclarationChange_CompilationUnitChange() {
		return (EReference)importDeclarationChangeEClass.getEStructuralFeatures().get(2);
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
	public EClass getImportDelete() {
		return importDeleteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodDeclarationChange() {
		return methodDeclarationChangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodDeclarationChange_MethodChange() {
		return (EReference)methodDeclarationChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodDeclarationChange_MethodDeclarationLeft() {
		return (EReference)methodDeclarationChangeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodDeclarationChange_MethodDeclarationRight() {
		return (EReference)methodDeclarationChangeEClass.getEStructuralFeatures().get(2);
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
	public EReference getClassChange_ClassDeclaractionChanges() {
		return (EReference)classChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassChange_MethodChanges() {
		return (EReference)classChangeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassChange_CompilationUnitChange() {
		return (EReference)classChangeEClass.getEStructuralFeatures().get(2);
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
	public EReference getMethodChange_MethodDeclarationChange() {
		return (EReference)methodChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodChange_StatementChanges() {
		return (EReference)methodChangeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodChange_ClassChange() {
		return (EReference)methodChangeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodModifierChange() {
		return methodModifierChangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReturnTypeChange() {
		return returnTypeChangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodParameterChange() {
		return methodParameterChangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodInsert() {
		return methodInsertEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodDelete() {
		return methodDeleteEClass;
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
	public EReference getCompilationUnitChange_ClassChanges() {
		return (EReference)compilationUnitChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationUnitChange_ImportDeclarationChanges() {
		return (EReference)compilationUnitChangeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationUnitChange_PackageChange() {
		return (EReference)compilationUnitChangeEClass.getEStructuralFeatures().get(2);
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
	public EReference getPackageChange_CompilationUnitChanges() {
		return (EReference)packageChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageChange_SubPackages() {
		return (EReference)packageChangeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageChange_PackageLeft() {
		return (EReference)packageChangeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageChange_PackageRight() {
		return (EReference)packageChangeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KDM2JavaDiffFactory getKDM2JavaDiffFactory() {
		return (KDM2JavaDiffFactory)getEFactoryInstance();
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
		kdm2JavaDiffExtensionEClass = createEClass(KDM2_JAVA_DIFF_EXTENSION);

		statementChangeEClass = createEClass(STATEMENT_CHANGE);
		createEReference(statementChangeEClass, STATEMENT_CHANGE__STATEMENT_LEFT);
		createEReference(statementChangeEClass, STATEMENT_CHANGE__STATEMENT_RIGHT);
		createEReference(statementChangeEClass, STATEMENT_CHANGE__METHOD_CHANGE);

		statementOrderChangeEClass = createEClass(STATEMENT_ORDER_CHANGE);
		createEAttribute(statementOrderChangeEClass, STATEMENT_ORDER_CHANGE__STATEMENT_INDEX_NEW);
		createEAttribute(statementOrderChangeEClass, STATEMENT_ORDER_CHANGE__STATEMENT_INDEX_OLD);

		statementInsertEClass = createEClass(STATEMENT_INSERT);

		statementDeleteEClass = createEClass(STATEMENT_DELETE);

		statementMoveEClass = createEClass(STATEMENT_MOVE);
		createEReference(statementMoveEClass, STATEMENT_MOVE__PARENT_LEFT);
		createEReference(statementMoveEClass, STATEMENT_MOVE__PARENT_RIGHT);

		classDeclarationChangeEClass = createEClass(CLASS_DECLARATION_CHANGE);
		createEReference(classDeclarationChangeEClass, CLASS_DECLARATION_CHANGE__CLASS_LEFT);
		createEReference(classDeclarationChangeEClass, CLASS_DECLARATION_CHANGE__CLASS_RIGHT);
		createEReference(classDeclarationChangeEClass, CLASS_DECLARATION_CHANGE__CLASS_CHANGE);

		classInsertEClass = createEClass(CLASS_INSERT);

		classDeleteEClass = createEClass(CLASS_DELETE);

		classModifierChangeEClass = createEClass(CLASS_MODIFIER_CHANGE);

		importDeclarationChangeEClass = createEClass(IMPORT_DECLARATION_CHANGE);
		createEReference(importDeclarationChangeEClass, IMPORT_DECLARATION_CHANGE__IMPORT_LEFT);
		createEReference(importDeclarationChangeEClass, IMPORT_DECLARATION_CHANGE__IMPORT_RIGHT);
		createEReference(importDeclarationChangeEClass, IMPORT_DECLARATION_CHANGE__COMPILATION_UNIT_CHANGE);

		importInsertEClass = createEClass(IMPORT_INSERT);

		importDeleteEClass = createEClass(IMPORT_DELETE);

		methodDeclarationChangeEClass = createEClass(METHOD_DECLARATION_CHANGE);
		createEReference(methodDeclarationChangeEClass, METHOD_DECLARATION_CHANGE__METHOD_CHANGE);
		createEReference(methodDeclarationChangeEClass, METHOD_DECLARATION_CHANGE__METHOD_DECLARATION_LEFT);
		createEReference(methodDeclarationChangeEClass, METHOD_DECLARATION_CHANGE__METHOD_DECLARATION_RIGHT);

		classChangeEClass = createEClass(CLASS_CHANGE);
		createEReference(classChangeEClass, CLASS_CHANGE__CLASS_DECLARACTION_CHANGES);
		createEReference(classChangeEClass, CLASS_CHANGE__METHOD_CHANGES);
		createEReference(classChangeEClass, CLASS_CHANGE__COMPILATION_UNIT_CHANGE);

		methodChangeEClass = createEClass(METHOD_CHANGE);
		createEReference(methodChangeEClass, METHOD_CHANGE__METHOD_DECLARATION_CHANGE);
		createEReference(methodChangeEClass, METHOD_CHANGE__STATEMENT_CHANGES);
		createEReference(methodChangeEClass, METHOD_CHANGE__CLASS_CHANGE);

		methodModifierChangeEClass = createEClass(METHOD_MODIFIER_CHANGE);

		returnTypeChangeEClass = createEClass(RETURN_TYPE_CHANGE);

		methodParameterChangeEClass = createEClass(METHOD_PARAMETER_CHANGE);

		methodInsertEClass = createEClass(METHOD_INSERT);

		methodDeleteEClass = createEClass(METHOD_DELETE);

		compilationUnitChangeEClass = createEClass(COMPILATION_UNIT_CHANGE);
		createEReference(compilationUnitChangeEClass, COMPILATION_UNIT_CHANGE__CLASS_CHANGES);
		createEReference(compilationUnitChangeEClass, COMPILATION_UNIT_CHANGE__IMPORT_DECLARATION_CHANGES);
		createEReference(compilationUnitChangeEClass, COMPILATION_UNIT_CHANGE__PACKAGE_CHANGE);

		packageChangeEClass = createEClass(PACKAGE_CHANGE);
		createEReference(packageChangeEClass, PACKAGE_CHANGE__COMPILATION_UNIT_CHANGES);
		createEReference(packageChangeEClass, PACKAGE_CHANGE__SUB_PACKAGES);
		createEReference(packageChangeEClass, PACKAGE_CHANGE__PACKAGE_LEFT);
		createEReference(packageChangeEClass, PACKAGE_CHANGE__PACKAGE_RIGHT);
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
		kdm2JavaDiffExtensionEClass.getESuperTypes().add(theDiffPackage.getAbstractDiffExtension());
		kdm2JavaDiffExtensionEClass.getESuperTypes().add(theDiffPackage.getDiffElement());
		statementChangeEClass.getESuperTypes().add(this.getKDM2JavaDiffExtension());
		statementOrderChangeEClass.getESuperTypes().add(this.getStatementChange());
		statementInsertEClass.getESuperTypes().add(this.getStatementChange());
		statementDeleteEClass.getESuperTypes().add(this.getStatementChange());
		statementMoveEClass.getESuperTypes().add(this.getStatementChange());
		classDeclarationChangeEClass.getESuperTypes().add(this.getKDM2JavaDiffExtension());
		classInsertEClass.getESuperTypes().add(this.getClassDeclarationChange());
		classDeleteEClass.getESuperTypes().add(this.getClassDeclarationChange());
		classModifierChangeEClass.getESuperTypes().add(this.getClassDeclarationChange());
		importDeclarationChangeEClass.getESuperTypes().add(this.getKDM2JavaDiffExtension());
		importInsertEClass.getESuperTypes().add(this.getImportDeclarationChange());
		importDeleteEClass.getESuperTypes().add(this.getImportDeclarationChange());
		methodDeclarationChangeEClass.getESuperTypes().add(this.getKDM2JavaDiffExtension());
		classChangeEClass.getESuperTypes().add(theDiffPackage.getDiffGroup());
		methodChangeEClass.getESuperTypes().add(theDiffPackage.getDiffGroup());
		methodModifierChangeEClass.getESuperTypes().add(this.getMethodDeclarationChange());
		returnTypeChangeEClass.getESuperTypes().add(this.getMethodDeclarationChange());
		methodParameterChangeEClass.getESuperTypes().add(this.getMethodDeclarationChange());
		methodInsertEClass.getESuperTypes().add(this.getMethodDeclarationChange());
		methodDeleteEClass.getESuperTypes().add(this.getMethodDeclarationChange());
		compilationUnitChangeEClass.getESuperTypes().add(theDiffPackage.getDiffGroup());
		packageChangeEClass.getESuperTypes().add(theDiffPackage.getDiffGroup());

		// Initialize classes and features; add operations and parameters
		initEClass(kdm2JavaDiffExtensionEClass, KDM2JavaDiffExtension.class, "KDM2JavaDiffExtension", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(statementChangeEClass, StatementChange.class, "StatementChange", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStatementChange_StatementLeft(), theJavaPackage.getStatement(), null, "statementLeft", null, 0, 1, StatementChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStatementChange_StatementRight(), theJavaPackage.getStatement(), null, "statementRight", null, 0, 1, StatementChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStatementChange_MethodChange(), this.getMethodChange(), this.getMethodChange_StatementChanges(), "methodChange", null, 0, 1, StatementChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(statementOrderChangeEClass, StatementOrderChange.class, "StatementOrderChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStatementOrderChange_StatementIndexNew(), ecorePackage.getEInt(), "statementIndexNew", "0", 1, 1, StatementOrderChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatementOrderChange_StatementIndexOld(), ecorePackage.getEInt(), "statementIndexOld", "0", 1, 1, StatementOrderChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(statementInsertEClass, StatementInsert.class, "StatementInsert", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(statementDeleteEClass, StatementDelete.class, "StatementDelete", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(statementMoveEClass, StatementMove.class, "StatementMove", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStatementMove_ParentLeft(), theJavaPackage.getASTNode(), null, "parentLeft", null, 1, 1, StatementMove.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStatementMove_ParentRight(), theJavaPackage.getASTNode(), null, "parentRight", null, 1, 1, StatementMove.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(classDeclarationChangeEClass, ClassDeclarationChange.class, "ClassDeclarationChange", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassDeclarationChange_ClassLeft(), theJavaPackage.getClassDeclaration(), null, "classLeft", null, 0, 1, ClassDeclarationChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassDeclarationChange_ClassRight(), theJavaPackage.getClassDeclaration(), null, "classRight", null, 0, 1, ClassDeclarationChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassDeclarationChange_ClassChange(), this.getClassChange(), this.getClassChange_ClassDeclaractionChanges(), "classChange", null, 0, 1, ClassDeclarationChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(classInsertEClass, ClassInsert.class, "ClassInsert", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(classDeleteEClass, ClassDelete.class, "ClassDelete", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(classModifierChangeEClass, ClassModifierChange.class, "ClassModifierChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(importDeclarationChangeEClass, ImportDeclarationChange.class, "ImportDeclarationChange", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getImportDeclarationChange_ImportLeft(), theJavaPackage.getImportDeclaration(), null, "importLeft", null, 0, 1, ImportDeclarationChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getImportDeclarationChange_ImportRight(), theJavaPackage.getImportDeclaration(), null, "importRight", null, 0, 1, ImportDeclarationChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getImportDeclarationChange_CompilationUnitChange(), this.getCompilationUnitChange(), this.getCompilationUnitChange_ImportDeclarationChanges(), "compilationUnitChange", null, 0, 1, ImportDeclarationChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(importInsertEClass, ImportInsert.class, "ImportInsert", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(importDeleteEClass, ImportDelete.class, "ImportDelete", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(methodDeclarationChangeEClass, MethodDeclarationChange.class, "MethodDeclarationChange", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMethodDeclarationChange_MethodChange(), this.getMethodChange(), this.getMethodChange_MethodDeclarationChange(), "methodChange", null, 0, 1, MethodDeclarationChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMethodDeclarationChange_MethodDeclarationLeft(), theJavaPackage.getMethodDeclaration(), null, "methodDeclarationLeft", null, 0, 1, MethodDeclarationChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMethodDeclarationChange_MethodDeclarationRight(), theJavaPackage.getMethodDeclaration(), null, "methodDeclarationRight", null, 0, 1, MethodDeclarationChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(classChangeEClass, ClassChange.class, "ClassChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassChange_ClassDeclaractionChanges(), this.getClassDeclarationChange(), this.getClassDeclarationChange_ClassChange(), "classDeclaractionChanges", null, 0, -1, ClassChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassChange_MethodChanges(), this.getMethodChange(), this.getMethodChange_ClassChange(), "methodChanges", null, 1, -1, ClassChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassChange_CompilationUnitChange(), this.getCompilationUnitChange(), this.getCompilationUnitChange_ClassChanges(), "compilationUnitChange", null, 0, 1, ClassChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(methodChangeEClass, MethodChange.class, "MethodChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMethodChange_MethodDeclarationChange(), this.getMethodDeclarationChange(), this.getMethodDeclarationChange_MethodChange(), "methodDeclarationChange", null, 0, -1, MethodChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMethodChange_StatementChanges(), this.getStatementChange(), this.getStatementChange_MethodChange(), "statementChanges", null, 0, -1, MethodChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMethodChange_ClassChange(), this.getClassChange(), this.getClassChange_MethodChanges(), "classChange", null, 0, 1, MethodChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(methodModifierChangeEClass, MethodModifierChange.class, "MethodModifierChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(returnTypeChangeEClass, ReturnTypeChange.class, "ReturnTypeChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(methodParameterChangeEClass, MethodParameterChange.class, "MethodParameterChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(methodInsertEClass, MethodInsert.class, "MethodInsert", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(methodDeleteEClass, MethodDelete.class, "MethodDelete", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(compilationUnitChangeEClass, CompilationUnitChange.class, "CompilationUnitChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompilationUnitChange_ClassChanges(), this.getClassChange(), this.getClassChange_CompilationUnitChange(), "classChanges", null, 0, -1, CompilationUnitChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompilationUnitChange_ImportDeclarationChanges(), this.getImportDeclarationChange(), this.getImportDeclarationChange_CompilationUnitChange(), "importDeclarationChanges", null, 0, -1, CompilationUnitChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompilationUnitChange_PackageChange(), this.getPackageChange(), this.getPackageChange_CompilationUnitChanges(), "packageChange", null, 0, 1, CompilationUnitChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(packageChangeEClass, PackageChange.class, "PackageChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPackageChange_CompilationUnitChanges(), this.getCompilationUnitChange(), this.getCompilationUnitChange_PackageChange(), "compilationUnitChanges", null, 0, -1, PackageChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageChange_SubPackages(), this.getPackageChange(), null, "subPackages", null, 0, -1, PackageChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageChange_PackageLeft(), theJavaPackage.getPackage(), null, "packageLeft", null, 0, 1, PackageChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageChange_PackageRight(), theJavaPackage.getPackage(), null, "packageRight", null, 0, 1, PackageChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		  (classDeclarationChangeEClass, 
		   source, 
		   new String[] {
			 "name", "ClassDeclarationChange"
		   });								
		addAnnotation
		  (importDeclarationChangeEClass, 
		   source, 
		   new String[] {
			 "name", "ImportDeclarationChange"
		   });								
	}

} //KDM2JavaDiffPackageImpl
