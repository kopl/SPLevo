/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Import Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.ImportChangeImpl#getChangedImport <em>Changed Import</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImportChangeImpl extends Java2KDMDiffExtensionImpl implements ImportChange {
    /**
     * The cached value of the '{@link #getChangedImport() <em>Changed Import</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChangedImport()
     * @generated
     * @ordered
     */
    protected ImportDeclaration changedImport;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ImportChangeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Java2KDMDiffPackage.Literals.IMPORT_CHANGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ImportDeclaration getChangedImport() {
        if (changedImport != null && changedImport.eIsProxy()) {
            InternalEObject oldChangedImport = (InternalEObject) changedImport;
            changedImport = (ImportDeclaration) eResolveProxy(oldChangedImport);
            if (changedImport != oldChangedImport) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Java2KDMDiffPackage.IMPORT_CHANGE__CHANGED_IMPORT, oldChangedImport, changedImport));
            }
        }
        return changedImport;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ImportDeclaration basicGetChangedImport() {
        return changedImport;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChangedImport(ImportDeclaration newChangedImport) {
        ImportDeclaration oldChangedImport = changedImport;
        changedImport = newChangedImport;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Java2KDMDiffPackage.IMPORT_CHANGE__CHANGED_IMPORT,
                    oldChangedImport, changedImport));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Java2KDMDiffPackage.IMPORT_CHANGE__CHANGED_IMPORT:
            if (resolve)
                return getChangedImport();
            return basicGetChangedImport();
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
        case Java2KDMDiffPackage.IMPORT_CHANGE__CHANGED_IMPORT:
            setChangedImport((ImportDeclaration) newValue);
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
        case Java2KDMDiffPackage.IMPORT_CHANGE__CHANGED_IMPORT:
            setChangedImport((ImportDeclaration) null);
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
        case Java2KDMDiffPackage.IMPORT_CHANGE__CHANGED_IMPORT:
            return changedImport != null;
        }
        return super.eIsSet(featureID);
    }

} //ImportChangeImpl
