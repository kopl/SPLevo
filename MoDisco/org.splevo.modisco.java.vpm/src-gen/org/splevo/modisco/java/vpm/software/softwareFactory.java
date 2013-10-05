/**
 */
package org.splevo.modisco.java.vpm.software;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.splevo.modisco.java.vpm.software.softwarePackage
 * @generated
 */
public interface softwareFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    softwareFactory eINSTANCE = org.splevo.modisco.java.vpm.software.impl.softwareFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Mo Disco Java Software Element</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Mo Disco Java Software Element</em>'.
     * @generated
     */
    MoDiscoJavaSoftwareElement createMoDiscoJavaSoftwareElement();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    softwarePackage getsoftwarePackage();

} //softwareFactory
