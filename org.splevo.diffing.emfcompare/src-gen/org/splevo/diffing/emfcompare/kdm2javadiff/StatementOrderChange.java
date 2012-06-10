/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Statement Order Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A change of a statement order. Statements are an ordered list within a parent element (e.g. a method or block statement). If the order is changed, the statement resides in the same parent element but the position in the ordered list is changed.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementOrderChange#getStatementIndexNew <em>Statement Index New</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementOrderChange#getStatementIndexOld <em>Statement Index Old</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getStatementOrderChange()
 * @model
 * @generated
 */
public interface StatementOrderChange extends StatementChange {
	/**
	 * Returns the value of the '<em><b>Statement Index New</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Statement Index New</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Statement Index New</em>' attribute.
	 * @see #setStatementIndexNew(int)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getStatementOrderChange_StatementIndexNew()
	 * @model default="0" required="true"
	 * @generated
	 */
	int getStatementIndexNew();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementOrderChange#getStatementIndexNew <em>Statement Index New</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Statement Index New</em>' attribute.
	 * @see #getStatementIndexNew()
	 * @generated
	 */
	void setStatementIndexNew(int value);

	/**
	 * Returns the value of the '<em><b>Statement Index Old</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Statement Index Old</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Statement Index Old</em>' attribute.
	 * @see #setStatementIndexOld(int)
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage#getStatementOrderChange_StatementIndexOld()
	 * @model default="0" required="true"
	 * @generated
	 */
	int getStatementIndexOld();

	/**
	 * Sets the value of the '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementOrderChange#getStatementIndexOld <em>Statement Index Old</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Statement Index Old</em>' attribute.
	 * @see #getStatementIndexOld()
	 * @generated
	 */
	void setStatementIndexOld(int value);

} // StatementOrderChange
