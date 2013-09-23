/**
 */
package org.splevo.diffing.java.modisco.java2kdmdiff;

import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.FieldDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field Delete</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.java.modisco.java2kdmdiff.FieldDelete#getFieldRight <em>Field Right</em>}</li>
 *   <li>{@link org.splevo.diffing.java.modisco.java2kdmdiff.FieldDelete#getLeftContainer <em>Left Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage#getFieldDelete()
 * @model
 * @generated
 */
public interface FieldDelete extends FieldChange {
	/**
	 * Returns the value of the '<em><b>Field Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The original field declaration that has been removed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Field Right</em>' reference.
	 * @see #setFieldRight(FieldDeclaration)
	 * @see org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage#getFieldDelete_FieldRight()
	 * @model
	 * @generated
	 */
	FieldDeclaration getFieldRight();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.java.modisco.java2kdmdiff.FieldDelete#getFieldRight <em>Field Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Right</em>' reference.
	 * @see #getFieldRight()
	 * @generated
	 */
	void setFieldRight(FieldDeclaration value);

	/**
	 * Returns the value of the '<em><b>Left Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The container of the deleted element in the old model.
	 * This can be either an AbstractTypeDeclaration or an AnonymousClassDeclaration. 
	 * Their most specific common super class is the ASTNode, so this is referenced here.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Left Container</em>' reference.
	 * @see #setLeftContainer(ASTNode)
	 * @see org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage#getFieldDelete_LeftContainer()
	 * @model
	 * @generated
	 */
	ASTNode getLeftContainer();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.java.modisco.java2kdmdiff.FieldDelete#getLeftContainer <em>Left Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Container</em>' reference.
	 * @see #getLeftContainer()
	 * @generated
	 */
	void setLeftContainer(ASTNode value);

} // FieldDelete
