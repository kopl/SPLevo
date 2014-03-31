/**
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Benjamin Klatt
 */
package org.splevo.vrm;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta
 * objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc --> <!-- begin-model-doc --> Variability Realization Model to describe
 * available realization techniques and configure how to realize the variation points of a variation
 * point model (VPM). <!-- end-model-doc -->
 * 
 * @see org.splevo.vrm.vrmFactory
 * @model kind="package"
 * @generated
 */
public interface vrmPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "vrm";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://splevo.org/vrm/1.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "org.splevo.vrm";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    vrmPackage eINSTANCE = org.splevo.vrm.impl.vrmPackageImpl.init();

    /**
     * The meta object id for the '{@link org.splevo.vrm.impl.VariabilityMechanismImpl
     * <em>Variability Mechanism</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.splevo.vrm.impl.VariabilityMechanismImpl
     * @see org.splevo.vrm.impl.vrmPackageImpl#getVariabilityMechanism()
     * @generated
     */
    int VARIABILITY_MECHANISM = 0;

    /**
     * The feature id for the '<em><b>Refactoring Id</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_MECHANISM__REFACTORING_ID = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_MECHANISM__NAME = 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_MECHANISM__DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Supported Binding Times</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_MECHANISM__SUPPORTED_BINDING_TIMES = 3;

    /**
     * The feature id for the '<em><b>Supported Extensibilities</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_MECHANISM__SUPPORTED_EXTENSIBILITIES = 4;

    /**
     * The feature id for the '<em><b>Supported Variability Types</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_MECHANISM__SUPPORTED_VARIABILITY_TYPES = 5;

    /**
     * The number of structural features of the '<em>Variability Mechanism</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_MECHANISM_FEATURE_COUNT = 6;

    /**
     * The number of operations of the '<em>Variability Mechanism</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_MECHANISM_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.splevo.vrm.impl.VariabilityRealizationConfigurationImpl
     * <em>Variability Realization Configuration</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.splevo.vrm.impl.VariabilityRealizationConfigurationImpl
     * @see org.splevo.vrm.impl.vrmPackageImpl#getVariabilityRealizationConfiguration()
     * @generated
     */
    int VARIABILITY_REALIZATION_CONFIGURATION = 1;

    /**
     * The feature id for the '<em><b>Variation Point</b></em>' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_REALIZATION_CONFIGURATION__VARIATION_POINT = 0;

    /**
     * The feature id for the '<em><b>Mechanism</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_REALIZATION_CONFIGURATION__MECHANISM = 1;

    /**
     * The number of structural features of the '<em>Variability Realization Configuration</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_REALIZATION_CONFIGURATION_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Variability Realization Configuration</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_REALIZATION_CONFIGURATION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.splevo.vrm.impl.VariabilityRealizationModelImpl
     * <em>Variability Realization Model</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.splevo.vrm.impl.VariabilityRealizationModelImpl
     * @see org.splevo.vrm.impl.vrmPackageImpl#getVariabilityRealizationModel()
     * @generated
     */
    int VARIABILITY_REALIZATION_MODEL = 2;

    /**
     * The feature id for the '<em><b>Realization Configurations</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_REALIZATION_MODEL__REALIZATION_CONFIGURATIONS = 0;

    /**
     * The feature id for the '<em><b>Vpm</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_REALIZATION_MODEL__VPM = 1;

    /**
     * The number of structural features of the '<em>Variability Realization Model</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_REALIZATION_MODEL_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Variability Realization Model</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_REALIZATION_MODEL_OPERATION_COUNT = 0;

    /**
     * Returns the meta object for class '{@link org.splevo.vrm.VariabilityMechanism
     * <em>Variability Mechanism</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Variability Mechanism</em>'.
     * @see org.splevo.vrm.VariabilityMechanism
     * @generated
     */
    EClass getVariabilityMechanism();

    /**
     * Returns the meta object for the attribute '
     * {@link org.splevo.vrm.VariabilityMechanism#getRefactoringId <em>Refactoring Id</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Refactoring Id</em>'.
     * @see org.splevo.vrm.VariabilityMechanism#getRefactoringId()
     * @see #getVariabilityMechanism()
     * @generated
     */
    EAttribute getVariabilityMechanism_RefactoringId();

    /**
     * Returns the meta object for the attribute '
     * {@link org.splevo.vrm.VariabilityMechanism#getName <em>Name</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.splevo.vrm.VariabilityMechanism#getName()
     * @see #getVariabilityMechanism()
     * @generated
     */
    EAttribute getVariabilityMechanism_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.splevo.vrm.VariabilityMechanism#getDescription <em>Description</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.splevo.vrm.VariabilityMechanism#getDescription()
     * @see #getVariabilityMechanism()
     * @generated
     */
    EAttribute getVariabilityMechanism_Description();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.splevo.vrm.VariabilityMechanism#getSupportedBindingTimes
     * <em>Supported Binding Times</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Supported Binding Times</em>'.
     * @see org.splevo.vrm.VariabilityMechanism#getSupportedBindingTimes()
     * @see #getVariabilityMechanism()
     * @generated
     */
    EAttribute getVariabilityMechanism_SupportedBindingTimes();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.splevo.vrm.VariabilityMechanism#getSupportedExtensibilities
     * <em>Supported Extensibilities</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Supported Extensibilities</em>'.
     * @see org.splevo.vrm.VariabilityMechanism#getSupportedExtensibilities()
     * @see #getVariabilityMechanism()
     * @generated
     */
    EAttribute getVariabilityMechanism_SupportedExtensibilities();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.splevo.vrm.VariabilityMechanism#getSupportedVariabilityTypes
     * <em>Supported Variability Types</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Supported Variability Types</em>'.
     * @see org.splevo.vrm.VariabilityMechanism#getSupportedVariabilityTypes()
     * @see #getVariabilityMechanism()
     * @generated
     */
    EAttribute getVariabilityMechanism_SupportedVariabilityTypes();

    /**
     * Returns the meta object for class '{@link org.splevo.vrm.VariabilityRealizationConfiguration
     * <em>Variability Realization Configuration</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Variability Realization Configuration</em>'.
     * @see org.splevo.vrm.VariabilityRealizationConfiguration
     * @generated
     */
    EClass getVariabilityRealizationConfiguration();

    /**
     * Returns the meta object for the reference '
     * {@link org.splevo.vrm.VariabilityRealizationConfiguration#getVariationPoint
     * <em>Variation Point</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Variation Point</em>'.
     * @see org.splevo.vrm.VariabilityRealizationConfiguration#getVariationPoint()
     * @see #getVariabilityRealizationConfiguration()
     * @generated
     */
    EReference getVariabilityRealizationConfiguration_VariationPoint();

    /**
     * Returns the meta object for the reference '
     * {@link org.splevo.vrm.VariabilityRealizationConfiguration#getMechanism <em>Mechanism</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Mechanism</em>'.
     * @see org.splevo.vrm.VariabilityRealizationConfiguration#getMechanism()
     * @see #getVariabilityRealizationConfiguration()
     * @generated
     */
    EReference getVariabilityRealizationConfiguration_Mechanism();

    /**
     * Returns the meta object for class '{@link org.splevo.vrm.VariabilityRealizationModel
     * <em>Variability Realization Model</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Variability Realization Model</em>'.
     * @see org.splevo.vrm.VariabilityRealizationModel
     * @generated
     */
    EClass getVariabilityRealizationModel();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.splevo.vrm.VariabilityRealizationModel#getRealizationConfigurations
     * <em>Realization Configurations</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Realization Configurations</em>'.
     * @see org.splevo.vrm.VariabilityRealizationModel#getRealizationConfigurations()
     * @see #getVariabilityRealizationModel()
     * @generated
     */
    EReference getVariabilityRealizationModel_RealizationConfigurations();

    /**
     * Returns the meta object for the reference '
     * {@link org.splevo.vrm.VariabilityRealizationModel#getVpm <em>Vpm</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Vpm</em>'.
     * @see org.splevo.vrm.VariabilityRealizationModel#getVpm()
     * @see #getVariabilityRealizationModel()
     * @generated
     */
    EReference getVariabilityRealizationModel_Vpm();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    vrmFactory getvrmFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each operation of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link org.splevo.vrm.impl.VariabilityMechanismImpl
         * <em>Variability Mechanism</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.splevo.vrm.impl.VariabilityMechanismImpl
         * @see org.splevo.vrm.impl.vrmPackageImpl#getVariabilityMechanism()
         * @generated
         */
        EClass VARIABILITY_MECHANISM = eINSTANCE.getVariabilityMechanism();

        /**
         * The meta object literal for the '<em><b>Refactoring Id</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VARIABILITY_MECHANISM__REFACTORING_ID = eINSTANCE.getVariabilityMechanism_RefactoringId();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VARIABILITY_MECHANISM__NAME = eINSTANCE.getVariabilityMechanism_Name();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VARIABILITY_MECHANISM__DESCRIPTION = eINSTANCE.getVariabilityMechanism_Description();

        /**
         * The meta object literal for the '<em><b>Supported Binding Times</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VARIABILITY_MECHANISM__SUPPORTED_BINDING_TIMES = eINSTANCE
                .getVariabilityMechanism_SupportedBindingTimes();

        /**
         * The meta object literal for the '<em><b>Supported Extensibilities</b></em>' attribute
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VARIABILITY_MECHANISM__SUPPORTED_EXTENSIBILITIES = eINSTANCE
                .getVariabilityMechanism_SupportedExtensibilities();

        /**
         * The meta object literal for the '<em><b>Supported Variability Types</b></em>' attribute
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VARIABILITY_MECHANISM__SUPPORTED_VARIABILITY_TYPES = eINSTANCE
                .getVariabilityMechanism_SupportedVariabilityTypes();

        /**
         * The meta object literal for the '
         * {@link org.splevo.vrm.impl.VariabilityRealizationConfigurationImpl
         * <em>Variability Realization Configuration</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.splevo.vrm.impl.VariabilityRealizationConfigurationImpl
         * @see org.splevo.vrm.impl.vrmPackageImpl#getVariabilityRealizationConfiguration()
         * @generated
         */
        EClass VARIABILITY_REALIZATION_CONFIGURATION = eINSTANCE.getVariabilityRealizationConfiguration();

        /**
         * The meta object literal for the '<em><b>Variation Point</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VARIABILITY_REALIZATION_CONFIGURATION__VARIATION_POINT = eINSTANCE
                .getVariabilityRealizationConfiguration_VariationPoint();

        /**
         * The meta object literal for the '<em><b>Mechanism</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VARIABILITY_REALIZATION_CONFIGURATION__MECHANISM = eINSTANCE
                .getVariabilityRealizationConfiguration_Mechanism();

        /**
         * The meta object literal for the '
         * {@link org.splevo.vrm.impl.VariabilityRealizationModelImpl
         * <em>Variability Realization Model</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.splevo.vrm.impl.VariabilityRealizationModelImpl
         * @see org.splevo.vrm.impl.vrmPackageImpl#getVariabilityRealizationModel()
         * @generated
         */
        EClass VARIABILITY_REALIZATION_MODEL = eINSTANCE.getVariabilityRealizationModel();

        /**
         * The meta object literal for the '<em><b>Realization Configurations</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VARIABILITY_REALIZATION_MODEL__REALIZATION_CONFIGURATIONS = eINSTANCE
                .getVariabilityRealizationModel_RealizationConfigurations();

        /**
         * The meta object literal for the '<em><b>Vpm</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VARIABILITY_REALIZATION_MODEL__VPM = eINSTANCE.getVariabilityRealizationModel_Vpm();

    }

} // vrmPackage
