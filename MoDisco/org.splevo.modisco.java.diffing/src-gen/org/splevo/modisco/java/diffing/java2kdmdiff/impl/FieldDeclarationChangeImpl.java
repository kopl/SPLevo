/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldDeclarationChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field Declaration Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.FieldDeclarationChangeImpl#getFieldLeft <em>Field Left</em>}</li>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.FieldDeclarationChangeImpl#getFieldRight <em>Field Right</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FieldDeclarationChangeImpl extends FieldChangeImpl implements
		FieldDeclarationChange {
	/**
	 * The cached value of the '{@link #getFieldLeft() <em>Field Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldLeft()
	 * @generated
	 * @ordered
	 */
	protected FieldDeclaration fieldLeft;

	/**
	 * The cached value of the '{@link #getFieldRight() <em>Field Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldRight()
	 * @generated
	 * @ordered
	 */
	protected FieldDeclaration fieldRight;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FieldDeclarationChangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Java2KDMDiffPackage.Literals.FIELD_DECLARATION_CHANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldDeclaration getFieldLeft() {
		if (fieldLeft != null && fieldLeft.eIsProxy()) {
			InternalEObject oldFieldLeft = (InternalEObject) fieldLeft;
			fieldLeft = (FieldDeclaration) eResolveProxy(oldFieldLeft);
			if (fieldLeft != oldFieldLeft) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(
							this,
							Notification.RESOLVE,
							Java2KDMDiffPackage.FIELD_DECLARATION_CHANGE__FIELD_LEFT,
							oldFieldLeft, fieldLeft));
			}
		}
		return fieldLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldDeclaration basicGetFieldLeft() {
		return fieldLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFieldLeft(FieldDeclaration newFieldLeft) {
		FieldDeclaration oldFieldLeft = fieldLeft;
		fieldLeft = newFieldLeft;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Java2KDMDiffPackage.FIELD_DECLARATION_CHANGE__FIELD_LEFT,
					oldFieldLeft, fieldLeft));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldDeclaration getFieldRight() {
		if (fieldRight != null && fieldRight.eIsProxy()) {
			InternalEObject oldFieldRight = (InternalEObject) fieldRight;
			fieldRight = (FieldDeclaration) eResolveProxy(oldFieldRight);
			if (fieldRight != oldFieldRight) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(
							this,
							Notification.RESOLVE,
							Java2KDMDiffPackage.FIELD_DECLARATION_CHANGE__FIELD_RIGHT,
							oldFieldRight, fieldRight));
			}
		}
		return fieldRight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldDeclaration basicGetFieldRight() {
		return fieldRight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFieldRight(FieldDeclaration newFieldRight) {
		FieldDeclaration oldFieldRight = fieldRight;
		fieldRight = newFieldRight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Java2KDMDiffPackage.FIELD_DECLARATION_CHANGE__FIELD_RIGHT,
					oldFieldRight, fieldRight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Java2KDMDiffPackage.FIELD_DECLARATION_CHANGE__FIELD_LEFT:
			if (resolve)
				return getFieldLeft();
			return basicGetFieldLeft();
		case Java2KDMDiffPackage.FIELD_DECLARATION_CHANGE__FIELD_RIGHT:
			if (resolve)
				return getFieldRight();
			return basicGetFieldRight();
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
		case Java2KDMDiffPackage.FIELD_DECLARATION_CHANGE__FIELD_LEFT:
			setFieldLeft((FieldDeclaration) newValue);
			return;
		case Java2KDMDiffPackage.FIELD_DECLARATION_CHANGE__FIELD_RIGHT:
			setFieldRight((FieldDeclaration) newValue);
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
		case Java2KDMDiffPackage.FIELD_DECLARATION_CHANGE__FIELD_LEFT:
			setFieldLeft((FieldDeclaration) null);
			return;
		case Java2KDMDiffPackage.FIELD_DECLARATION_CHANGE__FIELD_RIGHT:
			setFieldRight((FieldDeclaration) null);
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
		case Java2KDMDiffPackage.FIELD_DECLARATION_CHANGE__FIELD_LEFT:
			return fieldLeft != null;
		case Java2KDMDiffPackage.FIELD_DECLARATION_CHANGE__FIELD_RIGHT:
			return fieldRight != null;
		}
		return super.eIsSet(featureID);
	}

} //FieldDeclarationChangeImpl
