/**
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.gmt.modisco.java.ImportDeclaration;

import org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Import Declaration Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ImportDeclarationChangeImpl#getCompilationUnitChange <em>Compilation Unit Change</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ImportDeclarationChangeImpl extends KDM2JavaDiffExtensionImpl implements ImportDeclarationChange {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImportDeclarationChangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KDM2JavaDiffPackage.Literals.IMPORT_DECLARATION_CHANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnitChange getCompilationUnitChange() {
		if (eContainerFeatureID() != KDM2JavaDiffPackage.IMPORT_DECLARATION_CHANGE__COMPILATION_UNIT_CHANGE) return null;
		return (CompilationUnitChange)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompilationUnitChange(CompilationUnitChange newCompilationUnitChange, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCompilationUnitChange, KDM2JavaDiffPackage.IMPORT_DECLARATION_CHANGE__COMPILATION_UNIT_CHANGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompilationUnitChange(CompilationUnitChange newCompilationUnitChange) {
		if (newCompilationUnitChange != eInternalContainer() || (eContainerFeatureID() != KDM2JavaDiffPackage.IMPORT_DECLARATION_CHANGE__COMPILATION_UNIT_CHANGE && newCompilationUnitChange != null)) {
			if (EcoreUtil.isAncestor(this, newCompilationUnitChange))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCompilationUnitChange != null)
				msgs = ((InternalEObject)newCompilationUnitChange).eInverseAdd(this, KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__IMPORT_DECLARATION_CHANGES, CompilationUnitChange.class, msgs);
			msgs = basicSetCompilationUnitChange(newCompilationUnitChange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.IMPORT_DECLARATION_CHANGE__COMPILATION_UNIT_CHANGE, newCompilationUnitChange, newCompilationUnitChange));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KDM2JavaDiffPackage.IMPORT_DECLARATION_CHANGE__COMPILATION_UNIT_CHANGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCompilationUnitChange((CompilationUnitChange)otherEnd, msgs);
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
			case KDM2JavaDiffPackage.IMPORT_DECLARATION_CHANGE__COMPILATION_UNIT_CHANGE:
				return basicSetCompilationUnitChange(null, msgs);
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
			case KDM2JavaDiffPackage.IMPORT_DECLARATION_CHANGE__COMPILATION_UNIT_CHANGE:
				return eInternalContainer().eInverseRemove(this, KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__IMPORT_DECLARATION_CHANGES, CompilationUnitChange.class, msgs);
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
			case KDM2JavaDiffPackage.IMPORT_DECLARATION_CHANGE__COMPILATION_UNIT_CHANGE:
				return getCompilationUnitChange();
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
			case KDM2JavaDiffPackage.IMPORT_DECLARATION_CHANGE__COMPILATION_UNIT_CHANGE:
				setCompilationUnitChange((CompilationUnitChange)newValue);
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
			case KDM2JavaDiffPackage.IMPORT_DECLARATION_CHANGE__COMPILATION_UNIT_CHANGE:
				setCompilationUnitChange((CompilationUnitChange)null);
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
			case KDM2JavaDiffPackage.IMPORT_DECLARATION_CHANGE__COMPILATION_UNIT_CHANGE:
				return getCompilationUnitChange() != null;
		}
		return super.eIsSet(featureID);
	}

} //ImportDeclarationChangeImpl
