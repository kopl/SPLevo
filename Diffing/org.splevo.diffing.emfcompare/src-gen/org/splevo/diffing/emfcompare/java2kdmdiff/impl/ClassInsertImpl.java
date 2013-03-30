/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.compare.diff.metamodel.DifferenceKind;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmt.modisco.java.ClassDeclaration;

import org.splevo.diffing.emfcompare.java2kdmdiff.ClassInsert;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Insert</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ClassInsertImpl#getClassLeft <em>Class Left</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassInsertImpl extends ClassChangeImpl implements ClassInsert {
    /**
     * The cached value of the '{@link #getClassLeft() <em>Class Left</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getClassLeft()
     * @generated
     * @ordered
     */
    protected ClassDeclaration classLeft;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ClassInsertImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Java2KDMDiffPackage.Literals.CLASS_INSERT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ClassDeclaration getClassLeft() {
        if (classLeft != null && classLeft.eIsProxy()) {
            InternalEObject oldClassLeft = (InternalEObject)classLeft;
            classLeft = (ClassDeclaration)eResolveProxy(oldClassLeft);
            if (classLeft != oldClassLeft) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, Java2KDMDiffPackage.CLASS_INSERT__CLASS_LEFT, oldClassLeft, classLeft));
            }
        }
        return classLeft;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ClassDeclaration basicGetClassLeft() {
        return classLeft;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setClassLeft(ClassDeclaration newClassLeft) {
        ClassDeclaration oldClassLeft = classLeft;
        classLeft = newClassLeft;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Java2KDMDiffPackage.CLASS_INSERT__CLASS_LEFT, oldClassLeft, classLeft));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case Java2KDMDiffPackage.CLASS_INSERT__CLASS_LEFT:
                if (resolve) return getClassLeft();
                return basicGetClassLeft();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case Java2KDMDiffPackage.CLASS_INSERT__CLASS_LEFT:
                setClassLeft((ClassDeclaration)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case Java2KDMDiffPackage.CLASS_INSERT__CLASS_LEFT:
                setClassLeft((ClassDeclaration)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case Java2KDMDiffPackage.CLASS_INSERT__CLASS_LEFT:
                return classLeft != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * The difference kind of a class insert is always DifferenceKind.ADDITION.
     * <!-- end-user-doc -->
     * {@inheritDoc}
     * @generated NOT
     */
    @Override
    public DifferenceKind getKind() {
        return DifferenceKind.ADDITION;
    }

} //ClassInsertImpl
