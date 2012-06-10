/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff;

import org.eclipse.gmt.modisco.java.MethodDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Declaration Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A differnece identifying a change about a method
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange#getMethodChange <em>Method Change</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange#getMethodDeclarationLeft <em>Method Declaration Left</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange#getMethodDeclarationRight <em>Method Declaration Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getMethodDeclarationChange()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface MethodDeclarationChange extends KDM2JavaDiffExtension {
	/**
	 * Returns the value of the '<em><b>Method Change</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange#getMethodDeclarationChange <em>Method Declaration Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Change</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Change</em>' container reference.
	 * @see #setMethodChange(MethodChange)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getMethodDeclarationChange_MethodChange()
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange#getMethodDeclarationChange
	 * @model opposite="methodDeclarationChange" transient="false"
	 * @generated
	 */
	MethodChange getMethodChange();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange#getMethodChange <em>Method Change</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Change</em>' container reference.
	 * @see #getMethodChange()
	 * @generated
	 */
	void setMethodChange(MethodChange value);

	/**
	 * Returns the value of the '<em><b>Method Declaration Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Declaration Left</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Declaration Left</em>' reference.
	 * @see #setMethodDeclarationLeft(MethodDeclaration)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getMethodDeclarationChange_MethodDeclarationLeft()
	 * @model
	 * @generated
	 */
	MethodDeclaration getMethodDeclarationLeft();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange#getMethodDeclarationLeft <em>Method Declaration Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Declaration Left</em>' reference.
	 * @see #getMethodDeclarationLeft()
	 * @generated
	 */
	void setMethodDeclarationLeft(MethodDeclaration value);

	/**
	 * Returns the value of the '<em><b>Method Declaration Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Declaration Right</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Declaration Right</em>' reference.
	 * @see #setMethodDeclarationRight(MethodDeclaration)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getMethodDeclarationChange_MethodDeclarationRight()
	 * @model
	 * @generated
	 */
	MethodDeclaration getMethodDeclarationRight();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange#getMethodDeclarationRight <em>Method Declaration Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Declaration Right</em>' reference.
	 * @see #getMethodDeclarationRight()
	 * @generated
	 */
	void setMethodDeclarationRight(MethodDeclaration value);

} // MethodDeclarationChange
