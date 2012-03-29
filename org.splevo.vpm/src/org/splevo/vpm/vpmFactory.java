/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.vpm;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.splevo.vpm.vpmPackage
 * @generated
 */
public interface vpmFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	vpmFactory eINSTANCE = org.splevo.vpm.impl.vpmFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Variation Point Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Variation Point Model</em>'.
	 * @generated
	 */
	VariationPointModel createVariationPointModel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	vpmPackage getvpmPackage();

} //vpmFactory
