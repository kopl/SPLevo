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
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STATEMENT_CHANGE__HIDE_ELEMENTS = JAVA2_KDM_DIFF_EXTENSION__HIDE_ELEMENTS;

	/**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STATEMENT_CHANGE__IS_COLLAPSED = JAVA2_KDM_DIFF_EXTENSION__IS_COLLAPSED;

	/**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STATEMENT_CHANGE__SUB_DIFF_ELEMENTS = JAVA2_KDM_DIFF_EXTENSION__SUB_DIFF_ELEMENTS;

	/**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STATEMENT_CHANGE__IS_HIDDEN_BY = JAVA2_KDM_DIFF_EXTENSION__IS_HIDDEN_BY;

	/**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STATEMENT_CHANGE__CONFLICTING = JAVA2_KDM_DIFF_EXTENSION__CONFLICTING;

	/**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STATEMENT_CHANGE__KIND = JAVA2_KDM_DIFF_EXTENSION__KIND;

	/**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STATEMENT_CHANGE__REMOTE = JAVA2_KDM_DIFF_EXTENSION__REMOTE;

	/**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STATEMENT_CHANGE__REQUIRES = JAVA2_KDM_DIFF_EXTENSION__REQUIRES;

	/**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STATEMENT_CHANGE__REQUIRED_BY = JAVA2_KDM_DIFF_EXTENSION__REQUIRED_BY;

	/**
     * The feature id for the '<em><b>Statement Right</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STATEMENT_CHANGE__STATEMENT_RIGHT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Statement Left</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STATEMENT_CHANGE__STATEMENT_LEFT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Statement Change</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STATEMENT_CHANGE_FEATURE_COUNT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 2;

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
     * The feature id for the '<em><b>Import Left</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMPORT_INSERT__IMPORT_LEFT = IMPORT_DECLARATION_CHANGE_FEATURE_COUNT + 0;

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
     * The feature id for the '<em><b>Import Right</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMPORT_DELETE__IMPORT_RIGHT = IMPORT_DECLARATION_CHANGE_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Left Container</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_DELETE__LEFT_CONTAINER = IMPORT_DECLARATION_CHANGE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Import Delete</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMPORT_DELETE_FEATURE_COUNT = IMPORT_DECLARATION_CHANGE_FEATURE_COUNT + 2;


	/**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ClassChangeImpl <em>Class Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.ClassChangeImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getClassChange()
     * @generated
     */
    int CLASS_CHANGE = 5;

    /**
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__HIDE_ELEMENTS = JAVA2_KDM_DIFF_EXTENSION__HIDE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__IS_COLLAPSED = JAVA2_KDM_DIFF_EXTENSION__IS_COLLAPSED;

    /**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__SUB_DIFF_ELEMENTS = JAVA2_KDM_DIFF_EXTENSION__SUB_DIFF_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__IS_HIDDEN_BY = JAVA2_KDM_DIFF_EXTENSION__IS_HIDDEN_BY;

    /**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__CONFLICTING = JAVA2_KDM_DIFF_EXTENSION__CONFLICTING;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__KIND = JAVA2_KDM_DIFF_EXTENSION__KIND;

    /**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__REMOTE = JAVA2_KDM_DIFF_EXTENSION__REMOTE;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__REQUIRES = JAVA2_KDM_DIFF_EXTENSION__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__REQUIRED_BY = JAVA2_KDM_DIFF_EXTENSION__REQUIRED_BY;

    /**
     * The number of structural features of the '<em>Class Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE_FEATURE_COUNT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImplementsInterfaceInsertImpl <em>Implements Interface Insert</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImplementsInterfaceInsertImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getImplementsInterfaceInsert()
     * @generated
     */
    int IMPLEMENTS_INTERFACE_INSERT = 6;

    /**
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_INSERT__HIDE_ELEMENTS = CLASS_CHANGE__HIDE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_INSERT__IS_COLLAPSED = CLASS_CHANGE__IS_COLLAPSED;

    /**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_INSERT__SUB_DIFF_ELEMENTS = CLASS_CHANGE__SUB_DIFF_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_INSERT__IS_HIDDEN_BY = CLASS_CHANGE__IS_HIDDEN_BY;

    /**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_INSERT__CONFLICTING = CLASS_CHANGE__CONFLICTING;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_INSERT__KIND = CLASS_CHANGE__KIND;

    /**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_INSERT__REMOTE = CLASS_CHANGE__REMOTE;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_INSERT__REQUIRES = CLASS_CHANGE__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_INSERT__REQUIRED_BY = CLASS_CHANGE__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Implemented Interface</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_INSERT__IMPLEMENTED_INTERFACE = CLASS_CHANGE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Changed Class</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_INSERT__CHANGED_CLASS = CLASS_CHANGE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Implements Interface Insert</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_INSERT_FEATURE_COUNT = CLASS_CHANGE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImplementsInterfaceDeleteImpl <em>Implements Interface Delete</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImplementsInterfaceDeleteImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getImplementsInterfaceDelete()
     * @generated
     */
    int IMPLEMENTS_INTERFACE_DELETE = 7;

    /**
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_DELETE__HIDE_ELEMENTS = CLASS_CHANGE__HIDE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_DELETE__IS_COLLAPSED = CLASS_CHANGE__IS_COLLAPSED;

    /**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_DELETE__SUB_DIFF_ELEMENTS = CLASS_CHANGE__SUB_DIFF_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_DELETE__IS_HIDDEN_BY = CLASS_CHANGE__IS_HIDDEN_BY;

    /**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_DELETE__CONFLICTING = CLASS_CHANGE__CONFLICTING;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_DELETE__KIND = CLASS_CHANGE__KIND;

    /**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_DELETE__REMOTE = CLASS_CHANGE__REMOTE;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_DELETE__REQUIRES = CLASS_CHANGE__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_DELETE__REQUIRED_BY = CLASS_CHANGE__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Implemented Interface</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_DELETE__IMPLEMENTED_INTERFACE = CLASS_CHANGE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Changed Class</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_DELETE__CHANGED_CLASS = CLASS_CHANGE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Implements Interface Delete</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_INTERFACE_DELETE_FEATURE_COUNT = CLASS_CHANGE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.FieldChangeImpl <em>Field Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.FieldChangeImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getFieldChange()
     * @generated
     */
    int FIELD_CHANGE = 8;

    /**
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__HIDE_ELEMENTS = JAVA2_KDM_DIFF_EXTENSION__HIDE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__IS_COLLAPSED = JAVA2_KDM_DIFF_EXTENSION__IS_COLLAPSED;

    /**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__SUB_DIFF_ELEMENTS = JAVA2_KDM_DIFF_EXTENSION__SUB_DIFF_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__IS_HIDDEN_BY = JAVA2_KDM_DIFF_EXTENSION__IS_HIDDEN_BY;

    /**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__CONFLICTING = JAVA2_KDM_DIFF_EXTENSION__CONFLICTING;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__KIND = JAVA2_KDM_DIFF_EXTENSION__KIND;

    /**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__REMOTE = JAVA2_KDM_DIFF_EXTENSION__REMOTE;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__REQUIRES = JAVA2_KDM_DIFF_EXTENSION__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__REQUIRED_BY = JAVA2_KDM_DIFF_EXTENSION__REQUIRED_BY;

    /**
     * The number of structural features of the '<em>Field Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE_FEATURE_COUNT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.FieldInsertImpl <em>Field Insert</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.FieldInsertImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getFieldInsert()
     * @generated
     */
    int FIELD_INSERT = 9;

    /**
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_INSERT__HIDE_ELEMENTS = FIELD_CHANGE__HIDE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_INSERT__IS_COLLAPSED = FIELD_CHANGE__IS_COLLAPSED;

    /**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_INSERT__SUB_DIFF_ELEMENTS = FIELD_CHANGE__SUB_DIFF_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_INSERT__IS_HIDDEN_BY = FIELD_CHANGE__IS_HIDDEN_BY;

    /**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_INSERT__CONFLICTING = FIELD_CHANGE__CONFLICTING;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_INSERT__KIND = FIELD_CHANGE__KIND;

    /**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_INSERT__REMOTE = FIELD_CHANGE__REMOTE;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_INSERT__REQUIRES = FIELD_CHANGE__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_INSERT__REQUIRED_BY = FIELD_CHANGE__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Field Left</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_INSERT__FIELD_LEFT = FIELD_CHANGE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Field Insert</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_INSERT_FEATURE_COUNT = FIELD_CHANGE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.FieldDeleteImpl <em>Field Delete</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.FieldDeleteImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getFieldDelete()
     * @generated
     */
    int FIELD_DELETE = 10;

    /**
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DELETE__HIDE_ELEMENTS = FIELD_CHANGE__HIDE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DELETE__IS_COLLAPSED = FIELD_CHANGE__IS_COLLAPSED;

    /**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DELETE__SUB_DIFF_ELEMENTS = FIELD_CHANGE__SUB_DIFF_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DELETE__IS_HIDDEN_BY = FIELD_CHANGE__IS_HIDDEN_BY;

    /**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DELETE__CONFLICTING = FIELD_CHANGE__CONFLICTING;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DELETE__KIND = FIELD_CHANGE__KIND;

    /**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DELETE__REMOTE = FIELD_CHANGE__REMOTE;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DELETE__REQUIRES = FIELD_CHANGE__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DELETE__REQUIRED_BY = FIELD_CHANGE__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Field Right</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DELETE__FIELD_RIGHT = FIELD_CHANGE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Left Container</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DELETE__LEFT_CONTAINER = FIELD_CHANGE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Field Delete</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DELETE_FEATURE_COUNT = FIELD_CHANGE_FEATURE_COUNT + 2;


    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ClassInsertImpl <em>Class Insert</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.ClassInsertImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getClassInsert()
     * @generated
     */
    int CLASS_INSERT = 11;

    /**
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_INSERT__HIDE_ELEMENTS = CLASS_CHANGE__HIDE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_INSERT__IS_COLLAPSED = CLASS_CHANGE__IS_COLLAPSED;

    /**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_INSERT__SUB_DIFF_ELEMENTS = CLASS_CHANGE__SUB_DIFF_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_INSERT__IS_HIDDEN_BY = CLASS_CHANGE__IS_HIDDEN_BY;

    /**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_INSERT__CONFLICTING = CLASS_CHANGE__CONFLICTING;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_INSERT__KIND = CLASS_CHANGE__KIND;

    /**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_INSERT__REMOTE = CLASS_CHANGE__REMOTE;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_INSERT__REQUIRES = CLASS_CHANGE__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_INSERT__REQUIRED_BY = CLASS_CHANGE__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Class Left</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_INSERT__CLASS_LEFT = CLASS_CHANGE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Class Insert</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_INSERT_FEATURE_COUNT = CLASS_CHANGE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ClassDeleteImpl <em>Class Delete</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.ClassDeleteImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getClassDelete()
     * @generated
     */
    int CLASS_DELETE = 12;

    /**
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_DELETE__HIDE_ELEMENTS = CLASS_CHANGE__HIDE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_DELETE__IS_COLLAPSED = CLASS_CHANGE__IS_COLLAPSED;

    /**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_DELETE__SUB_DIFF_ELEMENTS = CLASS_CHANGE__SUB_DIFF_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_DELETE__IS_HIDDEN_BY = CLASS_CHANGE__IS_HIDDEN_BY;

    /**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_DELETE__CONFLICTING = CLASS_CHANGE__CONFLICTING;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_DELETE__KIND = CLASS_CHANGE__KIND;

    /**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_DELETE__REMOTE = CLASS_CHANGE__REMOTE;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_DELETE__REQUIRES = CLASS_CHANGE__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_DELETE__REQUIRED_BY = CLASS_CHANGE__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Class Right</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_DELETE__CLASS_RIGHT = CLASS_CHANGE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Left Container</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_DELETE__LEFT_CONTAINER = CLASS_CHANGE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Class Delete</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_DELETE_FEATURE_COUNT = CLASS_CHANGE_FEATURE_COUNT + 2;


    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.PackageChangeImpl <em>Package Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.PackageChangeImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getPackageChange()
     * @generated
     */
    int PACKAGE_CHANGE = 13;

    /**
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__HIDE_ELEMENTS = JAVA2_KDM_DIFF_EXTENSION__HIDE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__IS_COLLAPSED = JAVA2_KDM_DIFF_EXTENSION__IS_COLLAPSED;

    /**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__SUB_DIFF_ELEMENTS = JAVA2_KDM_DIFF_EXTENSION__SUB_DIFF_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__IS_HIDDEN_BY = JAVA2_KDM_DIFF_EXTENSION__IS_HIDDEN_BY;

    /**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__CONFLICTING = JAVA2_KDM_DIFF_EXTENSION__CONFLICTING;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__KIND = JAVA2_KDM_DIFF_EXTENSION__KIND;

    /**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__REMOTE = JAVA2_KDM_DIFF_EXTENSION__REMOTE;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__REQUIRES = JAVA2_KDM_DIFF_EXTENSION__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__REQUIRED_BY = JAVA2_KDM_DIFF_EXTENSION__REQUIRED_BY;

    /**
     * The number of structural features of the '<em>Package Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE_FEATURE_COUNT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.PackageInsertImpl <em>Package Insert</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.PackageInsertImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getPackageInsert()
     * @generated
     */
    int PACKAGE_INSERT = 14;

    /**
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_INSERT__HIDE_ELEMENTS = PACKAGE_CHANGE__HIDE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_INSERT__IS_COLLAPSED = PACKAGE_CHANGE__IS_COLLAPSED;

    /**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_INSERT__SUB_DIFF_ELEMENTS = PACKAGE_CHANGE__SUB_DIFF_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_INSERT__IS_HIDDEN_BY = PACKAGE_CHANGE__IS_HIDDEN_BY;

    /**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_INSERT__CONFLICTING = PACKAGE_CHANGE__CONFLICTING;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_INSERT__KIND = PACKAGE_CHANGE__KIND;

    /**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_INSERT__REMOTE = PACKAGE_CHANGE__REMOTE;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_INSERT__REQUIRES = PACKAGE_CHANGE__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_INSERT__REQUIRED_BY = PACKAGE_CHANGE__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Package Left</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_INSERT__PACKAGE_LEFT = PACKAGE_CHANGE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Package Insert</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_INSERT_FEATURE_COUNT = PACKAGE_CHANGE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.PackageDeleteImpl <em>Package Delete</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.PackageDeleteImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getPackageDelete()
     * @generated
     */
    int PACKAGE_DELETE = 15;

    /**
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_DELETE__HIDE_ELEMENTS = PACKAGE_CHANGE__HIDE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_DELETE__IS_COLLAPSED = PACKAGE_CHANGE__IS_COLLAPSED;

    /**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_DELETE__SUB_DIFF_ELEMENTS = PACKAGE_CHANGE__SUB_DIFF_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_DELETE__IS_HIDDEN_BY = PACKAGE_CHANGE__IS_HIDDEN_BY;

    /**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_DELETE__CONFLICTING = PACKAGE_CHANGE__CONFLICTING;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_DELETE__KIND = PACKAGE_CHANGE__KIND;

    /**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_DELETE__REMOTE = PACKAGE_CHANGE__REMOTE;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_DELETE__REQUIRES = PACKAGE_CHANGE__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_DELETE__REQUIRED_BY = PACKAGE_CHANGE__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Package Right</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_DELETE__PACKAGE_RIGHT = PACKAGE_CHANGE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Left Container</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_DELETE__LEFT_CONTAINER = PACKAGE_CHANGE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Package Delete</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_DELETE_FEATURE_COUNT = PACKAGE_CHANGE_FEATURE_COUNT + 2;


    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.MethodChangeImpl <em>Method Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.MethodChangeImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getMethodChange()
     * @generated
     */
    int METHOD_CHANGE = 16;

    /**
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__HIDE_ELEMENTS = JAVA2_KDM_DIFF_EXTENSION__HIDE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__IS_COLLAPSED = JAVA2_KDM_DIFF_EXTENSION__IS_COLLAPSED;

    /**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__SUB_DIFF_ELEMENTS = JAVA2_KDM_DIFF_EXTENSION__SUB_DIFF_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__IS_HIDDEN_BY = JAVA2_KDM_DIFF_EXTENSION__IS_HIDDEN_BY;

    /**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__CONFLICTING = JAVA2_KDM_DIFF_EXTENSION__CONFLICTING;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__KIND = JAVA2_KDM_DIFF_EXTENSION__KIND;

    /**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__REMOTE = JAVA2_KDM_DIFF_EXTENSION__REMOTE;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__REQUIRES = JAVA2_KDM_DIFF_EXTENSION__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__REQUIRED_BY = JAVA2_KDM_DIFF_EXTENSION__REQUIRED_BY;

    /**
     * The number of structural features of the '<em>Method Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE_FEATURE_COUNT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.MethodInsertImpl <em>Method Insert</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.MethodInsertImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getMethodInsert()
     * @generated
     */
    int METHOD_INSERT = 17;

    /**
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_INSERT__HIDE_ELEMENTS = METHOD_CHANGE__HIDE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_INSERT__IS_COLLAPSED = METHOD_CHANGE__IS_COLLAPSED;

    /**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_INSERT__SUB_DIFF_ELEMENTS = METHOD_CHANGE__SUB_DIFF_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_INSERT__IS_HIDDEN_BY = METHOD_CHANGE__IS_HIDDEN_BY;

    /**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_INSERT__CONFLICTING = METHOD_CHANGE__CONFLICTING;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_INSERT__KIND = METHOD_CHANGE__KIND;

    /**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_INSERT__REMOTE = METHOD_CHANGE__REMOTE;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_INSERT__REQUIRES = METHOD_CHANGE__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_INSERT__REQUIRED_BY = METHOD_CHANGE__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Method Left</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_INSERT__METHOD_LEFT = METHOD_CHANGE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Method Insert</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_INSERT_FEATURE_COUNT = METHOD_CHANGE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.MethodDeleteImpl <em>Method Delete</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.MethodDeleteImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getMethodDelete()
     * @generated
     */
    int METHOD_DELETE = 18;

    /**
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_DELETE__HIDE_ELEMENTS = METHOD_CHANGE__HIDE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_DELETE__IS_COLLAPSED = METHOD_CHANGE__IS_COLLAPSED;

    /**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_DELETE__SUB_DIFF_ELEMENTS = METHOD_CHANGE__SUB_DIFF_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_DELETE__IS_HIDDEN_BY = METHOD_CHANGE__IS_HIDDEN_BY;

    /**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_DELETE__CONFLICTING = METHOD_CHANGE__CONFLICTING;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_DELETE__KIND = METHOD_CHANGE__KIND;

    /**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_DELETE__REMOTE = METHOD_CHANGE__REMOTE;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_DELETE__REQUIRES = METHOD_CHANGE__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_DELETE__REQUIRED_BY = METHOD_CHANGE__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Method Right</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_DELETE__METHOD_RIGHT = METHOD_CHANGE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Left Container</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_DELETE__LEFT_CONTAINER = METHOD_CHANGE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Method Delete</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_DELETE_FEATURE_COUNT = METHOD_CHANGE_FEATURE_COUNT + 2;


    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.StatementInsertImpl <em>Statement Insert</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.StatementInsertImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getStatementInsert()
     * @generated
     */
    int STATEMENT_INSERT = 19;

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
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_INSERT__REQUIRES = STATEMENT_CHANGE__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_INSERT__REQUIRED_BY = STATEMENT_CHANGE__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Statement Right</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_INSERT__STATEMENT_RIGHT = STATEMENT_CHANGE__STATEMENT_RIGHT;

    /**
     * The feature id for the '<em><b>Statement Left</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_INSERT__STATEMENT_LEFT = STATEMENT_CHANGE__STATEMENT_LEFT;

    /**
     * The number of structural features of the '<em>Statement Insert</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_INSERT_FEATURE_COUNT = STATEMENT_CHANGE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.StatementDeleteImpl <em>Statement Delete</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.StatementDeleteImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getStatementDelete()
     * @generated
     */
    int STATEMENT_DELETE = 20;

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
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_DELETE__REQUIRES = STATEMENT_CHANGE__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_DELETE__REQUIRED_BY = STATEMENT_CHANGE__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Statement Right</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_DELETE__STATEMENT_RIGHT = STATEMENT_CHANGE__STATEMENT_RIGHT;

    /**
     * The feature id for the '<em><b>Statement Left</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_DELETE__STATEMENT_LEFT = STATEMENT_CHANGE__STATEMENT_LEFT;

    /**
     * The number of structural features of the '<em>Statement Delete</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_DELETE_FEATURE_COUNT = STATEMENT_CHANGE_FEATURE_COUNT + 0;


    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.FieldDeclarationChangeImpl <em>Field Declaration Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.FieldDeclarationChangeImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getFieldDeclarationChange()
     * @generated
     */
    int FIELD_DECLARATION_CHANGE = 21;

    /**
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DECLARATION_CHANGE__HIDE_ELEMENTS = FIELD_CHANGE__HIDE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DECLARATION_CHANGE__IS_COLLAPSED = FIELD_CHANGE__IS_COLLAPSED;

    /**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DECLARATION_CHANGE__SUB_DIFF_ELEMENTS = FIELD_CHANGE__SUB_DIFF_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DECLARATION_CHANGE__IS_HIDDEN_BY = FIELD_CHANGE__IS_HIDDEN_BY;

    /**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DECLARATION_CHANGE__CONFLICTING = FIELD_CHANGE__CONFLICTING;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DECLARATION_CHANGE__KIND = FIELD_CHANGE__KIND;

    /**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DECLARATION_CHANGE__REMOTE = FIELD_CHANGE__REMOTE;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DECLARATION_CHANGE__REQUIRES = FIELD_CHANGE__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DECLARATION_CHANGE__REQUIRED_BY = FIELD_CHANGE__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Field Left</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DECLARATION_CHANGE__FIELD_LEFT = FIELD_CHANGE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Field Right</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DECLARATION_CHANGE__FIELD_RIGHT = FIELD_CHANGE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Field Declaration Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_DECLARATION_CHANGE_FEATURE_COUNT = FIELD_CHANGE_FEATURE_COUNT + 2;


    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.EnumChangeImpl <em>Enum Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.EnumChangeImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getEnumChange()
     * @generated
     */
    int ENUM_CHANGE = 22;

    /**
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__HIDE_ELEMENTS = JAVA2_KDM_DIFF_EXTENSION__HIDE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__IS_COLLAPSED = JAVA2_KDM_DIFF_EXTENSION__IS_COLLAPSED;

    /**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__SUB_DIFF_ELEMENTS = JAVA2_KDM_DIFF_EXTENSION__SUB_DIFF_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__IS_HIDDEN_BY = JAVA2_KDM_DIFF_EXTENSION__IS_HIDDEN_BY;

    /**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__CONFLICTING = JAVA2_KDM_DIFF_EXTENSION__CONFLICTING;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__KIND = JAVA2_KDM_DIFF_EXTENSION__KIND;

    /**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__REMOTE = JAVA2_KDM_DIFF_EXTENSION__REMOTE;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__REQUIRES = JAVA2_KDM_DIFF_EXTENSION__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__REQUIRED_BY = JAVA2_KDM_DIFF_EXTENSION__REQUIRED_BY;

    /**
     * The number of structural features of the '<em>Enum Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE_FEATURE_COUNT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.EnumDeclarationChangeImpl <em>Enum Declaration Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.EnumDeclarationChangeImpl
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getEnumDeclarationChange()
     * @generated
     */
    int ENUM_DECLARATION_CHANGE = 23;

    /**
     * The feature id for the '<em><b>Hide Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_DECLARATION_CHANGE__HIDE_ELEMENTS = ENUM_CHANGE__HIDE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Collapsed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_DECLARATION_CHANGE__IS_COLLAPSED = ENUM_CHANGE__IS_COLLAPSED;

    /**
     * The feature id for the '<em><b>Sub Diff Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_DECLARATION_CHANGE__SUB_DIFF_ELEMENTS = ENUM_CHANGE__SUB_DIFF_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is Hidden By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_DECLARATION_CHANGE__IS_HIDDEN_BY = ENUM_CHANGE__IS_HIDDEN_BY;

    /**
     * The feature id for the '<em><b>Conflicting</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_DECLARATION_CHANGE__CONFLICTING = ENUM_CHANGE__CONFLICTING;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_DECLARATION_CHANGE__KIND = ENUM_CHANGE__KIND;

    /**
     * The feature id for the '<em><b>Remote</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_DECLARATION_CHANGE__REMOTE = ENUM_CHANGE__REMOTE;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_DECLARATION_CHANGE__REQUIRES = ENUM_CHANGE__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_DECLARATION_CHANGE__REQUIRED_BY = ENUM_CHANGE__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Enum Left</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_DECLARATION_CHANGE__ENUM_LEFT = ENUM_CHANGE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Enum Right</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_DECLARATION_CHANGE__ENUM_RIGHT = ENUM_CHANGE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Enum Declaration Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_DECLARATION_CHANGE_FEATURE_COUNT = ENUM_CHANGE_FEATURE_COUNT + 2;


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
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange#getStatementRight <em>Statement Right</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Statement Right</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange#getStatementRight()
     * @see #getStatementChange()
     * @generated
     */
	EReference getStatementChange_StatementRight();

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
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImportInsert#getImportLeft <em>Import Left</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Import Left</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.ImportInsert#getImportLeft()
     * @see #getImportInsert()
     * @generated
     */
	EReference getImportInsert_ImportLeft();

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
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete#getImportRight <em>Import Right</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Import Right</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete#getImportRight()
     * @see #getImportDelete()
     * @generated
     */
	EReference getImportDelete_ImportRight();

	/**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete#getLeftContainer <em>Left Container</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Left Container</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete#getLeftContainer()
     * @see #getImportDelete()
     * @generated
     */
    EReference getImportDelete_LeftContainer();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ClassChange <em>Class Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Class Change</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.ClassChange
     * @generated
     */
    EClass getClassChange();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceInsert <em>Implements Interface Insert</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Implements Interface Insert</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceInsert
     * @generated
     */
    EClass getImplementsInterfaceInsert();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceInsert#getImplementedInterface <em>Implemented Interface</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Implemented Interface</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceInsert#getImplementedInterface()
     * @see #getImplementsInterfaceInsert()
     * @generated
     */
    EReference getImplementsInterfaceInsert_ImplementedInterface();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceInsert#getChangedClass <em>Changed Class</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Class</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceInsert#getChangedClass()
     * @see #getImplementsInterfaceInsert()
     * @generated
     */
    EReference getImplementsInterfaceInsert_ChangedClass();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceDelete <em>Implements Interface Delete</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Implements Interface Delete</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceDelete
     * @generated
     */
    EClass getImplementsInterfaceDelete();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceDelete#getImplementedInterface <em>Implemented Interface</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Implemented Interface</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceDelete#getImplementedInterface()
     * @see #getImplementsInterfaceDelete()
     * @generated
     */
    EReference getImplementsInterfaceDelete_ImplementedInterface();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceDelete#getChangedClass <em>Changed Class</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Class</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceDelete#getChangedClass()
     * @see #getImplementsInterfaceDelete()
     * @generated
     */
    EReference getImplementsInterfaceDelete_ChangedClass();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldChange <em>Field Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Field Change</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.FieldChange
     * @generated
     */
    EClass getFieldChange();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldInsert <em>Field Insert</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Field Insert</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.FieldInsert
     * @generated
     */
    EClass getFieldInsert();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldInsert#getFieldLeft <em>Field Left</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Field Left</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.FieldInsert#getFieldLeft()
     * @see #getFieldInsert()
     * @generated
     */
    EReference getFieldInsert_FieldLeft();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldDelete <em>Field Delete</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Field Delete</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.FieldDelete
     * @generated
     */
    EClass getFieldDelete();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldDelete#getFieldRight <em>Field Right</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Field Right</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.FieldDelete#getFieldRight()
     * @see #getFieldDelete()
     * @generated
     */
    EReference getFieldDelete_FieldRight();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldDelete#getLeftContainer <em>Left Container</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Left Container</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.FieldDelete#getLeftContainer()
     * @see #getFieldDelete()
     * @generated
     */
    EReference getFieldDelete_LeftContainer();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ClassInsert <em>Class Insert</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Class Insert</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.ClassInsert
     * @generated
     */
    EClass getClassInsert();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ClassInsert#getClassLeft <em>Class Left</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Class Left</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.ClassInsert#getClassLeft()
     * @see #getClassInsert()
     * @generated
     */
    EReference getClassInsert_ClassLeft();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ClassDelete <em>Class Delete</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Class Delete</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.ClassDelete
     * @generated
     */
    EClass getClassDelete();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ClassDelete#getClassRight <em>Class Right</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Class Right</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.ClassDelete#getClassRight()
     * @see #getClassDelete()
     * @generated
     */
    EReference getClassDelete_ClassRight();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ClassDelete#getLeftContainer <em>Left Container</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Left Container</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.ClassDelete#getLeftContainer()
     * @see #getClassDelete()
     * @generated
     */
    EReference getClassDelete_LeftContainer();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.PackageChange <em>Package Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Package Change</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.PackageChange
     * @generated
     */
    EClass getPackageChange();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.PackageInsert <em>Package Insert</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Package Insert</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.PackageInsert
     * @generated
     */
    EClass getPackageInsert();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.PackageInsert#getPackageLeft <em>Package Left</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Package Left</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.PackageInsert#getPackageLeft()
     * @see #getPackageInsert()
     * @generated
     */
    EReference getPackageInsert_PackageLeft();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.PackageDelete <em>Package Delete</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Package Delete</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.PackageDelete
     * @generated
     */
    EClass getPackageDelete();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.PackageDelete#getPackageRight <em>Package Right</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Package Right</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.PackageDelete#getPackageRight()
     * @see #getPackageDelete()
     * @generated
     */
    EReference getPackageDelete_PackageRight();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.PackageDelete#getLeftContainer <em>Left Container</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Left Container</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.PackageDelete#getLeftContainer()
     * @see #getPackageDelete()
     * @generated
     */
    EReference getPackageDelete_LeftContainer();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.MethodChange <em>Method Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Method Change</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.MethodChange
     * @generated
     */
    EClass getMethodChange();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.MethodInsert <em>Method Insert</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Method Insert</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.MethodInsert
     * @generated
     */
    EClass getMethodInsert();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.MethodInsert#getMethodLeft <em>Method Left</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Method Left</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.MethodInsert#getMethodLeft()
     * @see #getMethodInsert()
     * @generated
     */
    EReference getMethodInsert_MethodLeft();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.MethodDelete <em>Method Delete</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Method Delete</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.MethodDelete
     * @generated
     */
    EClass getMethodDelete();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.MethodDelete#getMethodRight <em>Method Right</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Method Right</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.MethodDelete#getMethodRight()
     * @see #getMethodDelete()
     * @generated
     */
    EReference getMethodDelete_MethodRight();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.MethodDelete#getLeftContainer <em>Left Container</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Left Container</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.MethodDelete#getLeftContainer()
     * @see #getMethodDelete()
     * @generated
     */
    EReference getMethodDelete_LeftContainer();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementInsert <em>Statement Insert</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Statement Insert</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.StatementInsert
     * @generated
     */
    EClass getStatementInsert();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementDelete <em>Statement Delete</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Statement Delete</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.StatementDelete
     * @generated
     */
    EClass getStatementDelete();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldDeclarationChange <em>Field Declaration Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Field Declaration Change</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.FieldDeclarationChange
     * @generated
     */
    EClass getFieldDeclarationChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldDeclarationChange#getFieldLeft <em>Field Left</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Field Left</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.FieldDeclarationChange#getFieldLeft()
     * @see #getFieldDeclarationChange()
     * @generated
     */
    EReference getFieldDeclarationChange_FieldLeft();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldDeclarationChange#getFieldRight <em>Field Right</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Field Right</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.FieldDeclarationChange#getFieldRight()
     * @see #getFieldDeclarationChange()
     * @generated
     */
    EReference getFieldDeclarationChange_FieldRight();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.EnumChange <em>Enum Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Enum Change</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.EnumChange
     * @generated
     */
    EClass getEnumChange();

    /**
     * Returns the meta object for class '{@link org.splevo.diffing.emfcompare.java2kdmdiff.EnumDeclarationChange <em>Enum Declaration Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Enum Declaration Change</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.EnumDeclarationChange
     * @generated
     */
    EClass getEnumDeclarationChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.EnumDeclarationChange#getEnumLeft <em>Enum Left</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Enum Left</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.EnumDeclarationChange#getEnumLeft()
     * @see #getEnumDeclarationChange()
     * @generated
     */
    EReference getEnumDeclarationChange_EnumLeft();

    /**
     * Returns the meta object for the reference '{@link org.splevo.diffing.emfcompare.java2kdmdiff.EnumDeclarationChange#getEnumRight <em>Enum Right</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Enum Right</em>'.
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.EnumDeclarationChange#getEnumRight()
     * @see #getEnumDeclarationChange()
     * @generated
     */
    EReference getEnumDeclarationChange_EnumRight();

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
         * The meta object literal for the '<em><b>Statement Right</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference STATEMENT_CHANGE__STATEMENT_RIGHT = eINSTANCE.getStatementChange_StatementRight();

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
         * The meta object literal for the '<em><b>Import Left</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference IMPORT_INSERT__IMPORT_LEFT = eINSTANCE.getImportInsert_ImportLeft();

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
         * The meta object literal for the '<em><b>Import Right</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference IMPORT_DELETE__IMPORT_RIGHT = eINSTANCE.getImportDelete_ImportRight();

        /**
         * The meta object literal for the '<em><b>Left Container</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference IMPORT_DELETE__LEFT_CONTAINER = eINSTANCE.getImportDelete_LeftContainer();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ClassChangeImpl <em>Class Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.ClassChangeImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getClassChange()
         * @generated
         */
        EClass CLASS_CHANGE = eINSTANCE.getClassChange();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImplementsInterfaceInsertImpl <em>Implements Interface Insert</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImplementsInterfaceInsertImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getImplementsInterfaceInsert()
         * @generated
         */
        EClass IMPLEMENTS_INTERFACE_INSERT = eINSTANCE.getImplementsInterfaceInsert();

        /**
         * The meta object literal for the '<em><b>Implemented Interface</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference IMPLEMENTS_INTERFACE_INSERT__IMPLEMENTED_INTERFACE = eINSTANCE.getImplementsInterfaceInsert_ImplementedInterface();

        /**
         * The meta object literal for the '<em><b>Changed Class</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference IMPLEMENTS_INTERFACE_INSERT__CHANGED_CLASS = eINSTANCE.getImplementsInterfaceInsert_ChangedClass();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImplementsInterfaceDeleteImpl <em>Implements Interface Delete</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImplementsInterfaceDeleteImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getImplementsInterfaceDelete()
         * @generated
         */
        EClass IMPLEMENTS_INTERFACE_DELETE = eINSTANCE.getImplementsInterfaceDelete();

        /**
         * The meta object literal for the '<em><b>Implemented Interface</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference IMPLEMENTS_INTERFACE_DELETE__IMPLEMENTED_INTERFACE = eINSTANCE.getImplementsInterfaceDelete_ImplementedInterface();

        /**
         * The meta object literal for the '<em><b>Changed Class</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference IMPLEMENTS_INTERFACE_DELETE__CHANGED_CLASS = eINSTANCE.getImplementsInterfaceDelete_ChangedClass();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.FieldChangeImpl <em>Field Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.FieldChangeImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getFieldChange()
         * @generated
         */
        EClass FIELD_CHANGE = eINSTANCE.getFieldChange();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.FieldInsertImpl <em>Field Insert</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.FieldInsertImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getFieldInsert()
         * @generated
         */
        EClass FIELD_INSERT = eINSTANCE.getFieldInsert();

        /**
         * The meta object literal for the '<em><b>Field Left</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FIELD_INSERT__FIELD_LEFT = eINSTANCE.getFieldInsert_FieldLeft();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.FieldDeleteImpl <em>Field Delete</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.FieldDeleteImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getFieldDelete()
         * @generated
         */
        EClass FIELD_DELETE = eINSTANCE.getFieldDelete();

        /**
         * The meta object literal for the '<em><b>Field Right</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FIELD_DELETE__FIELD_RIGHT = eINSTANCE.getFieldDelete_FieldRight();

        /**
         * The meta object literal for the '<em><b>Left Container</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FIELD_DELETE__LEFT_CONTAINER = eINSTANCE.getFieldDelete_LeftContainer();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ClassInsertImpl <em>Class Insert</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.ClassInsertImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getClassInsert()
         * @generated
         */
        EClass CLASS_INSERT = eINSTANCE.getClassInsert();

        /**
         * The meta object literal for the '<em><b>Class Left</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CLASS_INSERT__CLASS_LEFT = eINSTANCE.getClassInsert_ClassLeft();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ClassDeleteImpl <em>Class Delete</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.ClassDeleteImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getClassDelete()
         * @generated
         */
        EClass CLASS_DELETE = eINSTANCE.getClassDelete();

        /**
         * The meta object literal for the '<em><b>Class Right</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CLASS_DELETE__CLASS_RIGHT = eINSTANCE.getClassDelete_ClassRight();

        /**
         * The meta object literal for the '<em><b>Left Container</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CLASS_DELETE__LEFT_CONTAINER = eINSTANCE.getClassDelete_LeftContainer();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.PackageChangeImpl <em>Package Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.PackageChangeImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getPackageChange()
         * @generated
         */
        EClass PACKAGE_CHANGE = eINSTANCE.getPackageChange();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.PackageInsertImpl <em>Package Insert</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.PackageInsertImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getPackageInsert()
         * @generated
         */
        EClass PACKAGE_INSERT = eINSTANCE.getPackageInsert();

        /**
         * The meta object literal for the '<em><b>Package Left</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PACKAGE_INSERT__PACKAGE_LEFT = eINSTANCE.getPackageInsert_PackageLeft();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.PackageDeleteImpl <em>Package Delete</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.PackageDeleteImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getPackageDelete()
         * @generated
         */
        EClass PACKAGE_DELETE = eINSTANCE.getPackageDelete();

        /**
         * The meta object literal for the '<em><b>Package Right</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PACKAGE_DELETE__PACKAGE_RIGHT = eINSTANCE.getPackageDelete_PackageRight();

        /**
         * The meta object literal for the '<em><b>Left Container</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PACKAGE_DELETE__LEFT_CONTAINER = eINSTANCE.getPackageDelete_LeftContainer();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.MethodChangeImpl <em>Method Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.MethodChangeImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getMethodChange()
         * @generated
         */
        EClass METHOD_CHANGE = eINSTANCE.getMethodChange();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.MethodInsertImpl <em>Method Insert</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.MethodInsertImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getMethodInsert()
         * @generated
         */
        EClass METHOD_INSERT = eINSTANCE.getMethodInsert();

        /**
         * The meta object literal for the '<em><b>Method Left</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference METHOD_INSERT__METHOD_LEFT = eINSTANCE.getMethodInsert_MethodLeft();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.MethodDeleteImpl <em>Method Delete</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.MethodDeleteImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getMethodDelete()
         * @generated
         */
        EClass METHOD_DELETE = eINSTANCE.getMethodDelete();

        /**
         * The meta object literal for the '<em><b>Method Right</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference METHOD_DELETE__METHOD_RIGHT = eINSTANCE.getMethodDelete_MethodRight();

        /**
         * The meta object literal for the '<em><b>Left Container</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference METHOD_DELETE__LEFT_CONTAINER = eINSTANCE.getMethodDelete_LeftContainer();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.StatementInsertImpl <em>Statement Insert</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.StatementInsertImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getStatementInsert()
         * @generated
         */
        EClass STATEMENT_INSERT = eINSTANCE.getStatementInsert();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.StatementDeleteImpl <em>Statement Delete</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.StatementDeleteImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getStatementDelete()
         * @generated
         */
        EClass STATEMENT_DELETE = eINSTANCE.getStatementDelete();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.FieldDeclarationChangeImpl <em>Field Declaration Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.FieldDeclarationChangeImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getFieldDeclarationChange()
         * @generated
         */
        EClass FIELD_DECLARATION_CHANGE = eINSTANCE.getFieldDeclarationChange();

        /**
         * The meta object literal for the '<em><b>Field Left</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FIELD_DECLARATION_CHANGE__FIELD_LEFT = eINSTANCE.getFieldDeclarationChange_FieldLeft();

        /**
         * The meta object literal for the '<em><b>Field Right</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FIELD_DECLARATION_CHANGE__FIELD_RIGHT = eINSTANCE.getFieldDeclarationChange_FieldRight();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.EnumChangeImpl <em>Enum Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.EnumChangeImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getEnumChange()
         * @generated
         */
        EClass ENUM_CHANGE = eINSTANCE.getEnumChange();

        /**
         * The meta object literal for the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.EnumDeclarationChangeImpl <em>Enum Declaration Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.EnumDeclarationChangeImpl
         * @see org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getEnumDeclarationChange()
         * @generated
         */
        EClass ENUM_DECLARATION_CHANGE = eINSTANCE.getEnumDeclarationChange();

        /**
         * The meta object literal for the '<em><b>Enum Left</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ENUM_DECLARATION_CHANGE__ENUM_LEFT = eINSTANCE.getEnumDeclarationChange_EnumLeft();

        /**
         * The meta object literal for the '<em><b>Enum Right</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ENUM_DECLARATION_CHANGE__ENUM_RIGHT = eINSTANCE.getEnumDeclarationChange_EnumRight();

	}

} //Java2KDMDiffPackage
