/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.modisco.java.diffing.java2kdmdiff.MethodInsert;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Insert</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.MethodInsertImpl#getMethodLeft <em>Method Left</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodInsertImpl extends MethodChangeImpl implements MethodInsert {
	/**
	 * The cached value of the '{@link #getMethodLeft() <em>Method Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodLeft()
	 * @generated
	 * @ordered
	 */
	protected AbstractMethodDeclaration methodLeft;

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
		return Java2KDMDiffPackage.Literals.METHOD_INSERT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractMethodDeclaration getMethodLeft() {
		if (methodLeft != null && methodLeft.eIsProxy()) {
			InternalEObject oldMethodLeft = (InternalEObject) methodLeft;
			methodLeft = (AbstractMethodDeclaration) eResolveProxy(oldMethodLeft);
			if (methodLeft != oldMethodLeft) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							Java2KDMDiffPackage.METHOD_INSERT__METHOD_LEFT,
							oldMethodLeft, methodLeft));
			}
		}
		return methodLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractMethodDeclaration basicGetMethodLeft() {
		return methodLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodLeft(AbstractMethodDeclaration newMethodLeft) {
		AbstractMethodDeclaration oldMethodLeft = methodLeft;
		methodLeft = newMethodLeft;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Java2KDMDiffPackage.METHOD_INSERT__METHOD_LEFT,
					oldMethodLeft, methodLeft));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Java2KDMDiffPackage.METHOD_INSERT__METHOD_LEFT:
			if (resolve)
				return getMethodLeft();
			return basicGetMethodLeft();
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
		case Java2KDMDiffPackage.METHOD_INSERT__METHOD_LEFT:
			setMethodLeft((AbstractMethodDeclaration) newValue);
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
		case Java2KDMDiffPackage.METHOD_INSERT__METHOD_LEFT:
			setMethodLeft((AbstractMethodDeclaration) null);
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
		case Java2KDMDiffPackage.METHOD_INSERT__METHOD_LEFT:
			return methodLeft != null;
		}
		return super.eIsSet(featureID);
	}

} //MethodInsertImpl
