/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff;

import org.eclipse.emf.compare.diff.metamodel.DiffPackage;

import org.eclipse.emf.ecore.EAttribute;
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
 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffFactory
 * @model kind="package"
 * @generated
 */
public interface KDM2JavaDiffPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "kdm2javadiff";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/emf/compare/diff/kdm2java/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "kdm2javadiff";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	KDM2JavaDiffPackage eINSTANCE = org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffExtensionImpl <em>Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffExtensionImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getKDM2JavaDiffExtension()
	 * @generated
	 */
	int KDM2_JAVA_DIFF_EXTENSION = 0;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KDM2_JAVA_DIFF_EXTENSION__HIDE_ELEMENTS = DiffPackage.ABSTRACT_DIFF_EXTENSION__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KDM2_JAVA_DIFF_EXTENSION__IS_COLLAPSED = DiffPackage.ABSTRACT_DIFF_EXTENSION__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KDM2_JAVA_DIFF_EXTENSION__SUB_DIFF_ELEMENTS = DiffPackage.ABSTRACT_DIFF_EXTENSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KDM2_JAVA_DIFF_EXTENSION__IS_HIDDEN_BY = DiffPackage.ABSTRACT_DIFF_EXTENSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KDM2_JAVA_DIFF_EXTENSION__CONFLICTING = DiffPackage.ABSTRACT_DIFF_EXTENSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KDM2_JAVA_DIFF_EXTENSION__KIND = DiffPackage.ABSTRACT_DIFF_EXTENSION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KDM2_JAVA_DIFF_EXTENSION__REMOTE = DiffPackage.ABSTRACT_DIFF_EXTENSION_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KDM2_JAVA_DIFF_EXTENSION_FEATURE_COUNT = DiffPackage.ABSTRACT_DIFF_EXTENSION_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange <em>Statement Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getStatementChange()
	 * @generated
	 */
	int STATEMENT_CHANGE = 1;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__HIDE_ELEMENTS = KDM2_JAVA_DIFF_EXTENSION__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__IS_COLLAPSED = KDM2_JAVA_DIFF_EXTENSION__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__SUB_DIFF_ELEMENTS = KDM2_JAVA_DIFF_EXTENSION__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__IS_HIDDEN_BY = KDM2_JAVA_DIFF_EXTENSION__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__CONFLICTING = KDM2_JAVA_DIFF_EXTENSION__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__KIND = KDM2_JAVA_DIFF_EXTENSION__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__REMOTE = KDM2_JAVA_DIFF_EXTENSION__REMOTE;

	/**
	 * The feature id for the '<em><b>Statement Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__STATEMENT_LEFT = KDM2_JAVA_DIFF_EXTENSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Statement Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__STATEMENT_RIGHT = KDM2_JAVA_DIFF_EXTENSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Method Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE__METHOD_CHANGE = KDM2_JAVA_DIFF_EXTENSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Statement Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_CHANGE_FEATURE_COUNT = KDM2_JAVA_DIFF_EXTENSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementOrderChangeImpl <em>Statement Order Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementOrderChangeImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getStatementOrderChange()
	 * @generated
	 */
	int STATEMENT_ORDER_CHANGE = 2;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_ORDER_CHANGE__HIDE_ELEMENTS = STATEMENT_CHANGE__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_ORDER_CHANGE__IS_COLLAPSED = STATEMENT_CHANGE__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_ORDER_CHANGE__SUB_DIFF_ELEMENTS = STATEMENT_CHANGE__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_ORDER_CHANGE__IS_HIDDEN_BY = STATEMENT_CHANGE__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_ORDER_CHANGE__CONFLICTING = STATEMENT_CHANGE__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_ORDER_CHANGE__KIND = STATEMENT_CHANGE__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_ORDER_CHANGE__REMOTE = STATEMENT_CHANGE__REMOTE;

	/**
	 * The feature id for the '<em><b>Statement Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_ORDER_CHANGE__STATEMENT_LEFT = STATEMENT_CHANGE__STATEMENT_LEFT;

	/**
	 * The feature id for the '<em><b>Statement Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_ORDER_CHANGE__STATEMENT_RIGHT = STATEMENT_CHANGE__STATEMENT_RIGHT;

	/**
	 * The feature id for the '<em><b>Method Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_ORDER_CHANGE__METHOD_CHANGE = STATEMENT_CHANGE__METHOD_CHANGE;

	/**
	 * The feature id for the '<em><b>Statement Index New</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_ORDER_CHANGE__STATEMENT_INDEX_NEW = STATEMENT_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Statement Index Old</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_ORDER_CHANGE__STATEMENT_INDEX_OLD = STATEMENT_CHANGE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Statement Order Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_ORDER_CHANGE_FEATURE_COUNT = STATEMENT_CHANGE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementInsertImpl <em>Statement Insert</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementInsertImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getStatementInsert()
	 * @generated
	 */
	int STATEMENT_INSERT = 3;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INSERT__HIDE_ELEMENTS = STATEMENT_CHANGE__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INSERT__IS_COLLAPSED = STATEMENT_CHANGE__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INSERT__SUB_DIFF_ELEMENTS = STATEMENT_CHANGE__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INSERT__IS_HIDDEN_BY = STATEMENT_CHANGE__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INSERT__CONFLICTING = STATEMENT_CHANGE__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INSERT__KIND = STATEMENT_CHANGE__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INSERT__REMOTE = STATEMENT_CHANGE__REMOTE;

	/**
	 * The feature id for the '<em><b>Statement Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INSERT__STATEMENT_LEFT = STATEMENT_CHANGE__STATEMENT_LEFT;

	/**
	 * The feature id for the '<em><b>Statement Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INSERT__STATEMENT_RIGHT = STATEMENT_CHANGE__STATEMENT_RIGHT;

	/**
	 * The feature id for the '<em><b>Method Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INSERT__METHOD_CHANGE = STATEMENT_CHANGE__METHOD_CHANGE;

	/**
	 * The number of structural features of the '<em>Statement Insert</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INSERT_FEATURE_COUNT = STATEMENT_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementDeleteImpl <em>Statement Delete</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementDeleteImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getStatementDelete()
	 * @generated
	 */
	int STATEMENT_DELETE = 4;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_DELETE__HIDE_ELEMENTS = STATEMENT_CHANGE__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_DELETE__IS_COLLAPSED = STATEMENT_CHANGE__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_DELETE__SUB_DIFF_ELEMENTS = STATEMENT_CHANGE__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_DELETE__IS_HIDDEN_BY = STATEMENT_CHANGE__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_DELETE__CONFLICTING = STATEMENT_CHANGE__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_DELETE__KIND = STATEMENT_CHANGE__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_DELETE__REMOTE = STATEMENT_CHANGE__REMOTE;

	/**
	 * The feature id for the '<em><b>Statement Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_DELETE__STATEMENT_LEFT = STATEMENT_CHANGE__STATEMENT_LEFT;

	/**
	 * The feature id for the '<em><b>Statement Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_DELETE__STATEMENT_RIGHT = STATEMENT_CHANGE__STATEMENT_RIGHT;

	/**
	 * The feature id for the '<em><b>Method Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_DELETE__METHOD_CHANGE = STATEMENT_CHANGE__METHOD_CHANGE;

	/**
	 * The number of structural features of the '<em>Statement Delete</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_DELETE_FEATURE_COUNT = STATEMENT_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementMoveImpl <em>Statement Move</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementMoveImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getStatementMove()
	 * @generated
	 */
	int STATEMENT_MOVE = 5;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_MOVE__HIDE_ELEMENTS = STATEMENT_CHANGE__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_MOVE__IS_COLLAPSED = STATEMENT_CHANGE__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_MOVE__SUB_DIFF_ELEMENTS = STATEMENT_CHANGE__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_MOVE__IS_HIDDEN_BY = STATEMENT_CHANGE__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_MOVE__CONFLICTING = STATEMENT_CHANGE__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_MOVE__KIND = STATEMENT_CHANGE__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_MOVE__REMOTE = STATEMENT_CHANGE__REMOTE;

	/**
	 * The feature id for the '<em><b>Statement Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_MOVE__STATEMENT_LEFT = STATEMENT_CHANGE__STATEMENT_LEFT;

	/**
	 * The feature id for the '<em><b>Statement Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_MOVE__STATEMENT_RIGHT = STATEMENT_CHANGE__STATEMENT_RIGHT;

	/**
	 * The feature id for the '<em><b>Method Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_MOVE__METHOD_CHANGE = STATEMENT_CHANGE__METHOD_CHANGE;

	/**
	 * The feature id for the '<em><b>Parent Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_MOVE__PARENT_LEFT = STATEMENT_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parent Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_MOVE__PARENT_RIGHT = STATEMENT_CHANGE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Statement Move</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_MOVE_FEATURE_COUNT = STATEMENT_CHANGE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange <em>Class Declaration Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getClassDeclarationChange()
	 * @generated
	 */
	int CLASS_DECLARATION_CHANGE = 6;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DECLARATION_CHANGE__HIDE_ELEMENTS = KDM2_JAVA_DIFF_EXTENSION__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DECLARATION_CHANGE__IS_COLLAPSED = KDM2_JAVA_DIFF_EXTENSION__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DECLARATION_CHANGE__SUB_DIFF_ELEMENTS = KDM2_JAVA_DIFF_EXTENSION__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DECLARATION_CHANGE__IS_HIDDEN_BY = KDM2_JAVA_DIFF_EXTENSION__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DECLARATION_CHANGE__CONFLICTING = KDM2_JAVA_DIFF_EXTENSION__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DECLARATION_CHANGE__KIND = KDM2_JAVA_DIFF_EXTENSION__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DECLARATION_CHANGE__REMOTE = KDM2_JAVA_DIFF_EXTENSION__REMOTE;

	/**
	 * The feature id for the '<em><b>Class Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DECLARATION_CHANGE__CLASS_LEFT = KDM2_JAVA_DIFF_EXTENSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Class Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DECLARATION_CHANGE__CLASS_RIGHT = KDM2_JAVA_DIFF_EXTENSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Class Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DECLARATION_CHANGE__CLASS_CHANGE = KDM2_JAVA_DIFF_EXTENSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Class Declaration Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DECLARATION_CHANGE_FEATURE_COUNT = KDM2_JAVA_DIFF_EXTENSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassInsertImpl <em>Class Insert</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassInsertImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getClassInsert()
	 * @generated
	 */
	int CLASS_INSERT = 7;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSERT__HIDE_ELEMENTS = CLASS_DECLARATION_CHANGE__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSERT__IS_COLLAPSED = CLASS_DECLARATION_CHANGE__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSERT__SUB_DIFF_ELEMENTS = CLASS_DECLARATION_CHANGE__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSERT__IS_HIDDEN_BY = CLASS_DECLARATION_CHANGE__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSERT__CONFLICTING = CLASS_DECLARATION_CHANGE__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSERT__KIND = CLASS_DECLARATION_CHANGE__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSERT__REMOTE = CLASS_DECLARATION_CHANGE__REMOTE;

	/**
	 * The feature id for the '<em><b>Class Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSERT__CLASS_LEFT = CLASS_DECLARATION_CHANGE__CLASS_LEFT;

	/**
	 * The feature id for the '<em><b>Class Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSERT__CLASS_RIGHT = CLASS_DECLARATION_CHANGE__CLASS_RIGHT;

	/**
	 * The feature id for the '<em><b>Class Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSERT__CLASS_CHANGE = CLASS_DECLARATION_CHANGE__CLASS_CHANGE;

	/**
	 * The number of structural features of the '<em>Class Insert</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSERT_FEATURE_COUNT = CLASS_DECLARATION_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassDeleteImpl <em>Class Delete</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassDeleteImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getClassDelete()
	 * @generated
	 */
	int CLASS_DELETE = 8;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DELETE__HIDE_ELEMENTS = CLASS_DECLARATION_CHANGE__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DELETE__IS_COLLAPSED = CLASS_DECLARATION_CHANGE__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DELETE__SUB_DIFF_ELEMENTS = CLASS_DECLARATION_CHANGE__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DELETE__IS_HIDDEN_BY = CLASS_DECLARATION_CHANGE__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DELETE__CONFLICTING = CLASS_DECLARATION_CHANGE__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DELETE__KIND = CLASS_DECLARATION_CHANGE__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DELETE__REMOTE = CLASS_DECLARATION_CHANGE__REMOTE;

	/**
	 * The feature id for the '<em><b>Class Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DELETE__CLASS_LEFT = CLASS_DECLARATION_CHANGE__CLASS_LEFT;

	/**
	 * The feature id for the '<em><b>Class Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DELETE__CLASS_RIGHT = CLASS_DECLARATION_CHANGE__CLASS_RIGHT;

	/**
	 * The feature id for the '<em><b>Class Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DELETE__CLASS_CHANGE = CLASS_DECLARATION_CHANGE__CLASS_CHANGE;

	/**
	 * The number of structural features of the '<em>Class Delete</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_DELETE_FEATURE_COUNT = CLASS_DECLARATION_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassModifierChangeImpl <em>Class Modifier Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassModifierChangeImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getClassModifierChange()
	 * @generated
	 */
	int CLASS_MODIFIER_CHANGE = 9;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODIFIER_CHANGE__HIDE_ELEMENTS = CLASS_DECLARATION_CHANGE__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODIFIER_CHANGE__IS_COLLAPSED = CLASS_DECLARATION_CHANGE__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODIFIER_CHANGE__SUB_DIFF_ELEMENTS = CLASS_DECLARATION_CHANGE__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODIFIER_CHANGE__IS_HIDDEN_BY = CLASS_DECLARATION_CHANGE__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODIFIER_CHANGE__CONFLICTING = CLASS_DECLARATION_CHANGE__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODIFIER_CHANGE__KIND = CLASS_DECLARATION_CHANGE__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODIFIER_CHANGE__REMOTE = CLASS_DECLARATION_CHANGE__REMOTE;

	/**
	 * The feature id for the '<em><b>Class Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODIFIER_CHANGE__CLASS_LEFT = CLASS_DECLARATION_CHANGE__CLASS_LEFT;

	/**
	 * The feature id for the '<em><b>Class Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODIFIER_CHANGE__CLASS_RIGHT = CLASS_DECLARATION_CHANGE__CLASS_RIGHT;

	/**
	 * The feature id for the '<em><b>Class Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODIFIER_CHANGE__CLASS_CHANGE = CLASS_DECLARATION_CHANGE__CLASS_CHANGE;

	/**
	 * The number of structural features of the '<em>Class Modifier Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODIFIER_CHANGE_FEATURE_COUNT = CLASS_DECLARATION_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange <em>Import Declaration Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getImportDeclarationChange()
	 * @generated
	 */
	int IMPORT_DECLARATION_CHANGE = 10;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__HIDE_ELEMENTS = KDM2_JAVA_DIFF_EXTENSION__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__IS_COLLAPSED = KDM2_JAVA_DIFF_EXTENSION__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__SUB_DIFF_ELEMENTS = KDM2_JAVA_DIFF_EXTENSION__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__IS_HIDDEN_BY = KDM2_JAVA_DIFF_EXTENSION__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__CONFLICTING = KDM2_JAVA_DIFF_EXTENSION__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__KIND = KDM2_JAVA_DIFF_EXTENSION__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__REMOTE = KDM2_JAVA_DIFF_EXTENSION__REMOTE;

	/**
	 * The feature id for the '<em><b>Import Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__IMPORT_LEFT = KDM2_JAVA_DIFF_EXTENSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Import Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__IMPORT_RIGHT = KDM2_JAVA_DIFF_EXTENSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Compilation Unit Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE__COMPILATION_UNIT_CHANGE = KDM2_JAVA_DIFF_EXTENSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Import Declaration Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_CHANGE_FEATURE_COUNT = KDM2_JAVA_DIFF_EXTENSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ImportInsertImpl <em>Import Insert</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.ImportInsertImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getImportInsert()
	 * @generated
	 */
	int IMPORT_INSERT = 11;

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
	 * The feature id for the '<em><b>Import Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_INSERT__IMPORT_LEFT = IMPORT_DECLARATION_CHANGE__IMPORT_LEFT;

	/**
	 * The feature id for the '<em><b>Import Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_INSERT__IMPORT_RIGHT = IMPORT_DECLARATION_CHANGE__IMPORT_RIGHT;

	/**
	 * The feature id for the '<em><b>Compilation Unit Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_INSERT__COMPILATION_UNIT_CHANGE = IMPORT_DECLARATION_CHANGE__COMPILATION_UNIT_CHANGE;

	/**
	 * The number of structural features of the '<em>Import Insert</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_INSERT_FEATURE_COUNT = IMPORT_DECLARATION_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ImportDeleteImpl <em>Import Delete</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.ImportDeleteImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getImportDelete()
	 * @generated
	 */
	int IMPORT_DELETE = 12;

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
	 * The feature id for the '<em><b>Import Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DELETE__IMPORT_LEFT = IMPORT_DECLARATION_CHANGE__IMPORT_LEFT;

	/**
	 * The feature id for the '<em><b>Import Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DELETE__IMPORT_RIGHT = IMPORT_DECLARATION_CHANGE__IMPORT_RIGHT;

	/**
	 * The feature id for the '<em><b>Compilation Unit Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DELETE__COMPILATION_UNIT_CHANGE = IMPORT_DECLARATION_CHANGE__COMPILATION_UNIT_CHANGE;

	/**
	 * The number of structural features of the '<em>Import Delete</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DELETE_FEATURE_COUNT = IMPORT_DECLARATION_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange <em>Method Declaration Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getMethodDeclarationChange()
	 * @generated
	 */
	int METHOD_DECLARATION_CHANGE = 13;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION_CHANGE__HIDE_ELEMENTS = KDM2_JAVA_DIFF_EXTENSION__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION_CHANGE__IS_COLLAPSED = KDM2_JAVA_DIFF_EXTENSION__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION_CHANGE__SUB_DIFF_ELEMENTS = KDM2_JAVA_DIFF_EXTENSION__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION_CHANGE__IS_HIDDEN_BY = KDM2_JAVA_DIFF_EXTENSION__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION_CHANGE__CONFLICTING = KDM2_JAVA_DIFF_EXTENSION__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION_CHANGE__KIND = KDM2_JAVA_DIFF_EXTENSION__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION_CHANGE__REMOTE = KDM2_JAVA_DIFF_EXTENSION__REMOTE;

	/**
	 * The feature id for the '<em><b>Method Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION_CHANGE__METHOD_CHANGE = KDM2_JAVA_DIFF_EXTENSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Method Declaration Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION_CHANGE__METHOD_DECLARATION_LEFT = KDM2_JAVA_DIFF_EXTENSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Method Declaration Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION_CHANGE__METHOD_DECLARATION_RIGHT = KDM2_JAVA_DIFF_EXTENSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Method Declaration Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION_CHANGE_FEATURE_COUNT = KDM2_JAVA_DIFF_EXTENSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassChangeImpl <em>Class Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassChangeImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getClassChange()
	 * @generated
	 */
	int CLASS_CHANGE = 14;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_CHANGE__SUB_DIFF_ELEMENTS = DiffPackage.DIFF_GROUP__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_CHANGE__IS_HIDDEN_BY = DiffPackage.DIFF_GROUP__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_CHANGE__CONFLICTING = DiffPackage.DIFF_GROUP__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_CHANGE__KIND = DiffPackage.DIFF_GROUP__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_CHANGE__REMOTE = DiffPackage.DIFF_GROUP__REMOTE;

	/**
	 * The feature id for the '<em><b>Right Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_CHANGE__RIGHT_PARENT = DiffPackage.DIFF_GROUP__RIGHT_PARENT;

	/**
	 * The feature id for the '<em><b>Subchanges</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_CHANGE__SUBCHANGES = DiffPackage.DIFF_GROUP__SUBCHANGES;

	/**
	 * The feature id for the '<em><b>Class Declaraction Changes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_CHANGE__CLASS_DECLARACTION_CHANGES = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Method Changes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_CHANGE__METHOD_CHANGES = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Compilation Unit Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_CHANGE__COMPILATION_UNIT_CHANGE = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Class Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_CHANGE_FEATURE_COUNT = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodChangeImpl <em>Method Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodChangeImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getMethodChange()
	 * @generated
	 */
	int METHOD_CHANGE = 15;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CHANGE__SUB_DIFF_ELEMENTS = DiffPackage.DIFF_GROUP__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CHANGE__IS_HIDDEN_BY = DiffPackage.DIFF_GROUP__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CHANGE__CONFLICTING = DiffPackage.DIFF_GROUP__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CHANGE__KIND = DiffPackage.DIFF_GROUP__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CHANGE__REMOTE = DiffPackage.DIFF_GROUP__REMOTE;

	/**
	 * The feature id for the '<em><b>Right Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CHANGE__RIGHT_PARENT = DiffPackage.DIFF_GROUP__RIGHT_PARENT;

	/**
	 * The feature id for the '<em><b>Subchanges</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CHANGE__SUBCHANGES = DiffPackage.DIFF_GROUP__SUBCHANGES;

	/**
	 * The feature id for the '<em><b>Method Declaration Changes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CHANGE__METHOD_DECLARATION_CHANGES = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Statement Changes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CHANGE__STATEMENT_CHANGES = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Class Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CHANGE__CLASS_CHANGE = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Method Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CHANGE_FEATURE_COUNT = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodModifierChangeImpl <em>Method Modifier Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodModifierChangeImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getMethodModifierChange()
	 * @generated
	 */
	int METHOD_MODIFIER_CHANGE = 16;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_MODIFIER_CHANGE__HIDE_ELEMENTS = METHOD_DECLARATION_CHANGE__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_MODIFIER_CHANGE__IS_COLLAPSED = METHOD_DECLARATION_CHANGE__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_MODIFIER_CHANGE__SUB_DIFF_ELEMENTS = METHOD_DECLARATION_CHANGE__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_MODIFIER_CHANGE__IS_HIDDEN_BY = METHOD_DECLARATION_CHANGE__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_MODIFIER_CHANGE__CONFLICTING = METHOD_DECLARATION_CHANGE__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_MODIFIER_CHANGE__KIND = METHOD_DECLARATION_CHANGE__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_MODIFIER_CHANGE__REMOTE = METHOD_DECLARATION_CHANGE__REMOTE;

	/**
	 * The feature id for the '<em><b>Method Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_MODIFIER_CHANGE__METHOD_CHANGE = METHOD_DECLARATION_CHANGE__METHOD_CHANGE;

	/**
	 * The feature id for the '<em><b>Method Declaration Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_MODIFIER_CHANGE__METHOD_DECLARATION_LEFT = METHOD_DECLARATION_CHANGE__METHOD_DECLARATION_LEFT;

	/**
	 * The feature id for the '<em><b>Method Declaration Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_MODIFIER_CHANGE__METHOD_DECLARATION_RIGHT = METHOD_DECLARATION_CHANGE__METHOD_DECLARATION_RIGHT;

	/**
	 * The number of structural features of the '<em>Method Modifier Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_MODIFIER_CHANGE_FEATURE_COUNT = METHOD_DECLARATION_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ReturnTypeChangeImpl <em>Return Type Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.ReturnTypeChangeImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getReturnTypeChange()
	 * @generated
	 */
	int RETURN_TYPE_CHANGE = 17;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_TYPE_CHANGE__HIDE_ELEMENTS = METHOD_DECLARATION_CHANGE__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_TYPE_CHANGE__IS_COLLAPSED = METHOD_DECLARATION_CHANGE__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_TYPE_CHANGE__SUB_DIFF_ELEMENTS = METHOD_DECLARATION_CHANGE__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_TYPE_CHANGE__IS_HIDDEN_BY = METHOD_DECLARATION_CHANGE__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_TYPE_CHANGE__CONFLICTING = METHOD_DECLARATION_CHANGE__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_TYPE_CHANGE__KIND = METHOD_DECLARATION_CHANGE__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_TYPE_CHANGE__REMOTE = METHOD_DECLARATION_CHANGE__REMOTE;

	/**
	 * The feature id for the '<em><b>Method Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_TYPE_CHANGE__METHOD_CHANGE = METHOD_DECLARATION_CHANGE__METHOD_CHANGE;

	/**
	 * The feature id for the '<em><b>Method Declaration Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_TYPE_CHANGE__METHOD_DECLARATION_LEFT = METHOD_DECLARATION_CHANGE__METHOD_DECLARATION_LEFT;

	/**
	 * The feature id for the '<em><b>Method Declaration Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_TYPE_CHANGE__METHOD_DECLARATION_RIGHT = METHOD_DECLARATION_CHANGE__METHOD_DECLARATION_RIGHT;

	/**
	 * The number of structural features of the '<em>Return Type Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_TYPE_CHANGE_FEATURE_COUNT = METHOD_DECLARATION_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodParameterChangeImpl <em>Method Parameter Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodParameterChangeImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getMethodParameterChange()
	 * @generated
	 */
	int METHOD_PARAMETER_CHANGE = 18;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PARAMETER_CHANGE__HIDE_ELEMENTS = METHOD_DECLARATION_CHANGE__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PARAMETER_CHANGE__IS_COLLAPSED = METHOD_DECLARATION_CHANGE__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PARAMETER_CHANGE__SUB_DIFF_ELEMENTS = METHOD_DECLARATION_CHANGE__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PARAMETER_CHANGE__IS_HIDDEN_BY = METHOD_DECLARATION_CHANGE__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PARAMETER_CHANGE__CONFLICTING = METHOD_DECLARATION_CHANGE__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PARAMETER_CHANGE__KIND = METHOD_DECLARATION_CHANGE__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PARAMETER_CHANGE__REMOTE = METHOD_DECLARATION_CHANGE__REMOTE;

	/**
	 * The feature id for the '<em><b>Method Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PARAMETER_CHANGE__METHOD_CHANGE = METHOD_DECLARATION_CHANGE__METHOD_CHANGE;

	/**
	 * The feature id for the '<em><b>Method Declaration Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PARAMETER_CHANGE__METHOD_DECLARATION_LEFT = METHOD_DECLARATION_CHANGE__METHOD_DECLARATION_LEFT;

	/**
	 * The feature id for the '<em><b>Method Declaration Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PARAMETER_CHANGE__METHOD_DECLARATION_RIGHT = METHOD_DECLARATION_CHANGE__METHOD_DECLARATION_RIGHT;

	/**
	 * The number of structural features of the '<em>Method Parameter Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PARAMETER_CHANGE_FEATURE_COUNT = METHOD_DECLARATION_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodInsertImpl <em>Method Insert</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodInsertImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getMethodInsert()
	 * @generated
	 */
	int METHOD_INSERT = 19;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INSERT__HIDE_ELEMENTS = METHOD_DECLARATION_CHANGE__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INSERT__IS_COLLAPSED = METHOD_DECLARATION_CHANGE__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INSERT__SUB_DIFF_ELEMENTS = METHOD_DECLARATION_CHANGE__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INSERT__IS_HIDDEN_BY = METHOD_DECLARATION_CHANGE__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INSERT__CONFLICTING = METHOD_DECLARATION_CHANGE__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INSERT__KIND = METHOD_DECLARATION_CHANGE__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INSERT__REMOTE = METHOD_DECLARATION_CHANGE__REMOTE;

	/**
	 * The feature id for the '<em><b>Method Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INSERT__METHOD_CHANGE = METHOD_DECLARATION_CHANGE__METHOD_CHANGE;

	/**
	 * The feature id for the '<em><b>Method Declaration Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INSERT__METHOD_DECLARATION_LEFT = METHOD_DECLARATION_CHANGE__METHOD_DECLARATION_LEFT;

	/**
	 * The feature id for the '<em><b>Method Declaration Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INSERT__METHOD_DECLARATION_RIGHT = METHOD_DECLARATION_CHANGE__METHOD_DECLARATION_RIGHT;

	/**
	 * The number of structural features of the '<em>Method Insert</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INSERT_FEATURE_COUNT = METHOD_DECLARATION_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodDeleteImpl <em>Method Delete</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodDeleteImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getMethodDelete()
	 * @generated
	 */
	int METHOD_DELETE = 20;

	/**
	 * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DELETE__HIDE_ELEMENTS = METHOD_DECLARATION_CHANGE__HIDE_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DELETE__IS_COLLAPSED = METHOD_DECLARATION_CHANGE__IS_COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DELETE__SUB_DIFF_ELEMENTS = METHOD_DECLARATION_CHANGE__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DELETE__IS_HIDDEN_BY = METHOD_DECLARATION_CHANGE__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DELETE__CONFLICTING = METHOD_DECLARATION_CHANGE__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DELETE__KIND = METHOD_DECLARATION_CHANGE__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DELETE__REMOTE = METHOD_DECLARATION_CHANGE__REMOTE;

	/**
	 * The feature id for the '<em><b>Method Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DELETE__METHOD_CHANGE = METHOD_DECLARATION_CHANGE__METHOD_CHANGE;

	/**
	 * The feature id for the '<em><b>Method Declaration Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DELETE__METHOD_DECLARATION_LEFT = METHOD_DECLARATION_CHANGE__METHOD_DECLARATION_LEFT;

	/**
	 * The feature id for the '<em><b>Method Declaration Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DELETE__METHOD_DECLARATION_RIGHT = METHOD_DECLARATION_CHANGE__METHOD_DECLARATION_RIGHT;

	/**
	 * The number of structural features of the '<em>Method Delete</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DELETE_FEATURE_COUNT = METHOD_DECLARATION_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.CompilationUnitChangeImpl <em>Compilation Unit Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.CompilationUnitChangeImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getCompilationUnitChange()
	 * @generated
	 */
	int COMPILATION_UNIT_CHANGE = 21;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_UNIT_CHANGE__SUB_DIFF_ELEMENTS = DiffPackage.DIFF_GROUP__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_UNIT_CHANGE__IS_HIDDEN_BY = DiffPackage.DIFF_GROUP__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_UNIT_CHANGE__CONFLICTING = DiffPackage.DIFF_GROUP__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_UNIT_CHANGE__KIND = DiffPackage.DIFF_GROUP__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_UNIT_CHANGE__REMOTE = DiffPackage.DIFF_GROUP__REMOTE;

	/**
	 * The feature id for the '<em><b>Right Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_UNIT_CHANGE__RIGHT_PARENT = DiffPackage.DIFF_GROUP__RIGHT_PARENT;

	/**
	 * The feature id for the '<em><b>Subchanges</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_UNIT_CHANGE__SUBCHANGES = DiffPackage.DIFF_GROUP__SUBCHANGES;

	/**
	 * The feature id for the '<em><b>Class Changes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_UNIT_CHANGE__CLASS_CHANGES = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Import Declaration Changes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_UNIT_CHANGE__IMPORT_DECLARATION_CHANGES = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Package Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_UNIT_CHANGE__PACKAGE_CHANGE = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Compilation Unit Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_UNIT_CHANGE_FEATURE_COUNT = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 3;


	/**
	 * The meta object id for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.PackageChangeImpl <em>Package Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.PackageChangeImpl
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getPackageChange()
	 * @generated
	 */
	int PACKAGE_CHANGE = 22;

	/**
	 * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_CHANGE__SUB_DIFF_ELEMENTS = DiffPackage.DIFF_GROUP__SUB_DIFF_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_CHANGE__IS_HIDDEN_BY = DiffPackage.DIFF_GROUP__IS_HIDDEN_BY;

	/**
	 * The feature id for the '<em><b>Conflicting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_CHANGE__CONFLICTING = DiffPackage.DIFF_GROUP__CONFLICTING;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_CHANGE__KIND = DiffPackage.DIFF_GROUP__KIND;

	/**
	 * The feature id for the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_CHANGE__REMOTE = DiffPackage.DIFF_GROUP__REMOTE;

	/**
	 * The feature id for the '<em><b>Right Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_CHANGE__RIGHT_PARENT = DiffPackage.DIFF_GROUP__RIGHT_PARENT;

	/**
	 * The feature id for the '<em><b>Subchanges</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_CHANGE__SUBCHANGES = DiffPackage.DIFF_GROUP__SUBCHANGES;

	/**
	 * The feature id for the '<em><b>Compilation Unit Changes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_CHANGE__COMPILATION_UNIT_CHANGES = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sub Packages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_CHANGE__SUB_PACKAGES = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Package Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_CHANGE__PACKAGE_LEFT = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Package Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_CHANGE__PACKAGE_RIGHT = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Package Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_CHANGE_FEATURE_COUNT = DiffPackage.DIFF_GROUP_FEATURE_COUNT + 4;


	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extension</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffExtension
	 * @generated
	 */
	EClass getKDM2JavaDiffExtension();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange <em>Statement Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Statement Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange
	 * @generated
	 */
	EClass getStatementChange();

	/**
	 * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange#getStatementLeft <em>Statement Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Statement Left</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange#getStatementLeft()
	 * @see #getStatementChange()
	 * @generated
	 */
	EReference getStatementChange_StatementLeft();

	/**
	 * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange#getStatementRight <em>Statement Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Statement Right</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange#getStatementRight()
	 * @see #getStatementChange()
	 * @generated
	 */
	EReference getStatementChange_StatementRight();

	/**
	 * Returns the meta object for the container reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange#getMethodChange <em>Method Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Method Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange#getMethodChange()
	 * @see #getStatementChange()
	 * @generated
	 */
	EReference getStatementChange_MethodChange();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementOrderChange <em>Statement Order Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Statement Order Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementOrderChange
	 * @generated
	 */
	EClass getStatementOrderChange();

	/**
	 * Returns the meta object for the attribute '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementOrderChange#getStatementIndexNew <em>Statement Index New</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Statement Index New</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementOrderChange#getStatementIndexNew()
	 * @see #getStatementOrderChange()
	 * @generated
	 */
	EAttribute getStatementOrderChange_StatementIndexNew();

	/**
	 * Returns the meta object for the attribute '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementOrderChange#getStatementIndexOld <em>Statement Index Old</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Statement Index Old</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementOrderChange#getStatementIndexOld()
	 * @see #getStatementOrderChange()
	 * @generated
	 */
	EAttribute getStatementOrderChange_StatementIndexOld();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementInsert <em>Statement Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Statement Insert</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementInsert
	 * @generated
	 */
	EClass getStatementInsert();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementDelete <em>Statement Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Statement Delete</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementDelete
	 * @generated
	 */
	EClass getStatementDelete();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove <em>Statement Move</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Statement Move</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove
	 * @generated
	 */
	EClass getStatementMove();

	/**
	 * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove#getParentLeft <em>Parent Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent Left</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove#getParentLeft()
	 * @see #getStatementMove()
	 * @generated
	 */
	EReference getStatementMove_ParentLeft();

	/**
	 * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove#getParentRight <em>Parent Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent Right</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove#getParentRight()
	 * @see #getStatementMove()
	 * @generated
	 */
	EReference getStatementMove_ParentRight();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange <em>Class Declaration Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Declaration Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange
	 * @generated
	 */
	EClass getClassDeclarationChange();

	/**
	 * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange#getClassLeft <em>Class Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Class Left</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange#getClassLeft()
	 * @see #getClassDeclarationChange()
	 * @generated
	 */
	EReference getClassDeclarationChange_ClassLeft();

	/**
	 * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange#getClassRight <em>Class Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Class Right</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange#getClassRight()
	 * @see #getClassDeclarationChange()
	 * @generated
	 */
	EReference getClassDeclarationChange_ClassRight();

	/**
	 * Returns the meta object for the container reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange#getClassChange <em>Class Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Class Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange#getClassChange()
	 * @see #getClassDeclarationChange()
	 * @generated
	 */
	EReference getClassDeclarationChange_ClassChange();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassInsert <em>Class Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Insert</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassInsert
	 * @generated
	 */
	EClass getClassInsert();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassDelete <em>Class Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Delete</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassDelete
	 * @generated
	 */
	EClass getClassDelete();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassModifierChange <em>Class Modifier Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Modifier Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassModifierChange
	 * @generated
	 */
	EClass getClassModifierChange();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange <em>Import Declaration Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Import Declaration Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange
	 * @generated
	 */
	EClass getImportDeclarationChange();

	/**
	 * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange#getImportLeft <em>Import Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Import Left</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange#getImportLeft()
	 * @see #getImportDeclarationChange()
	 * @generated
	 */
	EReference getImportDeclarationChange_ImportLeft();

	/**
	 * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange#getImportRight <em>Import Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Import Right</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange#getImportRight()
	 * @see #getImportDeclarationChange()
	 * @generated
	 */
	EReference getImportDeclarationChange_ImportRight();

	/**
	 * Returns the meta object for the container reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange#getCompilationUnitChange <em>Compilation Unit Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Compilation Unit Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange#getCompilationUnitChange()
	 * @see #getImportDeclarationChange()
	 * @generated
	 */
	EReference getImportDeclarationChange_CompilationUnitChange();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportInsert <em>Import Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Import Insert</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ImportInsert
	 * @generated
	 */
	EClass getImportInsert();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportDelete <em>Import Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Import Delete</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ImportDelete
	 * @generated
	 */
	EClass getImportDelete();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange <em>Method Declaration Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Declaration Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange
	 * @generated
	 */
	EClass getMethodDeclarationChange();

	/**
	 * Returns the meta object for the container reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange#getMethodChange <em>Method Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Method Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange#getMethodChange()
	 * @see #getMethodDeclarationChange()
	 * @generated
	 */
	EReference getMethodDeclarationChange_MethodChange();

	/**
	 * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange#getMethodDeclarationLeft <em>Method Declaration Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Method Declaration Left</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange#getMethodDeclarationLeft()
	 * @see #getMethodDeclarationChange()
	 * @generated
	 */
	EReference getMethodDeclarationChange_MethodDeclarationLeft();

	/**
	 * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange#getMethodDeclarationRight <em>Method Declaration Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Method Declaration Right</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange#getMethodDeclarationRight()
	 * @see #getMethodDeclarationChange()
	 * @generated
	 */
	EReference getMethodDeclarationChange_MethodDeclarationRight();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange <em>Class Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange
	 * @generated
	 */
	EClass getClassChange();

	/**
	 * Returns the meta object for the containment reference list '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange#getClassDeclaractionChanges <em>Class Declaraction Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Class Declaraction Changes</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange#getClassDeclaractionChanges()
	 * @see #getClassChange()
	 * @generated
	 */
	EReference getClassChange_ClassDeclaractionChanges();

	/**
	 * Returns the meta object for the containment reference list '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange#getMethodChanges <em>Method Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Method Changes</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange#getMethodChanges()
	 * @see #getClassChange()
	 * @generated
	 */
	EReference getClassChange_MethodChanges();

	/**
	 * Returns the meta object for the container reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange#getCompilationUnitChange <em>Compilation Unit Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Compilation Unit Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange#getCompilationUnitChange()
	 * @see #getClassChange()
	 * @generated
	 */
	EReference getClassChange_CompilationUnitChange();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange <em>Method Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange
	 * @generated
	 */
	EClass getMethodChange();

	/**
	 * Returns the meta object for the containment reference list '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange#getMethodDeclarationChanges <em>Method Declaration Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Method Declaration Changes</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange#getMethodDeclarationChanges()
	 * @see #getMethodChange()
	 * @generated
	 */
	EReference getMethodChange_MethodDeclarationChanges();

	/**
	 * Returns the meta object for the containment reference list '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange#getStatementChanges <em>Statement Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Statement Changes</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange#getStatementChanges()
	 * @see #getMethodChange()
	 * @generated
	 */
	EReference getMethodChange_StatementChanges();

	/**
	 * Returns the meta object for the container reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange#getClassChange <em>Class Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Class Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange#getClassChange()
	 * @see #getMethodChange()
	 * @generated
	 */
	EReference getMethodChange_ClassChange();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodModifierChange <em>Method Modifier Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Modifier Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodModifierChange
	 * @generated
	 */
	EClass getMethodModifierChange();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ReturnTypeChange <em>Return Type Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Return Type Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ReturnTypeChange
	 * @generated
	 */
	EClass getReturnTypeChange();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodParameterChange <em>Method Parameter Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Parameter Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodParameterChange
	 * @generated
	 */
	EClass getMethodParameterChange();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodInsert <em>Method Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Insert</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodInsert
	 * @generated
	 */
	EClass getMethodInsert();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodDelete <em>Method Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Delete</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodDelete
	 * @generated
	 */
	EClass getMethodDelete();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange <em>Compilation Unit Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Compilation Unit Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange
	 * @generated
	 */
	EClass getCompilationUnitChange();

	/**
	 * Returns the meta object for the containment reference list '{@link org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange#getClassChanges <em>Class Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Class Changes</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange#getClassChanges()
	 * @see #getCompilationUnitChange()
	 * @generated
	 */
	EReference getCompilationUnitChange_ClassChanges();

	/**
	 * Returns the meta object for the containment reference list '{@link org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange#getImportDeclarationChanges <em>Import Declaration Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Import Declaration Changes</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange#getImportDeclarationChanges()
	 * @see #getCompilationUnitChange()
	 * @generated
	 */
	EReference getCompilationUnitChange_ImportDeclarationChanges();

	/**
	 * Returns the meta object for the container reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange#getPackageChange <em>Package Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Package Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange#getPackageChange()
	 * @see #getCompilationUnitChange()
	 * @generated
	 */
	EReference getCompilationUnitChange_PackageChange();

	/**
	 * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange <em>Package Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package Change</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange
	 * @generated
	 */
	EClass getPackageChange();

	/**
	 * Returns the meta object for the containment reference list '{@link org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange#getCompilationUnitChanges <em>Compilation Unit Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Compilation Unit Changes</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange#getCompilationUnitChanges()
	 * @see #getPackageChange()
	 * @generated
	 */
	EReference getPackageChange_CompilationUnitChanges();

	/**
	 * Returns the meta object for the containment reference list '{@link org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange#getSubPackages <em>Sub Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Packages</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange#getSubPackages()
	 * @see #getPackageChange()
	 * @generated
	 */
	EReference getPackageChange_SubPackages();

	/**
	 * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange#getPackageLeft <em>Package Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Package Left</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange#getPackageLeft()
	 * @see #getPackageChange()
	 * @generated
	 */
	EReference getPackageChange_PackageLeft();

	/**
	 * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange#getPackageRight <em>Package Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Package Right</em>'.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange#getPackageRight()
	 * @see #getPackageChange()
	 * @generated
	 */
	EReference getPackageChange_PackageRight();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	KDM2JavaDiffFactory getKDM2JavaDiffFactory();

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
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffExtensionImpl <em>Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffExtensionImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getKDM2JavaDiffExtension()
		 * @generated
		 */
		EClass KDM2_JAVA_DIFF_EXTENSION = eINSTANCE.getKDM2JavaDiffExtension();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange <em>Statement Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getStatementChange()
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
		 * The meta object literal for the '<em><b>Statement Right</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATEMENT_CHANGE__STATEMENT_RIGHT = eINSTANCE.getStatementChange_StatementRight();

		/**
		 * The meta object literal for the '<em><b>Method Change</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATEMENT_CHANGE__METHOD_CHANGE = eINSTANCE.getStatementChange_MethodChange();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementOrderChangeImpl <em>Statement Order Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementOrderChangeImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getStatementOrderChange()
		 * @generated
		 */
		EClass STATEMENT_ORDER_CHANGE = eINSTANCE.getStatementOrderChange();

		/**
		 * The meta object literal for the '<em><b>Statement Index New</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATEMENT_ORDER_CHANGE__STATEMENT_INDEX_NEW = eINSTANCE.getStatementOrderChange_StatementIndexNew();

		/**
		 * The meta object literal for the '<em><b>Statement Index Old</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATEMENT_ORDER_CHANGE__STATEMENT_INDEX_OLD = eINSTANCE.getStatementOrderChange_StatementIndexOld();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementInsertImpl <em>Statement Insert</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementInsertImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getStatementInsert()
		 * @generated
		 */
		EClass STATEMENT_INSERT = eINSTANCE.getStatementInsert();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementDeleteImpl <em>Statement Delete</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementDeleteImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getStatementDelete()
		 * @generated
		 */
		EClass STATEMENT_DELETE = eINSTANCE.getStatementDelete();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementMoveImpl <em>Statement Move</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementMoveImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getStatementMove()
		 * @generated
		 */
		EClass STATEMENT_MOVE = eINSTANCE.getStatementMove();

		/**
		 * The meta object literal for the '<em><b>Parent Left</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATEMENT_MOVE__PARENT_LEFT = eINSTANCE.getStatementMove_ParentLeft();

		/**
		 * The meta object literal for the '<em><b>Parent Right</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATEMENT_MOVE__PARENT_RIGHT = eINSTANCE.getStatementMove_ParentRight();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange <em>Class Declaration Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getClassDeclarationChange()
		 * @generated
		 */
		EClass CLASS_DECLARATION_CHANGE = eINSTANCE.getClassDeclarationChange();

		/**
		 * The meta object literal for the '<em><b>Class Left</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_DECLARATION_CHANGE__CLASS_LEFT = eINSTANCE.getClassDeclarationChange_ClassLeft();

		/**
		 * The meta object literal for the '<em><b>Class Right</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_DECLARATION_CHANGE__CLASS_RIGHT = eINSTANCE.getClassDeclarationChange_ClassRight();

		/**
		 * The meta object literal for the '<em><b>Class Change</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_DECLARATION_CHANGE__CLASS_CHANGE = eINSTANCE.getClassDeclarationChange_ClassChange();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassInsertImpl <em>Class Insert</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassInsertImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getClassInsert()
		 * @generated
		 */
		EClass CLASS_INSERT = eINSTANCE.getClassInsert();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassDeleteImpl <em>Class Delete</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassDeleteImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getClassDelete()
		 * @generated
		 */
		EClass CLASS_DELETE = eINSTANCE.getClassDelete();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassModifierChangeImpl <em>Class Modifier Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassModifierChangeImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getClassModifierChange()
		 * @generated
		 */
		EClass CLASS_MODIFIER_CHANGE = eINSTANCE.getClassModifierChange();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange <em>Import Declaration Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getImportDeclarationChange()
		 * @generated
		 */
		EClass IMPORT_DECLARATION_CHANGE = eINSTANCE.getImportDeclarationChange();

		/**
		 * The meta object literal for the '<em><b>Import Left</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPORT_DECLARATION_CHANGE__IMPORT_LEFT = eINSTANCE.getImportDeclarationChange_ImportLeft();

		/**
		 * The meta object literal for the '<em><b>Import Right</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPORT_DECLARATION_CHANGE__IMPORT_RIGHT = eINSTANCE.getImportDeclarationChange_ImportRight();

		/**
		 * The meta object literal for the '<em><b>Compilation Unit Change</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPORT_DECLARATION_CHANGE__COMPILATION_UNIT_CHANGE = eINSTANCE.getImportDeclarationChange_CompilationUnitChange();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ImportInsertImpl <em>Import Insert</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.ImportInsertImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getImportInsert()
		 * @generated
		 */
		EClass IMPORT_INSERT = eINSTANCE.getImportInsert();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ImportDeleteImpl <em>Import Delete</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.ImportDeleteImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getImportDelete()
		 * @generated
		 */
		EClass IMPORT_DELETE = eINSTANCE.getImportDelete();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange <em>Method Declaration Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getMethodDeclarationChange()
		 * @generated
		 */
		EClass METHOD_DECLARATION_CHANGE = eINSTANCE.getMethodDeclarationChange();

		/**
		 * The meta object literal for the '<em><b>Method Change</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_DECLARATION_CHANGE__METHOD_CHANGE = eINSTANCE.getMethodDeclarationChange_MethodChange();

		/**
		 * The meta object literal for the '<em><b>Method Declaration Left</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_DECLARATION_CHANGE__METHOD_DECLARATION_LEFT = eINSTANCE.getMethodDeclarationChange_MethodDeclarationLeft();

		/**
		 * The meta object literal for the '<em><b>Method Declaration Right</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_DECLARATION_CHANGE__METHOD_DECLARATION_RIGHT = eINSTANCE.getMethodDeclarationChange_MethodDeclarationRight();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassChangeImpl <em>Class Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassChangeImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getClassChange()
		 * @generated
		 */
		EClass CLASS_CHANGE = eINSTANCE.getClassChange();

		/**
		 * The meta object literal for the '<em><b>Class Declaraction Changes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_CHANGE__CLASS_DECLARACTION_CHANGES = eINSTANCE.getClassChange_ClassDeclaractionChanges();

		/**
		 * The meta object literal for the '<em><b>Method Changes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_CHANGE__METHOD_CHANGES = eINSTANCE.getClassChange_MethodChanges();

		/**
		 * The meta object literal for the '<em><b>Compilation Unit Change</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_CHANGE__COMPILATION_UNIT_CHANGE = eINSTANCE.getClassChange_CompilationUnitChange();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodChangeImpl <em>Method Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodChangeImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getMethodChange()
		 * @generated
		 */
		EClass METHOD_CHANGE = eINSTANCE.getMethodChange();

		/**
		 * The meta object literal for the '<em><b>Method Declaration Changes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_CHANGE__METHOD_DECLARATION_CHANGES = eINSTANCE.getMethodChange_MethodDeclarationChanges();

		/**
		 * The meta object literal for the '<em><b>Statement Changes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_CHANGE__STATEMENT_CHANGES = eINSTANCE.getMethodChange_StatementChanges();

		/**
		 * The meta object literal for the '<em><b>Class Change</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_CHANGE__CLASS_CHANGE = eINSTANCE.getMethodChange_ClassChange();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodModifierChangeImpl <em>Method Modifier Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodModifierChangeImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getMethodModifierChange()
		 * @generated
		 */
		EClass METHOD_MODIFIER_CHANGE = eINSTANCE.getMethodModifierChange();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ReturnTypeChangeImpl <em>Return Type Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.ReturnTypeChangeImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getReturnTypeChange()
		 * @generated
		 */
		EClass RETURN_TYPE_CHANGE = eINSTANCE.getReturnTypeChange();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodParameterChangeImpl <em>Method Parameter Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodParameterChangeImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getMethodParameterChange()
		 * @generated
		 */
		EClass METHOD_PARAMETER_CHANGE = eINSTANCE.getMethodParameterChange();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodInsertImpl <em>Method Insert</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodInsertImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getMethodInsert()
		 * @generated
		 */
		EClass METHOD_INSERT = eINSTANCE.getMethodInsert();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodDeleteImpl <em>Method Delete</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodDeleteImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getMethodDelete()
		 * @generated
		 */
		EClass METHOD_DELETE = eINSTANCE.getMethodDelete();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.CompilationUnitChangeImpl <em>Compilation Unit Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.CompilationUnitChangeImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getCompilationUnitChange()
		 * @generated
		 */
		EClass COMPILATION_UNIT_CHANGE = eINSTANCE.getCompilationUnitChange();

		/**
		 * The meta object literal for the '<em><b>Class Changes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPILATION_UNIT_CHANGE__CLASS_CHANGES = eINSTANCE.getCompilationUnitChange_ClassChanges();

		/**
		 * The meta object literal for the '<em><b>Import Declaration Changes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPILATION_UNIT_CHANGE__IMPORT_DECLARATION_CHANGES = eINSTANCE.getCompilationUnitChange_ImportDeclarationChanges();

		/**
		 * The meta object literal for the '<em><b>Package Change</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPILATION_UNIT_CHANGE__PACKAGE_CHANGE = eINSTANCE.getCompilationUnitChange_PackageChange();

		/**
		 * The meta object literal for the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.PackageChangeImpl <em>Package Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.PackageChangeImpl
		 * @see org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffPackageImpl#getPackageChange()
		 * @generated
		 */
		EClass PACKAGE_CHANGE = eINSTANCE.getPackageChange();

		/**
		 * The meta object literal for the '<em><b>Compilation Unit Changes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_CHANGE__COMPILATION_UNIT_CHANGES = eINSTANCE.getPackageChange_CompilationUnitChanges();

		/**
		 * The meta object literal for the '<em><b>Sub Packages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_CHANGE__SUB_PACKAGES = eINSTANCE.getPackageChange_SubPackages();

		/**
		 * The meta object literal for the '<em><b>Package Left</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_CHANGE__PACKAGE_LEFT = eINSTANCE.getPackageChange_PackageLeft();

		/**
		 * The meta object literal for the '<em><b>Package Right</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_CHANGE__PACKAGE_RIGHT = eINSTANCE.getPackageChange_PackageRight();

	}

} //KDM2JavaDiffPackage
