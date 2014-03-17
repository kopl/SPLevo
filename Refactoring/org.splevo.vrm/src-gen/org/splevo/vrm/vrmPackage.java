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
import org.eclipse.emf.ecore.EOperation;
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
     * The meta object id for the '{@link org.splevo.vrm.impl.VariabilityRealizationTechniqueImpl
     * <em>Variability Realization Technique</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.splevo.vrm.impl.VariabilityRealizationTechniqueImpl
     * @see org.splevo.vrm.impl.vrmPackageImpl#getVariabilityRealizationTechnique()
     * @generated
     */
    int VARIABILITY_REALIZATION_TECHNIQUE = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_REALIZATION_TECHNIQUE__NAME = 0;

    /**
     * The number of structural features of the '<em>Variability Realization Technique</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_REALIZATION_TECHNIQUE_FEATURE_COUNT = 1;

    /**
     * The operation id for the '<em>Can Be Applied To</em>' operation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_REALIZATION_TECHNIQUE___CAN_BE_APPLIED_TO__VARIATIONPOINT = 0;

    /**
     * The number of operations of the '<em>Variability Realization Technique</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_REALIZATION_TECHNIQUE_OPERATION_COUNT = 1;

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
     * The feature id for the '<em><b>Technique</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABILITY_REALIZATION_CONFIGURATION__TECHNIQUE = 1;

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
     * Returns the meta object for class '{@link org.splevo.vrm.VariabilityRealizationTechnique
     * <em>Variability Realization Technique</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Variability Realization Technique</em>'.
     * @see org.splevo.vrm.VariabilityRealizationTechnique
     * @generated
     */
    EClass getVariabilityRealizationTechnique();

    /**
     * Returns the meta object for the attribute '
     * {@link org.splevo.vrm.VariabilityRealizationTechnique#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.splevo.vrm.VariabilityRealizationTechnique#getName()
     * @see #getVariabilityRealizationTechnique()
     * @generated
     */
    EAttribute getVariabilityRealizationTechnique_Name();

    /**
     * Returns the meta object for the '
     * {@link org.splevo.vrm.VariabilityRealizationTechnique#canBeAppliedTo(org.splevo.vpm.variability.VariationPoint)
     * <em>Can Be Applied To</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the '<em>Can Be Applied To</em>' operation.
     * @see org.splevo.vrm.VariabilityRealizationTechnique#canBeAppliedTo(org.splevo.vpm.variability.VariationPoint)
     * @generated
     */
    EOperation getVariabilityRealizationTechnique__CanBeAppliedTo__VariationPoint();

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
     * {@link org.splevo.vrm.VariabilityRealizationConfiguration#getTechnique <em>Technique</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Technique</em>'.
     * @see org.splevo.vrm.VariabilityRealizationConfiguration#getTechnique()
     * @see #getVariabilityRealizationConfiguration()
     * @generated
     */
    EReference getVariabilityRealizationConfiguration_Technique();

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
         * The meta object literal for the '
         * {@link org.splevo.vrm.impl.VariabilityRealizationTechniqueImpl
         * <em>Variability Realization Technique</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.splevo.vrm.impl.VariabilityRealizationTechniqueImpl
         * @see org.splevo.vrm.impl.vrmPackageImpl#getVariabilityRealizationTechnique()
         * @generated
         */
        EClass VARIABILITY_REALIZATION_TECHNIQUE = eINSTANCE.getVariabilityRealizationTechnique();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VARIABILITY_REALIZATION_TECHNIQUE__NAME = eINSTANCE.getVariabilityRealizationTechnique_Name();

        /**
         * The meta object literal for the '<em><b>Can Be Applied To</b></em>' operation. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EOperation VARIABILITY_REALIZATION_TECHNIQUE___CAN_BE_APPLIED_TO__VARIATIONPOINT = eINSTANCE
                .getVariabilityRealizationTechnique__CanBeAppliedTo__VariationPoint();

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
         * The meta object literal for the '<em><b>Technique</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VARIABILITY_REALIZATION_CONFIGURATION__TECHNIQUE = eINSTANCE
                .getVariabilityRealizationConfiguration_Technique();

    }

} // vrmPackage
