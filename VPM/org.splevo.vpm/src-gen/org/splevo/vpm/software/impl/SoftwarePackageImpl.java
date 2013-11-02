package org.splevo.vpm.software.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.featuremodel.FeatureModelPackage;
import org.splevo.vpm.software.JavaSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.software.SoftwareFactory;
import org.splevo.vpm.software.SoftwarePackage;
import org.splevo.vpm.software.SourceLocation;
import org.splevo.vpm.variability.variabilityPackage;
import org.splevo.vpm.variability.impl.variabilityPackageImpl;

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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass sourceLocationEClass = null;

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
    public EClass getSourceLocation() {
        return sourceLocationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSourceLocation_FilePath() {
        return (EAttribute) sourceLocationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSourceLocation_StartLine() {
        return (EAttribute) sourceLocationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSourceLocation_StartPosition() {
        return (EAttribute) sourceLocationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSourceLocation_EndLine() {
        return (EAttribute) sourceLocationEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSourceLocation_EndPosition() {
        return (EAttribute) sourceLocationEClass.getEStructuralFeatures().get(4);
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

        sourceLocationEClass = createEClass(SOURCE_LOCATION);
        createEAttribute(sourceLocationEClass, SOURCE_LOCATION__FILE_PATH);
        createEAttribute(sourceLocationEClass, SOURCE_LOCATION__START_LINE);
        createEAttribute(sourceLocationEClass, SOURCE_LOCATION__START_POSITION);
        createEAttribute(sourceLocationEClass, SOURCE_LOCATION__END_LINE);
        createEAttribute(sourceLocationEClass, SOURCE_LOCATION__END_POSITION);
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

        // Obtain other dependent packages
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        javaSoftwareElementEClass.getESuperTypes().add(this.getSoftwareElement());

        // Initialize classes and features; add operations and parameters
        initEClass(softwareElementEClass, SoftwareElement.class, "SoftwareElement", IS_ABSTRACT, IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);

        addEOperation(softwareElementEClass, ecorePackage.getEString(), "getLabel", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(softwareElementEClass, ecorePackage.getEString(), "getName", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(softwareElementEClass, this.getSourceLocation(), "getSourceLocation", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(javaSoftwareElementEClass, JavaSoftwareElement.class, "JavaSoftwareElement", IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(sourceLocationEClass, SourceLocation.class, "SourceLocation", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSourceLocation_FilePath(), theEcorePackage.getEString(), "filePath", null, 0, 1,
                SourceLocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSourceLocation_StartLine(), theEcorePackage.getEInt(), "startLine", null, 0, 1,
                SourceLocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSourceLocation_StartPosition(), theEcorePackage.getEInt(), "startPosition", null, 0, 1,
                SourceLocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSourceLocation_EndLine(), theEcorePackage.getEInt(), "endLine", null, 0, 1,
                SourceLocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSourceLocation_EndPosition(), theEcorePackage.getEInt(), "endPosition", null, 0, 1,
                SourceLocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //SoftwarePackageImpl
