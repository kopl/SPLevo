/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff;

import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Delete</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.MethodDelete#getMethodRight <em>Method Right</em>}</li>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.MethodDelete#getLeftContainer <em>Left Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getMethodDelete()
 * @model
 * @generated
 */
public interface MethodDelete extends MethodChange {
	/**
	 * Returns the value of the '<em><b>Method Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Right</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Right</em>' reference.
	 * @see #setMethodRight(AbstractMethodDeclaration)
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getMethodDelete_MethodRight()
	 * @model
	 * @generated
	 */
	AbstractMethodDeclaration getMethodRight();

	/**
	 * Sets the value of the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.MethodDelete#getMethodRight <em>Method Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Right</em>' reference.
	 * @see #getMethodRight()
	 * @generated
	 */
	void setMethodRight(AbstractMethodDeclaration value);

	/**
	 * Returns the value of the '<em><b>Left Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The container in the changed model that no longer contains the method. (according to emf compares "leftParent" terminology)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Left Container</em>' reference.
	 * @see #setLeftContainer(ASTNode)
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getMethodDelete_LeftContainer()
	 * @model
	 * @generated
	 */
	ASTNode getLeftContainer();

	/**
	 * Sets the value of the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.MethodDelete#getLeftContainer <em>Left Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Container</em>' reference.
	 * @see #getLeftContainer()
	 * @generated
	 */
	void setLeftContainer(ASTNode value);

} // MethodDelete
