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

import org.eclipse.gmt.modisco.java.ImportDeclaration;

import org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.ImportDelete;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Import Delete</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ImportDeleteImpl#getImportLeft <em>Import Left</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ImportDeleteImpl#getImportRight <em>Import Right</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ImportDeleteImpl#getCompilationUnitChange <em>Compilation Unit Change</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImportDeleteImpl extends KDM2JavaDiffExtensionImpl implements ImportDelete {
	/**
	 * The cached value of the '{@link #getImportLeft() <em>Import Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportLeft()
	 * @generated
	 * @ordered
	 */
	protected ImportDeclaration importLeft;

	/**
	 * The cached value of the '{@link #getImportRight() <em>Import Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportRight()
	 * @generated
	 * @ordered
	 */
	protected ImportDeclaration importRight;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImportDeleteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KDM2JavaDiffPackage.Literals.IMPORT_DELETE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportDeclaration getImportLeft() {
		if (importLeft != null && importLeft.eIsProxy()) {
			InternalEObject oldImportLeft = (InternalEObject)importLeft;
			importLeft = (ImportDeclaration)eResolveProxy(oldImportLeft);
			if (importLeft != oldImportLeft) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KDM2JavaDiffPackage.IMPORT_DELETE__IMPORT_LEFT, oldImportLeft, importLeft));
			}
		}
		return importLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportDeclaration basicGetImportLeft() {
		return importLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImportLeft(ImportDeclaration newImportLeft) {
		ImportDeclaration oldImportLeft = importLeft;
		importLeft = newImportLeft;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.IMPORT_DELETE__IMPORT_LEFT, oldImportLeft, importLeft));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportDeclaration getImportRight() {
		if (importRight != null && importRight.eIsProxy()) {
			InternalEObject oldImportRight = (InternalEObject)importRight;
			importRight = (ImportDeclaration)eResolveProxy(oldImportRight);
			if (importRight != oldImportRight) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KDM2JavaDiffPackage.IMPORT_DELETE__IMPORT_RIGHT, oldImportRight, importRight));
			}
		}
		return importRight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportDeclaration basicGetImportRight() {
		return importRight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImportRight(ImportDeclaration newImportRight) {
		ImportDeclaration oldImportRight = importRight;
		importRight = newImportRight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.IMPORT_DELETE__IMPORT_RIGHT, oldImportRight, importRight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnitChange getCompilationUnitChange() {
		if (eContainerFeatureID() != KDM2JavaDiffPackage.IMPORT_DELETE__COMPILATION_UNIT_CHANGE) return null;
		return (CompilationUnitChange)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompilationUnitChange(CompilationUnitChange newCompilationUnitChange, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCompilationUnitChange, KDM2JavaDiffPackage.IMPORT_DELETE__COMPILATION_UNIT_CHANGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompilationUnitChange(CompilationUnitChange newCompilationUnitChange) {
		if (newCompilationUnitChange != eInternalContainer() || (eContainerFeatureID() != KDM2JavaDiffPackage.IMPORT_DELETE__COMPILATION_UNIT_CHANGE && newCompilationUnitChange != null)) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.IMPORT_DELETE__COMPILATION_UNIT_CHANGE, newCompilationUnitChange, newCompilationUnitChange));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KDM2JavaDiffPackage.IMPORT_DELETE__COMPILATION_UNIT_CHANGE:
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
			case KDM2JavaDiffPackage.IMPORT_DELETE__COMPILATION_UNIT_CHANGE:
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
			case KDM2JavaDiffPackage.IMPORT_DELETE__COMPILATION_UNIT_CHANGE:
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
			case KDM2JavaDiffPackage.IMPORT_DELETE__IMPORT_LEFT:
				if (resolve) return getImportLeft();
				return basicGetImportLeft();
			case KDM2JavaDiffPackage.IMPORT_DELETE__IMPORT_RIGHT:
				if (resolve) return getImportRight();
				return basicGetImportRight();
			case KDM2JavaDiffPackage.IMPORT_DELETE__COMPILATION_UNIT_CHANGE:
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
			case KDM2JavaDiffPackage.IMPORT_DELETE__IMPORT_LEFT:
				setImportLeft((ImportDeclaration)newValue);
				return;
			case KDM2JavaDiffPackage.IMPORT_DELETE__IMPORT_RIGHT:
				setImportRight((ImportDeclaration)newValue);
				return;
			case KDM2JavaDiffPackage.IMPORT_DELETE__COMPILATION_UNIT_CHANGE:
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
			case KDM2JavaDiffPackage.IMPORT_DELETE__IMPORT_LEFT:
				setImportLeft((ImportDeclaration)null);
				return;
			case KDM2JavaDiffPackage.IMPORT_DELETE__IMPORT_RIGHT:
				setImportRight((ImportDeclaration)null);
				return;
			case KDM2JavaDiffPackage.IMPORT_DELETE__COMPILATION_UNIT_CHANGE:
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
			case KDM2JavaDiffPackage.IMPORT_DELETE__IMPORT_LEFT:
				return importLeft != null;
			case KDM2JavaDiffPackage.IMPORT_DELETE__IMPORT_RIGHT:
				return importRight != null;
			case KDM2JavaDiffPackage.IMPORT_DELETE__COMPILATION_UNIT_CHANGE:
				return getCompilationUnitChange() != null;
		}
		return super.eIsSet(featureID);
	}

} //ImportDeleteImpl
