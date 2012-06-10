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

import org.eclipse.gmt.modisco.java.Statement;

import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.StatementInsert;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Statement Insert</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementInsertImpl#getStatementLeft <em>Statement Left</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementInsertImpl#getStatementRight <em>Statement Right</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.StatementInsertImpl#getMethodChange <em>Method Change</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StatementInsertImpl extends KDM2JavaDiffExtensionImpl implements StatementInsert {
	/**
	 * The cached value of the '{@link #getStatementLeft() <em>Statement Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatementLeft()
	 * @generated
	 * @ordered
	 */
	protected Statement statementLeft;

	/**
	 * The cached value of the '{@link #getStatementRight() <em>Statement Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatementRight()
	 * @generated
	 * @ordered
	 */
	protected Statement statementRight;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StatementInsertImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KDM2JavaDiffPackage.Literals.STATEMENT_INSERT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement getStatementLeft() {
		if (statementLeft != null && statementLeft.eIsProxy()) {
			InternalEObject oldStatementLeft = (InternalEObject)statementLeft;
			statementLeft = (Statement)eResolveProxy(oldStatementLeft);
			if (statementLeft != oldStatementLeft) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KDM2JavaDiffPackage.STATEMENT_INSERT__STATEMENT_LEFT, oldStatementLeft, statementLeft));
			}
		}
		return statementLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement basicGetStatementLeft() {
		return statementLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatementLeft(Statement newStatementLeft) {
		Statement oldStatementLeft = statementLeft;
		statementLeft = newStatementLeft;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.STATEMENT_INSERT__STATEMENT_LEFT, oldStatementLeft, statementLeft));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement getStatementRight() {
		if (statementRight != null && statementRight.eIsProxy()) {
			InternalEObject oldStatementRight = (InternalEObject)statementRight;
			statementRight = (Statement)eResolveProxy(oldStatementRight);
			if (statementRight != oldStatementRight) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KDM2JavaDiffPackage.STATEMENT_INSERT__STATEMENT_RIGHT, oldStatementRight, statementRight));
			}
		}
		return statementRight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement basicGetStatementRight() {
		return statementRight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatementRight(Statement newStatementRight) {
		Statement oldStatementRight = statementRight;
		statementRight = newStatementRight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.STATEMENT_INSERT__STATEMENT_RIGHT, oldStatementRight, statementRight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodChange getMethodChange() {
		if (eContainerFeatureID() != KDM2JavaDiffPackage.STATEMENT_INSERT__METHOD_CHANGE) return null;
		return (MethodChange)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMethodChange(MethodChange newMethodChange, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newMethodChange, KDM2JavaDiffPackage.STATEMENT_INSERT__METHOD_CHANGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodChange(MethodChange newMethodChange) {
		if (newMethodChange != eInternalContainer() || (eContainerFeatureID() != KDM2JavaDiffPackage.STATEMENT_INSERT__METHOD_CHANGE && newMethodChange != null)) {
			if (EcoreUtil.isAncestor(this, newMethodChange))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newMethodChange != null)
				msgs = ((InternalEObject)newMethodChange).eInverseAdd(this, KDM2JavaDiffPackage.METHOD_CHANGE__STATEMENT_CHANGES, MethodChange.class, msgs);
			msgs = basicSetMethodChange(newMethodChange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.STATEMENT_INSERT__METHOD_CHANGE, newMethodChange, newMethodChange));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KDM2JavaDiffPackage.STATEMENT_INSERT__METHOD_CHANGE:
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
			case KDM2JavaDiffPackage.STATEMENT_INSERT__METHOD_CHANGE:
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
			case KDM2JavaDiffPackage.STATEMENT_INSERT__METHOD_CHANGE:
				return eInternalContainer().eInverseRemove(this, KDM2JavaDiffPackage.METHOD_CHANGE__STATEMENT_CHANGES, MethodChange.class, msgs);
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
			case KDM2JavaDiffPackage.STATEMENT_INSERT__STATEMENT_LEFT:
				if (resolve) return getStatementLeft();
				return basicGetStatementLeft();
			case KDM2JavaDiffPackage.STATEMENT_INSERT__STATEMENT_RIGHT:
				if (resolve) return getStatementRight();
				return basicGetStatementRight();
			case KDM2JavaDiffPackage.STATEMENT_INSERT__METHOD_CHANGE:
				return getMethodChange();
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
			case KDM2JavaDiffPackage.STATEMENT_INSERT__STATEMENT_LEFT:
				setStatementLeft((Statement)newValue);
				return;
			case KDM2JavaDiffPackage.STATEMENT_INSERT__STATEMENT_RIGHT:
				setStatementRight((Statement)newValue);
				return;
			case KDM2JavaDiffPackage.STATEMENT_INSERT__METHOD_CHANGE:
				setMethodChange((MethodChange)newValue);
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
			case KDM2JavaDiffPackage.STATEMENT_INSERT__STATEMENT_LEFT:
				setStatementLeft((Statement)null);
				return;
			case KDM2JavaDiffPackage.STATEMENT_INSERT__STATEMENT_RIGHT:
				setStatementRight((Statement)null);
				return;
			case KDM2JavaDiffPackage.STATEMENT_INSERT__METHOD_CHANGE:
				setMethodChange((MethodChange)null);
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
			case KDM2JavaDiffPackage.STATEMENT_INSERT__STATEMENT_LEFT:
				return statementLeft != null;
			case KDM2JavaDiffPackage.STATEMENT_INSERT__STATEMENT_RIGHT:
				return statementRight != null;
			case KDM2JavaDiffPackage.STATEMENT_INSERT__METHOD_CHANGE:
				return getMethodChange() != null;
		}
		return super.eIsSet(featureID);
	}

} //StatementInsertImpl
