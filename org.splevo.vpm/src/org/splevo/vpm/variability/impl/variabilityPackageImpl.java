/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.vpm.variability.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.featuremodel.FeatureModelPackage;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionPackage;

import org.eclipse.gmt.modisco.omg.kdm.build.BuildPackage;

import org.eclipse.gmt.modisco.omg.kdm.code.CodePackage;

import org.eclipse.gmt.modisco.omg.kdm.conceptual.ConceptualPackage;

import org.eclipse.gmt.modisco.omg.kdm.core.CorePackage;

import org.eclipse.gmt.modisco.omg.kdm.data.DataPackage;

import org.eclipse.gmt.modisco.omg.kdm.event.EventPackage;

import org.eclipse.gmt.modisco.omg.kdm.kdm.KdmPackage;

import org.eclipse.gmt.modisco.omg.kdm.platform.PlatformPackage;

import org.eclipse.gmt.modisco.omg.kdm.source.SourcePackage;

import org.eclipse.gmt.modisco.omg.kdm.structure.StructurePackage;

import org.eclipse.gmt.modisco.omg.kdm.ui.UiPackage;

import org.splevo.vpm.impl.vpmPackageImpl;

import org.splevo.vpm.realization.impl.realizationPackageImpl;

import org.splevo.vpm.realization.realizationPackage;

import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.variabilityFactory;
import org.splevo.vpm.variability.variabilityPackage;

import org.splevo.vpm.vpmPackage;

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
		CorePackage.eINSTANCE.eClass();
		KdmPackage.eINSTANCE.eClass();
		SourcePackage.eINSTANCE.eClass();
		CodePackage.eINSTANCE.eClass();
		ActionPackage.eINSTANCE.eClass();
		PlatformPackage.eINSTANCE.eClass();
		BuildPackage.eINSTANCE.eClass();
		ConceptualPackage.eINSTANCE.eClass();
		DataPackage.eINSTANCE.eClass();
		EventPackage.eINSTANCE.eClass();
		StructurePackage.eINSTANCE.eClass();
		UiPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		vpmPackageImpl thevpmPackage = (vpmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(vpmPackage.eNS_URI) instanceof vpmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(vpmPackage.eNS_URI) : vpmPackage.eINSTANCE);
		realizationPackageImpl therealizationPackage = (realizationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(realizationPackage.eNS_URI) instanceof realizationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(realizationPackage.eNS_URI) : realizationPackage.eINSTANCE);

		// Create package meta-data objects
		thevariabilityPackage.createPackageContents();
		thevpmPackage.createPackageContents();
		therealizationPackage.createPackageContents();

		// Initialize created meta-data
		thevariabilityPackage.initializePackageContents();
		thevpmPackage.initializePackageContents();
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
	public EReference getVariationPoint_SoftwareEntities() {
		return (EReference)variationPointEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariationPoint_Feature() {
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
	public EReference getVariant_SoftwareEntities() {
		return (EReference)variantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariant_Feature() {
		return (EReference)variantEClass.getEStructuralFeatures().get(1);
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
		createEReference(variationPointEClass, VARIATION_POINT__SOFTWARE_ENTITIES);
		createEReference(variationPointEClass, VARIATION_POINT__FEATURE);

		variantEClass = createEClass(VARIANT);
		createEReference(variantEClass, VARIANT__SOFTWARE_ENTITIES);
		createEReference(variantEClass, VARIANT__FEATURE);
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
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);
		FeatureModelPackage theFeatureModelPackage = (FeatureModelPackage)EPackage.Registry.INSTANCE.getEPackage(FeatureModelPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(variationPointEClass, VariationPoint.class, "VariationPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVariationPoint_Variants(), this.getVariant(), null, "variants", null, 0, -1, VariationPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVariationPoint_RealizationTechnique(), therealizationPackage.getRealizationTechnique(), null, "realizationTechnique", null, 0, 1, VariationPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVariationPoint_SoftwareEntities(), theCorePackage.getKDMEntity(), null, "softwareEntities", null, 0, -1, VariationPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVariationPoint_Feature(), theFeatureModelPackage.getFeature(), null, "feature", null, 0, 1, VariationPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(variantEClass, Variant.class, "Variant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVariant_SoftwareEntities(), theCorePackage.getKDMEntity(), null, "softwareEntities", null, 0, -1, Variant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVariant_Feature(), theFeatureModelPackage.getFeature(), null, "feature", null, 0, 1, Variant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //variabilityPackageImpl
