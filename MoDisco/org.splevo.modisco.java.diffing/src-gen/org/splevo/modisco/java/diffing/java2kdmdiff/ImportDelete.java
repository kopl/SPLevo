/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff;

import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.ImportDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Import Delete</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An import that was deleted and is only present in the right model.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.ImportDelete#getImportRight <em>Import Right</em>}</li>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.ImportDelete#getLeftContainer <em>Left Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getImportDelete()
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
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getImportDelete_ImportRight()
	 * @model
	 * @generated
	 */
	ImportDeclaration getImportRight();

	/**
	 * Sets the value of the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.ImportDelete#getImportRight <em>Import Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Import Right</em>' reference.
	 * @see #getImportRight()
	 * @generated
	 */
	void setImportRight(ImportDeclaration value);

	/**
	 * Returns the value of the '<em><b>Left Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The container in the changed model that no longer contains the import. (according to emf compares "leftParent" terminology)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Left Container</em>' reference.
	 * @see #setLeftContainer(CompilationUnit)
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getImportDelete_LeftContainer()
	 * @model
	 * @generated
	 */
	CompilationUnit getLeftContainer();

	/**
	 * Sets the value of the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.ImportDelete#getLeftContainer <em>Left Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Container</em>' reference.
	 * @see #getLeftContainer()
	 * @generated
	 */
	void setLeftContainer(CompilationUnit value);

} // ImportDelete
