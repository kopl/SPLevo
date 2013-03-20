/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff;

import org.eclipse.gmt.modisco.java.FieldDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field Delete</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldDelete#getFieldRight <em>Field Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getFieldDelete()
 * @model
 * @generated
 */
public interface FieldDelete extends FieldChange {
    /**
     * Returns the value of the '<em><b>Field Right</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Field Right</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The original field declaration that has been removed.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Field Right</em>' reference.
     * @see #setFieldRight(FieldDeclaration)
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getFieldDelete_FieldRight()
     * @model
     * @generated
     */
    FieldDeclaration getFieldRight();

    /**
     * Sets the value of the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldDelete#getFieldRight <em>Field Right</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Field Right</em>' reference.
     * @see #getFieldRight()
     * @generated
     */
    void setFieldRight(FieldDeclaration value);

} // FieldDelete
