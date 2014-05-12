/**
 */
package org.splevo.vpm.refinement;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta
 * objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.splevo.vpm.refinement.RefinementFactory
 * @model kind="package"
 * @generated
 */
public interface RefinementPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "refinement";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://splevo.org/vpm/refinement/1.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "org.splevo.vpm.refinement";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    RefinementPackage eINSTANCE = org.splevo.vpm.refinement.impl.RefinementPackageImpl.init();

    /**
     * The meta object id for the '{@link org.splevo.vpm.refinement.impl.RefinementImpl <em>Refinement</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.splevo.vpm.refinement.impl.RefinementImpl
     * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getRefinement()
     * @generated
     */
    int REFINEMENT = 1;

    /**
     * The meta object id for the '{@link org.splevo.vpm.refinement.impl.RefinementModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.splevo.vpm.refinement.impl.RefinementModelImpl
     * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getRefinementModel()
     * @generated
     */
    int REFINEMENT_MODEL = 0;

    /**
     * The feature id for the '<em><b>Refinements</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REFINEMENT_MODEL__REFINEMENTS = 0;

    /**
     * The number of structural features of the '<em>Model</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int REFINEMENT_MODEL_FEATURE_COUNT = 1;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int REFINEMENT__TYPE = 0;

    /**
     * The feature id for the '<em><b>Variation Points</b></em>' reference list.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFINEMENT__VARIATION_POINTS = 1;

    /**
     * The feature id for the '<em><b>Refinement Model</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REFINEMENT__REFINEMENT_MODEL = 2;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int REFINEMENT__SOURCE = 3;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFINEMENT__PARENT = 4;

    /**
     * The feature id for the '<em><b>Sub Refinements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFINEMENT__SUB_REFINEMENTS = 5;

    /**
     * The feature id for the '<em><b>Reasons</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFINEMENT__REASONS = 6;

    /**
     * The number of structural features of the '<em>Refinement</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFINEMENT_FEATURE_COUNT = 7;

    /**
     * The meta object id for the '{@link org.splevo.vpm.refinement.impl.RefinementReasonImpl <em>Reason</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.vpm.refinement.impl.RefinementReasonImpl
     * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getRefinementReason()
     * @generated
     */
    int REFINEMENT_REASON = 2;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFINEMENT_REASON__SOURCE = 0;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFINEMENT_REASON__TARGET = 1;

    /**
     * The feature id for the '<em><b>Refinement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFINEMENT_REASON__REFINEMENT = 2;

    /**
     * The feature id for the '<em><b>Reason</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFINEMENT_REASON__REASON = 3;

    /**
     * The number of structural features of the '<em>Reason</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFINEMENT_REASON_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link org.splevo.vpm.refinement.RefinementType <em>Type</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.splevo.vpm.refinement.RefinementType
     * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getRefinementType()
     * @generated
     */
    int REFINEMENT_TYPE = 3;

    /**
     * Returns the meta object for class '{@link org.splevo.vpm.refinement.Refinement <em>Refinement</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Variation Points</em>'.
     * @see org.splevo.vpm.refinement.Refinement#getVariationPoints()
     * @see #getRefinement()
     * @generated
     */
    EReference getRefinement_VariationPoints();

    /**
     * Returns the meta object for the container reference '{@link org.splevo.vpm.refinement.Refinement#getRefinementModel <em>Refinement Model</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Refinement Model</em>'.
     * @see org.splevo.vpm.refinement.Refinement#getRefinementModel()
     * @see #getRefinement()
     * @generated
     */
    EReference getRefinement_RefinementModel();

    /**
     * Returns the meta object for the attribute '{@link org.splevo.vpm.refinement.Refinement#getSource <em>Source</em>}'.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Source</em>'.
     * @see org.splevo.vpm.refinement.Refinement#getSource()
     * @see #getRefinement()
     * @generated
     */
    EAttribute getRefinement_Source();

    /**
     * Returns the meta object for the container reference '{@link org.splevo.vpm.refinement.Refinement#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Parent</em>'.
     * @see org.splevo.vpm.refinement.Refinement#getParent()
     * @see #getRefinement()
     * @generated
     */
    EReference getRefinement_Parent();

    /**
     * Returns the meta object for the containment reference list '{@link org.splevo.vpm.refinement.Refinement#getSubRefinements <em>Sub Refinements</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Sub Refinements</em>'.
     * @see org.splevo.vpm.refinement.Refinement#getSubRefinements()
     * @see #getRefinement()
     * @generated
     */
    EReference getRefinement_SubRefinements();

    /**
     * Returns the meta object for the containment reference list '{@link org.splevo.vpm.refinement.Refinement#getReasons <em>Reasons</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Reasons</em>'.
     * @see org.splevo.vpm.refinement.Refinement#getReasons()
     * @see #getRefinement()
     * @generated
     */
    EReference getRefinement_Reasons();

    /**
     * Returns the meta object for class '{@link org.splevo.vpm.refinement.RefinementReason <em>Reason</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Reason</em>'.
     * @see org.splevo.vpm.refinement.RefinementReason
     * @generated
     */
    EClass getRefinementReason();

    /**
     * Returns the meta object for the reference '{@link org.splevo.vpm.refinement.RefinementReason#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see org.splevo.vpm.refinement.RefinementReason#getSource()
     * @see #getRefinementReason()
     * @generated
     */
    EReference getRefinementReason_Source();

    /**
     * Returns the meta object for the reference '{@link org.splevo.vpm.refinement.RefinementReason#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see org.splevo.vpm.refinement.RefinementReason#getTarget()
     * @see #getRefinementReason()
     * @generated
     */
    EReference getRefinementReason_Target();

    /**
     * Returns the meta object for the container reference '{@link org.splevo.vpm.refinement.RefinementReason#getRefinement <em>Refinement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Refinement</em>'.
     * @see org.splevo.vpm.refinement.RefinementReason#getRefinement()
     * @see #getRefinementReason()
     * @generated
     */
    EReference getRefinementReason_Refinement();

    /**
     * Returns the meta object for the attribute '{@link org.splevo.vpm.refinement.RefinementReason#getReason <em>Reason</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Reason</em>'.
     * @see org.splevo.vpm.refinement.RefinementReason#getReason()
     * @see #getRefinementReason()
     * @generated
     */
    EAttribute getRefinementReason_Reason();

    /**
     * Returns the meta object for class '{@link org.splevo.vpm.refinement.RefinementModel <em>Model</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Model</em>'.
     * @see org.splevo.vpm.refinement.RefinementModel
     * @generated
     */
    EClass getRefinementModel();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.splevo.vpm.refinement.RefinementModel#getRefinements <em>Refinements</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Refinements</em>'.
     * @see org.splevo.vpm.refinement.RefinementModel#getRefinements()
     * @see #getRefinementModel()
     * @generated
     */
    EReference getRefinementModel_Refinements();

    /**
     * Returns the meta object for enum '{@link org.splevo.vpm.refinement.RefinementType <em>Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>Type</em>'.
     * @see org.splevo.vpm.refinement.RefinementType
     * @generated
     */
    EEnum getRefinementType();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    RefinementFactory getRefinementFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link org.splevo.vpm.refinement.impl.RefinementImpl <em>Refinement</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.splevo.vpm.refinement.impl.RefinementImpl
         * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getRefinement()
         * @generated
         */
        EClass REFINEMENT = eINSTANCE.getRefinement();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REFINEMENT__TYPE = eINSTANCE.getRefinement_Type();

        /**
         * The meta object literal for the '<em><b>Variation Points</b></em>' reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference REFINEMENT__VARIATION_POINTS = eINSTANCE.getRefinement_VariationPoints();

        /**
         * The meta object literal for the '<em><b>Refinement Model</b></em>' container reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference REFINEMENT__REFINEMENT_MODEL = eINSTANCE.getRefinement_RefinementModel();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REFINEMENT__SOURCE = eINSTANCE.getRefinement_Source();

        /**
         * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference REFINEMENT__PARENT = eINSTANCE.getRefinement_Parent();

        /**
         * The meta object literal for the '<em><b>Sub Refinements</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference REFINEMENT__SUB_REFINEMENTS = eINSTANCE.getRefinement_SubRefinements();

        /**
         * The meta object literal for the '<em><b>Reasons</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference REFINEMENT__REASONS = eINSTANCE.getRefinement_Reasons();

        /**
         * The meta object literal for the '{@link org.splevo.vpm.refinement.impl.RefinementReasonImpl <em>Reason</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.vpm.refinement.impl.RefinementReasonImpl
         * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getRefinementReason()
         * @generated
         */
        EClass REFINEMENT_REASON = eINSTANCE.getRefinementReason();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference REFINEMENT_REASON__SOURCE = eINSTANCE.getRefinementReason_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference REFINEMENT_REASON__TARGET = eINSTANCE.getRefinementReason_Target();

        /**
         * The meta object literal for the '<em><b>Refinement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference REFINEMENT_REASON__REFINEMENT = eINSTANCE.getRefinementReason_Refinement();

        /**
         * The meta object literal for the '<em><b>Reason</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute REFINEMENT_REASON__REASON = eINSTANCE.getRefinementReason_Reason();

        /**
         * The meta object literal for the '
         * {@link org.splevo.vpm.refinement.impl.RefinementModelImpl <em>Model</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.splevo.vpm.refinement.impl.RefinementModelImpl
         * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getRefinementModel()
         * @generated
         */
        EClass REFINEMENT_MODEL = eINSTANCE.getRefinementModel();

        /**
         * The meta object literal for the '<em><b>Refinements</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference REFINEMENT_MODEL__REFINEMENTS = eINSTANCE.getRefinementModel_Refinements();

        /**
         * The meta object literal for the '{@link org.splevo.vpm.refinement.RefinementType <em>Type</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.splevo.vpm.refinement.RefinementType
         * @see org.splevo.vpm.refinement.impl.RefinementPackageImpl#getRefinementType()
         * @generated
         */
        EEnum REFINEMENT_TYPE = eINSTANCE.getRefinementType();

    }

} // RefinementPackage
