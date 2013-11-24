/**
 */
package org.splevo.jamopp.diffing.jamoppdiff;

import org.emftext.language.java.classifiers.Interface;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interface Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A changed interface declaration.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.InterfaceChange#getChangedInterface <em>Changed Interface</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getInterfaceChange()
 * @model
 * @generated
 */
public interface InterfaceChange extends JaMoPPDiff {
	/**
	 * Returns the value of the '<em><b>Changed Interface</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Changed Interface</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Changed Interface</em>' reference.
	 * @see #setChangedInterface(Interface)
	 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getInterfaceChange_ChangedInterface()
	 * @model required="true"
	 * @generated
	 */
	Interface getChangedInterface();

	/**
	 * Sets the value of the '{@link org.splevo.jamopp.diffing.jamoppdiff.InterfaceChange#getChangedInterface <em>Changed Interface</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Changed Interface</em>' reference.
	 * @see #getChangedInterface()
	 * @generated
	 */
	void setChangedInterface(Interface value);

} // InterfaceChange
