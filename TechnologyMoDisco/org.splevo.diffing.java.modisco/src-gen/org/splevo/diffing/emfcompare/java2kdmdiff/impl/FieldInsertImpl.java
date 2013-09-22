/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.compare.diff.metamodel.DifferenceKind;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmt.modisco.java.FieldDeclaration;

import org.splevo.diffing.emfcompare.java2kdmdiff.FieldInsert;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Field Insert</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.FieldInsertImpl#getFieldLeft <em>Field
 * Left</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class FieldInsertImpl extends FieldChangeImpl implements FieldInsert {
    /**
     * The cached value of the '{@link #getFieldLeft() <em>Field Left</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFieldLeft()
     * @generated
     * @ordered
     */
    protected FieldDeclaration fieldLeft;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected FieldInsertImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Java2KDMDiffPackage.Literals.FIELD_INSERT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FieldDeclaration getFieldLeft() {
        if (fieldLeft != null && fieldLeft.eIsProxy()) {
            InternalEObject oldFieldLeft = (InternalEObject) fieldLeft;
            fieldLeft = (FieldDeclaration) eResolveProxy(oldFieldLeft);
            if (fieldLeft != oldFieldLeft) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Java2KDMDiffPackage.FIELD_INSERT__FIELD_LEFT, oldFieldLeft, fieldLeft));
            }
        }
        return fieldLeft;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FieldDeclaration basicGetFieldLeft() {
        return fieldLeft;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setFieldLeft(FieldDeclaration newFieldLeft) {
        FieldDeclaration oldFieldLeft = fieldLeft;
        fieldLeft = newFieldLeft;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Java2KDMDiffPackage.FIELD_INSERT__FIELD_LEFT,
                    oldFieldLeft, fieldLeft));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Java2KDMDiffPackage.FIELD_INSERT__FIELD_LEFT:
            if (resolve)
                return getFieldLeft();
            return basicGetFieldLeft();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case Java2KDMDiffPackage.FIELD_INSERT__FIELD_LEFT:
            setFieldLeft((FieldDeclaration) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case Java2KDMDiffPackage.FIELD_INSERT__FIELD_LEFT:
            setFieldLeft((FieldDeclaration) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case Java2KDMDiffPackage.FIELD_INSERT__FIELD_LEFT:
            return fieldLeft != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> The difference kind of a field insert is always
     * DifferenceKind.ADDITION. <!-- end-user-doc --> {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public DifferenceKind getKind() {
        return DifferenceKind.ADDITION;
    }

} // FieldInsertImpl
