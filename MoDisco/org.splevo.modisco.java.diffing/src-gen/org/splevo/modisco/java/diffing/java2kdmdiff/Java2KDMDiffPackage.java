package org.splevo.modisco.java.diffing.java2kdmdiff;

import org.eclipse.emf.compare.ComparePackage;
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
 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffFactory
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
    Java2KDMDiffPackage eINSTANCE = org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffPackageImpl.init();

    /**
     * The meta object id for the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffExtensionImpl <em>Extension</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffExtensionImpl
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getJava2KDMDiffExtension()
     * @generated
     */
    int JAVA2_KDM_DIFF_EXTENSION = 0;

    /**
     * The feature id for the '<em><b>Match</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JAVA2_KDM_DIFF_EXTENSION__MATCH = ComparePackage.DIFF__MATCH;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JAVA2_KDM_DIFF_EXTENSION__REQUIRES = ComparePackage.DIFF__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JAVA2_KDM_DIFF_EXTENSION__REQUIRED_BY = ComparePackage.DIFF__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JAVA2_KDM_DIFF_EXTENSION__REFINES = ComparePackage.DIFF__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JAVA2_KDM_DIFF_EXTENSION__REFINED_BY = ComparePackage.DIFF__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JAVA2_KDM_DIFF_EXTENSION__KIND = ComparePackage.DIFF__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JAVA2_KDM_DIFF_EXTENSION__SOURCE = ComparePackage.DIFF__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JAVA2_KDM_DIFF_EXTENSION__STATE = ComparePackage.DIFF__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JAVA2_KDM_DIFF_EXTENSION__EQUIVALENCE = ComparePackage.DIFF__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JAVA2_KDM_DIFF_EXTENSION__CONFLICT = ComparePackage.DIFF__CONFLICT;

    /**
     * The number of structural features of the '<em>Extension</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT = ComparePackage.DIFF_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.StatementChangeImpl <em>Statement Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.StatementChangeImpl
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getStatementChange()
     * @generated
     */
    int STATEMENT_CHANGE = 1;

    /**
     * The feature id for the '<em><b>Match</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__MATCH = JAVA2_KDM_DIFF_EXTENSION__MATCH;

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
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__REFINES = JAVA2_KDM_DIFF_EXTENSION__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__REFINED_BY = JAVA2_KDM_DIFF_EXTENSION__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__KIND = JAVA2_KDM_DIFF_EXTENSION__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__SOURCE = JAVA2_KDM_DIFF_EXTENSION__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__STATE = JAVA2_KDM_DIFF_EXTENSION__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__EQUIVALENCE = JAVA2_KDM_DIFF_EXTENSION__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__CONFLICT = JAVA2_KDM_DIFF_EXTENSION__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Statement</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__CHANGED_STATEMENT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Statement Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE_FEATURE_COUNT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.ImportChangeImpl <em>Import Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.ImportChangeImpl
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getImportChange()
     * @generated
     */
    int IMPORT_CHANGE = 2;

    /**
     * The feature id for the '<em><b>Match</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__MATCH = JAVA2_KDM_DIFF_EXTENSION__MATCH;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__REQUIRES = JAVA2_KDM_DIFF_EXTENSION__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__REQUIRED_BY = JAVA2_KDM_DIFF_EXTENSION__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__REFINES = JAVA2_KDM_DIFF_EXTENSION__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__REFINED_BY = JAVA2_KDM_DIFF_EXTENSION__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__KIND = JAVA2_KDM_DIFF_EXTENSION__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__SOURCE = JAVA2_KDM_DIFF_EXTENSION__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__STATE = JAVA2_KDM_DIFF_EXTENSION__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__EQUIVALENCE = JAVA2_KDM_DIFF_EXTENSION__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__CONFLICT = JAVA2_KDM_DIFF_EXTENSION__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Import</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__CHANGED_IMPORT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Import Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE_FEATURE_COUNT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.ClassChangeImpl <em>Class Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.ClassChangeImpl
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getClassChange()
     * @generated
     */
    int CLASS_CHANGE = 3;

    /**
     * The feature id for the '<em><b>Match</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__MATCH = JAVA2_KDM_DIFF_EXTENSION__MATCH;

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
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__REFINES = JAVA2_KDM_DIFF_EXTENSION__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__REFINED_BY = JAVA2_KDM_DIFF_EXTENSION__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__KIND = JAVA2_KDM_DIFF_EXTENSION__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__SOURCE = JAVA2_KDM_DIFF_EXTENSION__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__STATE = JAVA2_KDM_DIFF_EXTENSION__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__EQUIVALENCE = JAVA2_KDM_DIFF_EXTENSION__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__CONFLICT = JAVA2_KDM_DIFF_EXTENSION__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Class</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__CHANGED_CLASS = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Class Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE_FEATURE_COUNT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.FieldChangeImpl <em>Field Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.FieldChangeImpl
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getFieldChange()
     * @generated
     */
    int FIELD_CHANGE = 4;

    /**
     * The feature id for the '<em><b>Match</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__MATCH = JAVA2_KDM_DIFF_EXTENSION__MATCH;

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
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__REFINES = JAVA2_KDM_DIFF_EXTENSION__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__REFINED_BY = JAVA2_KDM_DIFF_EXTENSION__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__KIND = JAVA2_KDM_DIFF_EXTENSION__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__SOURCE = JAVA2_KDM_DIFF_EXTENSION__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__STATE = JAVA2_KDM_DIFF_EXTENSION__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__EQUIVALENCE = JAVA2_KDM_DIFF_EXTENSION__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__CONFLICT = JAVA2_KDM_DIFF_EXTENSION__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Field</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__CHANGED_FIELD = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Field Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE_FEATURE_COUNT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.PackageChangeImpl <em>Package Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.PackageChangeImpl
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getPackageChange()
     * @generated
     */
    int PACKAGE_CHANGE = 5;

    /**
     * The feature id for the '<em><b>Match</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__MATCH = JAVA2_KDM_DIFF_EXTENSION__MATCH;

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
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__REFINES = JAVA2_KDM_DIFF_EXTENSION__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__REFINED_BY = JAVA2_KDM_DIFF_EXTENSION__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__KIND = JAVA2_KDM_DIFF_EXTENSION__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__SOURCE = JAVA2_KDM_DIFF_EXTENSION__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__STATE = JAVA2_KDM_DIFF_EXTENSION__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__EQUIVALENCE = JAVA2_KDM_DIFF_EXTENSION__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__CONFLICT = JAVA2_KDM_DIFF_EXTENSION__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Package</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__CHANGED_PACKAGE = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Package Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE_FEATURE_COUNT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.MethodChangeImpl <em>Method Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.MethodChangeImpl
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getMethodChange()
     * @generated
     */
    int METHOD_CHANGE = 6;

    /**
     * The feature id for the '<em><b>Match</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__MATCH = JAVA2_KDM_DIFF_EXTENSION__MATCH;

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
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__REFINES = JAVA2_KDM_DIFF_EXTENSION__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__REFINED_BY = JAVA2_KDM_DIFF_EXTENSION__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__KIND = JAVA2_KDM_DIFF_EXTENSION__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__SOURCE = JAVA2_KDM_DIFF_EXTENSION__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__STATE = JAVA2_KDM_DIFF_EXTENSION__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__EQUIVALENCE = JAVA2_KDM_DIFF_EXTENSION__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__CONFLICT = JAVA2_KDM_DIFF_EXTENSION__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Method</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__CHANGED_METHOD = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Method Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE_FEATURE_COUNT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.EnumChangeImpl <em>Enum Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.EnumChangeImpl
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getEnumChange()
     * @generated
     */
    int ENUM_CHANGE = 7;

    /**
     * The feature id for the '<em><b>Match</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__MATCH = JAVA2_KDM_DIFF_EXTENSION__MATCH;

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
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__REFINES = JAVA2_KDM_DIFF_EXTENSION__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__REFINED_BY = JAVA2_KDM_DIFF_EXTENSION__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__KIND = JAVA2_KDM_DIFF_EXTENSION__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__SOURCE = JAVA2_KDM_DIFF_EXTENSION__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__STATE = JAVA2_KDM_DIFF_EXTENSION__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__EQUIVALENCE = JAVA2_KDM_DIFF_EXTENSION__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__CONFLICT = JAVA2_KDM_DIFF_EXTENSION__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Enum</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__CHANGED_ENUM = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Enum Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE_FEATURE_COUNT = JAVA2_KDM_DIFF_EXTENSION_FEATURE_COUNT + 1;

    /**
     * Returns the meta object for class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffExtension <em>Extension</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Extension</em>'.
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffExtension
     * @generated
     */
    EClass getJava2KDMDiffExtension();

    /**
     * Returns the meta object for class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange <em>Statement Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Statement Change</em>'.
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange
     * @generated
     */
    EClass getStatementChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange#getChangedStatement <em>Changed Statement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Statement</em>'.
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange#getChangedStatement()
     * @see #getStatementChange()
     * @generated
     */
    EReference getStatementChange_ChangedStatement();

    /**
     * Returns the meta object for class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange <em>Import Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Import Change</em>'.
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange
     * @generated
     */
    EClass getImportChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange#getChangedImport <em>Changed Import</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Import</em>'.
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange#getChangedImport()
     * @see #getImportChange()
     * @generated
     */
    EReference getImportChange_ChangedImport();

    /**
     * Returns the meta object for class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.ClassChange <em>Class Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Class Change</em>'.
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.ClassChange
     * @generated
     */
    EClass getClassChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.modisco.java.diffing.java2kdmdiff.ClassChange#getChangedClass <em>Changed Class</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Class</em>'.
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.ClassChange#getChangedClass()
     * @see #getClassChange()
     * @generated
     */
    EReference getClassChange_ChangedClass();

    /**
     * Returns the meta object for class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.FieldChange <em>Field Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Field Change</em>'.
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.FieldChange
     * @generated
     */
    EClass getFieldChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.modisco.java.diffing.java2kdmdiff.FieldChange#getChangedField <em>Changed Field</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Field</em>'.
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.FieldChange#getChangedField()
     * @see #getFieldChange()
     * @generated
     */
    EReference getFieldChange_ChangedField();

    /**
     * Returns the meta object for class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.PackageChange <em>Package Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Package Change</em>'.
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.PackageChange
     * @generated
     */
    EClass getPackageChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.modisco.java.diffing.java2kdmdiff.PackageChange#getChangedPackage <em>Changed Package</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Package</em>'.
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.PackageChange#getChangedPackage()
     * @see #getPackageChange()
     * @generated
     */
    EReference getPackageChange_ChangedPackage();

    /**
     * Returns the meta object for class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.MethodChange <em>Method Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Method Change</em>'.
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.MethodChange
     * @generated
     */
    EClass getMethodChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.modisco.java.diffing.java2kdmdiff.MethodChange#getChangedMethod <em>Changed Method</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Method</em>'.
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.MethodChange#getChangedMethod()
     * @see #getMethodChange()
     * @generated
     */
    EReference getMethodChange_ChangedMethod();

    /**
     * Returns the meta object for class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.EnumChange <em>Enum Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Enum Change</em>'.
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.EnumChange
     * @generated
     */
    EClass getEnumChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.modisco.java.diffing.java2kdmdiff.EnumChange#getChangedEnum <em>Changed Enum</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Enum</em>'.
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.EnumChange#getChangedEnum()
     * @see #getEnumChange()
     * @generated
     */
    EReference getEnumChange_ChangedEnum();

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
         * The meta object literal for the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffExtensionImpl <em>Extension</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffExtensionImpl
         * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getJava2KDMDiffExtension()
         * @generated
         */
        EClass JAVA2_KDM_DIFF_EXTENSION = eINSTANCE.getJava2KDMDiffExtension();

        /**
         * The meta object literal for the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.StatementChangeImpl <em>Statement Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.StatementChangeImpl
         * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getStatementChange()
         * @generated
         */
        EClass STATEMENT_CHANGE = eINSTANCE.getStatementChange();

        /**
         * The meta object literal for the '<em><b>Changed Statement</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STATEMENT_CHANGE__CHANGED_STATEMENT = eINSTANCE.getStatementChange_ChangedStatement();

        /**
         * The meta object literal for the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.ImportChangeImpl <em>Import Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.ImportChangeImpl
         * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getImportChange()
         * @generated
         */
        EClass IMPORT_CHANGE = eINSTANCE.getImportChange();

        /**
         * The meta object literal for the '<em><b>Changed Import</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference IMPORT_CHANGE__CHANGED_IMPORT = eINSTANCE.getImportChange_ChangedImport();

        /**
         * The meta object literal for the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.ClassChangeImpl <em>Class Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.ClassChangeImpl
         * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getClassChange()
         * @generated
         */
        EClass CLASS_CHANGE = eINSTANCE.getClassChange();

        /**
         * The meta object literal for the '<em><b>Changed Class</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CLASS_CHANGE__CHANGED_CLASS = eINSTANCE.getClassChange_ChangedClass();

        /**
         * The meta object literal for the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.FieldChangeImpl <em>Field Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.FieldChangeImpl
         * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getFieldChange()
         * @generated
         */
        EClass FIELD_CHANGE = eINSTANCE.getFieldChange();

        /**
         * The meta object literal for the '<em><b>Changed Field</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FIELD_CHANGE__CHANGED_FIELD = eINSTANCE.getFieldChange_ChangedField();

        /**
         * The meta object literal for the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.PackageChangeImpl <em>Package Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.PackageChangeImpl
         * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getPackageChange()
         * @generated
         */
        EClass PACKAGE_CHANGE = eINSTANCE.getPackageChange();

        /**
         * The meta object literal for the '<em><b>Changed Package</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PACKAGE_CHANGE__CHANGED_PACKAGE = eINSTANCE.getPackageChange_ChangedPackage();

        /**
         * The meta object literal for the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.MethodChangeImpl <em>Method Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.MethodChangeImpl
         * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getMethodChange()
         * @generated
         */
        EClass METHOD_CHANGE = eINSTANCE.getMethodChange();

        /**
         * The meta object literal for the '<em><b>Changed Method</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference METHOD_CHANGE__CHANGED_METHOD = eINSTANCE.getMethodChange_ChangedMethod();

        /**
         * The meta object literal for the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.EnumChangeImpl <em>Enum Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.EnumChangeImpl
         * @see org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffPackageImpl#getEnumChange()
         * @generated
         */
        EClass ENUM_CHANGE = eINSTANCE.getEnumChange();

        /**
         * The meta object literal for the '<em><b>Changed Enum</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ENUM_CHANGE__CHANGED_ENUM = eINSTANCE.getEnumChange_ChangedEnum();

    }

} //Java2KDMDiffPackage
