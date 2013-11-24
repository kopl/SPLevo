/**
 */
package org.splevo.jamopp.diffing.jamoppdiff;

import org.emftext.language.java.imports.Import;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Import Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A difference identifying that an import has been changed.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.ImportChange#getChangedImport <em>Changed Import</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getImportChange()
 * @model
 * @generated
 */
public interface ImportChange extends JaMoPPDiff {
	/**
	 * Returns the value of the '<em><b>Changed Import</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Changed Import</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Changed Import</em>' reference.
	 * @see #setChangedImport(Import)
	 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getImportChange_ChangedImport()
	 * @model required="true"
	 * @generated
	 */
	Import getChangedImport();

	/**
	 * Sets the value of the '{@link org.splevo.jamopp.diffing.jamoppdiff.ImportChange#getChangedImport <em>Changed Import</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Changed Import</em>' reference.
	 * @see #getChangedImport()
	 * @generated
	 */
	void setChangedImport(Import value);

} // ImportChange
