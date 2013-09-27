/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmt.modisco.java.ClassDeclaration;

import org.splevo.modisco.java.diffing.java2kdmdiff.ClassDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Delete</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.ClassDeleteImpl#getClassRight <em>Class Right</em>}</li>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.ClassDeleteImpl#getLeftContainer <em>Left Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassDeleteImpl extends ClassChangeImpl implements ClassDelete {
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
	protected ClassDeleteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Java2KDMDiffPackage.Literals.CLASS_DELETE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassDeclaration getClassRight() {
		if (classRight != null && classRight.eIsProxy()) {
			InternalEObject oldClassRight = (InternalEObject) classRight;
			classRight = (ClassDeclaration) eResolveProxy(oldClassRight);
			if (classRight != oldClassRight) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							Java2KDMDiffPackage.CLASS_DELETE__CLASS_RIGHT,
							oldClassRight, classRight));
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					Java2KDMDiffPackage.CLASS_DELETE__CLASS_RIGHT,
					oldClassRight, classRight));
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
							Java2KDMDiffPackage.CLASS_DELETE__LEFT_CONTAINER,
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
					Java2KDMDiffPackage.CLASS_DELETE__LEFT_CONTAINER,
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
		case Java2KDMDiffPackage.CLASS_DELETE__CLASS_RIGHT:
			if (resolve)
				return getClassRight();
			return basicGetClassRight();
		case Java2KDMDiffPackage.CLASS_DELETE__LEFT_CONTAINER:
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
		case Java2KDMDiffPackage.CLASS_DELETE__CLASS_RIGHT:
			setClassRight((ClassDeclaration) newValue);
			return;
		case Java2KDMDiffPackage.CLASS_DELETE__LEFT_CONTAINER:
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
		case Java2KDMDiffPackage.CLASS_DELETE__CLASS_RIGHT:
			setClassRight((ClassDeclaration) null);
			return;
		case Java2KDMDiffPackage.CLASS_DELETE__LEFT_CONTAINER:
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
		case Java2KDMDiffPackage.CLASS_DELETE__CLASS_RIGHT:
			return classRight != null;
		case Java2KDMDiffPackage.CLASS_DELETE__LEFT_CONTAINER:
			return leftContainer != null;
		}
		return super.eIsSet(featureID);
	}

} //ClassDeleteImpl
