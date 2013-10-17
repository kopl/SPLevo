/**
 */
package org.splevo.modisco.java.vpm.software.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.splevo.modisco.java.vpm.software.MoDiscoJavaSoftwareElement;
import org.splevo.modisco.java.vpm.software.softwarePackage;
import org.splevo.vpm.software.impl.JavaSoftwareElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Mo Disco Java Software Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.splevo.modisco.java.vpm.software.impl.MoDiscoJavaSoftwareElementImpl#getAstNode
 * <em>Ast Node</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MoDiscoJavaSoftwareElementImpl extends JavaSoftwareElementImpl implements MoDiscoJavaSoftwareElement {
    /**
     * The cached value of the '{@link #getAstNode() <em>Ast Node</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getAstNode()
     * @generated
     * @ordered
     */
    protected ASTNode astNode;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected MoDiscoJavaSoftwareElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return softwarePackage.Literals.MO_DISCO_JAVA_SOFTWARE_ELEMENT;
    }

    /**
     * <!-- begin-user-doc --> {@inheritDoc} <!-- end-user-doc -->
     * 
     * @generated not
     */
    @Override
    public String getLabel() {

        ASTNode node = getAstNode();
        if (node == null) {
            return "NULL";

        } else if (node instanceof Block && node.eContainer() instanceof MethodDeclaration) {
            MethodDeclaration method = (MethodDeclaration) node.eContainer();
            return method.getName() + "()";

        } else if (node instanceof NamedElement) {
            return ((NamedElement) node).getName();
        }

        return getAstNode().getClass().getSimpleName();
    }

    /**
     * <!-- begin-user-doc --> {@inheritDoc} <!-- end-user-doc -->
     * 
     * @generated not
     */
    @Override
    public String getName() {
        ASTNode node = getAstNode();
        if (node == null) {
            return null;
        }
        if (node instanceof NamedElement) {
            return ((NamedElement) node).getName();
        } else {
            return null;
        }
    }

    /**
     * <!-- begin-user-doc -->
     * {@inheritDoc} <!-- end-user-doc -->
     * 
     * @generated not
     */
    @Override
    public Object getValue() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ASTNode getAstNode() {
        if (astNode != null && astNode.eIsProxy()) {
            InternalEObject oldAstNode = (InternalEObject) astNode;
            astNode = (ASTNode) eResolveProxy(oldAstNode);
            if (astNode != oldAstNode) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            softwarePackage.MO_DISCO_JAVA_SOFTWARE_ELEMENT__AST_NODE, oldAstNode, astNode));
            }
        }
        return astNode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ASTNode basicGetAstNode() {
        return astNode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setAstNode(ASTNode newAstNode) {
        ASTNode oldAstNode = astNode;
        astNode = newAstNode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    softwarePackage.MO_DISCO_JAVA_SOFTWARE_ELEMENT__AST_NODE, oldAstNode, astNode));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case softwarePackage.MO_DISCO_JAVA_SOFTWARE_ELEMENT__AST_NODE:
            if (resolve)
                return getAstNode();
            return basicGetAstNode();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case softwarePackage.MO_DISCO_JAVA_SOFTWARE_ELEMENT__AST_NODE:
            setAstNode((ASTNode) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case softwarePackage.MO_DISCO_JAVA_SOFTWARE_ELEMENT__AST_NODE:
            setAstNode((ASTNode) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case softwarePackage.MO_DISCO_JAVA_SOFTWARE_ELEMENT__AST_NODE:
            return astNode != null;
        }
        return super.eIsSet(featureID);
    }

} // MoDiscoJavaSoftwareElementImpl
