/**
 */
package org.splevo.diffing.java.modisco.java2kdmdiff;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Insert</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.java.modisco.java2kdmdiff.MethodInsert#getMethodLeft <em>Method Left</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage#getMethodInsert()
 * @model
 * @generated
 */
public interface MethodInsert extends MethodChange {
	/**
	 * Returns the value of the '<em><b>Method Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Left</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Left</em>' reference.
	 * @see #setMethodLeft(AbstractMethodDeclaration)
	 * @see org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage#getMethodInsert_MethodLeft()
	 * @model
	 * @generated
	 */
	AbstractMethodDeclaration getMethodLeft();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.java.modisco.java2kdmdiff.MethodInsert#getMethodLeft <em>Method Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Left</em>' reference.
	 * @see #getMethodLeft()
	 * @generated
	 */
	void setMethodLeft(AbstractMethodDeclaration value);

} // MethodInsert
