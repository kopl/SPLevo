package org.splevo.modisco.java.vpm.software.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.eclipse.gmt.modisco.java.ReturnStatement;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.eclipse.modisco.java.composition.javaapplication.Java2File;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.eclipse.modisco.java.composition.javaapplication.JavaNodeSourceRegion;
import org.splevo.modisco.java.vpm.software.MoDiscoJavaSoftwareElement;
import org.splevo.modisco.java.vpm.software.softwarePackage;
import org.splevo.modisco.util.SourceConnector;
import org.splevo.vpm.software.SoftwareFactory;
import org.splevo.vpm.software.SourceLocation;
import org.splevo.vpm.software.impl.JavaSoftwareElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Mo Disco Java Software Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.splevo.modisco.java.vpm.software.impl.MoDiscoJavaSoftwareElementImpl#getAstNode
 * <em>Ast Node</em>}</li>
 * <li>
 * {@link org.splevo.modisco.java.vpm.software.impl.MoDiscoJavaSoftwareElementImpl#getJavaApplicationModel
 * <em>Java Application Model</em>}</li>
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
     * The cached value of the '{@link #getJavaApplicationModel() <em>Java Application Model</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getJavaApplicationModel()
     * @generated
     * @ordered
     */
    protected JavaApplication javaApplicationModel;

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

        } else if (node instanceof VariableDeclarationStatement) {
            VariableDeclarationStatement statement = (VariableDeclarationStatement) node;
            return "Variable Declaration Statement: " + statement.getFragments().get(0).getName();

        } else if (node instanceof ReturnStatement) {
            return "Return Statement";

        } else if (node instanceof ImportDeclaration) {
            ImportDeclaration importDecl = (ImportDeclaration) node;
            return "Import: " + importDecl.getImportedElement().getName();

        } else if (node instanceof MethodInvocation) {
            MethodInvocation invocation = (MethodInvocation) node;
            return "Method Invocation: " + invocation.getMethod().getName() + "()";

        } else if (node instanceof Block && node.eContainer() instanceof MethodDeclaration) {
            MethodDeclaration method = (MethodDeclaration) node.eContainer();
            return method.getName() + "()";

        } else if (node instanceof NamedElement) {
            return ((NamedElement) node).getName();
        }

        return "MoDisco AST Node: " + getAstNode().getClass().getSimpleName();
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
    public JavaApplication getJavaApplicationModel() {
        if (javaApplicationModel != null && javaApplicationModel.eIsProxy()) {
            InternalEObject oldJavaApplicationModel = (InternalEObject) javaApplicationModel;
            javaApplicationModel = (JavaApplication) eResolveProxy(oldJavaApplicationModel);
            if (javaApplicationModel != oldJavaApplicationModel) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            softwarePackage.MO_DISCO_JAVA_SOFTWARE_ELEMENT__JAVA_APPLICATION_MODEL,
                            oldJavaApplicationModel, javaApplicationModel));
            }
        }
        return javaApplicationModel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public JavaApplication basicGetJavaApplicationModel() {
        return javaApplicationModel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setJavaApplicationModel(JavaApplication newJavaApplicationModel) {
        JavaApplication oldJavaApplicationModel = javaApplicationModel;
        javaApplicationModel = newJavaApplicationModel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    softwarePackage.MO_DISCO_JAVA_SOFTWARE_ELEMENT__JAVA_APPLICATION_MODEL, oldJavaApplicationModel,
                    javaApplicationModel));
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
        case softwarePackage.MO_DISCO_JAVA_SOFTWARE_ELEMENT__JAVA_APPLICATION_MODEL:
            if (resolve)
                return getJavaApplicationModel();
            return basicGetJavaApplicationModel();
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
        case softwarePackage.MO_DISCO_JAVA_SOFTWARE_ELEMENT__JAVA_APPLICATION_MODEL:
            setJavaApplicationModel((JavaApplication) newValue);
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
        case softwarePackage.MO_DISCO_JAVA_SOFTWARE_ELEMENT__JAVA_APPLICATION_MODEL:
            setJavaApplicationModel((JavaApplication) null);
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
        case softwarePackage.MO_DISCO_JAVA_SOFTWARE_ELEMENT__JAVA_APPLICATION_MODEL:
            return javaApplicationModel != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> {@inheritDoc} <!-- end-user-doc -->
     * 
     * @generated not
     */
    @SuppressWarnings("restriction")
    @Override
    public SourceLocation getSourceLocation() {
        SourceConnector sourceConnector = new SourceConnector(getJavaApplicationModel());
        JavaNodeSourceRegion sourceRegion = sourceConnector.findSourceRegion(getAstNode());

        SourceLocation sourceLocation = null;

        if (sourceRegion != null && sourceRegion.eContainer() instanceof Java2File) {
            Java2File fileMapping = (Java2File) sourceRegion.eContainer();
            String filePath = fileMapping.getFile().getPath();

            sourceLocation = SoftwareFactory.eINSTANCE.createSourceLocation();
            sourceLocation.setFilePath(filePath);
            sourceLocation.setStartLine(sourceRegion.getStartLine());
            sourceLocation.setStartPosition(sourceRegion.getStartPosition());
            sourceLocation.setEndLine(sourceRegion.getEndLine());
            sourceLocation.setEndPosition(sourceRegion.getEndPosition());
        }

        return sourceLocation;
    }

    /**
     * <!-- begin-user-doc --> 
     * Equality is defined by the equality of the referenced ast nodes.
     * {@inheritDoc}
     * <!-- end-user-doc -->
     * 
     * @generated not
     */
    @Override
    public boolean equals(Object arg0) {
        if (getAstNode() != null && arg0 instanceof MoDiscoJavaSoftwareElement) {
            return getAstNode().equals(((MoDiscoJavaSoftwareElement) arg0).getAstNode());
        }
        return super.equals(arg0);
    }
    
    /**
     * <!-- begin-user-doc --> 
     * Equality is defined by the equality of the referenced ast nodes.
     * {@inheritDoc}
     * <!-- end-user-doc -->
     * 
     * @generated not
     */
    @Override
    public int hashCode() {
        return getAstNode().hashCode();
    }

} // MoDiscoJavaSoftwareElementImpl
