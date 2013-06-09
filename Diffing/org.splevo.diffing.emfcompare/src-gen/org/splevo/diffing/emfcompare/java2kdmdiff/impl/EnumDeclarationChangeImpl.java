/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmt.modisco.java.EnumDeclaration;

import org.splevo.diffing.emfcompare.java2kdmdiff.EnumDeclarationChange;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Enum Declaration Change</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.EnumDeclarationChangeImpl#getEnumLeft
 * <em>Enum Left</em>}</li>
 * <li>
 * {@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.EnumDeclarationChangeImpl#getEnumRight
 * <em>Enum Right</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class EnumDeclarationChangeImpl extends EnumChangeImpl implements EnumDeclarationChange {
    /**
     * The cached value of the '{@link #getEnumLeft() <em>Enum Left</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getEnumLeft()
     * @generated
     * @ordered
     */
    protected EnumDeclaration enumLeft;

    /**
     * The cached value of the '{@link #getEnumRight() <em>Enum Right</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getEnumRight()
     * @generated
     * @ordered
     */
    protected EnumDeclaration enumRight;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected EnumDeclarationChangeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Java2KDMDiffPackage.Literals.ENUM_DECLARATION_CHANGE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EnumDeclaration getEnumLeft() {
        if (enumLeft != null && enumLeft.eIsProxy()) {
            InternalEObject oldEnumLeft = (InternalEObject) enumLeft;
            enumLeft = (EnumDeclaration) eResolveProxy(oldEnumLeft);
            if (enumLeft != oldEnumLeft) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Java2KDMDiffPackage.ENUM_DECLARATION_CHANGE__ENUM_LEFT, oldEnumLeft, enumLeft));
            }
        }
        return enumLeft;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EnumDeclaration basicGetEnumLeft() {
        return enumLeft;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setEnumLeft(EnumDeclaration newEnumLeft) {
        EnumDeclaration oldEnumLeft = enumLeft;
        enumLeft = newEnumLeft;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Java2KDMDiffPackage.ENUM_DECLARATION_CHANGE__ENUM_LEFT, oldEnumLeft, enumLeft));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EnumDeclaration getEnumRight() {
        if (enumRight != null && enumRight.eIsProxy()) {
            InternalEObject oldEnumRight = (InternalEObject) enumRight;
            enumRight = (EnumDeclaration) eResolveProxy(oldEnumRight);
            if (enumRight != oldEnumRight) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Java2KDMDiffPackage.ENUM_DECLARATION_CHANGE__ENUM_RIGHT, oldEnumRight, enumRight));
            }
        }
        return enumRight;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EnumDeclaration basicGetEnumRight() {
        return enumRight;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setEnumRight(EnumDeclaration newEnumRight) {
        EnumDeclaration oldEnumRight = enumRight;
        enumRight = newEnumRight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Java2KDMDiffPackage.ENUM_DECLARATION_CHANGE__ENUM_RIGHT, oldEnumRight, enumRight));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Java2KDMDiffPackage.ENUM_DECLARATION_CHANGE__ENUM_LEFT:
            if (resolve)
                return getEnumLeft();
            return basicGetEnumLeft();
        case Java2KDMDiffPackage.ENUM_DECLARATION_CHANGE__ENUM_RIGHT:
            if (resolve)
                return getEnumRight();
            return basicGetEnumRight();
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
        case Java2KDMDiffPackage.ENUM_DECLARATION_CHANGE__ENUM_LEFT:
            setEnumLeft((EnumDeclaration) newValue);
            return;
        case Java2KDMDiffPackage.ENUM_DECLARATION_CHANGE__ENUM_RIGHT:
            setEnumRight((EnumDeclaration) newValue);
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
        case Java2KDMDiffPackage.ENUM_DECLARATION_CHANGE__ENUM_LEFT:
            setEnumLeft((EnumDeclaration) null);
            return;
        case Java2KDMDiffPackage.ENUM_DECLARATION_CHANGE__ENUM_RIGHT:
            setEnumRight((EnumDeclaration) null);
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
        case Java2KDMDiffPackage.ENUM_DECLARATION_CHANGE__ENUM_LEFT:
            return enumLeft != null;
        case Java2KDMDiffPackage.ENUM_DECLARATION_CHANGE__ENUM_RIGHT:
            return enumRight != null;
        }
        return super.eIsSet(featureID);
    }

} // EnumDeclarationChangeImpl
