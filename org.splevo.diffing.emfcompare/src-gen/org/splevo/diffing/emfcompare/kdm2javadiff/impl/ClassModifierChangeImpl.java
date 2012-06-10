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

import org.eclipse.gmt.modisco.java.ClassDeclaration;

import org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.ClassModifierChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Modifier Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassModifierChangeImpl#getClassLeft <em>Class Left</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassModifierChangeImpl#getClassRight <em>Class Right</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassModifierChangeImpl#getClassChange <em>Class Change</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassModifierChangeImpl extends KDM2JavaDiffExtensionImpl implements ClassModifierChange {
	/**
	 * The cached value of the '{@link #getClassLeft() <em>Class Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassLeft()
	 * @generated
	 * @ordered
	 */
	protected ClassDeclaration classLeft;

	/**
	 * The cached value of the '{@link #getClassRight() <em>Class Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassRight()
	 * @generated
	 * @ordered
	 */
	protected ClassDeclaration classRight;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassModifierChangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KDM2JavaDiffPackage.Literals.CLASS_MODIFIER_CHANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassDeclaration getClassLeft() {
		if (classLeft != null && classLeft.eIsProxy()) {
			InternalEObject oldClassLeft = (InternalEObject)classLeft;
			classLeft = (ClassDeclaration)eResolveProxy(oldClassLeft);
			if (classLeft != oldClassLeft) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_LEFT, oldClassLeft, classLeft));
			}
		}
		return classLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassDeclaration basicGetClassLeft() {
		return classLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassLeft(ClassDeclaration newClassLeft) {
		ClassDeclaration oldClassLeft = classLeft;
		classLeft = newClassLeft;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_LEFT, oldClassLeft, classLeft));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassDeclaration getClassRight() {
		if (classRight != null && classRight.eIsProxy()) {
			InternalEObject oldClassRight = (InternalEObject)classRight;
			classRight = (ClassDeclaration)eResolveProxy(oldClassRight);
			if (classRight != oldClassRight) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_RIGHT, oldClassRight, classRight));
			}
		}
		return classRight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassDeclaration basicGetClassRight() {
		return classRight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassRight(ClassDeclaration newClassRight) {
		ClassDeclaration oldClassRight = classRight;
		classRight = newClassRight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_RIGHT, oldClassRight, classRight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassChange getClassChange() {
		if (eContainerFeatureID() != KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_CHANGE) return null;
		return (ClassChange)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetClassChange(ClassChange newClassChange, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newClassChange, KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_CHANGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassChange(ClassChange newClassChange) {
		if (newClassChange != eInternalContainer() || (eContainerFeatureID() != KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_CHANGE && newClassChange != null)) {
			if (EcoreUtil.isAncestor(this, newClassChange))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newClassChange != null)
				msgs = ((InternalEObject)newClassChange).eInverseAdd(this, KDM2JavaDiffPackage.CLASS_CHANGE__CLASS_DECLARACTION_CHANGES, ClassChange.class, msgs);
			msgs = basicSetClassChange(newClassChange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_CHANGE, newClassChange, newClassChange));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_CHANGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetClassChange((ClassChange)otherEnd, msgs);
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
			case KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_CHANGE:
				return basicSetClassChange(null, msgs);
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
			case KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_CHANGE:
				return eInternalContainer().eInverseRemove(this, KDM2JavaDiffPackage.CLASS_CHANGE__CLASS_DECLARACTION_CHANGES, ClassChange.class, msgs);
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
			case KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_LEFT:
				if (resolve) return getClassLeft();
				return basicGetClassLeft();
			case KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_RIGHT:
				if (resolve) return getClassRight();
				return basicGetClassRight();
			case KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_CHANGE:
				return getClassChange();
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
			case KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_LEFT:
				setClassLeft((ClassDeclaration)newValue);
				return;
			case KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_RIGHT:
				setClassRight((ClassDeclaration)newValue);
				return;
			case KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_CHANGE:
				setClassChange((ClassChange)newValue);
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
			case KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_LEFT:
				setClassLeft((ClassDeclaration)null);
				return;
			case KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_RIGHT:
				setClassRight((ClassDeclaration)null);
				return;
			case KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_CHANGE:
				setClassChange((ClassChange)null);
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
			case KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_LEFT:
				return classLeft != null;
			case KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_RIGHT:
				return classRight != null;
			case KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE__CLASS_CHANGE:
				return getClassChange() != null;
		}
		return super.eIsSet(featureID);
	}

} //ClassModifierChangeImpl
