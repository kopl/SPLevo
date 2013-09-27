/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff;

import org.eclipse.gmt.modisco.java.ASTNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Statement Delete</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.StatementDelete#getLeftContainer <em>Left Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getStatementDelete()
 * @model
 * @generated
 */
public interface StatementDelete extends StatementChange {
	/**
	 * Returns the value of the '<em><b>Left Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The container in the changed model that no longer contains the statement. (according to emf compares "leftParent" terminology)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Left Container</em>' reference.
	 * @see #setLeftContainer(ASTNode)
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getStatementDelete_LeftContainer()
	 * @model
	 * @generated
	 */
	ASTNode getLeftContainer();

	/**
	 * Sets the value of the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.StatementDelete#getLeftContainer <em>Left Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Container</em>' reference.
	 * @see #getLeftContainer()
	 * @generated
	 */
	void setLeftContainer(ASTNode value);

} // StatementDelete
