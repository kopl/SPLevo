/**
 */
package org.splevo.vpm.software;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.splevo.vpm.software.SoftwarePackage
 * @generated
 */
public interface SoftwareFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    SoftwareFactory eINSTANCE = org.splevo.vpm.software.impl.SoftwareFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Source Location</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Source Location</em>'.
     * @generated
     */
    SourceLocation createSourceLocation();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    SoftwarePackage getSoftwarePackage();

} //SoftwareFactory
