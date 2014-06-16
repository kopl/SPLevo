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
package org.splevo.jamopp.diffing.jamoppdiff;

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
 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffFactory
 * @model kind="package"
 * @generated
 */
public interface JaMoPPDiffPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "jamoppdiff";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/emf/compare/diff/jamoppdiff/1.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "jamoppdiff";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    JaMoPPDiffPackage eINSTANCE = org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl.init();

    /**
     * The meta object id for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffImpl <em>Ja Mo PP Diff</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffImpl
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getJaMoPPDiff()
     * @generated
     */
    int JA_MO_PP_DIFF = 0;

    /**
     * The feature id for the '<em><b>Match</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JA_MO_PP_DIFF__MATCH = ComparePackage.DIFF__MATCH;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JA_MO_PP_DIFF__REQUIRES = ComparePackage.DIFF__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JA_MO_PP_DIFF__REQUIRED_BY = ComparePackage.DIFF__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JA_MO_PP_DIFF__REFINES = ComparePackage.DIFF__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JA_MO_PP_DIFF__REFINED_BY = ComparePackage.DIFF__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JA_MO_PP_DIFF__KIND = ComparePackage.DIFF__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JA_MO_PP_DIFF__SOURCE = ComparePackage.DIFF__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JA_MO_PP_DIFF__STATE = ComparePackage.DIFF__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JA_MO_PP_DIFF__EQUIVALENCE = ComparePackage.DIFF__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JA_MO_PP_DIFF__CONFLICT = ComparePackage.DIFF__CONFLICT;

    /**
     * The number of structural features of the '<em>Ja Mo PP Diff</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JA_MO_PP_DIFF_FEATURE_COUNT = ComparePackage.DIFF_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.StatementChangeImpl <em>Statement Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.StatementChangeImpl
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getStatementChange()
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
    int STATEMENT_CHANGE__MATCH = JA_MO_PP_DIFF__MATCH;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__REQUIRES = JA_MO_PP_DIFF__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__REQUIRED_BY = JA_MO_PP_DIFF__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__REFINES = JA_MO_PP_DIFF__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__REFINED_BY = JA_MO_PP_DIFF__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__KIND = JA_MO_PP_DIFF__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__SOURCE = JA_MO_PP_DIFF__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__STATE = JA_MO_PP_DIFF__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__EQUIVALENCE = JA_MO_PP_DIFF__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__CONFLICT = JA_MO_PP_DIFF__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Statement</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE__CHANGED_STATEMENT = JA_MO_PP_DIFF_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Statement Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATEMENT_CHANGE_FEATURE_COUNT = JA_MO_PP_DIFF_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.ImportChangeImpl <em>Import Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.ImportChangeImpl
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getImportChange()
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
    int IMPORT_CHANGE__MATCH = JA_MO_PP_DIFF__MATCH;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__REQUIRES = JA_MO_PP_DIFF__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__REQUIRED_BY = JA_MO_PP_DIFF__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__REFINES = JA_MO_PP_DIFF__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__REFINED_BY = JA_MO_PP_DIFF__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__KIND = JA_MO_PP_DIFF__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__SOURCE = JA_MO_PP_DIFF__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__STATE = JA_MO_PP_DIFF__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__EQUIVALENCE = JA_MO_PP_DIFF__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__CONFLICT = JA_MO_PP_DIFF__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Import</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE__CHANGED_IMPORT = JA_MO_PP_DIFF_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Import Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_CHANGE_FEATURE_COUNT = JA_MO_PP_DIFF_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.ClassChangeImpl <em>Class Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.ClassChangeImpl
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getClassChange()
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
    int CLASS_CHANGE__MATCH = JA_MO_PP_DIFF__MATCH;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__REQUIRES = JA_MO_PP_DIFF__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__REQUIRED_BY = JA_MO_PP_DIFF__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__REFINES = JA_MO_PP_DIFF__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__REFINED_BY = JA_MO_PP_DIFF__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__KIND = JA_MO_PP_DIFF__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__SOURCE = JA_MO_PP_DIFF__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__STATE = JA_MO_PP_DIFF__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__EQUIVALENCE = JA_MO_PP_DIFF__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__CONFLICT = JA_MO_PP_DIFF__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Class</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE__CHANGED_CLASS = JA_MO_PP_DIFF_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Class Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_CHANGE_FEATURE_COUNT = JA_MO_PP_DIFF_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.FieldChangeImpl <em>Field Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.FieldChangeImpl
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getFieldChange()
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
    int FIELD_CHANGE__MATCH = JA_MO_PP_DIFF__MATCH;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__REQUIRES = JA_MO_PP_DIFF__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__REQUIRED_BY = JA_MO_PP_DIFF__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__REFINES = JA_MO_PP_DIFF__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__REFINED_BY = JA_MO_PP_DIFF__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__KIND = JA_MO_PP_DIFF__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__SOURCE = JA_MO_PP_DIFF__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__STATE = JA_MO_PP_DIFF__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__EQUIVALENCE = JA_MO_PP_DIFF__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__CONFLICT = JA_MO_PP_DIFF__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Field</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE__CHANGED_FIELD = JA_MO_PP_DIFF_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Field Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_CHANGE_FEATURE_COUNT = JA_MO_PP_DIFF_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.PackageChangeImpl <em>Package Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.PackageChangeImpl
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getPackageChange()
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
    int PACKAGE_CHANGE__MATCH = JA_MO_PP_DIFF__MATCH;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__REQUIRES = JA_MO_PP_DIFF__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__REQUIRED_BY = JA_MO_PP_DIFF__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__REFINES = JA_MO_PP_DIFF__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__REFINED_BY = JA_MO_PP_DIFF__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__KIND = JA_MO_PP_DIFF__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__SOURCE = JA_MO_PP_DIFF__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__STATE = JA_MO_PP_DIFF__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__EQUIVALENCE = JA_MO_PP_DIFF__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__CONFLICT = JA_MO_PP_DIFF__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Package</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE__CHANGED_PACKAGE = JA_MO_PP_DIFF_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Package Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_CHANGE_FEATURE_COUNT = JA_MO_PP_DIFF_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.MethodChangeImpl <em>Method Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.MethodChangeImpl
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getMethodChange()
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
    int METHOD_CHANGE__MATCH = JA_MO_PP_DIFF__MATCH;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__REQUIRES = JA_MO_PP_DIFF__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__REQUIRED_BY = JA_MO_PP_DIFF__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__REFINES = JA_MO_PP_DIFF__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__REFINED_BY = JA_MO_PP_DIFF__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__KIND = JA_MO_PP_DIFF__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__SOURCE = JA_MO_PP_DIFF__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__STATE = JA_MO_PP_DIFF__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__EQUIVALENCE = JA_MO_PP_DIFF__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__CONFLICT = JA_MO_PP_DIFF__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Method</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE__CHANGED_METHOD = JA_MO_PP_DIFF_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Method Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METHOD_CHANGE_FEATURE_COUNT = JA_MO_PP_DIFF_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.ConstructorChangeImpl <em>Constructor Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.ConstructorChangeImpl
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getConstructorChange()
     * @generated
     */
    int CONSTRUCTOR_CHANGE = 7;

    /**
     * The feature id for the '<em><b>Match</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRUCTOR_CHANGE__MATCH = JA_MO_PP_DIFF__MATCH;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRUCTOR_CHANGE__REQUIRES = JA_MO_PP_DIFF__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRUCTOR_CHANGE__REQUIRED_BY = JA_MO_PP_DIFF__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRUCTOR_CHANGE__REFINES = JA_MO_PP_DIFF__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRUCTOR_CHANGE__REFINED_BY = JA_MO_PP_DIFF__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRUCTOR_CHANGE__KIND = JA_MO_PP_DIFF__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRUCTOR_CHANGE__SOURCE = JA_MO_PP_DIFF__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRUCTOR_CHANGE__STATE = JA_MO_PP_DIFF__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRUCTOR_CHANGE__EQUIVALENCE = JA_MO_PP_DIFF__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRUCTOR_CHANGE__CONFLICT = JA_MO_PP_DIFF__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Constructor</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRUCTOR_CHANGE__CHANGED_CONSTRUCTOR = JA_MO_PP_DIFF_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Constructor Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRUCTOR_CHANGE_FEATURE_COUNT = JA_MO_PP_DIFF_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.EnumChangeImpl <em>Enum Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.EnumChangeImpl
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getEnumChange()
     * @generated
     */
    int ENUM_CHANGE = 8;

    /**
     * The feature id for the '<em><b>Match</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__MATCH = JA_MO_PP_DIFF__MATCH;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__REQUIRES = JA_MO_PP_DIFF__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__REQUIRED_BY = JA_MO_PP_DIFF__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__REFINES = JA_MO_PP_DIFF__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__REFINED_BY = JA_MO_PP_DIFF__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__KIND = JA_MO_PP_DIFF__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__SOURCE = JA_MO_PP_DIFF__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__STATE = JA_MO_PP_DIFF__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__EQUIVALENCE = JA_MO_PP_DIFF__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__CONFLICT = JA_MO_PP_DIFF__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Enum</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE__CHANGED_ENUM = JA_MO_PP_DIFF_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Enum Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_CHANGE_FEATURE_COUNT = JA_MO_PP_DIFF_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.CompilationUnitChangeImpl <em>Compilation Unit Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.CompilationUnitChangeImpl
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getCompilationUnitChange()
     * @generated
     */
    int COMPILATION_UNIT_CHANGE = 9;

    /**
     * The feature id for the '<em><b>Match</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPILATION_UNIT_CHANGE__MATCH = JA_MO_PP_DIFF__MATCH;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPILATION_UNIT_CHANGE__REQUIRES = JA_MO_PP_DIFF__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPILATION_UNIT_CHANGE__REQUIRED_BY = JA_MO_PP_DIFF__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPILATION_UNIT_CHANGE__REFINES = JA_MO_PP_DIFF__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPILATION_UNIT_CHANGE__REFINED_BY = JA_MO_PP_DIFF__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPILATION_UNIT_CHANGE__KIND = JA_MO_PP_DIFF__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPILATION_UNIT_CHANGE__SOURCE = JA_MO_PP_DIFF__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPILATION_UNIT_CHANGE__STATE = JA_MO_PP_DIFF__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPILATION_UNIT_CHANGE__EQUIVALENCE = JA_MO_PP_DIFF__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPILATION_UNIT_CHANGE__CONFLICT = JA_MO_PP_DIFF__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Compilation Unit</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPILATION_UNIT_CHANGE__CHANGED_COMPILATION_UNIT = JA_MO_PP_DIFF_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Compilation Unit Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPILATION_UNIT_CHANGE_FEATURE_COUNT = JA_MO_PP_DIFF_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.InterfaceChangeImpl <em>Interface Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.InterfaceChangeImpl
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getInterfaceChange()
     * @generated
     */
    int INTERFACE_CHANGE = 10;

    /**
     * The feature id for the '<em><b>Match</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERFACE_CHANGE__MATCH = JA_MO_PP_DIFF__MATCH;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERFACE_CHANGE__REQUIRES = JA_MO_PP_DIFF__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERFACE_CHANGE__REQUIRED_BY = JA_MO_PP_DIFF__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERFACE_CHANGE__REFINES = JA_MO_PP_DIFF__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERFACE_CHANGE__REFINED_BY = JA_MO_PP_DIFF__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERFACE_CHANGE__KIND = JA_MO_PP_DIFF__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERFACE_CHANGE__SOURCE = JA_MO_PP_DIFF__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERFACE_CHANGE__STATE = JA_MO_PP_DIFF__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERFACE_CHANGE__EQUIVALENCE = JA_MO_PP_DIFF__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERFACE_CHANGE__CONFLICT = JA_MO_PP_DIFF__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Interface</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERFACE_CHANGE__CHANGED_INTERFACE = JA_MO_PP_DIFF_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Interface Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERFACE_CHANGE_FEATURE_COUNT = JA_MO_PP_DIFF_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.ImplementsChangeImpl <em>Implements Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.ImplementsChangeImpl
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getImplementsChange()
     * @generated
     */
    int IMPLEMENTS_CHANGE = 11;

    /**
     * The feature id for the '<em><b>Match</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_CHANGE__MATCH = JA_MO_PP_DIFF__MATCH;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_CHANGE__REQUIRES = JA_MO_PP_DIFF__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_CHANGE__REQUIRED_BY = JA_MO_PP_DIFF__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_CHANGE__REFINES = JA_MO_PP_DIFF__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_CHANGE__REFINED_BY = JA_MO_PP_DIFF__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_CHANGE__KIND = JA_MO_PP_DIFF__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_CHANGE__SOURCE = JA_MO_PP_DIFF__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_CHANGE__STATE = JA_MO_PP_DIFF__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_CHANGE__EQUIVALENCE = JA_MO_PP_DIFF__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_CHANGE__CONFLICT = JA_MO_PP_DIFF__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Reference</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_CHANGE__CHANGED_REFERENCE = JA_MO_PP_DIFF_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Implements Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTS_CHANGE_FEATURE_COUNT = JA_MO_PP_DIFF_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.ExtendsChangeImpl <em>Extends Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.ExtendsChangeImpl
     * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getExtendsChange()
     * @generated
     */
    int EXTENDS_CHANGE = 12;

    /**
     * The feature id for the '<em><b>Match</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXTENDS_CHANGE__MATCH = JA_MO_PP_DIFF__MATCH;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXTENDS_CHANGE__REQUIRES = JA_MO_PP_DIFF__REQUIRES;

    /**
     * The feature id for the '<em><b>Required By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXTENDS_CHANGE__REQUIRED_BY = JA_MO_PP_DIFF__REQUIRED_BY;

    /**
     * The feature id for the '<em><b>Refines</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXTENDS_CHANGE__REFINES = JA_MO_PP_DIFF__REFINES;

    /**
     * The feature id for the '<em><b>Refined By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXTENDS_CHANGE__REFINED_BY = JA_MO_PP_DIFF__REFINED_BY;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXTENDS_CHANGE__KIND = JA_MO_PP_DIFF__KIND;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXTENDS_CHANGE__SOURCE = JA_MO_PP_DIFF__SOURCE;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXTENDS_CHANGE__STATE = JA_MO_PP_DIFF__STATE;

    /**
     * The feature id for the '<em><b>Equivalence</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXTENDS_CHANGE__EQUIVALENCE = JA_MO_PP_DIFF__EQUIVALENCE;

    /**
     * The feature id for the '<em><b>Conflict</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXTENDS_CHANGE__CONFLICT = JA_MO_PP_DIFF__CONFLICT;

    /**
     * The feature id for the '<em><b>Changed Reference</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXTENDS_CHANGE__CHANGED_REFERENCE = JA_MO_PP_DIFF_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Extends Change</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXTENDS_CHANGE_FEATURE_COUNT = JA_MO_PP_DIFF_FEATURE_COUNT + 1;

    /**
     * Returns the meta object for class '{@link org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiff <em>Ja Mo PP Diff</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Ja Mo PP Diff</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiff
     * @generated
     */
    EClass getJaMoPPDiff();

    /**
     * Returns the meta object for class '{@link org.splevo.jamopp.diffing.jamoppdiff.StatementChange <em>Statement Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Statement Change</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.StatementChange
     * @generated
     */
    EClass getStatementChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.jamopp.diffing.jamoppdiff.StatementChange#getChangedStatement <em>Changed Statement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Statement</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.StatementChange#getChangedStatement()
     * @see #getStatementChange()
     * @generated
     */
    EReference getStatementChange_ChangedStatement();

    /**
     * Returns the meta object for class '{@link org.splevo.jamopp.diffing.jamoppdiff.ImportChange <em>Import Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Import Change</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.ImportChange
     * @generated
     */
    EClass getImportChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.jamopp.diffing.jamoppdiff.ImportChange#getChangedImport <em>Changed Import</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Import</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.ImportChange#getChangedImport()
     * @see #getImportChange()
     * @generated
     */
    EReference getImportChange_ChangedImport();

    /**
     * Returns the meta object for class '{@link org.splevo.jamopp.diffing.jamoppdiff.ClassChange <em>Class Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Class Change</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.ClassChange
     * @generated
     */
    EClass getClassChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.jamopp.diffing.jamoppdiff.ClassChange#getChangedClass <em>Changed Class</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Class</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.ClassChange#getChangedClass()
     * @see #getClassChange()
     * @generated
     */
    EReference getClassChange_ChangedClass();

    /**
     * Returns the meta object for class '{@link org.splevo.jamopp.diffing.jamoppdiff.FieldChange <em>Field Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Field Change</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.FieldChange
     * @generated
     */
    EClass getFieldChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.jamopp.diffing.jamoppdiff.FieldChange#getChangedField <em>Changed Field</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Field</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.FieldChange#getChangedField()
     * @see #getFieldChange()
     * @generated
     */
    EReference getFieldChange_ChangedField();

    /**
     * Returns the meta object for class '{@link org.splevo.jamopp.diffing.jamoppdiff.PackageChange <em>Package Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Package Change</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.PackageChange
     * @generated
     */
    EClass getPackageChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.jamopp.diffing.jamoppdiff.PackageChange#getChangedPackage <em>Changed Package</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Package</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.PackageChange#getChangedPackage()
     * @see #getPackageChange()
     * @generated
     */
    EReference getPackageChange_ChangedPackage();

    /**
     * Returns the meta object for class '{@link org.splevo.jamopp.diffing.jamoppdiff.MethodChange <em>Method Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Method Change</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.MethodChange
     * @generated
     */
    EClass getMethodChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.jamopp.diffing.jamoppdiff.MethodChange#getChangedMethod <em>Changed Method</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Method</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.MethodChange#getChangedMethod()
     * @see #getMethodChange()
     * @generated
     */
    EReference getMethodChange_ChangedMethod();

    /**
     * Returns the meta object for class '{@link org.splevo.jamopp.diffing.jamoppdiff.ConstructorChange <em>Constructor Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Constructor Change</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.ConstructorChange
     * @generated
     */
    EClass getConstructorChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.jamopp.diffing.jamoppdiff.ConstructorChange#getChangedConstructor <em>Changed Constructor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Constructor</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.ConstructorChange#getChangedConstructor()
     * @see #getConstructorChange()
     * @generated
     */
    EReference getConstructorChange_ChangedConstructor();

    /**
     * Returns the meta object for class '{@link org.splevo.jamopp.diffing.jamoppdiff.EnumChange <em>Enum Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Enum Change</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.EnumChange
     * @generated
     */
    EClass getEnumChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.jamopp.diffing.jamoppdiff.EnumChange#getChangedEnum <em>Changed Enum</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Enum</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.EnumChange#getChangedEnum()
     * @see #getEnumChange()
     * @generated
     */
    EReference getEnumChange_ChangedEnum();

    /**
     * Returns the meta object for class '{@link org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange <em>Compilation Unit Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Compilation Unit Change</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange
     * @generated
     */
    EClass getCompilationUnitChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange#getChangedCompilationUnit <em>Changed Compilation Unit</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Compilation Unit</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange#getChangedCompilationUnit()
     * @see #getCompilationUnitChange()
     * @generated
     */
    EReference getCompilationUnitChange_ChangedCompilationUnit();

    /**
     * Returns the meta object for class '{@link org.splevo.jamopp.diffing.jamoppdiff.InterfaceChange <em>Interface Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Interface Change</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.InterfaceChange
     * @generated
     */
    EClass getInterfaceChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.jamopp.diffing.jamoppdiff.InterfaceChange#getChangedInterface <em>Changed Interface</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Interface</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.InterfaceChange#getChangedInterface()
     * @see #getInterfaceChange()
     * @generated
     */
    EReference getInterfaceChange_ChangedInterface();

    /**
     * Returns the meta object for class '{@link org.splevo.jamopp.diffing.jamoppdiff.ImplementsChange <em>Implements Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Implements Change</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.ImplementsChange
     * @generated
     */
    EClass getImplementsChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.jamopp.diffing.jamoppdiff.ImplementsChange#getChangedReference <em>Changed Reference</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Reference</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.ImplementsChange#getChangedReference()
     * @see #getImplementsChange()
     * @generated
     */
    EReference getImplementsChange_ChangedReference();

    /**
     * Returns the meta object for class '{@link org.splevo.jamopp.diffing.jamoppdiff.ExtendsChange <em>Extends Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Extends Change</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.ExtendsChange
     * @generated
     */
    EClass getExtendsChange();

    /**
     * Returns the meta object for the reference '{@link org.splevo.jamopp.diffing.jamoppdiff.ExtendsChange#getChangedReference <em>Changed Reference</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Changed Reference</em>'.
     * @see org.splevo.jamopp.diffing.jamoppdiff.ExtendsChange#getChangedReference()
     * @see #getExtendsChange()
     * @generated
     */
    EReference getExtendsChange_ChangedReference();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    JaMoPPDiffFactory getJaMoPPDiffFactory();

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
         * The meta object literal for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffImpl <em>Ja Mo PP Diff</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffImpl
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getJaMoPPDiff()
         * @generated
         */
        EClass JA_MO_PP_DIFF = eINSTANCE.getJaMoPPDiff();

        /**
         * The meta object literal for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.StatementChangeImpl <em>Statement Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.StatementChangeImpl
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getStatementChange()
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
         * The meta object literal for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.ImportChangeImpl <em>Import Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.ImportChangeImpl
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getImportChange()
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
         * The meta object literal for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.ClassChangeImpl <em>Class Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.ClassChangeImpl
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getClassChange()
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
         * The meta object literal for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.FieldChangeImpl <em>Field Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.FieldChangeImpl
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getFieldChange()
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
         * The meta object literal for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.PackageChangeImpl <em>Package Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.PackageChangeImpl
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getPackageChange()
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
         * The meta object literal for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.MethodChangeImpl <em>Method Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.MethodChangeImpl
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getMethodChange()
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
         * The meta object literal for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.ConstructorChangeImpl <em>Constructor Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.ConstructorChangeImpl
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getConstructorChange()
         * @generated
         */
        EClass CONSTRUCTOR_CHANGE = eINSTANCE.getConstructorChange();

        /**
         * The meta object literal for the '<em><b>Changed Constructor</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CONSTRUCTOR_CHANGE__CHANGED_CONSTRUCTOR = eINSTANCE.getConstructorChange_ChangedConstructor();

        /**
         * The meta object literal for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.EnumChangeImpl <em>Enum Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.EnumChangeImpl
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getEnumChange()
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

        /**
         * The meta object literal for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.CompilationUnitChangeImpl <em>Compilation Unit Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.CompilationUnitChangeImpl
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getCompilationUnitChange()
         * @generated
         */
        EClass COMPILATION_UNIT_CHANGE = eINSTANCE.getCompilationUnitChange();

        /**
         * The meta object literal for the '<em><b>Changed Compilation Unit</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference COMPILATION_UNIT_CHANGE__CHANGED_COMPILATION_UNIT = eINSTANCE
                .getCompilationUnitChange_ChangedCompilationUnit();

        /**
         * The meta object literal for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.InterfaceChangeImpl <em>Interface Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.InterfaceChangeImpl
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getInterfaceChange()
         * @generated
         */
        EClass INTERFACE_CHANGE = eINSTANCE.getInterfaceChange();

        /**
         * The meta object literal for the '<em><b>Changed Interface</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INTERFACE_CHANGE__CHANGED_INTERFACE = eINSTANCE.getInterfaceChange_ChangedInterface();

        /**
         * The meta object literal for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.ImplementsChangeImpl <em>Implements Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.ImplementsChangeImpl
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getImplementsChange()
         * @generated
         */
        EClass IMPLEMENTS_CHANGE = eINSTANCE.getImplementsChange();

        /**
         * The meta object literal for the '<em><b>Changed Reference</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference IMPLEMENTS_CHANGE__CHANGED_REFERENCE = eINSTANCE.getImplementsChange_ChangedReference();

        /**
         * The meta object literal for the '{@link org.splevo.jamopp.diffing.jamoppdiff.impl.ExtendsChangeImpl <em>Extends Change</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.ExtendsChangeImpl
         * @see org.splevo.jamopp.diffing.jamoppdiff.impl.JaMoPPDiffPackageImpl#getExtendsChange()
         * @generated
         */
        EClass EXTENDS_CHANGE = eINSTANCE.getExtendsChange();

        /**
         * The meta object literal for the '<em><b>Changed Reference</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EXTENDS_CHANGE__CHANGED_REFERENCE = eINSTANCE.getExtendsChange_ChangedReference();

    }

} //JaMoPPDiffPackage
