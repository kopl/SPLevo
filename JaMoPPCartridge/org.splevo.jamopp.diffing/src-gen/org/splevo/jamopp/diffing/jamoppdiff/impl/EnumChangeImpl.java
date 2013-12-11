/**
 */
package org.splevo.jamopp.diffing.jamoppdiff.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.emftext.language.java.classifiers.Enumeration;

import org.splevo.jamopp.diffing.jamoppdiff.EnumChange;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.impl.EnumChangeImpl#getChangedEnum <em>Changed Enum</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumChangeImpl extends JaMoPPDiffImpl implements EnumChange {
	/**
	 * The cached value of the '{@link #getChangedEnum() <em>Changed Enum</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangedEnum()
	 * @generated
	 * @ordered
	 */
	protected Enumeration changedEnum;

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
		return JaMoPPDiffPackage.Literals.ENUM_CHANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enumeration getChangedEnum() {
		if (changedEnum != null && changedEnum.eIsProxy()) {
			InternalEObject oldChangedEnum = (InternalEObject) changedEnum;
			changedEnum = (Enumeration) eResolveProxy(oldChangedEnum);
			if (changedEnum != oldChangedEnum) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							JaMoPPDiffPackage.ENUM_CHANGE__CHANGED_ENUM,
							oldChangedEnum, changedEnum));
			}
		}
		return changedEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enumeration basicGetChangedEnum() {
		return changedEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChangedEnum(Enumeration newChangedEnum) {
		Enumeration oldChangedEnum = changedEnum;
		changedEnum = newChangedEnum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					JaMoPPDiffPackage.ENUM_CHANGE__CHANGED_ENUM,
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
		case JaMoPPDiffPackage.ENUM_CHANGE__CHANGED_ENUM:
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
		case JaMoPPDiffPackage.ENUM_CHANGE__CHANGED_ENUM:
			setChangedEnum((Enumeration) newValue);
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
		case JaMoPPDiffPackage.ENUM_CHANGE__CHANGED_ENUM:
			setChangedEnum((Enumeration) null);
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
		case JaMoPPDiffPackage.ENUM_CHANGE__CHANGED_ENUM:
			return changedEnum != null;
		}
		return super.eIsSet(featureID);
	}

} //EnumChangeImpl