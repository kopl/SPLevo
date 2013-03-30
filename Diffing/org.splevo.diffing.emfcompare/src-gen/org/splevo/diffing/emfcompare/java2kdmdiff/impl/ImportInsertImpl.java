/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.compare.diff.metamodel.DifferenceKind;

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
 *   <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.ImportInsertImpl#getImportLeft <em>Import Left</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImportInsertImpl extends ImportDeclarationChangeImpl implements ImportInsert {
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
	public ImportDeclaration getImportLeft() {
        if (importLeft != null && importLeft.eIsProxy()) {
            InternalEObject oldImportLeft = (InternalEObject)importLeft;
            importLeft = (ImportDeclaration)eResolveProxy(oldImportLeft);
            if (importLeft != oldImportLeft) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, Java2KDMDiffPackage.IMPORT_INSERT__IMPORT_LEFT, oldImportLeft, importLeft));
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
            eNotify(new ENotificationImpl(this, Notification.SET, Java2KDMDiffPackage.IMPORT_INSERT__IMPORT_LEFT, oldImportLeft, importLeft));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case Java2KDMDiffPackage.IMPORT_INSERT__IMPORT_LEFT:
                if (resolve) return getImportLeft();
                return basicGetImportLeft();
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
            case Java2KDMDiffPackage.IMPORT_INSERT__IMPORT_LEFT:
                setImportLeft((ImportDeclaration)newValue);
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
            case Java2KDMDiffPackage.IMPORT_INSERT__IMPORT_LEFT:
                setImportLeft((ImportDeclaration)null);
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
            case Java2KDMDiffPackage.IMPORT_INSERT__IMPORT_LEFT:
                return importLeft != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * The difference kind of an import insert is always DifferenceKind.ADDITION.
     * <!-- end-user-doc -->
     * {@inheritDoc}
     * @generated NOT
     */
    @Override
    public DifferenceKind getKind() {
        return DifferenceKind.ADDITION;
    }

} //ImportInsertImpl
