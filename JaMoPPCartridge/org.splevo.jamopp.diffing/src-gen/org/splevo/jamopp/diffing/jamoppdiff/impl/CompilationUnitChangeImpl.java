/**
 */
package org.splevo.jamopp.diffing.jamoppdiff.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.emftext.language.java.containers.CompilationUnit;

import org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Compilation Unit Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.impl.CompilationUnitChangeImpl#getChangedCompilationUnit <em>Changed Compilation Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompilationUnitChangeImpl extends JaMoPPDiffImpl implements
		CompilationUnitChange {
	/**
	 * The cached value of the '{@link #getChangedCompilationUnit() <em>Changed Compilation Unit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangedCompilationUnit()
	 * @generated
	 * @ordered
	 */
	protected CompilationUnit changedCompilationUnit;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompilationUnitChangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JaMoPPDiffPackage.Literals.COMPILATION_UNIT_CHANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnit getChangedCompilationUnit() {
		if (changedCompilationUnit != null && changedCompilationUnit.eIsProxy()) {
			InternalEObject oldChangedCompilationUnit = (InternalEObject) changedCompilationUnit;
			changedCompilationUnit = (CompilationUnit) eResolveProxy(oldChangedCompilationUnit);
			if (changedCompilationUnit != oldChangedCompilationUnit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(
							this,
							Notification.RESOLVE,
							JaMoPPDiffPackage.COMPILATION_UNIT_CHANGE__CHANGED_COMPILATION_UNIT,
							oldChangedCompilationUnit, changedCompilationUnit));
			}
		}
		return changedCompilationUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnit basicGetChangedCompilationUnit() {
		return changedCompilationUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChangedCompilationUnit(
			CompilationUnit newChangedCompilationUnit) {
		CompilationUnit oldChangedCompilationUnit = changedCompilationUnit;
		changedCompilationUnit = newChangedCompilationUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
					this,
					Notification.SET,
					JaMoPPDiffPackage.COMPILATION_UNIT_CHANGE__CHANGED_COMPILATION_UNIT,
					oldChangedCompilationUnit, changedCompilationUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case JaMoPPDiffPackage.COMPILATION_UNIT_CHANGE__CHANGED_COMPILATION_UNIT:
			if (resolve)
				return getChangedCompilationUnit();
			return basicGetChangedCompilationUnit();
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
		case JaMoPPDiffPackage.COMPILATION_UNIT_CHANGE__CHANGED_COMPILATION_UNIT:
			setChangedCompilationUnit((CompilationUnit) newValue);
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
		case JaMoPPDiffPackage.COMPILATION_UNIT_CHANGE__CHANGED_COMPILATION_UNIT:
			setChangedCompilationUnit((CompilationUnit) null);
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
		case JaMoPPDiffPackage.COMPILATION_UNIT_CHANGE__CHANGED_COMPILATION_UNIT:
			return changedCompilationUnit != null;
		}
		return super.eIsSet(featureID);
	}

} //CompilationUnitChangeImpl
