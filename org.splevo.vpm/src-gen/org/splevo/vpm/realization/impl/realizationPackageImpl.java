/**
 */
package org.splevo.vpm.realization.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.featuremodel.FeatureModelPackage;

import org.eclipse.modisco.java.composition.javaapplication.JavaapplicationPackage;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;

import org.splevo.vpm.realization.CompilationTimeRealizationTechnique;
import org.splevo.vpm.realization.DesignTimeRealizationTechnique;
import org.splevo.vpm.realization.LinkingTimeRealizationTechnique;
import org.splevo.vpm.realization.RealizationTechnique;
import org.splevo.vpm.realization.RuntimeRealizationTechnique;
import org.splevo.vpm.realization.realizationFactory;
import org.splevo.vpm.realization.realizationPackage;

import org.splevo.vpm.variability.impl.variabilityPackageImpl;

import org.splevo.vpm.variability.variabilityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class realizationPackageImpl extends EPackageImpl implements realizationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass realizationTechniqueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass designTimeRealizationTechniqueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compilationTimeRealizationTechniqueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linkingTimeRealizationTechniqueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass runtimeRealizationTechniqueEClass = null;

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
	 * @see org.splevo.vpm.realization.realizationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private realizationPackageImpl() {
		super(eNS_URI, realizationFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link realizationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static realizationPackage init() {
		if (isInited) return (realizationPackage)EPackage.Registry.INSTANCE.getEPackage(realizationPackage.eNS_URI);

		// Obtain or create and register package
		realizationPackageImpl therealizationPackage = (realizationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof realizationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new realizationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		FeatureModelPackage.eINSTANCE.eClass();
		JavaapplicationPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		variabilityPackageImpl thevariabilityPackage = (variabilityPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(variabilityPackage.eNS_URI) instanceof variabilityPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(variabilityPackage.eNS_URI) : variabilityPackage.eINSTANCE);

		// Create package meta-data objects
		therealizationPackage.createPackageContents();
		thevariabilityPackage.createPackageContents();

		// Initialize created meta-data
		therealizationPackage.initializePackageContents();
		thevariabilityPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		therealizationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(realizationPackage.eNS_URI, therealizationPackage);
		return therealizationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRealizationTechnique() {
		return realizationTechniqueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDesignTimeRealizationTechnique() {
		return designTimeRealizationTechniqueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompilationTimeRealizationTechnique() {
		return compilationTimeRealizationTechniqueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLinkingTimeRealizationTechnique() {
		return linkingTimeRealizationTechniqueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRuntimeRealizationTechnique() {
		return runtimeRealizationTechniqueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public realizationFactory getrealizationFactory() {
		return (realizationFactory)getEFactoryInstance();
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
		realizationTechniqueEClass = createEClass(REALIZATION_TECHNIQUE);

		designTimeRealizationTechniqueEClass = createEClass(DESIGN_TIME_REALIZATION_TECHNIQUE);

		compilationTimeRealizationTechniqueEClass = createEClass(COMPILATION_TIME_REALIZATION_TECHNIQUE);

		linkingTimeRealizationTechniqueEClass = createEClass(LINKING_TIME_REALIZATION_TECHNIQUE);

		runtimeRealizationTechniqueEClass = createEClass(RUNTIME_REALIZATION_TECHNIQUE);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		designTimeRealizationTechniqueEClass.getESuperTypes().add(this.getRealizationTechnique());
		compilationTimeRealizationTechniqueEClass.getESuperTypes().add(this.getRealizationTechnique());
		linkingTimeRealizationTechniqueEClass.getESuperTypes().add(this.getRealizationTechnique());
		runtimeRealizationTechniqueEClass.getESuperTypes().add(this.getRealizationTechnique());

		// Initialize classes and features; add operations and parameters
		initEClass(realizationTechniqueEClass, RealizationTechnique.class, "RealizationTechnique", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(designTimeRealizationTechniqueEClass, DesignTimeRealizationTechnique.class, "DesignTimeRealizationTechnique", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(compilationTimeRealizationTechniqueEClass, CompilationTimeRealizationTechnique.class, "CompilationTimeRealizationTechnique", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(linkingTimeRealizationTechniqueEClass, LinkingTimeRealizationTechnique.class, "LinkingTimeRealizationTechnique", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(runtimeRealizationTechniqueEClass, RuntimeRealizationTechnique.class, "RuntimeRealizationTechnique", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //realizationPackageImpl
