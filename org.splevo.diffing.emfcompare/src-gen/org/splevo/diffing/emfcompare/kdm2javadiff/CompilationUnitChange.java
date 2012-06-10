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
 * A representation of the model object '<em><b>Compilation Unit Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange#getClassChanges <em>Class Changes</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange#getImportDeclarationChanges <em>Import Declaration Changes</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange#getPackageChange <em>Package Change</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getCompilationUnitChange()
 * @model
 * @generated
 */
public interface CompilationUnitChange extends DiffGroup {
	/**
	 * Returns the value of the '<em><b>Class Changes</b></em>' containment reference list.
	 * The list contents are of type {@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange}.
	 * It is bidirectional and its opposite is '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange#getCompilationUnitChange <em>Compilation Unit Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Changes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Changes</em>' containment reference list.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getCompilationUnitChange_ClassChanges()
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange#getCompilationUnitChange
	 * @model opposite="compilationUnitChange" containment="true"
	 * @generated
	 */
	EList<ClassChange> getClassChanges();

	/**
	 * Returns the value of the '<em><b>Import Declaration Changes</b></em>' containment reference list.
	 * The list contents are of type {@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange}.
	 * It is bidirectional and its opposite is '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange#getCompilationUnitChange <em>Compilation Unit Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Import Declaration Changes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Import Declaration Changes</em>' containment reference list.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getCompilationUnitChange_ImportDeclarationChanges()
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange#getCompilationUnitChange
	 * @model opposite="compilationUnitChange" containment="true"
	 * @generated
	 */
	EList<ImportDeclarationChange> getImportDeclarationChanges();

	/**
	 * Returns the value of the '<em><b>Package Change</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange#getCompilationUnitChanges <em>Compilation Unit Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package Change</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package Change</em>' container reference.
	 * @see #setPackageChange(PackageChange)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getCompilationUnitChange_PackageChange()
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange#getCompilationUnitChanges
	 * @model opposite="compilationUnitChanges" transient="false"
	 * @generated
	 */
	PackageChange getPackageChange();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange#getPackageChange <em>Package Change</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Change</em>' container reference.
	 * @see #getPackageChange()
	 * @generated
	 */
	void setPackageChange(PackageChange value);

} // CompilationUnitChange
