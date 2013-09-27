/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Delete</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A package that was deleted and is only present in the right model.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.PackageDelete#getPackageRight <em>Package Right</em>}</li>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.PackageDelete#getLeftContainer <em>Left Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getPackageDelete()
 * @model
 * @generated
 */
public interface PackageDelete extends PackageChange {
	/**
	 * Returns the value of the '<em><b>Package Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The package removed from the software.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Package Right</em>' reference.
	 * @see #setPackageRight(org.eclipse.gmt.modisco.java.Package)
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getPackageDelete_PackageRight()
	 * @model
	 * @generated
	 */
	org.eclipse.gmt.modisco.java.Package getPackageRight();

	/**
	 * Sets the value of the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.PackageDelete#getPackageRight <em>Package Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Right</em>' reference.
	 * @see #getPackageRight()
	 * @generated
	 */
	void setPackageRight(org.eclipse.gmt.modisco.java.Package value);

	/**
	 * Returns the value of the '<em><b>Left Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The parent package in the current model that no longer contains the deleted package.
	 * It might be null if the deleted package is conainted in a parent package that was deleted, too.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Left Container</em>' reference.
	 * @see #setLeftContainer(org.eclipse.gmt.modisco.java.Package)
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getPackageDelete_LeftContainer()
	 * @model
	 * @generated
	 */
	org.eclipse.gmt.modisco.java.Package getLeftContainer();

	/**
	 * Sets the value of the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.PackageDelete#getLeftContainer <em>Left Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Container</em>' reference.
	 * @see #getLeftContainer()
	 * @generated
	 */
	void setLeftContainer(org.eclipse.gmt.modisco.java.Package value);

} // PackageDelete
