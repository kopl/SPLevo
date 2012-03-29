/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.vpm;

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
 * @see org.splevo.vpm.vpmFactory
 * @model kind="package"
 * @generated
 */
public interface vpmPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "vpm";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://splevo.org/vpm/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.splevo.vpm";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	vpmPackage eINSTANCE = org.splevo.vpm.impl.vpmPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.splevo.vpm.impl.VariationPointModelImpl <em>Variation Point Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.vpm.impl.VariationPointModelImpl
	 * @see org.splevo.vpm.impl.vpmPackageImpl#getVariationPointModel()
	 * @generated
	 */
	int VARIATION_POINT_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Variation Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIATION_POINT_MODEL__VARIATION_POINTS = 0;

	/**
	 * The feature id for the '<em><b>Realization Techniques</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIATION_POINT_MODEL__REALIZATION_TECHNIQUES = 1;

	/**
	 * The number of structural features of the '<em>Variation Point Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIATION_POINT_MODEL_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link org.splevo.vpm.VariationPointModel <em>Variation Point Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variation Point Model</em>'.
	 * @see org.splevo.vpm.VariationPointModel
	 * @generated
	 */
	EClass getVariationPointModel();

	/**
	 * Returns the meta object for the containment reference list '{@link org.splevo.vpm.VariationPointModel#getVariationPoints <em>Variation Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variation Points</em>'.
	 * @see org.splevo.vpm.VariationPointModel#getVariationPoints()
	 * @see #getVariationPointModel()
	 * @generated
	 */
	EReference getVariationPointModel_VariationPoints();

	/**
	 * Returns the meta object for the containment reference list '{@link org.splevo.vpm.VariationPointModel#getRealizationTechniques <em>Realization Techniques</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Realization Techniques</em>'.
	 * @see org.splevo.vpm.VariationPointModel#getRealizationTechniques()
	 * @see #getVariationPointModel()
	 * @generated
	 */
	EReference getVariationPointModel_RealizationTechniques();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	vpmFactory getvpmFactory();

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
		 * The meta object literal for the '{@link org.splevo.vpm.impl.VariationPointModelImpl <em>Variation Point Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.vpm.impl.VariationPointModelImpl
		 * @see org.splevo.vpm.impl.vpmPackageImpl#getVariationPointModel()
		 * @generated
		 */
		EClass VARIATION_POINT_MODEL = eINSTANCE.getVariationPointModel();

		/**
		 * The meta object literal for the '<em><b>Variation Points</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIATION_POINT_MODEL__VARIATION_POINTS = eINSTANCE.getVariationPointModel_VariationPoints();

		/**
		 * The meta object literal for the '<em><b>Realization Techniques</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIATION_POINT_MODEL__REALIZATION_TECHNIQUES = eINSTANCE.getVariationPointModel_RealizationTechniques();

	}

} //vpmPackage
