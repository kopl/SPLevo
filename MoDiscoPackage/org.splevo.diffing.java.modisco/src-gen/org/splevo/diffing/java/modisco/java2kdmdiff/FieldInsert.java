/**
 */
package org.splevo.diffing.java.modisco.java2kdmdiff;

import org.eclipse.gmt.modisco.java.FieldDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field Insert</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.java.modisco.java2kdmdiff.FieldInsert#getFieldLeft <em>Field Left</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage#getFieldInsert()
 * @model
 * @generated
 */
public interface FieldInsert extends FieldChange {
	/**
	 * Returns the value of the '<em><b>Field Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The new field declaration that has been added.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Field Left</em>' reference.
	 * @see #setFieldLeft(FieldDeclaration)
	 * @see org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage#getFieldInsert_FieldLeft()
	 * @model
	 * @generated
	 */
	FieldDeclaration getFieldLeft();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.java.modisco.java2kdmdiff.FieldInsert#getFieldLeft <em>Field Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Left</em>' reference.
	 * @see #getFieldLeft()
	 * @generated
	 */
	void setFieldLeft(FieldDeclaration value);

} // FieldInsert
