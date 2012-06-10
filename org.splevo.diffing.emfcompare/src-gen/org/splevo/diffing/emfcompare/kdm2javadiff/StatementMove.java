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
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove#getParentOld <em>Parent Old</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove#getParentNew <em>Parent New</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getStatementMove()
 * @model
 * @generated
 */
public interface StatementMove extends StatementChange {
	/**
	 * Returns the value of the '<em><b>Parent Old</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The old parent in the program tree (should be part of the left model)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parent Old</em>' reference.
	 * @see #setParentOld(ASTNode)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getStatementMove_ParentOld()
	 * @model required="true"
	 * @generated
	 */
	ASTNode getParentOld();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove#getParentOld <em>Parent Old</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Old</em>' reference.
	 * @see #getParentOld()
	 * @generated
	 */
	void setParentOld(ASTNode value);

	/**
	 * Returns the value of the '<em><b>Parent New</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The new parent element in the program tree (should be part of the right model)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parent New</em>' reference.
	 * @see #setParentNew(ASTNode)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getStatementMove_ParentNew()
	 * @model required="true"
	 * @generated
	 */
	ASTNode getParentNew();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove#getParentNew <em>Parent New</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent New</em>' reference.
	 * @see #getParentNew()
	 * @generated
	 */
	void setParentNew(ASTNode value);

} // StatementMove
