/**
 */
package org.splevo.vpm.refinement;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.splevo.vpm.refinement.RefinementFactory
 * @model kind="package"
 * @generated
 */
public interface RefinementPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "refinement";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://splevo.org/vpm/refinement/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.splevo.vpm.refinement";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RefinementPackage eINSTANCE = org.splevo.vpm.refinement.impl.RefinementPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.splevo.vpm.refinement.impl.RefinementImpl <em>Refinement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.vpm.refinement.impl.RefinementImpl
	 * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getRefinement()
	 * @generated
	 */
	int REFINEMENT = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFINEMENT__TYPE = 0;

	/**
	 * The number of structural features of the '<em>Refinement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFINEMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.splevo.vpm.refinement.impl.MergeImpl <em>Merge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.vpm.refinement.impl.MergeImpl
	 * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getMerge()
	 * @generated
	 */
	int MERGE = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MERGE__TYPE = REFINEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Variation Points</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MERGE__VARIATION_POINTS = REFINEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Merge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MERGE_FEATURE_COUNT = REFINEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.splevo.vpm.refinement.impl.GroupingImpl <em>Grouping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.vpm.refinement.impl.GroupingImpl
	 * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getGrouping()
	 * @generated
	 */
	int GROUPING = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUPING__TYPE = REFINEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Variation Points</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUPING__VARIATION_POINTS = REFINEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Grouping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUPING_FEATURE_COUNT = REFINEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.splevo.vpm.refinement.RefinementType <em>Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.vpm.refinement.RefinementType
	 * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getRefinementType()
	 * @generated
	 */
	int REFINEMENT_TYPE = 3;


	/**
	 * Returns the meta object for class '{@link org.splevo.vpm.refinement.Refinement <em>Refinement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Refinement</em>'.
	 * @see org.splevo.vpm.refinement.Refinement
	 * @generated
	 */
	EClass getRefinement();

	/**
	 * Returns the meta object for the attribute '{@link org.splevo.vpm.refinement.Refinement#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.splevo.vpm.refinement.Refinement#getType()
	 * @see #getRefinement()
	 * @generated
	 */
	EAttribute getRefinement_Type();

	/**
	 * Returns the meta object for class '{@link org.splevo.vpm.refinement.Merge <em>Merge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Merge</em>'.
	 * @see org.splevo.vpm.refinement.Merge
	 * @generated
	 */
	EClass getMerge();

	/**
	 * Returns the meta object for the reference list '{@link org.splevo.vpm.refinement.Merge#getVariationPoints <em>Variation Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Variation Points</em>'.
	 * @see org.splevo.vpm.refinement.Merge#getVariationPoints()
	 * @see #getMerge()
	 * @generated
	 */
	EReference getMerge_VariationPoints();

	/**
	 * Returns the meta object for class '{@link org.splevo.vpm.refinement.Grouping <em>Grouping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Grouping</em>'.
	 * @see org.splevo.vpm.refinement.Grouping
	 * @generated
	 */
	EClass getGrouping();

	/**
	 * Returns the meta object for the reference list '{@link org.splevo.vpm.refinement.Grouping#getVariationPoints <em>Variation Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Variation Points</em>'.
	 * @see org.splevo.vpm.refinement.Grouping#getVariationPoints()
	 * @see #getGrouping()
	 * @generated
	 */
	EReference getGrouping_VariationPoints();

	/**
	 * Returns the meta object for enum '{@link org.splevo.vpm.refinement.RefinementType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Type</em>'.
	 * @see org.splevo.vpm.refinement.RefinementType
	 * @generated
	 */
	EEnum getRefinementType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RefinementFactory getRefinementFactory();

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
		 * The meta object literal for the '{@link org.splevo.vpm.refinement.impl.RefinementImpl <em>Refinement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.vpm.refinement.impl.RefinementImpl
		 * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getRefinement()
		 * @generated
		 */
		EClass REFINEMENT = eINSTANCE.getRefinement();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFINEMENT__TYPE = eINSTANCE.getRefinement_Type();

		/**
		 * The meta object literal for the '{@link org.splevo.vpm.refinement.impl.MergeImpl <em>Merge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.vpm.refinement.impl.MergeImpl
		 * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getMerge()
		 * @generated
		 */
		EClass MERGE = eINSTANCE.getMerge();

		/**
		 * The meta object literal for the '<em><b>Variation Points</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MERGE__VARIATION_POINTS = eINSTANCE.getMerge_VariationPoints();

		/**
		 * The meta object literal for the '{@link org.splevo.vpm.refinement.impl.GroupingImpl <em>Grouping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.vpm.refinement.impl.GroupingImpl
		 * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getGrouping()
		 * @generated
		 */
		EClass GROUPING = eINSTANCE.getGrouping();

		/**
		 * The meta object literal for the '<em><b>Variation Points</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GROUPING__VARIATION_POINTS = eINSTANCE.getGrouping_VariationPoints();

		/**
		 * The meta object literal for the '{@link org.splevo.vpm.refinement.RefinementType <em>Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.vpm.refinement.RefinementType
		 * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getRefinementType()
		 * @generated
		 */
		EEnum REFINEMENT_TYPE = eINSTANCE.getRefinementType();

	}

} //RefinementPackage
