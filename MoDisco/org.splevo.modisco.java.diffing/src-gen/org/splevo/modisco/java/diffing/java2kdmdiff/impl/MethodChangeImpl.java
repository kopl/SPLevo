/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.modisco.java.diffing.java2kdmdiff.MethodChange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.MethodChangeImpl#getChangedMethod <em>Changed Method</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodChangeImpl extends Java2KDMDiffExtensionImpl implements MethodChange {
    /**
     * The cached value of the '{@link #getChangedMethod() <em>Changed Method</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChangedMethod()
     * @generated
     * @ordered
     */
    protected AbstractMethodDeclaration changedMethod;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MethodChangeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Java2KDMDiffPackage.Literals.METHOD_CHANGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractMethodDeclaration getChangedMethod() {
        if (changedMethod != null && changedMethod.eIsProxy()) {
            InternalEObject oldChangedMethod = (InternalEObject) changedMethod;
            changedMethod = (AbstractMethodDeclaration) eResolveProxy(oldChangedMethod);
            if (changedMethod != oldChangedMethod) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Java2KDMDiffPackage.METHOD_CHANGE__CHANGED_METHOD, oldChangedMethod, changedMethod));
            }
        }
        return changedMethod;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractMethodDeclaration basicGetChangedMethod() {
        return changedMethod;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChangedMethod(AbstractMethodDeclaration newChangedMethod) {
        AbstractMethodDeclaration oldChangedMethod = changedMethod;
        changedMethod = newChangedMethod;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Java2KDMDiffPackage.METHOD_CHANGE__CHANGED_METHOD,
                    oldChangedMethod, changedMethod));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Java2KDMDiffPackage.METHOD_CHANGE__CHANGED_METHOD:
            if (resolve)
                return getChangedMethod();
            return basicGetChangedMethod();
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
        case Java2KDMDiffPackage.METHOD_CHANGE__CHANGED_METHOD:
            setChangedMethod((AbstractMethodDeclaration) newValue);
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
        case Java2KDMDiffPackage.METHOD_CHANGE__CHANGED_METHOD:
            setChangedMethod((AbstractMethodDeclaration) null);
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
        case Java2KDMDiffPackage.METHOD_CHANGE__CHANGED_METHOD:
            return changedMethod != null;
        }
        return super.eIsSet(featureID);
    }

} //MethodChangeImpl
