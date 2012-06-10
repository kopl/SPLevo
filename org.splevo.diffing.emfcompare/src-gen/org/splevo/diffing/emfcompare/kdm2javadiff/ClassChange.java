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
 * A representation of the model object '<em><b>Class Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A class change group contains the changes in the classes declaration as well as the contained sub changes such as the changed methods within the class.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange#getClassDeclaractionChanges <em>Class Declaraction Changes</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange#getMethodChanges <em>Method Changes</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange#getCompilationUnitChange <em>Compilation Unit Change</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getClassChange()
 * @model
 * @generated
 */
public interface ClassChange extends DiffGroup {
	/**
	 * Returns the value of the '<em><b>Class Declaraction Changes</b></em>' containment reference list.
	 * The list contents are of type {@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange}.
	 * It is bidirectional and its opposite is '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange#getClassChange <em>Class Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The class desclaration changes of the class change group.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Class Declaraction Changes</em>' containment reference list.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getClassChange_ClassDeclaractionChanges()
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange#getClassChange
	 * @model opposite="classChange" containment="true"
	 * @generated
	 */
	EList<ClassDeclarationChange> getClassDeclaractionChanges();

	/**
	 * Returns the value of the '<em><b>Method Changes</b></em>' containment reference list.
	 * The list contents are of type {@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange}.
	 * It is bidirectional and its opposite is '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange#getClassChange <em>Class Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Changes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Changes</em>' containment reference list.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getClassChange_MethodChanges()
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange#getClassChange
	 * @model opposite="classChange" containment="true" required="true"
	 * @generated
	 */
	EList<MethodChange> getMethodChanges();

	/**
	 * Returns the value of the '<em><b>Compilation Unit Change</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange#getClassChanges <em>Class Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compilation Unit Change</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compilation Unit Change</em>' container reference.
	 * @see #setCompilationUnitChange(CompilationUnitChange)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getClassChange_CompilationUnitChange()
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange#getClassChanges
	 * @model opposite="classChanges" transient="false"
	 * @generated
	 */
	CompilationUnitChange getCompilationUnitChange();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange#getCompilationUnitChange <em>Compilation Unit Change</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compilation Unit Change</em>' container reference.
	 * @see #getCompilationUnitChange()
	 * @generated
	 */
	void setCompilationUnitChange(CompilationUnitChange value);

} // ClassChange
