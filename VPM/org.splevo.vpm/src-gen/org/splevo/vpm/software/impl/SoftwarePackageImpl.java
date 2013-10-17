/**
 */
package org.splevo.vpm.software.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.featuremodel.FeatureModelPackage;

import org.eclipse.modisco.java.composition.javaapplication.JavaapplicationPackage;

import org.splevo.vpm.software.JavaSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.software.SoftwareFactory;
import org.splevo.vpm.software.SoftwarePackage;

import org.splevo.vpm.variability.impl.variabilityPackageImpl;

import org.splevo.vpm.variability.variabilityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SoftwarePackageImpl extends EPackageImpl implements SoftwarePackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass softwareElementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass javaSoftwareElementEClass = null;

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
     * @see org.splevo.vpm.software.SoftwarePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private SoftwarePackageImpl() {
        super(eNS_URI, SoftwareFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link SoftwarePackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static SoftwarePackage init() {
        if (isInited)
            return (SoftwarePackage) EPackage.Registry.INSTANCE.getEPackage(SoftwarePackage.eNS_URI);

        // Obtain or create and register package
        SoftwarePackageImpl theSoftwarePackage = (SoftwarePackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SoftwarePackageImpl ? EPackage.Registry.INSTANCE
                .get(eNS_URI) : new SoftwarePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        FeatureModelPackage.eINSTANCE.eClass();
        JavaapplicationPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        variabilityPackageImpl thevariabilityPackage = (variabilityPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(variabilityPackage.eNS_URI) instanceof variabilityPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(variabilityPackage.eNS_URI) : variabilityPackage.eINSTANCE);

        // Create package meta-data objects
        theSoftwarePackage.createPackageContents();
        thevariabilityPackage.createPackageContents();

        // Initialize created meta-data
        theSoftwarePackage.initializePackageContents();
        thevariabilityPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theSoftwarePackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(SoftwarePackage.eNS_URI, theSoftwarePackage);
        return theSoftwarePackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSoftwareElement() {
        return softwareElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getJavaSoftwareElement() {
        return javaSoftwareElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SoftwareFactory getSoftwareFactory() {
        return (SoftwareFactory) getEFactoryInstance();
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
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        softwareElementEClass = createEClass(SOFTWARE_ELEMENT);

        javaSoftwareElementEClass = createEClass(JAVA_SOFTWARE_ELEMENT);
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
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        javaSoftwareElementEClass.getESuperTypes().add(this.getSoftwareElement());

        // Initialize classes and features; add operations and parameters
        initEClass(softwareElementEClass, SoftwareElement.class, "SoftwareElement", IS_ABSTRACT, IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);

        addEOperation(softwareElementEClass, ecorePackage.getEString(), "getLabel", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(softwareElementEClass, ecorePackage.getEString(), "getName", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(softwareElementEClass, ecorePackage.getEJavaObject(), "getValue", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(javaSoftwareElementEClass, JavaSoftwareElement.class, "JavaSoftwareElement", IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);
    }

} //SoftwarePackageImpl
