/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.compare.diff.metamodel.DifferenceKind;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;

import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.diffing.emfcompare.java2kdmdiff.MethodDelete;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Delete</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.MethodDeleteImpl#getMethodRight <em>Method Right</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.java2kdmdiff.impl.MethodDeleteImpl#getLeftContainer <em>Left Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodDeleteImpl extends MethodChangeImpl implements MethodDelete {
    /**
     * The cached value of the '{@link #getMethodRight() <em>Method Right</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMethodRight()
     * @generated
     * @ordered
     */
    protected AbstractMethodDeclaration methodRight;

    /**
     * The cached value of the '{@link #getLeftContainer() <em>Left Container</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLeftContainer()
     * @generated
     * @ordered
     */
    protected ASTNode leftContainer;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MethodDeleteImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Java2KDMDiffPackage.Literals.METHOD_DELETE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractMethodDeclaration getMethodRight() {
        if (methodRight != null && methodRight.eIsProxy()) {
            InternalEObject oldMethodRight = (InternalEObject)methodRight;
            methodRight = (AbstractMethodDeclaration)eResolveProxy(oldMethodRight);
            if (methodRight != oldMethodRight) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, Java2KDMDiffPackage.METHOD_DELETE__METHOD_RIGHT, oldMethodRight, methodRight));
            }
        }
        return methodRight;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractMethodDeclaration basicGetMethodRight() {
        return methodRight;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMethodRight(AbstractMethodDeclaration newMethodRight) {
        AbstractMethodDeclaration oldMethodRight = methodRight;
        methodRight = newMethodRight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Java2KDMDiffPackage.METHOD_DELETE__METHOD_RIGHT, oldMethodRight, methodRight));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ASTNode getLeftContainer() {
        if (leftContainer != null && leftContainer.eIsProxy()) {
            InternalEObject oldLeftContainer = (InternalEObject)leftContainer;
            leftContainer = (ASTNode)eResolveProxy(oldLeftContainer);
            if (leftContainer != oldLeftContainer) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, Java2KDMDiffPackage.METHOD_DELETE__LEFT_CONTAINER, oldLeftContainer, leftContainer));
            }
        }
        return leftContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ASTNode basicGetLeftContainer() {
        return leftContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLeftContainer(ASTNode newLeftContainer) {
        ASTNode oldLeftContainer = leftContainer;
        leftContainer = newLeftContainer;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Java2KDMDiffPackage.METHOD_DELETE__LEFT_CONTAINER, oldLeftContainer, leftContainer));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case Java2KDMDiffPackage.METHOD_DELETE__METHOD_RIGHT:
                if (resolve) return getMethodRight();
                return basicGetMethodRight();
            case Java2KDMDiffPackage.METHOD_DELETE__LEFT_CONTAINER:
                if (resolve) return getLeftContainer();
                return basicGetLeftContainer();
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
            case Java2KDMDiffPackage.METHOD_DELETE__METHOD_RIGHT:
                setMethodRight((AbstractMethodDeclaration)newValue);
                return;
            case Java2KDMDiffPackage.METHOD_DELETE__LEFT_CONTAINER:
                setLeftContainer((ASTNode)newValue);
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
            case Java2KDMDiffPackage.METHOD_DELETE__METHOD_RIGHT:
                setMethodRight((AbstractMethodDeclaration)null);
                return;
            case Java2KDMDiffPackage.METHOD_DELETE__LEFT_CONTAINER:
                setLeftContainer((ASTNode)null);
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
            case Java2KDMDiffPackage.METHOD_DELETE__METHOD_RIGHT:
                return methodRight != null;
            case Java2KDMDiffPackage.METHOD_DELETE__LEFT_CONTAINER:
                return leftContainer != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * The difference kind of a method delete is always DifferenceKind.DELETION.
     * <!-- end-user-doc -->
     * {@inheritDoc}
     * @generated NOT
     */
    @Override
    public DifferenceKind getKind() {
        return DifferenceKind.DELETION;
    }

} //MethodDeleteImpl
