/**
 */
package org.splevo.jamopp.diffing.jamoppdiff;

import org.emftext.language.java.classifiers.Enumeration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enum Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Change that indications a modification of an enumeration type.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.EnumChange#getChangedEnum <em>Changed Enum</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getEnumChange()
 * @model
 * @generated
 */
public interface EnumChange extends JaMoPPDiff {
	/**
	 * Returns the value of the '<em><b>Changed Enum</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Changed Enum</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Changed Enum</em>' reference.
	 * @see #setChangedEnum(Enumeration)
	 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getEnumChange_ChangedEnum()
	 * @model required="true"
	 * @generated
	 */
	Enumeration getChangedEnum();

	/**
	 * Sets the value of the '{@link org.splevo.jamopp.diffing.jamoppdiff.EnumChange#getChangedEnum <em>Changed Enum</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Changed Enum</em>' reference.
	 * @see #getChangedEnum()
	 * @generated
	 */
	void setChangedEnum(Enumeration value);

} // EnumChange
