/**
 */
package org.splevo.diffing.java.modisco.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmt.modisco.java.Statement;

import org.splevo.diffing.java.modisco.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.diffing.java.modisco.java2kdmdiff.StatementChange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Statement Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.diffing.java.modisco.java2kdmdiff.impl.StatementChangeImpl#getStatementRight <em>Statement Right</em>}</li>
 *   <li>{@link org.splevo.diffing.java.modisco.java2kdmdiff.impl.StatementChangeImpl#getStatementLeft <em>Statement Left</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StatementChangeImpl extends Java2KDMDiffExtensionImpl implements
		StatementChange {
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
	 * The cached value of the '{@link #getStatementLeft() <em>Statement Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatementLeft()
	 * @generated
	 * @ordered
	 */
	protected Statement statementLeft;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StatementChangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Java2KDMDiffPackage.Literals.STATEMENT_CHANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement getStatementRight() {
		if (statementRight != null && statementRight.eIsProxy()) {
			InternalEObject oldStatementRight = (InternalEObject) statementRight;
			statementRight = (Statement) eResolveProxy(oldStatementRight);
			if (statementRight != oldStatementRight) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(
							this,
							Notification.RESOLVE,
							Java2KDMDiffPackage.STATEMENT_CHANGE__STATEMENT_RIGHT,
							oldStatementRight, statementRight));
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					Java2KDMDiffPackage.STATEMENT_CHANGE__STATEMENT_RIGHT,
					oldStatementRight, statementRight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement getStatementLeft() {
		if (statementLeft != null && statementLeft.eIsProxy()) {
			InternalEObject oldStatementLeft = (InternalEObject) statementLeft;
			statementLeft = (Statement) eResolveProxy(oldStatementLeft);
			if (statementLeft != oldStatementLeft) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(
							this,
							Notification.RESOLVE,
							Java2KDMDiffPackage.STATEMENT_CHANGE__STATEMENT_LEFT,
							oldStatementLeft, statementLeft));
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					Java2KDMDiffPackage.STATEMENT_CHANGE__STATEMENT_LEFT,
					oldStatementLeft, statementLeft));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Java2KDMDiffPackage.STATEMENT_CHANGE__STATEMENT_RIGHT:
			if (resolve)
				return getStatementRight();
			return basicGetStatementRight();
		case Java2KDMDiffPackage.STATEMENT_CHANGE__STATEMENT_LEFT:
			if (resolve)
				return getStatementLeft();
			return basicGetStatementLeft();
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
		case Java2KDMDiffPackage.STATEMENT_CHANGE__STATEMENT_RIGHT:
			setStatementRight((Statement) newValue);
			return;
		case Java2KDMDiffPackage.STATEMENT_CHANGE__STATEMENT_LEFT:
			setStatementLeft((Statement) newValue);
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
		case Java2KDMDiffPackage.STATEMENT_CHANGE__STATEMENT_RIGHT:
			setStatementRight((Statement) null);
			return;
		case Java2KDMDiffPackage.STATEMENT_CHANGE__STATEMENT_LEFT:
			setStatementLeft((Statement) null);
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
		case Java2KDMDiffPackage.STATEMENT_CHANGE__STATEMENT_RIGHT:
			return statementRight != null;
		case Java2KDMDiffPackage.STATEMENT_CHANGE__STATEMENT_LEFT:
			return statementLeft != null;
		}
		return super.eIsSet(featureID);
	}

} //StatementChangeImpl
