/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff;

import org.eclipse.gmt.modisco.java.ImportDeclaration;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Import Insert</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A change representing a new import which is only present in the left model. 
 * Accordingly the importRight reference will always be null.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportInsert#getImportRight <em>Import Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getImportInsert()
 * @model
 * @generated
 */
public interface ImportInsert extends ImportDeclarationChange {

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
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getImportInsert_ImportRight()
	 * @model
	 * @generated
	 */
	ImportDeclaration getImportRight();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportInsert#getImportRight <em>Import Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Import Right</em>' reference.
	 * @see #getImportRight()
	 * @generated
	 */
	void setImportRight(ImportDeclaration value);
} // ImportInsert
