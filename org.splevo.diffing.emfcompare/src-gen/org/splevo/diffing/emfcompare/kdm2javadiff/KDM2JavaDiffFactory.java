/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage
 * @generated
 */
public interface KDM2JavaDiffFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	KDM2JavaDiffFactory eINSTANCE = org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Statement Order Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Statement Order Change</em>'.
	 * @generated
	 */
	StatementOrderChange createStatementOrderChange();

	/**
	 * Returns a new object of class '<em>Statement Insert</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Statement Insert</em>'.
	 * @generated
	 */
	StatementInsert createStatementInsert();

	/**
	 * Returns a new object of class '<em>Statement Delete</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Statement Delete</em>'.
	 * @generated
	 */
	StatementDelete createStatementDelete();

	/**
	 * Returns a new object of class '<em>Statement Move</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Statement Move</em>'.
	 * @generated
	 */
	StatementMove createStatementMove();

	/**
	 * Returns a new object of class '<em>Class Insert</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class Insert</em>'.
	 * @generated
	 */
	ClassInsert createClassInsert();

	/**
	 * Returns a new object of class '<em>Class Delete</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class Delete</em>'.
	 * @generated
	 */
	ClassDelete createClassDelete();

	/**
	 * Returns a new object of class '<em>Class Modifier Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class Modifier Change</em>'.
	 * @generated
	 */
	ClassModifierChange createClassModifierChange();

	/**
	 * Returns a new object of class '<em>Import Insert</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Import Insert</em>'.
	 * @generated
	 */
	ImportInsert createImportInsert();

	/**
	 * Returns a new object of class '<em>Import Delete</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Import Delete</em>'.
	 * @generated
	 */
	ImportDelete createImportDelete();

	/**
	 * Returns a new object of class '<em>Class Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class Change</em>'.
	 * @generated
	 */
	ClassChange createClassChange();

	/**
	 * Returns a new object of class '<em>Method Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Change</em>'.
	 * @generated
	 */
	MethodChange createMethodChange();

	/**
	 * Returns a new object of class '<em>Method Modifier Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Modifier Change</em>'.
	 * @generated
	 */
	MethodModifierChange createMethodModifierChange();

	/**
	 * Returns a new object of class '<em>Return Type Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Return Type Change</em>'.
	 * @generated
	 */
	ReturnTypeChange createReturnTypeChange();

	/**
	 * Returns a new object of class '<em>Method Parameter Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Parameter Change</em>'.
	 * @generated
	 */
	MethodParameterChange createMethodParameterChange();

	/**
	 * Returns a new object of class '<em>Method Insert</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Insert</em>'.
	 * @generated
	 */
	MethodInsert createMethodInsert();

	/**
	 * Returns a new object of class '<em>Method Delete</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Delete</em>'.
	 * @generated
	 */
	MethodDelete createMethodDelete();

	/**
	 * Returns a new object of class '<em>Compilation Unit Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Compilation Unit Change</em>'.
	 * @generated
	 */
	CompilationUnitChange createCompilationUnitChange();

	/**
	 * Returns a new object of class '<em>Package Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Package Change</em>'.
	 * @generated
	 */
	PackageChange createPackageChange();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	KDM2JavaDiffPackage getKDM2JavaDiffPackage();

} //KDM2JavaDiffFactory
