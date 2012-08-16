/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmt.modisco.java.ImportDeclaration;

import org.splevo.diffing.emfcompare.java2kdmdiff.ImportInsert;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Import Insert</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImportInsertImpl#getImportRight <em>Import Right</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImportInsertImpl extends ImportDeclarationChangeImpl implements ImportInsert {
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
	protected ImportInsertImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Java2KDMDiffPackage.Literals.IMPORT_INSERT;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Java2KDMDiffPackage.IMPORT_INSERT__IMPORT_RIGHT, oldImportRight, importRight));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Java2KDMDiffPackage.IMPORT_INSERT__IMPORT_RIGHT, oldImportRight, importRight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Java2KDMDiffPackage.IMPORT_INSERT__IMPORT_RIGHT:
				if (resolve) return getImportRight();
				return basicGetImportRight();
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
			case Java2KDMDiffPackage.IMPORT_INSERT__IMPORT_RIGHT:
				setImportRight((ImportDeclaration)newValue);
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
			case Java2KDMDiffPackage.IMPORT_INSERT__IMPORT_RIGHT:
				setImportRight((ImportDeclaration)null);
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
			case Java2KDMDiffPackage.IMPORT_INSERT__IMPORT_RIGHT:
				return importRight != null;
		}
		return super.eIsSet(featureID);
	}

} //ImportInsertImpl