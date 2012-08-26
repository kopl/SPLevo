/**
 */
package org.splevo.vpm.refinement;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.splevo.vpm.refinement.RefinementPackage
 * @generated
 */
public interface RefinementFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RefinementFactory eINSTANCE = org.splevo.vpm.refinement.impl.RefinementFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Merge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Merge</em>'.
	 * @generated
	 */
	Merge createMerge();

	/**
	 * Returns a new object of class '<em>Grouping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Grouping</em>'.
	 * @generated
	 */
	Grouping createGrouping();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	RefinementPackage getRefinementPackage();

} //RefinementFactory
