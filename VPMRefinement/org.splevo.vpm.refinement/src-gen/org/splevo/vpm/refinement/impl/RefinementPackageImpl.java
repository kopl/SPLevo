/**
 */
package org.splevo.vpm.refinement.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.splevo.vpm.realization.RealizationPackage;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementFactory;
import org.splevo.vpm.refinement.RefinementModel;
import org.splevo.vpm.refinement.RefinementPackage;
import org.splevo.vpm.refinement.RefinementReason;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.software.SoftwarePackage;
import org.splevo.vpm.variability.variabilityPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class RefinementPackageImpl extends EPackageImpl implements RefinementPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass refinementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass refinementReasonEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass refinementModelEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum refinementTypeEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.splevo.vpm.refinement.RefinementPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private RefinementPackageImpl() {
        super(eNS_URI, RefinementFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link RefinementPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static RefinementPackage init() {
        if (isInited)
            return (RefinementPackage) EPackage.Registry.INSTANCE.getEPackage(RefinementPackage.eNS_URI);

        // Obtain or create and register package
        RefinementPackageImpl theRefinementPackage = (RefinementPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RefinementPackageImpl ? EPackage.Registry.INSTANCE
                .get(eNS_URI) : new RefinementPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        variabilityPackage.eINSTANCE.eClass();
        SoftwarePackage.eINSTANCE.eClass();
        RealizationPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theRefinementPackage.createPackageContents();

        // Initialize created meta-data
        theRefinementPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theRefinementPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(RefinementPackage.eNS_URI, theRefinementPackage);
        return theRefinementPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getRefinement() {
        return refinementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getRefinement_Id() {
        return (EAttribute) refinementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getRefinement_Type() {
        return (EAttribute) refinementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getRefinement_VariationPoints() {
        return (EReference) refinementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getRefinement_RefinementModel() {
        return (EReference) refinementEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getRefinement_Source() {
        return (EAttribute) refinementEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRefinement_Parent() {
        return (EReference) refinementEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRefinement_SubRefinements() {
        return (EReference) refinementEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRefinement_Reasons() {
        return (EReference) refinementEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getRefinementReason() {
        return refinementReasonEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRefinementReason_Source() {
        return (EReference) refinementReasonEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRefinementReason_Target() {
        return (EReference) refinementReasonEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRefinementReason_Refinement() {
        return (EReference) refinementReasonEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getRefinementReason_Reason() {
        return (EAttribute) refinementReasonEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass getRefinementModel() {
        return refinementModelEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EReference getRefinementModel_Refinements() {
        return (EReference) refinementModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EEnum getRefinementType() {
        return refinementTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public RefinementFactory getRefinementFactory() {
        return (RefinementFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        refinementModelEClass = createEClass(REFINEMENT_MODEL);
        createEReference(refinementModelEClass, REFINEMENT_MODEL__REFINEMENTS);

        refinementEClass = createEClass(REFINEMENT);
        createEAttribute(refinementEClass, REFINEMENT__ID);
        createEAttribute(refinementEClass, REFINEMENT__TYPE);
        createEReference(refinementEClass, REFINEMENT__VARIATION_POINTS);
        createEReference(refinementEClass, REFINEMENT__REFINEMENT_MODEL);
        createEAttribute(refinementEClass, REFINEMENT__SOURCE);
        createEReference(refinementEClass, REFINEMENT__PARENT);
        createEReference(refinementEClass, REFINEMENT__SUB_REFINEMENTS);
        createEReference(refinementEClass, REFINEMENT__REASONS);

        refinementReasonEClass = createEClass(REFINEMENT_REASON);
        createEReference(refinementReasonEClass, REFINEMENT_REASON__SOURCE);
        createEReference(refinementReasonEClass, REFINEMENT_REASON__TARGET);
        createEReference(refinementReasonEClass, REFINEMENT_REASON__REFINEMENT);
        createEAttribute(refinementReasonEClass, REFINEMENT_REASON__REASON);

        // Create enums
        refinementTypeEEnum = createEEnum(REFINEMENT_TYPE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        variabilityPackage thevariabilityPackage = (variabilityPackage) EPackage.Registry.INSTANCE
                .getEPackage(variabilityPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        refinementEClass.getESuperTypes().add(thevariabilityPackage.getCustomizableDescriptionHaving());

        // Initialize classes and features; add operations and parameters
        initEClass(refinementModelEClass, RefinementModel.class, "RefinementModel", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRefinementModel_Refinements(), this.getRefinement(), this.getRefinement_RefinementModel(),
                "refinements", null, 0, -1, RefinementModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(refinementEClass, Refinement.class, "Refinement", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRefinement_Id(), ecorePackage.getEString(), "id", null, 0, 1, Refinement.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRefinement_Type(), this.getRefinementType(), "type", "GROUPING", 1, 1, Refinement.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRefinement_VariationPoints(), thevariabilityPackage.getVariationPoint(), null,
                "variationPoints", null, 0, -1, Refinement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRefinement_RefinementModel(), this.getRefinementModel(),
                this.getRefinementModel_Refinements(), "refinementModel", null, 0, 1, Refinement.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRefinement_Source(), ecorePackage.getEString(), "source", null, 0, 1, Refinement.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRefinement_Parent(), this.getRefinement(), this.getRefinement_SubRefinements(), "parent",
                null, 0, 1, Refinement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRefinement_SubRefinements(), this.getRefinement(), this.getRefinement_Parent(),
                "subRefinements", null, 0, -1, Refinement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRefinement_Reasons(), this.getRefinementReason(), this.getRefinementReason_Refinement(),
                "reasons", null, 0, -1, Refinement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(refinementReasonEClass, RefinementReason.class, "RefinementReason", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRefinementReason_Source(), thevariabilityPackage.getVariationPoint(), null, "source", null,
                1, 1, RefinementReason.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRefinementReason_Target(), thevariabilityPackage.getVariationPoint(), null, "target", null,
                1, 1, RefinementReason.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRefinementReason_Refinement(), this.getRefinement(), this.getRefinement_Reasons(),
                "refinement", null, 1, 1, RefinementReason.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRefinementReason_Reason(), theEcorePackage.getEString(), "reason", null, 1, 1,
                RefinementReason.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(refinementTypeEEnum, RefinementType.class, "RefinementType");
        addEEnumLiteral(refinementTypeEEnum, RefinementType.MERGE);
        addEEnumLiteral(refinementTypeEEnum, RefinementType.GROUPING);

        // Create resource
        createResource(eNS_URI);
    }

} // RefinementPackageImpl
