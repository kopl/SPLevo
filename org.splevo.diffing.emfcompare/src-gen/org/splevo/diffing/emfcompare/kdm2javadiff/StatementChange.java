/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff;

import org.eclipse.gmt.modisco.java.Statement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Statement Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A change on a statement level.
 * Statements include simple statements as well as structural statements such as loops and conditions. Check the KDM/Java GAST model for details.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange#getStatementLeft <em>Statement Left</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange#getStatementRight <em>Statement Right</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange#getMethodChange <em>Method Change</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getStatementChange()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface StatementChange extends KDM2JavaDiffExtension {
	/**
	 * Returns the value of the '<em><b>Statement Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The statement in the left model which is the new modified version.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Statement Left</em>' reference.
	 * @see #setStatementLeft(Statement)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getStatementChange_StatementLeft()
	 * @model
	 * @generated
	 */
	Statement getStatementLeft();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange#getStatementLeft <em>Statement Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Statement Left</em>' reference.
	 * @see #getStatementLeft()
	 * @generated
	 */
	void setStatementLeft(Statement value);

	/**
	 * Returns the value of the '<em><b>Statement Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The statement in the right model which is the original version.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Statement Right</em>' reference.
	 * @see #setStatementRight(Statement)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getStatementChange_StatementRight()
	 * @model
	 * @generated
	 */
	Statement getStatementRight();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange#getStatementRight <em>Statement Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Statement Right</em>' reference.
	 * @see #getStatementRight()
	 * @generated
	 */
	void setStatementRight(Statement value);

	/**
	 * Returns the value of the '<em><b>Method Change</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange#getStatementChanges <em>Statement Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Change</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Change</em>' container reference.
	 * @see #setMethodChange(MethodChange)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getStatementChange_MethodChange()
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange#getStatementChanges
	 * @model opposite="statementChanges" transient="false"
	 * @generated
	 */
	MethodChange getMethodChange();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange#getMethodChange <em>Method Change</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Change</em>' container reference.
	 * @see #getMethodChange()
	 * @generated
	 */
	void setMethodChange(MethodChange value);

} // StatementChange
