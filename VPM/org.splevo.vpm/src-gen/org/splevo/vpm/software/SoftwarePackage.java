/**
 */
package org.splevo.vpm.software;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.splevo.vpm.software.SoftwareFactory
 * @model kind="package"
 * @generated
 */
public interface SoftwarePackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "software";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://splevo.org/vpm/1.0/software";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "software";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    SoftwarePackage eINSTANCE = org.splevo.vpm.software.impl.SoftwarePackageImpl.init();

    /**
     * The meta object id for the '{@link org.splevo.vpm.software.SoftwareElement <em>Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.vpm.software.SoftwareElement
     * @see org.splevo.vpm.software.impl.SoftwarePackageImpl#getSoftwareElement()
     * @generated
     */
    int SOFTWARE_ELEMENT = 0;

    /**
     * The number of structural features of the '<em>Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOFTWARE_ELEMENT_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link org.splevo.vpm.software.impl.JavaSoftwareElementImpl <em>Java Software Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.vpm.software.impl.JavaSoftwareElementImpl
     * @see org.splevo.vpm.software.impl.SoftwarePackageImpl#getJavaSoftwareElement()
     * @generated
     */
    int JAVA_SOFTWARE_ELEMENT = 1;

    /**
     * The number of structural features of the '<em>Java Software Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JAVA_SOFTWARE_ELEMENT_FEATURE_COUNT = SOFTWARE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * Returns the meta object for class '{@link org.splevo.vpm.software.SoftwareElement <em>Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Element</em>'.
     * @see org.splevo.vpm.software.SoftwareElement
     * @generated
     */
    EClass getSoftwareElement();

    /**
     * Returns the meta object for class '{@link org.splevo.vpm.software.JavaSoftwareElement <em>Java Software Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Java Software Element</em>'.
     * @see org.splevo.vpm.software.JavaSoftwareElement
     * @generated
     */
    EClass getJavaSoftwareElement();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    SoftwareFactory getSoftwareFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link org.splevo.vpm.software.SoftwareElement <em>Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.vpm.software.SoftwareElement
         * @see org.splevo.vpm.software.impl.SoftwarePackageImpl#getSoftwareElement()
         * @generated
         */
        EClass SOFTWARE_ELEMENT = eINSTANCE.getSoftwareElement();

        /**
         * The meta object literal for the '{@link org.splevo.vpm.software.impl.JavaSoftwareElementImpl <em>Java Software Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.vpm.software.impl.JavaSoftwareElementImpl
         * @see org.splevo.vpm.software.impl.SoftwarePackageImpl#getJavaSoftwareElement()
         * @generated
         */
        EClass JAVA_SOFTWARE_ELEMENT = eINSTANCE.getJavaSoftwareElement();

    }

} //SoftwarePackage
