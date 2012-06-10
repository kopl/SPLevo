/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.compare.diff.metamodel.DiffGroup;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The changes within a specific packages. 
 * Do to the hierarchical structure of packages, also packages can contain sub-packages.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange#getCompilationUnitChanges <em>Compilation Unit Changes</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange#getSubPackages <em>Sub Packages</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange#getPackageLeft <em>Package Left</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange#getPackageRight <em>Package Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getPackageChange()
 * @model
 * @generated
 */
public interface PackageChange extends DiffGroup {
	/**
	 * Returns the value of the '<em><b>Compilation Unit Changes</b></em>' containment reference list.
	 * The list contents are of type {@link org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange}.
	 * It is bidirectional and its opposite is '{@link org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange#getPackageChange <em>Package Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compilation Unit Changes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compilation Unit Changes</em>' containment reference list.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getPackageChange_CompilationUnitChanges()
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange#getPackageChange
	 * @model opposite="packageChange" containment="true"
	 * @generated
	 */
	EList<CompilationUnitChange> getCompilationUnitChanges();

	/**
	 * Returns the value of the '<em><b>Sub Packages</b></em>' containment reference list.
	 * The list contents are of type {@link org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Packages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Packages</em>' containment reference list.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getPackageChange_SubPackages()
	 * @model containment="true"
	 * @generated
	 */
	EList<PackageChange> getSubPackages();

	/**
	 * Returns the value of the '<em><b>Package Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The package in the left model which is the new modified version.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Package Left</em>' reference.
	 * @see #setPackageLeft(org.eclipse.gmt.modisco.java.Package)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getPackageChange_PackageLeft()
	 * @model
	 * @generated
	 */
	org.eclipse.gmt.modisco.java.Package getPackageLeft();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange#getPackageLeft <em>Package Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Left</em>' reference.
	 * @see #getPackageLeft()
	 * @generated
	 */
	void setPackageLeft(org.eclipse.gmt.modisco.java.Package value);

	/**
	 * Returns the value of the '<em><b>Package Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The package in the right model which is the original version.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Package Right</em>' reference.
	 * @see #setPackageRight(org.eclipse.gmt.modisco.java.Package)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getPackageChange_PackageRight()
	 * @model
	 * @generated
	 */
	org.eclipse.gmt.modisco.java.Package getPackageRight();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange#getPackageRight <em>Package Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Right</em>' reference.
	 * @see #getPackageRight()
	 * @generated
	 */
	void setPackageRight(org.eclipse.gmt.modisco.java.Package value);

} // PackageChange
