/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.FieldDeclaration;

import org.splevo.modisco.java.diffing.java2kdmdiff.FieldDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field Delete</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.FieldDeleteImpl#getFieldRight <em>Field Right</em>}</li>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.FieldDeleteImpl#getLeftContainer <em>Left Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FieldDeleteImpl extends FieldChangeImpl implements FieldDelete {
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
	 * The cached value of the '{@link #getLeftContainer() <em>Left Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftContainer()
	 * @generated
	 * @ordered
	 */
	protected ASTNode leftContainer;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FieldDeleteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Java2KDMDiffPackage.Literals.FIELD_DELETE;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							Java2KDMDiffPackage.FIELD_DELETE__FIELD_RIGHT,
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
					Java2KDMDiffPackage.FIELD_DELETE__FIELD_RIGHT,
					oldFieldRight, fieldRight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASTNode getLeftContainer() {
		if (leftContainer != null && leftContainer.eIsProxy()) {
			InternalEObject oldLeftContainer = (InternalEObject) leftContainer;
			leftContainer = (ASTNode) eResolveProxy(oldLeftContainer);
			if (leftContainer != oldLeftContainer) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							Java2KDMDiffPackage.FIELD_DELETE__LEFT_CONTAINER,
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
	public ASTNode basicGetLeftContainer() {
		return leftContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftContainer(ASTNode newLeftContainer) {
		ASTNode oldLeftContainer = leftContainer;
		leftContainer = newLeftContainer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Java2KDMDiffPackage.FIELD_DELETE__LEFT_CONTAINER,
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
		case Java2KDMDiffPackage.FIELD_DELETE__FIELD_RIGHT:
			if (resolve)
				return getFieldRight();
			return basicGetFieldRight();
		case Java2KDMDiffPackage.FIELD_DELETE__LEFT_CONTAINER:
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
		case Java2KDMDiffPackage.FIELD_DELETE__FIELD_RIGHT:
			setFieldRight((FieldDeclaration) newValue);
			return;
		case Java2KDMDiffPackage.FIELD_DELETE__LEFT_CONTAINER:
			setLeftContainer((ASTNode) newValue);
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
		case Java2KDMDiffPackage.FIELD_DELETE__FIELD_RIGHT:
			setFieldRight((FieldDeclaration) null);
			return;
		case Java2KDMDiffPackage.FIELD_DELETE__LEFT_CONTAINER:
			setLeftContainer((ASTNode) null);
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
		case Java2KDMDiffPackage.FIELD_DELETE__FIELD_RIGHT:
			return fieldRight != null;
		case Java2KDMDiffPackage.FIELD_DELETE__LEFT_CONTAINER:
			return leftContainer != null;
		}
		return super.eIsSet(featureID);
	}

} //FieldDeleteImpl