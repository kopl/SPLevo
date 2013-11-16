/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.splevo.modisco.java.diffing.java2kdmdiff.EnumChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.EnumChangeImpl#getChangedEnum <em>Changed Enum</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumChangeImpl extends Java2KDMDiffExtensionImpl implements EnumChange {
    /**
     * The cached value of the '{@link #getChangedEnum() <em>Changed Enum</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChangedEnum()
     * @generated
     * @ordered
     */
    protected EnumDeclaration changedEnum;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EnumChangeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Java2KDMDiffPackage.Literals.ENUM_CHANGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EnumDeclaration getChangedEnum() {
        if (changedEnum != null && changedEnum.eIsProxy()) {
            InternalEObject oldChangedEnum = (InternalEObject) changedEnum;
            changedEnum = (EnumDeclaration) eResolveProxy(oldChangedEnum);
            if (changedEnum != oldChangedEnum) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Java2KDMDiffPackage.ENUM_CHANGE__CHANGED_ENUM, oldChangedEnum, changedEnum));
            }
        }
        return changedEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EnumDeclaration basicGetChangedEnum() {
        return changedEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChangedEnum(EnumDeclaration newChangedEnum) {
        EnumDeclaration oldChangedEnum = changedEnum;
        changedEnum = newChangedEnum;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Java2KDMDiffPackage.ENUM_CHANGE__CHANGED_ENUM,
                    oldChangedEnum, changedEnum));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Java2KDMDiffPackage.ENUM_CHANGE__CHANGED_ENUM:
            if (resolve)
                return getChangedEnum();
            return basicGetChangedEnum();
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
        case Java2KDMDiffPackage.ENUM_CHANGE__CHANGED_ENUM:
            setChangedEnum((EnumDeclaration) newValue);
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
        case Java2KDMDiffPackage.ENUM_CHANGE__CHANGED_ENUM:
            setChangedEnum((EnumDeclaration) null);
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
        case Java2KDMDiffPackage.ENUM_CHANGE__CHANGED_ENUM:
            return changedEnum != null;
        }
        return super.eIsSet(featureID);
    }

} //EnumChangeImpl
