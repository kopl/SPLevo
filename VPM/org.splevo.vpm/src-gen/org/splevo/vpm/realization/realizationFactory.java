/**
 */
package org.splevo.vpm.realization;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.splevo.vpm.realization.realizationPackage
 * @generated
 */
public interface realizationFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	realizationFactory eINSTANCE = org.splevo.vpm.realization.impl.realizationFactoryImpl.init();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	realizationPackage getrealizationPackage();

} //realizationFactory
