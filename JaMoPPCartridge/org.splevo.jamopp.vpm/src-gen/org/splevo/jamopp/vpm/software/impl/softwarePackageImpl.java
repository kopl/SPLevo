/**
 */
package org.splevo.jamopp.vpm.software.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.emftext.language.java.annotations.AnnotationsPackage;

import org.emftext.language.java.arrays.ArraysPackage;

import org.emftext.language.java.classifiers.ClassifiersPackage;

import org.emftext.language.java.commons.CommonsPackage;

import org.emftext.language.java.containers.ContainersPackage;

import org.emftext.language.java.expressions.ExpressionsPackage;

import org.emftext.language.java.generics.GenericsPackage;

import org.emftext.language.java.imports.ImportsPackage;

import org.emftext.language.java.instantiations.InstantiationsPackage;

import org.emftext.language.java.literals.LiteralsPackage;

import org.emftext.language.java.members.MembersPackage;

import org.emftext.language.java.modifiers.ModifiersPackage;

import org.emftext.language.java.operators.OperatorsPackage;

import org.emftext.language.java.parameters.ParametersPackage;

import org.emftext.language.java.references.ReferencesPackage;

import org.emftext.language.java.statements.StatementsPackage;

import org.emftext.language.java.types.TypesPackage;

import org.emftext.language.java.variables.VariablesPackage;

import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;
import org.splevo.jamopp.vpm.software.softwarePackage;

import org.splevo.vpm.software.SoftwarePackage;

import org.splevo.vpm.variability.variabilityPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class softwarePackageImpl extends EPackageImpl implements
		softwarePackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass jaMoPPSoftwareElementEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
	 * package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory
	 * method {@link #init init()}, which also performs initialization of the
	 * package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.splevo.jamopp.vpm.software.softwarePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private softwarePackageImpl() {
		super(eNS_URI, softwareFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model,
	 * and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link softwarePackage#eINSTANCE} when
	 * that field is accessed. Clients should not invoke it directly. Instead,
	 * they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static softwarePackage init() {
		if (isInited)
			return (softwarePackage) EPackage.Registry.INSTANCE
					.getEPackage(softwarePackage.eNS_URI);

		// Obtain or create and register package
		softwarePackageImpl thesoftwarePackage = (softwarePackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof softwarePackageImpl ? EPackage.Registry.INSTANCE
				.get(eNS_URI) : new softwarePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		AnnotationsPackage.eINSTANCE.eClass();
		ArraysPackage.eINSTANCE.eClass();
		ClassifiersPackage.eINSTANCE.eClass();
		CommonsPackage.eINSTANCE.eClass();
		ContainersPackage.eINSTANCE.eClass();
		ExpressionsPackage.eINSTANCE.eClass();
		GenericsPackage.eINSTANCE.eClass();
		ImportsPackage.eINSTANCE.eClass();
		InstantiationsPackage.eINSTANCE.eClass();
		LiteralsPackage.eINSTANCE.eClass();
		MembersPackage.eINSTANCE.eClass();
		ModifiersPackage.eINSTANCE.eClass();
		OperatorsPackage.eINSTANCE.eClass();
		ParametersPackage.eINSTANCE.eClass();
		ReferencesPackage.eINSTANCE.eClass();
		StatementsPackage.eINSTANCE.eClass();
		TypesPackage.eINSTANCE.eClass();
		VariablesPackage.eINSTANCE.eClass();
		variabilityPackage.eINSTANCE.eClass();
		SoftwarePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		thesoftwarePackage.createPackageContents();

		// Initialize created meta-data
		thesoftwarePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thesoftwarePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(softwarePackage.eNS_URI,
				thesoftwarePackage);
		return thesoftwarePackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getJaMoPPSoftwareElement() {
		return jaMoPPSoftwareElementEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getJaMoPPSoftwareElement_JamoppElement() {
		return (EReference) jaMoPPSoftwareElementEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public softwareFactory getsoftwareFactory() {
		return (softwareFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is guarded to
	 * have no affect on any invocation but its first. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		jaMoPPSoftwareElementEClass = createEClass(JA_MO_PP_SOFTWARE_ELEMENT);
		createEReference(jaMoPPSoftwareElementEClass,
				JA_MO_PP_SOFTWARE_ELEMENT__JAMOPP_ELEMENT);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This
	 * method is guarded to have no affect on any invocation but its first. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
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
		SoftwarePackage theSoftwarePackage = (SoftwarePackage) EPackage.Registry.INSTANCE
				.getEPackage(SoftwarePackage.eNS_URI);
		CommonsPackage theCommonsPackage = (CommonsPackage) EPackage.Registry.INSTANCE
				.getEPackage(CommonsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		jaMoPPSoftwareElementEClass.getESuperTypes().add(
				theSoftwarePackage.getJavaSoftwareElement());

		// Initialize classes, features, and operations; add parameters
		initEClass(jaMoPPSoftwareElementEClass, JaMoPPSoftwareElement.class,
				"JaMoPPSoftwareElement", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getJaMoPPSoftwareElement_JamoppElement(),
				theCommonsPackage.getCommentable(), null, "jamoppElement",
				null, 1, 1, JaMoPPSoftwareElement.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} // softwarePackageImpl
