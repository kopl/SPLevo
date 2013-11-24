/**
 */
package org.splevo.jamopp.diffing.jamoppdiff;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A changed class declaration.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.ClassChange#getChangedClass <em>Changed Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getClassChange()
 * @model
 * @generated
 */
public interface ClassChange extends JaMoPPDiff {
	/**
	 * Returns the value of the '<em><b>Changed Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Changed Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Changed Class</em>' reference.
	 * @see #setChangedClass(org.emftext.language.java.classifiers.Class)
	 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getClassChange_ChangedClass()
	 * @model required="true"
	 * @generated
	 */
	org.emftext.language.java.classifiers.Class getChangedClass();

	/**
	 * Sets the value of the '{@link org.splevo.jamopp.diffing.jamoppdiff.ClassChange#getChangedClass <em>Changed Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Changed Class</em>' reference.
	 * @see #getChangedClass()
	 * @generated
	 */
	void setChangedClass(org.emftext.language.java.classifiers.Class value);

} // ClassChange
