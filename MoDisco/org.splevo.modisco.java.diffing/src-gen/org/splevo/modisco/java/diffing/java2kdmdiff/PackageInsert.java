/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Insert</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A package that was inserted and is only present in the left model.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.PackageInsert#getPackageLeft <em>Package Left</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getPackageInsert()
 * @model
 * @generated
 */
public interface PackageInsert extends PackageChange {
	/**
	 * Returns the value of the '<em><b>Package Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The package inserted to the software.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Package Left</em>' reference.
	 * @see #setPackageLeft(org.eclipse.gmt.modisco.java.Package)
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getPackageInsert_PackageLeft()
	 * @model
	 * @generated
	 */
	org.eclipse.gmt.modisco.java.Package getPackageLeft();

	/**
	 * Sets the value of the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.PackageInsert#getPackageLeft <em>Package Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Left</em>' reference.
	 * @see #getPackageLeft()
	 * @generated
	 */
	void setPackageLeft(org.eclipse.gmt.modisco.java.Package value);

} // PackageInsert
