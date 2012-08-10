/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff;

import org.eclipse.emf.compare.diff.metamodel.DiffPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffFactory
 * @model kind="package"
 * @generated
 */
public interface Java2KDMDiffPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "java2kdmdiff";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/emf/compare/diff/java2kdmdiff/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "java2kdmdiff";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Java2KDMDiffPackage eINSTANCE = org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffExtensionImpl <em>Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffExtensionImpl
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getJava2KDMDiffExtension()
	 * @generated
	 */
	int JAVA2_KDM_DIFF_EXTENSION = 0;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA2_KDM_DIFF_EXTENSION__HIDE_ELEMENTS = DiffPackage.ABSTRACT_DIFF_EXTENSION__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA2_KDM_DIFF_EXTENSION__IS_COLLAPSED = DiffPackage.ABSTRACT_DIFF_EXTENSION__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA2_KDM_DIFF_EXTENSION__SUB_DIFF_ELEMENTS = DiffPackage.ABSTRACT_DIFF_EXTENSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA2_KDM_DIFF_EXTENSION__IS_HIDDEN_BY = DiffPackage.ABSTRACT_DIFF_EXTENSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA2_KDM_DIFF_EXTENSION__CONFLICTING = DiffPackage.ABSTRACT_DIFF_EXTENSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA2_KDM_DIFF_EXTENSION__KIND = DiffPackage.ABSTRACT_DIFF_EXTENSION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA2_KDM_DIFF_EXTENSION__REMOTE = DiffPackage.ABSTRACT_DIFF_EXTENSION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Requires</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA2_KDM_DIFF_EXTENSION__REQUIRES = DiffPackage.ABSTRACT_DIFF_EXTENSION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Required By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA2_KDM_DIFF_EXTENSION__REQUIRED_BY = DiffPackage.ABSTRACT_DIFF_EXTENSION_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT = DiffPackage.ABSTRACT_DIFF_EXTENSION_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.StatementChangeImpl <em>Statement Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.StatementChangeImpl
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getStatementChange()
	 * @generated
	 */
	int STATEMENT_CHANGE = 1;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__SUB_DIFF_ELEMENTS = DiffPackage.DIFF_GROUP__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__IS_HIDDEN_BY = DiffPackage.DIFF_GROUP__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__CONFLICTING = DiffPackage.DIFF_GROUP__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__KIND = DiffPackage.DIFF_GROUP__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__REMOTE = DiffPackage.DIFF_GROUP__REMOTE;

	/**
	 * The feature id for the '<em><b>Requires</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__REQUIRES = DiffPackage.DIFF_GROUP__REQUIRES;

	/**
	 * The feature id for the '<em><b>Required By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__REQUIRED_BY = DiffPackage.DIFF_GROUP__REQUIRED_BY;

	/**
	 * The feature id for the '<em><b>Right Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__RIGHT_PARENT = DiffPackage.DIFF_GROUP__RIGHT_PARENT;

	/**
	 * The feature id for the '<em><b>Subchanges</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__SUBCHANGES = DiffPackage.DIFF_GROUP__SUBCHANGES;

	/**
	 * The feature id for the '<em><b>Statement Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__STATEMENT_LEFT = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Statement Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE_FEATURE_COUNT = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImportDeclarationChangeImpl <em>Import Declaration Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImportDeclarationChangeImpl
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getImportDeclarationChange()
	 * @generated
	 */
	int IMPORT_DECLARATION_CHANGE = 2;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__HIDE_ELEMENTS = JAVA2_KDM_DIFF_EXTENSION__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__IS_COLLAPSED = JAVA2_KDM_DIFF_EXTENSION__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__SUB_DIFF_ELEMENTS = JAVA2_KDM_DIFF_EXTENSION__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__IS_HIDDEN_BY = JAVA2_KDM_DIFF_EXTENSION__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__CONFLICTING = JAVA2_KDM_DIFF_EXTENSION__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__KIND = JAVA2_KDM_DIFF_EXTENSION__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__REMOTE = JAVA2_KDM_DIFF_EXTENSION__REMOTE;

	/**
	 * The feature id for the '<em><b>Requires</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__REQUIRES = JAVA2_KDM_DIFF_EXTENSION__REQUIRES;

	/**
	 * The feature id for the '<em><b>Required By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__REQUIRED_BY = JAVA2_KDM_DIFF_EXTENSION__REQUIRED_BY;

	/**
	 * The number of structural features of the '<em>Import Declaration Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE_FEATURE_COUNT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImportInsertImpl <em>Import Insert</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImportInsertImpl
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getImportInsert()
	 * @generated
	 */
	int IMPORT_INSERT = 3;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_INSERT__HIDE_ELEMENTS = IMPORT_DECLARATION_CHANGE__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_INSERT__IS_COLLAPSED = IMPORT_DECLARATION_CHANGE__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_INSERT__SUB_DIFF_ELEMENTS = IMPORT_DECLARATION_CHANGE__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_INSERT__IS_HIDDEN_BY = IMPORT_DECLARATION_CHANGE__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_INSERT__CONFLICTING = IMPORT_DECLARATION_CHANGE__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_INSERT__KIND = IMPORT_DECLARATION_CHANGE__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_INSERT__REMOTE = IMPORT_DECLARATION_CHANGE__REMOTE;

	/**
	 * The feature id for the '<em><b>Requires</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_INSERT__REQUIRES = IMPORT_DECLARATION_CHANGE__REQUIRES;

	/**
	 * The feature id for the '<em><b>Required By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_INSERT__REQUIRED_BY = IMPORT_DECLARATION_CHANGE__REQUIRED_BY;

	/**
	 * The feature id for the '<em><b>Import Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_INSERT__IMPORT_RIGHT = IMPORT_DECLARATION_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Import Insert</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_INSERT_FEATURE_COUNT = IMPORT_DECLARATION_CHANGE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImportDeleteImpl <em>Import Delete</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImportDeleteImpl
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getImportDelete()
	 * @generated
	 */
	int IMPORT_DELETE = 4;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DELETE__HIDE_ELEMENTS = IMPORT_DECLARATION_CHANGE__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DELETE__IS_COLLAPSED = IMPORT_DECLARATION_CHANGE__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DELETE__SUB_DIFF_ELEMENTS = IMPORT_DECLARATION_CHANGE__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DELETE__IS_HIDDEN_BY = IMPORT_DECLARATION_CHANGE__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DELETE__CONFLICTING = IMPORT_DECLARATION_CHANGE__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DELETE__KIND = IMPORT_DECLARATION_CHANGE__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DELETE__REMOTE = IMPORT_DECLARATION_CHANGE__REMOTE;

	/**
	 * The feature id for the '<em><b>Requires</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DELETE__REQUIRES = IMPORT_DECLARATION_CHANGE__REQUIRES;

	/**
	 * The feature id for the '<em><b>Required By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DELETE__REQUIRED_BY = IMPORT_DECLARATION_CHANGE__REQUIRED_BY;

	/**
	 * The feature id for the '<em><b>Import Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DELETE__IMPORT_LEFT = IMPORT_DECLARATION_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Import Delete</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DELETE_FEATURE_COUNT = IMPORT_DECLARATION_CHANGE_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extension</em>'.
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffExtension
	 * @generated
	 */
	EClass getJava2KDMDiffExtension();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange <em>Statement Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Statement Change</em>'.
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange
	 * @generated
	 */
	EClass getStatementChange();

	/**
	 * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange#getStatementLeft <em>Statement Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Statement Left</em>'.
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange#getStatementLeft()
	 * @see #getStatementChange()
	 * @generated
	 */
	EReference getStatementChange_StatementLeft();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImportDeclarationChange <em>Import Declaration Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Import Declaration Change</em>'.
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.ImportDeclarationChange
	 * @generated
	 */
	EClass getImportDeclarationChange();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImportInsert <em>Import Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Import Insert</em>'.
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.ImportInsert
	 * @generated
	 */
	EClass getImportInsert();

	/**
	 * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImportInsert#getImportRight <em>Import Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Import Right</em>'.
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.ImportInsert#getImportRight()
	 * @see #getImportInsert()
	 * @generated
	 */
	EReference getImportInsert_ImportRight();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete <em>Import Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Import Delete</em>'.
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete
	 * @generated
	 */
	EClass getImportDelete();

	/**
	 * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete#getImportLeft <em>Import Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Import Left</em>'.
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete#getImportLeft()
	 * @see #getImportDelete()
	 * @generated
	 */
	EReference getImportDelete_ImportLeft();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Java2KDMDiffFactory getJava2KDMDiffFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffExtensionImpl <em>Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffExtensionImpl
		 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getJava2KDMDiffExtension()
		 * @generated
		 */
		EClass JAVA2_KDM_DIFF_EXTENSION = eINSTANCE.getJava2KDMDiffExtension();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.StatementChangeImpl <em>Statement Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.StatementChangeImpl
		 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getStatementChange()
		 * @generated
		 */
		EClass STATEMENT_CHANGE = eINSTANCE.getStatementChange();

		/**
		 * The meta object literal for the '<em><b>Statement Left</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATEMENT_CHANGE__STATEMENT_LEFT = eINSTANCE.getStatementChange_StatementLeft();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImportDeclarationChangeImpl <em>Import Declaration Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImportDeclarationChangeImpl
		 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getImportDeclarationChange()
		 * @generated
		 */
		EClass IMPORT_DECLARATION_CHANGE = eINSTANCE.getImportDeclarationChange();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImportInsertImpl <em>Import Insert</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImportInsertImpl
		 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getImportInsert()
		 * @generated
		 */
		EClass IMPORT_INSERT = eINSTANCE.getImportInsert();

		/**
		 * The meta object literal for the '<em><b>Import Right</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPORT_INSERT__IMPORT_RIGHT = eINSTANCE.getImportInsert_ImportRight();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImportDeleteImpl <em>Import Delete</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImportDeleteImpl
		 * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getImportDelete()
		 * @generated
		 */
		EClass IMPORT_DELETE = eINSTANCE.getImportDelete();

		/**
		 * The meta object literal for the '<em><b>Import Left</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPORT_DELETE__IMPORT_LEFT = eINSTANCE.getImportDelete_ImportLeft();

	}

} //Java2KDMDiffPackage
