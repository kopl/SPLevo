/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.gmt.modisco.java.MethodDeclaration;

import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodInsert;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Insert</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodInsertImpl#getMethodChange <em>Method Change</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodInsertImpl#getMethodDeclarationLeft <em>Method Declaration Left</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodInsertImpl#getMethodDeclarationRight <em>Method Declaration Right</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodInsertImpl extends KDM2JavaDiffExtensionImpl implements MethodInsert {
	/**
	 * The cached value of the '{@link #getMethodDeclarationLeft() <em>Method Declaration Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodDeclarationLeft()
	 * @generated
	 * @ordered
	 */
	protected MethodDeclaration methodDeclarationLeft;

	/**
	 * The cached value of the '{@link #getMethodDeclarationRight() <em>Method Declaration Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodDeclarationRight()
	 * @generated
	 * @ordered
	 */
	protected MethodDeclaration methodDeclarationRight;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodInsertImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KDM2JavaDiffPackage.Literals.METHOD_INSERT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodChange getMethodChange() {
		if (eContainerFeatureID() != KDM2JavaDiffPackage.METHOD_INSERT__METHOD_CHANGE) return null;
		return (MethodChange)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMethodChange(MethodChange newMethodChange, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newMethodChange, KDM2JavaDiffPackage.METHOD_INSERT__METHOD_CHANGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodChange(MethodChange newMethodChange) {
		if (newMethodChange != eInternalContainer() || (eContainerFeatureID() != KDM2JavaDiffPackage.METHOD_INSERT__METHOD_CHANGE && newMethodChange != null)) {
			if (EcoreUtil.isAncestor(this, newMethodChange))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newMethodChange != null)
				msgs = ((InternalEObject)newMethodChange).eInverseAdd(this, KDM2JavaDiffPackage.METHOD_CHANGE__METHOD_DECLARATION_CHANGE, MethodChange.class, msgs);
			msgs = basicSetMethodChange(newMethodChange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.METHOD_INSERT__METHOD_CHANGE, newMethodChange, newMethodChange));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodDeclaration getMethodDeclarationLeft() {
		if (methodDeclarationLeft != null && methodDeclarationLeft.eIsProxy()) {
			InternalEObject oldMethodDeclarationLeft = (InternalEObject)methodDeclarationLeft;
			methodDeclarationLeft = (MethodDeclaration)eResolveProxy(oldMethodDeclarationLeft);
			if (methodDeclarationLeft != oldMethodDeclarationLeft) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KDM2JavaDiffPackage.METHOD_INSERT__METHOD_DECLARATION_LEFT, oldMethodDeclarationLeft, methodDeclarationLeft));
			}
		}
		return methodDeclarationLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodDeclaration basicGetMethodDeclarationLeft() {
		return methodDeclarationLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodDeclarationLeft(MethodDeclaration newMethodDeclarationLeft) {
		MethodDeclaration oldMethodDeclarationLeft = methodDeclarationLeft;
		methodDeclarationLeft = newMethodDeclarationLeft;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.METHOD_INSERT__METHOD_DECLARATION_LEFT, oldMethodDeclarationLeft, methodDeclarationLeft));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodDeclaration getMethodDeclarationRight() {
		if (methodDeclarationRight != null && methodDeclarationRight.eIsProxy()) {
			InternalEObject oldMethodDeclarationRight = (InternalEObject)methodDeclarationRight;
			methodDeclarationRight = (MethodDeclaration)eResolveProxy(oldMethodDeclarationRight);
			if (methodDeclarationRight != oldMethodDeclarationRight) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KDM2JavaDiffPackage.METHOD_INSERT__METHOD_DECLARATION_RIGHT, oldMethodDeclarationRight, methodDeclarationRight));
			}
		}
		return methodDeclarationRight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodDeclaration basicGetMethodDeclarationRight() {
		return methodDeclarationRight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodDeclarationRight(MethodDeclaration newMethodDeclarationRight) {
		MethodDeclaration oldMethodDeclarationRight = methodDeclarationRight;
		methodDeclarationRight = newMethodDeclarationRight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.METHOD_INSERT__METHOD_DECLARATION_RIGHT, oldMethodDeclarationRight, methodDeclarationRight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KDM2JavaDiffPackage.METHOD_INSERT__METHOD_CHANGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetMethodChange((MethodChange)otherEnd, msgs);
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
			case KDM2JavaDiffPackage.METHOD_INSERT__METHOD_CHANGE:
				return basicSetMethodChange(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case KDM2JavaDiffPackage.METHOD_INSERT__METHOD_CHANGE:
				return eInternalContainer().eInverseRemove(this, KDM2JavaDiffPackage.METHOD_CHANGE__METHOD_DECLARATION_CHANGE, MethodChange.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case KDM2JavaDiffPackage.METHOD_INSERT__METHOD_CHANGE:
				return getMethodChange();
			case KDM2JavaDiffPackage.METHOD_INSERT__METHOD_DECLARATION_LEFT:
				if (resolve) return getMethodDeclarationLeft();
				return basicGetMethodDeclarationLeft();
			case KDM2JavaDiffPackage.METHOD_INSERT__METHOD_DECLARATION_RIGHT:
				if (resolve) return getMethodDeclarationRight();
				return basicGetMethodDeclarationRight();
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
			case KDM2JavaDiffPackage.METHOD_INSERT__METHOD_CHANGE:
				setMethodChange((MethodChange)newValue);
				return;
			case KDM2JavaDiffPackage.METHOD_INSERT__METHOD_DECLARATION_LEFT:
				setMethodDeclarationLeft((MethodDeclaration)newValue);
				return;
			case KDM2JavaDiffPackage.METHOD_INSERT__METHOD_DECLARATION_RIGHT:
				setMethodDeclarationRight((MethodDeclaration)newValue);
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
			case KDM2JavaDiffPackage.METHOD_INSERT__METHOD_CHANGE:
				setMethodChange((MethodChange)null);
				return;
			case KDM2JavaDiffPackage.METHOD_INSERT__METHOD_DECLARATION_LEFT:
				setMethodDeclarationLeft((MethodDeclaration)null);
				return;
			case KDM2JavaDiffPackage.METHOD_INSERT__METHOD_DECLARATION_RIGHT:
				setMethodDeclarationRight((MethodDeclaration)null);
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
			case KDM2JavaDiffPackage.METHOD_INSERT__METHOD_CHANGE:
				return getMethodChange() != null;
			case KDM2JavaDiffPackage.METHOD_INSERT__METHOD_DECLARATION_LEFT:
				return methodDeclarationLeft != null;
			case KDM2JavaDiffPackage.METHOD_INSERT__METHOD_DECLARATION_RIGHT:
				return methodDeclarationRight != null;
		}
		return super.eIsSet(featureID);
	}

} //MethodInsertImpl
