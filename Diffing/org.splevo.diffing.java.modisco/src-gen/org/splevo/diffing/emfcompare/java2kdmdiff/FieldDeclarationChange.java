/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff;

import org.eclipse.gmt.modisco.java.FieldDeclaration;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Field Declaration Change</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> A change identifying a modified field declaration. <!-- end-model-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldDeclarationChange#getFieldLeft <em>
 * Field Left</em>}</li>
 * <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldDeclarationChange#getFieldRight <em>
 * Field Right</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getFieldDeclarationChange()
 * @model
 * @generated
 */
public interface FieldDeclarationChange extends FieldChange {
    /**
     * Returns the value of the '<em><b>Field Left</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The new version of the field. <!-- end-model-doc
     * -->
     * 
     * @return the value of the '<em>Field Left</em>' reference.
     * @see #setFieldLeft(FieldDeclaration)
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getFieldDeclarationChange_FieldLeft()
     * @model
     * @generated
     */
    FieldDeclaration getFieldLeft();

    /**
     * Sets the value of the '
     * {@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldDeclarationChange#getFieldLeft
     * <em>Field Left</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Field Left</em>' reference.
     * @see #getFieldLeft()
     * @generated
     */
    void setFieldLeft(FieldDeclaration value);

    /**
     * Returns the value of the '<em><b>Field Right</b></em>' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> The original version of the field. <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>Field Right</em>' reference.
     * @see #setFieldRight(FieldDeclaration)
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getFieldDeclarationChange_FieldRight()
     * @model
     * @generated
     */
    FieldDeclaration getFieldRight();

    /**
     * Sets the value of the '
     * {@link org.splevo.diffing.emfcompare.java2kdmdiff.FieldDeclarationChange#getFieldRight
     * <em>Field Right</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Field Right</em>' reference.
     * @see #getFieldRight()
     * @generated
     */
    void setFieldRight(FieldDeclaration value);

} // FieldDeclarationChange
