/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A changed package declaration.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.PackageChange#getChangedPackage <em>Changed Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getPackageChange()
 * @model
 * @generated
 */
public interface PackageChange extends Java2KDMDiffExtension {

    /**
     * Returns the value of the '<em><b>Changed Package</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Changed Package</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Changed Package</em>' reference.
     * @see #setChangedPackage(org.eclipse.gmt.modisco.java.Package)
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getPackageChange_ChangedPackage()
     * @model required="true"
     * @generated
     */
    org.eclipse.gmt.modisco.java.Package getChangedPackage();

    /**
     * Sets the value of the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.PackageChange#getChangedPackage <em>Changed Package</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Changed Package</em>' reference.
     * @see #getChangedPackage()
     * @generated
     */
    void setChangedPackage(org.eclipse.gmt.modisco.java.Package value);
} // PackageChange
