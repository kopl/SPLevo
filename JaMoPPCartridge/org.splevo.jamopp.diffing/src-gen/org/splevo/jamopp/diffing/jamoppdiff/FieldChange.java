/**
 */
package org.splevo.jamopp.diffing.jamoppdiff;

import org.emftext.language.java.members.Field;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.FieldChange#getChangedField <em>Changed Field</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getFieldChange()
 * @model
 * @generated
 */
public interface FieldChange extends JaMoPPDiff {
	/**
	 * Returns the value of the '<em><b>Changed Field</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Changed Field</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Changed Field</em>' reference.
	 * @see #setChangedField(Field)
	 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getFieldChange_ChangedField()
	 * @model required="true"
	 * @generated
	 */
	Field getChangedField();

	/**
	 * Sets the value of the '{@link org.splevo.jamopp.diffing.jamoppdiff.FieldChange#getChangedField <em>Changed Field</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Changed Field</em>' reference.
	 * @see #getChangedField()
	 * @generated
	 */
	void setChangedField(Field value);

} // FieldChange
