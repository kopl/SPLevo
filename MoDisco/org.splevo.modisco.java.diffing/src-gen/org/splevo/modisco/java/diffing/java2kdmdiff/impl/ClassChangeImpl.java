/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.splevo.modisco.java.diffing.java2kdmdiff.ClassChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.impl.ClassChangeImpl#getChangedClass <em>Changed Class</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassChangeImpl extends Java2KDMDiffExtensionImpl implements ClassChange {
    /**
     * The cached value of the '{@link #getChangedClass() <em>Changed Class</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChangedClass()
     * @generated
     * @ordered
     */
    protected ClassDeclaration changedClass;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ClassChangeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Java2KDMDiffPackage.Literals.CLASS_CHANGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ClassDeclaration getChangedClass() {
        if (changedClass != null && changedClass.eIsProxy()) {
            InternalEObject oldChangedClass = (InternalEObject) changedClass;
            changedClass = (ClassDeclaration) eResolveProxy(oldChangedClass);
            if (changedClass != oldChangedClass) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Java2KDMDiffPackage.CLASS_CHANGE__CHANGED_CLASS, oldChangedClass, changedClass));
            }
        }
        return changedClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ClassDeclaration basicGetChangedClass() {
        return changedClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChangedClass(ClassDeclaration newChangedClass) {
        ClassDeclaration oldChangedClass = changedClass;
        changedClass = newChangedClass;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Java2KDMDiffPackage.CLASS_CHANGE__CHANGED_CLASS,
                    oldChangedClass, changedClass));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Java2KDMDiffPackage.CLASS_CHANGE__CHANGED_CLASS:
            if (resolve)
                return getChangedClass();
            return basicGetChangedClass();
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
        case Java2KDMDiffPackage.CLASS_CHANGE__CHANGED_CLASS:
            setChangedClass((ClassDeclaration) newValue);
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
        case Java2KDMDiffPackage.CLASS_CHANGE__CHANGED_CLASS:
            setChangedClass((ClassDeclaration) null);
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
        case Java2KDMDiffPackage.CLASS_CHANGE__CHANGED_CLASS:
            return changedClass != null;
        }
        return super.eIsSet(featureID);
    }

} //ClassChangeImpl
