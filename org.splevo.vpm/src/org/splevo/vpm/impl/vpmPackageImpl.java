/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.vpm.impl;

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

import org.splevo.vpm.VariationPointModel;

import org.splevo.vpm.realization.impl.realizationPackageImpl;

import org.splevo.vpm.realization.realizationPackage;

import org.splevo.vpm.variability.impl.variabilityPackageImpl;

import org.splevo.vpm.variability.variabilityPackage;

import org.splevo.vpm.vpmFactory;
import org.splevo.vpm.vpmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class vpmPackageImpl extends EPackageImpl implements vpmPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variationPointModelEClass = null;

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
	 * @see org.splevo.vpm.vpmPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private vpmPackageImpl() {
		super(eNS_URI, vpmFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link vpmPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static vpmPackage init() {
		if (isInited) return (vpmPackage)EPackage.Registry.INSTANCE.getEPackage(vpmPackage.eNS_URI);

		// Obtain or create and register package
		vpmPackageImpl thevpmPackage = (vpmPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof vpmPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new vpmPackageImpl());

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
		variabilityPackageImpl thevariabilityPackage = (variabilityPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(variabilityPackage.eNS_URI) instanceof variabilityPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(variabilityPackage.eNS_URI) : variabilityPackage.eINSTANCE);
		realizationPackageImpl therealizationPackage = (realizationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(realizationPackage.eNS_URI) instanceof realizationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(realizationPackage.eNS_URI) : realizationPackage.eINSTANCE);

		// Create package meta-data objects
		thevpmPackage.createPackageContents();
		thevariabilityPackage.createPackageContents();
		therealizationPackage.createPackageContents();

		// Initialize created meta-data
		thevpmPackage.initializePackageContents();
		thevariabilityPackage.initializePackageContents();
		therealizationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thevpmPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(vpmPackage.eNS_URI, thevpmPackage);
		return thevpmPackage;
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
	public EReference getVariationPointModel_VariationPoints() {
		return (EReference)variationPointModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariationPointModel_RealizationTechniques() {
		return (EReference)variationPointModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public vpmFactory getvpmFactory() {
		return (vpmFactory)getEFactoryInstance();
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
		variationPointModelEClass = createEClass(VARIATION_POINT_MODEL);
		createEReference(variationPointModelEClass, VARIATION_POINT_MODEL__VARIATION_POINTS);
		createEReference(variationPointModelEClass, VARIATION_POINT_MODEL__REALIZATION_TECHNIQUES);
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
		variabilityPackage thevariabilityPackage = (variabilityPackage)EPackage.Registry.INSTANCE.getEPackage(variabilityPackage.eNS_URI);
		realizationPackage therealizationPackage = (realizationPackage)EPackage.Registry.INSTANCE.getEPackage(realizationPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(thevariabilityPackage);
		getESubpackages().add(therealizationPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(variationPointModelEClass, VariationPointModel.class, "VariationPointModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVariationPointModel_VariationPoints(), thevariabilityPackage.getVariationPoint(), null, "variationPoints", null, 0, -1, VariationPointModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVariationPointModel_RealizationTechniques(), therealizationPackage.getRealizationTechnique(), null, "realizationTechniques", null, 0, -1, VariationPointModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //vpmPackageImpl
