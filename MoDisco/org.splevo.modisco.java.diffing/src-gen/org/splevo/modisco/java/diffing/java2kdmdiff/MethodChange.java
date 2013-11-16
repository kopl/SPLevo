/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.MethodChange#getChangedMethod <em>Changed Method</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getMethodChange()
 * @model
 * @generated
 */
public interface MethodChange extends Java2KDMDiffExtension {

    /**
     * Returns the value of the '<em><b>Changed Method</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Changed Method</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Changed Method</em>' reference.
     * @see #setChangedMethod(AbstractMethodDeclaration)
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getMethodChange_ChangedMethod()
     * @model required="true"
     * @generated
     */
    AbstractMethodDeclaration getChangedMethod();

    /**
     * Sets the value of the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.MethodChange#getChangedMethod <em>Changed Method</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Changed Method</em>' reference.
     * @see #getChangedMethod()
     * @generated
     */
    void setChangedMethod(AbstractMethodDeclaration value);
} // MethodChange
