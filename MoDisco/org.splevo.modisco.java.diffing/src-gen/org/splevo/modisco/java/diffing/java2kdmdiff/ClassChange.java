/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff;

import org.eclipse.gmt.modisco.java.ClassDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.ClassChange#getChangedClass <em>Changed Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getClassChange()
 * @model
 * @generated
 */
public interface ClassChange extends Java2KDMDiffExtension {

    /**
     * Returns the value of the '<em><b>Changed Class</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Changed Class</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Changed Class</em>' reference.
     * @see #setChangedClass(ClassDeclaration)
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getClassChange_ChangedClass()
     * @model required="true"
     * @generated
     */
    ClassDeclaration getChangedClass();

    /**
     * Sets the value of the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.ClassChange#getChangedClass <em>Changed Class</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Changed Class</em>' reference.
     * @see #getChangedClass()
     * @generated
     */
    void setChangedClass(ClassDeclaration value);
} // ClassChange
