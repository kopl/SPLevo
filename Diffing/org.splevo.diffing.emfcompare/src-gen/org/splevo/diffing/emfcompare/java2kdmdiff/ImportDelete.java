/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff;

import org.eclipse.gmt.modisco.java.ImportDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Import Delete</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An import that was deleted and is only present in the left model.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete#getImportRight <em>Import Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getImportDelete()
 * @model
 * @generated
 */
public interface ImportDelete extends ImportDeclarationChange {
	/**
     * Returns the value of the '<em><b>Import Right</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Import Right</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Import Right</em>' reference.
     * @see #setImportRight(ImportDeclaration)
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getImportDelete_ImportRight()
     * @model
     * @generated
     */
	ImportDeclaration getImportRight();

	/**
     * Sets the value of the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete#getImportRight <em>Import Right</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Import Right</em>' reference.
     * @see #getImportRight()
     * @generated
     */
	void setImportRight(ImportDeclaration value);

} // ImportDelete
