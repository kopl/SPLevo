/**
 */
package org.splevo.diffing.java.modisco.java2kdmdiff;

import org.eclipse.gmt.modisco.java.EnumDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enum Declaration Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.java.modisco.java2kdmdiff.EnumDeclarationChange#getEnumLeft <em>Enum Left</em>}</li>
 *   <li>{@link org.splevo.diffing.java.modisco.java2kdmdiff.EnumDeclarationChange#getEnumRight <em>Enum Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage#getEnumDeclarationChange()
 * @model
 * @generated
 */
public interface EnumDeclarationChange extends EnumChange {
	/**
	 * Returns the value of the '<em><b>Enum Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The new version of  the enumeration.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Enum Left</em>' reference.
	 * @see #setEnumLeft(EnumDeclaration)
	 * @see org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage#getEnumDeclarationChange_EnumLeft()
	 * @model
	 * @generated
	 */
	EnumDeclaration getEnumLeft();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.java.modisco.java2kdmdiff.EnumDeclarationChange#getEnumLeft <em>Enum Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enum Left</em>' reference.
	 * @see #getEnumLeft()
	 * @generated
	 */
	void setEnumLeft(EnumDeclaration value);

	/**
	 * Returns the value of the '<em><b>Enum Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The original version of  the enumeration
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Enum Right</em>' reference.
	 * @see #setEnumRight(EnumDeclaration)
	 * @see org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage#getEnumDeclarationChange_EnumRight()
	 * @model
	 * @generated
	 */
	EnumDeclaration getEnumRight();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.java.modisco.java2kdmdiff.EnumDeclarationChange#getEnumRight <em>Enum Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enum Right</em>' reference.
	 * @see #getEnumRight()
	 * @generated
	 */
	void setEnumRight(EnumDeclaration value);

} // EnumDeclarationChange
