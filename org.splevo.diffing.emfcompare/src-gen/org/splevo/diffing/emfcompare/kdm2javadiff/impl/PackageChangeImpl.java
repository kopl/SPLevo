/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.compare.diff.metamodel.impl.DiffGroupImpl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage;
import org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.PackageChangeImpl#getCompilationUnitChanges <em>Compilation Unit Changes</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.PackageChangeImpl#getSubPackages <em>Sub Packages</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.PackageChangeImpl#getPackageLeft <em>Package Left</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.PackageChangeImpl#getPackageRight <em>Package Right</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageChangeImpl extends DiffGroupImpl implements PackageChange {
	/**
	 * The cached value of the '{@link #getCompilationUnitChanges() <em>Compilation Unit Changes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompilationUnitChanges()
	 * @generated
	 * @ordered
	 */
	protected EList<CompilationUnitChange> compilationUnitChanges;

	/**
	 * The cached value of the '{@link #getSubPackages() <em>Sub Packages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubPackages()
	 * @generated
	 * @ordered
	 */
	protected EList<PackageChange> subPackages;

	/**
	 * The cached value of the '{@link #getPackageLeft() <em>Package Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageLeft()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.gmt.modisco.java.Package packageLeft;

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
		return KDM2JavaDiffPackage.Literals.PACKAGE_CHANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CompilationUnitChange> getCompilationUnitChanges() {
		if (compilationUnitChanges == null) {
			compilationUnitChanges = new EObjectContainmentWithInverseEList<CompilationUnitChange>(CompilationUnitChange.class, this, KDM2JavaDiffPackage.PACKAGE_CHANGE__COMPILATION_UNIT_CHANGES, KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__PACKAGE_CHANGE);
		}
		return compilationUnitChanges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PackageChange> getSubPackages() {
		if (subPackages == null) {
			subPackages = new EObjectContainmentEList<PackageChange>(PackageChange.class, this, KDM2JavaDiffPackage.PACKAGE_CHANGE__SUB_PACKAGES);
		}
		return subPackages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.gmt.modisco.java.Package getPackageLeft() {
		if (packageLeft != null && packageLeft.eIsProxy()) {
			InternalEObject oldPackageLeft = (InternalEObject)packageLeft;
			packageLeft = (org.eclipse.gmt.modisco.java.Package)eResolveProxy(oldPackageLeft);
			if (packageLeft != oldPackageLeft) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KDM2JavaDiffPackage.PACKAGE_CHANGE__PACKAGE_LEFT, oldPackageLeft, packageLeft));
			}
		}
		return packageLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.gmt.modisco.java.Package basicGetPackageLeft() {
		return packageLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageLeft(org.eclipse.gmt.modisco.java.Package newPackageLeft) {
		org.eclipse.gmt.modisco.java.Package oldPackageLeft = packageLeft;
		packageLeft = newPackageLeft;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.PACKAGE_CHANGE__PACKAGE_LEFT, oldPackageLeft, packageLeft));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.gmt.modisco.java.Package getPackageRight() {
		if (packageRight != null && packageRight.eIsProxy()) {
			InternalEObject oldPackageRight = (InternalEObject)packageRight;
			packageRight = (org.eclipse.gmt.modisco.java.Package)eResolveProxy(oldPackageRight);
			if (packageRight != oldPackageRight) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KDM2JavaDiffPackage.PACKAGE_CHANGE__PACKAGE_RIGHT, oldPackageRight, packageRight));
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
	public void setPackageRight(org.eclipse.gmt.modisco.java.Package newPackageRight) {
		org.eclipse.gmt.modisco.java.Package oldPackageRight = packageRight;
		packageRight = newPackageRight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.PACKAGE_CHANGE__PACKAGE_RIGHT, oldPackageRight, packageRight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__COMPILATION_UNIT_CHANGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCompilationUnitChanges()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__COMPILATION_UNIT_CHANGES:
				return ((InternalEList<?>)getCompilationUnitChanges()).basicRemove(otherEnd, msgs);
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__SUB_PACKAGES:
				return ((InternalEList<?>)getSubPackages()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__COMPILATION_UNIT_CHANGES:
				return getCompilationUnitChanges();
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__SUB_PACKAGES:
				return getSubPackages();
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__PACKAGE_LEFT:
				if (resolve) return getPackageLeft();
				return basicGetPackageLeft();
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__PACKAGE_RIGHT:
				if (resolve) return getPackageRight();
				return basicGetPackageRight();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__COMPILATION_UNIT_CHANGES:
				getCompilationUnitChanges().clear();
				getCompilationUnitChanges().addAll((Collection<? extends CompilationUnitChange>)newValue);
				return;
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__SUB_PACKAGES:
				getSubPackages().clear();
				getSubPackages().addAll((Collection<? extends PackageChange>)newValue);
				return;
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__PACKAGE_LEFT:
				setPackageLeft((org.eclipse.gmt.modisco.java.Package)newValue);
				return;
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__PACKAGE_RIGHT:
				setPackageRight((org.eclipse.gmt.modisco.java.Package)newValue);
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
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__COMPILATION_UNIT_CHANGES:
				getCompilationUnitChanges().clear();
				return;
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__SUB_PACKAGES:
				getSubPackages().clear();
				return;
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__PACKAGE_LEFT:
				setPackageLeft((org.eclipse.gmt.modisco.java.Package)null);
				return;
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__PACKAGE_RIGHT:
				setPackageRight((org.eclipse.gmt.modisco.java.Package)null);
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
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__COMPILATION_UNIT_CHANGES:
				return compilationUnitChanges != null && !compilationUnitChanges.isEmpty();
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__SUB_PACKAGES:
				return subPackages != null && !subPackages.isEmpty();
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__PACKAGE_LEFT:
				return packageLeft != null;
			case KDM2JavaDiffPackage.PACKAGE_CHANGE__PACKAGE_RIGHT:
				return packageRight != null;
		}
		return super.eIsSet(featureID);
	}

} //PackageChangeImpl
