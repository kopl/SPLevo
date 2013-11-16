/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.modisco.java.diffing.java2kdmdiff.PackageChange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.PackageChangeImpl#getChangedPackage <em>Changed Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageChangeImpl extends Java2KDMDiffExtensionImpl implements PackageChange {
    /**
     * The cached value of the '{@link #getChangedPackage() <em>Changed Package</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChangedPackage()
     * @generated
     * @ordered
     */
    protected org.eclipse.gmt.modisco.java.Package changedPackage;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PackageChangeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Java2KDMDiffPackage.Literals.PACKAGE_CHANGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public org.eclipse.gmt.modisco.java.Package getChangedPackage() {
        if (changedPackage != null && changedPackage.eIsProxy()) {
            InternalEObject oldChangedPackage = (InternalEObject) changedPackage;
            changedPackage = (org.eclipse.gmt.modisco.java.Package) eResolveProxy(oldChangedPackage);
            if (changedPackage != oldChangedPackage) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Java2KDMDiffPackage.PACKAGE_CHANGE__CHANGED_PACKAGE, oldChangedPackage, changedPackage));
            }
        }
        return changedPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public org.eclipse.gmt.modisco.java.Package basicGetChangedPackage() {
        return changedPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChangedPackage(org.eclipse.gmt.modisco.java.Package newChangedPackage) {
        org.eclipse.gmt.modisco.java.Package oldChangedPackage = changedPackage;
        changedPackage = newChangedPackage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Java2KDMDiffPackage.PACKAGE_CHANGE__CHANGED_PACKAGE,
                    oldChangedPackage, changedPackage));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Java2KDMDiffPackage.PACKAGE_CHANGE__CHANGED_PACKAGE:
            if (resolve)
                return getChangedPackage();
            return basicGetChangedPackage();
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
        case Java2KDMDiffPackage.PACKAGE_CHANGE__CHANGED_PACKAGE:
            setChangedPackage((org.eclipse.gmt.modisco.java.Package) newValue);
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
        case Java2KDMDiffPackage.PACKAGE_CHANGE__CHANGED_PACKAGE:
            setChangedPackage((org.eclipse.gmt.modisco.java.Package) null);
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
        case Java2KDMDiffPackage.PACKAGE_CHANGE__CHANGED_PACKAGE:
            return changedPackage != null;
        }
        return super.eIsSet(featureID);
    }

} //PackageChangeImpl
