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
 * A representation of the model object '<em><b>Import Declaration Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A difference identifying that an import has been changed.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange#getImportLeft <em>Import Left</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange#getImportRight <em>Import Right</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange#getCompilationUnitChange <em>Compilation Unit Change</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getImportDeclarationChange()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='ImportDeclarationChange'"
 * @generated
 */
public interface ImportDeclarationChange extends KDM2JavaDiffExtension {
	/**
	 * Returns the value of the '<em><b>Import Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Import Left</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Import Left</em>' reference.
	 * @see #setImportLeft(ImportDeclaration)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getImportDeclarationChange_ImportLeft()
	 * @model
	 * @generated
	 */
	ImportDeclaration getImportLeft();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange#getImportLeft <em>Import Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Import Left</em>' reference.
	 * @see #getImportLeft()
	 * @generated
	 */
	void setImportLeft(ImportDeclaration value);

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
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getImportDeclarationChange_ImportRight()
	 * @model
	 * @generated
	 */
	ImportDeclaration getImportRight();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange#getImportRight <em>Import Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Import Right</em>' reference.
	 * @see #getImportRight()
	 * @generated
	 */
	void setImportRight(ImportDeclaration value);

	/**
	 * Returns the value of the '<em><b>Compilation Unit Change</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange#getImportDeclarationChanges <em>Import Declaration Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compilation Unit Change</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compilation Unit Change</em>' container reference.
	 * @see #setCompilationUnitChange(CompilationUnitChange)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getImportDeclarationChange_CompilationUnitChange()
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange#getImportDeclarationChanges
	 * @model opposite="importDeclarationChanges" transient="false"
	 * @generated
	 */
	CompilationUnitChange getCompilationUnitChange();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange#getCompilationUnitChange <em>Compilation Unit Change</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compilation Unit Change</em>' container reference.
	 * @see #getCompilationUnitChange()
	 * @generated
	 */
	void setCompilationUnitChange(CompilationUnitChange value);

} // ImportDeclarationChange
