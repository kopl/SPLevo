/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.modisco.java.diffing.java2kdmdiff.PackageDelete;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Delete</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.PackageDeleteImpl#getPackageRight <em>Package Right</em>}</li>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.PackageDeleteImpl#getLeftContainer <em>Left Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageDeleteImpl extends PackageChangeImpl implements
		PackageDelete {
	/**
	 * The cached value of the '{@link #getPackageRight() <em>Package Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageRight()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.gmt.modisco.java.Package packageRight;

	/**
	 * The cached value of the '{@link #getLeftContainer() <em>Left Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftContainer()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.gmt.modisco.java.Package leftContainer;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageDeleteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Java2KDMDiffPackage.Literals.PACKAGE_DELETE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.gmt.modisco.java.Package getPackageRight() {
		if (packageRight != null && packageRight.eIsProxy()) {
			InternalEObject oldPackageRight = (InternalEObject) packageRight;
			packageRight = (org.eclipse.gmt.modisco.java.Package) eResolveProxy(oldPackageRight);
			if (packageRight != oldPackageRight) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							Java2KDMDiffPackage.PACKAGE_DELETE__PACKAGE_RIGHT,
							oldPackageRight, packageRight));
			}
		}
		return packageRight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.gmt.modisco.java.Package basicGetPackageRight() {
		return packageRight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageRight(
			org.eclipse.gmt.modisco.java.Package newPackageRight) {
		org.eclipse.gmt.modisco.java.Package oldPackageRight = packageRight;
		packageRight = newPackageRight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Java2KDMDiffPackage.PACKAGE_DELETE__PACKAGE_RIGHT,
					oldPackageRight, packageRight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.gmt.modisco.java.Package getLeftContainer() {
		if (leftContainer != null && leftContainer.eIsProxy()) {
			InternalEObject oldLeftContainer = (InternalEObject) leftContainer;
			leftContainer = (org.eclipse.gmt.modisco.java.Package) eResolveProxy(oldLeftContainer);
			if (leftContainer != oldLeftContainer) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							Java2KDMDiffPackage.PACKAGE_DELETE__LEFT_CONTAINER,
							oldLeftContainer, leftContainer));
			}
		}
		return leftContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.gmt.modisco.java.Package basicGetLeftContainer() {
		return leftContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftContainer(
			org.eclipse.gmt.modisco.java.Package newLeftContainer) {
		org.eclipse.gmt.modisco.java.Package oldLeftContainer = leftContainer;
		leftContainer = newLeftContainer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Java2KDMDiffPackage.PACKAGE_DELETE__LEFT_CONTAINER,
					oldLeftContainer, leftContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Java2KDMDiffPackage.PACKAGE_DELETE__PACKAGE_RIGHT:
			if (resolve)
				return getPackageRight();
			return basicGetPackageRight();
		case Java2KDMDiffPackage.PACKAGE_DELETE__LEFT_CONTAINER:
			if (resolve)
				return getLeftContainer();
			return basicGetLeftContainer();
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
		case Java2KDMDiffPackage.PACKAGE_DELETE__PACKAGE_RIGHT:
			setPackageRight((org.eclipse.gmt.modisco.java.Package) newValue);
			return;
		case Java2KDMDiffPackage.PACKAGE_DELETE__LEFT_CONTAINER:
			setLeftContainer((org.eclipse.gmt.modisco.java.Package) newValue);
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
		case Java2KDMDiffPackage.PACKAGE_DELETE__PACKAGE_RIGHT:
			setPackageRight((org.eclipse.gmt.modisco.java.Package) null);
			return;
		case Java2KDMDiffPackage.PACKAGE_DELETE__LEFT_CONTAINER:
			setLeftContainer((org.eclipse.gmt.modisco.java.Package) null);
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
		case Java2KDMDiffPackage.PACKAGE_DELETE__PACKAGE_RIGHT:
			return packageRight != null;
		case Java2KDMDiffPackage.PACKAGE_DELETE__LEFT_CONTAINER:
			return leftContainer != null;
		}
		return super.eIsSet(featureID);
	}

} //PackageDeleteImpl
