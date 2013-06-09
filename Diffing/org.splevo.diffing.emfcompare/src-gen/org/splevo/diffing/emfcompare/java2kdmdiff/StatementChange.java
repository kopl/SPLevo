/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff;

import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;

import org.eclipse.gmt.modisco.java.Statement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Statement Change</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> A change on a statement level. Depending on the type of change (delete,
 * add, remove, move) the left, right or both statement references are filled. <!-- end-model-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange#getStatementRight <em>
 * Statement Right</em>}</li>
 * <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange#getStatementLeft <em>
 * Statement Left</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getStatementChange()
 * @model
 * @generated
 */
public interface StatementChange extends Java2KDMDiffExtension {
    /**
     * Returns the value of the '<em><b>Statement Right</b></em>' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> The statement in the right model which is the
     * new modified version. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Statement Right</em>' reference.
     * @see #setStatementRight(Statement)
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getStatementChange_StatementRight()
     * @model extendedMetaData="name='statementRight' namespace=''"
     * @generated
     */
    Statement getStatementRight();

    /**
     * Sets the value of the '
     * {@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange#getStatementRight
     * <em>Statement Right</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Statement Right</em>' reference.
     * @see #getStatementRight()
     * @generated
     */
    void setStatementRight(Statement value);

    /**
     * Returns the value of the '<em><b>Statement Left</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Statement Left</em>' reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The statement source element in the left
     * model. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Statement Left</em>' reference.
     * @see #setStatementLeft(Statement)
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getStatementChange_StatementLeft()
     * @model
     * @generated
     */
    Statement getStatementLeft();

    /**
     * Sets the value of the '
     * {@link org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange#getStatementLeft
     * <em>Statement Left</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Statement Left</em>' reference.
     * @see #getStatementLeft()
     * @generated
     */
    void setStatementLeft(Statement value);

} // StatementChange
