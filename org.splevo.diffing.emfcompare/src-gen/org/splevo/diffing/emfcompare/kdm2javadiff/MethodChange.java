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
 * A representation of the model object '<em><b>Method Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange#getMethodDeclarationChanges <em>Method Declaration Changes</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange#getStatementChanges <em>Statement Changes</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange#getClassChange <em>Class Change</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getMethodChange()
 * @model
 * @generated
 */
public interface MethodChange extends DiffGroup {
	/**
	 * Returns the value of the '<em><b>Method Declaration Changes</b></em>' containment reference list.
	 * The list contents are of type {@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange}.
	 * It is bidirectional and its opposite is '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange#getMethodChange <em>Method Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Declaration Changes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Declaration Changes</em>' containment reference list.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getMethodChange_MethodDeclarationChanges()
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange#getMethodChange
	 * @model opposite="methodChange" containment="true"
	 * @generated
	 */
	EList<MethodDeclarationChange> getMethodDeclarationChanges();

	/**
	 * Returns the value of the '<em><b>Statement Changes</b></em>' containment reference list.
	 * The list contents are of type {@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange}.
	 * It is bidirectional and its opposite is '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange#getMethodChange <em>Method Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Statement Changes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Statement Changes</em>' containment reference list.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getMethodChange_StatementChanges()
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange#getMethodChange
	 * @model opposite="methodChange" containment="true"
	 * @generated
	 */
	EList<StatementChange> getStatementChanges();

	/**
	 * Returns the value of the '<em><b>Class Change</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange#getMethodChanges <em>Method Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Change</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Change</em>' container reference.
	 * @see #setClassChange(ClassChange)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getMethodChange_ClassChange()
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange#getMethodChanges
	 * @model opposite="methodChanges" transient="false"
	 * @generated
	 */
	ClassChange getClassChange();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange#getClassChange <em>Class Change</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Change</em>' container reference.
	 * @see #getClassChange()
	 * @generated
	 */
	void setClassChange(ClassChange value);

} // MethodChange
