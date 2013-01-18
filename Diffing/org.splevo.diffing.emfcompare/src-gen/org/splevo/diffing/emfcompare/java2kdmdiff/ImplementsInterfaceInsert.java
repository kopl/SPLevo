/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.InterfaceDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Implements Interface Insert</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceInsert#getImplementedInterface <em>Implemented Interface</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceInsert#getChangedClass <em>Changed Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getImplementsInterfaceInsert()
 * @model
 * @generated
 */
public interface ImplementsInterfaceInsert extends ClassChange {
    /**
     * Returns the value of the '<em><b>Implemented Interface</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Implemented Interface</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Implemented Interface</em>' reference.
     * @see #setImplementedInterface(InterfaceDeclaration)
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getImplementsInterfaceInsert_ImplementedInterface()
     * @model required="true"
     * @generated
     */
    InterfaceDeclaration getImplementedInterface();

    /**
     * Sets the value of the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceInsert#getImplementedInterface <em>Implemented Interface</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Implemented Interface</em>' reference.
     * @see #getImplementedInterface()
     * @generated
     */
    void setImplementedInterface(InterfaceDeclaration value);

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
     * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage#getImplementsInterfaceInsert_ChangedClass()
     * @model required="true"
     * @generated
     */
    ClassDeclaration getChangedClass();

    /**
     * Sets the value of the '{@link org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceInsert#getChangedClass <em>Changed Class</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Changed Class</em>' reference.
     * @see #getChangedClass()
     * @generated
     */
    void setChangedClass(ClassDeclaration value);

} // ImplementsInterfaceInsert
