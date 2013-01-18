/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff;

import org.eclipse.gmt.modisco.java.ClassDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Insert</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.ClassInsert#getClassLeft <em>Class Left</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getClassInsert()
 * @model
 * @generated
 */
public interface ClassInsert extends ClassChange {
    /**
     * Returns the value of the '<em><b>Class Left</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The added class declaration.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Class Left</em>' reference.
     * @see #setClassLeft(ClassDeclaration)
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getClassInsert_ClassLeft()
     * @model required="true"
     * @generated
     */
    ClassDeclaration getClassLeft();

    /**
     * Sets the value of the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ClassInsert#getClassLeft <em>Class Left</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Class Left</em>' reference.
     * @see #getClassLeft()
     * @generated
     */
    void setClassLeft(ClassDeclaration value);

} // ClassInsert
