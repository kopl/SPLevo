/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.FieldChangeImpl#getChangedField <em>Changed Field</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FieldChangeImpl extends Java2KDMDiffExtensionImpl implements FieldChange {
    /**
     * The cached value of the '{@link #getChangedField() <em>Changed Field</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChangedField()
     * @generated
     * @ordered
     */
    protected FieldDeclaration changedField;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FieldChangeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Java2KDMDiffPackage.Literals.FIELD_CHANGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FieldDeclaration getChangedField() {
        if (changedField != null && changedField.eIsProxy()) {
            InternalEObject oldChangedField = (InternalEObject) changedField;
            changedField = (FieldDeclaration) eResolveProxy(oldChangedField);
            if (changedField != oldChangedField) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Java2KDMDiffPackage.FIELD_CHANGE__CHANGED_FIELD, oldChangedField, changedField));
            }
        }
        return changedField;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FieldDeclaration basicGetChangedField() {
        return changedField;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChangedField(FieldDeclaration newChangedField) {
        FieldDeclaration oldChangedField = changedField;
        changedField = newChangedField;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Java2KDMDiffPackage.FIELD_CHANGE__CHANGED_FIELD,
                    oldChangedField, changedField));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Java2KDMDiffPackage.FIELD_CHANGE__CHANGED_FIELD:
            if (resolve)
                return getChangedField();
            return basicGetChangedField();
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
        case Java2KDMDiffPackage.FIELD_CHANGE__CHANGED_FIELD:
            setChangedField((FieldDeclaration) newValue);
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
        case Java2KDMDiffPackage.FIELD_CHANGE__CHANGED_FIELD:
            setChangedField((FieldDeclaration) null);
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
        case Java2KDMDiffPackage.FIELD_CHANGE__CHANGED_FIELD:
            return changedField != null;
        }
        return super.eIsSet(featureID);
    }

} //FieldChangeImpl
