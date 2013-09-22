/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.compare.diff.metamodel.DifferenceKind;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.diffing.emfcompare.java2kdmdiff.PackageInsert;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Package Insert</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.PackageInsertImpl#getPackageLeft <em>
 * Package Left</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PackageInsertImpl extends PackageChangeImpl implements PackageInsert {
    /**
     * The cached value of the '{@link #getPackageLeft() <em>Package Left</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPackageLeft()
     * @generated
     * @ordered
     */
    protected org.eclipse.gmt.modisco.java.Package packageLeft;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected PackageInsertImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Java2KDMDiffPackage.Literals.PACKAGE_INSERT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public org.eclipse.gmt.modisco.java.Package getPackageLeft() {
        if (packageLeft != null && packageLeft.eIsProxy()) {
            InternalEObject oldPackageLeft = (InternalEObject) packageLeft;
            packageLeft = (org.eclipse.gmt.modisco.java.Package) eResolveProxy(oldPackageLeft);
            if (packageLeft != oldPackageLeft) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Java2KDMDiffPackage.PACKAGE_INSERT__PACKAGE_LEFT, oldPackageLeft, packageLeft));
            }
        }
        return packageLeft;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public org.eclipse.gmt.modisco.java.Package basicGetPackageLeft() {
        return packageLeft;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setPackageLeft(org.eclipse.gmt.modisco.java.Package newPackageLeft) {
        org.eclipse.gmt.modisco.java.Package oldPackageLeft = packageLeft;
        packageLeft = newPackageLeft;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Java2KDMDiffPackage.PACKAGE_INSERT__PACKAGE_LEFT,
                    oldPackageLeft, packageLeft));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Java2KDMDiffPackage.PACKAGE_INSERT__PACKAGE_LEFT:
            if (resolve)
                return getPackageLeft();
            return basicGetPackageLeft();
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
        case Java2KDMDiffPackage.PACKAGE_INSERT__PACKAGE_LEFT:
            setPackageLeft((org.eclipse.gmt.modisco.java.Package) newValue);
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
        case Java2KDMDiffPackage.PACKAGE_INSERT__PACKAGE_LEFT:
            setPackageLeft((org.eclipse.gmt.modisco.java.Package) null);
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
        case Java2KDMDiffPackage.PACKAGE_INSERT__PACKAGE_LEFT:
            return packageLeft != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> The difference kind of a package insert is always
     * DifferenceKind.ADDITION. <!-- end-user-doc --> {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public DifferenceKind getKind() {
        return DifferenceKind.ADDITION;
    }

} // PackageInsertImpl
