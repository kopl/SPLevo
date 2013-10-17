/**
 */
package org.splevo.modisco.java.vpm.software.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.gmt.modisco.java.emf.JavaPackage;

import org.eclipse.modisco.java.composition.javaapplication.JavaapplicationPackage;
import org.splevo.modisco.java.vpm.software.MoDiscoJavaSoftwareElement;
import org.splevo.modisco.java.vpm.software.softwareFactory;
import org.splevo.modisco.java.vpm.software.softwarePackage;

import org.splevo.vpm.software.SoftwarePackage;

import org.splevo.vpm.variability.variabilityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class softwarePackageImpl extends EPackageImpl implements softwarePackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass moDiscoJavaSoftwareElementEClass = null;

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
     * @see org.splevo.modisco.java.vpm.software.softwarePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private softwarePackageImpl() {
        super(eNS_URI, softwareFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link softwarePackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static softwarePackage init() {
        if (isInited)
            return (softwarePackage) EPackage.Registry.INSTANCE.getEPackage(softwarePackage.eNS_URI);

        // Obtain or create and register package
        softwarePackageImpl thesoftwarePackage = (softwarePackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof softwarePackageImpl ? EPackage.Registry.INSTANCE
                .get(eNS_URI) : new softwarePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        variabilityPackage.eINSTANCE.eClass();
        SoftwarePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        thesoftwarePackage.createPackageContents();

        // Initialize created meta-data
        thesoftwarePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        thesoftwarePackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(softwarePackage.eNS_URI, thesoftwarePackage);
        return thesoftwarePackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMoDiscoJavaSoftwareElement() {
        return moDiscoJavaSoftwareElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMoDiscoJavaSoftwareElement_AstNode() {
        return (EReference) moDiscoJavaSoftwareElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMoDiscoJavaSoftwareElement_JavaApplicationModel() {
        return (EReference) moDiscoJavaSoftwareElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public softwareFactory getsoftwareFactory() {
        return (softwareFactory) getEFactoryInstance();
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
        moDiscoJavaSoftwareElementEClass = createEClass(MO_DISCO_JAVA_SOFTWARE_ELEMENT);
        createEReference(moDiscoJavaSoftwareElementEClass, MO_DISCO_JAVA_SOFTWARE_ELEMENT__AST_NODE);
        createEReference(moDiscoJavaSoftwareElementEClass, MO_DISCO_JAVA_SOFTWARE_ELEMENT__JAVA_APPLICATION_MODEL);
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
        SoftwarePackage theSoftwarePackage = (SoftwarePackage) EPackage.Registry.INSTANCE
                .getEPackage(SoftwarePackage.eNS_URI);
        JavaPackage theJavaPackage = (JavaPackage) EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI);
        JavaapplicationPackage theJavaapplicationPackage = (JavaapplicationPackage) EPackage.Registry.INSTANCE
                .getEPackage(JavaapplicationPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        moDiscoJavaSoftwareElementEClass.getESuperTypes().add(theSoftwarePackage.getJavaSoftwareElement());

        // Initialize classes and features; add operations and parameters
        initEClass(moDiscoJavaSoftwareElementEClass, MoDiscoJavaSoftwareElement.class, "MoDiscoJavaSoftwareElement",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMoDiscoJavaSoftwareElement_AstNode(), theJavaPackage.getASTNode(), null, "astNode", null, 1,
                1, MoDiscoJavaSoftwareElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMoDiscoJavaSoftwareElement_JavaApplicationModel(),
                theJavaapplicationPackage.getJavaApplication(), null, "javaApplicationModel", null, 0, 1,
                MoDiscoJavaSoftwareElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //softwarePackageImpl
