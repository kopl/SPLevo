/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gmt.modisco.java.Statement;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Statement Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.StatementChangeImpl#getChangedStatement <em>Changed Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StatementChangeImpl extends Java2KDMDiffExtensionImpl implements StatementChange {
    /**
     * The cached value of the '{@link #getChangedStatement() <em>Changed Statement</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChangedStatement()
     * @generated
     * @ordered
     */
    protected Statement changedStatement;

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
    public Statement getChangedStatement() {
        if (changedStatement != null && changedStatement.eIsProxy()) {
            InternalEObject oldChangedStatement = (InternalEObject) changedStatement;
            changedStatement = (Statement) eResolveProxy(oldChangedStatement);
            if (changedStatement != oldChangedStatement) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Java2KDMDiffPackage.STATEMENT_CHANGE__CHANGED_STATEMENT, oldChangedStatement,
                            changedStatement));
            }
        }
        return changedStatement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Statement basicGetChangedStatement() {
        return changedStatement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChangedStatement(Statement newChangedStatement) {
        Statement oldChangedStatement = changedStatement;
        changedStatement = newChangedStatement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Java2KDMDiffPackage.STATEMENT_CHANGE__CHANGED_STATEMENT, oldChangedStatement, changedStatement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Java2KDMDiffPackage.STATEMENT_CHANGE__CHANGED_STATEMENT:
            if (resolve)
                return getChangedStatement();
            return basicGetChangedStatement();
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
        case Java2KDMDiffPackage.STATEMENT_CHANGE__CHANGED_STATEMENT:
            setChangedStatement((Statement) newValue);
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
        case Java2KDMDiffPackage.STATEMENT_CHANGE__CHANGED_STATEMENT:
            setChangedStatement((Statement) null);
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
        case Java2KDMDiffPackage.STATEMENT_CHANGE__CHANGED_STATEMENT:
            return changedStatement != null;
        }
        return super.eIsSet(featureID);
    }

} //StatementChangeImpl
