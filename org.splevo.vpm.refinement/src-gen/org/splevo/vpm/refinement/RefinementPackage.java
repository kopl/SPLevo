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
	int REFINEMENT = 1;

	/**
	 * The meta object id for the '{@link org.splevo.vpm.refinement.impl.RefinementModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.vpm.refinement.impl.RefinementModelImpl
	 * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getRefinementModel()
	 * @generated
	 */
	int REFINEMENT_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Analyzer Configurations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFINEMENT_MODEL__ANALYZER_CONFIGURATIONS = 0;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFINEMENT_MODEL_FEATURE_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFINEMENT__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Variation Points</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFINEMENT__VARIATION_POINTS = 1;

	/**
	 * The feature id for the '<em><b>Analyzer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFINEMENT__ANALYZER = 2;

	/**
	 * The number of structural features of the '<em>Refinement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFINEMENT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.splevo.vpm.refinement.impl.AnalyzerConfigurationImpl <em>Analyzer Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.vpm.refinement.impl.AnalyzerConfigurationImpl
	 * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getAnalyzerConfiguration()
	 * @generated
	 */
	int ANALYZER_CONFIGURATION = 2;

	/**
	 * The feature id for the '<em><b>Analyzer ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYZER_CONFIGURATION__ANALYZER_ID = 0;

	/**
	 * The feature id for the '<em><b>Settings</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYZER_CONFIGURATION__SETTINGS = 1;

	/**
	 * The feature id for the '<em><b>Refinements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYZER_CONFIGURATION__REFINEMENTS = 2;

	/**
	 * The number of structural features of the '<em>Analyzer Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYZER_CONFIGURATION_FEATURE_COUNT = 3;

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
	 * Returns the meta object for the reference list '{@link org.splevo.vpm.refinement.Refinement#getVariationPoints <em>Variation Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Variation Points</em>'.
	 * @see org.splevo.vpm.refinement.Refinement#getVariationPoints()
	 * @see #getRefinement()
	 * @generated
	 */
	EReference getRefinement_VariationPoints();

	/**
	 * Returns the meta object for the container reference '{@link org.splevo.vpm.refinement.Refinement#getAnalyzer <em>Analyzer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Analyzer</em>'.
	 * @see org.splevo.vpm.refinement.Refinement#getAnalyzer()
	 * @see #getRefinement()
	 * @generated
	 */
	EReference getRefinement_Analyzer();

	/**
	 * Returns the meta object for class '{@link org.splevo.vpm.refinement.RefinementModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see org.splevo.vpm.refinement.RefinementModel
	 * @generated
	 */
	EClass getRefinementModel();

	/**
	 * Returns the meta object for the containment reference list '{@link org.splevo.vpm.refinement.RefinementModel#getAnalyzerConfigurations <em>Analyzer Configurations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Analyzer Configurations</em>'.
	 * @see org.splevo.vpm.refinement.RefinementModel#getAnalyzerConfigurations()
	 * @see #getRefinementModel()
	 * @generated
	 */
	EReference getRefinementModel_AnalyzerConfigurations();

	/**
	 * Returns the meta object for class '{@link org.splevo.vpm.refinement.AnalyzerConfiguration <em>Analyzer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Analyzer Configuration</em>'.
	 * @see org.splevo.vpm.refinement.AnalyzerConfiguration
	 * @generated
	 */
	EClass getAnalyzerConfiguration();

	/**
	 * Returns the meta object for the attribute '{@link org.splevo.vpm.refinement.AnalyzerConfiguration#getAnalyzerID <em>Analyzer ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Analyzer ID</em>'.
	 * @see org.splevo.vpm.refinement.AnalyzerConfiguration#getAnalyzerID()
	 * @see #getAnalyzerConfiguration()
	 * @generated
	 */
	EAttribute getAnalyzerConfiguration_AnalyzerID();

	/**
	 * Returns the meta object for the attribute '{@link org.splevo.vpm.refinement.AnalyzerConfiguration#getSettings <em>Settings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Settings</em>'.
	 * @see org.splevo.vpm.refinement.AnalyzerConfiguration#getSettings()
	 * @see #getAnalyzerConfiguration()
	 * @generated
	 */
	EAttribute getAnalyzerConfiguration_Settings();

	/**
	 * Returns the meta object for the containment reference list '{@link org.splevo.vpm.refinement.AnalyzerConfiguration#getRefinements <em>Refinements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Refinements</em>'.
	 * @see org.splevo.vpm.refinement.AnalyzerConfiguration#getRefinements()
	 * @see #getAnalyzerConfiguration()
	 * @generated
	 */
	EReference getAnalyzerConfiguration_Refinements();

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
		 * The meta object literal for the '<em><b>Variation Points</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFINEMENT__VARIATION_POINTS = eINSTANCE.getRefinement_VariationPoints();

		/**
		 * The meta object literal for the '<em><b>Analyzer</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFINEMENT__ANALYZER = eINSTANCE.getRefinement_Analyzer();

		/**
		 * The meta object literal for the '{@link org.splevo.vpm.refinement.impl.RefinementModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.vpm.refinement.impl.RefinementModelImpl
		 * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getRefinementModel()
		 * @generated
		 */
		EClass REFINEMENT_MODEL = eINSTANCE.getRefinementModel();

		/**
		 * The meta object literal for the '<em><b>Analyzer Configurations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFINEMENT_MODEL__ANALYZER_CONFIGURATIONS = eINSTANCE.getRefinementModel_AnalyzerConfigurations();

		/**
		 * The meta object literal for the '{@link org.splevo.vpm.refinement.impl.AnalyzerConfigurationImpl <em>Analyzer Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.vpm.refinement.impl.AnalyzerConfigurationImpl
		 * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getAnalyzerConfiguration()
		 * @generated
		 */
		EClass ANALYZER_CONFIGURATION = eINSTANCE.getAnalyzerConfiguration();

		/**
		 * The meta object literal for the '<em><b>Analyzer ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANALYZER_CONFIGURATION__ANALYZER_ID = eINSTANCE.getAnalyzerConfiguration_AnalyzerID();

		/**
		 * The meta object literal for the '<em><b>Settings</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANALYZER_CONFIGURATION__SETTINGS = eINSTANCE.getAnalyzerConfiguration_Settings();

		/**
		 * The meta object literal for the '<em><b>Refinements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANALYZER_CONFIGURATION__REFINEMENTS = eINSTANCE.getAnalyzerConfiguration_Refinements();

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
