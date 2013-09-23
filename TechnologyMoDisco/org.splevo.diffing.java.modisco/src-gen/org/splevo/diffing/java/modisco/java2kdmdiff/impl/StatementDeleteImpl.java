/**
 */
package org.splevo.diffing.java.modisco.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmt.modisco.java.ASTNode;

import org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.diffing.java.modisco.java2kdmdiff.StatementDelete;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Statement Delete</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.diffing.java.modisco.java2kdmdiff.impl.StatementDeleteImpl#getLeftContainer <em>Left Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StatementDeleteImpl extends StatementChangeImpl implements
		StatementDelete {
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
	protected StatementDeleteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Java2KDMDiffPackage.Literals.STATEMENT_DELETE;
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
					eNotify(new ENotificationImpl(
							this,
							Notification.RESOLVE,
							Java2KDMDiffPackage.STATEMENT_DELETE__LEFT_CONTAINER,
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
					Java2KDMDiffPackage.STATEMENT_DELETE__LEFT_CONTAINER,
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
		case Java2KDMDiffPackage.STATEMENT_DELETE__LEFT_CONTAINER:
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
		case Java2KDMDiffPackage.STATEMENT_DELETE__LEFT_CONTAINER:
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
		case Java2KDMDiffPackage.STATEMENT_DELETE__LEFT_CONTAINER:
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
		case Java2KDMDiffPackage.STATEMENT_DELETE__LEFT_CONTAINER:
			return leftContainer != null;
		}
		return super.eIsSet(featureID);
	}

} //StatementDeleteImpl
