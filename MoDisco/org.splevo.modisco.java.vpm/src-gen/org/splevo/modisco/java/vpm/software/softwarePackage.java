/**
 */
package org.splevo.modisco.java.vpm.software;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.splevo.vpm.software.SoftwarePackage;

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
 * @see org.splevo.modisco.java.vpm.software.softwareFactory
 * @model kind="package"
 * @generated
 */
public interface softwarePackage extends EPackage {
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
    String eNS_URI = "http://splevo.org/modisco/java/vpm/1.0/software";

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
    softwarePackage eINSTANCE = org.splevo.modisco.java.vpm.software.impl.softwarePackageImpl.init();

    /**
     * The meta object id for the '{@link org.splevo.modisco.java.vpm.software.impl.MoDiscoJavaSoftwareElementImpl <em>Mo Disco Java Software Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.modisco.java.vpm.software.impl.MoDiscoJavaSoftwareElementImpl
     * @see org.splevo.modisco.java.vpm.software.impl.softwarePackageImpl#getMoDiscoJavaSoftwareElement()
     * @generated
     */
    int MO_DISCO_JAVA_SOFTWARE_ELEMENT = 0;

    /**
     * The feature id for the '<em><b>Ast Node</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MO_DISCO_JAVA_SOFTWARE_ELEMENT__AST_NODE = SoftwarePackage.JAVA_SOFTWARE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Java Application Model</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MO_DISCO_JAVA_SOFTWARE_ELEMENT__JAVA_APPLICATION_MODEL = SoftwarePackage.JAVA_SOFTWARE_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Mo Disco Java Software Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MO_DISCO_JAVA_SOFTWARE_ELEMENT_FEATURE_COUNT = SoftwarePackage.JAVA_SOFTWARE_ELEMENT_FEATURE_COUNT + 2;

    /**
     * Returns the meta object for class '{@link org.splevo.modisco.java.vpm.software.MoDiscoJavaSoftwareElement <em>Mo Disco Java Software Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Mo Disco Java Software Element</em>'.
     * @see org.splevo.modisco.java.vpm.software.MoDiscoJavaSoftwareElement
     * @generated
     */
    EClass getMoDiscoJavaSoftwareElement();

    /**
     * Returns the meta object for the reference '{@link org.splevo.modisco.java.vpm.software.MoDiscoJavaSoftwareElement#getAstNode <em>Ast Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Ast Node</em>'.
     * @see org.splevo.modisco.java.vpm.software.MoDiscoJavaSoftwareElement#getAstNode()
     * @see #getMoDiscoJavaSoftwareElement()
     * @generated
     */
    EReference getMoDiscoJavaSoftwareElement_AstNode();

    /**
     * Returns the meta object for the reference '{@link org.splevo.modisco.java.vpm.software.MoDiscoJavaSoftwareElement#getJavaApplicationModel <em>Java Application Model</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Java Application Model</em>'.
     * @see org.splevo.modisco.java.vpm.software.MoDiscoJavaSoftwareElement#getJavaApplicationModel()
     * @see #getMoDiscoJavaSoftwareElement()
     * @generated
     */
    EReference getMoDiscoJavaSoftwareElement_JavaApplicationModel();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    softwareFactory getsoftwareFactory();

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
         * The meta object literal for the '{@link org.splevo.modisco.java.vpm.software.impl.MoDiscoJavaSoftwareElementImpl <em>Mo Disco Java Software Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.modisco.java.vpm.software.impl.MoDiscoJavaSoftwareElementImpl
         * @see org.splevo.modisco.java.vpm.software.impl.softwarePackageImpl#getMoDiscoJavaSoftwareElement()
         * @generated
         */
        EClass MO_DISCO_JAVA_SOFTWARE_ELEMENT = eINSTANCE.getMoDiscoJavaSoftwareElement();

        /**
         * The meta object literal for the '<em><b>Ast Node</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MO_DISCO_JAVA_SOFTWARE_ELEMENT__AST_NODE = eINSTANCE.getMoDiscoJavaSoftwareElement_AstNode();

        /**
         * The meta object literal for the '<em><b>Java Application Model</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MO_DISCO_JAVA_SOFTWARE_ELEMENT__JAVA_APPLICATION_MODEL = eINSTANCE
                .getMoDiscoJavaSoftwareElement_JavaApplicationModel();

    }

} //softwarePackage
