/**
 */
package org.splevo.vpm.variability.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.featuremodel.FeatureModelPackage;

import org.eclipse.gmt.modisco.java.emf.JavaPackage;

import org.eclipse.modisco.java.composition.javaapplication.JavaapplicationPackage;
import org.splevo.vpm.realization.impl.realizationPackageImpl;

import org.splevo.vpm.realization.realizationPackage;

import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityFactory;
import org.splevo.vpm.variability.variabilityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class variabilityPackageImpl extends EPackageImpl implements variabilityPackage {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass variationPointEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass variantEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass variationPointModelEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass variationPointGroupEClass = null;

	/**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.splevo.vpm.variability.variabilityPackage#eNS_URI
     * @see #init()
     * @generated
     */
	private variabilityPackageImpl() {
        super(eNS_URI, variabilityFactory.eINSTANCE);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static boolean isInited = false;

	/**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link variabilityPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
	public static variabilityPackage init() {
        if (isInited) return (variabilityPackage)EPackage.Registry.INSTANCE.getEPackage(variabilityPackage.eNS_URI);

        // Obtain or create and register package
        variabilityPackageImpl thevariabilityPackage = (variabilityPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof variabilityPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new variabilityPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        FeatureModelPackage.eINSTANCE.eClass();
        JavaapplicationPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        realizationPackageImpl therealizationPackage = (realizationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(realizationPackage.eNS_URI) instanceof realizationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(realizationPackage.eNS_URI) : realizationPackage.eINSTANCE);

        // Create package meta-data objects
        thevariabilityPackage.createPackageContents();
        therealizationPackage.createPackageContents();

        // Initialize created meta-data
        thevariabilityPackage.initializePackageContents();
        therealizationPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        thevariabilityPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(variabilityPackage.eNS_URI, thevariabilityPackage);
        return thevariabilityPackage;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getVariationPoint() {
        return variationPointEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getVariationPoint_Variants() {
        return (EReference)variationPointEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getVariationPoint_RealizationTechnique() {
        return (EReference)variationPointEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getVariationPoint_EnclosingSoftwareEntity() {
        return (EReference)variationPointEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getVariationPoint_Group() {
        return (EReference)variationPointEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getVariant() {
        return variantEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getVariant_Feature() {
        return (EReference)variantEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getVariant_SoftwareEntities() {
        return (EReference)variantEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getVariant_Leading() {
        return (EAttribute)variantEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getVariant_VariantId() {
        return (EAttribute)variantEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getVariant_VariationPoint() {
        return (EReference)variantEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getVariationPointModel() {
        return variationPointModelEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getVariationPointModel_RealizationTechniques() {
        return (EReference)variationPointModelEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getVariationPointModel_LeadingModel() {
        return (EReference)variationPointModelEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getVariationPointModel_IntegrationModel() {
        return (EReference)variationPointModelEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getVariationPointModel_VariationPointGroups() {
        return (EReference)variationPointModelEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getVariationPointGroup() {
        return variationPointGroupEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getVariationPointGroup_VariationPoints() {
        return (EReference)variationPointGroupEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getVariationPointGroup_GroupId() {
        return (EAttribute)variationPointGroupEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getVariationPointGroup_Model() {
        return (EReference)variationPointGroupEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getVariationPointGroup_Feature() {
        return (EReference)variationPointGroupEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public variabilityFactory getvariabilityFactory() {
        return (variabilityFactory)getEFactoryInstance();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isCreated = false;

	/**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        variationPointEClass = createEClass(VARIATION_POINT);
        createEReference(variationPointEClass, VARIATION_POINT__VARIANTS);
        createEReference(variationPointEClass, VARIATION_POINT__REALIZATION_TECHNIQUE);
        createEReference(variationPointEClass, VARIATION_POINT__ENCLOSING_SOFTWARE_ENTITY);
        createEReference(variationPointEClass, VARIATION_POINT__GROUP);

        variantEClass = createEClass(VARIANT);
        createEReference(variantEClass, VARIANT__FEATURE);
        createEReference(variantEClass, VARIANT__SOFTWARE_ENTITIES);
        createEAttribute(variantEClass, VARIANT__LEADING);
        createEAttribute(variantEClass, VARIANT__VARIANT_ID);
        createEReference(variantEClass, VARIANT__VARIATION_POINT);

        variationPointModelEClass = createEClass(VARIATION_POINT_MODEL);
        createEReference(variationPointModelEClass, VARIATION_POINT_MODEL__REALIZATION_TECHNIQUES);
        createEReference(variationPointModelEClass, VARIATION_POINT_MODEL__LEADING_MODEL);
        createEReference(variationPointModelEClass, VARIATION_POINT_MODEL__INTEGRATION_MODEL);
        createEReference(variationPointModelEClass, VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS);

        variationPointGroupEClass = createEClass(VARIATION_POINT_GROUP);
        createEReference(variationPointGroupEClass, VARIATION_POINT_GROUP__VARIATION_POINTS);
        createEAttribute(variationPointGroupEClass, VARIATION_POINT_GROUP__GROUP_ID);
        createEReference(variationPointGroupEClass, VARIATION_POINT_GROUP__MODEL);
        createEReference(variationPointGroupEClass, VARIATION_POINT_GROUP__FEATURE);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isInitialized = false;

	/**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        realizationPackage therealizationPackage = (realizationPackage)EPackage.Registry.INSTANCE.getEPackage(realizationPackage.eNS_URI);
        JavaPackage theJavaPackage = (JavaPackage)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI);
        FeatureModelPackage theFeatureModelPackage = (FeatureModelPackage)EPackage.Registry.INSTANCE.getEPackage(FeatureModelPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        JavaapplicationPackage theJavaapplicationPackage = (JavaapplicationPackage)EPackage.Registry.INSTANCE.getEPackage(JavaapplicationPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes and features; add operations and parameters
        initEClass(variationPointEClass, VariationPoint.class, "VariationPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVariationPoint_Variants(), this.getVariant(), this.getVariant_VariationPoint(), "variants", null, 0, -1, VariationPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVariationPoint_RealizationTechnique(), therealizationPackage.getRealizationTechnique(), null, "realizationTechnique", null, 0, 1, VariationPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVariationPoint_EnclosingSoftwareEntity(), theJavaPackage.getASTNode(), null, "enclosingSoftwareEntity", null, 1, 1, VariationPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVariationPoint_Group(), this.getVariationPointGroup(), this.getVariationPointGroup_VariationPoints(), "group", null, 1, 1, VariationPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(variantEClass, Variant.class, "Variant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVariant_Feature(), theFeatureModelPackage.getFeature(), null, "feature", null, 0, 1, Variant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVariant_SoftwareEntities(), theJavaPackage.getASTNode(), null, "softwareEntities", null, 0, -1, Variant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVariant_Leading(), theEcorePackage.getEBooleanObject(), "leading", null, 1, 1, Variant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVariant_VariantId(), theEcorePackage.getEString(), "variantId", null, 1, 1, Variant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVariant_VariationPoint(), this.getVariationPoint(), this.getVariationPoint_Variants(), "variationPoint", null, 1, 1, Variant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(variationPointModelEClass, VariationPointModel.class, "VariationPointModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVariationPointModel_RealizationTechniques(), therealizationPackage.getRealizationTechnique(), null, "realizationTechniques", null, 0, -1, VariationPointModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVariationPointModel_LeadingModel(), theJavaapplicationPackage.getJavaApplication(), null, "leadingModel", null, 1, 1, VariationPointModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVariationPointModel_IntegrationModel(), theJavaapplicationPackage.getJavaApplication(), null, "integrationModel", null, 1, 1, VariationPointModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVariationPointModel_VariationPointGroups(), this.getVariationPointGroup(), this.getVariationPointGroup_Model(), "variationPointGroups", null, 0, -1, VariationPointModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(variationPointGroupEClass, VariationPointGroup.class, "VariationPointGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVariationPointGroup_VariationPoints(), this.getVariationPoint(), this.getVariationPoint_Group(), "variationPoints", null, 0, -1, VariationPointGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVariationPointGroup_GroupId(), theEcorePackage.getEString(), "groupId", null, 1, 1, VariationPointGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVariationPointGroup_Model(), this.getVariationPointModel(), this.getVariationPointModel_VariationPointGroups(), "model", null, 1, 1, VariationPointGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVariationPointGroup_Feature(), theFeatureModelPackage.getFeature(), null, "feature", null, 0, 1, VariationPointGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //variabilityPackageImpl