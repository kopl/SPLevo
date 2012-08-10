/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff;

import org.eclipse.emf.compare.diff.metamodel.DiffGroup;

import org.eclipse.gmt.modisco.java.Statement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Statement Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A change on a statement level.
 * Statements include simple statements as well as structural statements such as loops and conditions. Check the KDM/Java GAST model for details.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange#getStatementLeft <em>Statement Left</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getStatementChange()
 * @model
 * @generated
 */
public interface StatementChange extends DiffGroup {
	/**
	 * Returns the value of the '<em><b>Statement Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The statement in the left model which is the new modified version.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Statement Left</em>' reference.
	 * @see #setStatementLeft(Statement)
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getStatementChange_StatementLeft()
	 * @model
	 * @generated
	 */
	Statement getStatementLeft();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange#getStatementLeft <em>Statement Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Statement Left</em>' reference.
	 * @see #getStatementLeft()
	 * @generated
	 */
	void setStatementLeft(Statement value);

} // StatementChange
