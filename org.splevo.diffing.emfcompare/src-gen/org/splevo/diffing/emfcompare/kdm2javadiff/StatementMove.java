/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff;

import org.eclipse.gmt.modisco.java.ASTNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Statement Move</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Identifies a statement that has been moved into a different parent element.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove#getParentLeft <em>Parent Left</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove#getParentRight <em>Parent Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getStatementMove()
 * @model
 * @generated
 */
public interface StatementMove extends StatementChange {
	/**
	 * Returns the value of the '<em><b>Parent Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The parent in the left program tree
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parent Left</em>' reference.
	 * @see #setParentLeft(ASTNode)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getStatementMove_ParentLeft()
	 * @model required="true"
	 * @generated
	 */
	ASTNode getParentLeft();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove#getParentLeft <em>Parent Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Left</em>' reference.
	 * @see #getParentLeft()
	 * @generated
	 */
	void setParentLeft(ASTNode value);

	/**
	 * Returns the value of the '<em><b>Parent Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The new parent element in the right program tree
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parent Right</em>' reference.
	 * @see #setParentRight(ASTNode)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getStatementMove_ParentRight()
	 * @model required="true"
	 * @generated
	 */
	ASTNode getParentRight();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove#getParentRight <em>Parent Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Right</em>' reference.
	 * @see #getParentRight()
	 * @generated
	 */
	void setParentRight(ASTNode value);

} // StatementMove
