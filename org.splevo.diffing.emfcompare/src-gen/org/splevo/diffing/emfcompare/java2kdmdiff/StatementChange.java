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
 *   <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange#getStatementRight <em>Statement Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getStatementChange()
 * @model
 * @generated
 */
public interface StatementChange extends DiffGroup {
	/**
	 * Returns the value of the '<em><b>Statement Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The statement in the right model which is the new modified version.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Statement Right</em>' reference.
	 * @see #setStatementRight(Statement)
	 * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getStatementChange_StatementRight()
	 * @model extendedMetaData="name='statementRight' namespace=''"
	 * @generated
	 */
	Statement getStatementRight();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange#getStatementRight <em>Statement Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Statement Right</em>' reference.
	 * @see #getStatementRight()
	 * @generated
	 */
	void setStatementRight(Statement value);

} // StatementChange
