/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff;

import org.eclipse.gmt.modisco.java.ClassDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Declaration Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A change on a class element.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange#getClassLeft <em>Class Left</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange#getClassRight <em>Class Right</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange#getClassChange <em>Class Change</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getClassDeclarationChange()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='ClassDeclarationChange'"
 * @generated
 */
public interface ClassDeclarationChange extends KDM2JavaDiffExtension {
	/**
	 * Returns the value of the '<em><b>Class Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The class in the left model which is the new modified version.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Class Left</em>' reference.
	 * @see #setClassLeft(ClassDeclaration)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getClassDeclarationChange_ClassLeft()
	 * @model
	 * @generated
	 */
	ClassDeclaration getClassLeft();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange#getClassLeft <em>Class Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Left</em>' reference.
	 * @see #getClassLeft()
	 * @generated
	 */
	void setClassLeft(ClassDeclaration value);

	/**
	 * Returns the value of the '<em><b>Class Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The class in the right model which is the original version.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Class Right</em>' reference.
	 * @see #setClassRight(ClassDeclaration)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getClassDeclarationChange_ClassRight()
	 * @model
	 * @generated
	 */
	ClassDeclaration getClassRight();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange#getClassRight <em>Class Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Right</em>' reference.
	 * @see #getClassRight()
	 * @generated
	 */
	void setClassRight(ClassDeclaration value);

	/**
	 * Returns the value of the '<em><b>Class Change</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange#getClassDeclaractionChanges <em>Class Declaraction Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Change</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Change</em>' container reference.
	 * @see #setClassChange(ClassChange)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getClassDeclarationChange_ClassChange()
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange#getClassDeclaractionChanges
	 * @model opposite="classDeclaractionChanges" transient="false"
	 * @generated
	 */
	ClassChange getClassChange();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange#getClassChange <em>Class Change</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Change</em>' container reference.
	 * @see #getClassChange()
	 * @generated
	 */
	void setClassChange(ClassChange value);

} // ClassDeclarationChange
