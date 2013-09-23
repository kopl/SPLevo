/**
 */
package org.splevo.diffing.java.modisco.java2kdmdiff;

import org.eclipse.gmt.modisco.java.ClassDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Delete</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.java.modisco.java2kdmdiff.ClassDelete#getClassRight <em>Class Right</em>}</li>
 *   <li>{@link org.splevo.diffing.java.modisco.java2kdmdiff.ClassDelete#getLeftContainer <em>Left Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage#getClassDelete()
 * @model
 * @generated
 */
public interface ClassDelete extends ClassChange {
	/**
	 * Returns the value of the '<em><b>Class Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The deleted class declaration.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Class Right</em>' reference.
	 * @see #setClassRight(ClassDeclaration)
	 * @see org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage#getClassDelete_ClassRight()
	 * @model required="true"
	 * @generated
	 */
	ClassDeclaration getClassRight();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.java.modisco.java2kdmdiff.ClassDelete#getClassRight <em>Class Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Right</em>' reference.
	 * @see #getClassRight()
	 * @generated
	 */
	void setClassRight(ClassDeclaration value);

	/**
	 * Returns the value of the '<em><b>Left Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The package in the current model that no longer contains the deleted class.
	 * It might be null if the deleted class is conainted in a package that was deleted, too.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Left Container</em>' reference.
	 * @see #setLeftContainer(org.eclipse.gmt.modisco.java.Package)
	 * @see org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage#getClassDelete_LeftContainer()
	 * @model
	 * @generated
	 */
	org.eclipse.gmt.modisco.java.Package getLeftContainer();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.java.modisco.java2kdmdiff.ClassDelete#getLeftContainer <em>Left Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Container</em>' reference.
	 * @see #getLeftContainer()
	 * @generated
	 */
	void setLeftContainer(org.eclipse.gmt.modisco.java.Package value);

} // ClassDelete
