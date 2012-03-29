/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.vpm.realization;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
 * @see org.splevo.vpm.realization.realizationFactory
 * @model kind="package"
 * @generated
 */
public interface realizationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "realization";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://splevo.org/vpm/1.0/realization";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "realization";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	realizationPackage eINSTANCE = org.splevo.vpm.realization.impl.realizationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.splevo.vpm.realization.impl.RealizationTechniqueImpl <em>Realization Technique</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.vpm.realization.impl.RealizationTechniqueImpl
	 * @see org.splevo.vpm.realization.impl.realizationPackageImpl#getRealizationTechnique()
	 * @generated
	 */
	int REALIZATION_TECHNIQUE = 0;

	/**
	 * The number of structural features of the '<em>Realization Technique</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REALIZATION_TECHNIQUE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.splevo.vpm.realization.impl.DesignTimeRealizationTechniqueImpl <em>Design Time Realization Technique</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.vpm.realization.impl.DesignTimeRealizationTechniqueImpl
	 * @see org.splevo.vpm.realization.impl.realizationPackageImpl#getDesignTimeRealizationTechnique()
	 * @generated
	 */
	int DESIGN_TIME_REALIZATION_TECHNIQUE = 1;

	/**
	 * The number of structural features of the '<em>Design Time Realization Technique</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESIGN_TIME_REALIZATION_TECHNIQUE_FEATURE_COUNT = REALIZATION_TECHNIQUE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.splevo.vpm.realization.impl.CompilationTimeRealizationTechniqueImpl <em>Compilation Time Realization Technique</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.vpm.realization.impl.CompilationTimeRealizationTechniqueImpl
	 * @see org.splevo.vpm.realization.impl.realizationPackageImpl#getCompilationTimeRealizationTechnique()
	 * @generated
	 */
	int COMPILATION_TIME_REALIZATION_TECHNIQUE = 2;

	/**
	 * The number of structural features of the '<em>Compilation Time Realization Technique</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_TIME_REALIZATION_TECHNIQUE_FEATURE_COUNT = REALIZATION_TECHNIQUE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.splevo.vpm.realization.impl.LinkingTimeRealizationTechniqueImpl <em>Linking Time Realization Technique</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.vpm.realization.impl.LinkingTimeRealizationTechniqueImpl
	 * @see org.splevo.vpm.realization.impl.realizationPackageImpl#getLinkingTimeRealizationTechnique()
	 * @generated
	 */
	int LINKING_TIME_REALIZATION_TECHNIQUE = 3;

	/**
	 * The number of structural features of the '<em>Linking Time Realization Technique</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINKING_TIME_REALIZATION_TECHNIQUE_FEATURE_COUNT = REALIZATION_TECHNIQUE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.splevo.vpm.realization.impl.RuntimeRealizationTechniqueImpl <em>Runtime Realization Technique</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.vpm.realization.impl.RuntimeRealizationTechniqueImpl
	 * @see org.splevo.vpm.realization.impl.realizationPackageImpl#getRuntimeRealizationTechnique()
	 * @generated
	 */
	int RUNTIME_REALIZATION_TECHNIQUE = 4;

	/**
	 * The number of structural features of the '<em>Runtime Realization Technique</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_REALIZATION_TECHNIQUE_FEATURE_COUNT = REALIZATION_TECHNIQUE_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.splevo.vpm.realization.RealizationTechnique <em>Realization Technique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Realization Technique</em>'.
	 * @see org.splevo.vpm.realization.RealizationTechnique
	 * @generated
	 */
	EClass getRealizationTechnique();

	/**
	 * Returns the meta object for class '{@link org.splevo.vpm.realization.DesignTimeRealizationTechnique <em>Design Time Realization Technique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Design Time Realization Technique</em>'.
	 * @see org.splevo.vpm.realization.DesignTimeRealizationTechnique
	 * @generated
	 */
	EClass getDesignTimeRealizationTechnique();

	/**
	 * Returns the meta object for class '{@link org.splevo.vpm.realization.CompilationTimeRealizationTechnique <em>Compilation Time Realization Technique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Compilation Time Realization Technique</em>'.
	 * @see org.splevo.vpm.realization.CompilationTimeRealizationTechnique
	 * @generated
	 */
	EClass getCompilationTimeRealizationTechnique();

	/**
	 * Returns the meta object for class '{@link org.splevo.vpm.realization.LinkingTimeRealizationTechnique <em>Linking Time Realization Technique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Linking Time Realization Technique</em>'.
	 * @see org.splevo.vpm.realization.LinkingTimeRealizationTechnique
	 * @generated
	 */
	EClass getLinkingTimeRealizationTechnique();

	/**
	 * Returns the meta object for class '{@link org.splevo.vpm.realization.RuntimeRealizationTechnique <em>Runtime Realization Technique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Runtime Realization Technique</em>'.
	 * @see org.splevo.vpm.realization.RuntimeRealizationTechnique
	 * @generated
	 */
	EClass getRuntimeRealizationTechnique();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	realizationFactory getrealizationFactory();

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
		 * The meta object literal for the '{@link org.splevo.vpm.realization.impl.RealizationTechniqueImpl <em>Realization Technique</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.vpm.realization.impl.RealizationTechniqueImpl
		 * @see org.splevo.vpm.realization.impl.realizationPackageImpl#getRealizationTechnique()
		 * @generated
		 */
		EClass REALIZATION_TECHNIQUE = eINSTANCE.getRealizationTechnique();

		/**
		 * The meta object literal for the '{@link org.splevo.vpm.realization.impl.DesignTimeRealizationTechniqueImpl <em>Design Time Realization Technique</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.vpm.realization.impl.DesignTimeRealizationTechniqueImpl
		 * @see org.splevo.vpm.realization.impl.realizationPackageImpl#getDesignTimeRealizationTechnique()
		 * @generated
		 */
		EClass DESIGN_TIME_REALIZATION_TECHNIQUE = eINSTANCE.getDesignTimeRealizationTechnique();

		/**
		 * The meta object literal for the '{@link org.splevo.vpm.realization.impl.CompilationTimeRealizationTechniqueImpl <em>Compilation Time Realization Technique</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.vpm.realization.impl.CompilationTimeRealizationTechniqueImpl
		 * @see org.splevo.vpm.realization.impl.realizationPackageImpl#getCompilationTimeRealizationTechnique()
		 * @generated
		 */
		EClass COMPILATION_TIME_REALIZATION_TECHNIQUE = eINSTANCE.getCompilationTimeRealizationTechnique();

		/**
		 * The meta object literal for the '{@link org.splevo.vpm.realization.impl.LinkingTimeRealizationTechniqueImpl <em>Linking Time Realization Technique</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.vpm.realization.impl.LinkingTimeRealizationTechniqueImpl
		 * @see org.splevo.vpm.realization.impl.realizationPackageImpl#getLinkingTimeRealizationTechnique()
		 * @generated
		 */
		EClass LINKING_TIME_REALIZATION_TECHNIQUE = eINSTANCE.getLinkingTimeRealizationTechnique();

		/**
		 * The meta object literal for the '{@link org.splevo.vpm.realization.impl.RuntimeRealizationTechniqueImpl <em>Runtime Realization Technique</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.vpm.realization.impl.RuntimeRealizationTechniqueImpl
		 * @see org.splevo.vpm.realization.impl.realizationPackageImpl#getRuntimeRealizationTechnique()
		 * @generated
		 */
		EClass RUNTIME_REALIZATION_TECHNIQUE = eINSTANCE.getRuntimeRealizationTechnique();

	}

} //realizationPackage
